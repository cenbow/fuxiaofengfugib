package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.account.service.UserSessionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userSessionService")
@ServiceHandleMapping(managerClass = UserSessionManager.class)
public class UserSessionServiceImpl implements UserSessionService {

	//private static final Logger logger = LoggerFactory.getLogger(UserSessionServiceImpl.class);
	
	@Autowired
	private UserSessionManager userSessionManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户APP当前登录信息表失败")})
	public Response<PageInfo<UserSession>> queryForPage(PageInfo<UserSession> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户APP当前登录信息表失败")})
	public Response<UserSession> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户APP当前登录信息表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户APP当前登录信息表失败")})
	public Response<Void> save(UserSession userSession) {
		return null;
	}

	@Override
    @ServiceMethodHandle(managerMethodName="getByToken",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="获取当前登录信息表失败")})
    public Response<UserSession> getByToken(String token) {
	    Response<UserSession> rs = Response.newInstance();
        try {
            rs.setData(userSessionManager.getByToken(token));
        } catch (Exception e) {
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取当前登录信息表失败");
        }
        return rs;
    }
	
	@Override
	@ServiceMethodHandle(managerMethodName="getTourist",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="获取当前游客登录信息表失败")})
	public Response<UserSession> getTourist(String sessionId) {
	    Response<UserSession> rs = Response.newInstance();
	    try {
	        rs.setData(userSessionManager.getTourist(sessionId));
	    } catch (Exception e) {
	        rs.setCode(ErrorCodes.FAILURE);
	        rs.setMessage("获取当前游客登录信息表失败");
	    }
	    return rs;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.service.UserSessionService#get(java.lang.String, java.lang.String)
	 */
	@Override
	@ServiceMethodHandle
	public Response<UserSession> get(String sessionId, String token) {
		return null;
	}
}
