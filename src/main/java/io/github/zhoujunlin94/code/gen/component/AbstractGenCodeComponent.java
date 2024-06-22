package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.extra.template.Template;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.Constant;
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
        String tableName = table.getTableName();
        String entityName = StrUtil.toCamelCase(tableName);
        entityName = StrUtil.removePrefix(entityName, context.get(Constant.Entity.ENTITY_REMOVE_PREFIX_KEY));
        context.put(Constant.Entity.ENTITY_NAME, entityName);
        context.put(Constant.Entity.CAMEL_CASE_ENTITY_NAME, StrUtil.toCamelCase(entityName, '-'));
        String entityClass = context.get(Constant.Entity.PACKAGE_NAME_KEY) + StrUtil.DOT + entityName;
        context.put(Constant.Entity.ENTITY_CLASS, entityClass);

        String mapperName = entityName + Constant.Mapper.MAPPER;
        context.put(Constant.Mapper.MAPPER_NAME, mapperName);
        String mapperClass = context.get(Constant.Mapper.PACKAGE_NAME_KEY) + StrUtil.DOT + mapperName;
        context.put(Constant.Mapper.MAPPER_CLASS, mapperClass);

        String handlerName = entityName + Constant.Handler.HANDLER;
        context.put(Constant.Handler.HANDLER_NAME, handlerName);
        String handlerClass = context.get(Constant.Handler.PACKAGE_NAME_KEY) + StrUtil.DOT + handlerName;
        context.put(Constant.Handler.HANDLER_CLASS, handlerClass);

        String dtoName = entityName + Constant.DTO.DTO;
        context.put(Constant.DTO.DTO_NAME, dtoName);
        String dtoClass = context.get(Constant.DTO.PACKAGE_NAME_KEY) + StrUtil.DOT + entityName.toLowerCase() + StrUtil.DOT + dtoName;
        context.put(Constant.DTO.DTO_CLASS, dtoClass);

        String pageQueryDtoName = entityName + Constant.DTO.PAGE_QUERY;
        context.put(Constant.DTO.PAGE_QUERY_DTO_NAME, pageQueryDtoName);
        String pageQueryDtoClass = context.get(Constant.DTO.PACKAGE_NAME_KEY) + StrUtil.DOT + entityName.toLowerCase() + StrUtil.DOT + pageQueryDtoName;
        context.put(Constant.DTO.PAGE_QUERY_DTO_CLASS, pageQueryDtoClass);

        String voName = entityName + Constant.VO.VO;
        context.put(Constant.VO.VO_NAME, voName);
        String voClass = context.get(Constant.VO.PACKAGE_NAME_KEY) + StrUtil.DOT + entityName.toLowerCase() + StrUtil.DOT + voName;
        context.put(Constant.VO.VO_CLASS, voClass);

        String serviceName = entityName + Constant.Service.SERVICE;
        context.put(Constant.Service.SERVICE_NAME, serviceName);
        String serviceClass = context.get(Constant.Service.PACKAGE_NAME_KEY) + StrUtil.DOT + entityName.toLowerCase() + StrUtil.DOT + serviceName;
        context.put(Constant.Service.SERVICE_CLASS, serviceClass);

        String serviceImplName = entityName + Constant.Service.SERVICE_IMPL;
        context.put(Constant.Service.SERVICE_IMPL_NAME, serviceImplName);

        String endpointName = entityName + Constant.Endpoint.ENDPOINT;
        context.put(Constant.Endpoint.ENDPOINT_NAME, endpointName);
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
        bindingMap.put(Constant.AUTHOR_KEY, context.get(Constant.AUTHOR_KEY));
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
        bindingMap.put(Constant.EXTERNAL_TYPES, externalTypes);
        bindingMap.put(Constant.INTERNAL_TYPES, internalTypes);
    }

    protected String buildDestPath(Setting context, String packageName) {
        return context.get(Constant.SRC_PATH_KEY) + StrUtil.SLASH +
                StrUtil.replace(packageName, StrUtil.DOT, StrUtil.SLASH);
    }

}
