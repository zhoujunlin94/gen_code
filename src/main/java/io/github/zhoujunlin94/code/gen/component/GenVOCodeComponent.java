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
public class GenVOCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected String getTemplateName() {
        return "VO.ftl";
    }

    @Override
    protected String getDestFileName(Setting context) {
        String destPath = buildDestPath(context, context.get(Constant.VO.PACKAGE_NAME_KEY));
        FileUtil.mkdir(destPath);
        String voName = context.get(Constant.VO.VO_NAME);
        return destPath + StrUtil.SLASH + voName + StrUtil.DOT + Constant.JAVA;
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(Constant.PACKAGE_NAME, context.get(Constant.VO.PACKAGE_NAME_KEY));
        buildImportTypes(importList(context), retMap);
        retMap.put(Constant.DTO.DTO_NAME, context.get(Constant.DTO.DTO_NAME));
        retMap.put(Constant.VO.VO_NAME, context.get(Constant.VO.VO_NAME));
        retMap.put("voDesc", table.getComment() + " VO");
        return retMap;
    }

    private List<String> importList(Setting context) {
        List<String> importList = new LinkedList<>();
        importList.add(context.get(Constant.DTO.DTO_CLASS));
        importList.add("io.swagger.annotations.ApiModel");
        importList.add("lombok.Data");

        return importList;
    }


}
