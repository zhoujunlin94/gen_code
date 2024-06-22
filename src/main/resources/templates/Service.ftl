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
public interface ${ServiceName} {

    void add(${DTOName} paramDTO, Integer loginUserId);

    void update(${DTOName} paramDTO, Integer loginUserId);

    ${DTOName} detail(Integer id);

    PageInfo<${VOName}> page(${PageQueryDTOName} pageQueryDTO);

}
