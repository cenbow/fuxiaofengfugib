package com.cqliving.cloud.online.app.service;

import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppVersion;
import com.cqliving.cloud.online.app.dto.AppVersionDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 客户端版本表 Service
 * Date: 2016-04-26 16:28:10
 * @author Code Generator
 */
public interface AppVersionService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AppVersion>> queryForPage(PageInfo<AppVersion> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AppVersion> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(AppVersion domain);
	/** @author Code Generator *****end*****/
	public Response<Void> update(AppVersion domain);
	public Response<PageInfo<AppVersionDto>> queryByPage(PageInfo<AppVersionDto> pageInfo, Map<String, Object> conditions,Map<String, Boolean> orders);
	public Response<AppVersionDto> getById(Long id);
}
