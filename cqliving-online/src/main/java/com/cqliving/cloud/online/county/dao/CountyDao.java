package com.cqliving.cloud.online.county.dao;


import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.county.domain.County;

/**
 * 区县表 JPA Dao
 * Date: 2017-01-05 10:11:02
 * @author Code Generator
 */
public interface CountyDao extends EntityJpaDao<County, Long> {
    
    /**
     * 删除区县信息通过状态
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月5日上午10:26:08
     */
    @Modifying
    @Query("delete from County where status=?1 ")
    public int delByStatus(Byte status);
    
    /**
     * 将状态修改为上线
     * @author Code Generator
     * @param ids
     * @return
     */
    @Modifying
    @Query("update County set status = ?1 where status = ?2")
    public void online(Byte online,Byte status);
    
    /**
     * 获取记录数，通过状态
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日上午10:33:38
     */
    @Query("select count(id) from County where status=?1 ")
    public Long getCountByStatus(Byte status);
    
    @Query("from County where status=?1 ")
    public List<County> getList(Byte status);
}
