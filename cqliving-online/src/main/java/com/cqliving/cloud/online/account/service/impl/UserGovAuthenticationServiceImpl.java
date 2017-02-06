package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.account.domain.UserGovAuthentication;
import com.cqliving.cloud.online.account.manager.UserGovAuthenticationManager;
import com.cqliving.cloud.online.account.service.UserGovAuthenticationService;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.framework.common.exception.BusinessException;

@Service("userGovAuthenticationService")
@ServiceHandleMapping(managerClass = UserGovAuthenticationManager.class)
public class UserGovAuthenticationServiceImpl implements UserGovAuthenticationService {

	private static final Logger logger = LoggerFactory.getLogger(UserGovAuthenticationServiceImpl.class);
	
	@Autowired
	private UserGovAuthenticationManager userGovAuthenticationManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户行政审批认证表失败")})
	public Response<PageInfo<UserGovAuthentication>> queryForPage(PageInfo<UserGovAuthentication> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户行政审批认证表失败")})
	public Response<UserGovAuthentication> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户行政审批认证表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户行政审批认证表失败")})
	public Response<Void> save(UserGovAuthentication userGovAuthentication) {
		return null;
	}

	/**
     * 获取认证信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月29日上午9:44:17
     */
    @Override
    public Response<UserGovAuthentication> get(String token, String sessionId, Long appId) {
        Response<UserGovAuthentication> response = Response.newInstance();
        try {
            UserGovAuthentication user = userGovAuthenticationManager.get(token, sessionId, appId);
            response.setData(user);
            response.setMessage("获取认证信息成功");
        } catch (BusinessException e) {
            logger.error("获取认证信息失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("获取认证信息失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("获取认证信息失败");
        }
        return response;
    }

    /**
     * 保存认证
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月29日上午10:49:04
     */
    @Override
    public Response<Void> save(String token, String sessionId, Long appId, String name, String idCard, Byte sex,
            String nation, String phone,String captcha) {
        Response<Void> response = Response.newInstance();
        try {
            userGovAuthenticationManager.save(token, sessionId, appId, name, idCard, sex,nation, phone,captcha);
            response.setMessage("保存认证信息成功");
        } catch (BusinessException e) {
            logger.error("保存认证信息失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("保存认证信息失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("保存认证信息失败");
        }
        return response;
    }
}
