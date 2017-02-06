package ${names.domainPackage};


import java.util.Map;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * ${table.comment} Entity
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 * @author Code Generator
 */
@Entity
@Table(name = "${table.name}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${names.domainClassName} extends AbstractEntity {

	private static final long serialVersionUID = 1L;

<#list table.columnMetadatas as entity>	
    <#if entity.options??>
	<#list entity.options?keys as key>
	/** ${entity.options[key]} */
	public static final ${entity.javaDataType} ${entity.propertyName?upper_case+key?replace('-','_')} = ${key};
	</#list>
		
	/** ${entity.common} */
	<#if entity.javaDataType = 'Byte'>
	public static final Map<Byte, String> all${entity.propertyName?cap_first}s = Maps.newTreeMap();
	<#else>
	public static final Map<Integer, String> all${entity.propertyName?cap_first}s = Maps.newTreeMap();
	</#if>
	static {
	<#list entity.options?keys as key>
		all${entity.propertyName?cap_first}s.put(${entity.propertyName?upper_case+key?replace('-','_')}, "${entity.options[key]}");
	</#list>
	}
	</#if>	
</#list>	
	
<#list table.columnMetadatas as entity>	
	/** ${entity.common} */
	private ${entity.javaDataType} ${entity.propertyName};
</#list>
	
<#list table.columnMetadatas as entity>
	<#if entity.name?lower_case = 'id'>
	@Id
	<#if entity.extra>${entityIdDeclare}(strategy = GenerationType.AUTO)</#if>
	</#if>
	public ${entity.javaDataType} get${entity.propertyName?cap_first}(){
		return this.${entity.propertyName};
	}
	
	public void set${entity.propertyName?cap_first}(${entity.javaDataType} ${entity.propertyName}){
		this.${entity.propertyName} = ${entity.propertyName};
	}
</#list>	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
