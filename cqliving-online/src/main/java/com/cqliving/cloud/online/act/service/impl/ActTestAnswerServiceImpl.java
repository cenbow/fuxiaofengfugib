package com.cqliving.cloud.online.act.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.ActTestAnswer;
import com.cqliving.cloud.online.act.dto.ActAnswerResult;
import com.cqliving.cloud.online.act.manager.ActTestAnswerManager;
import com.cqliving.cloud.online.act.service.ActTestAnswerService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("actTestAnswerService")
@ServiceHandleMapping(managerClass = ActTestAnswerManager.class)
public class ActTestAnswerServiceImpl implements ActTestAnswerService {

	private static final Logger logger = LoggerFactory.getLogger(ActTestAnswerServiceImpl.class);
	
	@Autowired
	private ActTestAnswerManager actTestAnswerManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询活动答题分类答案表失败")})
	public Response<PageInfo<ActTestAnswer>> queryForPage(PageInfo<ActTestAnswer> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询活动答题分类答案表失败")})
	public Response<ActTestAnswer> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动答题分类答案表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存活动答题分类答案表失败")})
	public Response<Void> save(ActTestAnswer actTestAnswer) {
		return null;
	}
	
	@Override
	public Response<Void> saveAndModify(ActTestAnswer actTestAnswer){
		Response<Void> res = Response.newInstance();
		try {
			actTestAnswerManager.saveAndModify(actTestAnswer);
		} catch (BusinessException e) {
			logger.error("保存活动答题分类答案表失败：" + e.getMessage(), e);
			res.setCode(e.getErrorCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("保存活动答题分类答案表失败：" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("保存活动答题分类答案表失败");
		}
		return res;
	}

	@Override
	public Response<ActAnswerResult> answer(String sessionId, String token, Long actInfoListId, String isLoad) {
		Response<ActAnswerResult> res = Response.newInstance();
		try {
			res.setData(actTestAnswerManager.answer( sessionId, token, actInfoListId, isLoad));
		} catch (BusinessException e) {
			logger.error("获取答题详情错误：" + e.getMessage(), e);
			res.setCode(e.getErrorCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取答题详情错误：" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("获取答题详情错误");
		}
		return res;
	}
}
