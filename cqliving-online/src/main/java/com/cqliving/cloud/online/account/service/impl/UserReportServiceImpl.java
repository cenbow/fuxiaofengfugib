package com.cqliving.cloud.online.account.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserReport;
import com.cqliving.cloud.online.account.dto.UserReportDto;
import com.cqliving.cloud.online.account.manager.UserReportManager;
import com.cqliving.cloud.online.account.service.UserReportService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userReportService")
@ServiceHandleMapping(managerClass = UserReportManager.class)
public class UserReportServiceImpl implements UserReportService {

	private static final Logger logger = LoggerFactory.getLogger(UserReportServiceImpl.class);
	
	@Autowired
	private UserReportManager userReportManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户举报表失败")})
	public Response<PageInfo<UserReport>> queryForPage(PageInfo<UserReport> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户举报表失败")})
	public Response<UserReport> get(Long id) {
		return null;
	}
	
    @Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户举报表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户举报表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户举报表失败")})
	public Response<Void> save(UserReport userReport) {
		return null;
	}

	@Override
	public Response<Void> saveUserReport(Long appId, String sessionId, String token, String content, Long sourceId, Byte type, Byte sourceType, String reportCodes) {
		Response<Void> response = Response.newInstance();
        try {
            userReportManager.saveUserReport(appId, sessionId, token, content, sourceId, type, sourceType, reportCodes);
        } catch (BusinessException e) {
            logger.error("举报失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("举报失败：" + e.getMessage(), e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("举报失败");
        }
        return response;
	}
	
	/**
     * 获取单个举报信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月31日上午10:26:49
     */
    @Override
    @ServiceMethodHandle(managerMethodName="getByIdAndSourceType",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="通过id和sourceType查询用户举报信息失败")})
    public Response<UserReportDto> getByIdAndSourceType(Long id, Byte sourceType,Byte type) {
        return null;
    }
    
    /**
     * 审核
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月31日下午2:54:40
     */
    @Override
    @ServiceMethodHandle(managerMethodName="auditing",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="审核失败")})
    public Response<Void> auditing(UserReport report,Long... ids){
        return null;
    }

	@Override
	public Response<List<UserReport>> getByUserAndSourceId(Long appId, Long userId, Long sourceId, Byte sourceType) {
		Response<List<UserReport>> rs = Response.newInstance();
		try {
			rs.setData(userReportManager.getByUserAndSourceId(appId, userId, sourceId, sourceType));
		} catch (Exception e) {
			logger.debug("获得用户已经举报集合失败:" + e.getMessage());
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("获得用户已经举报集合失败");
		}
		return rs;
	}
}