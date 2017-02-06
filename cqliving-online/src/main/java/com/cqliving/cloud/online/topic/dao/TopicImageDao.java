package com.cqliving.cloud.online.topic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.topic.domain.TopicImage;

/**
 * 话题图片表 JPA Dao
 * Date: 2016-07-14 09:46:12
 * @author Code Generator
 */
public interface TopicImageDao extends EntityJpaDao<TopicImage, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update TopicImage set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
	 * Title:根据topicInfoId修改状态
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月14日
	 * @param status
	 * @param topicInfoId
	 * @return
	 */
	@Modifying
    @Query("update TopicImage set status = ?1 where id = ?2")
	public int updateStatusByTopicInfoId(Byte status, Long topicInfoId);
	
	/**
	 * Title:根据topicInfoId删除数据
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月14日
	 * @param topicInfoId
	 * @return
	 */
	@Modifying
	@Query("delete from TopicImage where topicInfoId = ?1")
	public int deleteByTopicInfoId(Long topicInfoId);
	
	
}
