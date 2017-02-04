package com.org.weixin.module.ahjy.service;

import org.wechat.framework.service.EntityService;

import com.org.common.SessionUser;
import com.org.weixin.module.ahjy.domain.AhjyActivity;
import com.org.weixin.module.ahjy.dto.AhjyReceiveAwardDto;

/**
 * 艾赫金源活动领奖 Service 接口
 *
 * Date: 2016-03-26 09:10:38
 *
 * @author huxiaoping
 *
 */
public interface AhjyReceiveAwardService extends EntityService<AhjyActivity> {
	
	/**
	 * 领奖
	 * @param phone
	 * @param user
	 */
	public AhjyReceiveAwardDto receiveAward(String phone,SessionUser user) throws Exception;
}
