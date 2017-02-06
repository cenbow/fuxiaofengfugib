package com.cqliving.cloud.online.app.service;

import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppDetailVersion;
import com.cqliving.cloud.online.interfacc.dto.InitStartResult;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 客户端内容版本表 Service
 * Date: 2016-04-15 09:43:38
 * @author Code Generator
 */
public interface AppDetailVersionService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AppDetailVersion>> queryForPage(PageInfo<AppDetailVersion> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AppDetailVersion> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(AppDetailVersion domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title: 获取客户端初始化信息
	 * @author Tangtao on 2016年4月30日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param appVersion
	 * @param loadingImgVersion
	 * @param columnsVersion
	 * @param type
	 * @return
	 */
	Response<InitStartResult> getInitStartInfo(Long appId, String sessionId, String token, Integer appVersion,
			Integer loadingImgVersion, Integer columnsVersion, Integer type);
	
}
