package com.org.weixin.module.ahjy.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.tool.common.util.date.DateUtil;
import com.org.weixin.module.ahjy.common.AhjyConstants;
import com.org.weixin.module.ahjy.dao.AhjyUserActivityDao;
import com.org.weixin.module.ahjy.domain.AhjyActivity;
import com.org.weixin.module.ahjy.domain.AhjyUserActivity;
import com.org.weixin.module.ahjy.service.AhjyActivityService;
import com.org.weixin.module.ahjy.service.AhjyUserActivityService;

@Service("ahjyUserActivityService")
public class AhjyUserActivityServiceImpl extends EntityServiceImpl<AhjyUserActivity,AhjyUserActivityDao> implements AhjyUserActivityService {
	
	@Autowired
	private AhjyActivityService ahjyActivityService;

	@Override
	@Transactional
	public synchronized void finish(Long activityId, List<AhjyUserActivity> userList) {
		AhjyActivity activity = ahjyActivityService.get(activityId);
		if (AhjyActivity.STATUS1 == activity.getStatus()) {	//活动中时，才能结束
			activity.setStatus(AhjyActivity.STATUS2);
			activity.setFinishTime(DateUtil.now());
			//找出最高成绩
			int[] results = new int[userList.size()];
			for (int i = 0; i < userList.size(); i++) {
				results[i] = userList.get(i).getScore();
			}
			Integer maxScore = NumberUtils.max(results);
			boolean findWinner = false;
			for (AhjyUserActivity obj : userList) {
				Integer score = obj.getScore();
				if (score.equals(maxScore) && !findWinner) {
					obj.setIsAward(AhjyUserActivity.ISAWARD1);	//中奖
					activity.setAwardUserId(obj.getUserId());	//设置中奖人 id
					ahjyActivityService.update(activity);
					findWinner = true;
				} else {
					obj.setIsAward(AhjyUserActivity.ISAWARD0);	//未中奖
				}
				obj.setScore(score);
				update(obj);
			}
		}
	}

	@Override
	public List<AhjyUserActivity> getByActivity(Long activityId) {
		return getEntityDao().getByActivity(activityId);
	}

	@Override
	public Long getWinner(Long activityId) {
		List<AhjyUserActivity> users = getEntityDao().getWinner(activityId);
		if (CollectionUtils.isNotEmpty(users)) {
			if (users.get(0).getScore() > 0) {	//有分数才能获奖
				return users.get(0).getUserId();
			}
		}
		return null;
	}

	@Override
	@Transactional
	public synchronized Integer join(Long activityId, Long userId, String userImg) {
		if (activityId == null || userId == null) {
			return -1;
		}
		AhjyActivity activity = ahjyActivityService.get(activityId);
		if (activity == null) {
			return -1;
		}
		if (activity.getStatus() == AhjyActivity.STATUS0) {	//未开始
			List<AhjyUserActivity> userList = getByActivity(activityId);
			if (CollectionUtils.isEmpty(userList)) {	//首位加入用户
				AhjyUserActivity user = new AhjyUserActivity();
				user.setActivityId(activityId);
				user.setIsHost(AhjyUserActivity.ISHOST1);
				user.setJoinTime(DateUtil.now());
				user.setScore(0);
				user.setUserId(userId);
				user.setUserImg(userImg);
				save(user);
				return 0;
			} else if (userList.size() >= AhjyConstants.ACTIVITY_USER_LIMIT) {	//人数已满
				return -3;
			} else {
				//判断是否已加入
				boolean isJoined = false;
				for (AhjyUserActivity obj : userList) {
					if (obj.getUserId().equals(userId)) {
						isJoined = true;
						break;
					}
				}
				if (isJoined) {	//已加入
					return -2;
				}
				AhjyUserActivity user = new AhjyUserActivity();
				user.setActivityId(activityId);
				user.setIsHost(AhjyUserActivity.ISHOST0);
				user.setJoinTime(DateUtil.now());
				user.setScore(0);
				user.setUserId(userId);
				user.setUserImg(userImg);
				save(user);
				return 1;
			}
		} else if (activity.getStatus() == AhjyActivity.STATUS1) {	//已开始
			return -4;
		} else {	//已结束
			return -5;
		}
	}

	@Override
	public AhjyUserActivity getFirstJoinUser(Long activityId) {
		List<AhjyUserActivity> list = getEntityDao().getByActivity(activityId);
		for (AhjyUserActivity obj : list) {
			if (obj.getIsHost() == AhjyUserActivity.ISHOST1) {
				return obj;
			}
		}
		return null;
	}

}
