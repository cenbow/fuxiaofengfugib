package com.cqliving.cloud.online.account.manager;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.account.domain.UserSmsLog;
import com.cqliving.cloud.online.account.dto.SmsStatisticsDto;

/**
 * 用户验证码表 Manager
 * Date: 2016-04-15 09:46:19
 * @author Code Generator
 */
public interface UserSmsLogManager extends EntityService<UserSmsLog> {

	/**
	 * <p>Description: 获取最近的短信记录</p>
	 * @author Tangtao on 2016年4月27日
	 * @param appId
	 * @param sessionId
	 * @param type
	 * @return
	 */
	//UserSmsLog getLastSms(Long appId, String sessionId, Byte type, Byte status);
	UserSmsLog getLastSms(String telephone,Long appId, Byte type, Byte status);

	/**
	 * <p>Description: 发送验证码</p>
	 * @author Tangtao on 2016年4月27日
	 * @param appId
	 * @param sessionId
	 * @param phone
	 * @param type
	 */
	void sendCaptcha(Long appId, String sessionId, String phone, Byte type);

	/**
	 * <p>Description: 获取短信统计</p>
	 * @author Tangtao on 2016年10月9日
	 * @param pageInfo 
	 * @return
	 */
	PageInfo<SmsStatisticsDto> getStatistic(PageInfo<SmsStatisticsDto> pageInfo);

}