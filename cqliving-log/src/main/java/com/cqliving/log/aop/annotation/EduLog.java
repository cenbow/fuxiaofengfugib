package com.cqliving.log.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EduLog {
    /** 模块 */
    String module() default "";
    /** 模块名称 */
    String moduleName() default "";
    /** 如果是页面跳转方法则为 true */
    boolean paged() default true;
	/** 操作 */
	String action() default "";
    /** 操作名称 */
	String actionName() default "";
    /** 平台标识 */
    String tag() default "cloud-portal";
    /** 是否需要记录参数 */
    boolean needRecordParameter() default true;
    /** 忽略的参数 */
    String[] ignorParam() default "";
    /** 参数中文对应 */
    String[] paramMapping() default "";
    /** 是否需要记录日志 */
    boolean isNeedLog() default true;
}
