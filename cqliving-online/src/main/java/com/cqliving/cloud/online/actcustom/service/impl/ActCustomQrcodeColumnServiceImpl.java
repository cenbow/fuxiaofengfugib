package com.cqliving.cloud.online.actcustom.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.actcustom.dto.ActCustomQrcodeColumnDto;
import com.cqliving.cloud.online.actcustom.manager.ActCustomQrcodeColumnManager;
import com.cqliving.cloud.online.actcustom.service.ActCustomQrcodeColumnService;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;

@Service("actCustomQrcodeColumnService")
@ServiceHandleMapping(managerClass = ActCustomQrcodeColumnManager.class)
public class ActCustomQrcodeColumnServiceImpl implements ActCustomQrcodeColumnService {

	private static final Logger logger = LoggerFactory.getLogger(ActCustomQrcodeColumnServiceImpl.class);
	
	@Autowired
	private ActCustomQrcodeColumnManager actCustomQrcodeColumnManager;

	@Override
	public Response<List<ActCustomQrcodeColumnDto>> getColumnsByQrcode(String qrcode) {
		Response<List<ActCustomQrcodeColumnDto>> rs = Response.newInstance();
		try {
			rs.setData(actCustomQrcodeColumnManager.getColumnsByQrcode(qrcode));
		}catch(BusinessException e){
			logger.error("获取活动报名所需的列失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取活动报名所需的列失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("获取活动报名所需的列失败");
		}
		return rs;
	}

	@Override
	public Response<Void> signSave(String sessionId, String token, String qrcode, String columnIds, String values) {
		Response<Void> rs = Response.newInstance();
		try {
			actCustomQrcodeColumnManager.signSave(sessionId, token, qrcode, columnIds, values);
		}catch(BusinessException e){
			logger.error("活动报名失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("活动报名失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("活动报名失败");
		}
		return rs;
	}

	@Override
	public Response<Map<String, Object>> signList(String sessionId, String token, String qrcode) {
		Response<Map<String, Object>> rs = Response.newInstance();
		try {
			rs.setData(actCustomQrcodeColumnManager.signList(sessionId, token, qrcode));
		}catch(BusinessException e){
			logger.error("参赛者列表获取失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("参赛者列表获取失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("参赛者列表获取失败");
		}
		return rs;
	}

	@Override
	public Response<Map<String, String>> isSign(String sessionId, String token, String qrcode) {
		Response<Map<String, String>> rs = Response.newInstance();
		try {
			Map<String, String> map = actCustomQrcodeColumnManager.isSign(sessionId, token, qrcode);
			if(map != null && map.containsKey("phone")){
				rs.setCode(1);
				rs.setMessage("已报名");
			}
			rs.setData(map);
		}catch(BusinessException e){
			logger.error("验证活动是否报名失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("验证活动是否报名失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("验证活动是否报名失败");
		}
		return rs;
	}
}
