package com.cqliving.cloud.online.app.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.domain.AppQiniu;
import com.cqliving.cloud.online.app.domain.AppWeather;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 客户端 Manager
 * Date: 2016-04-15 09:43:46
 * @author Code Generator
 */
public interface AppInfoManager extends EntityService<AppInfo> {
    /**
     * 保存
     * @param domain
     * @param ip
     * @param password
     * @param username
     */
    public void saveAppInfo(AppInfo domain,String password, String username,String nickname,AppQiniu qiniu,AppWeather weather,Byte[] type,String[] content,String downLoadUrl);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param map
     * @param orderMap
     * @return
     */
    public PageInfo<AppInfoDto> queryPage(PageInfo<AppInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
    
    /**
     * 查询某用户对应数据权限表的App
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月29日上午9:47:42
     */
   // public PageInfo<AppInfoDto> queryPageByUser(PageInfo<AppInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);

	/**
	 * <p>Description: 获取 App 信息</p>
	 * @author Tangtao on 2016年4月27日
	 * @param appCode 客户端 code
	 * @return
	 */
	AppInfo getByCode(String code);
	
	public List<AppInfo> findByIds(List<Long> ids);
	
	//根据域名查找APPINFO，在自定义域名和后台域名之间找，找到一个就返回
	public AppInfo findByDomain(String domain);
	
	/**
     * 根据登录用户的用户类型，用户id查询客户端信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月2日
     */
    public List<AppInfoDto> getBySysUser(Byte usertype,Long userId);
    
    /**
     * 根据登录用户的用户类型，用户id分页查询客户端信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月20日
     */
    public PageInfo<AppInfoDto> queryPageByUser(Byte usertype,Long userId,PageInfo<AppInfoDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap);
    
    /**
     * 缓存所有的APP
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月1日上午11:44:53
     */
    public List<AppInfo> saveAllCache();
    
    /**
     * 获取所有APP缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月1日上午11:52:20
     */
    public List<AppInfo> getAllCache();
    
    /**
     * 按照key设置domain缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月1日下午1:44:31
     */
    public void saveDomainCache(List<AppInfo> appList,String key);
    
    /**
     * 通过key和domain获取domain缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月1日下午2:05:19
     */
    public AppInfo getDomainCache(String key,String domain);
}
