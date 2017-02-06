package com.cqliving.cloud.online.info.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InfoClassifyComment;
import com.cqliving.cloud.online.info.manager.InfoClassifyCommentManager;
import com.cqliving.cloud.online.info.service.InfoClassifyCommentService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("infoClassifyCommentService")
@ServiceHandleMapping(managerClass = InfoClassifyCommentManager.class)
public class InfoClassifyCommentServiceImpl implements InfoClassifyCommentService {

	private static final Logger logger = LoggerFactory.getLogger(InfoClassifyCommentServiceImpl.class);
	
	@Autowired
	private InfoClassifyCommentManager infoClassifyCommentManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯栏目推荐表失败")})
	public Response<PageInfo<InfoClassifyComment>> queryForPage(PageInfo<InfoClassifyComment> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯栏目推荐表失败")})
	public Response<InfoClassifyComment> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯栏目推荐表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯栏目推荐表失败")})
	public Response<Void> save(InfoClassifyComment infoClassifyComment) {
		return null;
	}

	@Override
	public Response<Void> ignore(Long id) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyCommentManager.ignore(id);
		} catch (Exception e) {
			logger.error("忽略操作失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("忽略操作失败");
		}
		return response;
	}
	
	@Override
	public Response<Void> publishToColumn(Long appId, Long infoClassifyId, Long id, Long appColumnId, String userName, Long userId) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyCommentManager.publishToColumn(appId, infoClassifyId, id, appColumnId, userName, userId);
		} catch (Exception e) {
			logger.error("发布到App失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("发布到App失败");
		}
		return response;
	}
	
}
