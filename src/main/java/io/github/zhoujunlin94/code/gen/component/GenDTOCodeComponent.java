package io.github.zhoujunlin94.code.gen.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.constant.CommonConstant;
import io.github.zhoujunlin94.code.gen.constant.DTOConstant;
import io.github.zhoujunlin94.code.gen.constant.EntityConstant;
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
        return "DTO.ftl";
    }

    @Override
    protected String getDestFileName(Setting context) {
        String destPath = buildDestPath(context, context.get(DTOConstant.PACKAGE_NAME_KEY));
        FileUtil.mkdir(destPath);
        String dtoName = context.get(DTOConstant.DTO_NAME);
        return destPath + StrUtil.SLASH + dtoName + StrUtil.DOT + CommonConstant.JAVA;
    }

    @Override
    protected Map<String, Object> buildBindingMap(Table table, Setting context) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(CommonConstant.PACKAGE_NAME, context.get(DTOConstant.PACKAGE_NAME_KEY));

        Collection<Column> columns = table.getColumns();
        buildImportTypes(importList(columns), retMap);

        retMap.put(DTOConstant.DTO_NAME, context.get(DTOConstant.DTO_NAME));
        retMap.put(DTOConstant.DTO_DESC, table.getComment() + " DTO");

        retMap.put(EntityConstant.FIELD_LIST, fieldList(columns));

        return retMap;
    }

    private List<Field> fieldList(Collection<Column> columns) {
        return columns.stream()
                .filter(column -> !EntityConstant.EXCLUDE_COLUMNS.contains(column.getName()))
                .map(column -> {
                    Field field = new Field();
                    field.setComment(column.getComment());
                    field.setFieldType(EntityConstant.FIELD_TYPE_MAP.get(column.getTypeName()));
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
            if (EntityConstant.EXCLUDE_COLUMNS.contains(column.getName())) {
                return;
            }
            String columnType = EntityConstant.IMPORT_TYPE_MAP.get(column.getTypeName());
            if (StrUtil.isNotBlank(columnType) && !importList.contains(columnType)) {
                importList.add(columnType);
            }
        });
        return importList;
    }


}
