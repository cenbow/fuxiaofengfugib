package com.cqliving.cloud.online.building.manager;

import java.util.List;

import com.cqliving.cloud.online.building.domain.BuildingImage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 楼房图片表 Manager
 * Date: 2016-10-10 17:07:13
 * @author Code Generator
 */
public interface BuildingImageManager extends EntityService<BuildingImage> {
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
	 * @param buildingId
	 * @param type
	 * @return
	 */
	public List<BuildingImage> getByBuilding(Long buildingId, Byte type);

}
