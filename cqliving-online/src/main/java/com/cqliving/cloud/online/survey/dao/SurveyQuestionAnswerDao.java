package com.cqliving.cloud.online.survey.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswer;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 问题表答案表 JPA Dao
 * Date: 2016-04-15 09:46:56
 * @author Code Generator
 */
public interface SurveyQuestionAnswerDao extends EntityJpaDao<SurveyQuestionAnswer, Long> {

	@Modifying
	@Query(value="update SurveyQuestionAnswer set status=99 where questionId=?1")
	public int deleteByQuesId(Long quesId);

	@Modifying
	@Query(value="update SurveyQuestionAnswer set status=?2 where id=?1")
	public int updateStatus(Long id, Byte status);
	/**
	 * <p>Description: 获取调研答案</p>
	 * @author Tangtao on 2016年5月12日
	 * @param questionId
	 * @return
	 */
	@Query("from SurveyQuestionAnswer where questionId = ?1 and status=3 order by sortNo")
	List<SurveyQuestionAnswer> getByQuestion(Long questionId);
	
	@Modifying
	@Query(value="update SurveyQuestionAnswer set actValue = ifnull(actValue,0) + 1 where id in(?1)")
	public int addActValue(List<Long> answerIds);
}
