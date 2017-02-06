package com.cqliving.cloud.online.act.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.act.domain.ActTestAnswer;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 活动答题分类答案表 JPA Dao
 * Date: 2016-06-07 09:22:20
 * @author Code Generator
 */
public interface ActTestAnswerDao extends EntityJpaDao<ActTestAnswer, Long> {
	
	/**
	 * Title:根据问题删除答案
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月16日
	 * @param questionIds
	 * @return
	 */
	@Modifying
	@Query("DELETE FROM ActTestAnswer WHERE actTestQuestionId in ?1")
	int deleteByQuestionId(List<Long> questionIds);
	
	/**
	 * Title:排序
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月17日
	 * @param id
	 * @param actTestQuestionId
	 * @param sortNum
	 * @return
	 */
	@Modifying
    @Query(value = "update ActTestAnswer set sortNo=?3 where id=?1 and actTestQuestionId=?2")
    int sort(Long id, Long actTestQuestionId, Integer sortNum);
}
