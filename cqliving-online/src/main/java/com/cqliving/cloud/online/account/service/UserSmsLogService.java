package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserSmsLog;
import com.cqliving.cloud.online.account.dto.SmsStatisticsDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户验证码表 Service
 * Date: 2016-04-15 09:46:19
 * @author Code Generator
 */
public interface UserSmsLogService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserSmsLog>> queryForPage(PageInfo<UserSmsLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserSmsLog> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(UserSmsLog domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 发送验证码</p>
	 * @author Tangtao on 2016年4月27日
	 * @param appId 客户端 id
	 * @param sessionId 会话 id
	 * @param phone 手机号
	 * @param type 短信类型
	 * @return
	 */
	Response<Void> sendCaptcha(Long appId, String sessionId, String phone, Byte type);
	
	/**
	 * <p>Description: 获取短信统计</p>
	 * @author Tangtao on 2016年10月9日
	 * @param pageInfo 
	 * @return
	 */
	Response<PageInfo<SmsStatisticsDto>> getStatistic(PageInfo<SmsStatisticsDto> pageInfo);
	
}
