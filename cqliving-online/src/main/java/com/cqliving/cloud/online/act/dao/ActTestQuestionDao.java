package com.cqliving.cloud.online.act.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.act.domain.ActTestQuestion;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 活动答题分类问题表 JPA Dao
 * Date: 2016-06-07 09:22:55
 * @author Code Generator
 */
public interface ActTestQuestionDao extends EntityJpaDao<ActTestQuestion, Long>, ActTestQuestionDaoCustom {
	
	/**
	 * Title:排序
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月17日
	 * @param id
	 * @param classifyId
	 * @param sortNum
	 * @return
	 */
	@Modifying
    @Query(value = "update ActTestQuestion set sortNo=?3 where id=?1 and actTestClassifyId=?2")
    int sort(Long id, Long classifyId, Integer sortNum);
}
