package com.cqliving.cloud.online.security.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.dto.SysUserDataDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 系统用户数据权限表，目前数据权限的值为app_id对应的值 Manager
 * Date: 2016-05-03 15:25:12
 * @author Code Generator
 */
public interface SysUserDataManager extends EntityService<SysUserData> {

	public List<SysUserData> findByUserId(Long userId,byte type);
	//查找用户所有的权限
	public Long[] findValueByUserId(Long userId,byte type);
	
	//根据类型保存用户权限
	public List<SysUserData> saveSysUserDataByType(Long userId, Long[] value, byte type);
	
	public PageInfo<SysUserDataDto> findPage(PageInfo<SysUserDataDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	//删除用户权限
	public void deleteByUserIdAndType(Long userId, byte type);
}
