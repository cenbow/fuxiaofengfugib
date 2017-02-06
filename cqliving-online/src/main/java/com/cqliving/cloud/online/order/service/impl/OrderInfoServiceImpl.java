package com.cqliving.cloud.online.order.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.order.domain.OrderInfo;
import com.cqliving.cloud.online.order.dto.OrderConfirmDarta;
import com.cqliving.cloud.online.order.dto.OrderInfoData;
import com.cqliving.cloud.online.order.dto.OrderInfoDto;
import com.cqliving.cloud.online.order.dto.OrderRefundData;
import com.cqliving.cloud.online.order.dto.PaySuccessResultData;
import com.cqliving.cloud.online.order.manager.OrderInfoManager;
import com.cqliving.cloud.online.order.service.OrderInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("orderInfoService")
@ServiceHandleMapping(managerClass = OrderInfoManager.class)
public class OrderInfoServiceImpl implements OrderInfoService {

	private static final Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);
	
	@Autowired
	private OrderInfoManager orderInfoManager;
	
	public Response<PageInfo<OrderInfoDto>> queryForPage(PageInfo<OrderInfoDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap) {
		Response<PageInfo<OrderInfoDto>> rs = Response.newInstance();
		try {
			rs.setData(orderInfoManager.queryForPage(pageInfo, map, orderMap));
		}catch(BusinessException e){
			logger.error("分页查询订单失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("分页查询订单失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("分页查询订单失败");
		}
		return rs;
	}

	@Override
	public Response<OrderInfo> get(Long id) {
		Response<OrderInfo> rs = Response.newInstance();
		try {
			rs.setData(orderInfoManager.get(id));
		}catch(BusinessException e){
			logger.error("查询订单失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询订单失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("查询订单失败");
		}
		return rs;
	}
	
	@Override
	public Response<OrderInfo> getByOrderNo(String orderNo) {
		Response<OrderInfo> rs = Response.newInstance();
		try {
			rs.setData(orderInfoManager.getByOrderNo(orderNo));
		}catch(BusinessException e){
			logger.error("根据订单号查询订单失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("根据订单号查询订单失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("查询订单失败");
		}
		return rs;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除订单失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除订单失败")})
	public Response<Void> deleteLogic(Long... ids) {
		logger.info("删除操作");
		return null;
	}
	
	@Override
	public Response<Void> updateStatus(Byte status,Long... ids) {
		Response<Void> rs = Response.newInstance();
		try {
			orderInfoManager.updateStatus(status, ids);
		}catch(BusinessException e){
			logger.error("订单【" + ids + "】修改状态【"+ (OrderInfo.allStatuss.containsKey(status) ? OrderInfo.allStatuss.get(status) : "未知状态") + "】失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("订单【" + ids + "】修改状态【"+ (OrderInfo.allStatuss.containsKey(status) ? OrderInfo.allStatuss.get(status) : "未知状态") + "】失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("订单修改状态失败");
		}
		return rs;
	}
	
	@Override
	public Response<Void> updatePayForStatus(Byte payforStatus,Long... ids) {
		Response<Void> rs = Response.newInstance();
		try {
			orderInfoManager.updatePayForStatus(payforStatus, ids);
		}catch(BusinessException e){
			logger.error("订单【" + ids + "】修改支付状态【"+ (OrderInfo.allPayforStatuss.containsKey(payforStatus) ? OrderInfo.allPayforStatuss.get(payforStatus) : "未知状态") + "】失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("订单【" + ids + "】修改支付状态【"+ (OrderInfo.allPayforStatuss.containsKey(payforStatus) ? OrderInfo.allPayforStatuss.get(payforStatus) : "未知状态") + "】失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("订单修改支付状态失败");
		}
		return rs;
	}
	
	@Override
	public Response<Void> cancelByUser(Long appId, String sessionId, String token, Long...ids) {
		Response<Void> rs = Response.newInstance();
		try {
			orderInfoManager.cancelByUser(appId, sessionId, token, ids);
		}catch(BusinessException e){
			logger.error("修改订单支付状态失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改订单支付状态失败：：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("修改订单支付状态失败");
		}
		return rs;
	}
	
	@Override
	public Response<Void> delByUser(Long appId, String sessionId, String token, Long...ids) {
		Response<Void> rs = Response.newInstance();
		try {
			orderInfoManager.delByUser(appId, sessionId, token, ids);
		}catch(BusinessException e){
			logger.error("用户取消订单失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("用户取消订单失败：：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("用户取消订单失败");
		}
		return rs;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存订单失败")})
	public Response<Void> save(OrderInfo orderInfo) {
		return null;
	}

	@Override
	public Response<CommonListResult<OrderInfoData>> getScrollPage(Long appId, String sessionId, String token, Byte payforStatus, Long lastId) {
		Response<CommonListResult<OrderInfoData>> rs = Response.newInstance();
		try {
			if(OrderInfo.PAYFORSTATUS7.equals(payforStatus)){
				rs.setData(orderInfoManager.getScrollPageRefund(appId, sessionId, token, lastId));
			}else{
				rs.setData(orderInfoManager.getScrollPage(appId, sessionId, token, payforStatus, lastId));
			}
		}catch(BusinessException e){
			logger.error("订单中心列表获取失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("订单中心列表获取失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("订单中心列表获取失败");
		}
		return rs;
	}

	@Override
	public Response<OrderInfoData> detail(Long appId, String sessionId, String token, Long orderId) {
		Response<OrderInfoData> rs = Response.newInstance();
		try {
			rs.setData(orderInfoManager.detail(appId, sessionId, token, orderId));
		}catch(BusinessException e){
			logger.error("订单详情获取失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("订单详情获取失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("订单详情获取失败");
		}
		return rs;
	}

	@Override
	public Response<OrderInfo> create(Long appId, String sessionId, String token, Long recAddressId, String goodsIds, String goodsNums, String descn) {
		Response<OrderInfo> rs = Response.newInstance();
		try {
			rs.setData(orderInfoManager.create(appId, sessionId, token, recAddressId, goodsIds, goodsNums, descn));
		}catch(BusinessException e){
			logger.error("创建订单失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("创建订单失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("创建订单失败");
		}
		return rs;
	}

	@Override
	public Response<OrderConfirmDarta> confirmOrderByUser(Long appId, String sessionId, String token, Long recAddressId, String goodsIds, String goodsNums) {
		Response<OrderConfirmDarta> rs = Response.newInstance();
		try {
			rs.setData(orderInfoManager.confirmOrderByUser(appId, sessionId, token, recAddressId, goodsIds, goodsNums));
		}catch(BusinessException e){
			logger.error("确认订单失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("确认订单失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("确认订单失败");
		}
		return rs;
	}

	@Override
	public Response<Void> applyRefundByUser(Long appId, String sessionId, String token, Long orderId, Long goodsId, String payAccount, String description, String images) {
		Response<Void> rs = Response.newInstance();
		try {
			orderInfoManager.applyRefundByUser(appId, sessionId, token, orderId, goodsId, payAccount, description, images);
		}catch(BusinessException e){
			logger.error("用户申请退款失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("用户申请退款失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("申请退款失败");
		}
		return rs;
	}

	@Override
	public Response<OrderRefundData> getRefundProgress(Long appId, String sessionId, String token, Long orderId, Long goodsId) {
		Response<OrderRefundData> rs = Response.newInstance();
		try {
			rs.setData(orderInfoManager.getRefundProgress(appId, sessionId, token, orderId, goodsId));
		}catch(BusinessException e){
			logger.error("获取退款进度失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取退款进度失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("获取退款进度失败");
		}
		return rs;
	}

	@Override
	public Response<Map<String, String>> getApplyRefund(Long appId, String sessionId, String token, Long orderId, Long goodsId) {
		Response<Map<String, String>> rs = Response.newInstance();
		try {
			rs.setData(orderInfoManager.getApplyRefund(appId, sessionId, token, orderId, goodsId));
		}catch(BusinessException e){
			logger.error("获取申请退款信息失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取申请退款信息失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("获取信息失败");
		}
		return rs;
	}

	@Override
	public Response<Void> confirmReceipt(Long appId, String sessionId, String token, Long orderId) {
		Response<Void> rs = Response.newInstance();
		try {
			orderInfoManager.confirmReceipt(appId, sessionId, token, orderId);
		}catch(BusinessException e){
			logger.error("确认收货失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("确认收货失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("确认收货失败");
		}
		return rs;
	}

	@Override
	public Response<Void> payExpiredTask() {
		Response<Void> rs = Response.newInstance();
		try {
			orderInfoManager.payExpiredTask();
		}catch(BusinessException e){
			logger.error("定时处理订单过期任务失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("定时处理订单过期任务失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("定时处理订单过期任务失败");
		}
		return rs;
	}

	@Override
	public Response<Void> alipayCallback(OrderInfo orderInfo, Map<String, String> map) {
		Response<Void> rs = Response.newInstance();
		try {
			orderInfoManager.alipayCallback(orderInfo, map);
		}catch(BusinessException e){
			logger.error("支付回调失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("支付回调失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("支付回调失败");
		}
		return rs;
	}

	@Override
	public Response<String> getAlipayConfig(Long appId, String sessionId, String token, Long orderId) {
		Response<String> rs = Response.newInstance();
		try {
			rs.setData(orderInfoManager.getAlipayConfig(appId, sessionId, token, orderId));
		}catch(BusinessException e){
			logger.error("支付回调失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("支付回调失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("支付回调失败");
		}
		return rs;
	}

	@Override
	public Response<Void> deliverGoods(Long orderId, String expressCompany, String expressNo, Long userId) {
		Response<Void> rs = Response.newInstance();
		try {
			orderInfoManager.deliverGoods(orderId, expressCompany, expressNo, userId);
		}catch(BusinessException e){
			logger.error("发货失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("发货失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("发货失败");
		}
		return rs;
	}

	@Override
	public Response<Void> refundByAdmin(Long[] ids, String type, String[] contents, Long userId) {
		Response<Void> rs = Response.newInstance();
		try {
			orderInfoManager.refundByAdmin(ids, type, contents, userId);
		}catch(BusinessException e){
			logger.error(("1".equals(type) ? "确认" : "拒绝") + "退款失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(("1".equals(type) ? "确认" : "拒绝") + "退款失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage(("1".equals(type) ? "确认" : "拒绝") + "退款失败");
		}
		return rs;
	}

	@Override
	public Response<Void> cancelByAdmin(Long userId, Long... ids) {
		Response<Void> rs = Response.newInstance();
		try {
			orderInfoManager.cancelByAdmin(userId, ids);
		}catch(BusinessException e){
			logger.error("取消订单失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("取消订单失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("取消订单失败");
		}
		return rs;
	}

	@Override
	public Response<PaySuccessResultData> getOrderPayResult(Long appId, String sessionId, String token, Long orderId) {
		Response<PaySuccessResultData> rs = Response.newInstance();
		try {
			rs.setData(orderInfoManager.getOrderPayResult(appId, sessionId, token, orderId));
		}catch(BusinessException e){
			logger.error("获取订单支付结果失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取订单支付结果失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("获取订单支付结果失败");
		}
		return rs;
	}

    @Override
    public Response<Void> saveByAdmim(OrderInfo orderInfo) {
        Response<Void> rs = Response.newInstance();
        try {
            orderInfoManager.saveByAdmim(orderInfo);
        }catch(BusinessException e){
            logger.error("订单修改失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("订单修改失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("订单修改失败");
        }
        return rs;
    }
}
