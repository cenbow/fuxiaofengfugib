package com.cqliving.cloud.online.order.service;

import java.util.Map;

import com.cqliving.cloud.online.order.domain.OrderEvaluate;
import com.cqliving.cloud.online.order.dto.OrderEvaluateDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.tool.common.Response;

/**
 * 订单商品评价表 Service
 * Date: 2016-11-23 17:32:16
 * @author Code Generator
 */
public interface OrderEvaluateService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<OrderEvaluate>> queryForPage(PageInfo<OrderEvaluate> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<OrderEvaluate> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(OrderEvaluate domain);
	/** @author Code Generator *****end*****/
	
	/**
     * 分页查询评论信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月25日上午11:37:43
     */
    public Response<PageInfo<OrderEvaluateDto>> queryEvaluateForPage(PageInfo<OrderEvaluateDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap);
    
    /**
     * 通过Id查询评论信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月25日上午11:40:42
     */
    public Response<OrderEvaluateDto> getById(Long id);
	
    /**
     * 审核
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月29日下午2:18:36
     */
    public Response<Void> auditing(Long[] ids,Long[] goodsIds,Byte status,String auditingContent);
    
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年12月1日
	 * @param scrollPage
	 * @param conditionMap
	 * @return
	 */
	Response<ScrollPage<OrderEvaluateDto>> queryForScrollPage(ScrollPage<OrderEvaluateDto> scrollPage, Map<String, Object> conditionMap);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年12月8日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param orderId
	 * @param goodsIds
	 * @param goodsScores
	 * @param contents
	 * @param imgs
	 * @return
	 */
	Response<Boolean> add(Long appId, String sessionId, String token, Long orderId, String goodsIds, String goodsScores, String contents, String imgs);
	
}