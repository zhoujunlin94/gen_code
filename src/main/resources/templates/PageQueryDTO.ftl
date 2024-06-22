package ${packageName};

<#list externalTypes as importType>
import ${importType};
</#list>

<#list internalTypes as importType>
import ${importType};
</#list>

@Data
@ApiModel(description = "${pageQueryDTODesc}")
public class ${pageQueryDTOName} extends ${PageQueryClass} {
    private static final long serialVersionUID = -5001610704205988933L;

}