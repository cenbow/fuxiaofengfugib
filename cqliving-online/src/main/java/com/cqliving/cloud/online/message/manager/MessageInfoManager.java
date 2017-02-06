package com.cqliving.cloud.online.message.manager;

import java.util.Map;

import com.cqliving.cloud.online.message.domain.MessageInfo;
import com.cqliving.cloud.online.message.dto.MessageInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 消息通知表 Manager
 * Date: 2016-04-15 09:47:57
 * @author Code Generator
 */
public interface MessageInfoManager extends EntityService<MessageInfo> {

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
	 * <p>Description: 发送</p>
	 * @author Tangtao on 2016年5月9日
	 * @param id
	 * @param userId 
	 * @param userName 
	 */
	void send(Long id, Long userId, String userName);
	
	/**
     * <p>Description: 新增通知</p>
     * @author Tangtao on 2016年5月10日
     * @param messageInfo
     * @param userId 
     * @param userName 
     */
    void add(MessageInfo messageInfo, Long userId, String userName);
	
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
     * <p>Description: 分页查询公告</p>
     * @author huxiaoping on 2016年5月13日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
    public PageInfo<MessageInfoDto> queryNoticeForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
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
}
