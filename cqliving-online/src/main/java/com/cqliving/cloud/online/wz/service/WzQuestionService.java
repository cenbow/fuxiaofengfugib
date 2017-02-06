package com.cqliving.cloud.online.wz.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.WzCollectInfoData;
import com.cqliving.cloud.online.interfacc.dto.WzQuestionDetailResult;
import com.cqliving.cloud.online.interfacc.dto.WzQuestionResult;
import com.cqliving.cloud.online.wz.domain.WzCollectInfo;
import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.cloud.online.wz.dto.WzQuestionData;
import com.cqliving.cloud.online.wz.dto.WzQuestionDto;
import com.cqliving.cloud.online.wz.dto.WzQuestionExcelDownload;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.tool.common.Response;

/**
 * Title:问政问题表 Service
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月10日
 */
public interface WzQuestionService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<WzQuestion>> queryForPage(PageInfo<WzQuestion> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<WzQuestion> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(WzQuestion domain);
	/** @author Code Generator *****end*****/

    /**
     * Title:问政列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月25日
     * @param conditions
     * @param page
     * @return
     */
    Response<List<WzQuestionData>> queryScrollPage(Map<String, Object> conditions, ScrollPage<WzQuestionDto> page);
    
    /**
     * Title:我的问政列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月11日
     * @param appId
     * @param token
     * @param lastId
     * @param query
     * @return
     */
    Response<List<WzQuestionDto>> getMyQuestionByPage(Long appId, String sessionId, String token, String lastId, String query);
    
    /**
     * Title:获得问政详情
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月10日
     * @param questionId
     * @return
     */
    Response<WzQuestionDto> getQuestionDetails(Long questionId);
    
    /**
     * Title:获得我的问政详情
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月11日
     * @param appId
     * @param token
     * @param questionId
     * @return
     */
    Response<WzQuestionDto> getMyQuestionDetails(Long appId, String sessionId, String token, Long questionId);
    
    
    /**
     * Title:问政新增或修改
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月11日
     * @param appId
     * @param token
     * @param id
     * @param type
     * @param regionCode
     * @param regionName
     * @param title
     * @param content
     * @param photos
     * @param place
     * @param lat
     * @param lng
     * @return 1:需要审核；0：不需要审核。返回回去为提示用
     */
    Response<Integer> saveOrModify(Long appId, String sessionId, String token, WzQuestion params, String[] photos, Long[] collectId, String[] collectValue);
    
    /**
     * Title:删除我的问政
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月11日
     * @param appId
     * @param token
     * @param questionId
     * @return
     */
    Response<Void> deleteMyQuestion(Long appId, String sessionId, String token, Long questionId);
    
    /**
     * Title:审核、批量审核
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月17日
     * @param userId
     * @param userName
     * @param questionId
     * @param status
     * @param content
     * @return
     */
    Response<Void> check(Long appId, Long userId, String userName, Long[] questionIds, Byte status, String content, String nickName);
    
    /**
     * Title:是否自动回复，返回有内容则表示是自动回复，反之则否
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月19日
     * @param appId
     * @return
     */
    String getAutoReplyContent(Long appId);
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月19日
     * @param wzQuestion
     * @return
     */
    Response<Void> update(WzQuestion wzQuestion);
    
    /**
     * Title:转交
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月20日
     * @param questionId
     * @param currentUserId
     * @param auditingDepartment
     * @param description
     * @param userId
     * @param nickName
     * @return
     */
    Response<Void> transferSave(Long questionId, Long currentUserId, String auditingDepartment, String description, Long userId, String nickName);
    
    /**
     * Title:回复
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月20日
     * @param questionId
     * @param auditingDepartment
     * @param content
     * @param userId
     * @return
     */
    Response<Void> replySave(Long appId, Long questionId, String auditingDepartment, String content, Long userId);
    /**
     * Title:发布
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月23日
     * @param ids
     * @return
     */
    Response<Void> publishSave(Long[] ids);
    
    /**
     * Title:问政第三方授权
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月31日
     * @param appId
     * @param sessionId
     * @param token
     * @param nickName
     * @param phone
     * @param headUrl
     * @param openId
     * @return
     */
    Response<UserSession> loginAuth(Long appId, String sessionId, String token, String userName, String phone, String headUrl, String openId);
    
    /**
     * Title:删除评论
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月2日
     * @param replyId
     * @return
     */
    Response<Void> delReply(Long replyId);
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月6日
     * @param appId
     * @param sessionId
     * @param token
     * @return
     */
    Response<List<WzCollectInfo>> addData(Long appId, String sessionId, String token);
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月25日
     * @param conditions
     * @param page
     * @return
     */
	public Response<CommonListResult<WzQuestionData>> getPageByWzQuestion(Map<String, Object> conditions, ScrollPage<WzQuestionDto> page);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月24日
	 * @param conditions
	 * @param page
	 * @return
	 */
	public Response<CommonListResult<WzQuestionData>> getPageByWzQuestionNew(Map<String, Object> conditions, ScrollPage<WzQuestionDto> page);
	
	/**
	 * Title:保存和修改接口
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月25日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param params
	 * @param photos
	 * @param collectIds
	 * @param collectValues
     * @return 1:需要审核；0：不需要审核。返回回去为提示用
	 */
	Response<Integer> saveI(Long appId, String sessionId, String token, WzQuestion params, String photos, String collectIds, String collectValues);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月23日
	 * @param questionId
	 * @return
	 */
	Response<WzQuestionResult> getDetails(Long questionId);

    /**
     * Title:修改问政获得详情接口用
     * <p>Description:</p>
     * @author DeweiLi on 2016年9月9日
     * @param questionId
     * @return
     */
    Response<WzQuestionDetailResult> getDetailsUseModify(Long questionId);
    
    /**
     * Title:获得用户收集的信息
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月8日
     * @param appId
     * @param questionId
     * @return
     */
    public Response<List<WzCollectInfoData>> getUserCollectInfo(Long appId, Long questionId);
    
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月18日
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	public Response<List<WzQuestionExcelDownload>> excelDownload(Map<String, Object> searchMap, Map<String, Boolean> sortMap);
}
