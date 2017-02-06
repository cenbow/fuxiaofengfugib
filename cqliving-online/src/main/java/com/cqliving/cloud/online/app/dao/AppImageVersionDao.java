package com.cqliving.cloud.online.app.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.app.domain.AppImageVersion;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 客户端发布广告表 JPA Dao
 * Date: 2016-05-04 16:01:26
 * @author Code Generator
 */
public interface AppImageVersionDao extends EntityJpaDao<AppImageVersion, Long>,AppImageVersionDaoCustom {
    /**
     * 查询某个App的最大版本号
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月26日上午11:35:54
     */
    @Query("select max(versionNo) from AppImageVersion a where appId=?1")
    Integer findVersionNoByAppId(Long appId);
    
    /**
     * 修改使用状态
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月26日上午11:54:14
     */
    @Modifying
    @Query(value = "update AppImageVersion t set t.useStatus = ?1,t.endTime = ?3 where t.appId = ?2")
    void updateUseStatusByAppId(Byte status0, Long appId, Date endTime);
    
    /**
     * 修改使用状态
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月26日上午11:54:14
     */
    @Modifying
    @Query(value = "update AppImageVersion t set t.useStatus = ?1,t.endTime = ?3 where t.id =  ?2")
    void updateUseStatusById(Byte status, Long id, Date endTime);
}
