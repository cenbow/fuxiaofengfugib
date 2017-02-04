package com.org.weixin.module.ahjy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.ahjy.domain.AhjyActivity;

/**
 * 艾赫金源活动表 JPA Dao
 *
 * Date: 2016-03-26 09:10:38
 *
 * @author Acooly Code Generator
 *
 */
public interface AhjyActivityDao extends EntityJpaDao<AhjyActivity,Long> {
    
    public List<AhjyActivity> findByStatusOrderByCreateTimeDesc(Byte statu);
            
    /**
     * Title:获取
     * @author yuwu on 2016年3月27日
     * @param activityId
     * @return
     */
    @Query("from AhjyActivity where awardUserId = ?1 and isGetAward = ?2")
    List<AhjyActivity> getListByUserId(Long userId,Byte isGetAward);
    
    
    @Query("from AhjyActivity where receiverPhone=?1 and isGetAward = 1")
    List<AhjyActivity> getListByReceivePhone(String receivePhone);
    
    /**
     * Title:已中奖但未抽奖客户
     * @author yuwu on 2016年3月29日
     * @param userId
     * @return
     */
    @Query("from AhjyActivity where awardUserId = ?1")
    List<AhjyActivity> getListByUserId(Long userId);
    
    /**
     * Title:获取用户未抽奖的数据
     * @author yuwu on 2016年3月29日
     * @param userId
     * @return
     */
    @Query("from AhjyActivity where awardUserId = ?1 and isGetAward is null")
    List<AhjyActivity> getByUserId(Long userId);
    
}
