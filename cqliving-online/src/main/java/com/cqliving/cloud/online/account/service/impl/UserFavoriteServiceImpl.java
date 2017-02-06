package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserFavorite;
import com.cqliving.cloud.online.account.manager.UserFavoriteManager;
import com.cqliving.cloud.online.account.service.UserFavoriteService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.FavoritesData;
import com.cqliving.cloud.online.interfacc.dto.FavoritesShoppingData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userFavoriteService")
@ServiceHandleMapping(managerClass = UserFavoriteManager.class)
public class UserFavoriteServiceImpl implements UserFavoriteService {

	private static final Logger logger = LoggerFactory.getLogger(UserFavoriteServiceImpl.class);
	
	@Autowired
	private UserFavoriteManager userFavoriteManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户收藏表失败")})
	public Response<PageInfo<UserFavorite>> queryForPage(PageInfo<UserFavorite> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户收藏表失败")})
	public Response<UserFavorite> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户收藏表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户收藏表失败")})
	public Response<Void> save(UserFavorite userFavorite) {
		return null;
	}

	@Override
	public Response<CommonListResult<FavoritesData>> getMyFavoritesPage(Long appId, String sessionId, String token, Long lastId) {
		Response<CommonListResult<FavoritesData>> response = Response.newInstance();
		try {
			CommonListResult<FavoritesData> data = userFavoriteManager.getMyFavoritesPage(appId, sessionId, token, lastId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取我的资讯/段子收藏失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取我的资讯/段子收藏失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取我的资讯/段子收藏失败");
		}
		return response;
	}

	@Override
	public Response<CommonListResult<FavoritesShoppingData>> getMyFavoritesShoppingPage(Long appId, String sessionId, String token, Long lastFavoriteId) {
		Response<CommonListResult<FavoritesShoppingData>> response = Response.newInstance();
		try {
			CommonListResult<FavoritesShoppingData> data = userFavoriteManager.getMyFavoritesShoppingPage(appId, sessionId, token, lastFavoriteId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取我的商城商品收藏失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取我的商城商品收藏失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取我的商城商品收藏失败");
		}
		return response;
	}

	@Override
	public Response<Boolean> isCollected(Long appId, String sessionId, String token, Long sourceId, Byte sourceType) {
		Response<Boolean> response = Response.newInstance();
		try {
			boolean data = userFavoriteManager.isCollected(appId, sessionId, token, sourceId, sourceType);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取是否已收藏失败", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
			response.setData(false);
		} catch (Exception e) {
			logger.error("获取是否已收藏失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取是否已收藏失败");
			response.setData(false);
		}
		return response;
	}

	@Override
	public Response<Boolean> collect(Long appId, String sessionId, String token, Byte type, String title, Long sourceId, Byte sourceType) {
		Response<Boolean> response = Response.newInstance();
		try {
			boolean data = userFavoriteManager.collect(appId, sessionId, token, type, title, sourceId, sourceType);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error(type.byteValue() == 1 ? "收藏" : "取消收藏" + "失败", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
			response.setData(false);
		} catch (Exception e) {
			logger.error(type.byteValue() == 1 ? "收藏" : "取消收藏" + "失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage(type.byteValue() == 1 ? "收藏" : "取消收藏" + "失败");
			response.setData(false);
		}
		return response;
	}
	
}
