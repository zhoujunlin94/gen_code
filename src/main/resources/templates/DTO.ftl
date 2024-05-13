package ${packageName};

<#list externalTypes as importType>
import ${importType};
</#list>

<#list internalTypes as importType>
import ${importType};
</#list>

@Data
@ApiModel(description = "${dtoDesc}")
public class ${dtoName} implements Serializable {

    private static final long serialVersionUID = -5001610704205988933L;

<#list fieldList as field>
    @ApiModelProperty(value = "${field.comment}")
    private ${field.fieldType} ${field.fieldName};

</#list>
}