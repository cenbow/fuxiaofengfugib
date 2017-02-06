package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserFeedback;
import com.cqliving.cloud.online.account.dto.UserFeedbackDto;
import com.cqliving.cloud.online.account.manager.UserFeedbackManager;
import com.cqliving.cloud.online.account.service.UserFeedbackService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.FeedbackData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userFeedbackService")
@ServiceHandleMapping(managerClass = UserFeedbackManager.class)
public class UserFeedbackServiceImpl implements UserFeedbackService {

	private static final Logger logger = LoggerFactory.getLogger(UserFeedbackServiceImpl.class);
	
	@Autowired
	private UserFeedbackManager userFeedbackManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询意见反馈表失败")})
	public Response<PageInfo<UserFeedback>> queryForPage(PageInfo<UserFeedback> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询意见反馈表失败")})
	public Response<UserFeedback> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除意见反馈表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除意见反馈表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存意见反馈表失败")})
	public Response<Void> save(UserFeedback userFeedback) {
		return null;
	}

	@Override
	public Response<Void> add(Long appId, String sessionId, String token, String title, String content, String images) {
		Response<Void> response = Response.newInstance();
		try {
			 userFeedbackManager.add(appId, sessionId, token, title, content, images);
		} catch (BusinessException e) {
			logger.error("保存留言失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("保存留言失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("保存留言失败");
		}
		return response;
	}

	@Override
	public Response<CommonListResult<FeedbackData>> queryForScrollPage(Long appId, String sessionId, String token, Long lastId) {
		Response<CommonListResult<FeedbackData>> response = Response.newInstance();
		try {
			CommonListResult<FeedbackData> data = userFeedbackManager.queryForScrollPage(appId, sessionId, token, lastId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取反馈分页列表失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取反馈分页列表失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取反馈分页列表失败");
		}
		return response;
	}

    @Override
    @ServiceMethodHandle(managerMethodName="queryByPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询意见反馈失败")})
    public Response<PageInfo<UserFeedbackDto>> queryByPage(PageInfo<UserFeedbackDto> pageInfo,
            Map<String, Object> conditions, Map<String, Boolean> orders) {
        return null;
    }
    
    /**
     * <p>Description:通过id查询</p>
     * @author huxiaoping on 2016年5月28日
     * @param id
     * @return
     */
    @Override
    @ServiceMethodHandle(managerMethodName="getById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="通过id查询失败")})
    public Response<UserFeedbackDto> getById(Long id){
        return null;
    }
    
    /**
     * 回复
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月28日下午5:13:25
     */
    @Override
    @ServiceMethodHandle(managerMethodName="reply",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="回复失败")})
    public Response<Void> reply(UserFeedback userFeedback){
        return null;
    }
}