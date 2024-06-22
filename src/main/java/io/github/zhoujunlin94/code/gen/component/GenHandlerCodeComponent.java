package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.Constant;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024-05-09-17:29
 */
public class GenHandlerCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected String getTemplateName() {
        return "Handler.ftl";
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(Constant.PACKAGE_NAME, context.get(Constant.Handler.PACKAGE_NAME_KEY));

        buildImportTypes(importList(context), retMap);

        retMap.put(Constant.Handler.HANDLER_NAME, context.get(Constant.Handler.HANDLER_NAME));
        retMap.put(Constant.Mapper.MAPPER_NAME, context.get(Constant.Mapper.MAPPER_NAME));
        retMap.put(Constant.Entity.ENTITY_NAME, context.get(Constant.Entity.ENTITY_NAME));
        retMap.put(Constant.DTO.PAGE_QUERY_DTO_NAME, context.get(Constant.DTO.PAGE_QUERY_DTO_NAME));

        return retMap;
    }

    @Override
    protected String getDestFileName(Setting context) {
        String destPath = buildDestPath(context, context.get(Constant.Handler.PACKAGE_NAME_KEY));
        FileUtil.mkdir(destPath);
        String mapperName = context.get(Constant.Handler.HANDLER_NAME);
        return destPath + StrUtil.SLASH + mapperName + StrUtil.DOT + Constant.JAVA;
    }

    private List<String> importList(Setting context) {
        List<String> importList = new LinkedList<>();
        importList.add(context.get(Constant.DTO.PAGE_QUERY_DTO_CLASS));
        importList.add(context.get(Constant.Entity.ENTITY_CLASS));
        importList.add(context.get(Constant.Mapper.MAPPER_CLASS));
        importList.add(context.get("tkHandler"));
        importList.add("org.springframework.stereotype.Repository");
        importList.add("tk.mybatis.mapper.weekend.Weekend");

        importList.add("java.util.List");
        return importList;
    }
}
