package com.cqliving.cloud.online.order.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.order.domain.OrderPayConfig;
import com.cqliving.tool.common.Response;

/**
 * 订单支付配置表 Service
 * Date: 2016-11-21 21:35:27
 * @author Code Generator
 */
public interface OrderPayConfigService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<OrderPayConfig>> queryForPage(PageInfo<OrderPayConfig> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<OrderPayConfig> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(OrderPayConfig domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月8日
	 * @param appId
	 * @param payModel
	 * @return
	 */
	public Response<OrderPayConfig> getByAppId(Long appId, Byte payModel);
}
