package com.cqliving.cloud.online.wz.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.wz.domain.WzUser;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 子帐号列表 Service
 * Date: 2016-05-17 15:18:12
 * @author Code Generator
 */
public interface WzUserService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<WzUser>> queryForPage(PageInfo<WzUser> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<WzUser> get(Long id);
	public Response<Void> save(WzUser domain);
	/** @author Code Generator *****end*****/
	
	Response<List<WzUser>> getByAppId(Long appId);
	
}
