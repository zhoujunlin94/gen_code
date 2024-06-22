package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.Constant;
import io.github.zhoujunlin94.code.gen.constant.Constant.Mapper;
import io.github.zhoujunlin94.code.gen.constant.Constant.Entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024-05-09-17:29
 */
public class GenMapperCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected String getTemplateName() {
        return "Mapper.ftl";
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(Constant.PACKAGE_NAME, context.get(Mapper.KEY_PACKAGE_NAME));

        buildImportTypes(importList(context), retMap);

        retMap.put(Mapper.MAPPER_NAME, context.get(Mapper.MAPPER_NAME));
        retMap.put(Entity.ENTITY_NAME, context.get(Entity.ENTITY_NAME));

        return retMap;
    }

    @Override
    protected String getDestFileName(Setting context) {
        String destPath = buildDestPath(context, context.get(Mapper.KEY_PACKAGE_NAME));
        FileUtil.mkdir(destPath);
        String mapperName = context.get(Mapper.MAPPER_NAME);
        return destPath + StrUtil.SLASH + mapperName + StrUtil.DOT + Constant.JAVA;
    }

    private List<String> importList(Setting context) {
        List<String> importList = new LinkedList<>();
        importList.add(context.get(Entity.ENTITY_CLASS));
        importList.add(context.get("TKMapperClass"));
        importList.add("org.apache.ibatis.annotations.Mapper");
        return importList;
    }
}
