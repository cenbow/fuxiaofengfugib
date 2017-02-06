package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserMessageNum;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserMessageNumManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.account.service.UserMessageNumService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userMessageNumService")
@ServiceHandleMapping(managerClass = UserMessageNumManager.class)
public class UserMessageNumServiceImpl implements UserMessageNumService {

	private static final Logger logger = LoggerFactory.getLogger(UserMessageNumServiceImpl.class);
	
	@Autowired
	private UserMessageNumManager userMessageNumManager;
	@Autowired
	private UserSessionManager userSessionManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户消息通知数量表失败")})
	public Response<PageInfo<UserMessageNum>> queryForPage(PageInfo<UserMessageNum> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户消息通知数量表失败")})
	public Response<UserMessageNum> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户消息通知数量表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户消息通知数量表失败")})
	public Response<Void> save(UserMessageNum userMessageNum) {
		return null;
	}
	
	/**
	 * Title:用户信息验证并返回
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月12日
	 * @param token
	 * @return
	 */
	private UserSession validateUserSession(String token){
	    UserSession userSession = userSessionManager.getByToken(token);
        if(userSession == null){
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(),
                    ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        return userSession;
	}
	
    @Override
    public Response<Integer> getNum(Long appId, String token, Byte type) {
        Response<Integer> rs = Response.newInstance();
        try {
            UserSession userSession = this.validateUserSession(token);
            
            UserMessageNum userMessageNum = userMessageNumManager.getNum(appId, userSession.getUserId(), type);
            if(userMessageNum != null){
                rs.setData(userMessageNum.getQuantity());
            }else{
                rs.setData(0);
            }
        } catch (BusinessException e) {
            logger.error("获取用户通知数量失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("获取用户通知数量失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取用户通知数量失败");
        }
        return rs;
    }

    @Override
    public Response<Void> modifyNum(Long appId, String token, Byte type, Integer num) {
        Response<Void> rs = Response.newInstance();
        try {
            UserSession userSession = this.validateUserSession(token);
            userMessageNumManager.setMessageNum(appId, type, userSession.getUserId(), num);
        } catch (BusinessException e) {
            logger.error("用户通知数量修改失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("用户通知数量修改失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("用户通知数量修改失败");
        }
        return rs;
    }
}
