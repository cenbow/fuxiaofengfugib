package com.cqliving.cloud.online.account.manager;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.dto.UserDto;
import com.cqliving.cloud.online.interfacc.dto.LoginResult;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 用户账号表 Manager
 * Date: 2016-04-15 09:46:01
 * @author Code Generator
 */
public interface UserAccountManager extends EntityService<UserAccount> {

	/**
	 * <p>Description: 更换手机号</p>
	 * @author Tangtao on 2016年5月3日
	 * @param appId
	 * @param sessionId
	 * @param phone
	 * @param captcha
	 */
	void changePhone(Long appId, String sessionId, String phone, String captcha);

	/**
	 * <p>Description: 验证用户信息是否匹配</p>
	 * @author Tangtao on 2016年5月3日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param phone
	 * @param pwd
	 */
	void check(Long appId, String sessionId, String token, String phone, String pwd);

	/**
	 * <p>Description: 获取用户账户</p>
	 * @author Tangtao on 2016年4月27日
	 * @param loginName 用户名
	 * @return
	 */
	UserAccount getByUserName(String loginName);

	/**
	 * <p>Description: 获取用户账户</p>
	 * @author Tangtao on 2016年5月3日
	 * @param phone
	 * @return
	 */
	UserAccount getByTelephone(String phone);

	/**
	 * <p>Description: 登录</p>
	 * @author Tangtao on 2016年5月4日
	 * @param appId
	 * @param sessionId
	 * @param loginName
	 * @param pwd
	 * @param place
	 * @param lat
	 * @param lng
	 * @param openId
	 * @param type
	 * @param nickName
	 * @param imgUrl
	 * @param ip 
	 * @return 
	 */
	LoginResult login(Long appId, String sessionId, String loginName, String pwd, String place, 
			String lat, String lng, String openId, Byte type, String nickName, String imgUrl, String ip);

	/**
	 * <p>Description: 退出登录</p>
	 * @author Tangtao on 2016年5月5日
	 * @param appId
	 * @param sessionId
	 * @param token
	 */
	void logout(Long appId, String sessionId, String token);
	
	/**
     * 逻辑删除
     * @param id
     * @return
     */
    public int deleteLogic(Long[] id);
    
    /**
     * 修改状态
     * @param status 状态
     * @param ids ID
     * @return
     */
    public int updateStatus(Byte status,Long... ids);
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月9日下午5:13:06
     */
    public PageInfo<UserDto> queryPage(PageInfo<UserDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap);
    
    /**
     * 保存用户
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月9日下午7:10:26
     */
    public void saveUser(UserAccount userAccount,UserInfo userInfo);
    
    /**
     * 查询单个记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月9日下午5:13:06
     */
    public UserDto getById(Long id,Long AppId);
    
    /**
     * 重置密码
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月10日下午6:58:24
     */
    public void resetPwd(Long id ,String password);

	/**
	 * <p>Description: 找回密码</p>
	 * @author Tangtao on 2016年5月18日
	 * @param appId
	 * @param sessionId
	 * @param pwd
	 * @param captcha
	 * @param phone
	 */
	void findPwd(Long appId, String sessionId, String pwd, String captcha, String phone);
	
	/**
	 * <p>Description: 创建游客账户</p>
	 * @author Tangtao on 2016年5月19日
	 * @param appId
	 * @param sessionId
	 * @return
	 */
	UserAccount createTourist(Long appId, String sessionId);
	
}