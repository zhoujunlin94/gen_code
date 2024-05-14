package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.CommonConstant;
import io.github.zhoujunlin94.code.gen.constant.EntityConstant;
import io.github.zhoujunlin94.code.gen.constant.MapperConstant;
import io.github.zhoujunlin94.code.gen.constant.MapperXmlConstant;
import io.github.zhoujunlin94.code.gen.dto.Field;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhoujunlin
 * @date 2024-05-11-9:57
 */
public class GenMapperXmlCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected String getTemplateName() {
        return "MapperXml.ftl";
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();
        retMap.put(MapperConstant.MAPPER_CLASS, context.get(MapperConstant.MAPPER_CLASS));
        retMap.put(EntityConstant.ENTITY_CLASS, context.get(EntityConstant.ENTITY_CLASS));
        retMap.put(EntityConstant.FIELD_LIST, fieldList(table.getColumns()));
        return retMap;
    }

    @Override
    protected String getDestFileName(Setting context) {
        String mapperXmlDestPath = context.get(MapperXmlConstant.DEST_PATH_KEY);
        FileUtil.mkdir(mapperXmlDestPath);
        String mapperName = context.get(MapperConstant.MAPPER_NAME);
        return mapperXmlDestPath + StrUtil.SLASH + mapperName + StrUtil.DOT + CommonConstant.XML;
    }

    private List<Field> fieldList(Collection<Column> columns) {
        return columns.stream().map(column -> {
            Field field = new Field();
            field.setPk(column.isPk());
            field.setColumnName(column.getName());
            String typeName = column.getTypeName();
            field.setTypeName(MapperXmlConstant.TYPE_NAME_MAP.getOrDefault(typeName, typeName));
            field.setFieldName(StrUtil.toCamelCase(column.getName()));
            return field;
        }).collect(Collectors.toList());
    }

}
