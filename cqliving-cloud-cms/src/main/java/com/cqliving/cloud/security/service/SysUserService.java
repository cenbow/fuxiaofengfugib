package com.cqliving.cloud.security.service;

import java.util.Date;
import java.util.List;

import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.framework.common.service.EntityService;

/**
 * 系统用户表 Service
 *
 * Date: 2015-05-11 16:11:20
 *
 * @author Code Generator
 *
 */
public interface SysUserService extends EntityService<SysUser> {

	//通过登录用户名查找用户信息
	public SysUser findByUsername(String username);
	
	//更新用户状态
    public int updateStatusById(Long id, Integer status);

    //更新用户密码
    public int updateUserpassById(Long id, String userpass, String salt);
    
    //更新用户登录信息
    public int updateUserLoginById(Long id, Date lastLoginDate, String lastLoginIp);
    
    /**
     * 检查名称是否存在
     * @param username
     * @return false 不存在，true 存在
     */
    public boolean checkUsername(String username);
    //添加新的系统用户
    public SysUser createSysUser(SysUser sysUser);
    //查找系统用户
    public List<SysUser> findByAppId(Long appId);
}
