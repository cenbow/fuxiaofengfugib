package com.cqliving.cloud.online.topic.manager;

import com.cqliving.framework.common.service.EntityService;

import java.util.List;

import com.cqliving.cloud.online.topic.domain.TopicImage;

/**
 * 话题图片表 Manager
 * Date: 2016-07-14 09:46:12
 * @author Code Generator
 */
public interface TopicImageManager extends EntityService<TopicImage> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月14日
	 * @param topicInfoId
	 * @return
	 */
	public List<TopicImage> getByTopicInfoId(Long topicInfoId);
}
