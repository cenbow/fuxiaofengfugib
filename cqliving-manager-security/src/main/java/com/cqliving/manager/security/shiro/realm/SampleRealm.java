package com.cqliving.manager.security.shiro.realm;

import java.lang.reflect.Field;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.cqliving.framework.utils.Dates;
import com.cqliving.manager.security.api.SubjectInfo;
import com.cqliving.manager.security.api.SubjectService;
import com.cqliving.manager.security.exception.AccountExpiredException;
import com.cqliving.manager.security.exception.AccountForbiddenException;
import com.cqliving.manager.security.exception.DomainAccountException;

/**
 * Created by Administrator on 2015/5/20.
 */
public class SampleRealm extends AuthorizingRealm {


    private Logger logger = LoggerFactory.getLogger(SampleRealm.class);

    private SubjectService subjectService;
    
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清空指定用户的授权缓存
     * @param userName
     */
    public void clearCachedAuthorizationInfo(String userName){
        //TODO 单独删除有问题，不知道原因，统一全部清除
        /*Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if(cache!=null) {
            cache.remove(userName);
        }*/
        clearAllCachedAuthorizationInfo();
    }

    /**
     * 清空所有用户的授权缓存
     */
    public void clearAllCachedAuthorizationInfo(){
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if(cache!=null) {
            cache.clear();
        }
    }

    /**
     * 登陆认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SubjectInfo subject = subjectService.getSubject(token.getUsername());
        
        if(subject==null){
            throw new AuthenticationException("username \""+token.getUsername()+"\" is not found user");
        }
        
        Long appId = subject.getAppId();
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        Object appInfo = session.getAttribute("session_app_info");
        
        Field field =  ReflectionUtils.findField(appInfo.getClass(), "id");
        field.setAccessible(true);
		try {
			Object _appId = field.get(appInfo);
			if((appId !=null  && null !=_appId && appId.longValue() != (long)_appId) ||
					(null != appId && null == _appId) || (null == appId && null != _appId)){
	        	throw new DomainAccountException("该用户不在此系统中");
	        }
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new DomainAccountException("该用户不在此系统中");
		}
        if(null !=subject.getStatus() && subject.getStatus() == 0){
       	    throw new AccountForbiddenException("账户已被禁用");
        }
        if(null !=subject.getStatus() && subject.getStatus() == 2){
       	    throw new AccountForbiddenException("账户已被锁定");
        }
        Date now = Dates.now();
        if(subject.getExpiredDate() != null && subject.getUnlockDate() != null && 
        		subject.getExpiredDate().before(now) && subject.getUnlockDate().after(now)){
        	throw new AccountExpiredException("账户已过期");
        }
        SimpleAuthenticationInfo saInfo = new SimpleAuthenticationInfo(token.getPrincipal(),
                subject.getPassword(),
                subject.getPwdSalt() != null ? ByteSource.Util.bytes(subject.getPwdSalt()) : null,
                getName());
        return saInfo;
    }

    /**
     * 用户授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //授予角色
        info.addRoles(subjectService.listRole(userName));
        //授予权限
        info.addStringPermissions(subjectService.listPermission(userName));

        logger.debug("User Name:{}, authorization info Roles:{}, Permissions:{}",
                userName,
                info.getRoles().toString(),
                info.getStringPermissions().toString());
        return info;
    }



    public SubjectService getSubjectService() {
        return subjectService;
    }

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
}
