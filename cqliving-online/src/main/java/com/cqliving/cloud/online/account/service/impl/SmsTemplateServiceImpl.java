package com.cqliving.cloud.online.account.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.SmsTemplate;
import com.cqliving.cloud.online.account.manager.SmsTemplateManager;
import com.cqliving.cloud.online.account.service.SmsTemplateService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("smsTemplateService")
@ServiceHandleMapping(managerClass = SmsTemplateManager.class)
public class SmsTemplateServiceImpl implements SmsTemplateService {
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询短信模版表失败")})
	public Response<PageInfo<SmsTemplate>> queryForPage(PageInfo<SmsTemplate> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询短信模版表失败")})
	public Response<SmsTemplate> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除短信模版表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存短信模版表失败")})
	public Response<Void> save(SmsTemplate smsTemplate) {
		return null;
	}
	
	/**
     * <p>Description: 获取短信模板通过appId</p>
     * @author huxiaoping on 2016年6月1日
     * @param appId
     * @return
     */
	@Override
	@ServiceMethodHandle(managerMethodName="getByAppId",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="通过appId获取短信模板失败")})
    public Response<List<SmsTemplate>> getByAppId(Long appId){
        return null;
    }
}
