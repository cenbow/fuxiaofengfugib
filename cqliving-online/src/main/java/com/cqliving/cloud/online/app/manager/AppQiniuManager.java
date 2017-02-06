package com.cqliving.cloud.online.app.manager;

import java.util.List;

import com.cqliving.cloud.online.app.domain.AppQiniu;
import com.cqliving.framework.common.service.EntityService;

/**
 * APP七牛云服务配置 Manager
 * Date: 2016-05-24 17:03:56
 * @author Code Generator
 */
public interface AppQiniuManager extends EntityService<AppQiniu> {
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
	
	public List<AppQiniu> findByAppId(Long appId);
	
	public List<AppQiniu> findByDefault(Byte isDefault);
}
