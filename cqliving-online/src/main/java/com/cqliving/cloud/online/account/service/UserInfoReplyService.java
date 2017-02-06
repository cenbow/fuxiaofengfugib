package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.dto.UserInfoReplyDto;
import com.cqliving.cloud.online.interfacc.dto.CommentsData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.MyCommentsData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.tool.common.Response;

/**
 * 用户资讯回复表 Service
 * Date: 2016-04-15 09:46:08
 * @author Code Generator
 */
public interface UserInfoReplyService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserInfoReply>> queryForPage(PageInfo<UserInfoReply> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserInfoReply> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> auditing(Byte status,String auditingContent,Long auditingId,String auditingtor,Long[] sourceIds,Byte sourceType,Long... ids);
	public Response<Void> save(UserInfoReply domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title: 获取评论列表
	 * @author Tangtao on 2016年5月2日
	 * @param appId
	 * @param token 
	 * @param sessionId 
	 * @param informationId
	 * @param sourceType 
	 * @param lastId
	 * @return
	 */
	Response<CommonListResult<CommentsData>> getPageBySourceId(Long appId, String sessionId, String token, Long informationId, Byte sourceType, Long lastId);
	
	/**
	 * Title: 获取用户评论列表
	 * @author Tangtao on 2016年5月2日
	 * @param appId
	 * @param sessionId 
	 * @param token
	 * @param type
	 * @param lastReplyId
	 * @param sourceType fixedBy：DeweiLi 目前问政用到这个参数
	 * @return
	 */
	Response<CommonListResult<MyCommentsData>> getPageByUser(Long appId, String sessionId, String token, Integer type, Long lastReplyId, Byte sourceType);
	
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月12日
     */
	public Response<PageInfo<UserInfoReplyDto>> queryByPage(PageInfo<UserInfoReplyDto> pageInfo,Map<String, Object> conditions,Map<String, Boolean> orders,Byte sourceType);
	
	//滚动分页
	public Response<ScrollPage<UserInfoReplyDto>> queryScrollPage(ScrollPage<UserInfoReplyDto> scrollPage,Map<String,Object> conditions, Byte sourceType);
	
	/**
	 * <p>Description: 增加评论</p>
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
	Response<Void> add(Long appId, String sessionId, String token, Long sourceId, Byte sourceType, String place,
			String lng, String lat, Long replyId, String passiveReplyName, Long passiveReplyId, String content, Boolean isAnonymous);
	
	/**
	 * Title:获取评论列表，游客也可以获取
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月18日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type
	 * @param sourceType
	 * @param lastId
	 * @return
	 */
	Response<CommonListResult<MyCommentsData>> queryWzScrollPage(Long appId, String sessionId, String token, Integer type, Byte sourceType, Long lastId);
	
	/**
	 * <p>Description: 获取评论数</p>
	 * @author Tangtao on 2016年6月1日
	 * @param appId
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	Response<Integer> getReplyCount(Long appId, Long sourceId, Byte sourceType);
	
	/**
	 * <p>Description: 删除我的评论</p>
	 * @author Tangtao on 2016年6月2日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param userInfoReplyId
	 * @return
	 */
	Response<Void> remove(Long appId, String sessionId, String token, Long userInfoReplyId);
	
	Response<ScrollPage<UserInfoReplyDto>> queryForTopicCommentsPage(ScrollPage<UserInfoReplyDto> scrollPage,Map<String,Object> conditions);
}
