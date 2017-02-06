package com.cqliving.cloud.online.shopping.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.cloud.online.shopping.domain.ShoppingRecommend;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 商品表 Manager
 * Date: 2016-11-17 15:41:33
 * @author Code Generator
 */
public interface ShoppingGoodsManager extends EntityService<ShoppingGoods> {
	
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
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月25日
	 * @param scrollPage
	 * @param conditionMap
	 * @return
	 */
	ScrollPage<ShoppingGoodsDto> queryForScrollPage(ScrollPage<ShoppingGoodsDto> scrollPage, Map<String, Object> conditionMap);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月28日
	 * @param appId 
	 * @param sessionId
	 * @param token
	 * @return
	 */
	List<ShoppingGoodsDto> getMyCart(Long appId, String sessionId, String token);
	
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
    
    /**
     * 修改排序号
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月22日下午5:18:43
     */
    public void updateSortNo(Integer sortNo, Long id);
    
    public void updateShoppingGoods(ShoppingGoods shoppingGoods);
    
    /**
     * 修改推荐到首页
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    public void updateIsRecommemdIndex(Byte isRecommemdIndex,ShoppingRecommend recommend);
    
    /**
     * 修改推荐到轮播
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    public void updateIsRecommendCarousel(Byte isRecommendCarousel,ShoppingRecommend recommend);
	
}