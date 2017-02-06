package com.cqliving.manager.security.api;

import java.util.List;

/**
 * Created by Administrator on 2015/5/20.
 *
 * 用户接口
 */
public interface SubjectService {

    /**
     * 返回用户密码和加密盐值信息，如没有盐值，将盐值赋空即可
     * @param userName 用户名
     * @return SubjectInfo 用户主体对象
     */
    SubjectInfo getSubject(String userName);

    /**
     * 返回用户的角色信息
     * @param userName 用户名
     * @return List<String> 角色列表
     */
    List<String> listRole(String userName);

    /**
     * 返回用户的权限信息
     * @param userName 用户名
     * @return List<String> 权限列表
     */
    List<String> listPermission(String userName);
}
