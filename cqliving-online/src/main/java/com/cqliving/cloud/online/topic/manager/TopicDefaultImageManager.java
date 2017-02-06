package com.cqliving.cloud.online.topic.manager;

import com.cqliving.framework.common.service.EntityService;

import java.util.Map;

import com.cqliving.cloud.online.topic.domain.TopicDefaultImage;

/**
 * 话题默认图片表 Manager
 * Date: 2016-07-14 09:46:16
 * @author Code Generator
 */
public interface TopicDefaultImageManager extends EntityService<TopicDefaultImage> {
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
	 * Title:根据appId获得数据
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月14日
	 * @param appId
	 * @return
	 */
	public TopicDefaultImage getByAppId(Long appId);

	/**
	 * <p>Description: 保存话题配置</p>
	 * @author Tangtao on 2016年7月25日
	 * @param appId
	 * @param userId
	 * @param nickname
	 * @param parma
	 * @param defaultImage
	 */
	void save(Long appId, Long userId, String nickname, Map<Long, Byte> parma, String defaultImage);
	
}
