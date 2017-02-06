package com.cqliving.cloud.online.shopping.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shopping.domain.ShoppingRecommend;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.cloud.online.shopping.dto.ShoppingRecommendDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 商品推荐表 Service
 * Date: 2016-11-17 15:41:40
 * @author Code Generator
 */
public interface ShoppingRecommendService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShoppingRecommend>> queryForPage(PageInfo<ShoppingRecommend> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShoppingRecommend> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ShoppingRecommend domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 获取推荐轮播数据</p>
	 * @author Tangtao on 2016年11月21日
	 * @param appId
	 * @return
	 */
	Response<List<ShoppingGoodsDto>> getCarouselByAppId(Long appId);
	
	/**
     * 分页查询商城首页信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月22日下午2:45:25
     */
	public Response<PageInfo<ShoppingRecommendDto>> queryDtoForPage(PageInfo<ShoppingRecommendDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	
	/**
	 * 修改排序号
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年11月22日下午5:15:54
	 */
    public Response<Void> updateSortNo(Integer sortNo,Long ids);
    
    /**
     * 通过id查询推荐详情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月23日下午3:43:38
     */
    public Response<ShoppingRecommendDto> getById(Long id);
    
	/**
	 * <p>Description: 获取首页推荐商品</p>
	 * @author Tangtao on 2016年11月23日
	 * @param conditionMap
	 * @param orderMap
	 * @return
	 */
	Response<List<ShoppingGoodsDto>> getIndex(Map<String, Object> conditionMap, Map<String, Boolean> orderMap);
	
}