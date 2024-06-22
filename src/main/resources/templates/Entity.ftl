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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "${TableName}")
public class ${EntityName} {

<#list FieldList as field>
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