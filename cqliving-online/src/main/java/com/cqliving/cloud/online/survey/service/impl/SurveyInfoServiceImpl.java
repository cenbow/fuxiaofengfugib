package com.cqliving.cloud.online.survey.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.survey.domain.SurveyInfo;
import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswer;
import com.cqliving.cloud.online.survey.dto.SurveyInfoDto;
import com.cqliving.cloud.online.survey.manager.SurveyInfoManager;
import com.cqliving.cloud.online.survey.service.SurveyInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("surveyInfoService")
@ServiceHandleMapping(managerClass = SurveyInfoManager.class)
public class SurveyInfoServiceImpl implements SurveyInfoService {

	//private static final Logger logger = LoggerFactory.getLogger(SurveyInfoServiceImpl.class);
	
	@Autowired
	private SurveyInfoManager surveyInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询调研表失败")})
	public Response<PageInfo<SurveyInfo>> queryForPage(PageInfo<SurveyInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询调研表失败")})
	public Response<SurveyInfo> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除调研表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存调研表失败")})
	public Response<Void> save(SurveyInfo surveyInfo) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.survey.service.SurveyInfoService#saveVote(com.cqliving.cloud.online.survey.domain.SurveyInfo)
	 */
	@Override
	@ServiceMethodHandle
	public Response<SurveyInfo> saveVote(SurveyInfo surveyInfo,SurveyQuestionAnswer[] surveyAnswers) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.survey.service.SurveyInfoService#saveSurvey(com.cqliving.cloud.online.survey.domain.SurveyInfo, java.lang.Long[])
	 */
	@Override
	@ServiceMethodHandle
	public Response<SurveyInfo> saveSurvey(SurveyInfo surveyInfo, Long[] questionIds) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.survey.service.SurveyInfoService#saveArena(javax.servlet.http.HttpServletRequest, com.cqliving.cloud.online.survey.domain.SurveyInfo, java.util.List)
	 */
	@Override
	@ServiceMethodHandle
	public Response<SurveyInfo> saveArena(SurveyInfo surveyInfo,List<SurveyQuestionAnswer> surveyAnswers) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.survey.service.SurveyInfoService#findByInfoId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<SurveyInfoDto> findByInfoContentId(Long infoContentId) {
		return null;
	}
}
