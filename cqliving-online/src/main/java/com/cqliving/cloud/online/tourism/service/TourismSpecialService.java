package com.cqliving.cloud.online.tourism.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.tourism.domain.TourismSpecial;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 旅游专题关系表 Service
 * Date: 2016-08-23 13:55:36
 * @author Code Generator
 */
public interface TourismSpecialService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<TourismSpecial>> queryForPage(PageInfo<TourismSpecial> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<TourismSpecial> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(TourismSpecial domain);
	/** @author Code Generator *****end*****/
	//查询专题子景点
	public Response<PageInfo<TourismInfoDto>> queryForSpecialSub(PageInfo<TourismInfoDto> pageInfo,Map<String,Object> conditions,Map<String,Boolean> orderMap);
	//修改专题子景点排序
	public Response<Void> updateSortNo(Long tourismSpecialId,Integer sortNo);
	//查询未加入专题的子景点
	public Response<PageInfo<TourismInfoDto>> queryForNoJoinSpecial(PageInfo<TourismInfoDto> pageInfo,Map<String,Object> conditions,Map<String,Boolean> orderMap);
	//加入子景点
	public Response<Void> joinSecial(TourismSpecial tourismSpecial,Long[] refTourismId);
	
	/**
	 * <p>Description: 子经典列表（无分页）</p>
	 * @author Tangtao on 2016年8月25日
	 * @param appId
	 * @param tourismId
	 * @param lat 
	 * @param lng 
	 * @return
	 */
	Response<List<TourismInfoDto>> queryForSubList(Long appId, Long tourismId, double lat, double lng);
	
}
