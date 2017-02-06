package com.cqliving.cloud.online.survey.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswerAttach;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 调研问题表答案附加属性 JPA Dao
 * Date: 2016-04-15 09:46:59
 * @author Code Generator
 */
public interface SurveyQuestionAnswerAttachDao extends EntityJpaDao<SurveyQuestionAnswerAttach, Long> {

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年5月12日
	 * @param answerId
	 * @return
	 */
	@Query("from SurveyQuestionAnswerAttach where answerId = ?1 order by sortNo")
	List<SurveyQuestionAnswerAttach> getByAnswer(Long answerId);

}
