package ${packageName};

<#list externalTypes as importType>
import ${importType};
</#list>

<#list internalTypes as importType>
import ${importType};
</#list>

@Data
@ApiModel(description = "${voDesc}")
public class ${voName} extends ${dtoName} {

    private static final long serialVersionUID = 7697167583320506967L;


}