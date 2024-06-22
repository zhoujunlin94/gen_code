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

    public static final String GEN_CODE_SETTING = "genCode.setting";

    public static final String TABLES_KEY = "tables";
    public static final String AUTHOR_KEY = "Author";
    public static final String SRC_PATH_KEY = "SrcPath";


    public static final TemplateConfig TEMPLATE_CONFIG = new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH);
    public static final TemplateEngine ENGINE = TemplateUtil.createEngine(TEMPLATE_CONFIG);
    public static final String JAVA = "java";
    public static final String PACKAGE_NAME = "PackageName";
    public static final String TABLE_NAME = "TableName";
    public static final String EXTERNAL_TYPES = "ExternalTypes";
    public static final String INTERNAL_TYPES = "InternalTypes";
    public static final String FIELD_LIST = "FieldList";

    public static class Entity {
        public static final String ENTITY_REMOVE_PREFIX_KEY = "EntityRemovePrefix";
        public static final String PACKAGE_NAME_KEY = "EntityPackageName";

        public static final String ENTITY_NAME = "EntityName";
        public static final String CAMEL_CASE_ENTITY_NAME = "camel-case-entity-name";
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
        public static final String PACKAGE_NAME_KEY = "MapperPackageName";

        public static final String MAPPER = "Mapper";
        public static final String MAPPER_NAME = "MapperName";
        public static final String MAPPER_CLASS = "MapperClass";
    }

    public static class MapperXml {
        public static final String XML = "xml";

        public static final Map<String, String> TYPE_NAME_MAP = new HashMap<>();

        static {
            TYPE_NAME_MAP.put("INT UNSIGNED", "INTEGER");
            TYPE_NAME_MAP.put("INT", "INTEGER");
            TYPE_NAME_MAP.put("DATETIME", "TIMESTAMP");
        }
    }

    public static class Handler {
        public static final String HANDLER = "Handler";
        public static final String HANDLER_NAME = "HandlerName";
        public static final String HANDLER_CLASS = "HandlerClass";

        public static final String PACKAGE_NAME_KEY = "HandlerPackageName";

    }

    public static class DTO {
        public static final String DTO = "DTO";
        public static final String DTO_DESC = "DTODesc";
        public static final String DTO_NAME = "DTOName";
        public static final String DTO_CLASS = "DTOClass";

        public static final String PAGE_QUERY = "PageQueryDTO";
        public static final String PAGE_QUERY_DTO_NAME = "PageQueryDTOName";
        public static final String PAGE_QUERY_DTO_CLASS = "PageQueryDTOClass";
        public static final String PAGE_QUERY_DTO_DESC = "PageQueryDTODesc";

        public static final String PACKAGE_NAME_KEY = "DTOPackageName";
        public static final String PAGE_QUERY_CLASS_KEY = "PageQueryClass";
    }

    public static class VO {
        public static final String VO = "VO";

        public static final String PACKAGE_NAME_KEY = "VOPackageName";

        public static final String VO_NAME = "VOName";

        public static final String VO_CLASS = "VOClass";
    }

    public static class Service {
        public static final String SERVICE = "Service";
        public static final String SERVICE_NAME = "ServiceName";
        public static final String SERVICE_CLASS = "ServiceClass";

        public static final String SERVICE_IMPL = "ServiceImpl";
        public static final String SERVICE_IMPL_NAME = "ServiceImplName";

        public static final String PACKAGE_NAME_KEY = "ServicePackageName";
    }

    public static class Endpoint {
        public static final String ENDPOINT = "Endpoint";
        public static final String ENDPOINT_NAME = "EndPointName";

        public static final String PACKAGE_NAME_KEY = "EndPointPackageName";
    }

    public static class FTL {
        public static final String ENTITY = "Entity.ftl";
        public static final String DTO = "DTO.ftl";
        public static final String PAGE_QUERY_DTO = "PageQueryDTO.ftl";
    }

}
