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
public class GenEndpointCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected String getTemplateName() {
        return "Endpoint.ftl";
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(CommonConstant.PACKAGE_NAME, context.get(EndpointConstant.PACKAGE_NAME_KEY));
        retMap.put("endPointDesc", "B-" + context.get("camelCaseEntityName"));
        retMap.put("camelCaseEntityName", context.get("camelCaseEntityName"));
        retMap.put("endPointName", context.get(EndpointConstant.ENDPOINT_NAME));

        buildImportTypes(importList(context), retMap);

        retMap.put(ServiceConstant.SERVICE_NAME, context.get(ServiceConstant.SERVICE_NAME));
        retMap.put("lowerServiceName", StrUtil.lowerFirst(context.get(ServiceConstant.SERVICE_NAME)));
        retMap.put(DTOConstant.DTO_NAME, context.get(DTOConstant.DTO_NAME));
        retMap.put(VOConstant.VO_NAME, context.get(VOConstant.VO_NAME));
        retMap.put(DTOConstant.PAGE_QUERY_DTO_NAME, context.get(DTOConstant.PAGE_QUERY_DTO_NAME));

        return retMap;
    }

    @Override
    protected String getDestFileName(Setting context) {
        String destPath = buildDestPath(context, context.get(EndpointConstant.PACKAGE_NAME_KEY));
        FileUtil.mkdir(destPath);
        String endpointName = context.get(EndpointConstant.ENDPOINT_NAME);
        return destPath + StrUtil.SLASH + endpointName + StrUtil.DOT + CommonConstant.JAVA;
    }

    private List<String> importList(Setting context) {
        List<String> importList = new LinkedList<>();
        importList.add("com.github.pagehelper.PageInfo");
        importList.add(context.get(DTOConstant.DTO_CLASS));
        importList.add(context.get(DTOConstant.PAGE_QUERY_DTO_CLASS));
        importList.add(context.get(ServiceConstant.SERVICE_CLASS));
        importList.add(context.get(VOConstant.VO_CLASS));

        importList.add("io.swagger.annotations.Api");
        importList.add("io.swagger.annotations.ApiOperation");
        importList.add("lombok.RequiredArgsConstructor");
        importList.add("org.springframework.util.Assert");
        importList.add("org.springframework.validation.annotation.Validated");
        importList.add("org.springframework.web.bind.annotation.*");

        importList.add("javax.validation.Valid");
        importList.add("javax.validation.constraints.NotNull");
        return importList;
    }
}
