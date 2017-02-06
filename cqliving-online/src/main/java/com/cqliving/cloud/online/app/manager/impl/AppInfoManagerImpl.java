package com.cqliving.cloud.online.app.manager.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cqliving.cloud.common.CacheConstant;
import com.cqliving.cloud.common.CommonSysRoleEnum;
import com.cqliving.cloud.common.Encryption;
import com.cqliving.cloud.online.account.dao.SmsTemplateDao;
import com.cqliving.cloud.online.account.domain.SmsTemplate;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.dao.AppQiniuDao;
import com.cqliving.cloud.online.app.dao.AppWeatherDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.domain.AppQiniu;
import com.cqliving.cloud.online.app.domain.AppWeather;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.manager.AppInfoManager;
import com.cqliving.cloud.online.app.manager.AppTempletManager;
import com.cqliving.cloud.online.config.dao.AppConfigDao;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.cloud.online.security.dao.SysUserDao;
import com.cqliving.cloud.online.security.dao.SysUserDataDao;
import com.cqliving.cloud.online.security.domain.SysRole;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.manager.SysRoleService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.redis.base.AbstractRedisClient;
import com.cqliving.tool.common.util.date.DateUtil;

@Service("appInfoManager")
public class AppInfoManagerImpl extends EntityServiceImpl<AppInfo, AppInfoDao> implements AppInfoManager {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private AppTempletManager appTempletManager;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private AppQiniuDao appQiniuDao;
    @Autowired
    private AppWeatherDao appWeatherDao;
    @Autowired
    private SmsTemplateDao smsTemplateDao;
    @Autowired
    private SysUserDataDao sysUserDataDao;
    @Autowired
    private AppConfigDao appConfigDao;
    @Autowired
    private AbstractRedisClient abstractRedisClient;
    
    @Override
    /**
     * 保存客户端信息
     * @param domain 客户端信息
     * @author huxiaoping
     */
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public void saveAppInfo(AppInfo domain, String password, String username,String nickname,
            AppQiniu qiniu,AppWeather weather,Byte[] type,String[] content,String downLoadUrl){
        
        //step1:校验域名是否合法
        checkDomain(domain);
        
        //step2:设置时间
        Date now = DateUtil.now();
        if(null!=domain.getId()){
            domain.setUpdateTime(now);
        }else{
            domain.setUpdateTime(now);
            domain.setCreateTime(now);
        }
        //step3:保存客户端信息
        this.save(domain);
        
        //step4:保存管理账户
        if(StringUtils.isNotBlank(username)){
            saveUser(password, username, nickname, domain, now);
        }
        
        //step5:七牛云服务配置
        if(null!=qiniu&&StringUtils.isNotBlank(qiniu.getBucketName())){
            saveQiniu(qiniu, domain, now);
        }
        
        //step6:天气信息
        if(null!=weather){
            saveWeather(weather, domain, now);
        }
        
        //step7:短信模板信息
        if(null!=type&&type.length>0){
            saveSmsTemplate(type, content, domain);
        }
        
        //step8：保存app配置表
        saveAppConfig(domain, downLoadUrl);
        
        //操作缓存
        operCache(domain);
    }
    
    /**
     * 校验域名的合法性
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月14日上午9:15:53
     */
    private void checkDomain(AppInfo domain){
        if(StringUtils.isNotBlank(domain.getAppDomain())&&domain.getAppDomain().trim().equals(domain.getCmsDomain().trim())){
            throw new BusinessException("自定义域名和后台域名重复,请修改！");
        }
        List<AppInfo> appList = null;
        if(null!=domain.getId()){
            //校验自定义域名是否重复
            if(StringUtils.isNotBlank(domain.getAppDomain())){
                appList = this.getEntityDao().findByAppDomainNotId(domain.getAppDomain().trim(), domain.getId());
                if(null!=appList && appList.size()>0){
                    throw new BusinessException("自定义域名重复,请修改！");
                }
                appList = this.getEntityDao().findByCmsDomainNotId(domain.getAppDomain().trim(), domain.getId());
                if(null!=appList && appList.size()>0){
                    throw new BusinessException("自定义域名与其他后台域名重复,请修改！");
                }
            }
            appList  = this.getEntityDao().findByCmsDomainNotId(domain.getCmsDomain().trim(), domain.getId());
            if(null!=appList && appList.size()>0){
                throw new BusinessException("后台域名重复,请修改！");
            }
            appList = this.getEntityDao().findByAppDomainNotId(domain.getCmsDomain().trim(), domain.getId());
            if(null!=appList && appList.size()>0){
                throw new BusinessException("后台域名与其他自定义域名重复,请修改！");
            }
        }else{
            if(StringUtils.isNotBlank(domain.getAppDomain())){
                appList = this.getEntityDao().findByAppDomain(domain.getAppDomain().trim());
                if(null!=appList && appList.size()>0){
                    throw new BusinessException("自定义域名重复,请修改！");
                }
                appList = this.getEntityDao().findByCmsDomain(domain.getAppDomain().trim());
                if(null!=appList && appList.size()>0){
                    throw new BusinessException("自定义域名与其他后台域名重复,请修改！");
                }
            }
            appList  = this.getEntityDao().findByCmsDomain(domain.getCmsDomain().trim());
            if(null!=appList && appList.size()>0){
                throw new BusinessException("后台域名重复,请修改！");
            }
            appList = this.getEntityDao().findByAppDomain(domain.getCmsDomain().trim());
            if(null!=appList && appList.size()>0){
                throw new BusinessException("后台域名与其他自定义域名重复,请修改！");
            }
        }
    }
    
    /**
     * 保存管理账户
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月14日上午9:21:39
     */
    private void saveUser(String password, String username,String nickname,AppInfo domain,Date now){
        Boolean validateUserName = Encryption.validateUserName(username);
        if(!validateUserName){
            throw new BusinessException(-1,"管理账号格式不正确，只能数字字母，最少6位");
        }
        SysUser sysUser = sysUserDao.findByUsername(username); 
        if(null!=sysUser){
            throw new BusinessException("管理账号称已存在，请更换！");
        }
        // 创建角色
        sysRoleService.giveCommonRoleByAppId(domain.getId());
        String code = CommonSysRoleEnum.DISTRICT_MANAGER.getCode().toString();
        // 获取APP管理员的角色
        SysRole role = sysRoleService.getByAppIdAndCode(domain.getId(), code);
        //保存管理用户信息
        sysUser = new SysUser();
        if(null!=role){
            Set<SysRole> roles = new HashSet<SysRole>();
            roles.add(role);
            sysUser.setRole(roles);
        }
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        Encryption.entryptPassword(sysUser);
        sysUser.setAppId(domain.getId());
        sysUser.setNickname(StringUtils.isBlank(nickname)?username:nickname);
        sysUser.setStatus(SysUser.STATUS1);
        sysUser.setCreateDate(now);
        //app管理员
        sysUser.setUsertype(SysUser.USERTYPE3);
        sysUser.setCreateDate(now);
        sysUserDao.save(sysUser);
        //复制模板
        appTempletManager.copyConTemplet(domain.getId());
        //数据权限表实例化
        SysUserData userData = new SysUserData();
        userData.setValue(domain.getId());
        userData.setUserId(sysUser.getId());
        sysUserDataDao.save(userData);
    }
    
    /**
     * 保存七牛云信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月14日上午9:22:09
     */
    private void saveQiniu(AppQiniu qiniu,AppInfo domain,Date now){
        if(null!=qiniu.getId()){
            AppQiniu qiniuOld = appQiniuDao.get(qiniu.getId());
            if(null!=qiniuOld){
                qiniuOld.setBucketName(qiniu.getBucketName());
                qiniuOld.setDomainCustom(qiniu.getDomainCustom());
                qiniuOld.setDomainTest(qiniu.getDomainTest());
                qiniuOld.setIsDefault(qiniu.getIsDefault());
                qiniuOld.setUpdator(domain.getCreator());
                qiniuOld.setUpdateTime(now);
                qiniu = qiniuOld;
            }
        }else{
            qiniu.setAppId(domain.getId());
            qiniu.setStatus(AppQiniu.STATUS3);
            qiniu.setCreateTime(now);
            qiniu.setCreatorId(domain.getCreatorId());
            qiniu.setCreator(domain.getCreator());
            qiniu.setUpdatorId(domain.getCreatorId());
            qiniu.setUpdator(domain.getCreator());
            qiniu.setUpdateTime(now);
            qiniu.setIsDefault(AppQiniu.ISDEFAULT0);
        }
        appQiniuDao.save(qiniu);
    }
    
    /**
     * 保存获取天气预报的基础信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月14日上午9:25:46
     */
    private void saveWeather(AppWeather weather,AppInfo domain,Date now){
        if(null!=weather.getId()){
            AppWeather weatherOld = appWeatherDao.get(weather.getId());
            weatherOld.setCityName(weather.getCityName());
            weatherOld.setCityPhoneticize(weather.getCityPhoneticize());
            weatherOld.setIsDefault(weather.getIsDefault());
            weatherOld.setUpdateTime(now);
            weather = weatherOld;
        }else{
            weather.setAppId(domain.getId());
            weather.setCreateTime(now);
            weather.setUpdateTime(now);
        }
        appWeatherDao.save(weather);
    }
    
    /**
     * 保存短信模板信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月14日上午9:26:54
     */
    private void saveSmsTemplate(Byte[] type,String[] content,AppInfo domain){
        List<SmsTemplate> smsListDB = smsTemplateDao.getByAppId(domain.getId());
        if(null!=smsListDB&&smsListDB.size()>0){
            for (SmsTemplate smsTemplate : smsListDB) {
                for (int i = 0; i < type.length; i++) {
                    if(smsTemplate.getType().equals(type[i])){
                        smsTemplate.setContent(content[i]);
                        break;
                    }
                }
            }
            smsTemplateDao.saves(smsListDB);
        }else{
            List<SmsTemplate> smsList = new ArrayList<SmsTemplate>();
            SmsTemplate sms ;
            for (int i = 0; i < type.length; i++) {
                if(null!=type[i]){
                    sms = new SmsTemplate();
                    sms.setAppId(domain.getId());
                    sms.setType(type[i]);
                    sms.setContent(content[i]);
                    smsList.add(sms);
                }
            }
            if(smsList.size()>0){
                smsTemplateDao.saves(smsList);
            }
        }
    }
    
    /**
     * 保存app配置表
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月21日上午10:32:33
     */
    private void saveAppConfig(AppInfo domain,String downLoadUrl){
        if(StringUtils.isNotBlank(downLoadUrl)&&null!=domain&&null!=domain.getId()){
            AppConfig appConfig;
            List<AppConfig> appConfigList = appConfigDao.findByAppId(domain.getId());
            if(null!=appConfigList&&appConfigList.size()>0){
                appConfig = appConfigList.get(0);
                appConfig.setDownLoadUrl(downLoadUrl);
                appConfigDao.update(appConfig);
            }else{
                appConfig = new AppConfig();
                appConfig.setAppId(domain.getId());
                appConfig.setDownLoadUrl(downLoadUrl);
                appConfigDao.save(appConfig);
            }
        }
    }
    
    /**
     * 分页查询
     * @param pageInfo
     * @param map
     * @param orderMap
     * @return pageIndo
     * @author huxiaoping
     */
    @Override
    public PageInfo<AppInfoDto> queryPage(PageInfo<AppInfoDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return getEntityDao().queryPage(pageInfo, map, orderMap);
    }

	@Override
	public AppInfo getByCode(String code) {
		List<AppInfo> list = getEntityDao().getByCode(code);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
     * 查询某用户对应数据权限表的App
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月29日上午9:47:42
     */
    /*@Override
    public PageInfo<AppInfoDto> queryPageByUser(PageInfo<AppInfoDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return getEntityDao().queryPageByUser(pageInfo, map, orderMap);
    }*/

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.manager.AppInfoManager#findByIds(java.util.List)
	 */
	@Override
	public List<AppInfo> findByIds(List<Long> ids) {
		
		return this.getEntityDao().findByIds(ids);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.manager.AppInfoManager#findByDomain(java.lang.String)
	 */
	@Override
	public AppInfo findByDomain(String domain) {
		/**
		 * update by huxiaoping on 2016-9-1 修改为从缓存中读取
		 * 
		 List<AppInfo> data  = this.getEntityDao().findByCmsDomain(domain);
		if(CollectionUtils.isEmpty(data)){
			data = this.getEntityDao().findByAppDomain(domain);
		}
		if(CollectionUtils.isEmpty(data)){
			return null;
		}
 		return data.get(0);
 		*/
	    if(StringUtils.isNotBlank(domain)){
	        AppInfo app = getDomainCache(CacheConstant.APP_INFO_CMS_DOMAIN,domain);
	        if(null==app){
	            app = getDomainCache(CacheConstant.APP_INFO_APP_DOMAIN,domain);
	        }
	        return app;
	    }
	    return null;
	    
	    
	}

	/**
     * 根据用户的用户类型，用户id查询其管理的客户端信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月2日
     */
    @Override
    public List<AppInfoDto> getBySysUser(Byte usertype, Long userId) {
        if(SysUser.USERTYPE1.equals(usertype)){
            // 超级管理员查询所有APP，页面作为查询条件
            List<AppInfo> appList = getAllCache();
            if(null!=appList&&appList.size()>0){
                AppInfoDto dto = null;
                List<AppInfoDto> retList = new ArrayList<AppInfoDto>();
                for (AppInfo appInfo : appList) {
                    dto = new AppInfoDto();
                    BeanUtils.copyProperties(appInfo, dto);
                    retList.add(dto);
                }
                return retList;
            }
        }else{
            PageInfo<AppInfoDto> appPageInfo = new PageInfo<AppInfoDto>(Integer.MAX_VALUE, 1);
            Map<String, Boolean> orderBys = new HashMap<String, Boolean>();
            orderBys.put("id", false);
            // 普通管理员根据自己的数据权限查询APP，页面用作查询条件
            Map<String, Object> queryMap = new TreeMap<String, Object>();
            queryMap.put("EQ_userId", userId);
            appPageInfo = this.getEntityDao().queryPageByUser(appPageInfo, queryMap, orderBys);
            return appPageInfo.getPageResults();
        }
        return null;
    }
    
    /**
     * 操作缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月1日下午2:24:41
     */
    private void operCache(AppInfo app){
        if(null!=app){
            //保存所有缓存
            List<AppInfo> appList = saveAllCache();
            //按照自定义/cms域名保存缓存
            saveDomainCache(appList,CacheConstant.APP_INFO_CMS_DOMAIN);
            saveDomainCache(appList,CacheConstant.APP_INFO_APP_DOMAIN);
        }
    }
    
    /**
     * 缓存所有的APP
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月1日上午11:44:53
     */
    public List<AppInfo> saveAllCache(){
        //获取所有app
        List<AppInfo> appList = getEntityDao().getAllIdDesc();
        //删除所有缓存
        abstractRedisClient.del(CacheConstant.APP_INFO_LIST);
        abstractRedisClient.set(CacheConstant.APP_INFO_LIST, appList);
        return appList;
    }
    
    /**
     * 获取所有APP缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月1日上午11:52:20
     */
    public List<AppInfo> getAllCache(){
        List<AppInfo> appList = abstractRedisClient.getList(CacheConstant.APP_INFO_LIST,AppInfo.class);
        if(null==appList||appList.size()<1){
            appList = getEntityDao().getAllIdDesc();
            abstractRedisClient.set(CacheConstant.APP_INFO_LIST, appList);
        }
        return appList ;
    }
    
    /**
     * 按照key设置domain缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月1日下午1:44:31
     */
    public void saveDomainCache(List<AppInfo> appList,String key){
        if(null!=appList&&appList.size()>0){
            abstractRedisClient.del(key);
            if(CacheConstant.APP_INFO_CMS_DOMAIN.equals(key)){
                for (AppInfo appInfo : appList) {
                    if(null!=appInfo && StringUtils.isNotBlank(appInfo.getCmsDomain())){
                        //缓存
                        abstractRedisClient.setHSet(key,appInfo.getCmsDomain().trim(), JSON.toJSONString(appInfo));
                    }
                }
            }else if(CacheConstant.APP_INFO_APP_DOMAIN.equals(key)){
                for (AppInfo appInfo : appList) {
                    if(null!=appInfo && StringUtils.isNotBlank(appInfo.getAppDomain())){
                        //缓存
                        abstractRedisClient.setHSet(key,appInfo.getAppDomain().trim(), JSON.toJSONString(appInfo));
                    }
                }
            }
        }
    }
    
    /**
     * 通过key和domain获取domain缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月1日下午2:05:19
     */
    public AppInfo getDomainCache(String key,String domain){
        if(StringUtils.isNotBlank(domain)){
            //缓存中域名存在就直接从缓存获取返回
            if(abstractRedisClient.existsKey(key)){
                return abstractRedisClient.getHSet(key, domain.trim(),AppInfo.class);
            }
            //数据库中获取并设置domain缓存
            List<AppInfo> appList = getEntityDao().getAllIdDesc();
            if(null!=appList&&appList.size()>0){
                saveDomainCache(appList,CacheConstant.APP_INFO_CMS_DOMAIN);
                saveDomainCache(appList,CacheConstant.APP_INFO_APP_DOMAIN);
                //返回值
                if(CacheConstant.APP_INFO_CMS_DOMAIN.equals(key)){
                    for (AppInfo appInfo : appList) {
                        if(domain.trim().equals(appInfo.getCmsDomain())){
                            return appInfo;
                        }
                    }
                }else if(CacheConstant.APP_INFO_APP_DOMAIN.equals(key)){
                    for (AppInfo appInfo : appList) {
                        if(domain.trim().equals(appInfo.getAppDomain())){
                            return appInfo;
                        }
                    }
                }                
            }
        }
        return null;
    }

    /**
     * 根据登录用户的用户类型，用户id分页查询客户端信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月20日
     */
    @Override
    public PageInfo<AppInfoDto> queryPageByUser(Byte usertype, Long userId, PageInfo<AppInfoDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap) {
        if(SysUser.USERTYPE1.equals(usertype)){
            return this.queryPage(pageInfo, map, orderMap);
        }else{
            PageInfo<AppInfoDto> appPageInfo = new PageInfo<AppInfoDto>(Integer.MAX_VALUE, 1);
            map.put("EQ_userId", userId);
            return this.getEntityDao().queryPageByUser(appPageInfo, map, orderMap);
        }
    }
}