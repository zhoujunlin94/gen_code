package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.Constant;
import io.github.zhoujunlin94.code.gen.constant.Constant.Endpoint;
import io.github.zhoujunlin94.code.gen.constant.Constant.Entity;
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
public class GenEndpointCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected String getTemplateName() {
        return "Endpoint.ftl";
    }

    @Override
    protected String getDestFileName(Setting context) {
        String destPath = buildDestPath(context, context.get(Endpoint.KEY_PACKAGE_NAME));
        FileUtil.mkdir(destPath);
        String endpointName = context.get(Endpoint.ENDPOINT_NAME);
        return destPath + StrUtil.SLASH + endpointName + StrUtil.DOT + Constant.JAVA;
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(Constant.PACKAGE_NAME, context.get(Endpoint.KEY_PACKAGE_NAME));
        retMap.put("EndpointDesc", "B-" + context.get(Entity.CAMEL_CASE_ENTITY_NAME));
        retMap.put(Entity.CAMEL_CASE_ENTITY_NAME, context.get(Entity.CAMEL_CASE_ENTITY_NAME));
        retMap.put(Endpoint.ENDPOINT_NAME, context.get(Endpoint.ENDPOINT_NAME));

        buildImportTypes(importList(context), retMap);

        retMap.put(Service.SERVICE_NAME, context.get(Service.SERVICE_NAME));
        retMap.put("lowerFirstServiceName", StrUtil.lowerFirst(context.get(Service.SERVICE_NAME)));
        retMap.put(DTO.DTO_NAME, context.get(DTO.DTO_NAME));
        retMap.put(VO.VO_NAME, context.get(VO.VO_NAME));
        retMap.put(DTO.PAGE_QUERY_DTO_NAME, context.get(DTO.PAGE_QUERY_DTO_NAME));

        return retMap;
    }



    private List<String> importList(Setting context) {
        List<String> importList = new LinkedList<>();
        importList.add("com.github.pagehelper.PageInfo");
        importList.add(context.get(DTO.DTO_CLASS));
        importList.add(context.get(DTO.PAGE_QUERY_DTO_CLASS));
        importList.add(context.get(Service.SERVICE_CLASS));
        importList.add(context.get(VO.VO_CLASS));

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
