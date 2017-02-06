package com.cqliving.cloud.online.shopping.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年11月25日
 */
public interface ShoppingGoodsDaoCustom {
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月25日
	 * @param scrollPage
	 * @param conditionMap
	 * @return
	 */
	ScrollPage<ShoppingGoodsDto> queryForScrollPage(ScrollPage<ShoppingGoodsDto> scrollPage, Map<String, Object> conditionMap);	
	
	/**
	 * <p>Description: 我的购物车</p>
	 * @author Tangtao on 2016年11月28日
	 * @param scrollPage
	 * @param conditionMap
	 * @return
	 */
	List<ShoppingGoodsDto> getMyCart(Map<String, Object> conditionMap, Map<String, Boolean> orders);
	
	/**
     * 分页查询商品信息
        SELECT a.*,b.name FROM shopping_goods a INNER JOIN shopping_category b ON a.category_level_two_id =b.id 
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月1日上午9:43:57
     */
    public PageInfo<ShoppingGoodsDto> queryByPage(PageInfo<ShoppingGoodsDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap);
    
    /**
     * 通过id查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月1日上午9:43:57
     */
    public ShoppingGoodsDto getById(Long id);
	
}