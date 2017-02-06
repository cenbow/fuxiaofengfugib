package com.cqliving.cloud.online.topic.manager;

import java.util.List;

import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 话题表 Manager
 * Date: 2016-07-14 09:45:12
 * @author Code Generator
 */
public interface TopicInfoManager extends EntityService<TopicInfo> {
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
	 * Title:修改置顶状态
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月15日
	 * @param status
	 * @param id
	 * @param imageUrl
	 * @param userId
	 * @param userName
	 * @return
	 */
	public void updateTopStatus(Byte status,Long id, String imageUrl, Long userId, String userName);
	
	/**
	 * Title:推荐到首页、取消推荐到首页
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月18日
	 * @param status
	 * @param id
	 * @param imageUrl
	 * @return
	 */
	public void updateRecommendStatus(Byte status, Long id, String imageUrl);
	
	/**
	 * Title:cms保存话题
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月15日
	 * @param topicInfo
	 * @param imageUrls
	 */
	public void saveByAdmin(TopicInfo topicInfo, String[] imageUrls);

	/**
	 * <p>Description: 获取置顶话题列表</p>
	 * @author Tangtao on 2016年7月26日
	 * @param appId
	 * @return
	 */
	List<TopicInfo> getTopList(Long appId);

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
	ScrollPage<TopicInfo> queryForScrollPage(ScrollPage<TopicInfo> scrollPage, Long appId, Long type, String name, Byte isRecommend);

	/**
	 * <p>Description: 获取我的话题列表</p>
	 * @author Tangtao on 2016年7月27日
	 * @param scrollPage
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	ScrollPage<TopicInfo> queryMyScrollPage(ScrollPage<TopicInfo> scrollPage, Long appId, String sessionId, String token);

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
	Byte add(Long appId, String sessionId, String token, String name, String content, String imgs, String typeIds);

	/**
	 * <p>Description: 删除我的话题</p>
	 * @author Tangtao on 2016年7月28日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param id
	 * @return
	 */
	Boolean remove(Long appId, String sessionId, String token, Long id);
	
}