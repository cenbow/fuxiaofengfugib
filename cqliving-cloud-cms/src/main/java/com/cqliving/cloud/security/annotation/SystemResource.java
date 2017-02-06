package com.cqliving.cloud.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value=ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SystemResource {
	/**
	 * 资源标题 
	 */
	String title() ;
	/**资源类型，类认值 {@link ResourceType#URL}
	 *@see ResourceType
	 **/
	ResourceType restype() default ResourceType.URL ;
	/**
	 *资源URL值
	 *  
	 */
	String resString() default "";
	/**
	 * 
	 * 资源权限值,需保持维一性
	 */
	String permissionValue();
	/**
	 *资源描述
	 */
	String descn() default "";
	/**
	 * 资源状态,默认值{@link ResourceStatus#Enable}
	 * @see ResourceStatus
	 */
	ResourceStatus status()  default ResourceStatus.Enable;
	/**
	 * 资源排序号
	 */
	int sortNum() default -1;
	/**
	 * 父资源权限值
	 */
	String parentValue() default "";
}
