package com.cqliving.cloud.online.building.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.building.domain.BuildingInfo;
import com.cqliving.cloud.online.building.dto.BuildingInfoDto;
import com.cqliving.cloud.online.building.manager.BuildingInfoManager;
import com.cqliving.cloud.online.building.service.BuildingInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("buildingInfoService")
@ServiceHandleMapping(managerClass = BuildingInfoManager.class)
public class BuildingInfoServiceImpl implements BuildingInfoService {

	private static final Logger logger = LoggerFactory.getLogger(BuildingInfoServiceImpl.class);
	
	@Autowired
	private BuildingInfoManager buildingInfoManager;
	
	public Response<PageInfo<BuildingInfo>> queryForPage(PageInfo<BuildingInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		Response<PageInfo<BuildingInfo>> rs = Response.newInstance();
		try {
			rs.setData(buildingInfoManager.query(pageInfo, map));
		} catch (BusinessException e) {
			logger.error("置业列表获取失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("置业列表获取失败:" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("置业列表获取失败");
		}
		return rs;
	}

	@Override
	public Response<BuildingInfo> get(Long id) {
		Response<BuildingInfo> rs = Response.newInstance();
		try {
			rs.setData(buildingInfoManager.get(id));
		} catch (BusinessException e) {
			logger.error("查询楼房信息表失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询楼房信息表失败:" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("查询楼房信息表失败");
		}
		return rs;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除楼房信息表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除楼房信息表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存楼房信息表失败")})
	public Response<Void> save(BuildingInfo buildingInfo, String[] images, String[] descType, String[] descArea, Long userId, String userName) {
		Response<Void> res = Response.newInstance();
		try {
			buildingInfoManager.save(buildingInfo, images, descType, descArea, userId, userName);
		} catch (BusinessException e) {
			logger.error("置业信息保存失败：" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("置业信息保存失败:" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("置业信息保存失败");
		}
		return res;
	}

	@Override
	public Response<Void> modifySortNo(Long id, Integer sortNo) {
		Response<Void> res = Response.newInstance();
		try {
			buildingInfoManager.modifySortNo(id, sortNo);;
		} catch (BusinessException e) {
			logger.error("置业排序保存失败：" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("置业排序保存失败:" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("置业排序保存失败");
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.building.service.BuildingInfoService#queryScrollPage(com.cqliving.framework.common.dao.support.ScrollPage, java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<ScrollPage<BuildingInfo>> queryScrollPage(ScrollPage<BuildingInfo> scrollPage,
			Map<String, Object> conditions) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.building.service.BuildingInfoService#buildingDetail(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<BuildingInfoDto> buildingDetail(Long buildingId) {
		return null;
	}
}
