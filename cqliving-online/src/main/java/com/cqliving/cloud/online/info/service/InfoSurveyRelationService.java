package com.cqliving.cloud.online.info.service;

import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoSurveyRelation;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 资讯调研关联表 Service
 * Date: 2016-04-15 09:44:54
 * @author Code Generator
 */
public interface InfoSurveyRelationService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<InfoSurveyRelation>> queryForPage(PageInfo<InfoSurveyRelation> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<InfoSurveyRelation> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(InfoSurveyRelation domain);
	/** @author Code Generator *****end*****/
	
}
