package com.cqliving.cloud.online.building.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.building.domain.BuildingImage;
import com.cqliving.cloud.online.building.manager.BuildingImageManager;
import com.cqliving.cloud.online.building.service.BuildingImageService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("buildingImageService")
@ServiceHandleMapping(managerClass = BuildingImageManager.class)
public class BuildingImageServiceImpl implements BuildingImageService {

	private static final Logger logger = LoggerFactory.getLogger(BuildingImageServiceImpl.class);
	
	@Autowired
	private BuildingImageManager buildingImageManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询楼房图片表失败")})
	public Response<PageInfo<BuildingImage>> queryForPage(PageInfo<BuildingImage> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询楼房图片表失败")})
	public Response<BuildingImage> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除楼房图片表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除楼房图片表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存楼房图片表失败")})
	public Response<Void> save(BuildingImage buildingImage) {
		return null;
	}

	@Override
	public Response<List<BuildingImage>> getByBuilding(Long buildingId, Byte type) {
		Response<List<BuildingImage>> res = Response.newInstance();
		try {
			res.setData(buildingImageManager.getByBuilding(buildingId, type));
		} catch (BusinessException e) {
			logger.error("获取置业图片失败：", e.getMessage(), e);
			res.setCode(e.getErrorCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取置业图片失败：", e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("获取置业图片失败");
		}
		return res;
	}
}
