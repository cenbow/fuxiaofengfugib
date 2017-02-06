package com.cqliving.cloud.online.app.dao;

import java.util.List;

import com.cqliving.cloud.online.app.dto.AppMarketplaceResourceDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年5月1日
 */
public interface AppMarketplaceResourceDaoCustom {
	
	/**
	 * Title: 获取客户端需要更新的图片
	 * @author Tangtao on 2016年5月1日
	 * @param appId
	 * @param type
	 * @param version 当前客户端版本号
	 * @return
	 */
	List<AppMarketplaceResourceDto> getUpdateImgs(Long appId, Integer type, Integer version);

}