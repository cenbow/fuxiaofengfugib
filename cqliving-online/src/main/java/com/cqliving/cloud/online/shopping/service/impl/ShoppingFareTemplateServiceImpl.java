package com.cqliving.cloud.online.shopping.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplate;
import com.cqliving.cloud.online.shopping.dto.ShoppingFareTemplateDto;
import com.cqliving.cloud.online.shopping.manager.ShoppingFareTemplateManager;
import com.cqliving.cloud.online.shopping.service.ShoppingFareTemplateService;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.framework.common.exception.BusinessException;

@Service("shoppingFareTemplateService")
@ServiceHandleMapping(managerClass = ShoppingFareTemplateManager.class)
public class ShoppingFareTemplateServiceImpl implements ShoppingFareTemplateService {

	private static final Logger logger = LoggerFactory.getLogger(ShoppingFareTemplateServiceImpl.class);
	
	@Autowired
	private ShoppingFareTemplateManager shoppingFareTemplateManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询运费模板表失败")})
	public Response<PageInfo<ShoppingFareTemplate>> queryForPage(PageInfo<ShoppingFareTemplate> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询运费模板表失败")})
	public Response<ShoppingFareTemplate> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除运费模板表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除运费模板表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		logger.info("删除操作");
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存运费模板表失败")})
	public Response<Void> save(ShoppingFareTemplate shoppingFareTemplate) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.shopping.service.ShoppingFareTemplateService#saveOrUpdate(com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplate)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> saveOrUpdate(ShoppingFareTemplate fareTemplate) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.shopping.service.ShoppingFareTemplateService#findConditions(java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<ShoppingFareTemplateDto>> findConditions(Map<String, Object> conditions) {
		return null;
	}
}
