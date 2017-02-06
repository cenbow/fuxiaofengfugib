package com.cqliving.cloud.online.shopping.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.shopping.domain.ShoppingCategory;
import com.cqliving.cloud.online.shopping.dto.ShoppingCategoryDto;
import com.cqliving.cloud.online.shopping.manager.ShoppingCategoryManager;
import com.cqliving.cloud.online.shopping.service.ShoppingCategoryService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("shoppingCategoryService")
@ServiceHandleMapping(managerClass = ShoppingCategoryManager.class)
public class ShoppingCategoryServiceImpl implements ShoppingCategoryService {

	private static final Logger logger = LoggerFactory.getLogger(ShoppingCategoryServiceImpl.class);
	
	@Autowired
	private ShoppingCategoryManager shoppingCategoryManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询商品分类表失败")})
	public Response<PageInfo<ShoppingCategory>> queryForPage(PageInfo<ShoppingCategory> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询商品分类表失败")})
	public Response<ShoppingCategory> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商品分类表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除商品分类表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存商品分类表失败")})
	public Response<Void> save(ShoppingCategory shoppingCategory) {
		return null;
	}

    @Override
    @ServiceMethodHandle(managerMethodName="getList",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询商品分类集合失败")})
    public Response<List<ShoppingCategoryDto>> getList(Map<String, Object> conditions, Map<String, Boolean> orderMap) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName="queryTreeMode",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询树模型失败")})
    public Response<List<ShoppingCategoryDto>> queryTreeMode(Map<String, Object> conditions,
            Map<String, Boolean> orderMap) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName="sort",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改商品分类排序失败")})
    public Response<Void> sort(Long[] ids, Integer[] sortNums, Long[] parentIds) {
        return null;
    }
    
    @Override
    @ServiceMethodHandle(managerMethodName="saveCategory",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存商品分类表失败")})
    public Response<Void> saveCategory(ShoppingCategory shoppingCategory) {
        return null;
    }
    


    @Override
    public Response<List<ShoppingCategory>> queryForList(Map<String, Object> map, Map<String, Boolean> orderMap) {
        Response<List<ShoppingCategory>> response = Response.newInstance();
        try {
            List<ShoppingCategory> data = shoppingCategoryManager.query(map, orderMap);
            response.setData(data);
        } catch (BusinessException e) {
            logger.error("获取商城分类列表失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("获取商城分类列表失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("获取商城分类列表失败");
        }
        return response;
    }

    @Override
    @ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询商品分类表失败")})
    public Response<List<ShoppingCategory>> queryList(Map<String, Object> conditions, Map<String, Boolean> orderMap) {
        return null;
    }
}
