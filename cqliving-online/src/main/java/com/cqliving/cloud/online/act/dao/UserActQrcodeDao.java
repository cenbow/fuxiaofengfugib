package com.cqliving.cloud.online.act.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.act.domain.UserActQrcode;

/**
 * 用户参与二维码扫描活动表 JPA Dao
 * Date: 2016-12-16 15:16:13
 * @author Code Generator
 */
public interface UserActQrcodeDao extends EntityJpaDao<UserActQrcode, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update UserActQrcode set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	@Query("from UserActQrcode where status = 3 and token=?1 ")
	public List<UserActQrcode> findByToken(String token);
	
	@Query("from UserActQrcode where status = 3 and userId=?1 and actQrcodeCode=?2 ")
	public List<UserActQrcode> findByUserIdActCode(Long userId,String actCode);
}
