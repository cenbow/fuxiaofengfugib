package com.cqliving.cloud.online.topic.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.topic.domain.TopicImage;
import com.cqliving.tool.common.Response;

/**
 * 话题图片表 Service
 * Date: 2016-07-14 09:46:12
 * @author Code Generator
 */
public interface TopicImageService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<TopicImage>> queryForPage(PageInfo<TopicImage> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<TopicImage> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(TopicImage domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月14日
	 * @param topicInfoId
	 * @return
	 */
	public Response<List<TopicImage>> getByTopicInfoId(Long topicInfoId);
	
}
