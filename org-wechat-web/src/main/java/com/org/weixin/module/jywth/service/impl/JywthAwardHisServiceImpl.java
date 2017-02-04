package com.org.weixin.module.jywth.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.exception.BusinessException;
import org.wechat.framework.service.EntityServiceImpl;
import org.wechat.framework.utils.Dates;

import com.cqliving.tool.common.util.date.DateUtil;
import com.org.weixin.module.jywth.common.RandomUtil;
import com.org.weixin.module.jywth.dao.JywthAwardHisDao;
import com.org.weixin.module.jywth.dao.JywthAwardHisDaoCustom;
import com.org.weixin.module.jywth.domain.JywthAwardHis;
import com.org.weixin.module.jywth.domain.JywthMsgLog;
import com.org.weixin.module.jywth.service.JywthAwardHisService;
import com.org.weixin.module.jywth.service.JywthMsgLogService;

@Service("jywthAwardHisService")
public class JywthAwardHisServiceImpl extends EntityServiceImpl<JywthAwardHis,JywthAwardHisDao> implements JywthAwardHisService {

	@Autowired
	JywthAwardHisDaoCustom jywthAwardHisDaoCustom;
	@Autowired
	JywthMsgLogService jywthMsgLogService;
	/**
	 * @param phone
	 * @return
	 * @see com.org.weixin.module.jywth.service.JywthAwardHisService#hasGetAwardToday(java.lang.String)
	 * @author fuxiaofeng on 2016年4月3日
	 */
	@Override
	public boolean hasGetAwardToday(String phone) {
		List<JywthAwardHis> list= this.getEntityDao().findByPhone(phone);
		if(CollectionUtils.isEmpty(list)) return false;
		
		JywthAwardHis jywthAwardHis = list.get(0);
		return DateUtil.isSameDay(jywthAwardHis.getAwardTime(), Dates.now());
	}

	/**
	 * @param code
	 * @param status
	 * @return
	 * @see com.org.weixin.module.jywth.service.JywthAwardHisService#updateStatus(java.lang.String, byte)
	 * @author fuxiaofeng on 2016年4月3日
	 */
	@Override
	@Transactional
	public int updateStatus(String phone,String exchangeCode,byte status) {
		
		return getEntityDao().updateStatus(exchangeCode, status);
	}

	/**
	 * @param phone
	 * @param awardCode
	 * @param exchangeCode
	 * @return
	 * @see com.org.weixin.module.jywth.service.JywthAwardHisService#saveAwardHis(java.lang.String, java.lang.String, java.lang.String)
	 * @author fuxiaofeng on 2016年4月3日
	 */
	@Override
	@Transactional
	public JywthAwardHis saveAwardHis(String phone, String awardCode,String awardName) {
		
		JywthAwardHis jywthAwardHis = new JywthAwardHis();
		
		//发送中奖码
		String exchangeCode = RandomUtil.random(6);
		
		jywthAwardHis.setAwardCode(awardCode);
		jywthAwardHis.setAwardTime(Dates.now());
		jywthAwardHis.setExchangeCode(exchangeCode);
		jywthAwardHis.setPhone(phone);
		jywthAwardHis.setTakeNum(1);
		jywthAwardHis.setTakeStatus(JywthAwardHis.TAKESTATUS0);
		jywthAwardHis.setAwardName(awardName);
		return this.save(jywthAwardHis);
	}

	/**
	 * @param phone
	 * @param exchangeCode
	 * @see com.org.weixin.module.jywth.service.JywthAwardHisService#getAward(java.lang.String, java.lang.String)
	 * @author fuxiaofeng on 2016年4月3日
	 */
	@Override
	@Transactional
	public void verifyAward(String exchangeCode) {

         this.getEntityDao().verifyAward(exchangeCode, Dates.now());
		
	}

	/**
	 * @param date
	 * @return
	 * @see com.org.weixin.module.jywth.service.JywthAwardHisService#queryAwardByDate(java.util.Date)
	 * @author fuxiaofeng on 2016年4月3日
	 */
	@Override
	public List<JywthAwardHis> queryAwardByDate(Date date) {
	
		return this.getEntityDao().queryAwardByDate(date);
	}

	/**
	 * @param date
	 * @return
	 * @see com.org.weixin.module.jywth.service.JywthAwardHisService#queryGetAwardByDate(java.util.Date)
	 * @author fuxiaofeng on 2016年4月3日
	 */
	@Override
	public List<JywthAwardHis> queryVerifyAwardByDate(Date date) {
		
		return this.getEntityDao().queryVerifyAwardByDate(date);
	}

	/**
	 * @param hisId
	 * @return
	 * @see com.org.weixin.module.jywth.service.JywthAwardHisService#takeAward(java.lang.String)
	 * @author fuxiaofeng on 2016年4月3日
	 */
	@Override
	@Transactional
	public synchronized int takeAward(Long hisId) {

		JywthAwardHis his = this.get(hisId);
		
		if(null == his)throw new BusinessException(-1,"奖券不存在");
		
		JywthMsgLog jywthMsgLog = jywthMsgLogService.sendTextSms(his.getPhone(),his.getAwardName(),his.getExchangeCode());
		if(jywthMsgLog.getIsSuccess() != JywthMsgLog.ISSUCCESS0){//未发送成功，中奖失败
			throw new BusinessException(-1,"短信发送失败，中奖失败");
		}
		
		this.getEntityDao().takeAward(hisId, Dates.now());
		
		return 0;
	}

	/**
	 * @param searchMap
	 * @param sortMap
	 * @return
	 * @see com.org.weixin.module.jywth.service.JywthAwardHisService#queryAwardByConditions(java.util.Map, java.util.Map)
	 * @author fuxiaofeng on 2016年4月5日
	 */
	@Override
	public List<JywthAwardHis> queryAwardByConditions(Map<String, String> searchMap, Map<String, Boolean> sortMap) {
		
		return jywthAwardHisDaoCustom.queryAwardByConditions(searchMap, sortMap);
	}
}
