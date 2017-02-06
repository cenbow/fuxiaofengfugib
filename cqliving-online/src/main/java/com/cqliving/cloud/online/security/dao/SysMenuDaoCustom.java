/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.security.dao;

import java.util.List;

import com.cqliving.cloud.online.security.dto.MenuView;
import com.cqliving.cloud.online.security.dto.SysMenuDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年4月20日
 */
public interface SysMenuDaoCustom {

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author fuxiaofeng on 2016年4月20日
	 * @return
	 */
	public List<SysMenuDto> findAllSysMenu();
	
	public List<MenuView> queryAuthorisedMenusByUserName(String userName);
	
	public List<MenuView> queryAuthorisedMenusByRole(Long roleId);
}
