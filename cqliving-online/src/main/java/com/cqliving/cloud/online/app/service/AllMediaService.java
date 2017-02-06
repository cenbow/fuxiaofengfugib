package com.cqliving.cloud.online.app.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.app.domain.AllMedia;
import com.cqliving.cloud.online.app.dto.AllMediaDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 全媒体表 Service
 * Date: 2016-11-02 14:35:32
 * @author Code Generator
 */
public interface AllMediaService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AllMedia>> queryForPage(PageInfo<AllMedia> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AllMedia> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(AllMedia domain);
	/** @author Code Generator *****end*****/
	public Response<PageInfo<AllMediaDto>> queryPage(PageInfo<AllMediaDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月2日
	 * @param map
	 * @param sortMap
	 * @return
	 */
	Response<List<AllMedia>> queryForList(Map<String, Object> map, Map<String, Boolean> sortMap);
	
	Response<Void> updateSortNo(Long id,Integer sortNo);
	
}