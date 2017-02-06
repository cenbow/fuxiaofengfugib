package com.cqliving.cloud.online.info.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoLable;
import com.cqliving.cloud.online.info.dto.InfoLableDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 资讯标签表 Service
 * Date: 2016-04-15 09:44:47
 * @author Code Generator
 */
public interface InfoLableService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<InfoLable>> queryForPage(PageInfo<InfoLable> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<InfoLable> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(InfoLable domain);
	/** @author Code Generator *****end*****/
	public Response<List<InfoLable>> findByAppId(Long appId, Byte sourceType);
	/**
     * 查询单条记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月6日下午5:13:06
     */
    public Response<InfoLableDto> getById(Long id);
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月6日下午5:13:06
     */
    public Response<PageInfo<InfoLableDto>> queryPage(PageInfo<InfoLableDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
}
