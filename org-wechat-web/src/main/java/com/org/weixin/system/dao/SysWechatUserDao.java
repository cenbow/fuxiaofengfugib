package com.org.weixin.system.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.system.domain.SysWechatUser;

/**
 * sys_wechat_user JPA Dao
 *
 * Date: 2015-07-23 20:46:53
 *
 * @author Acooly Code Generator
 *
 */
public interface SysWechatUserDao extends EntityJpaDao<SysWechatUser, Long> {

	/**
	 * <p>Description: 获取有效手机号码数</p>
	 * @param moduleId 模块 id
	 * @return
	 * @author Tangtao on 2015年7月23日
	 */
	@Query("select count(*) from SysWechatUser where phone is not null and moduleId = ?1")
	Integer getValidCellPhoneNum(Long moduleId);

	@Query("FROM SysWechatUser WHERE openid = ?1 AND moduleId = ?2")
	public SysWechatUser queryUserByOpenIdAndMid(String openId , Long moduleId);
	
	@Query("FROM SysWechatUser WHERE openid = ?1")
	public List<SysWechatUser> queryUserByOpenId(String openId);

	/**
	 * <p>Description: 获取时间范围内的用户数</p>
	 * @param moduleId
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @author Tangtao on 2015年7月27日
	 */
	@Query(value = ""
			+ "select count(*) from SysWechatUser "
			+ "where moduleId = ?1 "
			+ "	and createTime >= ?2 "
			+ "	and createTime <= ?3")
	Integer getUserNum(Long moduleId, Date beginTime, Date endTime);

	/**
	 * <p>Description: 获取时间范围内的用户</p>
	 * @param moduleId
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @author Tangtao on 2015年7月28日
	 */
	@Query(value = ""
			+ "from SysWechatUser "
			+ "where moduleId = ?1 "
			+ "	and createTime >= ?2 "
			+ "	and createTime <= ?3 "
			+ "order by createTime")
	List<SysWechatUser> getUsers(Long moduleId, Date beginTime, Date endTime);
	
	@Modifying
	@Query(value="update SysWechatUser set phone=?1 where id = ?2")
	public void updatePhone(String phone, Long userId);
	
	@Query(value="from SysWechatUser where accountId=?1 and moduleId=?2")
	public List<SysWechatUser> findByAccIdAndModuleId(Long accId,Long moduleId);
}