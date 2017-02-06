package com.cqliving.cloud.online.app.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppQiniu;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * APP七牛云服务配置 Service
 * Date: 2016-05-24 17:03:56
 * @author Code Generator
 */
public interface AppQiniuService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AppQiniu>> queryForPage(PageInfo<AppQiniu> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AppQiniu> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(AppQiniu domain);
	/** @author Code Generator *****end*****/
	public Response<List<AppQiniu>> findByAppId(Long appId);
	
	public Response<List<AppQiniu>> findByDefault(Byte isDefault);
}
