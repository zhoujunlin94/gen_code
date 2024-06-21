package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.CommonConstant;
import io.github.zhoujunlin94.code.gen.constant.DTOConstant;

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
        String destPath = buildDestPath(context, context.get(DTOConstant.PACKAGE_NAME_KEY));
        FileUtil.mkdir(destPath);
        String pageQueryDtoName = context.get(DTOConstant.PAGE_QUERY_DTO_NAME);
        return destPath + StrUtil.SLASH + pageQueryDtoName + StrUtil.DOT + CommonConstant.JAVA;
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(CommonConstant.PACKAGE_NAME, context.get(DTOConstant.PACKAGE_NAME_KEY));
        String pageQuery = context.get("PageQuery");
        String pageQueryClass = CollUtil.getLast(StrUtil.splitTrim(pageQuery, StrUtil.DOT));
        retMap.put("PageQueryClass", pageQueryClass);
        buildImportTypes(importList(context), retMap);

        retMap.put(DTOConstant.PAGE_QUERY_DTO_NAME, context.get(DTOConstant.PAGE_QUERY_DTO_NAME));
        retMap.put("pageQueryDTODesc", table.getComment() + " 分页参数");
        return retMap;
    }

    private List<String> importList(Setting context) {
        List<String> importList = new LinkedList<>();
        importList.add("io.swagger.annotations.ApiModel");
        importList.add("lombok.Data");
        importList.add("java.io.Serializable");
        importList.add(context.get("PageQuery"));
        return importList;
    }


}
