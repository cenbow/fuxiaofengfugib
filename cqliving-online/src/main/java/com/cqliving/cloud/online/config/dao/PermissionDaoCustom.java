package com.cqliving.cloud.online.config.dao;

import java.util.List;

import com.cqliving.cloud.online.config.dto.PermissionDto;

public interface PermissionDaoCustom {
    
    /**
     * 查询每个类型下的资源
     * -- 查询资源和资源对应的值
    -- app,客户端资源权限表id,是否有该方法的请求权限,是否必须登陆,是对接口请求进行签名验证,
    SELECT a.*,b.app_id,b.id app_permission_id,b.is_permission ,b.is_login,b.is_sign,
    -- 是否验证sessionId为空,是否控制接口请求次数,请求次数间隔时间（单位分钟）,请求限制次数
    b.is_session_id,b.is_request_times,b.request_times_interval,b.request_times_limit
    FROM permission a LEFT JOIN app_permission b ON a.id=b.config_permission_id AND b.app_id=10 WHERE  a.sys_res_type_id=10 
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月20日下午5:14:46
     */
    public List<PermissionDto> findByAppAndType(Long appId,Long sysResTypeId);

}
