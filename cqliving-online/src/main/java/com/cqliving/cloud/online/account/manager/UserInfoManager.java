package com.cqliving.cloud.online.account.manager;

import com.cqliving.framework.common.service.EntityService;

import java.io.IOException;
import java.util.List;

import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.interfacc.dto.UploadResult;
import com.cqliving.cloud.online.interfacc.dto.VisitResult;

/**
 * 用户信息表 Manager
 * Date: 2016-04-15 09:46:05
 * @author Code Generator
 */
public interface UserInfoManager extends EntityService<UserInfo> {

	/**
	 * <p>Description: 注册</p>
	 * @author Tangtao on 2016年4月27日
	 * @param appId
	 * @param sessionId
	 * @param loginName
	 * @param pwd
	 * @param captcha
	 * @param actSource 来源
	 */
	void register(Long appId, String sessionId, String loginName, String pwd, String captcha, String actSource);

	/**
	 * Title: 修改密码
	 * @author Tangtao on 2016年5月1日
	 * @param appId
	 * @param token
	 * @param pwd
	 * @param newPwd
	 */
	void updatePwd(Long appId, String token, String pwd, String newPwd);

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
	VisitResult visit(Long appId, String sessionId, String token, String phoneCode, String place, String lat, String lng, String userAgent);

	/**
	 * <p>Description: 修改昵称</p>
	 * @author Tangtao on 2016年5月16日
	 * @param appId
	 * @param token
	 * @param nickname
	 */
	void modifyNickname(Long appId, String token, String nickname);

	/**
	 * <p>Description: 修改性别</p>
	 * @author Tangtao on 2016年5月16日
	 * @param appId
	 * @param token
	 * @param gender
	 */
	void modifyGender(Long appId, String token, Byte gender);

	/**
	 * <p>Description: 修改邮箱</p>
	 * @author Tangtao on 2016年5月16日
	 * @param appId
	 * @param token
	 * @param email
	 */
	void modifyEmail(Long appId, String token, String email);

	/**
	 * <p>Description: 修改头像</p>
	 * @author Tangtao on 2016年5月16日
	 * @param appId
	 * @param token
	 * @param head
	 */
	void modifyHead(Long appId, String token, String head);

	/**
	 * <p>Description: 删除收藏</p>
	 * @author Tangtao on 2016年5月16日
	 * @param appId
	 * @param sessionId 
	 * @param token
	 * @param idList
	 */
	void removeFavorites(Long appId, String sessionId, String token, List<Long> idList);

	/**
	 * <p>Description: 上传图片</p>
	 * @author Tangtao on 2016年5月16日
	 * @param image
	 * @param appId
	 * @return 
	 * @throws IOException 
	 */
	UploadResult uploadImg(String image, Long appId) throws IOException;

}