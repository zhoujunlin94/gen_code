package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.extra.template.Template;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.CommonConstant;
import io.github.zhoujunlin94.code.gen.constant.EntityConstant;
import io.github.zhoujunlin94.code.gen.constant.MapperConstant;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024-05-09-17:29
 */
public class GenMapperCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected Template getTemplate() {
        return CommonConstant.ENGINE.getTemplate(MapperConstant.TEMPLATE_NAME);
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put("packageName", context.get(MapperConstant.PACKAGE_NAME_KEY));

        buildImportTypes(importList(context), retMap);

        retMap.put("mapperName", context.get(MapperConstant.MAPPER_NAME));
        retMap.put("entityName", context.get(EntityConstant.ENTITY_NAME));

        return retMap;
    }

    @Override
    protected String getDestFileName(Setting context) {
        String mapperDestPath = context.get(MapperConstant.DEST_PATH_KEY);
        FileUtil.mkdir(mapperDestPath);
        String mapperName = context.get(MapperConstant.MAPPER_NAME);
        return mapperDestPath + "/" + mapperName + ".java";
    }

    private List<String> importList(Setting context) {
        List<String> importList = new LinkedList<>();
        importList.add(context.get(EntityConstant.ENTITY_CLASS));
        importList.add(context.get(MapperConstant.TK_MAPPER_KEY));
        importList.add("org.apache.ibatis.annotations.Mapper");
        return importList;
    }
}
