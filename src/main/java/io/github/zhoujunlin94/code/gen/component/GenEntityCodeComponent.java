package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.Constant;
import io.github.zhoujunlin94.code.gen.constant.Constant.Entity;
import io.github.zhoujunlin94.code.gen.dto.Field;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhoujunlin
 * @date 2024-05-09-17:29
 */
public class GenEntityCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected String getTemplateName() {
        return "Entity.ftl";
    }

    @Override
    protected String getDestFileName(Setting context) {
        String destPath = buildDestPath(context, context.get(Entity.KEY_PACKAGE_NAME));
        FileUtil.mkdir(destPath);
        String entityName = context.get(Entity.ENTITY_NAME);
        return destPath + StrUtil.SLASH + entityName + StrUtil.DOT + Constant.JAVA;
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(Constant.PACKAGE_NAME, context.get(Entity.KEY_PACKAGE_NAME));

        Collection<Column> columns = table.getColumns();
        buildImportTypes(importList(columns), retMap);

        retMap.put("TableName", table.getTableName());

        retMap.put(Entity.ENTITY_NAME, context.get(Entity.ENTITY_NAME));

        retMap.put(Constant.FIELD_LIST, fieldList(columns));
        return retMap;
    }

    private List<Field> fieldList(Collection<Column> columns) {
        return columns.stream().map(column -> {
            Field field = new Field();
            field.setPk(column.isPk());
            field.setColumnName(column.getName());
            field.setFieldType(Entity.FIELD_TYPE_MAP.get(column.getTypeName()));
            field.setFieldName(StrUtil.toCamelCase(column.getName()));
            field.setComment(column.getComment());
            return field;
        }).collect(Collectors.toList());
    }

    private List<String> importList(Collection<Column> columns) {
        List<String> importList = new LinkedList<>();
        importList.add("lombok.AllArgsConstructor");
        importList.add("lombok.Builder");
        importList.add("lombok.Data");
        importList.add("lombok.NoArgsConstructor");

        importList.add("javax.persistence.Column");
        importList.add("javax.persistence.GeneratedValue");
        importList.add("javax.persistence.Id");
        importList.add("javax.persistence.Table");

        columns.forEach(column -> {
            String columnType = Entity.IMPORT_TYPE_MAP.get(column.getTypeName());
            if (StrUtil.isNotBlank(columnType) && !importList.contains(columnType)) {
                importList.add(columnType);
            }
        });
        return importList;
    }


}
