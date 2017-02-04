package com.org.weixin.module.zjchj.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;
import com.org.weixin.module.zjchj.dao.ZjchjBillInfoDao;
import com.org.weixin.module.zjchj.dao.ZjchjGoodsInfoDao;
import com.org.weixin.module.zjchj.domain.BillInfo;
import com.org.weixin.module.zjchj.domain.GoodsDetail;
import com.org.weixin.module.zjchj.domain.ZjchjBillInfo;
import com.org.weixin.module.zjchj.domain.ZjchjGoodsInfo;
import com.org.weixin.module.zjchj.dto.ZjchjBillInfoDto;
import com.org.weixin.module.zjchj.manager.ZjchjBillInfoManager;

@Service("zjchjBillInfoManager")
public class ZjchjBillInfoManagerImpl extends EntityServiceImpl<ZjchjBillInfo, ZjchjBillInfoDao> implements ZjchjBillInfoManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ZjchjBillInfoManagerImpl.class);
	
	@Autowired
	private ZjchjGoodsInfoDao zjchjGoodsInfoDao;

	@Override
	@Transactional
	public String save(String openId, BillInfo billInfo) {
		if (billInfo == null || CollectionUtils.isEmpty(billInfo.getGoodsDetails()) || StringUtils.isBlank(billInfo.getBillSerialNumber())) {
			return null;
		}
		
		//查询此订单是否获取过流水号
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_billSerialNumber", billInfo.getBillSerialNumber());
		List<ZjchjBillInfo> list = query(map, null);
		if (CollectionUtils.isNotEmpty(list)) {
			//已获取过流水号
			return "-1";
		}
		
		//生成业务流水号
		String serialId = UUID.randomUUID().toString().replaceAll("-", "");
		//保存订单记录，每个菜品一条记录
		Date now = DateUtil.now();
		ZjchjBillInfo obj;
		//查询爆款菜品
		List<ZjchjGoodsInfo> all = zjchjGoodsInfoDao.getAll();
		//处理瑞可爷爷店的订单
		if (billInfo.getShopEntityName().contains("瑞可爷爷")) {
			//如果订单是瑞可爷爷，则点亮该店铺的菜品
			map = Maps.newHashMap();
			map.put("EQ_shopName", "瑞可爷爷");
			List<ZjchjGoodsInfo> shop = zjchjGoodsInfoDao.query(map, null);
			for (ZjchjGoodsInfo good : shop) {
				obj = new ZjchjBillInfo();
				obj.setBillSerialNumber(billInfo.getBillSerialNumber());
				obj.setCreateTime(now);
				obj.setItemsErial("");
				obj.setItemsName(good.getName());
				obj.setOpenId(openId);
				obj.setSerialId(serialId);
				obj.setShopName(billInfo.getShopEntityName());
				getEntityDao().save(obj);
			}
		} else {
			logger.info("=============================查找爆款菜品=============================");
			for (GoodsDetail gd : billInfo.getGoodsDetails()) {
				for (ZjchjGoodsInfo gi : all) {
					logger.info("订单菜品：【" + gd.getName() + "】,爆款菜品：【" + gi.getName() + "】");
					boolean b1 = false;
					boolean b2 = false;
					if (StringUtils.isNotBlank(gi.getOtherNames())) {
						//如果存在备用名称，使用备用名称匹配
						b1 = gi.getShopName().equals(billInfo.getShopEntityName()) && ArrayUtils.contains(gi.getOtherNames().split(","), gd.getName());
					}
					//店铺名和菜品左匹配
					b2 = gd.getName().contains(gi.getName()) && gi.getShopName().equals(billInfo.getShopEntityName());
					if (b1 || b2) {
						obj = new ZjchjBillInfo();
						obj.setBillSerialNumber(billInfo.getBillSerialNumber());
						obj.setCreateTime(now);
						obj.setItemsErial(gd.getItemserial());
						obj.setItemsName(gi.getName());
						obj.setOpenId(openId);
						obj.setSerialId(serialId);
						obj.setShopName(billInfo.getShopEntityName());
						getEntityDao().save(obj);
					}
				}
			}
		}
		return serialId;
	}

	@Override
	public List<ZjchjBillInfoDto> getStatistics(Date beginTime, Date endTime) {
		return getEntityDao().getStatistics(beginTime, endTime);
	}
	
}