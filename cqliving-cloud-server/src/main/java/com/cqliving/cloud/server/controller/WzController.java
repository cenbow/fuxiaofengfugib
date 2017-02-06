/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.server.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.domain.UserReport;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.service.UserAccountService;
import com.cqliving.cloud.online.account.service.UserInfoReplyService;
import com.cqliving.cloud.online.account.service.UserReportService;
import com.cqliving.cloud.online.account.service.UserSessionService;
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.service.ConfigRegionService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.MyCommentsData;
import com.cqliving.cloud.online.interfacc.dto.WzQuestionDetailResult;
import com.cqliving.cloud.online.interfacc.dto.WzQuestionResult;
import com.cqliving.cloud.online.wz.domain.WzAuthority;
import com.cqliving.cloud.online.wz.domain.WzCollectInfo;
import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.cloud.online.wz.dto.WzAppAuthorityDto;
import com.cqliving.cloud.online.wz.dto.WzQuestionData;
import com.cqliving.cloud.online.wz.dto.WzQuestionDto;
import com.cqliving.cloud.online.wz.service.WzAuthorityService;
import com.cqliving.cloud.online.wz.service.WzQuestionService;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title:问政接口相关
 * <p>Description:所有加了deprecated 注释的方法都是旧版本的方法。像万州，等app所使用的方法。在不清楚的情况下请勿动，以免以前未升级的app无法使用。</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月10日
 */
@Controller
@RequestMapping(value = "/wz")
public class WzController {
	
    private static final Logger logger = LoggerFactory.getLogger(WzController.class);
    
    @Autowired
    private WzQuestionService wzQuestionService;
    @Autowired
    private WzAuthorityService wzAuthorityService;
    @Autowired
    private ConfigRegionService configRegionService;
    @Autowired
    private UserSessionService userSessionService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserReportService userReportService;
    @Autowired
    private UserInfoReplyService userInfoReplyService;
    
    /**
     * Title:问政列表
     * @deprecated
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月10日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param status
     * @param sortColumn 排序字段
     * @param sortType 排序方式｛asc，desc｝
     * @param lastValue 要排序字段的最后一条值
     * @param lastId 上一页最后一条数据的id，第二次查询必须要有该值
     * @param query
     * @return
     */
    @RequestMapping(value = { "politics" }, method = { RequestMethod.POST })
    @ResponseBody
    public Response<List<WzQuestionData>> politics(HttpServletRequest request, 
            @RequestParam Long appId,
            @RequestParam String sessionId,
            @RequestParam(required = false) String token,
            @RequestParam(required = false) Byte type,
            @RequestParam(required = false, defaultValue="0") Byte status,
            @RequestParam(required = false) String sortColumn,
            @RequestParam(required = false) String sortType,
            @RequestParam(required = false) String lastValue,
            @RequestParam(required = false) String lastId,
            @RequestParam(required = false) String query
            ) {
    	logger.debug("=========问政列表==========>>appId=" + appId +";stauts="+ status +";type="+ type + ";sortColumn=" + sortColumn + ";sortType="+sortType + ";lastValue=" + lastValue + ";lastId=" + lastId + ";query=" + query);
        Response<List<WzQuestionData>> rs = Response.newInstance();
        //检查参数的必要性
        if (appId == null || StringUtils.isBlank(sessionId)) {
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        int pageSize = 10;
        
        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("EQ_appId", appId);
        conditions.put("LIKE_title", query);
        conditions.put("LIKE_type", type != null && 0 == type ? null : type);
    	List<Byte> listByte = Lists.newArrayList();
        switch (status) {
        case 1://已回复
            conditions.put("EQ_status", WzQuestion.STATUS7);
            break;
        case 2://已受理
            listByte.add(WzQuestion.STATUS3);
            listByte.add(WzQuestion.STATUS6);
            conditions.put("IN_status", listByte);
            break;
        case 3://已转交
            listByte.add(WzQuestion.STATUS4);
            listByte.add(WzQuestion.STATUS5);
            conditions.put("IN_status", listByte);
            break;
        default:
            listByte.add(WzQuestion.STATUS3);
            listByte.add(WzQuestion.STATUS4);
            listByte.add(WzQuestion.STATUS5);
            listByte.add(WzQuestion.STATUS6);
            listByte.add(WzQuestion.STATUS7);
            conditions.put("IN_status", listByte);
            break;
        }
        
        ScrollPage<WzQuestionDto> page = new ScrollPage<>();
        page.setPageSize(pageSize);
        if (StringUtils.isNotBlank(sortColumn) && StringUtils.isNotBlank(sortType)) {
            page.addScrollPageOrder(new ScrollPageOrder(sortColumn, sortType, StringUtils.isBlank(lastValue) ? null : lastValue));
        }
        page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, StringUtils.isBlank(lastId) ? null : lastId));
        rs = wzQuestionService.queryScrollPage(conditions, page);
        return rs;
    }
    
    
    /**
     * Title:问政详情
     * @deprecated
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月10日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "details" }, method = { RequestMethod.POST })
    public Response<WzQuestionDto> details(HttpServletRequest request,
            @RequestParam String appId,
            @RequestParam String sessionId,
            @RequestParam(required=false) String token,
            @RequestParam Long id){
        Response<WzQuestionDto> rs = Response.newInstance();
        logger.debug("=========问政详情接口===========>>appId:" + appId + ";sessionId:" + sessionId + ";token:"  + token + ";id:"  + id);
        //参数验证
        if(appId == null || StringUtils.isBlank(sessionId) || id == null || id < 1){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzQuestionService.getQuestionDetails(id);
        return rs;
    }
    
    /**
     * Title:问政提交、修改
     * @deprecated
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月11日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param params
     * @param photos
     * @param collectId
     * @param collectValue
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "politicsSave" }, method = { RequestMethod.POST })
    public Response<Integer> politicsSave(HttpServletRequest request,
            @RequestParam Long appId,
            @RequestParam String sessionId,
            @RequestParam(required=false) String token,
            WzQuestion params,
            @RequestParam(required = false) String[] photos,
            @RequestParam(required = false) Long[] collectId,
            @RequestParam(required = false) String[] collectValue){
    	logger.debug("===========问政提交、修改接口=============>>id=" + params.getId() + ";regionCode=" + params.getRegionCode() + ";regionName=" + params.getRegionName());
        Response<Integer> rs = Response.newInstance();
        //验证参数
        if (appId == null || StringUtils.isBlank(sessionId) || params == null || params.getType() == null || !WzQuestion.allTypes.containsKey(params.getType()) || StringUtils.isBlank(params.getTitle()) || StringUtils.isBlank(params.getContent()) || StringUtils.isBlank(params.getRegionCode()) || StringUtils.isBlank(params.getRegionName())) {
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzQuestionService.saveOrModify(appId, sessionId, token, params, photos, collectId, collectValue);
        return rs;
    }
    
    /**
     * Title:我的问政列表
     * @deprecated
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月10日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param query
     * @param pkLastValue
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "myPoliticsList" }, method = { RequestMethod.POST })
    public Response<List<WzQuestionData>> myPoliticsList(HttpServletRequest request,
            @RequestParam Long appId,
            @RequestParam String sessionId,
            String token,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String lastId){

    	logger.debug("=========问政列表==========>>appId=" + appId + ";lastId=" + lastId + ";query=" + query);
        Response<List<WzQuestionData>> rs = Response.newInstance();
        //检查参数的必要性
        if (appId == null || StringUtils.isBlank(sessionId)) {
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        int pageSize = 10;
        
        UserSession userSession = null;
    	if(StringUtils.isNotBlank(token)){
    		userSession = userSessionService.getByToken(token).getData();
    	}
    	if(userSession == null){
    		userSession = userSessionService.getTourist(sessionId).getData();
    		if(userSession == null){
    			userAccountService.createTourist(appId, sessionId);
    			userSession = userSessionService.getTourist(sessionId).getData();
    		}
    	}
        
        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("EQ_appId", appId);
        conditions.put("LIKE_title", query);
        conditions.put("EQ_creatorId", userSession.getUserId());
        conditions.put("NOTEQ_status", WzQuestion.STATUS99);// 删除的不查询
        
        ScrollPage<WzQuestionDto> page = new ScrollPage<>();
        page.setPageSize(pageSize);
        page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, StringUtils.isBlank(lastId) ? null : lastId));
        rs = wzQuestionService.queryScrollPage(conditions, page);
        return rs;
    }
    
    /**
     * Title:我的问政删除
     * @deprecated
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月10日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "myPoliticsDel" }, method = { RequestMethod.POST })
    public Response<Void> myPoliticsDel(HttpServletRequest request,
            @RequestParam Long appId,
            @RequestParam String sessionId,
            String token,
            @RequestParam Long id){
    	logger.debug("============我的问政删除接口============>>appId=" + appId + ";id=" + id);
        Response<Void> rs = Response.newInstance();
        //检查参数的必要性
        if (appId == null || StringUtils.isBlank(sessionId)) {
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzQuestionService.deleteMyQuestion(appId, sessionId, token, id);
        return rs;
    }
    
    /**
     * Title:我的问政详情
     * @deprecated
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月10日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "myPolitics" }, method = { RequestMethod.POST })
    public Response<WzQuestionDto> myPolitics(HttpServletRequest request,
            @RequestParam Long appId,
            @RequestParam String sessionId,
            String token,
            @RequestParam Long id){
    	logger.debug("============我的问政详情，修改用============>>appId=" + appId + ";id=" + id);
        Response<WzQuestionDto> rs = Response.newInstance();
        //参数验证
        if(appId == null || StringUtils.isBlank(sessionId) || id == null || id < 1){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzQuestionService.getMyQuestionDetails(appId, sessionId, token, id);
        return rs;
    }
    
    /**
     * Title:问政是否允许游客发布的配置信息。
     * @deprecated
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月12日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param type 1:问政信息是否允许游客发布;2:问政是否收集信息
     * @return 
     */
    @ResponseBody
    @RequestMapping(value = { "getConfig" }, method = { RequestMethod.POST })
    public Response<WzAppAuthorityDto> getConfig(HttpServletRequest request,
            @RequestParam Long appId,
            @RequestParam String sessionId,
            String token,
            @RequestParam Integer type){
        Response<WzAppAuthorityDto> rs = Response.newInstance();
        logger.debug("======获得问政配置=======>>appId:" + appId + ";type:" + type);
        //参数验证
        if(appId == null || StringUtils.isBlank(sessionId) || type == null){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        String configName = "";
        switch (type) {
			case 1:
				configName = WzAuthority.NAME_ALLOW_TOURIST_PUBLISH;
				break;
			case 2:
				configName = WzAuthority.NAME_IS_COLLECT_USER_INFO;
				break;
			default:
				rs.setCode(ErrorCodes.FAILURE);
				rs.setMessage("设置不存在");
				break;
		}
        if(!"".equals(configName)){
        	rs = wzAuthorityService.getAppAuthByAuthName(appId, configName);
        }
        return rs;
    }
    
    /**
     * Title:添加问政的登录验证和返回收集信息
     * @deprecated
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月6日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @return 如果是游客：设置了不允许游客问政，就会返回code:999992
     * 		如果条见允许问政就会返回code：0，且数据有收集信息的数据
     */
    @ResponseBody
    @RequestMapping(value = { "addData" }, method = { RequestMethod.POST })
    public Response<List<WzCollectInfo>> addData(HttpServletRequest request,
            @RequestParam Long appId,
            @RequestParam String sessionId,
            String token){
    	logger.debug("============我要问政验证和返回收集信息接口============>>appId=" + appId);
        Response<List<WzCollectInfo>> rs = Response.newInstance();
        //参数验证
        if(appId == null || StringUtils.isBlank(sessionId)){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzQuestionService.addData(appId, sessionId, token);
        return rs;
    }
    
    /**
     * <p>Description:问政第三方登录接口</p>
     * @author DeweiLi on 2016年6月14日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param openId
     * @param userName
     * @param headUrl
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value="loginAuth", method= RequestMethod.POST)
    public Response<String> login(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, 
            @RequestParam(required=false) String token, 
            @RequestParam(value="userId", required=false) String openId,
            @RequestParam(required=false) String userName,
            @RequestParam(required=false) String headUrl,
            @RequestParam(required=false) String phone){
        Response<String> rs = Response.newInstance();
        logger.debug("=========第三方登录=============>>appId:" + appId + ";sessionId:" + sessionId + ";token:" + token + ";openId:" + openId + ";userName:" + userName + ";headUrl:" + headUrl + ";phone:" + phone);
        if(appId == null || StringUtils.isBlank(sessionId)){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        Response<UserSession> res = wzQuestionService.loginAuth(appId, sessionId, token, userName, phone, headUrl, openId);
        rs.setCode(res.getCode());
        rs.setMessage(res.getMessage());
        rs.setData(res.getData().getToken());
        return rs;
    }
    
    /**
     * Title:验证用户是否已经举报过
     * @deprecated
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月18日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param replyId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="reportValidate", method=RequestMethod.POST)
    public Response<Boolean> reportValidate(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long replyId){
        Response<Boolean> rs = Response.newInstance();
        logger.debug("=======验证是否举报==============>>" +  String.format("请求参数：appId=%d, sessionId=%s, token=%s, replyId=%d", appId, sessionId, token, replyId));
        if(appId == null || StringUtils.isBlank(sessionId)){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        UserSession userSession = userSessionService.getByToken(token).getData();
        if(userSession == null)
        	userSession = userSessionService.getTourist(sessionId).getData();
        
        Response<List<UserReport>> res = userReportService.getByUserAndSourceId(appId, userSession.getUserId(), replyId, UserReport.SOURCETYPE2);
        rs.setCode(res.getCode());
        rs.setMessage(res.getMessage());
        rs.setData(res.getData().size() > 0);
        return rs;
    }
    
    /**
     * Title:问政的举报，可以多选
     * @deprecated
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月18日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param replyId
     * @param codes
     * @return
     */
    @ResponseBody
    @RequestMapping(value="reportSave", method=RequestMethod.POST)
    public Response<Void> reportSave(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long replyId, @RequestParam(value="codes[]") String[] codes){
    	logger.debug("=======验证是否举报==============>>" +  String.format("请求参数：appId=%d, sessionId=%s, token=%s, replyId=%d", appId, sessionId, token, replyId));
        Response<Void> rs = Response.newInstance();
        if(appId == null || StringUtils.isBlank(sessionId)){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        String reportCode = "";
        if(codes != null && codes.length > 0){
        	for(String str : codes){
        		if(StringUtils.isNotBlank(reportCode)){
        			reportCode += ",";
        		}
        		reportCode += str;
        	}
        }
        rs = userReportService.saveUserReport(appId, sessionId, token, null, replyId, (byte)1, UserReport.SOURCETYPE2, reportCode);
        return rs;
    }
    
    /*--------------------------------新接口开始------------------------------*/
    
    /**
     * Title:新的问政列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月19日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param type
     * @param status
     * @param sortColumn
     * @param sortType
     * @param lastValue
     * @param lastId
     * @param query
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "list" }, method = { RequestMethod.POST })
    public Response<CommonListResult<WzQuestionData>> list(HttpServletRequest request, 
    		@RequestParam Long appId,
    		@RequestParam String sessionId,
    		@RequestParam(required = false) String token,
    		@RequestParam(required = false) Byte type,
    		@RequestParam(required = false, defaultValue="0") Byte status,
    		@RequestParam(required = false) String sortColumn,
    		@RequestParam(required = false) String sortType,
    		@RequestParam(required = false) String lastValue,
    		@RequestParam(required = false) String lastId,
    		@RequestParam(required = false) String query
    		) {
    	logger.debug("=========问政列表==========>>appId=" + appId +";sessionId="+sessionId+";stauts="+ status +";type="+ type + ";sortColumn=" + sortColumn + ";sortType="+sortType + ";lastValue=" + lastValue + ";lastId=" + lastId + ";query=" + query);
    	Response<CommonListResult<WzQuestionData>> rs = Response.newInstance();
    	//检查参数的必要性
    	if (appId == null || StringUtils.isBlank(sessionId)) {
    		rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
    		rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    		return rs;
    	}
    	int pageSize = 10;
    	
    	Map<String, Object> conditions = Maps.newHashMap();
    	conditions.put("EQ_appId", appId);
    	conditions.put("LIKE_title", query);
    	conditions.put("LIKE_type", type != null && 0 == type ? null : type);
    	List<Byte> listByte = Lists.newArrayList();
    	switch (status) {
    	case 1://已回复
    		conditions.put("EQ_status", WzQuestion.STATUS7);
    		break;
    	case 2://已受理
    		listByte.add(WzQuestion.STATUS3);
    		listByte.add(WzQuestion.STATUS6);
    		conditions.put("IN_status", listByte);
    		break;
    	case 3://已转交
    		listByte.add(WzQuestion.STATUS4);
    		listByte.add(WzQuestion.STATUS5);
    		conditions.put("IN_status", listByte);
    		break;
    	default:
    		listByte.add(WzQuestion.STATUS3);
    		listByte.add(WzQuestion.STATUS4);
    		listByte.add(WzQuestion.STATUS5);
    		listByte.add(WzQuestion.STATUS6);
    		listByte.add(WzQuestion.STATUS7);
    		conditions.put("IN_status", listByte);
    		break;
    	}
    	
    	ScrollPage<WzQuestionDto> page = new ScrollPage<>();
    	page.setPageSize(pageSize);
    	if (StringUtils.isNotBlank(sortColumn) && StringUtils.isNotBlank(sortType)) {
    		page.addScrollPageOrder(new ScrollPageOrder(sortColumn, sortType, StringUtils.isBlank(lastValue) ? null : lastValue));
    	}
    	page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, StringUtils.isBlank(lastId) ? null : lastId));
    	rs = wzQuestionService.getPageByWzQuestionNew(conditions, page);
    	return rs;
    }
    
    /**
     * Title:新的问政详情
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月19日
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "detail/{id}", method = { RequestMethod.POST })
    public Response<WzQuestionResult> detail(HttpServletRequest request, @PathVariable(value="id") Long id){
    	Response<WzQuestionResult> rs = Response.newInstance();
    	logger.debug("=========问政详情接口 new===========>>"  + String.format("id=%d", id));
    	//参数验证
    	if(id == null || id < 1){
    		rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
    		rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    		return rs;
    	}
    	rs = wzQuestionService.getDetails(id);
    	logger.debug("--------------问政详情接口返回数据----------------->>" + rs);
    	return rs;
    }
    
    /**
     * Title:新的问政保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月25日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param params
     * @param photos
     * @param collectId
     * @param collectValue
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "save" }, method = { RequestMethod.POST })
    public Response<Integer> save(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, String token, WzQuestion params, String photos, String collectIds, String collectValues){
    	logger.debug("===========问政提交、修改接口=============>>" + String.format("appId=%d, sessionId=%s, token=%s, id=%d, collectIds=%s, collectValues=%s", appId, sessionId, token, params.getId(), collectIds, collectValues));
        Response<Integer> rs = Response.newInstance();
        if (appId == null || StringUtils.isBlank(sessionId) || params == null || params.getType() == null || !WzQuestion.allTypes.containsKey(params.getType()) || StringUtils.isBlank(params.getTitle()) || StringUtils.isBlank(params.getContent()) || StringUtils.isBlank(params.getRegionCode()) || StringUtils.isBlank(params.getRegionName())) {
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzQuestionService.saveI(appId, sessionId, token, params, photos, collectIds, collectValues);
        if(0 == rs.getCode()){
        	Integer tmp = 1;
        	rs.setMessage(tmp.equals(rs.getData()) ? "发布成功，审核中" : "发布成功");
        }
        return rs;
    }
    
    /**
     * Title:添加问政的登录验证和返回收集信息
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月25日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @return 如果是游客：设置了不允许游客问政，就会返回code:999992
     * 		如果条见允许问政就会返回code：0，且数据有收集信息的数据
     */
    @ResponseBody
    @RequestMapping(value = { "add" }, method = { RequestMethod.POST })
    public Response<List<WzCollectInfo>> add(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, String token){
    	logger.debug("========我要问政验证和返回收集信息接口=========>>" + String.format("appId=%d, sessionId=%s, token=%s", appId, sessionId, token));
        Response<List<WzCollectInfo>> rs = Response.newInstance();
        //参数验证
        if(appId == null || StringUtils.isBlank(sessionId)){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzQuestionService.addData(appId, sessionId, token);
        return rs;
    }
    
    /**
     * Title:我的问政列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月25日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param query
     * @param lastId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "myList" }, method = { RequestMethod.POST })
    public Response<CommonListResult<WzQuestionData>> myWzList(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, String token, @RequestParam(required = false) String query,  @RequestParam(required = false) String lastId){
    	logger.debug("========我的问政列表=========>>" + String.format("appId=%d, sessionId=%s, token=%s, query=%s, lastId=%s", appId, sessionId, token, query, lastId));
        Response<CommonListResult<WzQuestionData>> rs = Response.newInstance();
        //检查参数的必要性
        if (appId == null || StringUtils.isBlank(sessionId)) {
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        int pageSize = 10;
        
        UserSession userSession = userSessionService.get(sessionId, token).getData();
    	if(userSession == null){
			userAccountService.createTourist(appId, sessionId);
		}
        
        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("EQ_appId", appId);
        conditions.put("LIKE_title", query);
        conditions.put("EQ_creatorId", userSession.getUserId());
        List<Byte> notInStatus = Lists.newArrayList();
        notInStatus.add(WzQuestion.STATUS99);// 删除的不查询
        notInStatus.add(WzQuestion.STATUS88);// 已下线的不查询
        conditions.put("NOTIN_status", notInStatus);
        
        ScrollPage<WzQuestionDto> page = new ScrollPage<>();
        page.setPageSize(pageSize);
        page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, StringUtils.isBlank(lastId) ? null : lastId));
    	rs = wzQuestionService.getPageByWzQuestion(conditions, page);
        return rs;
    }
    
    /**
     * Title:删除
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月25日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "del" }, method = { RequestMethod.POST })
    public Response<Void> del(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, String token, @RequestParam Long id){
    	logger.debug("============我的问政删除接口============>>" + String.format("appId=%d, sessionId=%s, token=%s, id=%d", appId, sessionId, token, id));
        Response<Void> rs = Response.newInstance();
        if (appId == null || StringUtils.isBlank(sessionId)) {
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzQuestionService.deleteMyQuestion(appId, sessionId, token, id);
        return rs;
    }
    
    /**
     * Title:新的问政详情 修改用
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月19日
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "modify/{id}", method = { RequestMethod.POST })
    public Response<WzQuestionDetailResult> detailByModify(HttpServletRequest request, @PathVariable(value="id") Long id){
    	Response<WzQuestionDetailResult> rs = Response.newInstance();
    	logger.debug("=========问政详情修改接口 new===========>>"  + String.format("id=%d", id));
    	//参数验证
    	if(id == null || id < 1){
    		rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
    		rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    		return rs;
    	}
    	rs = wzQuestionService.getDetailsUseModify(id);
    	logger.debug("--------------问政详情修改接口返回数据----------------->>" + rs);
    	return rs;
    }
    
    /**
     * Title:我的评论列表和我收到的评论列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月25日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param type
     * @param lastReplyId
     * @return
     */
    @ResponseBody
	@RequestMapping(value = {"myComments"}, method = {RequestMethod.POST, RequestMethod.GET})
	public Response<CommonListResult<MyCommentsData>> getMyComments(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String sessionId, 
			String token, 
			@RequestParam Integer type, 
			Long lastReplyId) {
		String typeStr = (type != null && type.intValue() == 1) ? "我的评价" :  (type != null && type.intValue() == 2) ? "收到的评价" : "未知";
		logger.debug("===================== 调用获取我的评论接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, type=%d[%s], lastReplyId=%s",  appId, sessionId, token, type, typeStr, lastReplyId));
		Response<CommonListResult<MyCommentsData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId) || "未知".equals(typeStr)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取我的评论异常：" + response);
			return response;
		}
    	UserSession userSession = userSessionService.get(sessionId, token).getData();
    	if(userSession == null){
			userAccountService.createTourist(appId, sessionId);
		}
		response = userInfoReplyService.getPageByUser(appId, sessionId, token, type, lastReplyId, UserInfoReply.SOURCETYPE2);
		logger.debug("调用获取我的评论结果：" + response);
		return response;
	}
    
    /**
     * Title:区域列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月10日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param query
     * @return
     */
    @ResponseBody
    @RequestMapping(value = { "region" }, method = { RequestMethod.POST })
    public Response<List<ConfigRegion>> region(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, String token, String query){
    	logger.debug("============问政获取区域接口============>>" + String.format("appId=%d, sessionId=%s, token=%s, query=%s", appId, sessionId, token, query));
        Response<List<ConfigRegion>> rs = Response.newInstance();
        rs = configRegionService.getByAppAndType(appId, ConfigRegion.TYPE2, query);
        return rs;
    }
}
