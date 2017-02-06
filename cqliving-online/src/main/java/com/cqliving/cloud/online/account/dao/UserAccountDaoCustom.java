package com.cqliving.cloud.online.account.dao;

import java.util.Map;

import com.cqliving.cloud.online.account.dto.UserDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * 用户账号表 JPA Dao
 * Date: 2016-04-15 09:46:01
 * @author Code Generator
 */
public interface UserAccountDaoCustom {
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月9日下午5:13:06
     */
    public PageInfo<UserDto> queryPage(PageInfo<UserDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap);
    
    /**
     * 查询单个记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月9日下午5:13:06
     */
    public UserDto getById(Long id,Long AppId);
}
