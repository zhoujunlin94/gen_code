package ${PackageName};

<#list ExternalTypes as importType>
import ${importType};
</#list>

<#list InternalTypes as importType>
import ${importType};
</#list>

/**
 * @author ${Author}
 */
@Data
@ApiModel(description = "${DTODesc}")
public class ${DTOName} implements Serializable {

    private static final long serialVersionUID = -5001610704205988933L;

<#list FieldList as field>
    @ApiModelProperty(value = "${field.comment}")
    private ${field.fieldType} ${field.fieldName};

</#list>
}