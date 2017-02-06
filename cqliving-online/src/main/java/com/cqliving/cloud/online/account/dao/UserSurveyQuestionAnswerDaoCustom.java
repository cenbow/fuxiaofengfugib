/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.account.dao;

import java.util.List;

import com.cqliving.cloud.online.account.dto.UserSurveyQuestionAnswerDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月28日
 */
public interface UserSurveyQuestionAnswerDaoCustom {

	//对某一个调研类型某一个问题的每个答案的总数
	public List<UserSurveyQuestionAnswerDto> findTotalByUserIdAndSurveyId(Long questionId,Long surveyId,Long userId,boolean isToday);
	
}
