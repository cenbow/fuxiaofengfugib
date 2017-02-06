package com.cqliving.cloud.online.act.manager;

import java.util.List;

import com.cqliving.cloud.online.act.domain.ActTestQuestion;
import com.cqliving.cloud.online.act.domain.ActTestQuestionDto;
import com.cqliving.cloud.online.act.dto.ActTestQuestionDtoResult;
import com.cqliving.cloud.online.act.dto.ActTestQuestionExcelOption;
import com.cqliving.framework.common.service.EntityService;

/**
 * 活动答题分类问题表 Manager
 * Date: 2016-06-07 09:22:55
 * @author Code Generator
 */
public interface ActTestQuestionManager extends EntityService<ActTestQuestion> {
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月17日
	 * @param ids
	 */
	void deletes(Long...ids);

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月17日
	 * @param questionListStr
	 */
	void sort(String questionListStr);
	
	/**
	 * Title:excel导入
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月18日
	 * @param classifyId
	 * @param content
	 * @param list
	 */
	void excelImport(Long classifyId, String content, List<ActTestQuestionExcelOption> list);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月27日
	 * @param sessionId
	 * @param token
	 * @param classifyId
	 * @param type
	 * @return
	 */
	ActTestQuestionDtoResult getQuestionAndAnswer(String sessionId, String token, Long classifyId, Integer type);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月27日
	 * @param actInfoListId
	 * @return
	 */
	List<ActTestQuestionDto> validateNullAnswer(Long actInfoListId);
}
