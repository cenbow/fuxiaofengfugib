package com.cqliving.cloud.online.account.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.interfacc.dto.UploadResult;
import com.cqliving.cloud.online.interfacc.dto.VisitResult;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户信息表 Service
 * Date: 2016-04-15 09:46:05
 * @author Code Generator
 */
public interface UserInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserInfo>> queryForPage(PageInfo<UserInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(UserInfo domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 注册</p>
	 * @author Tangtao on 2016年4月27日
	 * @param appId
	 * @param sessionId
	 * @param loginName
	 * @param pwd
	 * @param captcha
	 * @param actSource 来源
	 * @return
	 */
	Response<Void> register(Long appId, String sessionId, String loginName, String pwd, String captcha, String actSource);
	
	/**
	 * Title: 修改密码
	 * @author Tangtao on 2016年5月1日
	 * @param appId
	 * @param token
	 * @param pwd
	 * @param newPwd
	 * @return
	 */
	Response<Void> updatePwd(Long appId, String token, String pwd, String newPwd);
	
	/**
	 * <p>Description: 用户访问</p>
	 * @author Tangtao on 2016年5月5日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param phoneCode
	 * @param place
	 * @param lat
	 * @param lng 
	 * @param userAgent 
	 * @return
	 */
	Response<VisitResult> visit(Long appId, String sessionId, String token, String phoneCode, String place, String lat, String lng, String userAgent);
	
	/**
	 * <p>Description: 修改昵称</p>
	 * @author Tangtao on 2016年5月16日
	 * @param appId
	 * @param token
	 * @param nickname
	 * @return
	 */
	Response<Void> modifyNickname(Long appId, String token, String nickname);
	
	/**
	 * <p>Description: 修改性别</p>
	 * @author Tangtao on 2016年5月16日
	 * @param appId
	 * @param token
	 * @param gender
	 * @return
	 */
	Response<Void> modifyGender(Long appId, String token, Byte gender);
	
	/**
	 * <p>Description: 修改邮箱</p>
	 * @author Tangtao on 2016年5月16日
	 * @param appId
	 * @param token
	 * @param email
	 * @return
	 */
	Response<Void> modifyEmail(Long appId, String token, String email);
	
	/**
	 * <p>Description: 修改头像</p>
	 * @author Tangtao on 2016年5月16日
	 * @param appId
	 * @param token
	 * @param head
	 * @return
	 */
	Response<Void> modifyHead(Long appId, String token, String head);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年5月16日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param idList
	 * @return
	 */
	Response<Void> removeFavorites(Long appId, String sessionId, String token, List<Long> idList);
	
	/**
	 * <p>Description: 图片上传</p>
	 * @author Tangtao on 2016年5月16日
	 * @param image
	 * @param appId
	 * @return
	 */
	Response<UploadResult> uploadImg(String image, Long appId);
	
}