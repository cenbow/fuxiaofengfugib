package com.cqliving.cloud.online.config.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.config.domain.ConfigText;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 联系我们、区情介绍、反馈回复 JPA Dao
 * Date: 2016-07-13 17:16:58
 * @author Code Generator
 */
public interface ConfigTextDao extends EntityJpaDao<ConfigText, Long> {
    
    /**
     * 通过appId和type查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月14日下午5:08:54
     */
    @Query("from ConfigText where appId = ?1 and type=?2")
    public List<ConfigText> getByAppId(Long appId, Byte type);
}
