package com.cqliving.cloud.online.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.app.domain.AppVersion;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 客户端版本表 JPA Dao
 * Date: 2016-04-26 16:28:10
 * @author Code Generator
 */
public interface AppVersionDao extends EntityJpaDao<AppVersion, Long>,AppVersionDaoCustom {
    /**
     * <p>Description: 修改状态</p>
     * @author huxiaoping on 2016年4月27日
     * @param id
     * @param status
     */
    @Modifying
    @Query(value = "update AppVersion t set t.status=?2 where t.id=?1")
    void changeStatus(Long id, Byte status);
    
    /**
     * 查询最大的版本号
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月19日下午4:35:19
     */
    @Query(value = "select vesionNo from AppVersion where appId=?1")
    Integer findMaxVNoByAppId(Long appId);
	
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update AppVersion set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
}
