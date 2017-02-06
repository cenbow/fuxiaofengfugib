package com.cqliving.cloud.online.app.service;

import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppImageVersion;
import com.cqliving.cloud.online.app.dto.AppImageVersionDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 客户端发布广告表 Service
 * Date: 2016-05-04 16:01:26
 * @author Code Generator
 */
public interface AppImageVersionService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AppImageVersion>> queryForPage(PageInfo<AppImageVersion> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AppImageVersion> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(AppImageVersion domain);
	/** @author Code Generator *****end*****/
	public Response<PageInfo<AppImageVersionDto>> queryPage(PageInfo<AppImageVersionDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	//保存
	public Response<Void> saveImageVersion(AppImageVersion appImageVersion, String loadingUrl, String linkUrl,Integer showTime);
	public Response<AppImageVersionDto> getById(Long id);
	
	/**
     * 设置启用
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月26日下午1:46:47
     */
    public Response<Void> startUsing(Long id);
    
    /**
     * 设置停用
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月26日下午1:46:47
     */
    public Response<Void> nonUse(Long id);
}
