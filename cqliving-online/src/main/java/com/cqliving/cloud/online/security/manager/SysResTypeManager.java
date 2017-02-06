package com.cqliving.cloud.online.security.manager;

import java.util.List;

import com.cqliving.cloud.online.security.domain.SysResType;
import com.cqliving.framework.common.service.EntityService;

/**
 * 系统资源分类表 Manager
 * Date: 2016-06-29 17:07:53
 * @author Code Generator
 */
public interface SysResTypeManager extends EntityService<SysResType> {
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
	
	public List<SysResType> findExistsRes();
	
	public List<SysResType> findExistsResByUserId(Long userId);
}
