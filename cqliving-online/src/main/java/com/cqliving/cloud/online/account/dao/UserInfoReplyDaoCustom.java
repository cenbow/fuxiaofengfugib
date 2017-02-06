package com.cqliving.cloud.online.account.dao;

import java.util.Map;

import com.cqliving.cloud.online.account.dto.UserInfoReplyDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年5月2日
 */
public interface UserInfoReplyDaoCustom {

	/**
	 * Title: 获取评论列表
	 * @author Tangtao on 2016年5月2日
	 * @param page
	 * @param conditions
	 * @param userId 当前用户 id，用于查询是否已点赞评论
	 * @param sourceType 
	 * @return
	 */
	ScrollPage<UserInfoReplyDto> queryDtoForScrollPage(ScrollPage<UserInfoReplyDto> page, Map<String, Object> conditions, Long userId, Byte sourceType);
	
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月12日
     */
    public PageInfo<UserInfoReplyDto> queryByPage(PageInfo<UserInfoReplyDto> pageInfo,Map<String, Object> conditions,Map<String, Boolean> orders,Byte sourceType);
    
    public ScrollPage<UserInfoReplyDto> queryForTopicCommentsPage(ScrollPage<UserInfoReplyDto> scrollPage,
			Map<String, Object> conditions);
}
