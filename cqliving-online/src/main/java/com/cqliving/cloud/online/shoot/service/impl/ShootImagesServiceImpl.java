package com.cqliving.cloud.online.shoot.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.shoot.domain.ShootImages;
import com.cqliving.cloud.online.shoot.manager.ShootImagesManager;
import com.cqliving.cloud.online.shoot.service.ShootImagesService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("shootImagesService")
@ServiceHandleMapping(managerClass = ShootImagesManager.class)
public class ShootImagesServiceImpl implements ShootImagesService {

	private static final Logger logger = LoggerFactory.getLogger(ShootImagesServiceImpl.class);
	
	@Autowired
	private ShootImagesManager shootImagesManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询随手拍图片表失败")})
	public Response<PageInfo<ShootImages>> queryForPage(PageInfo<ShootImages> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询随手拍图片表失败")})
	public Response<ShootImages> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除随手拍图片表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存随手拍图片表失败")})
	public Response<Void> save(ShootImages shootImages) {
		return null;
	}

	@Override
	public Response<List<ShootImages>> getByShootId(Long shootId) {
		Response<List<ShootImages>> response = Response.newInstance();
		try {
			List<ShootImages> data = Lists.newArrayList();
			if (shootId != null) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("EQ_shootId", shootId);
				Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
				sortMap.put("id", true);
				data = shootImagesManager.query(map, sortMap);
			}
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取随手拍图片列表失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取随手拍图片列表失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取随手拍图片列表失败");
		}
		return response;
	}
	
}