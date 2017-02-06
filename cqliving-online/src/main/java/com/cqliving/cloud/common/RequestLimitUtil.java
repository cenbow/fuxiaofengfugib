/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqliving.cloud.common.constant.RequestLimitType;
import com.cqliving.redis.base.AbstractRedisClient;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年12月9日
 */
@Component
public class RequestLimitUtil {
	
	@Autowired
	private AbstractRedisClient abstractRedisClient;

	/**
	 * <p>Description: 判断是否超出限制</p>
	 * @author Tangtao on 2016年12月9日
	 * @param phone 手机号
	 * @param type 操作类型
	 * @param limit 限制次数
	 * @param expirySecond 过期时间（秒）
	 * @return
	 */
	public Boolean isExceedLimit(String phone, Byte type, int limit, int expirySecond) {
		if (type != null && !RequestLimitType.allTypes.containsKey(type)) {
			return false;
		}
		
		String prefix = "";
		String value = "0";
		if (RequestLimitType.TYPE0.equals(type)) {	//注册
			prefix = CacheConstant.REQUEST_LIMIT_REGISTER_PREFIX;
		} else if (RequestLimitType.TYPE1.equals(type)) {	//登录
			prefix = CacheConstant.REQUEST_LIMIT_LOGIN_PREFIX;
		} else if (RequestLimitType.TYPE2.equals(type)) {	//找回密码
			prefix = CacheConstant.REQUEST_LIMIT_FIND_PWD_PREFIX;
		}
		value = abstractRedisClient.get(prefix + phone);
		if (StringUtils.isBlank(value)) {
			abstractRedisClient.set(prefix + phone, 1, expirySecond);
		} else {
			int times = Integer.parseInt(value);
			if (++times > limit) {	//超出限制
				return true;
			} else {
				abstractRedisClient.set(prefix + phone, times, expirySecond);
			}
		}
		return false;
	}

}