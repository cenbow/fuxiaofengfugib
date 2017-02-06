package com.cqliving.cloud.online.account.dao;

import com.cqliving.cloud.online.account.domain.UserPraise;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author Tangtao on 2016年5月2日
 */
public interface UserPraiseDaoCustom {
	
	/**
	 * Title: 获取我收到的赞
	 * @author Tangtao on 2016年5月2日
	 * @param page
	 * @param appId
	 * @param userId
	 * @return
	 */
	ScrollPage<UserPraise> getMyPraisePage(ScrollPage<UserPraise> page, Long appId, Long userId);

}
