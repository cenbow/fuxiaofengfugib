package com.cqliving.cloud.online.act.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.ActTestQuestion;
import com.cqliving.cloud.online.act.domain.ActTestQuestionDto;
import com.cqliving.cloud.online.act.dto.ActTestQuestionDtoResult;
import com.cqliving.cloud.online.act.dto.ActTestQuestionExcelOption;
import com.cqliving.cloud.online.act.manager.ActTestAnswerManager;
import com.cqliving.cloud.online.act.manager.ActTestQuestionManager;
import com.cqliving.cloud.online.act.service.ActTestQuestionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("actTestQuestionService")
@ServiceHandleMapping(managerClass = ActTestQuestionManager.class)
public class ActTestQuestionServiceImpl implements ActTestQuestionService {

	private static final Logger logger = LoggerFactory.getLogger(ActTestQuestionServiceImpl.class);
	
	@Autowired
	private ActTestQuestionManager actTestQuestionManager;
	@Autowired
	private ActTestAnswerManager actTestAnswerManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询活动答题分类问题表失败")})
	public Response<PageInfo<ActTestQuestion>> queryForPage(PageInfo<ActTestQuestion> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询活动答题分类问题表失败")})
	public Response<ActTestQuestion> get(Long id) {
		return null;
	}
	
	@Override
	public Response<Void> delete(Long... ids) {
		Response<Void> rs = Response.newInstance();
		try {
			actTestQuestionManager.deletes(ids);
		} catch (BusinessException e) {
			logger.error("删除活动答题分类问题表失败" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("删除活动答题分类问题表失败" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("删除活动答题分类问题表失败");
		}
		return rs;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存活动答题分类问题表失败")})
	public Response<Void> save(ActTestQuestion actTestQuestion) {
		return null;
	}

	@Override
	public Response<Void> sort(String questionList, String answerList) {
		Response<Void> rs = Response.newInstance();
		try {
			actTestQuestionManager.sort(questionList);
			actTestAnswerManager.sort(answerList);
		} catch (BusinessException e) {
			logger.error("活动问题排序失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("活动问题排序失败" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("活动问题排序失败");
		}
		return rs;
	}

	@Override
	public Response<Void> excelImport(Long classifyId, String content, List<ActTestQuestionExcelOption> list) {
		Response<Void> rs = Response.newInstance();
		try {
			actTestQuestionManager.excelImport(classifyId, content, list);
		} catch (BusinessException e) {
			logger.error("活动问题excel导入失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("活动问题excel导入失败" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("活动问题excel导入失败");
		}
		return rs;
	}

	@Override
	public Response<ActTestQuestionDtoResult> getQuestionAndAnswer(String sessionId, String token, Long classifyId, Integer type) {
		Response<ActTestQuestionDtoResult> rs = Response.newInstance();
		try {
			rs.setData(actTestQuestionManager.getQuestionAndAnswer(sessionId, token, classifyId, type));
		} catch (BusinessException e) {
			logger.error("获得问题和答案以及用户答题数据失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获得问题和答案以及用户答题数据失败" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("获得问题和答案以及用户答题数据失败");
		}
		return rs;
	}

	@Override
	public Response<List<ActTestQuestionDto>> validateNullAnswer(Long actInfoListId) {
		Response<List<ActTestQuestionDto>> rs = Response.newInstance();
		try {
			rs.setData(actTestQuestionManager.validateNullAnswer(actInfoListId));
		}catch (Exception e) {
			logger.error("验证问题是否设置正确答案失败" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("验证问题是否设置正确答案失败");
		}
		return rs;
	}
}
