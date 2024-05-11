package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.extra.template.Template;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.EntityConstant;
import io.github.zhoujunlin94.code.gen.constant.MapperConstant;
import lombok.SneakyThrows;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024-05-09-17:29
 */
public abstract class AbstractGenCodeComponent {

    public static void initContext(Table table, Setting context) {
        String entityName = getEntityName(table, context);
        context.put(EntityConstant.ENTITY_NAME, entityName);

        String entityClass = context.get(EntityConstant.PACKAGE_NAME_KEY) + "." + entityName;
        context.put(EntityConstant.ENTITY_CLASS, entityClass);

        String mapperName = getMapperName(entityName);
        context.put(MapperConstant.MAPPER_NAME, mapperName);

        String mapperClass = context.get(MapperConstant.PACKAGE_NAME_KEY) + "." + mapperName;
        context.put(MapperConstant.MAPPER_CLASS, mapperClass);
    }

    protected abstract Template getTemplate();

    protected abstract Map<String, Object> buildBindingMap(Table table, Setting context);

    protected abstract String getDestFileName(Setting context);

    @SneakyThrows
    public void genCode(Table table, Setting context) {
        initContext(table, context);
        Template template = getTemplate();
        Map<String, Object> bindingMap = buildBindingMap(table, context);
        String destFileName = getDestFileName(context);
        template.render(bindingMap, new FileWriter(destFileName));
    }

    protected static String getEntityName(Table table, Setting genCodeSetting) {
        String entityName = StrUtil.toCamelCase(table.getTableName());
        entityName = StrUtil.removePrefix(entityName, genCodeSetting.get(EntityConstant.REMOVE_PREFIX_KEY));
        return entityName;
    }

    protected static String getMapperName(String entityName) {
        return entityName + "Mapper";
    }

    protected void buildImportTypes(List<String> importTypes, Map<String, Object> bindingMap) {
        List<String> externalTypes = new ArrayList<>();
        List<String> internalTypes = new ArrayList<>();
        importTypes.forEach(importType -> {
            if (StrUtil.startWith(importType, "java")) {
                internalTypes.add(importType);
            } else {
                externalTypes.add(importType);
            }
        });
        bindingMap.put("externalTypes", externalTypes);
        bindingMap.put("internalTypes", internalTypes);
    }

}
