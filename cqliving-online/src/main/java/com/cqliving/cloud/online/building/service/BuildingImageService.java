package com.cqliving.cloud.online.building.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.building.domain.BuildingImage;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 楼房图片表 Service
 * Date: 2016-10-10 17:07:13
 * @author Code Generator
 */
public interface BuildingImageService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<BuildingImage>> queryForPage(PageInfo<BuildingImage> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<BuildingImage> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(BuildingImage domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月12日
	 * @param buildingId
	 * @param type
	 * @return
	 */
	public Response<List<BuildingImage>> getByBuilding(Long buildingId, Byte type);
	
}
