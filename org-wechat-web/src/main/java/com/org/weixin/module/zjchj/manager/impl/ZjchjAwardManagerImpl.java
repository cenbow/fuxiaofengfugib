package com.org.weixin.module.zjchj.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;
import com.org.weixin.module.zjchj.dao.ZjchjAwardDao;
import com.org.weixin.module.zjchj.dao.ZjchjUserAwardDao;
import com.org.weixin.module.zjchj.domain.ZjchjAward;
import com.org.weixin.module.zjchj.domain.ZjchjUserAward;
import com.org.weixin.module.zjchj.dto.ZjchjAwardDto;
import com.org.weixin.module.zjchj.manager.ZjchjAwardManager;

@Service("zjchjAwardManager")
public class ZjchjAwardManagerImpl extends EntityServiceImpl<ZjchjAward, ZjchjAwardDao> implements ZjchjAwardManager {
	
	@Autowired
	private ZjchjUserAwardDao zjchjUserAwardDao;
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}

	@Override
	@Transactional
	public synchronized ZjchjAward draw(Long userId, Byte level) {
		//获取奖品列表
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_level", level);
		map.put("EQ_status", ZjchjAward.STATUS3);
		map.put("GT_actualNum", 0);
		map.put("GT_virtualNum", 0);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("id", false);
		List<ZjchjAward> awards = query(map, sortMap);
		//获取奖品数总量
		ZjchjAward drawAward = null;	//中奖奖品
		Long totalCount = getEntityDao().getVirtualTotalCount(level);
		if (CollectionUtils.isNotEmpty(awards)) {
			int random = new Random().nextInt(totalCount.intValue());
			int count = 0;
			//抽奖
			for (ZjchjAward wzAward : awards) {
				if (random >= count && random < wzAward.getVirtualNum() + count) {
					drawAward = wzAward;
					break;
				} else {
					count = count + wzAward.getVirtualNum();
				}
			}
		}
		
		//判断是否中奖，更新抽奖信息
		if (drawAward != null) {
			//减少奖品真实数量
			if (drawAward.getActualNum() >= 1) {
				getEntityDao().decreaseActualNum(drawAward.getId());
			}
			//增加抽中数
			getEntityDao().increaceAwardNum(drawAward.getId());
			//中奖、未中奖都保存记录，便于判断是否抽过奖
//			if (drawAward.getIsTrueAward().equals(ZjchjAward.ISTRUEAWARD1)) {	//中奖
				//保存中奖信息
				ZjchjUserAward userAward = new ZjchjUserAward();
				userAward.setAwardId(drawAward.getId());
				userAward.setAwardName(drawAward.getName());
				userAward.setCreateTime(DateUtil.now());
				userAward.setIsReward(ZjchjUserAward.ISREWARD0);
				userAward.setIsTrueAward(drawAward.getIsTrueAward());
				userAward.setLevel(level);
				userAward.setRewardPwd(getRewardPwd());
				userAward.setUserId(userId);
				zjchjUserAwardDao.save(userAward);
//			} 
		}
		//返回奖品id
		return drawAward;
	}

	/**
	 * <p>Description: 获取可用的核销码</p>
	 * @author Tangtao on 2016年9月27日
	 * @return
	 */
	private String getRewardPwd() {
		//生成8为随机数字作为核销码
		int pwd = new Random().nextInt(99999999);
		String rewardPwd = StringUtils.leftPad(String.valueOf(pwd), 8, '0');
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_rewardPwd", rewardPwd);
		List<ZjchjUserAward> list = zjchjUserAwardDao.query(map, null);
		if (CollectionUtils.isEmpty(list)) {
			return rewardPwd;
		}
		return getRewardPwd();
	}

	@Override
	public List<ZjchjAwardDto> queryData(Date beginTime, Date endTime, Map<String, Boolean> sortMap) {
		return getEntityDao().queryData(beginTime, endTime, sortMap);
	}
	
}