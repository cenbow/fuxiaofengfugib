/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.framework.utils;

import org.springframework.data.domain.Page;

import com.cqliving.framework.common.dao.support.PageInfo;
/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author fuxiaofeng on 2015年10月14日
 */
public class PageUtil {

	public static <T>PageInfo<T> pageToPageInfo(PageInfo<T> pageInfo,Page<T> page){
		
		if(pageInfo == null || page == null){
			return null;
		}
		
		//pageInfo.setCountOfCurrentPage(page.getSize());
		//pageInfo.setCurrentPage(page.getNumber());
		pageInfo.setPageResults(page.getContent());
		pageInfo.setTotalCount(page.getTotalElements());
		pageInfo.setTotalPage(page.getTotalPages());
		
		return pageInfo;
	}
}
