package com.cqliving.cloud.online.order.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.order.domain.OrderEvaluate;
import com.cqliving.cloud.online.order.dto.OrderEvaluateDto;
import com.cqliving.cloud.online.order.manager.OrderEvaluateManager;
import com.cqliving.cloud.online.order.service.OrderEvaluateService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("orderEvaluateService")
@ServiceHandleMapping(managerClass = OrderEvaluateManager.class)
public class OrderEvaluateServiceImpl implements OrderEvaluateService {

	private static final Logger logger = LoggerFactory.getLogger(OrderEvaluateServiceImpl.class);
	
	@Autowired
	private OrderEvaluateManager orderEvaluateManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询订单商品评价表失败")})
	public Response<PageInfo<OrderEvaluate>> queryForPage(PageInfo<OrderEvaluate> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询订单商品评价表失败")})
	public Response<OrderEvaluate> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除订单商品评价表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除订单商品评价表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存订单商品评价表失败")})
	public Response<Void> save(OrderEvaluate orderEvaluate) {
		return null;
	}
	/**
     * 分页查询评论信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月25日上午11:37:43
     */
	@Override
    public Response<PageInfo<OrderEvaluateDto>> queryEvaluateForPage(PageInfo<OrderEvaluateDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap){
	    Response<PageInfo<OrderEvaluateDto>> res = Response.newInstance();
	    try {
	        PageInfo<OrderEvaluateDto> data = orderEvaluateManager.queryEvaluateForPage(pageInfo, map, orderMap);
            res.setData(data);
        } catch (BusinessException e) {
            logger.error("分页查询评论信息失败：" + e.getMessage(), e);
            res.setCode(e.getErrorCode());
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("分页查询评论信息失败", e);
            res.setCode(ErrorCodes.FAILURE);
            res.setMessage("分页查询评论信息失败");
        }
	    return res;
	}
    
    /**
     * 通过Id查询评论信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月25日上午11:40:42
     */
	@Override
    public Response<OrderEvaluateDto> getById(Long id){
	    Response<OrderEvaluateDto> res = Response.newInstance();
	    try {
            OrderEvaluateDto orderEvaluateDto = orderEvaluateManager.getById(id);
            res.setData(orderEvaluateDto);
        } catch (BusinessException e) {
            logger.error("通过Id查询评论信息失败：" + e.getMessage(), e);
            res.setCode(e.getErrorCode());
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("通过Id查询评论信息失败", e);
            res.setCode(ErrorCodes.FAILURE);
            res.setMessage("通过Id查询评论信息失败");
        }
        return res;
	}

	/**
     * 审核
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月29日下午2:18:36
     */
    @Override
    public Response<Void> auditing(Long[] ids, Long[] goodsIds, Byte status, String auditingContent) {
        Response<Void> res = Response.newInstance();
        try {
            orderEvaluateManager.auditing(ids, goodsIds, status, auditingContent);
            res.setCode(1);
            res.setMessage("审核成功");
        } catch (BusinessException e) {
            logger.error("审核失败：" + e.getMessage(), e);
            res.setCode(e.getErrorCode());
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("审核失败", e);
            res.setCode(ErrorCodes.FAILURE);
            res.setMessage("审核失败");
        }
        return res;
    }

	@Override
	public Response<ScrollPage<OrderEvaluateDto>> queryForScrollPage(ScrollPage<OrderEvaluateDto> scrollPage, Map<String, Object> conditionMap) {
		Response<ScrollPage<OrderEvaluateDto>> response = Response.newInstance();
		try {
			ScrollPage<OrderEvaluateDto> data = orderEvaluateManager.queryForScrollPage(scrollPage, conditionMap);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取商品评价列表失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取商品评价列表失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取商品评价列表失败");
		}
		return response;
	}

	@Override
	public Response<Boolean> add(Long appId, String sessionId, String token, Long orderId, String goodsIds, String goodsScores, String contents, String imgs) {
		Response<Boolean> response = Response.newInstance();
		try {
			Byte status = orderEvaluateManager.add(appId, sessionId, token, orderId, goodsIds, goodsScores, contents, imgs);
			response.setData(true);
			response.setMessage(OrderEvaluate.STATUS1.equals(status) ? "发表成功，请等待审核通过" : "发表成功");
			response.setCode(OrderEvaluate.STATUS1.equals(status) ? 1 : 0);	
		} catch (BusinessException e) {
			logger.error("发表订单商品评价失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
			response.setData(false);
		} catch (Exception e) {
			logger.error("发表订单商品评价失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("发表订单商品评价失败");
			response.setData(false);
		}
		return response;
	}
	
}