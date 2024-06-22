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
public class GenServiceImplCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected String getTemplateName() {
        return "ServiceImpl.ftl";
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(CommonConstant.PACKAGE_NAME, context.get(ServiceConstant.PACKAGE_NAME_KEY) + ".impl");

        buildImportTypes(importList(context), retMap);

        retMap.put(ServiceConstant.SERVICE_IMPL_NAME, context.get(ServiceConstant.SERVICE_IMPL_NAME));
        retMap.put(ServiceConstant.SERVICE_NAME, context.get(ServiceConstant.SERVICE_NAME));
        retMap.put(HandlerConstant.HANDLER_NAME, context.get(HandlerConstant.HANDLER_NAME));
        retMap.put("lowerHandlerName", StrUtil.lowerFirst(context.get(HandlerConstant.HANDLER_NAME)));
        retMap.put(DTOConstant.DTO_NAME, context.get(DTOConstant.DTO_NAME));
        retMap.put(EntityConstant.ENTITY_NAME, context.get(EntityConstant.ENTITY_NAME));
        retMap.put(DTOConstant.PAGE_QUERY_DTO_NAME, context.get(DTOConstant.PAGE_QUERY_DTO_NAME));
        retMap.put(VOConstant.VO_NAME, context.get(VOConstant.VO_NAME));
        return retMap;
    }

    @Override
    protected String getDestFileName(Setting context) {
        String destPath = buildDestPath(context, context.get(ServiceConstant.PACKAGE_NAME_KEY) + ".impl");
        FileUtil.mkdir(destPath);
        String serviceImplName = context.get(ServiceConstant.SERVICE_IMPL_NAME);
        return destPath + StrUtil.SLASH + serviceImplName + StrUtil.DOT + CommonConstant.JAVA;
    }

    private List<String> importList(Setting context) {
        List<String> importList = new LinkedList<>();
        importList.add("cn.hutool.core.bean.BeanUtil");
        importList.add("com.github.pagehelper.PageHelper");
        importList.add("com.github.pagehelper.PageInfo");
        importList.add(context.get(DTOConstant.DTO_CLASS));
        importList.add(context.get(DTOConstant.PAGE_QUERY_DTO_CLASS));
        importList.add(context.get(EntityConstant.ENTITY_CLASS));
        importList.add(context.get(HandlerConstant.HANDLER_CLASS));
        importList.add(context.get(ServiceConstant.SERVICE_CLASS));
        importList.add(context.get(VOConstant.VO_CLASS));
        importList.add(context.get("PageUtil"));
        importList.add("lombok.RequiredArgsConstructor");
        importList.add("lombok.extern.slf4j.Slf4j");
        importList.add("org.springframework.stereotype.Service");

        importList.add("java.util.List");
        importList.add("java.util.stream.Collectors");

        return importList;
    }
}
