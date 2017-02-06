package com.cqliving.cloud.online.manuscript.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.manuscript.domain.ManuscriptColumns;

/**
 * 抓稿栏目配置表 Manager
 * Date: 2016-11-08 16:06:24
 * @author Code Generator
 */
public interface ManuscriptColumnsManager extends EntityService<ManuscriptColumns> {
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
