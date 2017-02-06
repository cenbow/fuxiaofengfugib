package com.cqliving.cloud.online.shop.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shop.domain.ShopInfo;
import com.cqliving.cloud.online.shop.dto.ShopInfoDto;
import com.cqliving.cloud.online.shop.dto.ShopInfoListDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 商铺表 Manager
 * Date: 2016-05-16 20:41:01
 * @author Code Generator
 */
public interface ShopInfoManager extends EntityService<ShopInfo> {
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
	 * <p>Description: 保存商铺</p>
	 * @author Tangtao on 2016年5月20日
	 * @param shopInfo
	 * @param images
	 * @param userId 
	 * @param userName 
	 */
	void save(ShopInfo shopInfo, String images, Long userId, String userName);

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
	ScrollPage<ShopInfoDto> queryForScrollPage(ScrollPage<ShopInfoDto> scrollPage, Double lat, Double lng, Long appId, Long shopTypeId, String regionCode, Long shopCategoryId, String shopName);

	/**
	 * <p>Description: 置顶</p>
	 * @author Tangtao on 2016年7月6日
	 * @param id
	 * @param nickname
	 * @param userId
	 */
	void top(Long id, String nickname, Long userId);

	/**
	 * <p>Description: 取消置顶</p>
	 * @author Tangtao on 2016年7月25日
	 * @param id
	 * @param nickname
	 * @param userId
	 */
	void untop(Long id, String nickname, Long userId);

	/**
	 * Title:商情app用户保存接口
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月24日
	 * @param shopInfo
	 * @param images
	 * @param descriptions
	 * @param sessionId
	 * @param token
	 */
	Byte save(ShopInfo shopInfo, String images, String descriptions, String sessionId, String token);
	
	/**
	 * Title:审核
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月24日
	 * @param shopInfoIds
	 * @param status
	 * @param content
	 */
	void audit(Long[] shopInfoIds, Byte status, String content);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年12月7日
	 * @param appId
	 * @return
	 */
	List<ShopInfo> getRecommendIndex(Long appId);
	
	/**
     * 获取推荐到首页的商情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月13日下午3:34:53
     */
    List<ShopInfoDto> getShopRecommendIndex(Long appId);
	 
	/**
     * 分页查询商商情信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     */
    public PageInfo<ShopInfoListDto> queryByPage(PageInfo<ShopInfoListDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
}