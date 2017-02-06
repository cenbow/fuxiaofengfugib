package com.cqliving.cloud.security.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.security.dao.SysUserDao;
import com.cqliving.cloud.online.security.domain.SysResource;
import com.cqliving.cloud.online.security.domain.SysRole;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.manager.SysUserDataManager;
import com.cqliving.cloud.security.cache.SecurityCache;
import com.cqliving.cloud.security.service.SysUserService;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.manager.security.api.SubjectInfo;
import com.cqliving.manager.security.api.SubjectService;
import com.google.common.collect.Lists;

@Service("sysUserService")
public class SysUserServiceImpl extends EntityServiceImpl<SysUser, SysUserDao> implements SysUserService, SubjectService {

    private Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SecurityCache securityCache;
    @Autowired
    private SysUserDataManager sysUserDataManager;

    @Override
    public void update(SysUser o) throws BusinessException {
        super.update(o);
        try{
            securityCache.clearAuthorization(o.getUsername());
        }catch (Exception ex){
            logger.error("", ex);
        }
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public int updateStatusById(Long id, Integer status){
        return super.getEntityDao().updateStatusById(id, status);
    }

	@Override
	public SysUser findByUsername(String username) {
		return super.getEntityDao().findByUsername(username);
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int updateUserpassById(Long id, String userpass, String salt) {
		return super.getEntityDao().updateUserpassById(id, userpass, salt);
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int updateUserLoginById(Long id, Date lastLoginDate,
			String lastLoginIp) {
		return super.getEntityDao().updateUserLoginById(id, lastLoginDate, lastLoginIp);
	}

	@Override
	public boolean checkUsername(String username) {
		if(super.getEntityDao().findByUsername(username) != null){
			return true;
		}else{
			return false;
		}
	}

    @Override
    public SubjectInfo getSubject(String userName) {
        SubjectInfo subjectInfo = new SubjectInfo();
        SysUser sysUser = this.findByUsername(userName);
        
        if(null == sysUser)return null;
        
        subjectInfo.setPassword(sysUser.getPassword());
        subjectInfo.setPwdSalt(sysUser.getSalt());
        subjectInfo.setExpiredDate(sysUser.getExpiredDate());
        subjectInfo.setStatus(Integer.valueOf(sysUser.getStatus()));
        subjectInfo.setUnlockDate(sysUser.getUnlockDate());
        subjectInfo.setAppId(sysUser.getAppId());
        subjectInfo.setImgUrl(sysUser.getImgUrl());
        return subjectInfo;
    }

    @Override
    public List<String> listRole(String userName) {
        SysUser sysUser = this.findByUsername(userName);
        
        List<String> roleNames = Lists.newArrayList();
        
        if(sysUser!=null && !CollectionUtils.isEmpty(sysUser.getRole())){
        	
        	for(SysRole role : sysUser.getRole()){
        		roleNames.add(role.getRoleName());
        	}
            return roleNames;
        }
        return null;
    }

    @Override
    public List<String> listPermission(String userName) {
        SysUser sysUser = this.findByUsername(userName);
        
        Set<SysRole> roles = sysUser.getRole();
        if(sysUser!=null && !CollectionUtils.isEmpty(roles)) {
            List<String> resList = new ArrayList<String>();
            for(SysRole role : roles){
            	if(!CollectionUtils.isEmpty(role.getRescs())){
            		for(SysResource resource : role.getRescs()){
                        resList.add(resource.getPermissionValue());
                    }
            	}
            }
            return resList;
        }
        return null;
    }

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.security.service.SysUserService#createSysUser(com.cqliving.cloud.online.security.domain.SysUser)
	 */
	@Override
	public SysUser createSysUser(SysUser sysUser) {
		
		if(null != sysUser.getId()){
			sysUser = this.getEntityDao().saveAndFlush(sysUser);
			return sysUser;
		}
		sysUser = this.getEntityDao().saveAndFlush(sysUser);
		SysUserData sysUserData = new SysUserData();
		sysUserData.setUserId(sysUser.getId());
		sysUserData.setValue(sysUser.getAppId());
		sysUserData.setType(SysUserData.TYPE1);
		sysUserDataManager.save(sysUserData);
		
		return sysUser;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.security.service.SysUserService#findByAppId(java.lang.Long)
	 */
	@Override
	public List<SysUser> findByAppId(Long appId) {
		
		if(null == appId)return null;
		List<Long> appIds  = Lists.newArrayList();
		appIds.add(appId);
		return this.getEntityDao().getByAppIds(appIds);
	}
}
