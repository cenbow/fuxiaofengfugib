package com.cqliving.cloud.online.app.manager;

import com.cqliving.cloud.online.app.domain.AppDetailVersion;
import com.cqliving.cloud.online.interfacc.dto.InitStartResult;
import com.cqliving.framework.common.service.EntityService;

/**
 * 客户端内容版本表 Manager
 * Date: 2016-04-15 09:43:38
 * @author Code Generator
 */
public interface AppDetailVersionManager extends EntityService<AppDetailVersion> {

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
	InitStartResult getInitStartInfo(Long appId, String sessionId, String token, Integer appVersion,
			Integer loadingImgVersion, Integer columnsVersion, Integer type);
	
	/**
	 * Title: 获取 app 最新版本号
	 * @author Tangtao on 2016年4月30日
	 * @param appId
	 * @param type
	 * @return
	 */
	AppDetailVersion getLatest(Long appId, Byte type);

}
