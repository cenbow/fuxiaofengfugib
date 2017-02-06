package com.cqliving.cloud.online.tourism.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.tourism.domain.TourismInfo;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.cloud.online.tourism.manager.TourismInfoManager;
import com.cqliving.cloud.online.tourism.service.TourismInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("tourismInfoService")
@ServiceHandleMapping(managerClass = TourismInfoManager.class)
public class TourismInfoServiceImpl implements TourismInfoService {

	private static final Logger logger = LoggerFactory.getLogger(TourismInfoServiceImpl.class);
	
	@Autowired
	private TourismInfoManager tourismInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询旅游表失败")})
	public Response<PageInfo<TourismInfo>> queryForPage(PageInfo<TourismInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询旅游表失败")})
	public Response<TourismInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除旅游表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除旅游表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存旅游表失败")})
	public Response<Void> save(TourismInfo tourismInfo) {
		return null;
	}

	@Override
	public Response<PageInfo<TourismInfoDto>> queryDtoForPage(PageInfo<TourismInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		Response<PageInfo<TourismInfoDto>> response = Response.newInstance();
		try {
			PageInfo<TourismInfoDto> data = tourismInfoManager.queryDtoForPage(pageInfo, searchMap, sortMap);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取旅游分类分页列表失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取旅游分类分页列表失败");
		}
		return response;
	}

	@Override
	public Response<Void> modifySortNo(Long id, Integer sortNo) {
		Response<Void> response = Response.newInstance();
		try {
			 tourismInfoManager.modifySortNo(id, sortNo);
		} catch (Exception e) {
			logger.error("修改排序失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改排序失败");
		}
		return response;
	}

	@Override
	public Response<ScrollPage<TourismInfoDto>> queryForScrollPage(ScrollPage<TourismInfoDto> scrollPage,
			double lat, double lng, Long appId, String regionCode, Byte type, String tourismName) {
		Response<ScrollPage<TourismInfoDto>> response = Response.newInstance();
		try {
			ScrollPage<TourismInfoDto> data = tourismInfoManager.queryForScrollPage(scrollPage, lat, lng, appId, regionCode, type, tourismName);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取旅游列表失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取旅游列表失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取旅游列表失败");
		}
		return response;
	}

	@Override
	public Response<Void> saveByAdmin(TourismInfo tourismInfo, String images, Long userId, String userName) {
		Response<Void> res = Response.newInstance();
		try {
			tourismInfoManager.saveByAdmin(tourismInfo, images, userId, userName);
		} catch (BusinessException e) {
			logger.error("保存景点或专题失败：" + e.getMessage(), e);
			res.setCode(e.getErrorCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("保存景点或专题失败：" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("保存失败");
		}
		return res;
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
}