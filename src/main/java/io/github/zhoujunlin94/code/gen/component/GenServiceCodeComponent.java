package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.Constant;
import io.github.zhoujunlin94.code.gen.constant.Constant.Service;
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
public class GenServiceCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected String getTemplateName() {
        return "Service.ftl";
    }

    @Override
    protected String getDestFileName(Setting context) {
        String destPath = buildDestPath(context, context.get(Service.SERVICE_PACKAGE));
        FileUtil.mkdir(destPath);
        String serviceName = context.get(Service.SERVICE_NAME);
        return destPath + StrUtil.SLASH + serviceName + StrUtil.DOT + Constant.JAVA;
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(Constant.PACKAGE_NAME, context.get(Service.SERVICE_PACKAGE));

        buildImportTypes(importList(context), retMap);

        retMap.put(Service.SERVICE_NAME, context.get(Service.SERVICE_NAME));
        retMap.put(DTO.DTO_NAME, context.get(DTO.DTO_NAME));
        retMap.put(DTO.PAGE_QUERY_DTO_NAME, context.get(DTO.PAGE_QUERY_DTO_NAME));
        retMap.put(VO.VO_NAME, context.get(VO.VO_NAME));
        return retMap;
    }



    private List<String> importList(Setting context) {
        List<String> importList = new LinkedList<>();
        importList.add(context.get(DTO.DTO_CLASS));
        importList.add(context.get(DTO.PAGE_QUERY_DTO_CLASS));
        importList.add(context.get(VO.VO_CLASS));
        importList.add("com.github.pagehelper.PageInfo");
        return importList;
    }
}
