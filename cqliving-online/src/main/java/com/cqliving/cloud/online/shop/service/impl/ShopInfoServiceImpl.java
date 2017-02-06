package com.cqliving.cloud.online.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.shop.domain.ShopInfo;
import com.cqliving.cloud.online.shop.dto.ShopInfoDto;
import com.cqliving.cloud.online.shop.dto.ShopInfoListDto;
import com.cqliving.cloud.online.shop.manager.ShopInfoManager;
import com.cqliving.cloud.online.shop.service.ShopInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("shopInfoService")
@ServiceHandleMapping(managerClass = ShopInfoManager.class)
public class ShopInfoServiceImpl implements ShopInfoService {

	private static final Logger logger = LoggerFactory.getLogger(ShopInfoServiceImpl.class);
	
	@Autowired
	private ShopInfoManager shopInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询商铺表失败")})
	public Response<PageInfo<ShopInfo>> queryForPage(PageInfo<ShopInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询商铺表失败")})
	public Response<ShopInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商铺表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商铺表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存商铺表失败")})
	public Response<Void> save(ShopInfo shopInfo) {
		return null;
	}

	@Override
	public Response<Void> save(ShopInfo shopInfo, String images, Long userId, String userName) {
		Response<Void> response = Response.newInstance();
		try {
			shopInfoManager.save(shopInfo, images, userId, userName);
		} catch (Exception e) {
			logger.error("保存商铺失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("保存商铺失败");
		}
		return response;
	}

	@Override
	public Response<ScrollPage<ShopInfoDto>> queryForScrollPage(ScrollPage<ShopInfoDto> scrollPage, Double lat, Double lng, Long appId, Long shopTypeId, String regionCode, Long shopCategoryId, String shopName) {
		Response<ScrollPage<ShopInfoDto>> response = Response.newInstance();
		try {
			ScrollPage<ShopInfoDto> data = shopInfoManager.queryForScrollPage(scrollPage, lat, lng, appId, shopTypeId, regionCode, shopCategoryId, shopName);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取商铺列表失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取商铺列表失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取商铺列表失败");
		}
		return response;
	}

	@Override
	public Response<Void> top(Long id, String nickname, Long userId) {
		Response<Void> response = Response.newInstance();
		try {
			shopInfoManager.top(id, nickname, userId);
		} catch (Exception e) {
			logger.error("置顶失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("置顶失败");
		}
		return response;
	}

	@Override
	public Response<Void> untop(Long id, String nickname, Long userId) {
		Response<Void> response = Response.newInstance();
		try {
			shopInfoManager.untop(id, nickname, userId);
		} catch (Exception e) {
			logger.error("取消置顶失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("取消置顶失败");
		}
		return response;
	}

	@Override
	public Response<Byte> save(ShopInfo shopInfo, String images, String descriptions, String sessionId, String token) {
		Response<Byte> response = Response.newInstance();
		try {
			response.setData(shopInfoManager.save(shopInfo, images, descriptions, sessionId, token));
		} catch (BusinessException e) {
			logger.error("商情app用户保存失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("商情app用户保存失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("发布失败");
		}
		return response;
	}
	
	@Override
	public Response<Void> audit(Long[] shopInfoIds, Byte status, String content) {
		Response<Void> response = Response.newInstance();
		try {
			shopInfoManager.audit(shopInfoIds, status, content);
		} catch (BusinessException e) {
			logger.error("商情审核失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("商情审核失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("审核保存失败");
		}
		return response;
	}

	@Override
	public Response<List<ShopInfo>> getRecommendIndex(Long appId) {
		Response<List<ShopInfo>> response = Response.newInstance();
		try {
			List<ShopInfo> data = shopInfoManager.getRecommendIndex(appId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取首页商情失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取首页商情失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取首页商情失败");
		}
		return response;
	}
	/**
     * 获取首页商情列表
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月13日下午3:42:22
     */
	@Override
	public Response<List<ShopInfoDto>> getShopRecommendIndex(Long appId) {
	    Response<List<ShopInfoDto>> response = Response.newInstance();
	    try {
	        List<ShopInfoDto> data = shopInfoManager.getShopRecommendIndex(appId);
	        response.setData(data);
	    } catch (BusinessException e) {
	        logger.error("获取首页商情失败：" + e.getMessage(), e);
	        response.setCode(e.getErrorCode());
	        response.setMessage(e.getMessage());
	    } catch (Exception e) {
	        logger.error("获取首页商情失败：" + e.getMessage(), e);
	        response.setCode(ErrorCodes.FAILURE);
	        response.setMessage("获取首页商情失败");
	    }
	    return response;
	}

	/**
     * 分页查询商商情信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     */
    @Override
    public Response<PageInfo<ShopInfoListDto>> queryByPage(PageInfo<ShopInfoListDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        Response<PageInfo<ShopInfoListDto>> response = Response.newInstance();
        try {
            shopInfoManager.queryByPage(pageInfo, map, orderMap);
            response.setData(pageInfo);
        } catch (BusinessException e) {
            logger.error("分页查询商商情信息失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("分页查询商商情信息失败：" + e.getMessage(), e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("分页查询商商情信息失败");
        }
        return response;
    }
}