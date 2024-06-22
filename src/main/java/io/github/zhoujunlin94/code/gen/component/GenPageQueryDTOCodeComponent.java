package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.Constant;
import io.github.zhoujunlin94.code.gen.constant.Constant.DTO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024-05-09-17:29
 */
public class GenPageQueryDTOCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected String getTemplateName() {
        return "PageQueryDTO.ftl";
    }

    @Override
    protected String getDestFileName(Setting context) {
        String destPath = buildDestPath(context, context.get(DTO.DTO_PACKAGE));
        FileUtil.mkdir(destPath);
        String pageQueryDtoName = context.get(DTO.PAGE_QUERY_DTO_NAME);
        return destPath + StrUtil.SLASH + pageQueryDtoName + StrUtil.DOT + Constant.JAVA;
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(Constant.PACKAGE_NAME, context.get(DTO.DTO_PACKAGE));
        String pageQuery = context.get(DTO.KEY_PAGE_QUERY_CLASS);
        String pageQueryClass = CollUtil.getLast(StrUtil.splitTrim(pageQuery, StrUtil.DOT));
        retMap.put(DTO.KEY_PAGE_QUERY_CLASS, pageQueryClass);
        buildImportTypes(importList(context), retMap);

        retMap.put(DTO.PAGE_QUERY_DTO_NAME, context.get(DTO.PAGE_QUERY_DTO_NAME));
        retMap.put("PageQueryDTODesc", context.get(DTO.PAGE_QUERY_DTO_NAME) + " 分页参数");
        return retMap;
    }

    private List<String> importList(Setting context) {
        List<String> importList = new LinkedList<>();
        importList.add("io.swagger.annotations.ApiModel");
        importList.add("lombok.Data");
        importList.add(context.get(DTO.KEY_PAGE_QUERY_CLASS));
        return importList;
    }

}
