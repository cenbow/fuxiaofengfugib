package com.cqliving.cloud.online.account.manager;

import java.util.List;

import com.cqliving.cloud.online.account.domain.UserReport;
import com.cqliving.cloud.online.account.dto.UserReportDto;
import com.cqliving.framework.common.service.EntityService;

/**
 * 用户举报表 Manager
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserReportManager extends EntityService<UserReport> {

	/**
	 * <p>Description: 举报</p>
	 * @author Tangtao on 2016年5月26日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param content
	 * @param sourceId
	 * @param type
	 * @param sourceType
	 * @param reportCodes
	 */
	void saveUserReport(Long appId, String sessionId, String token, String content, Long sourceId, Byte type, Byte sourceType, String reportCodes);

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
     * 获取单个举报信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月31日上午10:26:49
     */
	public UserReportDto getByIdAndSourceType(Long id, Byte sourceType,Byte type);
	
	/**
	 * 审核
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年5月31日下午2:54:40
	 */
	public void auditing(UserReport report,Long... ids);
	
	/**
	 * Title:获得用户评论、新闻的举报集合
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月14日
	 * @param appId
	 * @param userId
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	List<UserReport> getByUserAndSourceId(Long appId, Long userId, Long sourceId, Byte sourceType);
}