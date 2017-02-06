package com.cqliving.cloud.online.actcustom.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.actcustom.domain.ActCustomVoteItem;
import com.cqliving.cloud.online.actcustom.domain.UserActCustomVote;
import com.cqliving.cloud.online.actcustom.dto.ActCustomQrcodeColumnDto;
import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteDto;
import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteItemDto;
import com.cqliving.cloud.online.actcustom.manager.ActCustomVoteItemManager;
import com.cqliving.cloud.online.actcustom.service.ActCustomVoteItemService;
import com.cqliving.cloud.online.app.domain.AppMarketplaceResource;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.framework.common.exception.BusinessException;

@Service("actCustomVoteItemService")
@ServiceHandleMapping(managerClass = ActCustomVoteItemManager.class)
public class ActCustomVoteItemServiceImpl implements ActCustomVoteItemService {

	private static final Logger logger = LoggerFactory.getLogger(ActCustomVoteItemServiceImpl.class);
	
	@Autowired
	private ActCustomVoteItemManager actCustomVoteItemManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询自定义活动投票选项表失败")})
	public Response<PageInfo<ActCustomVoteItem>> queryForPage(PageInfo<ActCustomVoteItem> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询自定义活动投票选项表失败")})
	public Response<ActCustomVoteItem> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除自定义活动投票选项表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除自定义活动投票选项表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存自定义活动投票选项表失败")})
	public Response<Void> save(ActCustomVoteItem actCustomVoteItem) {
		return null;
	}
	@Override
	public Response<ActCustomVoteItem> acteVoteDetail(String token, String sessionId,
			Long actCustomVoteItemId) {
		Response<ActCustomVoteItem> response = Response.newInstance();
		try {
			ActCustomVoteItem data = actCustomVoteItemManager.queryActeVoteDetail(actCustomVoteItemId, sessionId, token);
			if(data!=null){
				response.setData(data);
			}
			
		} catch (BusinessException e) {
			logger.error("获取投票项详情信息失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取投票项详情信息失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取投票项详情信息失败");
		}
		return response;
	}

	@Override
	public Response<CommonListResult<ActCustomVoteItemDto>> getColumnsByAcode(String actQrcodeCode, String itemTitle,Long lastId,Long ranking) {
		Response<CommonListResult<ActCustomVoteItemDto>> response = Response.newInstance();
		try {
			CommonListResult<ActCustomVoteItemDto> data = actCustomVoteItemManager.getActCustomByAcode(actQrcodeCode, itemTitle,lastId,ranking);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取投票项列表失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取投票项列表失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取投票项列表失败");
		}
		return response;
	}

	@Override
	public Response<ActCustomVoteDto> acteDetail(String token, String sessionId, String actQrcodeCode) {
		Response<ActCustomVoteDto> response = Response.newInstance();
		try {
			ActCustomVoteDto data = actCustomVoteItemManager.queryActeDetail(actQrcodeCode);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取活动详情失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取活动详情失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取活动详情失败");
		}
		return response;
	}
}
