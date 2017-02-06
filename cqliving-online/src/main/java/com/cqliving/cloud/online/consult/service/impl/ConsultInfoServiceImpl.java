package com.cqliving.cloud.online.consult.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.consult.domain.ConsultInfo;
import com.cqliving.cloud.online.consult.dto.ConsultInfoDto;
import com.cqliving.cloud.online.consult.manager.ConsultInfoManager;
import com.cqliving.cloud.online.consult.service.ConsultInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("consultInfoService")
@ServiceHandleMapping(managerClass = ConsultInfoManager.class)
public class ConsultInfoServiceImpl implements ConsultInfoService {

	private static final Logger logger = LoggerFactory.getLogger(ConsultInfoServiceImpl.class);
	
	@Autowired
	private ConsultInfoManager consultInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询工商联咨询表失败")})
	public Response<PageInfo<ConsultInfo>> queryForPage(PageInfo<ConsultInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询工商联咨询表失败")})
	public Response<ConsultInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除工商联咨询表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除工商联咨询表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		logger.info("删除操作");
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存工商联咨询表失败")})
	public Response<Void> save(ConsultInfo consultInfo) {
		return null;
	}

	/**
     * 我要咨询列表(滚动分页)
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月29日下午3:11:59
     */
    @Override
    public Response<ScrollPage<ConsultInfoDto>> queryConsultScrollPage(ScrollPage<ConsultInfoDto> page,
            Map<String, Object> conditions, String sessionId, String token) {
        Response<ScrollPage<ConsultInfoDto>> response = Response.newInstance();
        try {
            ScrollPage<ConsultInfoDto> data = consultInfoManager.queryConsultScrollPage(page, conditions, sessionId, token);
            response.setData(data);
        } catch (BusinessException e) {
            logger.error("滚动分页查询咨询列表失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("滚动分页查询咨询列表失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("分页查询咨询列表失败");
        }
        return response;
    }

    @Override
    public Response<Boolean> saveConsultInfo(Long appId, String type, String content, String enterpriseName,
            String linkmanName, String linkmanPhone, String token, String sessionId,String captcha) {
        Response<Boolean> response = Response.newInstance();
        try {
            consultInfoManager.saveConsultInfo(appId, type, content, enterpriseName, linkmanName, linkmanPhone, token, sessionId,captcha);
            response.setData(true);
            response.setMessage("提交咨询成功！");
        } catch (BusinessException e) {
            logger.error("保存咨询失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("保存资讯失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("保存咨询失败");
        }
        return response;
    }

    /**
     * 回复
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月1日下午2:38:57
     */
    @Override
    public Response<Void> reply(ConsultInfo domain) {
        Response<Void> response = Response.newInstance();
        try {
            consultInfoManager.reply(domain);
            response.setMessage("回复成功！");
        } catch (BusinessException e) {
            logger.error("回复失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("回复失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("回复失败");
        }
        return response;
    }
}
