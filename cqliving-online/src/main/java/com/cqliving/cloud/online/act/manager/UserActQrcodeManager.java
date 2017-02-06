package com.cqliving.cloud.online.act.manager;

import com.cqliving.cloud.online.act.domain.UserActQrcode;
import com.cqliving.framework.common.service.EntityService;

/**
 * 用户参与二维码扫描活动表 Manager
 * Date: 2016-12-16 15:16:13
 * @author Code Generator
 */
public interface UserActQrcodeManager extends EntityService<UserActQrcode> {
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
	//核销优惠券
	public UserActQrcode verify(String code, String token);
	//根据code查找活动信息,返回专属于用户的活动二维码优惠券链接
	public UserActQrcode findByCode(String actCode, String token);
}
