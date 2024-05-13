package io.github.zhoujunlin94.code.gen.constant;

import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;

/**
 * @author zhoujunlin
 * @date 2024-05-09-17:27
 */
public class CommonConstant {

    public static final TemplateConfig TEMPLATE_CONFIG = new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH);
    public static final TemplateEngine ENGINE = TemplateUtil.createEngine(TEMPLATE_CONFIG);

    public static final String GEN_CODE_SETTING = "genCode.setting";
    public static final String TABLE = "table";

    public static final String SRC_PATH = "srcPath";

    public static final String TABLE_NAME = "tableName";
    public static final String PACKAGE_NAME = "packageName";
    public static final String JAVA = "java";
    public static final String EXTERNAL_TYPES = "externalTypes";
    public static final String INTERNAL_TYPES = "internalTypes";

    public static final String XML = "xml";

}
