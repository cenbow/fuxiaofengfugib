package com.cqliving.cloud.online.building.service;

import java.util.Map;

import com.cqliving.cloud.online.building.domain.BuildingInfo;
import com.cqliving.cloud.online.building.dto.BuildingInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.tool.common.Response;

/**
 * 楼房信息表 Service
 * Date: 2016-10-10 17:07:08
 * @author Code Generator
 */
public interface BuildingInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<BuildingInfo>> queryForPage(PageInfo<BuildingInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<BuildingInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月12日
	 * @param buildingInfo
	 * @param images
	 * @param descType
	 * @param descArea
	 * @param userId
	 * @param userName
	 * @return
	 */
	public Response<Void> save(BuildingInfo buildingInfo, String[] images, String[] descType, String[] descArea, Long userId, String userName);

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月12日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	public Response<Void> modifySortNo(Long id, Integer sortNo);
	//列表滚动分页查询
	public Response<ScrollPage<BuildingInfo>> queryScrollPage(ScrollPage<BuildingInfo> scrollPage,Map<String,Object> conditions);
	
	//置业详情
	public Response<BuildingInfoDto> buildingDetail(Long buildingId);
}
