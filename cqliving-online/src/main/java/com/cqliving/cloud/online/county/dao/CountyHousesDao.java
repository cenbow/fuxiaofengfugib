package com.cqliving.cloud.online.county.dao;


import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.county.domain.CountyHouses;

/**
 * 区县楼盘表 JPA Dao
 * Date: 2017-01-05 10:11:11
 * @author Code Generator
 */
public interface CountyHousesDao extends EntityJpaDao<CountyHouses, Long>,CountyHousesDaoCustom {
    
    /**
     * 删除区县楼盘信息根据状态
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月5日上午10:26:08
     */
    @Modifying
    @Query("delete from CountyHouses where status=?1 ")
    public int delByStatus(Byte status);
    
    /**
     * 将状态修改为上线
     * @author Code Generator
     * @param ids
     * @return
     */
    @Modifying
    @Query("update CountyHouses set status = ?1 where status = ?2")
    public void online(Byte online,Byte status);
    
    /**
     * 获取记录数，通过状态
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日上午10:33:38
     */
    @Query("select count(id) from CountyHouses where status=?1 ")
    public Long getCountByStatus(Byte status);
}
