package com.cqliving.cloud.online.config.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.config.domain.HousingPublic;
import com.cqliving.tool.common.Response;

/**
 * 公租房表 Service
 * Date: 2016-11-07 16:34:55
 * @author Code Generator
 */
public interface HousingPublicService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<HousingPublic>> queryForPage(PageInfo<HousingPublic> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<HousingPublic> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(HousingPublic domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月7日
	 * @param map
	 * @param sortMap
	 * @return
	 */
	Response<List<HousingPublic>> queryForList(Map<String, Object> map, Map<String, Boolean> sortMap);
	
}