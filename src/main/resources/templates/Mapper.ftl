package ${packageName};

<#list externalTypes as importType>
import ${importType};
</#list>

<#list internalTypes as importType>
import ${importType};
</#list>

@Mapper
public interface ${mapperName} extends TKMapper<${entityName}> {

}