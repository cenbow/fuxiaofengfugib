package com.cqliving.cloud.online.info.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InfoTheme;
import com.cqliving.cloud.online.info.manager.InfoThemeManager;
import com.cqliving.cloud.online.info.service.InfoThemeService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("infoThemeService")
@ServiceHandleMapping(managerClass = InfoThemeManager.class)
public class InfoThemeServiceImpl implements InfoThemeService {

	private static final Logger logger = LoggerFactory.getLogger(InfoThemeServiceImpl.class);
	
	@Autowired
	private InfoThemeManager infoThemeManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯专题分类表失败")})
	public Response<PageInfo<InfoTheme>> queryForPage(PageInfo<InfoTheme> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯专题分类表失败")})
	public Response<InfoTheme> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯专题分类表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯专题分类表失败")})
	public Response<Void> save(InfoTheme infoTheme) {
		return null;
	}

	@Override
	public Response<List<InfoTheme>> getByApp(Long appId) {
		Response<List<InfoTheme>> response = Response.newInstance();
		try {
			List<InfoTheme> data = infoThemeManager.getByApp(appId);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取专题分类失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取专题分类失败");
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoThemeService#findByInfoClassifyId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<InfoTheme>> findByInfoClassifyId(Long infoClassifyId) {
		return null;
	}
}
