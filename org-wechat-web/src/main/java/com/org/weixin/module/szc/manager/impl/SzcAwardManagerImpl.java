package com.org.weixin.module.szc.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.framework.utils.Dates;
import com.google.common.collect.Lists;
import com.org.weixin.module.szc.dao.SzcAwardDao;
import com.org.weixin.module.szc.domain.SzcAward;
import com.org.weixin.module.szc.manager.SzcAwardManager;
import com.org.weixin.module.szc.util.RandomUtil;


@Service("szcAwardManager")
public class SzcAwardManagerImpl extends EntityServiceImpl<SzcAward, SzcAwardDao> implements SzcAwardManager {

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcAwardManager#getSzcAward()
	 */
	@Override
	public SzcAward getSzcAward(Integer district) {
		
		Date now = Dates.now();
		List<SzcAward> szcAwards = this.getEntityDao().getSzcAward(now, now,district);
		if(CollectionUtils.isEmpty(szcAwards))return null;
		return szcAwards.get(0);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcAwardManager#findByCode(java.lang.String)
	 */
	@Override
	public SzcAward findByCode(String awardCode) {
		
		return this.getEntityDao().findByCode(awardCode);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcAwardManager#getAllSzcAward()
	 */
	@Override
	public long getAllSzcAward(Date date,Integer district) {
		return this.getEntityDao().getAllSzcAward(date, date,district);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcAwardManager#luchAward()
	 */
	@Override
	public SzcAward luckDraw(Integer district) {

		Date now = Dates.now();
		List<SzcAward> szcAwards = this.getEntityDao().getSzcAward(now, now,district);
		if(CollectionUtils.isEmpty(szcAwards))
			return null;
		
		int total = 0;
		List<SzcAward> award=Lists.newArrayList();
		for(SzcAward szcAward : szcAwards){
			total += szcAward.getVirtualNum();
			for(int i=0;i<szcAward.getVirtualNum();i++){
				award.add(szcAward);
			}
		}
		return award.get(RandomUtil.getRandom(total));
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcAwardManager#minusVirtualNum(java.lang.String, int)
	 */
	@Override
	public int minusVirtualNum(String awardCode, int num) {
		
		return this.getEntityDao().minusVirtualNum(awardCode, num);
	}
}
