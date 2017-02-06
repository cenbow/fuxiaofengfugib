package com.cqliving.framework.common.dao.support;

import org.springframework.data.domain.PageRequest;

import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * 
 * ClassName: PageableWrapper <p> 
 * Function: 分页包装类. <p> 
 * date: 2015年8月5日 下午3:23:43 <p> 
 * 
 * @author weiming  
 * @version
 */
public class PageableWrapper extends PageRequest{
	
	private static final long serialVersionUID = -7002023809976740852L;

	public  PageableWrapper(PageInfo<?> pageInfo){
		super(pageInfo.getCurrentPage()-1,pageInfo.getCountOfCurrentPage());
	}
	
}
