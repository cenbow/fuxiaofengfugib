package com.cqliving.cloud.online.act.service;

import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActTestAnswer;
import com.cqliving.cloud.online.act.dto.ActAnswerResult;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 活动答题分类答案表 Service
 * Date: 2016-06-07 09:22:20
 * @author Code Generator
 */
public interface ActTestAnswerService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActTestAnswer>> queryForPage(PageInfo<ActTestAnswer> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActTestAnswer> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ActTestAnswer domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:获得答题详情
	 * <p>Description:App调用</p>
	 * @author DeweiLi on 2016年6月22日
	 * @param sessionId
	 * @param token
	 * @param actInfoListId
	 * @param isLoad
	 * @return
	 */
	Response<ActAnswerResult> answer(String sessionId, String token, Long actInfoListId, String isLoad);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月28日
	 * @param actTestAnswer
	 * @return
	 */
	Response<Void> saveAndModify(ActTestAnswer actTestAnswer);
}
