package ${packageName};

<#list externalTypes as importType>
import ${importType};
</#list>

<#list internalTypes as importType>
import ${importType};
</#list>

@Repository
public class ${handlerName} extends TKHandler<${mapperName}, ${entityName}> {

}