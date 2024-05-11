package io.github.zhoujunlin94.code.gen.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhoujunlin
 * @date 2024-05-10-11:47
 */
@Data
@Accessors(chain = true)
public class Field {

    private boolean pk;
    private String columnName;
    private String fieldType;
    private String fieldName;

    private String typeName;

    private String comment;

}
