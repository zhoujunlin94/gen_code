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
public class GenServiceImplCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected String getTemplateName() {
        return "ServiceImpl.ftl";
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(Constant.PACKAGE_NAME, context.get(Constant.Service.PACKAGE_NAME_KEY) + ".impl");

        buildImportTypes(importList(context), retMap);

        retMap.put(Constant.Service.SERVICE_IMPL_NAME, context.get(Constant.Service.SERVICE_IMPL_NAME));
        retMap.put(Constant.Service.SERVICE_NAME, context.get(Constant.Service.SERVICE_NAME));
        retMap.put(Constant.Handler.HANDLER_NAME, context.get(Constant.Handler.HANDLER_NAME));
        retMap.put("lowerHandlerName", StrUtil.lowerFirst(context.get(Constant.Handler.HANDLER_NAME)));
        retMap.put(Constant.DTO.DTO_NAME, context.get(Constant.DTO.DTO_NAME));
        retMap.put(Constant.Entity.ENTITY_NAME, context.get(Constant.Entity.ENTITY_NAME));
        retMap.put(Constant.DTO.PAGE_QUERY_DTO_NAME, context.get(Constant.DTO.PAGE_QUERY_DTO_NAME));
        retMap.put(Constant.VO.VO_NAME, context.get(Constant.VO.VO_NAME));
        return retMap;
    }

    @Override
    protected String getDestFileName(Setting context) {
        String destPath = buildDestPath(context, context.get(Constant.Service.PACKAGE_NAME_KEY) + ".impl");
        FileUtil.mkdir(destPath);
        String serviceImplName = context.get(Constant.Service.SERVICE_IMPL_NAME);
        return destPath + StrUtil.SLASH + serviceImplName + StrUtil.DOT + Constant.JAVA;
    }

    private List<String> importList(Setting context) {
        List<String> importList = new LinkedList<>();
        importList.add("cn.hutool.core.bean.BeanUtil");
        importList.add("com.github.pagehelper.PageHelper");
        importList.add("com.github.pagehelper.PageInfo");
        importList.add(context.get(Constant.DTO.DTO_CLASS));
        importList.add(context.get(Constant.DTO.PAGE_QUERY_DTO_CLASS));
        importList.add(context.get(Constant.Entity.ENTITY_CLASS));
        importList.add(context.get(Constant.Handler.HANDLER_CLASS));
        importList.add(context.get(Constant.Service.SERVICE_CLASS));
        importList.add(context.get(Constant.VO.VO_CLASS));
        importList.add(context.get("PageUtil"));
        importList.add("lombok.RequiredArgsConstructor");
        importList.add("lombok.extern.slf4j.Slf4j");
        importList.add("org.springframework.stereotype.Service");

        importList.add("java.util.List");
        importList.add("java.util.stream.Collectors");

        return importList;
    }
}
