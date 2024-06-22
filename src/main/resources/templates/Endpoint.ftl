package ${packageName};

<#list externalTypes as importType>
import ${importType};
</#list>

<#list internalTypes as importType>
import ${importType};
</#list>

@Api(tags = {"${endPointDesc}"})
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/${camelCaseEntityName}")
public class ${endPointName} {

    private final ${serviceName} ${lowerServiceName};

    @PostMapping("add")
    @ApiOperation("新增")
    public void add(@RequestBody @Valid ${dtoName} paramDTO) {
        paramDTO.setId(null);
        ${lowerServiceName}.add(paramDTO, null);
    }

    @PostMapping("update")
    @ApiOperation("更新")
    public void update(@RequestBody @Valid ${dtoName} paramDTO) {
        Assert.notNull(paramDTO.getId(), "更新时主键不可为空");
        ${lowerServiceName}.update(paramDTO, null);
    }

    @GetMapping("detail")
    @ApiOperation("详情")
    public ${dtoName} detail(@RequestParam @NotNull(message = "主键不可为空") Integer id) {
        return ${lowerServiceName}.detail(id);
    }

    @PostMapping("page")
    @ApiOperation("分页")
    public PageInfo<${voName}> page(@RequestBody ${pageQueryDTOName} pageQueryDTO) {
        return ${lowerServiceName}.page(pageQueryDTO);
    }

}
