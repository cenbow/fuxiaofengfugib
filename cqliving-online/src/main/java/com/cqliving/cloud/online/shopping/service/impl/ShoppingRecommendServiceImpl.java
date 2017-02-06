package com.cqliving.cloud.online.shopping.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.shopping.domain.ShoppingRecommend;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.cloud.online.shopping.dto.ShoppingRecommendDto;
import com.cqliving.cloud.online.shopping.manager.ShoppingRecommendManager;
import com.cqliving.cloud.online.shopping.service.ShoppingRecommendService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("shoppingRecommendService")
@ServiceHandleMapping(managerClass = ShoppingRecommendManager.class)
public class ShoppingRecommendServiceImpl implements ShoppingRecommendService {

	private static final Logger logger = LoggerFactory.getLogger(ShoppingRecommendServiceImpl.class);
	
	@Autowired
	private ShoppingRecommendManager shoppingRecommendManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询商品推荐表失败")})
	public Response<PageInfo<ShoppingRecommend>> queryForPage(PageInfo<ShoppingRecommend> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询商品推荐表失败")})
	public Response<ShoppingRecommend> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商品推荐表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商品推荐表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存商品推荐表失败")})
	public Response<Void> save(ShoppingRecommend shoppingRecommend) {
		return null;
	}

	@Override
	public Response<List<ShoppingGoodsDto>> getCarouselByAppId(Long appId) {
		Response<List<ShoppingGoodsDto>> response = Response.newInstance();
		try {
			List<ShoppingGoodsDto> data = shoppingRecommendManager.getCarouselByAppId(appId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取商城推荐轮播数据失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取商城推荐轮播数据失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取商城推荐轮播数据失败");
		}
		return response;
	}

    @Override
    @ServiceMethodHandle(managerMethodName="queryDtoForPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询商城首页信息失败")})
    public Response<PageInfo<ShoppingRecommendDto>> queryDtoForPage(PageInfo<ShoppingRecommendDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName="updateSortNo",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改排序号失败")})
    public Response<Void> updateSortNo(Integer sortNo, Long id) {
        return null;
    }

    /**
     * 通过id查询推荐详情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月23日下午3:43:38
     */
    @Override
    @ServiceMethodHandle(managerMethodName="getById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改排序号失败")})
    public Response<ShoppingRecommendDto> getById(Long id) {
        return null;
    }

	@Override
	public Response<List<ShoppingGoodsDto>> getIndex(Map<String, Object> conditionMap, Map<String, Boolean> orderMap) {
		Response<List<ShoppingGoodsDto>> response = Response.newInstance();
		try {
			List<ShoppingGoodsDto> data = shoppingRecommendManager.getIndex(conditionMap, orderMap);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取商城推荐首页数据失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取商城推荐首页数据失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取商城推荐首页数据失败");
		}
		return response;
	}
	
}