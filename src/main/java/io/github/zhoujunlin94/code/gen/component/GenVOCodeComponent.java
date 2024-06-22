package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.Constant;
import io.github.zhoujunlin94.code.gen.constant.Constant.DTO;
import io.github.zhoujunlin94.code.gen.constant.Constant.VO;

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
        String destPath = buildDestPath(context, context.get(VO.VO_PACKAGE));
        FileUtil.mkdir(destPath);
        String voName = context.get(VO.VO_NAME);
        return destPath + StrUtil.SLASH + voName + StrUtil.DOT + Constant.JAVA;
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(Constant.PACKAGE_NAME, context.get(VO.VO_PACKAGE));
        buildImportTypes(importList(context), retMap);
        retMap.put(DTO.DTO_NAME, context.get(DTO.DTO_NAME));
        retMap.put(VO.VO_NAME, context.get(VO.VO_NAME));
        retMap.put("VODesc", context.get(VO.VO_NAME));
        return retMap;
    }

    private List<String> importList(Setting context) {
        List<String> importList = new LinkedList<>();
        importList.add(context.get(DTO.DTO_CLASS));
        importList.add("io.swagger.annotations.ApiModel");
        importList.add("lombok.Data");

        return importList;
    }


}
