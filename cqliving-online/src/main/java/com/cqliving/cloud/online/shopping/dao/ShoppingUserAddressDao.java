package com.cqliving.cloud.online.shopping.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 用户收货地址表 JPA Dao
 * Date: 2016-11-17 15:41:44
 * @author Code Generator
 */
public interface ShoppingUserAddressDao extends EntityJpaDao<ShoppingUserAddress, Long>,ShoppingUserAddressDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ShoppingUserAddress set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	@Modifying
    @Query("update ShoppingUserAddress set isDefault =0  where appId=?1 and userId=?2 and isDefault=1")
    public int updateStatusBef(Long appId, Long userId);
	@Modifying
    @Query("update ShoppingUserAddress set status = ?1 where userId=?2 and id in ?3")
    public int updateStatusAndUserId(Byte status,Long userId,List<Long> ids);
	@Modifying
    @Query("update ShoppingUserAddress set isDefault =1  where appId=?1 and userId=?2 and id=?3")
    public int updateStatusUf(Long appId, Long userId,Long shoppingUserAddressId);
	@Modifying
    @Query("from ShoppingUserAddress where isDefault=1 and appId=?1 and userId=?2 and id in ?3")
    public List<ShoppingUserAddress> selectId(Long appId, Long userId,List<Long> ids);
	@Modifying
    @Query("from ShoppingUserAddress where  status=3 and  appId=?1 and userId=?2 and id not in ?3 order by id")
    public List<ShoppingUserAddress> selectIds(Long appId, Long userId,List<Long> ids);
	@Modifying
    @Query("from ShoppingUserAddress where isDefault=1 and status=3 and  appId=?1 and userId=?2 ")
    public List<ShoppingUserAddress> selectByAu(Long appId, Long userId);
}
