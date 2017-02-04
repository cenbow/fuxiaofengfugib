package com.org.weixin.module.ahjy.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.service.EntityServiceImpl;
import org.wechat.framework.utils.Dates;

import com.cqliving.tool.common.util.date.DateUtil;
import com.org.weixin.module.ahjy.common.Probability;
import com.org.weixin.module.ahjy.dao.AhjyActivityDao;
import com.org.weixin.module.ahjy.dao.AhjyActivityDaoCustomImpl;
import com.org.weixin.module.ahjy.dao.AhjyAwardDao;
import com.org.weixin.module.ahjy.domain.AhjyActivity;
import com.org.weixin.module.ahjy.domain.AhjyAward;
import com.org.weixin.module.ahjy.dto.AhjyActivityCountDto;
import com.org.weixin.module.ahjy.dto.AhjyLuckyDrawDto;
import com.org.weixin.module.ahjy.service.AhjyActivityService;

@Service("ahjyActivityService")
public class AhjyActivityServiceImpl extends EntityServiceImpl<AhjyActivity,AhjyActivityDao> implements AhjyActivityService {

    @Autowired
    private AhjyAwardDao ahjyAwardDao;
    @Autowired
    AhjyActivityDaoCustomImpl ahjyActivityDaoCustomImpl;
    
	@Override
	@Transactional
	public synchronized void begin(Long activityId) {
		AhjyActivity activity = get(activityId);
		//未开始的活动，才能开始操作
		if (activity != null && activity.getStatus() == AhjyActivity.STATUS0) {
			activity.setStatus(AhjyActivity.STATUS1);
			activity.setBeginTime(DateUtil.now());
			update(activity);
		}
	}

	/**
	 * @return
	 * @see com.org.weixin.module.ahjy.service.AhjyActivityService#createActivity()
	 * @author fuxiaofeng on 2016年3月26日
	 */
	@Override
	@Transactional
	public AhjyActivity createActivity() {
		
		/*List<AhjyActivity> list = this.getEntityDao().findByStatusOrderByCreateTimeDesc(AhjyActivity.STATUS0);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}*/
		AhjyActivity acti = new AhjyActivity();
		acti.setCreateTime(Dates.now());
		acti.setStatus(AhjyActivity.STATUS0);
		return this.save(acti);
	}

	/**
	 * Title:检查用户是否有中奖
	 * @author yuwu on 2016年3月29日
	 * @param userId
	 * @return
	 */
	public AhjyLuckyDrawDto check(Long userId){
	    AhjyLuckyDrawDto dto = new AhjyLuckyDrawDto();
	    //2、获取用户中奖的活动
        List<AhjyActivity> list1 = this.getEntityDao().getListByUserId(userId);
        //如果用户有未领奖的记录则为true
        boolean flag = false;
        for(AhjyActivity acti : list1){
            Byte isGetAward = acti.getIsGetAward();
            //已领取的不能再领取
            if(isGetAward != null && isGetAward.byteValue() == AhjyActivity.ISGETAWARD1){
                dto.setResultFlag(AhjyLuckyDrawDto.RESULT_FLAG_2);
                return dto;
            }
            //有未领奖的
            if(isGetAward != null && isGetAward.byteValue() == AhjyActivity.ISGETAWARD0){
                flag = true;
            }
        }
        
        //用户未参加该活动或未中奖
        if(!flag && CollectionUtils.isEmpty(list1)){
            dto.setResultFlag(AhjyLuckyDrawDto.RESULT_FLAG_1);
            return dto;
        }
	    return dto;
	}
	
	/**
     * Title:从外面链接进入抽奖页面
     * @author yuwu on 2016年3月27日
     * @param userId
     * @param activityId
     * @return
     */
	@Override
    public AhjyLuckyDrawDto linkAward(Long userId){
	    //检查已领奖或未中奖
        AhjyLuckyDrawDto dto = check(userId);
        
        /** 
         * ------2016-4-21新加需求 @author fuxiaofeng
         *  让用户直接抽奖
         *
         */
        //未参与活动或者未中奖
        if(dto.getResultFlag() != null && dto.getResultFlag() == AhjyLuckyDrawDto.RESULT_FLAG_1){
        	
        	AhjyActivity acti = new AhjyActivity();
    		acti.setCreateTime(Dates.now());
    		acti.setStatus(AhjyActivity.STATUS2);
    		acti.setBeginTime(Dates.now());
    		acti.setFinishTime(Dates.now());
    		acti.setAwardUserId(userId);
    		acti.setCreateUserId(userId);
    		acti.setIsGetAward(AhjyActivity.ISGETAWARD0);
    		//桃子页面
    		dto.setResultFlag(AhjyLuckyDrawDto.RESULT_FLAG1);
    		
    		synchronized(this){
                //获取奖品集合
                  List<AhjyAward> awardLists = ahjyAwardDao.getList();
                  if(CollectionUtils.isNotEmpty(awardLists)){
                      //AhjyAward award = awardLists.get(0);
                      AhjyAward award = getRandomAhjyAward(awardLists);
                      Integer num = award.getNum() - 1;
                      num = (num < 0 ) ? 0 :num; 
                      award.setNum(num);
                      ahjyAwardDao.saveAndFlush(award);//奖品数量减1
                      acti.setAwardPhysical(award.getCode());
                      acti.setImg(award.getImg());
                  }else{
                	  acti.setAwardIntegral("500");
                  }
                  //activity.setAwardCoupon(json.getAwardCoupon());
                  acti.setIsGetAward(AhjyActivity.ISGETAWARD0);
                  this.getEntityDao().saveAndFlush(acti);
                  BeanUtils.copyProperties(acti, dto);
                  dto.setActivityId(acti.getId());
              }
    		return dto;
        	
        }
        
        /** ------2016-4-21新加需求 @author fuxiaofeng  */
        
        if(dto.getResultFlag() != null && dto.getResultFlag() < 0){
            return dto;
        }
        
        List<AhjyActivity> list2 = this.getEntityDao().getListByUserId(userId, AhjyActivity.ISGETAWARD0);
        //奖品获取方式1：之前抽中过，但是未领取，则用之前的。
        if(CollectionUtils.isNotEmpty(list2)){
            AhjyActivity temp = list2.get(0);
            BeanUtils.copyProperties(temp, dto);
            setSetResultFlag(dto);
            dto.setActivityId(temp.getId());
        }else{
            dto.setResultFlag(AhjyLuckyDrawDto.RESULT_FLAG1);
        }
        return dto;
    }
    
	/**
     * Title:抽奖操作
     * 1、如果用户未中奖或未参加该活动，直接返回
     * 2、如果用户在当前活动已经抽过奖，则提示已经抽过奖
     * 3、如果用户之前抽中过奖但是未领奖，则使用之前的奖品。
     * 4、如果用户之前未抽过中奖，则调用接口获取奖品
     * @author yuwu on 2016年3月27日
     * @param userId
     * @param activityId
     * @return
     */
    @Override
    @Transactional
    public AhjyLuckyDrawDto luckyDraw(Long userId){
        //检查已领奖或未中奖
        AhjyLuckyDrawDto dto = check(userId);
        if(dto.getResultFlag() != null && dto.getResultFlag() < 0){
            return dto;
        }
        
        //下面设置用户中奖奖品，奖品获取方式1：之前抽中过，但是未领取，则用之前的。奖品获取方式2：直接调用接口获取奖品
        List<AhjyActivity> list2 = this.getEntityDao().getListByUserId(userId, AhjyActivity.ISGETAWARD0);
        //奖品获取方式1：之前抽中过，但是未领取，则用之前的。
        if(CollectionUtils.isNotEmpty(list2)){
            AhjyActivity temp = list2.get(0);
            BeanUtils.copyProperties(temp, dto);
            dto.setActivityId(temp.getId());
            setSetResultFlag(dto);
            return dto;
        }else{
            //获取未中奖的数据
            List<AhjyActivity> list0 = this.getEntityDao().getByUserId(userId);
            AhjyActivity activity = list0.get(0);
            synchronized(this){
              //获取奖品集合
                List<AhjyAward> awardLists = ahjyAwardDao.getList();
                if(CollectionUtils.isNotEmpty(awardLists)){
                    //AhjyAward award = awardLists.get(0);
                    AhjyAward award = getRandomAhjyAward(awardLists);
                    Integer num = award.getNum() - 1;
                    num = (num < 0 ) ? 0 :num; 
                    award.setNum(num);
                    ahjyAwardDao.saveAndFlush(award);//奖品数量减1
                    activity.setAwardPhysical(award.getCode());
                    activity.setImg(award.getImg());
                }else{
                    activity.setAwardIntegral("500");
                }
                //activity.setAwardCoupon(json.getAwardCoupon());
                activity.setIsGetAward(AhjyActivity.ISGETAWARD0);
                this.getEntityDao().saveAndFlush(activity);
                BeanUtils.copyProperties(activity, dto);
                dto.setActivityId(activity.getId());
                setSetResultFlag(dto);
            }
            return dto;
        }
        
    }
    private AhjyAward getRandomAhjyAward(List<AhjyAward> awardLists){
        AhjyAward ahjyAward = null;
        if(CollectionUtils.isNotEmpty(awardLists)){
            int num = 0;
            for(AhjyAward temp : awardLists){
                num = num+temp.getNum();
            }
            ahjyAward = Probability.probability(awardLists,num);
        }
        return ahjyAward;
    }
    private void setSetResultFlag(AhjyLuckyDrawDto dto){
        if(StringUtils.isNotBlank(dto.getAwardPhysical())){
            dto.setResultFlag(AhjyLuckyDrawDto.RESULT_FLAG2);
        }else{
            dto.setResultFlag(AhjyLuckyDrawDto.RESULT_FLAG3);
        }
    }

	/**
	 * @param conditions
	 * @return
	 * @see com.org.weixin.module.ahjy.service.AhjyActivityService#statistics(java.util.Map)
	 * @author fuxiaofeng on 2016年4月21日
	 */
	@Override
	public Map<String, List<AhjyActivityCountDto>> statistics(Map<String, Object> conditions) {
		
		return ahjyActivityDaoCustomImpl.statistics(conditions);
	}
}
