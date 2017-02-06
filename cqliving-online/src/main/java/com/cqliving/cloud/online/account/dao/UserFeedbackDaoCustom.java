package com.cqliving.cloud.online.account.dao;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserFeedback;
import com.cqliving.cloud.online.account.dto.UserFeedbackDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * 意见反馈表 JPA Dao
 * Date: 2016-04-29 16:28:55
 * @author Code Generator
 */
public interface UserFeedbackDaoCustom {
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年5月19日
	 * @param page
	 * @param appId
	 * @param userId
	 * @return
	 */
	ScrollPage<UserFeedback> queryForScrollPage(ScrollPage<UserFeedback> page, Long appId, Long userId);
	
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

}