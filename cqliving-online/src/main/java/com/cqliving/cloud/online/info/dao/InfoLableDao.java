package com.cqliving.cloud.online.info.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.info.domain.InfoLable;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 资讯标签表 JPA Dao
 * Date: 2016-05-06 10:52:59
 * @author Code Generator
 */
public interface InfoLableDao extends EntityJpaDao<InfoLable, Long>,InfoLableDaoCustom {
    public List<InfoLable> findByAppIdAndSourceType(Long appId, Byte sourceType);
    public List<InfoLable> findByAppIdAndSourceTypeAndName(Long appId, Byte sourceType,String name);
    
    @Query(value="from InfoLable where appId = ?1 and sourceType=?2 and name=?3 and id <> ?4")
    public List<InfoLable> findByAppIdAndSourceTypeAndNameAndNotId(Long appId, Byte sourceType,String name,Long id);
}
