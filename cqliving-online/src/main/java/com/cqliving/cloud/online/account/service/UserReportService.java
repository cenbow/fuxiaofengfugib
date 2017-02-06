package com.cqliving.cloud.online.account.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserReport;
import com.cqliving.cloud.online.account.dto.UserReportDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户举报表 Service
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserReportService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserReport>> queryForPage(PageInfo<UserReport> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserReport> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(UserReport domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 举报（新闻、评论）</p>
	 * @author Tangtao on 2016年5月26日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param content
	 * @param sourceId
	 * @param type
	 * @param sourceType 
	 * @param reportCodes
	 * @return
	 */
	Response<Void> saveUserReport(Long appId, String sessionId, String token, String content, Long sourceId, Byte type, Byte sourceType, String reportCodes);
	
	/**
     * 获取单个举报信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月31日上午10:26:49
     */
    public Response<UserReportDto> getByIdAndSourceType(Long id, Byte sourceType,Byte type);
    
    /**
     * 审核
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月31日下午2:54:40
     */
    public Response<Void> auditing(UserReport report,Long... ids);
    
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
    Response<List<UserReport>> getByUserAndSourceId(Long appId, Long userId, Long sourceId, Byte sourceType);
}