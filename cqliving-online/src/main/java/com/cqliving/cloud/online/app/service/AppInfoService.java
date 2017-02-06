package com.cqliving.cloud.online.app.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.domain.AppQiniu;
import com.cqliving.cloud.online.app.domain.AppWeather;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 客户端 Service
 * Date: 2016-04-15 09:43:46
 * @author Code Generator
 */
public interface AppInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AppInfo>> queryForPage(PageInfo<AppInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AppInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(AppInfo domain);
	public Response<Void> update(AppInfo domain);
	/** @author Code Generator *****end*****/
	public Response<List<AppInfo>> getAll();
	public Response<Void> saveAppInfo(AppInfo domain,String password, String username,String nickname,AppQiniu qiniu,AppWeather weather,Byte[] type,String[] content,String downLoadUrl);
	
	/**
     * 分页查询
     * @param pageInfo
     * @param map
     * @param orderMap
     * @return pageIndo
     * @author huxiaoping
     */
	public Response<PageInfo<AppInfoDto>> queryPage(PageInfo<AppInfoDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	
	/**
     * 查询某用户对应数据权限表的App
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月29日上午9:47:42
     */
	public Response<PageInfo<AppInfoDto>> queryPageByUser(Byte usertype,Long userId,PageInfo<AppInfoDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap);
	
	
	public Response<List<AppInfo>> findByIds(List<Long> ids);
	
	//根据域名查找appInfo
	public Response<AppInfo> findByDomain(String domain);
	/**
     * 根据登录用户的用户类型，用户id查询客户端信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月2日
     */
	public Response<List<AppInfoDto>> getBySysUser(Byte usertype,Long userId);
}