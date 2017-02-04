/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.dalingmusicale.dao;

import java.util.List;
import java.util.Map;

import com.org.weixin.module.dalingmusicale.domain.UserTicket;
import com.org.weixin.module.dalingmusicale.dto.UserTicketDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月16日
 */
public interface UserTicketDaoCustom {

	//查找当日用户对某一场音乐会总共的抢票次数(包括该音乐会未中奖的)
    public List<UserTicket> findByUserIdDaily(Long userId,Long musicaleId);
    //根据核销码查找中奖用户信息
    public UserTicketDto findByVerifyCode(String verifyCode);
    //查找用户所有中奖的门票
    public List<UserTicketDto> findByUserId(Long userId);
    
    public List<UserTicketDto> statistics(Map<String,Object> conditions);
}
