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
@Repository
public class ${HandlerName} extends TKHandler<${MapperName}, ${EntityName}> {

    public List<${EntityName}> page(${PageQueryDTOName} pageQuery) {
        Weekend<${EntityName}> weekend = thisWeekend();
        return this.baseMapper.selectByExample(weekend);
    }

}