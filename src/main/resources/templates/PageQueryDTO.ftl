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
@ApiModel(description = "${PageQueryDTODesc}")
public class ${PageQueryDTOName} extends ${PageQueryClass} {
    private static final long serialVersionUID = -5001610704205988933L;

}