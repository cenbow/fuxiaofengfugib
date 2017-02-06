package com.cqliving.cloud.online.actcustom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.actcustom.domain.UserActCustomSignup;

/**
 * 用户参与报名表 JPA Dao
 * Date: 2016-12-21 09:31:29
 * @author Code Generator
 */
public interface UserActCustomSignupDao extends EntityJpaDao<UserActCustomSignup, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update UserActCustomSignup set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
}
