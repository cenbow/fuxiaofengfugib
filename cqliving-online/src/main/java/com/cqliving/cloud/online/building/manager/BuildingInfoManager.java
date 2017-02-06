package com.cqliving.cloud.online.building.manager;

import java.util.Map;

import com.cqliving.cloud.online.building.domain.BuildingInfo;
import com.cqliving.cloud.online.building.dto.BuildingInfoDto;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 楼房信息表 Manager
 * Date: 2016-10-10 17:07:08
 * @author Code Generator
 */
public interface BuildingInfoManager extends EntityService<BuildingInfo> {
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
	public BuildingInfo save(BuildingInfo buildingInfo, String[] images, String[] descType, String[] descArea, Long userId, String userName);

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月12日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	public int modifySortNo(Long id, Integer sortNo);
	//置业列表滚动分页
	public ScrollPage<BuildingInfo> queryScrollPage(ScrollPage<BuildingInfo> scrollPage,
			Map<String, Object> conditions);
	
	//置业详情
	public BuildingInfoDto buildingDetail(Long buildingId);
}
