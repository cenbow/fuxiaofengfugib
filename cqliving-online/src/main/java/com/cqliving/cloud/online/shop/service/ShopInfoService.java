package com.cqliving.cloud.online.shop.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shop.domain.ShopInfo;
import com.cqliving.cloud.online.shop.dto.ShopInfoDto;
import com.cqliving.cloud.online.shop.dto.ShopInfoListDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.tool.common.Response;

/**
 * 商铺表 Service
 * Date: 2016-05-16 20:41:01
 * @author Code Generator
 */
public interface ShopInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShopInfo>> queryForPage(PageInfo<ShopInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShopInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ShopInfo domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 新增商铺</p>
	 * @author Tangtao on 2016年5月20日
	 * @param shopInfo
	 * @param images
	 * @param userId 
	 * @param userName 
	 * @return
	 */
	Response<Void> save(ShopInfo shopInfo, String images, Long userId, String userName);
	
	/**
	 * <p>Description: 获取商铺列表（滚动分页）</p>
	 * @author Tangtao on 2016年5月28日
	 * @param scrollPage
	 * @param lat 
	 * @param lng 
	 * @param appId
	 * @param shopTypeId 
	 * @param regionCode
	 * @param shopCategoryId
	 * @param shopName 
	 * @return
	 */
	Response<ScrollPage<ShopInfoDto>> queryForScrollPage(ScrollPage<ShopInfoDto> scrollPage, Double lat, Double lng, Long appId, Long shopTypeId, String regionCode, Long shopCategoryId, String shopName);
	
	/**
	 * <p>Description: 置顶</p>
	 * @author Tangtao on 2016年7月6日
	 * @param id
	 * @param nickname
	 * @param userId
	 * @return
	 */
	Response<Void> top(Long id, String nickname, Long userId);
	
	/**
	 * <p>Description: 取消指定</p>
	 * @author Tangtao on 2016年7月25日
	 * @param id
	 * @param nickname
	 * @param userId
	 * @return
	 */
	Response<Void> untop(Long id, String nickname, Long userId);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月24日
	 * @param shopInfo
	 * @param images
	 * @param descriptions
	 * @param sessionId
	 * @param token
	 * @return
	 */
	Response<Byte> save(ShopInfo shopInfo, String images, String descriptions, String sessionId, String token);
	
	/**
	 * Title:审核
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月24日
	 * @param shopInfoIds
	 * @param status
	 * @param content
	 * @return
	 */
	Response<Void> audit(Long[] shopInfoIds, Byte status, String content);
	
	/**
	 * <p>Description: 首页商情列表</p>
	 * @author Tangtao on 2016年12月7日
	 * @param appId
	 * @return
	 */
	Response<List<ShopInfo>> getRecommendIndex(Long appId);
	
	/**
	 * 获取首页商情列表
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年12月13日下午3:42:22
	 */
	Response<List<ShopInfoDto>> getShopRecommendIndex(Long appId);
	
	/**
     * 分页查询商商情信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     */
	public Response<PageInfo<ShopInfoListDto>> queryByPage(PageInfo<ShopInfoListDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	
}