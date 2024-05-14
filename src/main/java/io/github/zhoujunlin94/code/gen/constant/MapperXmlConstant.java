package io.github.zhoujunlin94.code.gen.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024-05-11-9:59
 */
public class MapperXmlConstant {

    public static final Map<String, String> TYPE_NAME_MAP = new HashMap<>();

    static {
        TYPE_NAME_MAP.put("INT UNSIGNED", "INTEGER");
        TYPE_NAME_MAP.put("INT", "INTEGER");
        TYPE_NAME_MAP.put("DATETIME", "TIMESTAMP");
    }

}
