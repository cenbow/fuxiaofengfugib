package com.cqliving.cloud.online.account.manager;

import com.cqliving.cloud.online.account.domain.UserActTest;
import com.cqliving.cloud.online.act.domain.UserActTestClassify;
import com.cqliving.framework.common.service.EntityService;

/**
 * 用户答题表 Manager
 * Date: 2016-06-07 09:29:42
 * @author Code Generator
 */
public interface UserActTestManager extends EntityService<UserActTest> {
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月27日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param actTestClassifyId
	 * @param startTime
	 * @param questionIds
	 * @param answerIds
	 * @param inputQuestionIds
	 * @param inputAnswerValues
	 * @return
	 */
	public UserActTestClassify save(Long appId, String sessionId, String token, Long actTestClassifyId, Long startTime, Integer isFinish, Long[] questionIds, String[] answerIds, Long[] inputQuestionIds, String[] inputAnswerValues);
}
