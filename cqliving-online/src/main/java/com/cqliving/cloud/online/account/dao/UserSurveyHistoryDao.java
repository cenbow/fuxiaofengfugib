package com.cqliving.cloud.online.account.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.account.domain.UserSurveyHistory;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 用户参与调研历史表 JPA Dao
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserSurveyHistoryDao extends EntityJpaDao<UserSurveyHistory, Long> {
    //当天的总数
	@Query(value="select count(ush) from UserSurveyHistory ush where ush.userId=?1 and date(ush.createTime)=date(?2) and ush.surveyId=?3")
	long findCurrentDayTotalByUserId(Long userId,Date now,Long surveyId);
	//所有的总数
	@Query(value="select count(ush) from UserSurveyHistory ush where ush.userId=?1 and ush.surveyId=?2 ")
	long findTotalByUserId(Long userId,Long surveyId);
}
