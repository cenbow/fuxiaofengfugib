/**

 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.info.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.app.dao.AppResouseDao;
import com.cqliving.cloud.online.app.domain.AppResouse;
import com.cqliving.cloud.online.info.dao.InformationContentDao;
import com.cqliving.cloud.online.info.dao.InformationDaoCustom;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.cloud.online.survey.dao.SurveyInfoDao;
import com.cqliving.cloud.online.survey.dto.SurveyInfoDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.google.common.collect.Lists;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月20日
 */
@Repository
public class InformationDaoImpl implements InformationDaoCustom {
	
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplate;
	@Autowired
	SurveyInfoDao surveyInfoDao;
	@Autowired
	AppResouseDao appResouseDao;
	@Autowired
	InformationContentDao informationContentDao;

	/* (non-Javadoc)获取新闻详情
	 * @see com.cqliving.cloud.online.info.dao.InformationDaoCustom#findDetail(java.lang.Long)
	 */
	@Override
	public InformationDto findDetail(Long infoClassifyId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select i.add_type,ic.hlw_old_guid,ic.app_id,i.classify_id,i.content,i.content_text,i.content_url,i.context_type,i.create_time,i.info_label,");
		sql.append("i.ID,i.info_source,i.init_count,i.keywords,i.multiple_img_count,i.pl_view_type,");
        sql.append("i.praise_count,i.reply_count,i.synopsis,i.top_time,i.type,i.validate_type,i.video_status,");
		sql.append("ic.id info_classify_id,ic.title,ic.status,ic.online_time,icl.image_url list_view_img,i.view_count ");
		sql.append(",ic.reply_count classify_reply_count ");
		sql.append("from info_classify ic inner join information i on ic.information_id = i.ID ");
		sql.append(" left join info_classify_list icl  ON icl.classify_id = ic.ID  where  ic.id=? ");
		
		List<InformationDto> list = mysqlPagedJdbcTemplate.queryForList(InformationDto.class, sql.toString(), infoClassifyId);
		
		if(CollectionUtils.isEmpty(list))return null;
		
		InformationDto informationDto = list.get(0);
		//根据不同新闻内容类型查询不同展示结果
		byte contextType = informationDto.getContextType().byteValue();
		//文本及外链直接返回
        if(Information.CONTEXTTYPE0.byteValue() == contextType || Information.CONTEXTTYPE3.byteValue() == contextType){
        	return informationDto;
        }		
        
        List<InformationContent> inforcontents = informationContentDao.findByInformationId(informationDto.getId());
        
        if(CollectionUtils.isEmpty(inforcontents))return informationDto;
       
        List<SurveyInfoDto> surveyInfos = Lists.newArrayList();
        
        List<AppResouse> appResource = Lists.newArrayList();
        //根据informationContent区分，查询appResource及surveyInfo
        for(InformationContent informationContent : inforcontents){
        	
        	byte inforcontentType = informationContent.getType().byteValue();
        	if(InformationContent.TYPE3.byteValue() == inforcontentType){
        		SurveyInfoDto vote = surveyInfoDao.findByInfoContentId(informationContent.getId());
        		List<SurveyInfoDto> votes = Lists.newArrayList();
        		votes.add(vote);
        		informationContent.setVotes(votes);
        	}else if(InformationContent.TYPE4.byteValue() == inforcontentType){
        		SurveyInfoDto arena = surveyInfoDao.findByInfoContentId(informationContent.getId());
        		List<SurveyInfoDto> arenas = Lists.newArrayList();
        		arenas.add(arena);
        		informationContent.setArenas(arenas);
        	}else if(InformationContent.TYPE5.byteValue() == inforcontentType){
        		surveyInfos.add(surveyInfoDao.findByInfoContentId(informationContent.getId()));
        	}else{
        		List<AppResouse> sqlAppResource = appResouseDao.findByInfoContentId(informationContent.getId());
        		informationContent.setAppResource(sqlAppResource);
        		appResource.addAll(sqlAppResource);
        	}
        }
        
        informationDto.setInfocontents(inforcontents);
        informationDto.setSurveyInfos(surveyInfos);
        informationDto.setAppResource(appResource);
        
		return informationDto;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.dao.InformationDaoCustom#findAnswerNumByInfoId(java.lang.Long)
	 */
	@Override
	public List<Map<String, Object>> findAnswerNumByInfoId(Long infoId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select answer.id,(IFNULL(answer.act_value,0) + IFNULL(answer.init_value,0)) answer_num ");
		sql.append("from survey_info survey,survey_question ques,survey_question_answer answer ");
		sql.append("where survey.ID = ques.survey_id and ques.ID = answer.question_id ");
		sql.append("and survey.`status`=3 and ques.`status` = 3 and answer.`status`=3 ");
		sql.append("and survey.information_id = ?");
		return mysqlPagedJdbcTemplate.queryForList(sql.toString(), infoId);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.dao.InformationDaoCustom#addReplyCount(java.lang.Long)
	 */
	//@Override
	/*public int addReplyCount(Long infoClassifyId,int num) {
		StringBuilder sql = new StringBuilder();
		sql.append("update information info,info_classify ic set info.reply_count = ifnull(info.reply_count,0) + ? ");
		sql.append("where info.ID = ic.information_id and ic.ID = ? ");
		mysqlPagedJdbcTemplate.update(sql.toString(),num,infoClassifyId);
		
		sql = new StringBuilder();
		sql.append("update info_classify  set reply_count = ifnull(reply_count,0) + ? ");
		sql.append("where id = ? ");
		return mysqlPagedJdbcTemplate.update(sql.toString(),num,infoClassifyId);
	}*/

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.dao.InformationDaoCustom#addViewCount(java.lang.Long)
	 */
	/*@Override
	public int addViewCount(Long infoClassifyId,int num) {
		StringBuilder sql = new StringBuilder();
		sql.append("update information info,info_classify ic set info.view_count = ifnull(info.view_count,0) + ? ");
	    sql.append("where info.ID = ic.information_id and ic.ID = ? ");
		mysqlPagedJdbcTemplate.update(sql.toString(),num, infoClassifyId);
		
		sql = new StringBuilder();
		sql.append("update info_classify  set view_count = ifnull(view_count,0) + ? ");
		sql.append("where id = ? ");
		return mysqlPagedJdbcTemplate.update(sql.toString(),num,infoClassifyId);
		
	}*/

}