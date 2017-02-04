package com.org.weixin.module.ahjy.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.service.EntityServiceImpl;
import org.wechat.framework.utils.Dates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.common.SessionUser;
import com.org.weixin.module.ahjy.common.AhjyConstants;
import com.org.weixin.module.ahjy.dao.AhjyActivityDao;
import com.org.weixin.module.ahjy.dao.AhjyReceiveAwardDao;
import com.org.weixin.module.ahjy.domain.AhjyActivity;
import com.org.weixin.module.ahjy.dto.AhjyReceiveAwardDto;
import com.org.weixin.module.ahjy.dto.GenerationIntegralDto;
import com.org.weixin.module.ahjy.service.AhjyReceiveAwardService;
import com.ysmall.service.CRMService;
import com.ysmall.service.impl.CRMServiceImplService;
/**
 * 艾赫金源活动领奖 Service 实现类
 *
 * Date: 2016-03-26 09:10:38
 *
 * @author huxiaoping
 *
 */
@Service("ahjyReceiveAwardService")
public class AhjyReceiveAwardServiceImpl extends EntityServiceImpl<AhjyActivity,AhjyReceiveAwardDao> implements AhjyReceiveAwardService {
	public static final Log logger = LogFactory.getLog(AhjyReceiveAwardServiceImpl.class);
	
	@Autowired
	private AhjyActivityDao ahjyActivityDao;
	@Autowired
	private SpyMemcachedClient memcachedClient;
	
	/**
	 * 领奖
	 * @param activityId
	 * @param phone
	 * @param user
	 */
	@Override
	@Transactional
	public  AhjyReceiveAwardDto receiveAward(String phone,SessionUser user) throws Exception{
		AhjyReceiveAwardDto retDto = new AhjyReceiveAwardDto();
		//step1:校验手机号码
		if(!StringUtils.isEmpty(phone)){
			phone = phone.trim();
			Pattern p = Pattern.compile("^[1][3,4,5,8,7][0-9]{9}$"); // 验证手机号 
			Matcher m = p.matcher(phone);
			if(!m.matches()){
				logger.info("手机号码错误：" + phone);
				retDto.setResultFlag(AhjyReceiveAwardDto.RESULT_FLAG1);
			}
		}else{
			logger.info("手机号码错误，手机号码是：" + phone);
			retDto.setResultFlag(AhjyReceiveAwardDto.RESULT_FLAG1);
		}
		// step2:判断是否已经领取奖品
		AhjyActivity activity = getAwardByUserId(retDto,user.getId());
		if(null!=activity){
			
			//根据手机号取领奖数量（判断该手机是否已领奖：空：未领，不空：已领）
			if(!CollectionUtils.isEmpty(ahjyActivityDao.getListByReceivePhone(phone))){
				retDto.setResultFlag(AhjyReceiveAwardDto.RESULT_FLAG2);
				return retDto;
			}
			
			CRMServiceImplService ser = new CRMServiceImplService();
			// step4调用接口：如不是MALL会员，输入手机号将默认注册成为金源新燕莎MALL普卡会员
			createMALL(ser,phone);
			//step：领取奖品
			activity.setIsGetAward(AhjyActivity.ISGETAWARD1);
			
			activity.setReceiverPhone(phone);
			activity.setAwardGainTime(Dates.now());
			
			// step5:判断活动奖品是否为实物
			String awardJson = "";
			if(!StringUtils.isEmpty(activity.getAwardPhysical())){
				awardJson = exchangeCoupon(ser, phone, activity.getAwardPhysical());
			}else{
				awardJson = exchangeCoupon(ser, phone, (String)memcachedClient.get(AhjyConstants.AWARD_INTEGRAL_CODE));
				//Step 6:增加积分,获取授权码
				String str = generationIntegral(ser, phone);
				if(!StringUtils.isEmpty(str)){
					ObjectMapper mapper = new ObjectMapper();
					str = json2String(str);
					logger.info("获取授权码json转换后：" + str);
					GenerationIntegralDto dto = mapper.readValue(str,GenerationIntegralDto.class);
					if(null!=dto&&GenerationIntegralDto.CODESUCCEED.equals(dto.getRetcode())){
						updateScore(ser, phone, dto.getIntegral());
					}
				}
			}
            activity.setAwardJson(awardJson);
			this.update(activity);
		}else{
			return retDto;
		}
		return null;
	}
	/**
	 * 
	 * @param s
	 * @return
	 */
	private String json2String(String s) throws Exception{
		StringBuffer ret = new StringBuffer("{");
		if(!StringUtils.isEmpty(s)){
			s=s.substring(1, s.length()-1);
			String arg[] = s.split(",");
			String arg1[] = null;
			for (String str : arg) {
				if(!StringUtils.isEmpty(str)){
					arg1 = str.split(":");
					if(!StringUtils.isEmpty(arg1[0])){
						ret.append(arg1[0].toLowerCase()).append(":").append(arg1[1]).append(",");
					}
				}
			}
			ret.delete(ret.length()-1, ret.length());
			ret.append("}");
		}
		return ret.toString();
	}
	
	/**
	 * 通过手机号码获取位领奖的活动
	 * @param userId
	 * @return
	 */
	private AhjyActivity getAwardByUserId(AhjyReceiveAwardDto retDto,Long userId) throws Exception{
		//判断是否已经领奖
		List<AhjyActivity> activityList = ahjyActivityDao.getListByUserId(userId, AhjyActivity.ISGETAWARD1);
		//未领奖
		if(null!=activityList&&activityList.size()>0){
			retDto.setResultFlag(AhjyReceiveAwardDto.RESULT_FLAG2);
		}else{
			activityList = ahjyActivityDao.getListByUserId(userId, AhjyActivity.ISGETAWARD0);
			if(null!=activityList&&activityList.size()>0){
				return activityList.get(0);
			}else{
				retDto.setResultFlag(AhjyReceiveAwardDto.RESULT_FLAG3);
			}
		}
		return null;
	}
	
	/**
	 * 创建会员
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	private String createMALL(CRMServiceImplService ser,String phone) throws Exception{
		try{
			CRMService cms = ser.getCRMServiceImplPort();
			String ret = cms.registerMember(phone, null, null, null, 8);
			logger.info("调用接口 ：创建MALL会员：" + ret);
			return ret;
		}catch(Exception e){
			logger.info("调用接口 ：创建MALL会员异常" + e);
			throw e;
		}
	}
	/**
	 * 调用接口，告诉接口方这个实物被领取了
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	private String exchangeCoupon(CRMServiceImplService ser,String phone,String awardPhysicalStr) throws Exception{
		try{
			CRMService cms = ser.getCRMServiceImplPort();
			int awardPhysical = Integer.valueOf(null==awardPhysicalStr?"0":awardPhysicalStr.trim());
			String ret = cms.exchangeCoupon(1, phone, awardPhysical, 8);
			logger.info("调用接口 ：兑换优惠券：" + ret);
			return ret;
		}catch(Exception e){
			logger.info("调用接口 ：兑换优惠券异常" + e);
			throw e;
		}
	}
	
	/**
	 * 调用接口，增加积分获取授权码接口
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	private String generationIntegral(CRMServiceImplService ser,String phone) throws Exception{
		try{
			CRMService cms = ser.getCRMServiceImplPort();
			String ret = cms.generationIntegral(phone, "lS8nKrfKjRY");
			logger.info("调用接口 ：增加积分获取授权码：" + ret);
			return ret;
		}catch(Exception e){
			logger.info("调用接口 ：增加积分获取授权码异常" + e);
			throw e;
		}
	}
	
	/**
	 * 调用接口，增加积分获取授权码接口
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	private String updateScore(CRMServiceImplService ser,String phone,String sqm) throws Exception{
		try{
			CRMService cms = ser.getCRMServiceImplPort();
			String ret = cms.updateScore(sqm, "lS8nKrfKjRY", "500");
			logger.info("调用接口 ：增加积分获取授权码：" + ret);
			return ret;
		}catch(Exception e){
			logger.info("调用接口 ：增加积分获取授权码异常" + e);
			throw e;
		}
	}
}