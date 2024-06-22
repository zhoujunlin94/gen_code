package io.github.zhoujunlin94.code.gen.constant;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024-06-22-10:36
 */
public class Constant {

    public static final String KEY_AUTHOR = "Author";

    public static final TemplateConfig TEMPLATE_CONFIG = new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH);
    public static final TemplateEngine ENGINE = TemplateUtil.createEngine(TEMPLATE_CONFIG);
    public static final String JAVA = "java";
    public static final String PACKAGE_NAME = "PackageName";
    public static final String FIELD_LIST = "FieldList";

    public static class Entity {
        public static final String KEY_PACKAGE_NAME = "EntityPackageName";

        public static final String ENTITY_NAME = "EntityName";
        public static final String CAMEL_CASE_ENTITY_NAME = "CamelCaseEntityName";
        public static final String ENTITY_CLASS = "EntityClass";

        public static final List<String> EXCLUDE_COLUMNS = CollUtil.newArrayList(
                "is_delete", "created_by", "created_at", "updated_by", "updated_at"
        );

        public static final Map<String, String> IMPORT_TYPE_MAP = new HashMap<>();
        public static final Map<String, String> FIELD_TYPE_MAP = new HashMap<>();

        static {
            IMPORT_TYPE_MAP.put("DECIMAL", "java.math.BigDecimal");
            IMPORT_TYPE_MAP.put("DATETIME", "java.util.Date");

            FIELD_TYPE_MAP.put("TINYINT", "Integer");
            FIELD_TYPE_MAP.put("INT UNSIGNED", "Integer");
            FIELD_TYPE_MAP.put("INT", "Integer");
            FIELD_TYPE_MAP.put("VARCHAR", "String");
            FIELD_TYPE_MAP.put("DECIMAL", "BigDecimal");
            FIELD_TYPE_MAP.put("DATETIME", "Date");
        }
    }

    public static class Mapper {
        public static final String KEY_PACKAGE_NAME = "MapperPackageName";

        public static final String MAPPER_NAME = "MapperName";
        public static final String MAPPER_CLASS = "MapperClass";
    }

    public static class MapperXml {
        public static final Map<String, String> TYPE_NAME_MAP = new HashMap<>();

        static {
            TYPE_NAME_MAP.put("INT UNSIGNED", "INTEGER");
            TYPE_NAME_MAP.put("INT", "INTEGER");
            TYPE_NAME_MAP.put("DATETIME", "TIMESTAMP");
        }
    }

    public static class Handler {
        public static final String KEY_PACKAGE_NAME = "HandlerPackageName";

        public static final String HANDLER_NAME = "HandlerName";
        public static final String HANDLER_CLASS = "HandlerClass";
    }

    public static class DTO {
        public static final String KEY_PACKAGE_NAME = "DTOPackageName";
        public static final String KEY_PAGE_QUERY_CLASS = "PageQueryClass";

        public static final String DTO_NAME = "DTOName";
        public static final String DTO_CLASS = "DTOClass";
        public static final String DTO_PACKAGE = "DTOPackage";

        public static final String PAGE_QUERY_DTO_NAME = "PageQueryDTOName";
        public static final String PAGE_QUERY_DTO_CLASS = "PageQueryDTOClass";
    }

    public static class VO {
        public static final String KEY_PACKAGE_NAME = "VOPackageName";

        public static final String VO_NAME = "VOName";
        public static final String VO_CLASS = "VOClass";

        public static final String VO_PACKAGE = "VOPackage";
    }

    public static class Service {
        public static final String KEY_PACKAGE_NAME = "ServicePackageName";

        public static final String SERVICE_NAME = "ServiceName";
        public static final String SERVICE_CLASS = "ServiceClass";
        public static final String SERVICE_PACKAGE = "ServicePackage";

        public static final String SERVICE_IMPL_NAME = "ServiceImplName";
    }

    public static class Endpoint {
        public static final String KEY_PACKAGE_NAME = "EndPointPackageName";

        public static final String ENDPOINT_PACKAGE = "EndPointPackage";

        public static final String ENDPOINT_NAME = "EndPointName";
    }

}
