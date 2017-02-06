package com.cqliving.cloud.online.act.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.act.domain.ActQrcode;
import com.cqliving.tool.common.Response;

/**
 * 二维码扫描活动 Service
 * Date: 2016-12-16 15:15:52
 * @author Code Generator
 */
public interface ActQrcodeService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActQrcode>> queryForPage(PageInfo<ActQrcode> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActQrcode> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ActQrcode domain);
	/** @author Code Generator *****end*****/
	
}
