package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.dto.UserInfoReplyDto;
import com.cqliving.cloud.online.account.manager.UserInfoReplyManager;
import com.cqliving.cloud.online.account.service.UserInfoReplyService;
import com.cqliving.cloud.online.interfacc.dto.CommentsData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.MyCommentsData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userInfoReplyService")
@ServiceHandleMapping(managerClass = UserInfoReplyManager.class)
public class UserInfoReplyServiceImpl implements UserInfoReplyService {

	private static final Logger logger = LoggerFactory.getLogger(UserInfoReplyServiceImpl.class);
	
	@Autowired
	private UserInfoReplyManager userInfoReplyManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户资讯回复表失败")})
	public Response<PageInfo<UserInfoReply>> queryForPage(PageInfo<UserInfoReply> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户资讯回复表失败")})
	public Response<UserInfoReply> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户资讯回复表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户资讯回复表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="auditing",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="审核失败")})
	public Response<Void> auditing(Byte status,String auditingContent,Long auditingId,String auditingtor,Long[] sourceIds,Byte sourceType,Long... ids){
	    return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户资讯回复表失败")})
	public Response<Void> save(UserInfoReply userInfoReply) {
		return null;
	}

	@Override
	public Response<CommonListResult<CommentsData>> getPageBySourceId(Long appId, String sessionId, String token, Long sourceId, Byte sourceType, Long lastId) {
		Response<CommonListResult<CommentsData>> response = Response.newInstance();
		try {
			CommonListResult<CommentsData> data = userInfoReplyManager.getPageBySourceId(appId, sessionId, token, sourceId, sourceType, lastId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取评论列表失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取评论列表失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取评论列表失败");
		}
		return response;
	}

	@Override
	public Response<CommonListResult<MyCommentsData>> getPageByUser(Long appId, String sessionId, String token, Integer type, Long lastReplyId, Byte sourceType) {
		Response<CommonListResult<MyCommentsData>> response = Response.newInstance();
		try {
			CommonListResult<MyCommentsData> data = userInfoReplyManager.getPageByUser(appId, sessionId, token, type, lastReplyId, sourceType);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取我的评论失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取我的评论失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取我的评论失败");
		}
		return response;
	}

    @Override
    @ServiceMethodHandle(managerMethodName="queryByPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询回复表失败")})
    public Response<PageInfo<UserInfoReplyDto>> queryByPage(PageInfo<UserInfoReplyDto> pageInfo,
            Map<String, Object> conditions, Map<String, Boolean> orders,Byte sourceType) {
        return null;
    }

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.service.UserInfoReplyService#queryScrollPage(com.cqliving.framework.common.dao.support.ScrollPage, java.util.Map, java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<ScrollPage<UserInfoReplyDto>> queryScrollPage(ScrollPage<UserInfoReplyDto> scrollPage,Map<String, Object> conditions, Byte sourceType) {
		return null;
	}

	@Override
	public Response<Void> add(Long appId, String sessionId, String token, Long sourceId, Byte sourceType, String place,
			String lng, String lat, Long replyId, String passiveReplyName, Long passiveReplyId, String content, Boolean isAnonymous) {
		Response<Void> response = Response.newInstance();
		try {
			Byte status = userInfoReplyManager.add(appId, sessionId, token, sourceId, sourceType, place, lng, lat, replyId, passiveReplyName, passiveReplyId, content, isAnonymous);
			response.setMessage(UserInfoReply.STATUS3.equals(status) ? "保存评论成功" : "保存评论成功，请等待审核通过");
			response.setCode(UserInfoReply.STATUS3.equals(status) ? 0 : 1);
		} catch (BusinessException e) {
			logger.error("保存评论失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("保存评论失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("保存评论失败");
		}
		return response;
	}

    @Override
    public Response<CommonListResult<MyCommentsData>> queryWzScrollPage(Long appId, String sessionId, String token, Integer type, Byte sourceType, Long lastId) {
        Response<CommonListResult<MyCommentsData>> rs = Response.newInstance();
        try {
            rs.setData(userInfoReplyManager.queryWzScrollPage(appId, sessionId, token, type, sourceType, lastId));
        } catch (Exception e) {
            logger.error("我的评论分页查询失败", e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("我的评论分页查询失败");
        }
        return rs;
    }

	@Override
	public Response<Integer> getReplyCount(Long appId, Long sourceId, Byte sourceType) {
		Response<Integer> response = Response.newInstance();
		try {
			Integer data = userInfoReplyManager.getReplyCount(appId, sourceId, sourceType);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取评论数失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取评论数失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取评论数失败");
		}
		return response;
	}

	@Override
	public Response<Void> remove(Long appId, String sessionId, String token, Long userInfoReplyId) {
		Response<Void> response = Response.newInstance();
		try {
			userInfoReplyManager.remove(appId, sessionId, token, userInfoReplyId);
		} catch (BusinessException e) {
			logger.error("删除我的评论失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("删除我的评论失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("删除我的评论失败");
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.service.UserInfoReplyService#queryForTopicCommentsPage(com.cqliving.framework.common.dao.support.ScrollPage, java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<ScrollPage<UserInfoReplyDto>> queryForTopicCommentsPage(ScrollPage<UserInfoReplyDto> scrollPage,
			Map<String, Object> conditions) {
		return null;
	}
	
}
