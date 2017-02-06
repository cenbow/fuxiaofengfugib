package com.cqliving.cloud.online.survey.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.survey.domain.SurveyQuestion;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 调研问题表 JPA Dao
 * Date: 2016-04-15 09:46:53
 * @author Code Generator
 */
public interface SurveyQuestionDao extends EntityJpaDao<SurveyQuestion, Long> {

	@Modifying
	@Query("update SurveyQuestion set appId=?3,surveyId=?2 where id in(?1)")
	public int updateSurveyIdAppId(List<Long> questionIds,Long surveyId,Long appId);
	
	@Query(value="from SurveyQuestion where surveyId = ?1 and status=3 ")
	public List<SurveyQuestion> findBySurveyId(Long surveyId);
}
