package com.cqliving.cloud.online.topic.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.online.config.manager.CommentAppConfigManager;
import com.cqliving.cloud.online.topic.dao.TopicDefaultImageDao;
import com.cqliving.cloud.online.topic.domain.TopicDefaultImage;
import com.cqliving.cloud.online.topic.manager.TopicDefaultImageManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Service("topicDefaultImageManager")
public class TopicDefaultImageManagerImpl extends EntityServiceImpl<TopicDefaultImage, TopicDefaultImageDao> implements TopicDefaultImageManager {
	
	@Autowired
	private CommentAppConfigManager commentAppConfigManager;
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(TopicDefaultImage.STATUS99, idList);
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
		return this.getEntityDao().updateStatus(TopicDefaultImage.STATUS99, idList);
	}

	@Override
	public TopicDefaultImage getByAppId(Long appId) {
		Map<String, Object> map = Maps.newHashMap();
		Map<String, Boolean> sortMap = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_status", TopicDefaultImage.STATUS3);
		List<TopicDefaultImage> list = this.query(map, sortMap);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void save(Long appId, Long userId, String nickname, Map<Long, Byte> parma, String defaultImage) {
		//保存评论审核相关配置
		commentAppConfigManager.save(appId, userId, nickname, parma, BusinessType.SOURCE_TYPE_7);
		//保存默认图片
		Date now = DateUtil.now();
		TopicDefaultImage topicDefaultImage = getByAppId(appId);
		if (topicDefaultImage == null) {	//新增
			topicDefaultImage = new TopicDefaultImage();
			topicDefaultImage.setAppId(appId);
			topicDefaultImage.setCreateTime(now);
			topicDefaultImage.setCreator(nickname);
			topicDefaultImage.setCreatorId(userId);
			topicDefaultImage.setStatus(TopicDefaultImage.STATUS3);
			topicDefaultImage.setUpdateTime(now);
			topicDefaultImage.setUpdator(nickname);
			topicDefaultImage.setUpdatorId(userId);
		}
		topicDefaultImage.setImageUrl(defaultImage);
		topicDefaultImage.setUpdateTime(now);
		topicDefaultImage.setUpdator(nickname);
		topicDefaultImage.setUpdatorId(userId);
		getEntityDao().save(topicDefaultImage);
	}
}
