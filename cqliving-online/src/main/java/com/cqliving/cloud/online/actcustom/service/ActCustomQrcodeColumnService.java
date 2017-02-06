package com.cqliving.cloud.online.actcustom.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.actcustom.dto.ActCustomQrcodeColumnDto;
import com.cqliving.tool.common.Response;

/**
 * 报名活动自定义收集列 Service
 * Date: 2016-12-21 09:29:51
 * @author Code Generator
 */
public interface ActCustomQrcodeColumnService {
	
	/**
	 * Title:获取活动报名所需的字段
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月21日
	 * @param qrcode
	 * @return
	 */
	public Response<List<ActCustomQrcodeColumnDto>> getColumnsByQrcode(String qrcode);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月21日
	 * @param sessionId
	 * @param token
	 * @param qrcode
	 * @param columnIds
	 * @param values
	 * @return
	 */
	public Response<Void> signSave(String sessionId, String token, String qrcode, String columnIds, String values);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月21日
	 * @param sessionId
	 * @param token
	 * @param qrcode
	 * @return
	 */
	public Response<Map<String, Object>> signList(String sessionId, String token, String qrcode);

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月22日
	 * @param sessionId
	 * @param token
	 * @param qrcode
	 * @return
	 */
	public Response<Map<String, String>> isSign(String sessionId, String token, String qrcode);
}
