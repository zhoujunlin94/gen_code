<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${MapperClass}">
    <resultMap id="BaseResultMap" type="${EntityClass}">
        <#list FieldList as field>
            <#if field.pk >
        <id column="${field.columnName}" jdbcType="${field.typeName}" property="${field.fieldName}"/>
            <#else>
        <result column="${field.columnName}" jdbcType="${field.typeName}" property="${field.fieldName}"/>
            </#if>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <#list FieldList as field>${field.columnName}<#if field?has_next>, </#if><#if (field_index + 1)%10 == 0>${"\n\t\t"}</#if></#list>
    </sql>

</mapper>