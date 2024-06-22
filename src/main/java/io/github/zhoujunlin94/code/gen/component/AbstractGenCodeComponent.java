package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.extra.template.Template;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.Constant;
import io.github.zhoujunlin94.code.gen.constant.Constant.*;
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
        // ===== Entity =====
        String tableName = table.getTableName();
        String entityName = StrUtil.toCamelCase(tableName);
        entityName = StrUtil.removePrefix(entityName, context.get("EntityRemovePrefix"));
        context.put(Entity.ENTITY_NAME, entityName);
        context.put(Entity.CAMEL_CASE_ENTITY_NAME, StrUtil.toCamelCase(entityName, '-'));
        String entityClass = context.get(Entity.KEY_PACKAGE_NAME) + StrUtil.DOT + entityName;
        context.put(Entity.ENTITY_CLASS, entityClass);

        String mapperName = entityName + "Mapper";
        context.put(Mapper.MAPPER_NAME, mapperName);
        String mapperClass = context.get(Mapper.KEY_PACKAGE_NAME) + StrUtil.DOT + mapperName;
        context.put(Mapper.MAPPER_CLASS, mapperClass);

        String handlerName = entityName + "Handler";
        context.put(Handler.HANDLER_NAME, handlerName);
        String handlerClass = context.get(Handler.KEY_PACKAGE_NAME) + StrUtil.DOT + handlerName;
        context.put(Handler.HANDLER_CLASS, handlerClass);

        String dtoName = entityName + "DTO";
        context.put(DTO.DTO_NAME, dtoName);
        String dtoPackageName = context.get(DTO.KEY_PACKAGE_NAME) + StrUtil.DOT + entityName.toLowerCase();
        context.put(DTO.DTO_PACKAGE, dtoPackageName);
        String dtoClass = dtoPackageName + StrUtil.DOT + dtoName;
        context.put(DTO.DTO_CLASS, dtoClass);

        String pageQueryDtoName = entityName + "PageQueryDTO";
        context.put(DTO.PAGE_QUERY_DTO_NAME, pageQueryDtoName);
        String pageQueryDtoClass = dtoPackageName + StrUtil.DOT + pageQueryDtoName;
        context.put(DTO.PAGE_QUERY_DTO_CLASS, pageQueryDtoClass);

        String voName = entityName + "VO";
        context.put(VO.VO_NAME, voName);
        String voPackageName = context.get(VO.KEY_PACKAGE_NAME) + StrUtil.DOT + entityName.toLowerCase();
        context.put(VO.VO_PACKAGE, voPackageName);
        String voClass = voPackageName + StrUtil.DOT + voName;
        context.put(VO.VO_CLASS, voClass);

        String serviceName = entityName + "Service";
        context.put(Service.SERVICE_NAME, serviceName);
        String servicePackageName = context.get(Service.KEY_PACKAGE_NAME) + StrUtil.DOT + entityName.toLowerCase();
        context.put(Service.SERVICE_PACKAGE, servicePackageName);
        String serviceClass = servicePackageName + StrUtil.DOT + serviceName;
        context.put(Service.SERVICE_CLASS, serviceClass);

        String serviceImplName = entityName + "ServiceImpl";
        context.put(Service.SERVICE_IMPL_NAME, serviceImplName);

        String endpointName = entityName + "Endpoint";
        context.put(Endpoint.ENDPOINT_NAME, endpointName);
    }

    protected Template getTemplate() {
        return Constant.ENGINE.getTemplate(getTemplateName());
    }

    protected abstract String getTemplateName();

    protected abstract Map<String, Object> buildBindingMap(Table table, Setting context);

    protected abstract String getDestFileName(Setting context);

    @SneakyThrows
    public void genCode(Table table, Setting context) {
        Template template = getTemplate();
        Map<String, Object> bindingMap = buildBindingMap(table, context);
        bindingMap.put(Constant.KEY_AUTHOR, context.get(Constant.KEY_AUTHOR));
        String destFileName = getDestFileName(context);
        template.render(bindingMap, new FileWriter(destFileName));
    }

    protected void buildImportTypes(List<String> importTypes, Map<String, Object> bindingMap) {
        List<String> externalTypes = new ArrayList<>();
        List<String> internalTypes = new ArrayList<>();
        importTypes.forEach(importType -> {
            if (StrUtil.startWith(importType, Constant.JAVA)) {
                internalTypes.add(importType);
            } else {
                externalTypes.add(importType);
            }
        });
        bindingMap.put("ExternalTypes", externalTypes);
        bindingMap.put("InternalTypes", internalTypes);
    }

    protected String buildDestPath(Setting context, String packageName) {
        return context.get("SrcPath") + StrUtil.SLASH +
                StrUtil.replace(packageName, StrUtil.DOT, StrUtil.SLASH);
    }

}
