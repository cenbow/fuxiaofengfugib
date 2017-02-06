package com.cqliving.cloud.online.shopping.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateRegionDetail;
import com.cqliving.cloud.online.shopping.manager.ShoppingFareTemplateRegionDetailManager;
import com.cqliving.cloud.online.shopping.service.ShoppingFareTemplateRegionDetailService;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.framework.common.exception.BusinessException;

@Service("shoppingFareTemplateRegionDetailService")
@ServiceHandleMapping(managerClass = ShoppingFareTemplateRegionDetailManager.class)
public class ShoppingFareTemplateRegionDetailServiceImpl implements ShoppingFareTemplateRegionDetailService {

	private static final Logger logger = LoggerFactory.getLogger(ShoppingFareTemplateRegionDetailServiceImpl.class);
	
	@Autowired
	private ShoppingFareTemplateRegionDetailManager shoppingFareTemplateRegionDetailManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询运费模板明细地区表失败")})
	public Response<PageInfo<ShoppingFareTemplateRegionDetail>> queryForPage(PageInfo<ShoppingFareTemplateRegionDetail> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询运费模板明细地区表失败")})
	public Response<ShoppingFareTemplateRegionDetail> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除运费模板明细地区表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存运费模板明细地区表失败")})
	public Response<Void> save(ShoppingFareTemplateRegionDetail shoppingFareTemplateRegionDetail) {
		return null;
	}
}
