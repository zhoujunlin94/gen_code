package ${packageName};

<#list externalTypes as importType>
import ${importType};
</#list>

<#list internalTypes as importType>
import ${importType};
</#list>

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "${tableName}")
public class ${entityName} {

<#list fieldList as field>
    /**
     * ${field.comment}
     */
    <#if field.pk >
    @Id
    @GeneratedValue(generator = "JDBC")
    </#if>
    @Column(name = "${field.columnName}")
    private ${field.fieldType} ${field.fieldName};

</#list>
}