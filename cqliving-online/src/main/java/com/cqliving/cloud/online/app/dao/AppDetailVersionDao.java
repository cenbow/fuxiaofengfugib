package com.cqliving.cloud.online.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.app.domain.AppDetailVersion;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 客户端内容版本表 JPA Dao
 * Date: 2016-04-15 09:43:38
 * @author Code Generator
 */
public interface AppDetailVersionDao extends EntityJpaDao<AppDetailVersion, Long> {
    /**
     * <p>Description: 根据类型和appid获取所有版本</p>
     * @author huxiaoping on 2016年4月27日
     * @param appId 
     * @return
     */
    @Query("from AppDetailVersion where appId = ?1 and type = ?2 order by id desc")
    public List<AppDetailVersion> getByAppId(Long appId,Byte type);
}
