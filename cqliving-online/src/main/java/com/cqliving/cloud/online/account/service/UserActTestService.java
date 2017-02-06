package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserActTest;
import com.cqliving.cloud.online.act.domain.UserActTestClassify;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户答题表 Service
 * Date: 2016-06-07 09:29:42
 * @author Code Generator
 */
public interface UserActTestService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserActTest>> queryForPage(PageInfo<UserActTest> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserActTest> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(UserActTest domain);
	/** @author Code Generator *****end*****/
	
	public Response<UserActTestClassify> save(Long appId, String sessionId, String token, Long actTestClassifyId, Long startTime, Integer isFinish, Long[] questionIds, String[] answerIds, Long[] inputQuestionIds, String[] inputAnswerValues);
}
