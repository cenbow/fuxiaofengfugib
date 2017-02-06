/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.info.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.info.dto.InformationDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月20日
 */
public interface InformationDaoCustom {

	//新闻详情
	public InformationDto findDetail(Long infoClassifyId);
	
	//增加回复量
	//public int addReplyCount(Long infoClassifyId,int num);
	
	//增加浏览量
	//public int addViewCount(Long infoClassifyId,int num);

	public List<Map<String, Object>> findAnswerNumByInfoId(Long infoId);
}