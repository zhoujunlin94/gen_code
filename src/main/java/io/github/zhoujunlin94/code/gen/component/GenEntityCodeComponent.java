package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.Table;
import cn.hutool.extra.template.Template;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.CommonConstant;
import io.github.zhoujunlin94.code.gen.constant.EntityConstant;
import io.github.zhoujunlin94.code.gen.dto.Field;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhoujunlin
 * @date 2024-05-09-17:29
 */
public class GenEntityCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected Template getTemplate() {
        return CommonConstant.ENGINE.getTemplate(EntityConstant.TEMPLATE_NAME);
    }

    @Override
    protected String getDestFileName(Setting context) {
        String entityDestPath = context.get(EntityConstant.DEST_PATH_KEY);
        FileUtil.mkdir(entityDestPath);
        String entityName = context.get(EntityConstant.ENTITY_NAME);
        return entityDestPath + "/" + entityName + ".java";
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put("packageName", context.get(EntityConstant.PACKAGE_NAME_KEY));

        Collection<Column> columns = table.getColumns();
        buildImportTypes(importList(columns), retMap);

        retMap.put("tableName", table.getTableName());

        retMap.put("entityName", context.get(EntityConstant.ENTITY_NAME));

        retMap.put("fieldList", fieldList(columns));
        return retMap;
    }

    private List<Field> fieldList(Collection<Column> columns) {
        return columns.stream().map(column -> {
            Field field = new Field();
            field.setPk(column.isPk());
            field.setColumnName(column.getName());
            field.setFieldType(EntityConstant.FIELD_TYPE_MAP.get(column.getTypeName()));
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
            String columnType = EntityConstant.IMPORT_TYPE_MAP.get(column.getTypeName());
            if (StrUtil.isNotBlank(columnType) && !importList.contains(columnType)) {
                importList.add(columnType);
            }
        });
        return importList;
    }


}
