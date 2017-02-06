package com.cqliving.cloud.online.wz.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.wz.domain.WzUser;
import com.cqliving.cloud.online.wz.manager.WzUserManager;
import com.cqliving.cloud.online.wz.service.WzUserService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("wzUserService")
@ServiceHandleMapping(managerClass = WzUserManager.class)
public class WzUserServiceImpl implements WzUserService {

	private static final Logger logger = LoggerFactory.getLogger(WzUserServiceImpl.class);
	
	@Autowired
	private WzUserManager wzUserManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询子帐号列表失败")})
	public Response<PageInfo<WzUser>> queryForPage(PageInfo<WzUser> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询子帐号列表失败")})
	public Response<WzUser> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存子帐号列表失败")})
	public Response<Void> save(WzUser wzUser) {
		return null;
	}

    @Override
    public Response<List<WzUser>> getByAppId(Long appId) {
        Response<List<WzUser>> rs = Response.newInstance();
        try {
            rs.setData(wzUserManager.getByAppId(appId));
        } catch (Exception e) {
            logger.error("获取问政详情失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取子帐号失败");
            return rs;
        }
        return rs;
    }
}
