package com.cqliving.cloud.online.security.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.dto.SysUserDataDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 系统用户数据权限表，目前数据权限的值为app_id对应的值 Service
 * Date: 2016-05-03 15:25:12
 * @author Code Generator
 */
public interface SysUserDataService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<SysUserData>> queryForPage(PageInfo<SysUserData> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<SysUserData> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(SysUserData domain);
	/** @author Code Generator *****end*****/
	public Response<List<SysUserData>> findByUserId(Long userId,byte type);
	
	public Response<Long[]> findValueByUserId(Long userId,byte type);
	//根据类型保存
	public Response<List<SysUserData>> saveSysUserDataByType(Long userId,Long[] value,byte type);
	
	public Response<PageInfo<SysUserDataDto>> findPage(PageInfo<SysUserDataDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	//删除数据权限
	public Response<Void> deleteByUserIdAndType(Long userId,byte type);
}
