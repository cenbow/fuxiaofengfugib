package com.cqliving.cloud.online.survey.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.survey.domain.SurveyQuestion;
import com.cqliving.cloud.online.survey.manager.SurveyQuestionManager;
import com.cqliving.cloud.online.survey.service.SurveyQuestionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("surveyQuestionService")
@ServiceHandleMapping(managerClass = SurveyQuestionManager.class)
public class SurveyQuestionServiceImpl implements SurveyQuestionService {

	//private static final Logger logger = LoggerFactory.getLogger(SurveyQuestionServiceImpl.class);
	
	@Autowired
	private SurveyQuestionManager surveyQuestionManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询调研问题表失败")})
	public Response<PageInfo<SurveyQuestion>> queryForPage(PageInfo<SurveyQuestion> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询调研问题表失败")})
	public Response<SurveyQuestion> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除调研问题表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存调研问题表失败")})
	public Response<Void> save(SurveyQuestion surveyQuestion) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.survey.service.SurveyQuestionService#deleteSurveyQuestion(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> deleteSurveyQuestion(Long id) {
		return null;
	}
}
