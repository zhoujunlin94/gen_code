package ${packageName};

<#list externalTypes as importType>
import ${importType};
</#list>

<#list internalTypes as importType>
import ${importType};
</#list>

@Slf4j
@Service
@RequiredArgsConstructor
public class ${serviceImplName} implements ${serviceName} {

    private final ${handlerName} ${lowerHandlerName};

    @Override
    public void add(${dtoName} paramDTO, Integer loginUserId) {
        ${entityName} entity = BeanUtil.toBean(paramDTO, ${entityName}.class);
        entity.setCreatedBy(loginUserId);
        entity.setUpdatedBy(loginUserId);
        ${lowerHandlerName}.insertSelective(entity);
    }

    @Override
    public void update(${dtoName} paramDTO, Integer loginUserId) {
        ${entityName} entity = BeanUtil.toBean(paramDTO, ${entityName}.class);
        entity.setUpdatedBy(loginUserId);
        ${lowerHandlerName}.updateByPrimaryKeySelective(entity);
    }

    @Override
    public ${dtoName} detail(Integer id) {
        ${entityName} entity = ${lowerHandlerName}.selectByPrimaryKey(id);
        return BeanUtil.toBean(entity, ${dtoName}.class);
    }

    @Override
    public PageInfo<${voName}> page(${pageQueryDTOName} pageQueryDTO) {
        PageInfo<${voName}> entityPageInfo = PageHelper.startPage(pageQueryDTO.getPageNo(), pageQueryDTO.getPageSize())
                    .doSelectPageInfo(()-> ${lowerHandlerName}.page(pageQueryDTO));

        List<${voName}> retList = entityPageInfo.getList().stream()
                    .map(entity -> BeanUtil.toBean(entity, ${voName}.class)).collect(Collectors.toList());

        return PageUtil.copy(entityPageInfo, retList);
    }

}
