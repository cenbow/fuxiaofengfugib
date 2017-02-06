package com.cqliving.cloud.online.config.manager;

import com.cqliving.framework.common.service.EntityService;

import java.util.List;

import com.cqliving.cloud.online.config.domain.Permission;
import com.cqliving.cloud.online.security.dto.SysResTypeDto;
import com.cqliving.cloud.online.security.dto.TypesDto;

/**
 * 前端资源权限，该表数据需要初始化好 Manager
 * Date: 2016-12-20 10:12:14
 * @author Code Generator
 */
public interface PermissionManager extends EntityService<Permission> {
    /**
     * 查询所有的资源
     * 
     * @Description 
     * @Company 
     * @parameter appId，客户端Id(查询该客户端对应资源的值)
     * @return
     * @author huxiaoping 2016年12月20日下午5:32:44
     */
    public List<SysResTypeDto> findAllPermission(Long appId);
    
    /**
     * 保存对App的授权
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月22日下午5:39:42
     */
    public void saveAppPermission(Long appId,TypesDto types,Long userId,String userName);
}
