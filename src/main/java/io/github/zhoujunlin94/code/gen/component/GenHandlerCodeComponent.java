package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.*;

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

        retMap.put(CommonConstant.PACKAGE_NAME, context.get(HandlerConstant.PACKAGE_NAME_KEY));

        buildImportTypes(importList(context), retMap);

        retMap.put(HandlerConstant.HANDLER_NAME, context.get(HandlerConstant.HANDLER_NAME));
        retMap.put(MapperConstant.MAPPER_NAME, context.get(MapperConstant.MAPPER_NAME));
        retMap.put(EntityConstant.ENTITY_NAME, context.get(EntityConstant.ENTITY_NAME));
        retMap.put(DTOConstant.PAGE_QUERY_DTO_NAME, context.get(DTOConstant.PAGE_QUERY_DTO_NAME));

        return retMap;
    }

    @Override
    protected String getDestFileName(Setting context) {
        String destPath = buildDestPath(context, context.get(HandlerConstant.PACKAGE_NAME_KEY));
        FileUtil.mkdir(destPath);
        String mapperName = context.get(HandlerConstant.HANDLER_NAME);
        return destPath + StrUtil.SLASH + mapperName + StrUtil.DOT + CommonConstant.JAVA;
    }

    private List<String> importList(Setting context) {
        List<String> importList = new LinkedList<>();
        importList.add(context.get(DTOConstant.PAGE_QUERY_DTO_CLASS));
        importList.add(context.get(EntityConstant.ENTITY_CLASS));
        importList.add(context.get(MapperConstant.MAPPER_CLASS));
        importList.add(context.get("tkHandler"));
        importList.add("org.springframework.stereotype.Repository");
        importList.add("tk.mybatis.mapper.weekend.Weekend");

        importList.add("java.util.List");
        return importList;
    }
}
