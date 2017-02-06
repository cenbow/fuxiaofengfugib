package com.cqliving.cloud.online.survey.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswer;
import com.cqliving.cloud.online.survey.manager.SurveyQuestionAnswerManager;
import com.cqliving.cloud.online.survey.service.SurveyQuestionAnswerService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("surveyQuestionAnswerService")
@ServiceHandleMapping(managerClass = SurveyQuestionAnswerManager.class)
public class SurveyQuestionAnswerServiceImpl implements SurveyQuestionAnswerService {

	//private static final Logger logger = LoggerFactory.getLogger(SurveyQuestionAnswerServiceImpl.class);
	
	@Autowired
	private SurveyQuestionAnswerManager surveyQuestionAnswerManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询问题表答案表失败")})
	public Response<PageInfo<SurveyQuestionAnswer>> queryForPage(PageInfo<SurveyQuestionAnswer> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询问题表答案表失败")})
	public Response<SurveyQuestionAnswer> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除问题表答案表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存问题表答案表失败")})
	public Response<Void> save(SurveyQuestionAnswer surveyQuestionAnswer) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.survey.service.SurveyQuestionAnswerService#updateStatus(java.lang.Long, java.lang.Byte)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> updateStatus(Long id, Byte status) {
		return null;
	}
}
