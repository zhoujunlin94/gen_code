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
@Slf4j
@Service
@RequiredArgsConstructor
public class ${ServiceImplName} implements ${ServiceName} {

    private final ${HandlerName} ${lowerFirstHandlerName};

    @Override
    public void add(${DTOName} paramDTO, Integer loginUserId) {
        ${EntityName} entity = BeanUtil.toBean(paramDTO, ${EntityName}.class);
        entity.setCreatedBy(loginUserId);
        entity.setUpdatedBy(loginUserId);
        ${lowerFirstHandlerName}.insertSelective(entity);
    }

    @Override
    public void update(${DTOName} paramDTO, Integer loginUserId) {
        ${EntityName} entity = BeanUtil.toBean(paramDTO, ${EntityName}.class);
        entity.setUpdatedBy(loginUserId);
        ${lowerFirstHandlerName}.updateByPrimaryKeySelective(entity);
    }

    @Override
    public ${DTOName} detail(Integer id) {
        ${entityName} entity = ${lowerFirstHandlerName}.selectByPrimaryKey(id);
        return BeanUtil.toBean(entity, ${DTOName}.class);
    }

    @Override
    public PageInfo<${VOName}> page(${PageQueryDTOName} pageQueryDTO) {
        PageInfo<${VOName}> entityPageInfo = PageHelper.startPage(pageQueryDTO.getPageNo(), pageQueryDTO.getPageSize())
                .doSelectPageInfo(()-> ${lowerFirstHandlerName}.page(pageQueryDTO));

        List<${VOName}> retList = entityPageInfo.getList().stream()
                .map(entity -> BeanUtil.toBean(entity, ${VOName}.class)).collect(Collectors.toList());

        return PageUtil.copy(entityPageInfo, retList);
    }

}
