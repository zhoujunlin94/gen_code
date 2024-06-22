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
@ApiModel(description = "${VODesc}")
public class ${VOName} extends ${DTOName} {

    private static final long serialVersionUID = 7697167583320506967L;


}