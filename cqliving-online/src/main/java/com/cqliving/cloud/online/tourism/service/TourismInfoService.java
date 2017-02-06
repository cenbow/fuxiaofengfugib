package com.cqliving.cloud.online.tourism.service;

import java.util.Map;

import com.cqliving.cloud.online.tourism.domain.TourismInfo;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.tool.common.Response;

/**
 * 旅游表 Service
 * Date: 2016-08-23 13:55:25
 * @author Code Generator
 */
public interface TourismInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<TourismInfo>> queryForPage(PageInfo<TourismInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<TourismInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(TourismInfo domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年8月24日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	Response<PageInfo<TourismInfoDto>> queryDtoForPage(PageInfo<TourismInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年8月24日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	Response<Void> modifySortNo(Long id, Integer sortNo);
	
	/**
	 * <p>Description: 客户端旅游列表</p>
	 * @author Tangtao on 2016年8月25日
	 * @param scrollPage
	 * @param lat
	 * @param lng
	 * @param appId
	 * @param regionCode
	 * @param type 
	 * @param tourismName
	 * @return
	 */
	Response<ScrollPage<TourismInfoDto>> queryForScrollPage(ScrollPage<TourismInfoDto> scrollPage, double lat, double lng, Long appId, String regionCode, Byte type, String tourismName);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月24日
	 * @param tourismInfo
	 * @param images
	 * @param userId
	 * @param userName
	 * @return
	 */
	Response<Void> saveByAdmin(TourismInfo tourismInfo, String images, Long userId, String userName);
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param ids
     * @return
     */
    public Response<Void> updateSortNo(Integer sortNo,String updator,Long updateUserId,Long... ids);
}
