package com.cqliving.cloud.online.shopping.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.cloud.online.shopping.dto.ShoppingRecommendDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年11月21日
 */
public interface ShoppingRecommendDaoCustom {
	
	/**
	 * <p>Description: 获取轮播数据</p>
	 * @author Tangtao on 2016年11月21日
	 * @param appId
	 * @return
	 */
	List<ShoppingGoodsDto> getCarouselByAppId(Long appId);
	
	/**
	 * <p>Description: 获取首页推荐商品</p>
	 * @author Tangtao on 2016年11月23日
	 * @param conditionMap
	 * @param orderMap
	 * @return
	 */
	List<ShoppingGoodsDto> getIndex(Map<String, Object> conditionMap, Map<String, Boolean> orderMap);
	
	/**
     * 分页查询商城首页信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月22日下午2:45:25
     */
    public PageInfo<ShoppingRecommendDto> queryDtoForPage(PageInfo<ShoppingRecommendDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
    
    public ShoppingRecommendDto getById(Long id);

}