package com.cqliving.cloud.online.topic.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.tool.common.Response;

/**
 * 话题表 Service
 * Date: 2016-07-14 09:45:12
 * @author Code Generator
 */
public interface TopicInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<TopicInfo>> queryForPage(PageInfo<TopicInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<TopicInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(TopicInfo domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月14日
	 * @param topicInfo
	 * @param imageUlrs
	 * @return
	 */
	public Response<Void> saveByAdmin(TopicInfo topicInfo, String[] imageUrls);
	
	/**
	 * Title:修改置顶状态
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月15日
	 * @param status
	 * @param ids
	 * @param userId
	 * @param userName
	 * @return
	 */
	public Response<Void> updateTopStatus(Byte status, Long id, String imageUrl, Long userId, String userName);
	
	/**
	 * Title:推荐到首页、取消推荐到首页
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月18日
	 * @param status
	 * @param id
	 * @param imageUrl
	 * @return
	 */
	public Response<Void> updateRecommendStatus(Byte status, Long id, String imageUrl);
	
	/**
	 * <p>Description: 获取置顶话题列表</p>
	 * @author Tangtao on 2016年7月26日
	 * @param appId
	 * @return
	 */
	Response<List<TopicInfo>> getTopList(Long appId);
	
	/**
	 * <p>Description: 获取话题列表</p>
	 * @author Tangtao on 2016年7月26日
	 * @param scrollPage
	 * @param appId
	 * @param type
	 * @param name 
	 * @param isRecommend 
	 * @return
	 */
	Response<ScrollPage<TopicInfo>> queryForScrollPage(ScrollPage<TopicInfo> scrollPage, Long appId, Long type, String name, Byte isRecommend);
	
	/**
	 * <p>Description: 获取我的话题列表</p>
	 * @author Tangtao on 2016年7月27日
	 * @param scrollPage
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	Response<ScrollPage<TopicInfo>> queryMyScrollPage(ScrollPage<TopicInfo> scrollPage, Long appId, String sessionId, String token);
	
	/**
	 * <p>Description: 发布话题</p>
	 * @author Tangtao on 2016年7月27日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param name
	 * @param content
	 * @param imgs
	 * @param typeIds
	 * @return
	 */
	Response<Boolean> add(Long appId, String sessionId, String token, String name, String content, String imgs, String typeIds);
	
	/**
	 * <p>Description: 删除我的话题</p>
	 * @author Tangtao on 2016年7月28日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param id
	 * @return
	 */
	Response<Boolean> remove(Long appId, String sessionId, String token, Long id);
	
}
