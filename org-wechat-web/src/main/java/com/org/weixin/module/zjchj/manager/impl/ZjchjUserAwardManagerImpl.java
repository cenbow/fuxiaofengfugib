package com.org.weixin.module.zjchj.manager.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.tool.common.util.date.DateUtil;
import com.org.weixin.module.zjchj.dao.ZjchjUserAwardDao;
import com.org.weixin.module.zjchj.domain.ZjchjUserAward;
import com.org.weixin.module.zjchj.manager.ZjchjUserAwardManager;

@Service("zjchjUserAwardManager")
public class ZjchjUserAwardManagerImpl extends EntityServiceImpl<ZjchjUserAward, ZjchjUserAwardDao> implements ZjchjUserAwardManager {

	@Override
	@Transactional
	public void reward(Long id, Long userId, String userName) {
		ZjchjUserAward award = get(id);
		if (award != null && ZjchjUserAward.ISREWARD0.equals(award.getIsReward())) {	//未核销
			award.setBackUserId(userId);
			award.setBackUserName(userName);
			award.setIsReward(ZjchjUserAward.ISREWARD1);
			award.setRewardTime(DateUtil.now());
			update(award);
		}
	}
	
}