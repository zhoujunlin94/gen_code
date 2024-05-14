package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.extra.template.Template;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.*;
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
        String entityName = StrUtil.toCamelCase(table.getTableName());
        entityName = StrUtil.removePrefix(entityName, context.get("entityRemovePrefix"));
        context.put(EntityConstant.ENTITY_NAME, entityName);

        String entityClass = context.get(EntityConstant.PACKAGE_NAME_KEY) + StrUtil.DOT + entityName;
        context.put(EntityConstant.ENTITY_CLASS, entityClass);

        String mapperName = entityName + "Mapper";
        context.put(MapperConstant.MAPPER_NAME, mapperName);

        String mapperClass = context.get(MapperConstant.PACKAGE_NAME_KEY) + StrUtil.DOT + mapperName;
        context.put(MapperConstant.MAPPER_CLASS, mapperClass);

        String handlerName = entityName + "Handler";
        context.put(HandlerConstant.HANDLER_NAME, handlerName);

        String handlerClass = context.get(HandlerConstant.PACKAGE_NAME_KEY) + StrUtil.DOT + handlerName;
        context.put("handlerClass", handlerClass);


        String dtoName = entityName + "DTO";
        context.put(DTOConstant.DTO_NAME, dtoName);

        String dtoClass = context.get(DTOConstant.PACKAGE_NAME_KEY) + StrUtil.DOT + dtoName;
        context.put("dtoClass", dtoClass);
    }

    protected Template getTemplate() {
        return CommonConstant.ENGINE.getTemplate(getTemplateName());
    }

    protected abstract String getTemplateName();

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

    protected void buildImportTypes(List<String> importTypes, Map<String, Object> bindingMap) {
        List<String> externalTypes = new ArrayList<>();
        List<String> internalTypes = new ArrayList<>();
        importTypes.forEach(importType -> {
            if (StrUtil.startWith(importType, CommonConstant.JAVA)) {
                internalTypes.add(importType);
            } else {
                externalTypes.add(importType);
            }
        });
        bindingMap.put("externalTypes", externalTypes);
        bindingMap.put("internalTypes", internalTypes);
    }

    protected String buildDestPath(Setting context, String packageName) {
        return context.get("srcPath") + StrUtil.SLASH +
                StrUtil.replace(packageName, StrUtil.DOT, StrUtil.SLASH);
    }

}
