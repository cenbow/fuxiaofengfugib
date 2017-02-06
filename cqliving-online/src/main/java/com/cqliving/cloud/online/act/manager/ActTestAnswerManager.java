package com.cqliving.cloud.online.act.manager;

import com.cqliving.cloud.online.act.domain.ActTestAnswer;
import com.cqliving.cloud.online.act.dto.ActAnswerResult;
import com.cqliving.framework.common.service.EntityService;

/**
 * 活动答题分类答案表 Manager
 * Date: 2016-06-07 09:22:20
 * @author Code Generator
 */
public interface ActTestAnswerManager extends EntityService<ActTestAnswer> {
	
	/**
	 * Title:排序保存
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月22日
	 * @param answerListStr
	 */
	void sort(String answerListStr);
	
	/**
	 * Title:获得答题详情
	 * <p>Description:App调用</p>
	 * @author DeweiLi on 2016年6月22日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param actInfoListId
	 * @param isLoad
	 * @return
	 */
	ActAnswerResult answer(String sessionId, String token, Long actInfoListId, String isLoad);

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月28日
	 * @param actTestAnswer
	 */
	void saveAndModify(ActTestAnswer actTestAnswer);
}
