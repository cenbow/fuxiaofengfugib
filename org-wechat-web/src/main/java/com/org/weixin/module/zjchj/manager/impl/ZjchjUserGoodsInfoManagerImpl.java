package com.org.weixin.module.zjchj.manager.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.tool.common.util.date.DateUtil;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.org.weixin.module.zjchj.constant.ZjchjConstants;
import com.org.weixin.module.zjchj.dao.ZjchjBillInfoDao;
import com.org.weixin.module.zjchj.dao.ZjchjGoodsInfoDao;
import com.org.weixin.module.zjchj.dao.ZjchjUserGoodsInfoDao;
import com.org.weixin.module.zjchj.domain.ZjchjBillInfo;
import com.org.weixin.module.zjchj.domain.ZjchjGoodsInfo;
import com.org.weixin.module.zjchj.domain.ZjchjUserGoodsInfo;
import com.org.weixin.module.zjchj.dto.ZjchjAwardDto;
import com.org.weixin.module.zjchj.manager.ZjchjUserGoodsInfoManager;

@Service("zjchjUserGoodsInfoManager")
public class ZjchjUserGoodsInfoManagerImpl extends EntityServiceImpl<ZjchjUserGoodsInfo, ZjchjUserGoodsInfoDao> implements ZjchjUserGoodsInfoManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ZjchjUserGoodsInfoManagerImpl.class);
	
	@Autowired
	private SpyMemcachedClient memcachedClient;
	@Autowired
	private ZjchjBillInfoDao zjchjBillInfoDao;
	@Autowired
	private ZjchjGoodsInfoDao zjchjGoodsInfoDao;

	@Override
	@Transactional
	public void light(String serialid, Long userId) {
		//获取订单数据
		List<ZjchjBillInfo> billInfos = zjchjBillInfoDao.getBySerialid(serialid);
		if (CollectionUtils.isEmpty(billInfos)) {
			return;
		}
		
		//获取爆款菜品
		List<ZjchjGoodsInfo> goodsInfos = zjchjGoodsInfoDao.getAll();
		Set<ZjchjGoodsInfo> matchGoods = Sets.newTreeSet();
		//找出订单中的爆款菜品
		for (ZjchjBillInfo b : billInfos) {
			for (ZjchjGoodsInfo g : goodsInfos) {
				logger.info("订单菜品：【" + b.getItemsName() + "】,爆款菜品：【" + g.getName() + "】");
				if (b.getItemsName().trim().equals(g.getName().trim())) {
					logger.info("===========================>用户【" + userId + "】点亮菜品：" + g.getName());
					matchGoods.add(g);
					break;
				}
			}
			//更新订单表的用户id
			if (b.getUserId() == null) {
				b.setUserId(userId);
				zjchjBillInfoDao.update(b);
			}
		}
		
		//获取用户点亮菜品列表
		Date now = DateUtil.now();
		List<ZjchjUserGoodsInfo> userGoodsInfos = getEntityDao().getByUserId(userId);
		//找出新增点亮的菜品，保存到数据库
		out:
		for (ZjchjGoodsInfo g : matchGoods) {
			for (ZjchjUserGoodsInfo ug : userGoodsInfos) {
				if (g.getId().equals(ug.getGoodsId())) {	//已点亮过，跳过
					continue out;
				}
			}
			//在已点亮列表中未找到该菜品，新增点亮菜品
			ZjchjUserGoodsInfo obj = new ZjchjUserGoodsInfo();
			obj.setCreateTime(now);
			obj.setGoodsId(g.getId());
			obj.setImage(g.getImage());
			obj.setName(g.getName());
			obj.setUserId(userId);
			save(obj);
		}
	}

	@Override
	public List<Long> getExtremeWin() {
		List<ZjchjUserGoodsInfo> list = getEntityDao().getStatistics();
		List<Long> userIds = Lists.newArrayList();
		String extremeFoodNumStr = memcachedClient.get(ZjchjConstants.EXTREME_FOOD_NUM);
		Integer extremeFoodNum = NumberUtils.toInt(extremeFoodNumStr, 20);
		for (ZjchjUserGoodsInfo obj : list) {
			if (obj.getGoodsId().intValue() >= extremeFoodNum) {
				userIds.add(obj.getUserId());
			}
		}
		Collections.shuffle(userIds);
		if (CollectionUtils.isNotEmpty(userIds) && userIds.size() >= 3) {
			//返回随机3个
			return 	userIds.subList(0, 3);
		}
		return userIds;
	}

	@Override
	public Long getTotalPeople() {
		Long count = getEntityDao().getTotalPeople();
		return count == null ? 0L : count;
	}

	@Override
	public Long getCountByQuantity(Integer quantity) {
		Long count = getEntityDao().getCountByQuantity(quantity);
		return count == null ? 0L : count;
	}

	@Override
	public List<ZjchjAwardDto> getDistribution() {
		List<ZjchjAwardDto> list = getEntityDao().getDistribution();
		return list;
	}
	
}