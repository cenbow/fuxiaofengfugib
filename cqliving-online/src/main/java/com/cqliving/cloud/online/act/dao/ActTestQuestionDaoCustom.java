package com.cqliving.cloud.online.act.dao;

import java.util.List;

import com.cqliving.cloud.online.act.domain.ActTestQuestionDto;

/**
 * 活动答题分类问题表 JPA Dao
 * Date: 2016-06-07 09:22:55
 * @author Code Generator
 */
public interface ActTestQuestionDaoCustom {
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月27日
	 * @param classifyId
	 * @return
	 */
	public List<ActTestQuestionDto> getListAndAnswer(Long classifyId, Long userId);
	
	/**
	 * Title:获得分类下问题的正确答案集合
	 * <p>Description:主要是用于计算答题的分值</p>
	 * @author DeweiLi on 2016年6月27日
	 * @param classifyId
	 * @return
	 */
	public List<ActTestQuestionDto> getQuestionRightAnswer(Long classifyId);
	
	/**
	 * Title:验证问题答题下是否有没设置正确答案的问题
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月27日
	 * @param actInfoListId
	 * @return
	 */
	public List<ActTestQuestionDto> validateNullAnswer(Long actInfoListId);
}
