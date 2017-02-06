package com.cqliving.cloud.online.config.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.config.domain.CommentAppConfig;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 评论APP配置表 JPA Dao
 * Date: 2016-06-12 10:01:10
 * @author Code Generator
 */
public interface CommentAppConfigDao extends EntityJpaDao<CommentAppConfig, Long> {

	/**
	 * <p>Description: 获取评论APP配置</p>
	 * @author Tangtao on 2016年6月12日
	 * @param appId
	 * @param configId
	 * @return
	 */
	@Query("from CommentAppConfig where appId = ?1 and commentConfigId = ?2")
	List<CommentAppConfig> getByAppAndCofigId(Long appId, Long configId);
	
}
