package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserFeedback;
import com.cqliving.cloud.online.account.dto.UserFeedbackDto;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.FeedbackData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 意见反馈表 Service
 * Date: 2016-04-29 16:28:55
 * @author Code Generator
 */
public interface UserFeedbackService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserFeedback>> queryForPage(PageInfo<UserFeedback> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserFeedback> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(UserFeedback domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 保存留言</p>
	 * @author Tangtao on 2016年5月19日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param title
	 * @param content
	 * @param images
	 * @return
	 */
	Response<Void> add(Long appId, String sessionId, String token, String title, String content, String images);
	
	/**
	 * <p>Description: 我的反馈列表</p>
	 * @author Tangtao on 2016年5月19日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lastId
	 * @return
	 */
	Response<CommonListResult<FeedbackData>> queryForScrollPage(Long appId, String sessionId, String token, Long lastId);
	
	/**
     * <p>Description:分页查询反馈信息</p>
     * @author huxiaoping on 2016年5月28日
     * @param pageInfo
     * @param conditions
     * @param orders
     * @return
     */
    public Response<PageInfo<UserFeedbackDto>> queryByPage(PageInfo<UserFeedbackDto> pageInfo, Map<String, Object> conditions, Map<String, Boolean> orders);
    
    /**
     * <p>Description:通过id查询</p>
     * @author huxiaoping on 2016年5月28日
     * @param id
     * @return
     */
    public Response<UserFeedbackDto> getById(Long id);
    
    /**
     * 回复
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月28日下午5:13:25
     */
    public Response<Void> reply(UserFeedback userFeedback);
}