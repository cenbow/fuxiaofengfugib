package com.org.weixin.module.jywth.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wechat.framework.exception.BusinessException;
import org.wechat.framework.service.EntityServiceImpl;
import org.wechat.framework.utils.Dates;

import com.cqliving.tool.common.util.PhoneUtil;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.google.common.collect.Lists;
import com.org.weixin.module.jywth.common.JYWTHConstants;
import com.org.weixin.module.jywth.dao.JywthAwardDao;
import com.org.weixin.module.jywth.domain.JywthAward;
import com.org.weixin.module.jywth.domain.JywthAwardHis;
import com.org.weixin.module.jywth.domain.JywthMsgLog;
import com.org.weixin.module.jywth.service.JywthAwardHisService;
import com.org.weixin.module.jywth.service.JywthAwardService;
import com.org.weixin.module.jywth.service.JywthMsgLogService;

@Service("jywthAwardService")
public class JywthAwardServiceImpl extends EntityServiceImpl<JywthAward,JywthAwardDao> implements JywthAwardService {

	@Autowired
	private SpyMemcachedClient memcachedClient;
	@Autowired
	JywthMsgLogService jywthMsgLogService;
	@Autowired
	JywthAwardHisService jywthAwardHisService;
	/**
	 * @return
	 * @see com.org.weixin.module.jywth.service.JywthAwardService#getAward()
	 * @author fuxiaofeng on 2016年4月2日
	 */
	@Override
	@Transactional
	public JywthAward getAward(String phone,String code) throws BusinessException{
     		
		try {
			
			//特殊处理,两个业务融合在同一个方法中，不理解请见谅，反正也用不了多久，这个玩意
			if (StringUtil.isEmpty(phone)) {
				String duration = nowIsWhichAwardStage();
				String[] tarr = duration.split(":");
				String endDate = tarr[1];
				//int date = DateUtil.getDay(DateUtil.parseDate(tarr[1], DateUtil.FORMAT_YYYY_MM_DD));
				Integer total = this.getEntityDao().getTotalDuration(tarr[0],endDate);
				if(null == total || total<=0){
					throw new BusinessException(JYWTHConstants.GETAWARD_NOAWARD_ERR,"当天红包已领完,不可继续游戏");
				}
				return new JywthAward();
			}
			
			if(!PhoneUtil.validatePhone(phone.trim())){
				throw new BusinessException(JYWTHConstants.PHONE_ERR,"请输入正确的手机号");
			}
			
			if(jywthAwardHisService.hasGetAwardToday(phone))
				throw new BusinessException(JYWTHConstants.GETAWARD_TODAY_ERR,"你今天已领取过红包啦");
			
            String duration = nowIsWhichAwardStage();
			Assert.hasText(duration, "活动未开始，请耐心等待");
			JywthMsgLog jywthMsgLog = jywthMsgLogService.sendAuthCodeSms(phone, code);
			
			if(jywthMsgLog.getIsSuccess() != JywthMsgLog.ISSUCCESS0)
				throw new BusinessException(JYWTHConstants.GETAWARD_CODE_ERR,"请输入正确的验证码");
			
			String[] tarr = duration.split(":");
			String endDate = tarr[1];
			//int date = DateUtil.getDay(DateUtil.parseDate(tarr[1], DateUtil.FORMAT_YYYY_MM_DD));
			
			/*if(date<=13){
				endDate = "2016-04-13";
			}*/
			
			Integer total = this.getEntityDao().getTotalDuration(tarr[0],endDate);
			if(null == total || total<=0){
				throw new BusinessException(JYWTHConstants.GETAWARD_NOAWARD_ERR,"当天红包已领完,不可继续游戏");
			}
			
			List<JywthAward> data = this.getEntityDao().getListDuration(tarr[0],endDate);
			data = this.addList(data);
			Random random = new Random();
			
			if(total >= 1){
				total = random.nextInt(total);
			}
			//中奖的奖品
			JywthAward jywthAward = data.get(total);
			
			JywthAwardHis jywthAwardHis = jywthAwardHisService.saveAwardHis(phone, jywthAward.getCode(),jywthAward.getName());
			//数量减一
			this.getEntityDao().subtractNum(jywthAward.getCode(), 1);

			jywthAward.setAwardHisId(jywthAwardHis.getId());
			return jywthAward;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new BusinessException(-1,e.getMessage());
		}
		
	}
	
	//当前时间在活动的哪个时间阶段(取活动的当前时间阶段)
	//简单比较法，只比较天
	public String nowIsWhichAwardStage() throws ParseException{
		
		String awardTimeStr = memcachedClient.get(JYWTHConstants.JYWTH_AWARD_DURATION_TIME);
		Assert.hasText(awardTimeStr, "活动时间阶段未配置");
		
		String[] timeArr = awardTimeStr.split(",");
		int day = DateUtil.getDay(Dates.now());
		String durationStr = "";
		
		for(String tr : timeArr){
			String[] timeStr = tr.split(":");
			int ad1 = DateUtil.getDay(DateUtil.parseDate(timeStr[0], DateUtil.FORMAT_YYYY_MM_DD));
			int ad2 = DateUtil.getDay(DateUtil.parseDate(timeStr[1], DateUtil.FORMAT_YYYY_MM_DD));
			if(ad1<=day && day<=ad2){
				durationStr = tr;
				break;
			}
		}
		return durationStr;
	}
	
	//将所有的奖品按照每份一个来做成新集合
	public List<JywthAward> addList(List<JywthAward> data){
		
		if(CollectionUtils.isEmpty(data)) return null;
		List<JywthAward> newData = Lists.newArrayList();
		
		for(JywthAward jywthAward : data){
			int num = jywthAward.getNum();
			for(int i=0;i<num;i++){
				newData.add(jywthAward);
			}
		}
		return newData;
	}

	/**
	 * 
	 * @see com.org.weixin.module.jywth.service.JywthAwardService#updateAwardNum()
	 * @author fuxiaofeng on 2016年4月3日
	 * @throws ParseException 
	 */
	@Override
	@Transactional
	public void updateAwardNum(Map<String,Integer> codenum) throws ParseException {
         
		for(Map.Entry<String,Integer> entry : codenum.entrySet()){
			this.getEntityDao().createNewNum(entry.getKey(), entry.getValue(),Dates.now(),Dates.now());
		}
		
	}
}
