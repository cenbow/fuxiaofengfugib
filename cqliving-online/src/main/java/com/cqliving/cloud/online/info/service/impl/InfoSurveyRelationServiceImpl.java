package com.cqliving.cloud.online.info.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InfoSurveyRelation;
import com.cqliving.cloud.online.info.manager.InfoSurveyRelationManager;
import com.cqliving.cloud.online.info.service.InfoSurveyRelationService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("infoSurveyRelationService")
@ServiceHandleMapping(managerClass = InfoSurveyRelationManager.class)
public class InfoSurveyRelationServiceImpl implements InfoSurveyRelationService {

	//private static final Logger logger = LoggerFactory.getLogger(InfoSurveyRelationServiceImpl.class);
	
	@Autowired
	private InfoSurveyRelationManager infoSurveyRelationManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯调研关联表失败")})
	public Response<PageInfo<InfoSurveyRelation>> queryForPage(PageInfo<InfoSurveyRelation> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯调研关联表失败")})
	public Response<InfoSurveyRelation> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯调研关联表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯调研关联表失败")})
	public Response<Void> save(InfoSurveyRelation infoSurveyRelation) {
		return null;
	}
}
