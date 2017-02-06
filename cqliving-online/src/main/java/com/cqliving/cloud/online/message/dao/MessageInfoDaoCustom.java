package com.cqliving.cloud.online.message.dao;

import java.util.Map;

import com.cqliving.cloud.online.message.dto.MessageInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年5月9日
 */
public interface MessageInfoDaoCustom {

	/**
	 * <p>Description: 获取通知分页数据</p>
	 * @author Tangtao on 2016年5月9日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	PageInfo<MessageInfoDto> queryDtoForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
			Map<String, Boolean> sortMap);
	/**
     * <p>Description: 分页查询站内信</p>
     * @author huxiaoping on 2016年5月9日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
	public PageInfo<MessageInfoDto> queryLetterForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
            Map<String, Boolean> sortMap);
	
	/**
     * <p>Description: 查询接收表站内信通过id</p>
     * @author huxiaoping on 2016年5月14日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
	public MessageInfoDto getById(Long id);
	
	/**
	 * <p>Description: 分页查询通知</p>
	 * @author huxiaoping on 2016年5月13日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	public PageInfo<MessageInfoDto> queryNoticeForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
	        Map<String, Boolean> sortMap);
	
}
