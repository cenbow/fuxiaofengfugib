package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.dto.UserDto;
import com.cqliving.cloud.online.interfacc.dto.LoginResult;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户账号表 Service
 * Date: 2016-04-15 09:46:01
 * @author Code Generator
 */
public interface UserAccountService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserAccount>> queryForPage(PageInfo<UserAccount> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserAccount> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(UserAccount domain);
	/** @author Code Generator *****end*****/
	/**
	 * 保存用户
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年5月9日下午6:58:24
	 */
	public Response<Void> saveUser(UserAccount userAccount,UserInfo userInfo);
	
	/**
	 * 重置密码
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年5月10日下午6:58:24
	 */
	public Response<Void> resetPwd(Long id ,String password);
	
	/**
     * 查询单个记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月9日下午5:13:06
     */
    public  Response<UserDto> getById(Long id,Long AppId);
	
	/**
	 * <p>Description: 验证用户信息是否匹配</p>
	 * @author Tangtao on 2016年5月3日
	 * @param appId 客户端 id
	 * @param sessionId 
	 * @param token 
	 * @param phone 手机号
	 * @param pwd 密码
	 * @return
	 */
	Response<Void> check(Long appId, String sessionId, String token, String phone, String pwd);
	
	/**
	 * <p>Description: 更换手机号</p>
	 * @author Tangtao on 2016年5月3日
	 * @param appId
	 * @param sessionId
	 * @param phone
	 * @param captcha
	 * @return
	 */
	Response<Void> changePhone(Long appId, String sessionId, String phone, String captcha);
	
	/**
	 * <p>Description: 登录</p>
	 * @author Tangtao on 2016年5月4日
	 * @param appId
	 * @param sessionId
	 * @param loginName
	 * @param pwd
	 * @param lat
	 * @param lng
	 * @param position
	 * @param openId
	 * @param type
	 * @param nickName
	 * @param imgUrl
	 * @param ip 
	 * @return
	 */
	Response<LoginResult> login(Long appId, String sessionId, String loginName, String pwd, String position, 
			String lat, String lng, String openId, Byte type, String nickName, String imgUrl, String ip);
	
	/**
	 * <p>Description: 退出登录</p>
	 * @author Tangtao on 2016年5月5日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	Response<Void> logout(Long appId, String sessionId, String token);
	
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月9日下午5:13:06
     */
    public Response<PageInfo<UserDto>> queryPage(PageInfo<UserDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap);
    
	/**
	 * <p>Description: 找回密码</p>
	 * @author Tangtao on 2016年5月18日
	 * @param appId
	 * @param sessionId
	 * @param pwd
	 * @param captcha
	 * @param phone
	 * @return
	 */
	Response<Void> findPwd(Long appId, String sessionId, String pwd, String captcha, String phone);
	
	/**
	 * Title:创建游客
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月27日
	 * @param appId
	 * @param sessionId
	 * @return
	 */
    Response<UserAccount> createTourist(Long appId, String sessionId);
	
}
