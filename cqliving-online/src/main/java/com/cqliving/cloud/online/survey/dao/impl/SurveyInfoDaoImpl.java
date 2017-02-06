/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.survey.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.survey.dao.SurveyInfoDaoCustom;
import com.cqliving.cloud.online.survey.dto.SurveyInfoDto;
import com.cqliving.cloud.online.survey.dto.SurveyQuestionAnswerDto;
import com.cqliving.cloud.online.survey.dto.SurveyQuestionDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.google.common.collect.Lists;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月9日
 */
public class SurveyInfoDaoImpl implements SurveyInfoDaoCustom {

	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.survey.dao.SurveyInfoDaoCustom#findByInfoId(java.lang.Long)
	 */
	@Override
	public SurveyInfoDto findByInfoContentId(Long infoContentId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select si.*, ");
		sql.append("sq.ID question_id,sq.images,sq.limit_count,sq.question,sq.sort_no question_Sort_No,sq.type question_Type,");
		sql.append("sq.create_time question_create_time,");
		sql.append("sqa.ID answer_id,sqa.image_url,sqa.init_value,sqa.input_limit,sqa.is_affirmative,sqa.number,");
		sql.append("sqa.sort_no answer_sort_no,sqa.type answer_type,sqa.create_time answer_create_time,sqa.answer,sqa.act_value ");
		sql.append("from survey_info si,survey_question sq,survey_question_answer sqa ");
		sql.append("where si.ID = sq.survey_id and sq.ID = sqa.question_id and si.status=3 and sqa.status=3 and sq.status=3 ");
		sql.append("and si.information_content_id = ? order by sqa.sort_no ");
		
		List<SurveyInfoDto> list = mysqlPagedJdbcTemplate.queryForList(SurveyInfoDto.class, sql.toString(), infoContentId);
		
		if(CollectionUtils.isEmpty(list))return null;
		
		//组装问题
		Iterator<SurveyInfoDto> iterator = list.iterator();
		
		//父类
		SurveyInfoDto surveyInfoDto = list.get(0);
		
		while(iterator.hasNext()){
			
			SurveyInfoDto iteratorSurveyInfoDto = iterator.next();
			Long questionid = iteratorSurveyInfoDto.getQuestionId();
			//问题组装
			List<SurveyQuestionDto> questions = surveyInfoDto.getSurveyQuestionDtos();
			if(CollectionUtils.isEmpty(questions))questions = Lists.newArrayList();
			SurveyQuestionDto surveyQuestionDto = this.coverToSurveyQuestionDto(iteratorSurveyInfoDto);
			if(!questions.contains(surveyQuestionDto)){
				questions.add(surveyQuestionDto);
				surveyInfoDto.setSurveyQuestionDtos(questions);
			}
			
			//问题答案组装
			SurveyQuestionAnswerDto surveyQuestionAnswerDto = this.coverToSurveyQuestionAnswer(iteratorSurveyInfoDto);
			for(SurveyQuestionDto questionDto : questions){
				
				List<SurveyQuestionAnswerDto> answers = questionDto.getSurveyQuestionAnswers();
				if(CollectionUtils.isEmpty(answers))answers = Lists.newArrayList();
				if(!answers.contains(surveyQuestionAnswerDto) && questionid.longValue() == questionDto.getId()){//同一个问题的不同答案选项
					answers.add(surveyQuestionAnswerDto);
					questionDto.setSurveyQuestionAnswers(answers);
				}
			}
		}
		return surveyInfoDto;
	}

	
	private SurveyQuestionDto coverToSurveyQuestionDto(SurveyInfoDto surveyInfoDto){
		
		SurveyQuestionDto surveyQuestionDto = new SurveyQuestionDto();
		
		surveyQuestionDto.setAppId(surveyInfoDto.getAppId());
		surveyQuestionDto.setId(surveyInfoDto.getQuestionId());
		surveyQuestionDto.setImages(surveyInfoDto.getImages());
		surveyQuestionDto.setLimitCount(surveyInfoDto.getLimitCount());
		surveyQuestionDto.setQuestion(surveyInfoDto.getQuestion());
		surveyQuestionDto.setSortNo(surveyInfoDto.getQuestionSortNo());
		surveyQuestionDto.setSurveyId(surveyInfoDto.getId());
		surveyQuestionDto.setType(surveyInfoDto.getQuestionType());
		surveyQuestionDto.setCreateTime(surveyInfoDto.getQuestionCreateTime());
		return surveyQuestionDto;
	}
	
	private SurveyQuestionAnswerDto coverToSurveyQuestionAnswer(SurveyInfoDto surveyInfoDto){
		
		SurveyQuestionAnswerDto surveyQuestionAnswer = new SurveyQuestionAnswerDto();
		
		surveyQuestionAnswer.setAnswer(surveyInfoDto.getAnswer());
		surveyQuestionAnswer.setId(surveyInfoDto.getAnswerId());
		surveyQuestionAnswer.setImageUrl(surveyInfoDto.getImageUrl());
		surveyQuestionAnswer.setInitValue(surveyInfoDto.getInitValue());
		surveyQuestionAnswer.setInputLimit(surveyInfoDto.getInputLimit());
		surveyQuestionAnswer.setIsAffirmative(surveyInfoDto.getIsAffirmative());
		surveyQuestionAnswer.setNumber(surveyInfoDto.getNumber());
		surveyQuestionAnswer.setQuestionId(surveyInfoDto.getQuestionId());
		surveyQuestionAnswer.setSortNo(surveyInfoDto.getAnswerSortNo());
		surveyQuestionAnswer.setType(surveyInfoDto.getAnswerType());
		surveyQuestionAnswer.setCreateTime(surveyInfoDto.getAnswerCreateTime());
		surveyQuestionAnswer.setActValue(surveyInfoDto.getActValue());
		return surveyQuestionAnswer;
	}
}
