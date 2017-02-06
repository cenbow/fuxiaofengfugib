package com.cqliving.cloud.online.message.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.message.domain.MessageInfo;
import com.cqliving.cloud.online.message.dto.MessageInfoDto;
import com.cqliving.cloud.online.message.manager.MessageInfoManager;
import com.cqliving.cloud.online.message.service.MessageInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("messageInfoService")
@ServiceHandleMapping(managerClass = MessageInfoManager.class)
public class MessageInfoServiceImpl implements MessageInfoService {

	private static final Logger logger = LoggerFactory.getLogger(MessageInfoServiceImpl.class);
	
	@Autowired
	private MessageInfoManager messageInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询消息通知表失败")})
	public Response<PageInfo<MessageInfo>> queryForPage(PageInfo<MessageInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询消息通知表失败")})
	public Response<MessageInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除消息通知表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除消息通知表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存消息通知表失败")})
	public Response<Void> save(MessageInfo messageInfo) {
		return null;
	}

	@Override
	public Response<PageInfo<MessageInfoDto>> queryDtoForPage(PageInfo<MessageInfoDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		Response<PageInfo<MessageInfoDto>> response = Response.newInstance();
		try {
			PageInfo<MessageInfoDto> data = messageInfoManager.queryDtoForPage(pageInfo, searchMap, sortMap);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取通知分页数据失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取通知分页数据失败");
		}
		return response;
	}

	@Override
	public Response<Void> send(Long id, Long userId, String userName) {
		Response<Void> response = Response.newInstance();
		try {
			messageInfoManager.send(id, userId, userName);
		} catch (Exception e) {
			logger.error("通知发送失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("通知发送失败");
		}
		return response;
	}

	@Override
	public Response<Void> add(MessageInfo messageInfo, Long userId, String userName) {
		Response<Void> response = Response.newInstance();
		try {
			messageInfoManager.add(messageInfo, userId, userName);
		} catch (Exception e) {
			logger.error("新增通知失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("新增通知失败");
		}
		return response;
	}
	
	/**
     * <p>Description: 分页查询站内信</p>
     * @author huxiaoping on 2016年5月9日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
    @Override
    @ServiceMethodHandle(managerMethodName="queryLetterForPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询站内信失败")})
    public Response<PageInfo<MessageInfoDto>> queryLetterForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
            Map<String, Boolean> sortMap){
        return null;
    }
    
    /**
     * <p>Description: 分页查询公告</p>
     * @author huxiaoping on 2016年5月13日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
    @Override
    @ServiceMethodHandle(managerMethodName="queryNoticeForPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询公告失败")})
    public Response<PageInfo<MessageInfoDto>> queryNoticeForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
            Map<String, Boolean> sortMap){
        return null;
    }
    
    /**
     * <p>Description: 查询接收表站内信通过id</p>
     * @author huxiaoping on 2016年5月14日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
    @Override
    @ServiceMethodHandle(managerMethodName="getById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询站内信信息通过接受表id失败")})
    public Response<MessageInfoDto> getById(Long id){
        return null;
    }
}