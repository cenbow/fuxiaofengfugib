package com.cqliving.cloud.online.shopping.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shopping.domain.ShoppingRecommend;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.cloud.online.shopping.dto.ShoppingRecommendDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 商品推荐表 Manager
 * Date: 2016-11-17 15:41:40
 * @author Code Generator
 */
public interface ShoppingRecommendManager extends EntityService<ShoppingRecommend> {
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
	 * <p>Description: 获取轮播数据</p>
	 * @author Tangtao on 2016年11月21日
	 * @param appId
	 * @return
	 */
	List<ShoppingGoodsDto> getCarouselByAppId(Long appId);
	
	/**
	 * 分页查询商城首页信息
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年11月22日下午2:45:25
	 */
	public PageInfo<ShoppingRecommendDto> queryDtoForPage(PageInfo<ShoppingRecommendDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	
	/**
	 * 修改排序号
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年11月22日下午5:18:43
	 */
	public void updateSortNo(Integer sortNo, Long id);
	
	/**
     * 通过id查询推荐详情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月23日下午3:43:38
     */
	public ShoppingRecommendDto getById(Long id);

	/**
	 * <p>Description: 获取首页推荐商品</p>
	 * @author Tangtao on 2016年11月23日
	 * @param conditionMap
	 * @param orderMap
	 * @return
	 */
	List<ShoppingGoodsDto> getIndex(Map<String, Object> conditionMap, Map<String, Boolean> orderMap);
	
}