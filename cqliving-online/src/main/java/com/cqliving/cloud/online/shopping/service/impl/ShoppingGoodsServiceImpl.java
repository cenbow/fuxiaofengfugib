package com.cqliving.cloud.online.shopping.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.cloud.online.shopping.domain.ShoppingRecommend;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.cloud.online.shopping.manager.ShoppingGoodsManager;
import com.cqliving.cloud.online.shopping.service.ShoppingGoodsService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("shoppingGoodsService")
@ServiceHandleMapping(managerClass = ShoppingGoodsManager.class)
public class ShoppingGoodsServiceImpl implements ShoppingGoodsService {

	private static final Logger logger = LoggerFactory.getLogger(ShoppingGoodsServiceImpl.class);
	
	@Autowired
	private ShoppingGoodsManager shoppingGoodsManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询商品表失败")})
	public Response<PageInfo<ShoppingGoods>> queryForPage(PageInfo<ShoppingGoods> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询商品表失败")})
	public Response<ShoppingGoods> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商品表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商品表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存商品表失败")})
	public Response<Void> save(ShoppingGoods shoppingGoods) {
		return null;
	}

	@Override
	public Response<ScrollPage<ShoppingGoodsDto>> queryForScrollPage(ScrollPage<ShoppingGoodsDto> scrollPage, Map<String, Object> conditionMap) {
		Response<ScrollPage<ShoppingGoodsDto>> response = Response.newInstance();
		try {
			ScrollPage<ShoppingGoodsDto> data = shoppingGoodsManager.queryForScrollPage(scrollPage, conditionMap);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取商城商品分页数据失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取商城商品分页数据失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取商城商品分页数据失败");
		}
		return response;
	}

	@Override
	public Response<List<ShoppingGoodsDto>> getMyCart(Long appId, String sessionId, String token) {
		Response<List<ShoppingGoodsDto>> response = Response.newInstance();
		try {
			List<ShoppingGoodsDto> data = shoppingGoodsManager.getMyCart(appId, sessionId, token);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取我的购物车分页数据失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取我的购物车分页数据失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取我的购物车分页数据失败");
		}
		return response;
	}

	/**
     * 分页查询商品信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月1日上午9:43:57
     */
    @Override
    public Response<PageInfo<ShoppingGoodsDto>> queryByPage(PageInfo<ShoppingGoodsDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap) {
        Response<PageInfo<ShoppingGoodsDto>> response = Response.newInstance();
        try {
            PageInfo<ShoppingGoodsDto> data = shoppingGoodsManager.queryByPage(pageInfo, map, orderMap);
            response.setData(data);
        } catch (BusinessException e) {
            logger.error("分页查询商品信息失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("分页查询商品信息失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("分页查询商品信息失败");
        }
        return response;
    }

    /**
     * 查询商品详细信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月1日上午9:43:57
     */
    @Override
    public Response<ShoppingGoodsDto> getById(Long id) {
        Response<ShoppingGoodsDto> response = Response.newInstance();
        try {
            ShoppingGoodsDto data = shoppingGoodsManager.getById(id);
            response.setData(data);
        } catch (BusinessException e) {
            logger.error("查询商品详细信息失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("查询商品详细信息失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("查询商品详细信息失败");
        }
        return response;
    }

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.shopping.service.ShoppingGoodsService#updateShoppingGoods(com.cqliving.cloud.online.shopping.domain.ShoppingGoods)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> updateShoppingGoods(ShoppingGoods shoppingGoods) {
		return null;
	}
	
    /**
     * 修改排序号
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月2日下午3:47:07
     */
    @Override
    public Response<Void> updateSortNo(Integer sortNo, Long id) {
        Response<Void> response = Response.newInstance();
        try {
            shoppingGoodsManager.updateSortNo(sortNo,id);
        } catch (BusinessException e) {
            logger.error("修改排序号失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("修改排序号失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("修改排序号失败");
        }
        return response;
    }

    /**
     * 修改推荐到首页
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    @Override
    public Response<Void> updateIsRecommemdIndex(Byte isRecommemdIndex, ShoppingRecommend recommend) {
        Response<Void> response = Response.newInstance();
        try {
            shoppingGoodsManager.updateIsRecommemdIndex(isRecommemdIndex, recommend);
        } catch (BusinessException e) {
            logger.error("修改推荐到首页失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("修改推荐到首页失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("修改推荐到首页失败");
        }
        return response;
    }

    /**
     * 修改推荐到轮播
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    @Override
    public Response<Void> updateIsRecommendCarousel(Byte isRecommendCarousel, ShoppingRecommend recommend) {
        Response<Void> response = Response.newInstance();
        try {
            shoppingGoodsManager.updateIsRecommendCarousel(isRecommendCarousel, recommend);
        } catch (BusinessException e) {
            logger.error("修改推荐到轮播失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("修改推荐到轮播失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("修改推荐到轮播失败");
        }
        return response;
    }
}