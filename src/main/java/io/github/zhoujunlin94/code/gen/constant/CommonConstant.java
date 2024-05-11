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

}
