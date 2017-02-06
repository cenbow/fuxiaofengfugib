package com.cqliving.cloud.online.topic.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.topic.dao.TopicImageDao;
import com.cqliving.cloud.online.topic.domain.TopicImage;
import com.cqliving.cloud.online.topic.manager.TopicImageManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("topicImageManager")
public class TopicImageManagerImpl extends EntityServiceImpl<TopicImage, TopicImageDao> implements TopicImageManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(TopicImage.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(TopicImage.STATUS99, idList);
	}

	@Override
	public List<TopicImage> getByTopicInfoId(Long topicInfoId) {
		Map<String, Object> map = Maps.newHashMap();
		Map<String, Boolean> sortMap = Maps.newHashMap();
		map.put("EQ_topicInfoId", topicInfoId);
		map.put("EQ_status", TopicImage.STATUS3);
		sortMap.put("sortNo", true);
		return this.query(map, sortMap);
	}
}
