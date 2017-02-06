package com.cqliving.cloud.online.shop.dao;

import java.util.Map;

import com.cqliving.cloud.online.shop.dto.ShopInfoDto;
import com.cqliving.cloud.online.shop.dto.ShopInfoListDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

public interface ShopInfoDaoCustom {
	
	/**
	 * <p>Description: 按距离获取商铺列表（滚动分页）</p>
	 * @author Tangtao on 2016年5月28日
	 * @param scrollPage
	 * @param map
	 * @param lat 
	 * @param lng 
	 * @return
	 */
	ScrollPage<ShopInfoDto> queryForScrollPageByDistance(ScrollPage<ShopInfoDto> scrollPage, Map<String, Object> map, double lat, double lng);
	
	/**
	 * <p>Description: 按业务字段倒序获取商铺列表（滚动分页）</p>
	 * @author Tangtao on 2016年5月30日
	 * @param scrollPage
	 * @param map
	 * @param columnName
	 * @return
	 */
	ScrollPage<ShopInfoDto> queryForScrollPage(ScrollPage<ShopInfoDto> scrollPage, Map<String, Object> map, String columnName);
	
	/**
     * 分页查询商商情信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     */
    public PageInfo<ShopInfoListDto> queryByPage(PageInfo<ShopInfoListDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	
}