package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.Constant;
import io.github.zhoujunlin94.code.gen.constant.Constant.Entity;
import io.github.zhoujunlin94.code.gen.constant.Constant.Mapper;
import io.github.zhoujunlin94.code.gen.constant.Constant.MapperXml;
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
    protected String getDestFileName(Setting context) {
        String mapperXmlDestPath = context.get("MapperXmlDestPath");
        FileUtil.mkdir(mapperXmlDestPath);
        String mapperName = context.get(Mapper.MAPPER_NAME);
        return mapperXmlDestPath + StrUtil.SLASH + mapperName + StrUtil.DOT + "xml";
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();
        retMap.put(Mapper.MAPPER_CLASS, context.get(Mapper.MAPPER_CLASS));
        retMap.put(Entity.ENTITY_CLASS, context.get(Entity.ENTITY_CLASS));
        retMap.put(Constant.FIELD_LIST, fieldList(table.getColumns()));
        return retMap;
    }

    private List<Field> fieldList(Collection<Column> columns) {
        return columns.stream().map(column -> {
            Field field = new Field();
            field.setPk(column.isPk());
            field.setColumnName(column.getName());
            String typeName = column.getTypeName();
            field.setTypeName(MapperXml.TYPE_NAME_MAP.getOrDefault(typeName, typeName));
            field.setFieldName(StrUtil.toCamelCase(column.getName()));
            return field;
        }).collect(Collectors.toList());
    }

}
