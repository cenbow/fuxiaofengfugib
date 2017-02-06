package com.cqliving.cloud.online.shopping.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.interfacc.dto.BusinessDetailResult;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.FeedbackData;
import com.cqliving.cloud.online.interfacc.dto.ReginDto;
import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.cloud.online.shopping.manager.ShoppingUserAddressManager;
import com.cqliving.cloud.online.shopping.service.ShoppingUserAddressService;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.framework.common.exception.BusinessException;

@Service("shoppingUserAddressService")
@ServiceHandleMapping(managerClass = ShoppingUserAddressManager.class)
public class ShoppingUserAddressServiceImpl implements ShoppingUserAddressService {

	private static final Logger logger = LoggerFactory.getLogger(ShoppingUserAddressServiceImpl.class);
	
	@Autowired
	private ShoppingUserAddressManager shoppingUserAddressManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户收货地址表失败")})
	public Response<PageInfo<ShoppingUserAddress>> queryForPage(PageInfo<ShoppingUserAddress> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户收货地址表失败")})
	public Response<ShoppingUserAddress> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户收货地址表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户收货地址表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户收货地址表失败")})
	public Response<Void> save(ShoppingUserAddress shoppingUserAddress) {
		return null;
	}
	/**
	 * 添加收货地址
	 */
	@Override
	public Response<Void> addShoppingUserAddress(Long appId,String token,String sessionId,ShoppingUserAddress shoppingUserAddress){
		Response<Void> response = Response.newInstance();
		try {
			Byte status =shoppingUserAddressManager.addShoppingUserAddress(appId,token,sessionId,shoppingUserAddress);
			response.setMessage(ShoppingUserAddress.STATUS3.equals(status) ? "保存地址成功" : "保存失败");
			response.setCode(ShoppingUserAddress.STATUS3.equals(status) ? 0 : 1);
		} catch (BusinessException e) {
			logger.error("保存地址失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("保存地址失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("保存地址失败");
		}
		return response;
	}
	/**
	 * 查询收货地址列表
	 */
	@Override
	public Response<CommonListResult<ShoppingUserAddress>> queryAdressPage(Long appId,String sessionId,String token,Long lastId){
		Response<CommonListResult<ShoppingUserAddress>> response = Response.newInstance();
		try {
			CommonListResult<ShoppingUserAddress> data = shoppingUserAddressManager.queryAdressPage(appId, sessionId, token,lastId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取收货地址分页列表失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取收货地址分页列表失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取收货地址分页列表失败");
		}
		return response;
	}
	/**
	 * 修改收货地址
	 */
	@Override
	public Response<Void> updateShoppingUserAddress(Long appId, String token, String sessionId,ShoppingUserAddress shoppingUserAddress) {
		Response<Void> response = Response.newInstance();
		try {
			shoppingUserAddressManager.updateShoppingUserAddress(appId,token,sessionId,shoppingUserAddress);
			response.setMessage("修改成功");
		} catch (BusinessException e) {
			logger.error("修改收货地址失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改收货地址失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改收货地址失败");
		}
		return response;
	}
	/**
	 * 查询收货地址详情
	 */
	@Override
	public Response<ShoppingUserAddress> queryAdressOne(Long appId, String sessionId, String token) {
		Response<ShoppingUserAddress> response = Response.newInstance();
		try {
			ShoppingUserAddress data = shoppingUserAddressManager.queryAdressOne(appId, sessionId, token);
			if(data!=null){
				response.setData(data);
			}
			
		} catch (BusinessException e) {
			logger.error("获取收货地址详情信息失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取收货地址详情信息失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取收货地址详情信息失败");
		}
		return response;
	}
	/**
	 * 删除收货地址
	 */
	@Override
	public Response<Void> addressRemove(Long appId, String sessionId, String token, List<Long> idList) {
		Response<Void> response = Response.newInstance();
		try {
			shoppingUserAddressManager.remove(appId, sessionId, token, idList);
			response.setMessage("删除成功");
		} catch (BusinessException e) {
			logger.error("删除收货地址失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("删除收货地址失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("删除收货地址失败");
		}
		return response;
	}

	@Override
	public Response<CommonListResult<ReginDto>> reginList() {
		Response<CommonListResult<ReginDto>> response = Response.newInstance();
		try {
			CommonListResult<ReginDto> data = shoppingUserAddressManager.queryReginList();
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取区域失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取区域失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取区域失败");
		}
		return response;
	}

	@Override
	public Response<Void> addressUf(Long appId, String sessionId, String token, Long shoppingUserAddressId) {
		Response<Void> response = Response.newInstance();
		try {
			shoppingUserAddressManager.addressUf(appId, sessionId, token, shoppingUserAddressId);
			response.setMessage("修改成功");
		} catch (BusinessException e) {
			logger.error("修改默认收货地址失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改默认收货地址失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改默认收货地址失败");
		}
		return response;
		
	}
}
