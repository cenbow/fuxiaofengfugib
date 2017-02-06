package com.cqliving.cloud.online.account.dao;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.account.domain.UserAccount;

/**
 * 用户账号表 JPA Dao
 * Date: 2016-04-15 09:46:01
 * @author Code Generator
 */
public interface UserAccountDao extends EntityJpaDao<UserAccount, Long>,UserAccountDaoCustom {

	/**
	 * <p>Description: 获取用户帐号</p>
	 * @author Tangtao on 2016年4月27日
	 * @param loginName 用户名
	 * @return
	 */
	@Query("from UserAccount where userName = ?1 order by id desc")
	List<UserAccount> getByUserName(String loginName);
	
	/**
	 * <p>Description: 获取用户帐号,并且排除给定id的数据</p>
	 * @author Tangtao on 2016年4月27日（这方法不是我写的 by Tangtao）
	 * @param loginName 用户名
	 * @param id 用户名
	 * @return
	 */
	@Query("from UserAccount where userName = ?1 and id <> ?2 order by id desc")
	List<UserAccount> getByUserName(String loginName,Long id);

	/**
	 * <p>Description: 获取用户帐号</p>
	 * @author Tangtao on 2016年5月3日
	 * @param phone 手机号
	 * @return
	 */
	@Query("from UserAccount where telephone = ?1 order by id desc")
	List<UserAccount> getByTelephone(String phone);
	
	/**
     * 修改状态
     * @author Code Generator
     * @param ids
     * @return
     */
    @Modifying
    @Query("update UserAccount set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
    
    /**
     * 修改密码
     * @author huxiaoping
     * @param id
     * @param password
     * @return
     */
    @Modifying
    @Query("update UserAccount set password = ?2 where id = ?1")
    public int updatePassword(Long id ,String password);

}
