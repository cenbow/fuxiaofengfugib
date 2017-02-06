package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserSurveyHistory;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户参与调研历史表 Service
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserSurveyHistoryService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserSurveyHistory>> queryForPage(PageInfo<UserSurveyHistory> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserSurveyHistory> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(UserSurveyHistory domain);
	/** @author Code Generator *****end*****/
	
}
