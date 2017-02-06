package com.cqliving.cloud.online.config.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cqliving.cloud.common.CacheConstant;
import com.cqliving.cloud.online.config.dao.AppPermissionDao;
import com.cqliving.cloud.online.config.dao.PermissionDao;
import com.cqliving.cloud.online.config.domain.AppPermission;
import com.cqliving.cloud.online.config.domain.Permission;
import com.cqliving.cloud.online.config.dto.AppPermissionDto;
import com.cqliving.cloud.online.config.dto.PermissionDto;
import com.cqliving.cloud.online.config.manager.PermissionManager;
import com.cqliving.cloud.online.security.dao.SysResTypeDao;
import com.cqliving.cloud.online.security.dto.SysResTypeDto;
import com.cqliving.cloud.online.security.dto.TypesDto;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.redis.base.AbstractRedisClient;

@Service("permissionManager")
public class PermissionManagerImpl extends EntityServiceImpl<Permission, PermissionDao> implements PermissionManager {

    private static final Logger logger = LoggerFactory.getLogger(PermissionManagerImpl.class);
    @Autowired 
    private AbstractRedisClient abstractRedisClient;
    @Autowired
    private SysResTypeDao sysResTypeDao;
    @Autowired
    private AppPermissionDao appPermissionDao;
    
    /**
     * 查询所有的资源
     * @Description 
     * @Company 
     * @parameter appId，客户端Id(查询该客户端对应资源的值)
     * @return
     * @author huxiaoping 2016年12月20日下午5:32:44
     */
    @Override
    public List<SysResTypeDto> findAllPermission(Long appId) {
        List<SysResTypeDto> typeLidt = sysResTypeDao.findAppResType();
        if(null!=typeLidt && typeLidt.size()>0){
            List<PermissionDto> permissionList = null;
            for (SysResTypeDto type : typeLidt) {
                if(null!=type && null!=type.getId()){
                    permissionList = this.getEntityDao().findByAppAndType(appId, type.getId());
                    type.setPermissions(permissionList);
                }
            }
        }
        return typeLidt;
    }

    /**
     * 保存对App的授权
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月22日下午5:39:42
     */
    @Override
    @Transactional(value="transactionManager")
    public void saveAppPermission(Long appId, TypesDto types,Long userId,String userName) {
        if(null==appId || types==null || null==types.getTypes()||types.getTypes().size()<1){
            throw new BusinessException(-1,"数据异常，请刷新重试！");
        }
        //step1:删除App对应的授权
        appPermissionDao.delByAppId(appId);
        //step2：添加授权
        AppPermission appPermission = null;
        List<AppPermission> appPermissionList = new ArrayList<AppPermission>();
        Date now = new Date();
        for (SysResTypeDto sysResType : types.getTypes()) {
            if(null!=sysResType&&null!=sysResType.getPermissions()&&sysResType.getPermissions().size()>0){
                for (PermissionDto permission : sysResType.getPermissions()) {
                    if(null==permission.getId()){
                        throw new BusinessException(-1,"数据异常，请刷新重试！");
                    }
                    if(null!=permission.getIsPermission()||null!=permission.getIsLogin()||null!=permission.getIsSign()||null!=permission.getIsSessionId()||null!=permission.getIsRequestTimes()||null!=permission.getRequestTimesInterval()||null!=permission.getRequestTimesLimit()){
                        appPermission = new AppPermission();
                        appPermission.setAppId(appId);
                        appPermission.setConfigPermissionId(permission.getId());
                        appPermission.setIsPermission(null==permission.getIsPermission()?AppPermission.ISPERMISSION0:permission.getIsPermission());
                        appPermission.setIsLogin(null==permission.getIsLogin()?AppPermission.ISLOGIN0:permission.getIsLogin());
                        appPermission.setIsSign(null==permission.getIsSign()?AppPermission.ISSIGN0:permission.getIsSign());
                        appPermission.setIsSessionId(null==permission.getIsSessionId()?AppPermission.ISSESSIONID0:permission.getIsSessionId());
                        appPermission.setIsRequestTimes(null==permission.getIsRequestTimes()?AppPermission.ISREQUESTTIMES0:permission.getIsRequestTimes());
                        appPermission.setRequestTimesInterval(permission.getRequestTimesInterval());
                        appPermission.setRequestTimesLimit(permission.getRequestTimesLimit());
                        appPermission.setCreateTime(now);
                        appPermission.setCreator(userName);
                        appPermission.setCreatorId(userId);
                        appPermission.setUpdateTime(now);
                        appPermission.setUpdator(userName);
                        appPermission.setUpdatorId(userId);
                        appPermission.setStatus(AppPermission.STATUS3);
                        appPermissionList.add(appPermission);
                    }
                }
            }
        }
        if(appPermissionList.size()>0){
            appPermissionDao.save(appPermissionList);
        }
        //缓存
        saveCache(appId);
    }
    
    /**
     * 保存缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月23日上午10:28:26
     */
    private void saveCache(Long appId){
        //查询
        List<AppPermissionDto> appPermissionList = appPermissionDao.getByAppId(appId);
        //写入缓存
        if(null!=appPermissionList&&appPermissionList.size()>0){
            //删除缓存
            abstractRedisClient.del(CacheConstant.REQUEST_PERMISSION_APPID_HASH_PREFIX + appId);
            //写入缓存
            logger.debug("============ 刷新多条客户端资源权限 begin ============");
            for (AppPermissionDto dto : appPermissionList) {
                abstractRedisClient.setHSet(CacheConstant.REQUEST_PERMISSION_APPID_HASH_PREFIX + appId, dto.getUrl(), JSON.toJSONString(dto));
            }
            logger.debug("============ 刷新多条客户端资源权限 finished ============");
        }
    }
}
