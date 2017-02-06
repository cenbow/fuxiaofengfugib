package com.cqliving.cloud.online.account.manager;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.dto.UserInfoReplyDto;
import com.cqliving.cloud.online.interfacc.dto.CommentsData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.MyCommentsData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 用户资讯回复表 Manager
 * Date: 2016-04-15 09:46:08
 * @author Code Generator
 */
public interface UserInfoReplyManager extends EntityService<UserInfoReply> {
	
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
	 * 审核
	 * @param status 状态
	 * @param auditingContent 审核描述
	 * @param ids ID
	 * @return
	 */
	public int auditing(Byte status,String auditingContent,Long auditingId,String auditingtor,Long[] sourceIds,Byte sourceType,Long... ids);
	
	/**
	 * Title: 获取评论列表
	 * @author Tangtao on 2016年5月2日
	 * @param appId
	 * @param token 
	 * @param sessionId 
	 * @param sourceId
	 * @param sourceType 
	 * @param lastId
	 * @return
	 */
	CommonListResult<CommentsData> getPageBySourceId(Long appId, String sessionId, String token, Long sourceId, Byte sourceType, Long lastId);

	/**
	 * Title: 获取我的评论列表
	 * @author Tangtao on 2016年5月2日
	 * @param appId
	 * @param sessionId 
	 * @param token
	 * @param type
	 * @param lastId
	 * @param sourceType fixedBy：DeweiLi 目前问政用到这个参数
	 * @return
	 */
	CommonListResult<MyCommentsData> getPageByUser(Long appId, String sessionId, String token, Integer type, Long lastId, Byte sourceType);
	
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月12日
     */
    public PageInfo<UserInfoReplyDto> queryByPage(PageInfo<UserInfoReplyDto> pageInfo,Map<String, Object> conditions,Map<String, Boolean> orders,Byte sourceType);
    
    public ScrollPage<UserInfoReplyDto> queryScrollPage(ScrollPage<UserInfoReplyDto> scrollPage,Map<String, Object> conditions, Byte sourceType);
    
	/**
	 * <p>Description: 保存评论</p>
	 * @author Tangtao on 2016年5月26日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param sourceId
	 * @param sourceType
	 * @param place
	 * @param lng
	 * @param lat
	 * @param replyId 
	 * @param passiveReplyName
	 * @param passiveReplyId
	 * @param content
	 * @param isAnonymous 
	 * @return
	 */
	Byte add(Long appId, String sessionId, String token, Long sourceId, Byte sourceType, String place,
			String lng, String lat, Long replyId, String passiveReplyName, Long passiveReplyId, String content, Boolean isAnonymous);

	/**
	 * Title:分页查询
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月28日
	 * @param page
	 * @param conditions
	 * @return
	 */
	CommonListResult<MyCommentsData> queryWzScrollPage(Long appId, String sessionId, String token, Integer type, Byte sourceType, Long lastId);

	/**
	 * <p>Description: 获取评论数</p>
	 * @author Tangtao on 2016年6月1日
	 * @param appId
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	Integer getReplyCount(Long appId, Long sourceId, Byte sourceType);

	/**
	 * <p>Description: 删除我的评论</p>
	 * @author Tangtao on 2016年6月2日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param userInfoReplyId
	 */
	void remove(Long appId, String sessionId, String token, Long userInfoReplyId);
	
	public ScrollPage<UserInfoReplyDto> queryForTopicCommentsPage(ScrollPage<UserInfoReplyDto> scrollPage,
			Map<String, Object> conditions);
	
}