package com.org.weixin.system.service;

import java.util.Date;
import java.util.List;

import org.wechat.framework.service.EntityService;

import com.org.weixin.system.domain.SysWechatUser;

/**
 * sys_wechat_user Service
 *
 * Date: 2015-07-23 20:46:53
 *
 * @author Acooly Code Generator
 *
 */
public interface SysWechatUserService extends EntityService<SysWechatUser> {
	
	/**
	 * <p>Description: 获取应用下有效手机号码数</p>
	 * @param moduleId 应用模块 id
	 * @return
	 * @author Tangtao on 2015年7月23日
	 */
	Integer getValidCellPhoneNum(Long moduleId);
	
	/**
	 * <p>Description:通过openId和模块Id查询对应的用户</p>
	 * @param openId
	 * @param mId
	 * @return
	 * @author fengshi on 2015年7月23日
	 */
	public SysWechatUser getUserByOpenIdAndMId(String openId,Long mId);
	
	/**
	 * <p>Description:</p>
	 * @param openId
	 * @return
	 * @author Tangtao on 2015年7月27日
	 */
	public List<SysWechatUser> getUserByOpenId(String openId);

	/**
	 * <p>Description: 获取昨日新增用户数</p>
	 * @param moduleId
	 * @return
	 * @author Tangtao on 2015年7月27日
	 */
	Integer getYesterdayNewUserNum(Long moduleId);
	
	/**
	 * <p>Description:</p>
	 * @param moduleId
	 * @param date
	 * @return
	 * @author Tangtao on 2015年8月17日
	 */
	Integer getNewUserNum(Long moduleId, Date date);

	/**
	 * <p>Description: 获取昨日新增用户列表</p>
	 * @param moduleId
	 * @return
	 * @author Tangtao on 2015年7月28日
	 */
	List<SysWechatUser> getYesterdayNewUsers(Long moduleId);

	/**
	 * <p>Description: 获取昨日拍摄大片的用户</p>
	 * @param moduleId
	 * @return
	 * @author Tangtao on 2015年7月29日
	 */
	List<SysWechatUser> getYesterdayNewPageUsers(Long moduleId);
	
	public void updatePhone(String phone,Long userId);
	
	public List<SysWechatUser> findByAccIdAndModuleId(Long accId,Long moduleId);

}