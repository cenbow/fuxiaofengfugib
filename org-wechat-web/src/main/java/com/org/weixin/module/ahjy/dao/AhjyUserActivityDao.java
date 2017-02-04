package com.org.weixin.module.ahjy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.ahjy.domain.AhjyUserActivity;

/**
 * 艾赫金源活动用户表 JPA Dao
 *
 * Date: 2016-03-26 09:10:39
 *
 * @author Acooly Code Generator
 *
 */
public interface AhjyUserActivityDao extends EntityJpaDao<AhjyUserActivity, Long> {

	/**
	 * Title: 获取参与活动的用户
	 * @author Tangtao on 2016年3月26日
	 * @param activityId 活动 id
	 * @return
	 */
	@Query("from AhjyUserActivity where activityId = ?1 order by joinTime")
	List<AhjyUserActivity> getByActivity(Long activityId);

	/**
	 * Title: 获取获胜用户
	 * @author Tangtao on 2016年3月28日
	 * @param activityId 活动 id
	 * @return
	 */
	@Query("from AhjyUserActivity where activityId = ?1 and isAward = 1")
	List<AhjyUserActivity> getWinner(Long activityId);

}
