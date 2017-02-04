package com.org.weixin.module.ahjy.service;

import java.util.List;
import java.util.Map;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.ahjy.domain.AhjyActivity;
import com.org.weixin.module.ahjy.dto.AhjyActivityCountDto;
import com.org.weixin.module.ahjy.dto.AhjyLuckyDrawDto;

/**
 * 艾赫金源活动表 Service
 *
 * Date: 2016-03-26 09:10:38
 *
 * @author Acooly Code Generator
 *
 */
public interface AhjyActivityService extends EntityService<AhjyActivity> {

	/**
	 * Title: 开始活动
	 * @author Tangtao on 2016年3月26日
	 * @param activityId
	 */
	void begin(Long activityId);

	public AhjyActivity createActivity();
	
	/**
	 * Title:从外面链接进入抽奖页面
	 * @author yuwu on 2016年3月27日
	 * @param userId
     * @param activityId
	 * @return
	 */
	public AhjyLuckyDrawDto linkAward(Long userId);
    
	/**
     * Title:抽奖操作
     * @author yuwu on 2016年3月27日
     * @param userId
     * @param activityId
     * @return
     */
    public AhjyLuckyDrawDto luckyDraw(Long userId);
    
    /**
     * <p>Description:</p>
     * @param conditions
     * @return
     * @author fuxiaofeng on 2016年4月21日
     */
    public Map<String,List<AhjyActivityCountDto>> statistics(Map<String,Object> conditions);
}
