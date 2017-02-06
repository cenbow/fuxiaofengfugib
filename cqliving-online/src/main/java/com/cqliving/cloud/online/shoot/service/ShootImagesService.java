package com.cqliving.cloud.online.shoot.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shoot.domain.ShootImages;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 随手拍图片表 Service
 * Date: 2016-06-07 16:39:28
 * @author Code Generator
 */
public interface ShootImagesService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShootImages>> queryForPage(PageInfo<ShootImages> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShootImages> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ShootImages domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 获取随手拍图文列表</p>
	 * @author Tangtao on 2016年6月14日
	 * @param shootId
	 * @return
	 */
	Response<List<ShootImages>> getByShootId(Long shootId);
	
}
