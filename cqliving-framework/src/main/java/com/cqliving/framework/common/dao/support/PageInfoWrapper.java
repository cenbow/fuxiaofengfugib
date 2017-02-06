package com.cqliving.framework.common.dao.support;

import org.springframework.data.domain.Page;

import com.cqliving.framework.common.dao.support.PageInfo;
/**
 * 
 * ClassName: PageInfoWrapper <p> 
 * Function: PageInfo包装类,将Page包装为PageInfo. <p> 
 * date: 2015年8月11日 下午2:56:28 <p> 
 * 
 * @author weiming 
 * @param <T> 
 * @version
 * @see Page
 * @see PageInfo
 */
public class PageInfoWrapper<T> extends PageInfo<T>{
	public PageInfoWrapper(Page<T> page){
		super(page.getSize(), page.getNumber()+1);
		this.setPageResults(page.getContent());
		this.setTotalCount(page.getTotalElements());
		this.setTotalPage(page.getTotalPages());
	}
}
