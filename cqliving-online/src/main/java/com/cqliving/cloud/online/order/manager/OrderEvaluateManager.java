package com.cqliving.cloud.online.order.manager;

import java.util.Map;

import com.cqliving.cloud.online.order.domain.OrderEvaluate;
import com.cqliving.cloud.online.order.dto.OrderEvaluateDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 订单商品评价表 Manager
 * Date: 2016-11-23 17:32:16
 * @author Code Generator
 */
public interface OrderEvaluateManager extends EntityService<OrderEvaluate> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
	
	/**
     * 分页查询评论信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月25日上午11:37:43
     */
    public PageInfo<OrderEvaluateDto> queryEvaluateForPage(PageInfo<OrderEvaluateDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap);
    
    /**
     * 通过Id查询评论信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月25日上午11:40:42
     */
    public OrderEvaluateDto getById(Long id);
    
    /**
     * 审核
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月29日下午2:24:14
     */
    public void auditing(Long[] ids,Long[] goodsIds,Byte status,String auditingContent);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年12月1日
	 * @param scrollPage
	 * @param conditionMap
	 * @return
	 */
	ScrollPage<OrderEvaluateDto> queryForScrollPage(ScrollPage<OrderEvaluateDto> scrollPage, Map<String, Object> conditionMap);

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
	Byte add(Long appId, String sessionId, String token, Long orderId, String goodsIds, String goodsScores, String contents, String imgs);
	
}