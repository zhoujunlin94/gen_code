package io.github.zhoujunlin94.code.gen.constant;

import cn.hutool.core.collection.CollUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024-05-10-13:37
 */
public class EntityConstant {

    public static final String REMOVE_PREFIX_KEY = "entityRemovePrefix";
    public static final String PACKAGE_NAME_KEY = "entityPackageName";

    public static final String TEMPLATE_NAME = "Entity.ftl";
    public static final String ENTITY_NAME = "entityName";
    public static final String ENTITY_CLASS = "entityClass";

    public static final String FIELD_LIST = "fieldList";

    public static final List<String> EXCLUDE_COLUMNS = CollUtil.newArrayList("is_delete", "created_by", "created_at", "updated_by", "updated_at");

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
