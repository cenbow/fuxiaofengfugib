package com.cqliving.cloud.online.shopping.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.cloud.online.shopping.domain.ShoppingRecommend;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.tool.common.Response;

/**
 * 商品表 Service
 * Date: 2016-11-17 15:41:33
 * @author Code Generator
 */
public interface ShoppingGoodsService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShoppingGoods>> queryForPage(PageInfo<ShoppingGoods> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShoppingGoods> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ShoppingGoods domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月25日
	 * @param scrollPage
	 * @param conditionMap
	 * @return
	 */
	Response<ScrollPage<ShoppingGoodsDto>> queryForScrollPage(ScrollPage<ShoppingGoodsDto> scrollPage, Map<String, Object> conditionMap);
	
	/**
	 * <p>Description: 我的购物车</p>
	 * @author Tangtao on 2016年11月28日
	 * @param appId 
	 * @param sessionId
	 * @param token
	 * @return
	 */
	Response<List<ShoppingGoodsDto>> getMyCart(Long appId, String sessionId, String token);
	
	/**
     * 分页查询商品信息
        SELECT a.*,b.name FROM shopping_goods a INNER JOIN shopping_category b ON a.category_level_two_id =b.id 
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月1日上午9:43:57
     */
	public Response<PageInfo<ShoppingGoodsDto>> queryByPage(PageInfo<ShoppingGoodsDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap);
	
	/**
     * 通过id查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月1日上午9:43:57
     */
	public Response<ShoppingGoodsDto> getById(Long id);
	
	/**
     * 修改排序号
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月22日下午5:15:54
     */
    public Response<Void> updateSortNo(Integer sortNo,Long ids);
    
	//商品修改及新增
	public Response<Void> updateShoppingGoods(ShoppingGoods shoppingGoods);
	
	/**
     * 修改推荐到首页
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    public Response<Void> updateIsRecommemdIndex(Byte isRecommemdIndex,ShoppingRecommend recommend);
    
    /**
     * 修改推荐到轮播
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    public Response<Void> updateIsRecommendCarousel(Byte isRecommendCarousel,ShoppingRecommend recommend);
}