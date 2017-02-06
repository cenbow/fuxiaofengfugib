package com.cqliving.cloud.online.county.service;

import java.util.Map;

import com.cqliving.cloud.online.county.domain.CountyHouses;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.tool.common.Response;

/**
 * 区县楼盘表 Service
 * Date: 2017-01-05 10:11:11
 * @author Code Generator
 */
public interface CountyHousesService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<CountyHouses>> queryForPage(PageInfo<CountyHouses> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<CountyHouses> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(CountyHouses domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * 获取区县和楼盘信息并保存
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2017年1月5日上午10:15:54
	 */
	public Response<Void> getAndSaveTask();
	
	/**
     * 上线
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日上午10:27:50
     */
	public Response<Void> online();
	
	/**
	 * 滚动分页获取区县楼盘信息
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2017年1月6日下午2:29:21
	 */
	public Response<ScrollPage<CountyHouses>> getScrollPage(ScrollPage<CountyHouses> page, Map<String, Object> conditions,String token,String sessionId);
}
