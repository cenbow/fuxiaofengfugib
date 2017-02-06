package com.cqliving.cloud.online.app.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppMarketplaceResource;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 客户端发布市场资源表 Service
 * Date: 2016-05-04 15:48:25
 * @author Code Generator
 */
public interface AppMarketplaceResourceService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AppMarketplaceResource>> queryForPage(PageInfo<AppMarketplaceResource> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AppMarketplaceResource> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(AppMarketplaceResource domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年12月5日
	 * @param map
	 * @param sortMap
	 * @return
	 */
	Response<List<AppMarketplaceResource>> queryForList(Map<String, Object> map, Map<String, Boolean> sortMap);
	/**
     * 修改排序号
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月6日下午5:15:54
     */
    public Response<Void> updateSortNo(Integer sortNo,Long ids);
	
}