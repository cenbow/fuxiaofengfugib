package com.org.weixin.module.ahjy.service;

import java.util.List;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.ahjy.domain.AhjyUserActivity;

/**
 * 艾赫金源活动用户表 Service
 *
 * Date: 2016-03-26 09:10:39
 *
 * @author Acooly Code Generator
 *
 */
public interface AhjyUserActivityService extends EntityService<AhjyUserActivity> {

	/**
	 * Title: 活动结束
	 * @author Tangtao on 2016年3月26日
	 * @param activityId 活动 id
	 * @param userList 用户活动集合
	 */
	void finish(Long activityId, List<AhjyUserActivity> userList);

	/**
	 * Title: 获取参与活动的用户
	 * @author Tangtao on 2016年3月26日
	 * @param activityId 活动 id
	 * @return
	 */
	List<AhjyUserActivity> getByActivity(Long activityId);

	/**
	 * Title: 获取活动赢家
	 * @author Tangtao on 2016年3月28日
	 * @param activity 活动
	 * @return
	 */
	Long getWinner(Long activityId);

	/**
	 * Title: 加入活动
	 * @author Tangtao on 2016年3月26日
	 * @param activityId 活动 id
	 * @param userId 用户 id
	 * @param userImg 用户头像
	 * @return 
	 * 	0: 加入成功，首位加入<br />
	 * 	1: 加入成功，非首位加入<br />
	 * 	-1: 活动不存在<br />
	 * 	-2: 已加入<br />
	 * 	-3: 人数已满<br />
	 * 	-4: 活动已开始<br />
	 * 	-5: 活动已结束
	 */
	Integer join(Long activityId, Long userId, String userImg);

	/**
	 * Title:
	 * @author Tangtao on 2016年3月30日
	 * @param activityId
	 * @return
	 */
	AhjyUserActivity getFirstJoinUser(Long activityId);

}
