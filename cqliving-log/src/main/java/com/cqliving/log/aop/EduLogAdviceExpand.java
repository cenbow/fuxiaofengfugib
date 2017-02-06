package com.cqliving.log.aop;

import com.cqliving.log.domain.BaseLog;

import javax.servlet.http.HttpServletRequest;

/**
 * com.CQLIVING.edu.log.aop.
 * User: wangyx
 * Date: 14-4-3
 * Time: 下午4:37
 */
public interface EduLogAdviceExpand {
    public void expand(HttpServletRequest request, BaseLog baseLog);
}
