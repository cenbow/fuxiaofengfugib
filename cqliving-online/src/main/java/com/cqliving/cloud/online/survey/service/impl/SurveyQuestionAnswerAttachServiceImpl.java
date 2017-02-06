package com.cqliving.cloud.online.survey.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswerAttach;
import com.cqliving.cloud.online.survey.manager.SurveyQuestionAnswerAttachManager;
import com.cqliving.cloud.online.survey.service.SurveyQuestionAnswerAttachService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("surveyQuestionAnswerAttachService")
@ServiceHandleMapping(managerClass = SurveyQuestionAnswerAttachManager.class)
public class SurveyQuestionAnswerAttachServiceImpl implements SurveyQuestionAnswerAttachService {

	//private static final Logger logger = LoggerFactory.getLogger(SurveyQuestionAnswerAttachServiceImpl.class);
	
	@Autowired
	private SurveyQuestionAnswerAttachManager surveyQuestionAnswerAttachManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询调研问题表答案附加属性失败")})
	public Response<PageInfo<SurveyQuestionAnswerAttach>> queryForPage(PageInfo<SurveyQuestionAnswerAttach> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询调研问题表答案附加属性失败")})
	public Response<SurveyQuestionAnswerAttach> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除调研问题表答案附加属性失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存调研问题表答案附加属性失败")})
	public Response<Void> save(SurveyQuestionAnswerAttach surveyQuestionAnswerAttach) {
		return null;
	}
}
