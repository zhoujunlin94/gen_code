package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.Constant;
import io.github.zhoujunlin94.code.gen.dto.Field;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhoujunlin
 * @date 2024-05-09-17:29
 */
public class GenDTOCodeComponent extends AbstractGenCodeComponent {

    @Override
    protected String getTemplateName() {
        return Constant.FTL.DTO;
    }

    @Override
    protected String getDestFileName(Setting context) {
        String destPath = buildDestPath(context, context.get(Constant.DTO.PACKAGE_NAME_KEY));
        FileUtil.mkdir(destPath);
        String dtoName = context.get(Constant.DTO.DTO_NAME);
        return destPath + StrUtil.SLASH + dtoName + StrUtil.DOT + Constant.JAVA;
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(Constant.PACKAGE_NAME, context.get(Constant.DTO.PACKAGE_NAME_KEY));

        Collection<Column> columns = table.getColumns();
        buildImportTypes(importList(columns), retMap);

        retMap.put(Constant.DTO.DTO_NAME, context.get(Constant.DTO.DTO_NAME));
        retMap.put(Constant.DTO.DTO_DESC, table.getComment() + " DTO");

        retMap.put(Constant.FIELD_LIST, fieldList(columns));
        return retMap;
    }

    private List<Field> fieldList(Collection<Column> columns) {
        return columns.stream()
                .filter(column -> !Constant.Entity.EXCLUDE_COLUMNS.contains(column.getName()))
                .map(column -> {
                    Field field = new Field();
                    field.setComment(column.getComment());
                    field.setFieldType(Constant.Entity.FIELD_TYPE_MAP.get(column.getTypeName()));
                    String columnName = column.getName();
                    field.setFieldName(StrUtil.toCamelCase(columnName));
                    return field;
                }).collect(Collectors.toList());
    }

    private List<String> importList(Collection<Column> columns) {
        List<String> importList = new LinkedList<>();
        importList.add("io.swagger.annotations.ApiModel");
        importList.add("io.swagger.annotations.ApiModelProperty");
        importList.add("lombok.Data");

        importList.add("java.io.Serializable");

        columns.forEach(column -> {
            if (Constant.Entity.EXCLUDE_COLUMNS.contains(column.getName())) {
                return;
            }
            String columnType = Constant.Entity.IMPORT_TYPE_MAP.get(column.getTypeName());
            if (StrUtil.isNotBlank(columnType) && !importList.contains(columnType)) {
                importList.add(columnType);
            }
        });
        return importList;
    }


}
