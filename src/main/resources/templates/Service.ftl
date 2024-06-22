package ${packageName};

<#list externalTypes as importType>
import ${importType};
</#list>

<#list internalTypes as importType>
import ${importType};
</#list>

public interface ${serviceName} {

    void add(${dtoName} paramDTO, Integer loginUserId);

    void update(${dtoName} paramDTO, Integer loginUserId);

    ${dtoName} detail(Integer id);

    PageInfo<${voName}> page(${pageQueryDTOName} pageQueryDTO);

}
