package com.cqliving.cloud.online.config.manager;

import com.cqliving.framework.common.service.EntityService;

import java.util.List;

import com.cqliving.cloud.online.config.domain.AppPermission;
import com.cqliving.cloud.online.config.dto.AppPermissionDto;

/**
 * 客户端资源权限表 Manager
 * Date: 2016-12-14 16:50:46
 * @author Code Generator
 */
public interface AppPermissionManager extends EntityService<AppPermission> {
	
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);

	 /**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年12月15日
	 * @return
	 */
	List<AppPermissionDto> getDtoOfAll();
	 
}