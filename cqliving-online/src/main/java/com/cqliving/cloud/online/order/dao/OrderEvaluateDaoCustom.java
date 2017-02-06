package com.cqliving.cloud.online.order.dao;

import java.util.Map;

import com.cqliving.cloud.online.order.dto.OrderEvaluateDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;


public interface OrderEvaluateDaoCustom {
    
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
     * <p>Description:</p>
     * @author Tangtao on 2016年12月1日
     * @param scrollPage
     * @param conditionMap
     * @return
     */
    ScrollPage<OrderEvaluateDto> queryForScrollPage(ScrollPage<OrderEvaluateDto> scrollPage, Map<String, Object> conditionMap);

}