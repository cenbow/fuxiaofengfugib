/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.security.dao;

import java.util.List;

import com.cqliving.cloud.online.security.domain.SysResType;
import com.cqliving.cloud.online.security.dto.SysResTypeDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月30日
 */
public interface SysResTypeDaoCustom {

	public List<SysResType> findExistsRes();
	
	public List<SysResType> findExistsResByUserId(Long userId);
	
	/**
	 * 查询前台资源类型
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年12月20日下午5:14:46
	 */
	public List<SysResTypeDto> findAppResType();
}
