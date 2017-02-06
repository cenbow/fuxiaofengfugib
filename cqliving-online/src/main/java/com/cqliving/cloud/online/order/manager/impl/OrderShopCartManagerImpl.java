package com.cqliving.cloud.online.order.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.order.dao.OrderShopCartDao;
import com.cqliving.cloud.online.order.domain.OrderShopCart;
import com.cqliving.cloud.online.order.manager.OrderShopCartManager;
import com.cqliving.cloud.online.shopping.dao.ShoppingGoodsDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Service("orderShopCartManager")
public class OrderShopCartManagerImpl extends EntityServiceImpl<OrderShopCart, OrderShopCartDao> implements OrderShopCartManager {
	
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private ShoppingGoodsDao shoppingGoodsDao;
	@Autowired
	private UserSessionManager userSessionManager;

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public Boolean add(Long appId, String sessionId, String token, Long goodsId, Integer quantity) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//获取登录用户
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		Long userId = userSession.getUserId();
		
		//查询此商品是否已加入过购物车
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_goodsId", goodsId);
		map.put("EQ_userId", userId);
		List<OrderShopCart> list = query(map, null);
		Date now = DateUtil.now();
		boolean result;
		
		//获取要加入购物车的商品
		ShoppingGoods goods = shoppingGoodsDao.get(goodsId);
		if (!ShoppingGoods.STATUS3.equals(goods.getStatus())) {
			throw new BusinessException(ErrorCodes.FAILURE, "商品已下架");	//未上架
		}
		if (quantity > goods.getStock()) {
			throw new BusinessException(ErrorCodes.FAILURE, "库存不足");	//库存不足
		}
		
		if (CollectionUtils.isEmpty(list)) {
			//新增
			OrderShopCart obj = new OrderShopCart();
			obj.setAppId(appId);
			obj.setCreateTime(now);
			obj.setGoodsId(goodsId);
			obj.setQuantity(quantity);
			obj.setUpdateTime(now);
			obj.setUserId(userId);
			save(obj);
			result = obj.getId() != null;
		} else {
			//修改
			OrderShopCart obj = list.get(0);
			if (obj.getQuantity() + quantity > goods.getStock()) {	//库存不足
				throw new BusinessException(ErrorCodes.FAILURE, "库存不足");
			} 
			int count = getEntityDao().modifyQuantity(obj.getId(), quantity, now);
			result = count == 1;
		}
		return result;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public Boolean remove(Long appId, String sessionId, String token, List<Long> idList) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//获取登录用户
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		Long userId = userSession.getUserId();
		
		//查询并删除购物车数据
		int count = 0;
		Iterable<OrderShopCart> list = getEntityDao().findAll(idList);
		for (OrderShopCart obj : list) {
			if (obj.getAppId().equals(appId) && obj.getUserId().equals(userId)) {
				remove(obj);
				count++;
			}
		}
		return count > 0;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public Integer modify(Long appId, String sessionId, String token, Long id, Long goodsId, Integer quantity) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//获取登录用户
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		Long userId = userSession.getUserId();
		
		OrderShopCart obj = get(id);
		//校验
		if (!obj.getUserId().equals(userId)) {
			throw new BusinessException(ErrorCodes.FAILURE, "不能操作其他用户的数据");
		}
		if (!obj.getAppId().equals(appId) || !obj.getGoodsId().equals(goodsId)) {
			throw new BusinessException(ErrorCodes.FAILURE, "商品信息错误");
		}
		//校验通过，修改数据
		if (quantity > 0) {
			//检查库存
			ShoppingGoods goods = shoppingGoodsDao.get(goodsId);
			if (obj.getQuantity() + quantity <= goods.getStock()) {	//库存充足
				obj.setQuantity(obj.getQuantity() + quantity);
				obj.setUpdateTime(DateUtil.now());
				update(obj);
			} else {
				throw new BusinessException(ErrorCodes.FAILURE, "库存不足");
			}
		} else {
			if (obj.getQuantity() <= 1) {	//不能小于1
				obj.setQuantity(1);
			} else {
				obj.setQuantity(obj.getQuantity() + quantity);
			}
			obj.setUpdateTime(DateUtil.now());
			update(obj);
		}
		return obj.getQuantity();
	}
	
}