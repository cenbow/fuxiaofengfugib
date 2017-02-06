package com.cqliving.cloud.online.recruitinfo.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.recruitinfo.domain.RecruitImage;
import com.cqliving.cloud.online.recruitinfo.manager.RecruitImageManager;
import com.cqliving.cloud.online.recruitinfo.service.RecruitImageService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("recruitImageService")
@ServiceHandleMapping(managerClass = RecruitImageManager.class)
public class RecruitImageServiceImpl implements RecruitImageService {

	private static final Logger logger = LoggerFactory.getLogger(RecruitImageServiceImpl.class);
	
//	@Autowired
//	private RecruitImageManager recruitImageManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询人才招聘图片表失败")})
	public Response<PageInfo<RecruitImage>> queryForPage(PageInfo<RecruitImage> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询人才招聘图片表失败")})
	public Response<RecruitImage> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除人才招聘图片表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除人才招聘图片表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存人才招聘图片表失败")})
	public Response<Void> save(RecruitImage recruitImage) {
		return null;
	}

    @Override
    @ServiceMethodHandle(managerMethodName="getByRecruitInfoId",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="通过招聘信息查找图片失败")})
    public Response<List<RecruitImage>> getByRecruitInfoId(Long recruitInfoId) {
        return null;
    }
}
