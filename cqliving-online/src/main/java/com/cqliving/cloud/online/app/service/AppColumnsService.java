package com.cqliving.cloud.online.app.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.dto.AppColumnsDto;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.GetColumnsData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 客户端栏目表 Service
 * Date: 2016-04-28 11:59:18
 * @author Code Generator
 */
public interface AppColumnsService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AppColumns>> queryForPage(PageInfo<AppColumns> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AppColumns> get(Long id);
	public Response<Void> delete(AppColumns appColumns);
	public Response<Void> save(AppColumns domain);
	/** @author Code Generator *****end*****/
	public Response<Void> saveColumns(AppColumns domain);
	public Response<List<AppColumnsDto>> getByConditions(Map<String,Object> conditions);
	public Response<List<AppColumnsDto>> getList(Map<String, Object> conditions, Map<String, Boolean> orderMap);
	
	/**
	 * <p>Description: 获取子栏目</p>
	 * @author Tangtao on 2016年5月1日
	 * @param appId
	 * @param parentCode
	 * @return
	 */
	Response<CommonListResult<GetColumnsData>> getChildren(Long appId, String parentCode);
	/**
	 * 编辑
	 * @param domain
	 * @return
	 */
	public Response<Void> updateColumns(AppColumns domain);
	public Response<Void> sort(Long[] ids,Integer[] sortNums,Long[] parentIds);
	
	/**
     * 发布
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月20日
     */
    public Response<Void> send(Long AppId);
    
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月18日
	 * @param conditions
	 * @param orderMap
	 * @return
	 */
	Response<List<AppColumns>> queryForList(Map<String, Object> conditions, Map<String, Boolean> orderMap);
	
	
	/**
	 * Title:
	 * <p>Description:从缓存获取栏目数据，排序是sortNo asc,id desc</p>
	 * @author fuxiaofeng on 2016年11月4日
	 * @param appId
	 * @return
	 */
	public Response<List<GetColumnsData>> getCache(Long appId);
    
}