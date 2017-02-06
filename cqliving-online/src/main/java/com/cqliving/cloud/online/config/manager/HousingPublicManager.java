package com.cqliving.cloud.online.config.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.config.domain.HousingPublic;

/**
 * 公租房表 Manager
 * Date: 2016-11-07 16:34:55
 * @author Code Generator
 */
public interface HousingPublicManager extends EntityService<HousingPublic> {
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
}
