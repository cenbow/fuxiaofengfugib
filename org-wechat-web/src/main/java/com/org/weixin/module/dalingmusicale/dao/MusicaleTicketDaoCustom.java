/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.dalingmusicale.dao;

import java.util.List;

import com.org.weixin.module.dalingmusicale.domain.MusicaleTicket;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月16日
 */
public interface MusicaleTicketDaoCustom {

	//查找音乐会门票
	public List<MusicaleTicket> findAllTicketsById(Long id);
}
