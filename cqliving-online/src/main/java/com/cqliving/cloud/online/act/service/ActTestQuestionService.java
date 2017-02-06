package com.cqliving.cloud.online.act.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActTestQuestion;
import com.cqliving.cloud.online.act.domain.ActTestQuestionDto;
import com.cqliving.cloud.online.act.dto.ActTestQuestionDtoResult;
import com.cqliving.cloud.online.act.dto.ActTestQuestionExcelOption;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 活动答题分类问题表 Service
 * Date: 2016-06-07 09:22:55
 * @author Code Generator
 */
public interface ActTestQuestionService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActTestQuestion>> queryForPage(PageInfo<ActTestQuestion> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActTestQuestion> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ActTestQuestion domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:问题排序和答案排序
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月17日
	 * @param questionList
	 * @param answerList
	 * @return
	 */
	Response<Void> sort(String questionList, String answerList);
	
	/**
	 * Title:excel导入
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月18日
	 * @param classifyId
	 * @param content
	 * @param list
	 * @return
	 */
	Response<Void> excelImport(Long classifyId, String content, List<ActTestQuestionExcelOption> list);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月27日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param classifyId
	 * @return
	 */
	Response<ActTestQuestionDtoResult> getQuestionAndAnswer(String sessionId, String token, Long classifyId, Integer type);
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月27日
	 * @param actInfoListId
	 * @return
	 */
	public Response<List<ActTestQuestionDto>> validateNullAnswer(Long actInfoListId);
}
