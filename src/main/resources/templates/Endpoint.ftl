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
@Api(tags = {"${EndpointDesc}"})
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/${camel-case-entity-name}")
public class ${EndPointName} {

    private final ${ServiceName} ${lowerFirstServiceName};

    @PostMapping("add")
    @ApiOperation("新增")
    public void add(@RequestBody @Valid ${DTOName} paramDTO) {
        paramDTO.setId(null);
        ${lowerFirstServiceName}.add(paramDTO, null);
    }

    @PostMapping("update")
    @ApiOperation("更新")
    public void update(@RequestBody @Valid ${DTOName} paramDTO) {
        Assert.notNull(paramDTO.getId(), "更新时主键不可为空");
        ${lowerFirstServiceName}.update(paramDTO, null);
    }

    @GetMapping("detail")
    @ApiOperation("详情")
    public ${DTOName} detail(@RequestParam @NotNull(message = "主键不可为空") Integer id) {
        return ${lowerFirstServiceName}.detail(id);
    }

    @PostMapping("page")
    @ApiOperation("分页")
    public PageInfo<${VOName}> page(@RequestBody ${PageQueryDTOName} pageQueryDTO) {
        return ${lowerFirstServiceName}.page(pageQueryDTO);
    }

}
