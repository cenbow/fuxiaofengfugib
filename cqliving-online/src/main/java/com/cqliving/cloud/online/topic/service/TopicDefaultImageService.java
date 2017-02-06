package com.cqliving.cloud.online.topic.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.topic.domain.TopicDefaultImage;
import com.cqliving.tool.common.Response;

/**
 * 话题默认图片表 Service
 * Date: 2016-07-14 09:46:16
 * @author Code Generator
 */
public interface TopicDefaultImageService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<TopicDefaultImage>> queryForPage(PageInfo<TopicDefaultImage> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<TopicDefaultImage> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(TopicDefaultImage domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 获取话题默认图片</p>
	 * @author Tangtao on 2016年7月25日
	 * @param appId
	 * @return
	 */
	Response<TopicDefaultImage> getByApp(Long appId);
	
	/**
	 * <p>Description: 保存话题配置</p>
	 * @author Tangtao on 2016年7月25日
	 * @param appId
	 * @param userId
	 * @param nickname
	 * @param parma
	 * @param defaultImage
	 * @return 
	 */
	Response<Void> save(Long appId, Long userId, String nickname, Map<Long, Byte> parma, String defaultImage);
	
}
