package com.cqliving.cloud.online.recruitinfo.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.recruitinfo.domain.RecruitInfo;
import com.cqliving.cloud.online.recruitinfo.manager.RecruitInfoManager;
import com.cqliving.cloud.online.recruitinfo.service.RecruitInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("recruitInfoService")
@ServiceHandleMapping(managerClass = RecruitInfoManager.class)
public class RecruitInfoServiceImpl implements RecruitInfoService {

	private static final Logger logger = LoggerFactory.getLogger(RecruitInfoServiceImpl.class);
	
	@Autowired
	private RecruitInfoManager recruitInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询人才招聘表失败")})
	public Response<PageInfo<RecruitInfo>> queryForPage(PageInfo<RecruitInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询人才招聘表失败")})
	public Response<RecruitInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除人才招聘表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除人才招聘表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存人才招聘表失败")})
	public Response<Void> save(RecruitInfo recruitInfo) {
		return null;
	}

    @Override
    @ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存人才招聘表失败")})
    public Response<Void> save(RecruitInfo domain, String[] urls) {
        return null;
    }

    /**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param ids
     * @return
     */
    @Override
    @ServiceMethodHandle(managerMethodName="updateSortNo",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改排序号失败")})
    public Response<Void> updateSortNo(Integer sortNo, String updator, Long updateUserId, Long... ids) {
        return null;
    }

	@Override
	public Response<ScrollPage<RecruitInfo>> queryForScrollPage(ScrollPage<RecruitInfo> scrollPage, Map<String, Object> conditions) {
		Response<ScrollPage<RecruitInfo>> response = Response.newInstance();
		try {
			ScrollPage<RecruitInfo> data = recruitInfoManager.queryForScrollPage(scrollPage, conditions);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取招聘职位列表失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取招聘职位列表失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取招聘职位列表失败");
		}
		return response;
	}
	
}