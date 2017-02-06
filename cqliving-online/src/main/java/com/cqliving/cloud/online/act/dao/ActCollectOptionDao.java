package com.cqliving.cloud.online.act.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.act.domain.ActCollectOption;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 活动信息收集选项表 JPA Dao
 * Date: 2016-06-07 09:18:50
 * @author Code Generator
 */
public interface ActCollectOptionDao extends EntityJpaDao<ActCollectOption, Long> {
    
    /**
     * <p>Description: 通过actCollectInfoId查询信息</p>
     * @author huxiaoping on 2016年6月12日
     * @param actCollectInfoId
     */
    @Query("from ActCollectOption where actCollectInfoId = ?1 ")
    public List<ActCollectOption> findByActCollectInfoId(Long actCollectInfoId);
    
    /**
     * <p>Description: 通过actCollectInfoId删除</p>
     * @author huxiaoping on 2016年6月12日
     * @param actCollectInfoId
     */
    @Modifying
    @Query(value = "delete ActCollectOption where actCollectInfoId=?1")
    public void delByActCollectInfoId(Long actCollectInfoId);
}
