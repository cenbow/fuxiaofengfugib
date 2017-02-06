package com.cqliving.cloud.online.act.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.act.domain.ActQrcode;

/**
 * 二维码扫描活动 Manager
 * Date: 2016-12-16 15:15:52
 * @author Code Generator
 */
public interface ActQrcodeManager extends EntityService<ActQrcode> {
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
	
	//判断活动是否过期
	public boolean actUsable(ActQrcode actQrcode);
	
	public ActQrcode findByCode(String code);
}
