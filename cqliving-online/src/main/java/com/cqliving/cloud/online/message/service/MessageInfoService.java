package com.cqliving.cloud.online.message.service;

import java.util.Map;

import com.cqliving.cloud.online.message.domain.MessageInfo;
import com.cqliving.cloud.online.message.dto.MessageInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 消息通知表 Service
 * Date: 2016-05-09 14:30:13
 * @author Code Generator
 */
public interface MessageInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<MessageInfo>> queryForPage(PageInfo<MessageInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<MessageInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(MessageInfo domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 获取通知分页数据</p>
	 * @author Tangtao on 2016年5月9日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	Response<PageInfo<MessageInfoDto>> queryDtoForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
			Map<String, Boolean> sortMap);
	
	 /**
	 * <p>Description: 发送</p>
	 * @author Tangtao on 2016年5月9日
	 * @param id
	 * @param userName 
	 * @param userId 
	 * @return
	 */
	Response<Void> send(Long id, Long userId, String userName);
	
	/**
     * <p>Description: 新增通知</p>
     * @author Tangtao on 2016年5月10日
     * @param messageInfo
     * @param userName 
     * @param userId
     * @return
     */
    Response<Void> add(MessageInfo messageInfo, Long userId, String userName);
	
	/**
     * <p>Description: 分页查询站内信</p>
     * @author huxiaoping on 2016年5月9日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
    public Response<PageInfo<MessageInfoDto>> queryLetterForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
            Map<String, Boolean> sortMap);
    
    /**
     * <p>Description: 分页查询公告</p>
     * @author huxiaoping on 2016年5月13日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
    public Response<PageInfo<MessageInfoDto>> queryNoticeForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
            Map<String, Boolean> sortMap);
	
    /**
     * <p>Description: 查询接收表站内信通过id</p>
     * @author huxiaoping on 2016年5月14日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
    public Response<MessageInfoDto> getById(Long id);
}
