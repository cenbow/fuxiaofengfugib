/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.account.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.account.dao.UserSurveyQuestionAnswerDaoCustom;
import com.cqliving.cloud.online.account.dto.UserSurveyQuestionAnswerDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.utils.Dates;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月28日
 */
public class UserSurveyQuestionAnswerDaoImpl implements UserSurveyQuestionAnswerDaoCustom {

	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplate;

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.dao.UserSurveyQuestionAnswerDaoCustom#findTotalBySessionCodeAndSurveyId(java.lang.Long, java.lang.Long, java.lang.String, boolean)
	 */
	@Override
	public List<UserSurveyQuestionAnswerDto> findTotalByUserIdAndSurveyId(Long questionId, Long surveyId, Long userId, boolean isToday) {
         
		StringBuilder sql = new StringBuilder();
        
        sql.append("select count(usqa.answer_id) total,usqa.answer_id from user_survey_history ush,user_survey_question_answer usqa ");
        sql.append("where ush.ID=usqa.history_id and usqa.question_id = ? and ush.survey_id = ? ");
        sql.append("and ush.user_id=? ");
        
        if(isToday){
        	Date dayStart = Dates.dayStart(Dates.now());
        	Date dayEnd = Dates.dayEnd(Dates.now());
        	sql.append(" and usqa.create_time<=? and usqa.create_time >= ? ");	
        	sql.append(" group by usqa.answer_id ");
        	return  mysqlPagedJdbcTemplate.queryForList(UserSurveyQuestionAnswerDto.class,sql.toString(),questionId,surveyId,userId,dayEnd,dayStart);
        }
      sql.append(" group by usqa.answer_id ");
      return  mysqlPagedJdbcTemplate.queryForList(UserSurveyQuestionAnswerDto.class,sql.toString(),questionId,surveyId,userId);
	}

}
