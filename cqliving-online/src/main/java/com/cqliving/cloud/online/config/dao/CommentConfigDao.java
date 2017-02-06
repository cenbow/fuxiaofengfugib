package com.cqliving.cloud.online.config.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 评论配置表 JPA Dao
 * Date: 2016-06-12 10:01:52
 * @author Code Generator
 */
public interface CommentConfigDao extends EntityJpaDao<CommentConfig, Long> {

	/**
	 * <p>Description: 获取评论配置</p>
	 * @author Tangtao on 2016年6月12日
	 * @param name
	 * @param type 
	 * @return
	 */
	@Query("from CommentConfig where name = ?1 and type = ?2 order by id desc")
	List<CommentConfig> getByName(String name, Byte type);
	
}