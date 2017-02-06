package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.account.domain.UserGovAuthentication;
import com.cqliving.tool.common.Response;

/**
 * 用户行政审批认证表 Service
 * Date: 2016-12-29 09:17:02
 * @author Code Generator
 */
public interface UserGovAuthenticationService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserGovAuthentication>> queryForPage(PageInfo<UserGovAuthentication> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserGovAuthentication> get(Long id);
	/**
	 * 获取认证信息
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年12月29日上午9:44:17
	 */
	public Response<UserGovAuthentication> get(String token, String sessionId, Long appId);
	public Response<Void> delete(Long... id);
	public Response<Void> save(UserGovAuthentication domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * 保存认证
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年12月29日上午10:49:04
	 */
	public Response<Void> save(String token, String sessionId, Long appId,String name,String idCard,Byte sex,String nation,String phone,String captcha);
	
}
