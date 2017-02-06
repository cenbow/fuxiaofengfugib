package com.cqliving.cloud.online.act.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.ActTemplate;
import com.cqliving.cloud.online.act.manager.ActTemplateManager;
import com.cqliving.cloud.online.act.service.ActTemplateService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("actTemplateService")
@ServiceHandleMapping(managerClass = ActTemplateManager.class)
public class ActTemplateServiceImpl implements ActTemplateService {

	private static final Logger logger = LoggerFactory.getLogger(ActTemplateServiceImpl.class);
	
	@Autowired
	private ActTemplateManager actTemplateManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询活动模板表失败")})
	public Response<PageInfo<ActTemplate>> queryForPage(PageInfo<ActTemplate> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询活动模板表失败")})
	public Response<ActTemplate> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动模板表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存活动模板表失败")})
	public Response<Void> save(ActTemplate actTemplate) {
		return null;
	}

	@Override
	public Response<List<ActTemplate>> getByApp(Long appId, Byte type) {
		Response<List<ActTemplate>> rs = Response.newInstance();
		try {
			rs.setData(actTemplateManager.getByApp(appId, type));
		} catch (Exception e) {
			logger.error("活动模板数据获取失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("活动模板数据获取失败");
		}
		return rs;
	}
}
