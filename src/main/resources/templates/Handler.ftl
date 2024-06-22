package ${packageName};

<#list externalTypes as importType>
    import ${importType};
</#list>

<#list internalTypes as importType>
    import ${importType};
</#list>

@Repository
public class ${handlerName} extends TKHandler<${mapperName}, ${entityName}> {

    public List<${entityName}> page(${pageQueryDTOName} pageQuery){
        Weekend<${entityName}> weekend = thisWeekend();
        return this.baseMapper.selectByExample(weekend);
    }

}