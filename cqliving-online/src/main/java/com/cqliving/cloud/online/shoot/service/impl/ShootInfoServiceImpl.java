package com.cqliving.cloud.online.shoot.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ShootInfoData;
import com.cqliving.cloud.online.shoot.domain.ShootInfo;
import com.cqliving.cloud.online.shoot.dto.ShootInfoDto;
import com.cqliving.cloud.online.shoot.manager.ShootInfoManager;
import com.cqliving.cloud.online.shoot.service.ShootInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("shootInfoService")
@ServiceHandleMapping(managerClass = ShootInfoManager.class)
public class ShootInfoServiceImpl implements ShootInfoService {

	private static final Logger logger = LoggerFactory.getLogger(ShootInfoServiceImpl.class);
	
	@Autowired
	private ShootInfoManager shootInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询随手拍表失败")})
	public Response<PageInfo<ShootInfo>> queryForPage(PageInfo<ShootInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询随手拍表失败")})
	public Response<ShootInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除随手拍表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除随手拍表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		logger.info("删除操作");
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存随手拍表失败")})
	public Response<Void> save(ShootInfo shootInfo) {
		return null;
	}

	@Override
	public Response<CommonListResult<ShootInfoData>> queryForScrollPage(Long appId, String sessionId, String token, Byte type, Long shootInfoId, Long lastId) {
		Response<CommonListResult<ShootInfoData>> response = Response.newInstance();
		try {
			CommonListResult<ShootInfoData> data = shootInfoManager.queryForScrollPage(appId, sessionId, token, type, shootInfoId, lastId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取随手拍首页/我的随手拍失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取随手拍首页/我的随手拍失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取随手拍首页/我的随手拍失败");
		}
		return response;
	}

	@Override
	public Response<Boolean> add(Long appId, String sessionId, String token, String place, String lat, String lng, String content, Byte shootType, String imgs, String imgDescs) {
		Response<Boolean> response = Response.newInstance();
		try {
			Byte status = shootInfoManager.add(appId, sessionId, token, place, lat, lng, content, shootType, imgs, imgDescs);
			response.setData(true);
			response.setMessage(ShootInfo.STATUS2.equals(status) ? "发布成功，请等待审核通过" : "发布成功");
		} catch (BusinessException e) {
			logger.error("发布随手拍失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
			response.setData(false);
		} catch (Exception e) {
			logger.error("发布随手拍失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("发布随手拍失败");
			response.setData(false);
		}
		return response;
	}

	@Override
	public Response<Boolean> remove(Long appId, String sessionId, String token, Long id) {
		Response<Boolean> response = Response.newInstance();
		try {
			boolean data = shootInfoManager.remove(appId, sessionId, token, id);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("删除随手拍失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
			response.setData(false);
		} catch (Exception e) {
			logger.error("删除随手拍失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("删除随手拍失败");
			response.setData(false);
		}
		return response;
	}

	@Override
	public Response<PageInfo<ShootInfoDto>> queryDtoForPage(PageInfo<ShootInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		Response<PageInfo<ShootInfoDto>> response = Response.newInstance();
		try {
			PageInfo<ShootInfoDto> data = shootInfoManager.queryDtoForPage(pageInfo, searchMap, sortMap);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("查询随手拍列表失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询随手拍列表失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("查询随手拍列表失败");
		}
		return response;
	}

	@Override
	public Response<Void> updateStatus(Byte status, Long id, Long userId, String nickname) {
		Response<Void> response = Response.newInstance();
		try {
			shootInfoManager.updateStatus(status, id, userId, nickname);
		} catch (BusinessException e) {
			logger.error("修改随手拍状态失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改随手拍状态失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改随手拍状态失败");
		}
		return response;
	}
	
}
