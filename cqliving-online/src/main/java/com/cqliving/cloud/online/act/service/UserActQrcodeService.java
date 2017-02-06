package com.cqliving.cloud.online.act.service;

import java.util.Map;

import com.cqliving.cloud.online.act.domain.UserActQrcode;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户参与二维码扫描活动表 Service
 * Date: 2016-12-16 15:16:13
 * @author Code Generator
 */
public interface UserActQrcodeService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserActQrcode>> queryForPage(PageInfo<UserActQrcode> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserActQrcode> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(UserActQrcode domain);
	/** @author Code Generator *****end*****/
	
	public Response<UserActQrcode> verify(String code,String token);
	//根据code查找活动信息,返回专属于用户的活动二维码优惠券链接
	public Response<UserActQrcode> findByCode(String actCode,String token);
}
