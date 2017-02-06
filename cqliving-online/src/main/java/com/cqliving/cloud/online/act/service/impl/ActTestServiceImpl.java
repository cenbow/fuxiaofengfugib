package com.cqliving.cloud.online.act.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.ActTest;
import com.cqliving.cloud.online.act.domain.ActTestCollect;
import com.cqliving.cloud.online.act.manager.ActTestManager;
import com.cqliving.cloud.online.act.service.ActTestService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("actTestService")
@ServiceHandleMapping(managerClass = ActTestManager.class)
public class ActTestServiceImpl implements ActTestService {

	private static final Logger logger = LoggerFactory.getLogger(ActTestServiceImpl.class);
	
	@Autowired
	private ActTestManager actTestManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询活动答题表失败")})
	public Response<PageInfo<ActTest>> queryForPage(PageInfo<ActTest> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询活动答题表失败")})
	public Response<ActTest> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动答题表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动答题表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	
	@Override
	public Response<Void> save(ActTest actTest, Long[] classifyIds, Integer[] classifySortNos, List<ActTestCollect> actTestCollectList) {
		Response<Void> res = Response.newInstance();
		try {
			actTestManager.save(actTest, classifyIds, classifySortNos, actTestCollectList);
		} catch (Exception e) {
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("活动答题保存失败");
		}
		return res;
	}

	@Override
	public Response<ActTest> getByInfoList(Long actInfoId, Long actInfoListId) {
		Response<ActTest> res = Response.newInstance();
		try {
			res.setData(actTestManager.getByInfoList(actInfoId, actInfoListId));
		} catch (Exception e) {
			logger.error("获取活动答题失败:" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("获取活动答题失败");
		}
		return res;
	}
	
	@Override
	public Response<ActTest> getByActTestClassify(Long actTestClassifyId) {
		Response<ActTest> res = Response.newInstance();
		try {
			res.setData(actTestManager.getByActTestClassify(actTestClassifyId));
		} catch (Exception e) {
			logger.error("获取活动答题失败:" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("获取活动答题失败");
		}
		return res;
	}
}
