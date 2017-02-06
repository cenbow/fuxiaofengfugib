package com.cqliving.cloud.online.config.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.config.domain.ConfigHotline;
import com.cqliving.cloud.online.config.dto.ConfigHotlineDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * config_热线表 Service
 * Date: 2016-07-07 11:54:13
 * @author Code Generator
 */
public interface ConfigHotlineService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ConfigHotline>> queryForPage(PageInfo<ConfigHotline> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ConfigHotline> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(String updator,Long updatorId,Long... id);
	public Response<Void> updateStatus(String updator,Long updatorId,Byte status,Long... ids);
	public Response<Void> save(ConfigHotline domain);
	/** @author Code Generator *****end*****/
	
	/**
     * 分页查询热线
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月7日
     */
    public Response<PageInfo<ConfigHotlineDto>> queryByPage(PageInfo<ConfigHotlineDto> pageInfo,Map<String, Object> conditions,Map<String, Boolean> orders);
    
    /**
     * 查询详情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月7日
     */
    public Response<ConfigHotlineDto> getById(Long id);
    
	/**
	 * <p>Description: 获取热线列表</p>
	 * @author Tangtao on 2016年7月8日
	 * @param map
	 * @param sortMap
	 * @return
	 */
	Response<List<ConfigHotline>> queryForList(Map<String, Object> map, Map<String, Boolean> sortMap);
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param ids
     * @return
     */
    public Response<Void> updateSortNo(Integer sortNo,String updator,Long updateUserId,Long... ids);
	
}
