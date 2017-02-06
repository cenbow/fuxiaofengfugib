package com.cqliving.cloud.online.account.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.account.domain.UserOtherAccount;

/**
 * 用户其他平台账号表 Manager
 * Date: 2016-04-29 16:28:56
 * @author Code Generator
 */
public interface UserOtherAccountManager extends EntityService<UserOtherAccount> {

	/**
	 * <p>Description: 获取第三方用户信息</p>
	 * @author Tangtao on 2016年5月5日
	 * @param userToken
	 * @return
	 */
	UserOtherAccount getByUserToken(String userToken);

}