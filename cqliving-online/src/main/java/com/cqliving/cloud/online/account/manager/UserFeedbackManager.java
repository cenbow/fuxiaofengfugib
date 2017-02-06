package com.cqliving.cloud.online.account.manager;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserFeedback;
import com.cqliving.cloud.online.account.dto.UserFeedbackDto;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.FeedbackData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 意见反馈表 Manager
 * Date: 2016-04-29 16:28:55
 * @author Code Generator
 */
public interface UserFeedbackManager extends EntityService<UserFeedback> {

	/**
	 * <p>Description: 保存留言</p>
	 * @author Tangtao on 2016年5月19日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param title
	 * @param content
	 * @param images
	 */
	void add(Long appId, String sessionId, String token, String title, String content, String images);

	/**
	 * <p>Description: 我的反馈列表（滚动分页）</p>
	 * @author Tangtao on 2016年5月19日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lastId
	 * @return 
	 */
	CommonListResult<FeedbackData> queryForScrollPage(Long appId, String sessionId, String token, Long lastId);

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
     * <p>Description:分页查询反馈信息</p>
     * @author huxiaoping on 2016年5月28日
     * @param pageInfo
     * @param conditions
     * @param orders
     * @return
     */
    public PageInfo<UserFeedbackDto> queryByPage(PageInfo<UserFeedbackDto> pageInfo, Map<String, Object> conditions,
            Map<String, Boolean> orders);
    
    /**
     * <p>Description:通过id查询</p>
     * @author huxiaoping on 2016年5月28日
     * @param id
     * @return
     */
    public UserFeedbackDto getById(Long id);
    
    /**
     * 回复
     * @Description 
     * @Company 
     * @parameter 
     * @return 
     * @author huxiaoping 2016年5月28日下午5:13:25
     */
    public void reply(UserFeedback userFeedback);
}
