package com.cqliving.cloud.online.topic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.topic.domain.TopicDefaultImage;

/**
 * 话题默认图片表 JPA Dao
 * Date: 2016-07-14 09:46:16
 * @author Code Generator
 */
public interface TopicDefaultImageDao extends EntityJpaDao<TopicDefaultImage, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update TopicDefaultImage set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
}
