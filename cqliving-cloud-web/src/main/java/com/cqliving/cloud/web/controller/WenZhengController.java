package com.cqliving.cloud.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
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

import com.cqliving.basic.domain.Option;
import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.basic.service.BasicService;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.domain.UserReport;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.dto.UserInfoReplyDto;
import com.cqliving.cloud.online.account.service.UserAccountService;
import com.cqliving.cloud.online.account.service.UserInfoReplyService;
import com.cqliving.cloud.online.account.service.UserInfoService;
import com.cqliving.cloud.online.account.service.UserReportService;
import com.cqliving.cloud.online.account.service.UserSessionService;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.service.AppConfigService;
import com.cqliving.cloud.online.config.service.ConfigRegionService;
import com.cqliving.cloud.online.interfacc.dto.CommentsData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.MyCommentsData;
import com.cqliving.cloud.online.wz.domain.WzAppAuthority;
import com.cqliving.cloud.online.wz.domain.WzAuthority;
import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.cloud.online.wz.dto.WzAppAuthorityDto;
import com.cqliving.cloud.online.wz.dto.WzQuestionData;
import com.cqliving.cloud.online.wz.dto.WzQuestionDto;
import com.cqliving.cloud.online.wz.service.WzAuthorityService;
import com.cqliving.cloud.online.wz.service.WzQuestionCollectInfoService;
import com.cqliving.cloud.online.wz.service.WzQuestionImageService;
import com.cqliving.cloud.online.wz.service.WzQuestionService;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title: 和原生交互的问政
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年7月21日
 */

@Controller
@RequestMapping(value = "/wenzheng")
public class WenZhengController {
	
	private static final Logger logger = LoggerFactory.getLogger(WenZhengController.class);
	
	 @Autowired
	    private WzQuestionService wzQuestionService;
	    @Autowired
	    private UserSessionService userSessionService;
	    @Autowired
	    private WzQuestionImageService wzQuestionImageService;
	    @Autowired
	    private WzAuthorityService wzAuthorityService;
	    @Autowired
	    private ConfigRegionService configRegionService;
	    @Autowired
	    private WzQuestionCollectInfoService wzQuestionCollectInfoService;
	    @Autowired
	    private AppInfoService appInfoService;
	    @Autowired
	    private UserInfoReplyService userInfoReplyService;
	    @Autowired
	    private BasicService basicService;
	    @Autowired
	    private UserReportService userReportService;
	    @Autowired
	    private AppConfigService appConfigService;
	    @Autowired
	    private UserAccountService userAccountService;
	    @Autowired
	    private UserInfoService userInfoService;
	    
	    private final static String DELETE_JSP = "wenzheng/common/detelte_status";
	    private final static String LIST_PAGE = "wenzheng/wenzheng_list";
	    private final static String VIEW_PAGE = "wenzheng/wenzheng_view";
	    private final static String ADD_PAGE = "wenzheng/wenzheng_add";
	    private final static String LIST_MY_PAGE = "wenzheng/wenzheng_my_list";
	    private final static String LIST_MY_REPLY_PAGE = "wenzheng/wenzheng_my_reply_list";
	    private final static String SHARE_PAGE = "wenzheng/wenzheng_share";
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月21日
	 * @param request
	 * @param map
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type
	 * @param status
	 * @param sortColumn
	 * @param sortType
	 * @param lastValue
	 * @param lastId
	 * @param queryValue
	 * @param isSearch
	 * @param isAjaxPage
	 * @return
	 */
	 @RequestMapping(value="list")
	 public String list(HttpServletRequest request, Map<String, Object> map, @RequestParam Long appId, @RequestParam String sessionId
	            , @RequestParam(required=false) String token
	            , @RequestParam(required = false) Byte type
	            , @RequestParam(required = false, defaultValue="0") Byte status
	            , @RequestParam(required = false) String sortColumn
	            , @RequestParam(required = false) String sortType
	            , @RequestParam(required = false) Long lastValue
	            , @RequestParam(required = false) Long lastId
	            , @RequestParam(required = false) String queryValue
	            , @RequestParam(required = false) String isSearch
	            , @RequestParam(required = false) String isAjaxPage
    ){
    	logger.debug("==========="+isAjaxPage+"=============>" + String.format("appId=%d, sessionId=%s, token=%s, isAjaxPage=%s, queryValue=%s, isSearch=%s", appId, sessionId, token, isAjaxPage, queryValue, isSearch));
        //用户不存在就创建游客用户
    	UserSession userSession = userSessionService.get(sessionId, token).getData();
    	if(userSession == null){
    		userAccountService.createTourist(appId, sessionId);
    		userSession = userSessionService.getTourist(sessionId).getData();
    	}
    	if(userSession == null){
    		return DELETE_JSP;
    	}
        map.put("sessionId", userSession.getSessionCode());
        map.put("token", userSession.getToken());
        map.put("appId", appId);
        
        int  pageSize = PropertiesConfig.getInteger(PropertyKey.WZ_LIST_PAGE_SIZE);
        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("EQ_appId", appId);
        conditions.put("LIKE_title", queryValue);
        conditions.put("LIKE_type", type);
        switch (status) {
        case 1://已回复
            conditions.put("EQ_status", WzQuestion.STATUS7);
            break;
        case 2://已受理
            conditions.put("IN_status", WzQuestion.STATUS3 + "," + WzQuestion.STATUS6);
            break;
        case 3://已转交
            conditions.put("IN_status", WzQuestion.STATUS4 + "," + WzQuestion.STATUS5);
            break;
        default:
            List<Byte> listByte = Lists.newArrayList();
            listByte.add(WzQuestion.STATUS3);
            listByte.add(WzQuestion.STATUS4);
            listByte.add(WzQuestion.STATUS5);
            listByte.add(WzQuestion.STATUS6);
            listByte.add(WzQuestion.STATUS7);
            conditions.put("IN_status", listByte);
            break;
        }

        map.put("allStatuss", WzQuestion.allStatuss);
        map.put("allTypes", WzQuestion.allTypes);
        map.put("pageSize", pageSize);
        String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
        map.put("webPath", webPath);
        if(StringUtils.isNotBlank(isAjaxPage)){
            ScrollPage<WzQuestionDto> page = new ScrollPage<>();
            page.setPageSize(pageSize);
            if (StringUtils.isNotBlank(sortColumn) && StringUtils.isNotBlank(sortType)) {
                page.addScrollPageOrder(new ScrollPageOrder(sortColumn, sortType, lastValue));
            }
            page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
            Response<List<WzQuestionData>> res = wzQuestionService.queryScrollPage(conditions, page);
            map.put("list", res.getData());
            return LIST_PAGE + "_page";
        }else if(StringUtils.isNotBlank(isSearch)){
            map.put("queryValue", queryValue);
            return LIST_PAGE + "_search";
        }else{
            map.put("sortColumn", sortColumn);
            map.put("sortType", sortType);
            map.put("type", type);
            map.put("status", status);
            return LIST_PAGE;
        }
    }
	 
 	/**
 	 * Title:查看
 	 * <p>Description:</p>
 	 * @author DeweiLi on 2016年7月21日
 	 * @param request
 	 * @param map
 	 * @param questionId
 	 * @return
 	 */
	@RequestMapping("view/{id}")
	public String view(HttpServletRequest request, Map<String, Object> map, @PathVariable(value="id") Long questionId){
		logger.debug("=============H5问政查看==============>>>>" + String.format("id=%d", questionId));
		WzQuestion wzQuestion = wzQuestionService.get(questionId).getData();
		if(wzQuestion == null || WzQuestion.STATUS88.equals(wzQuestion.getStatus()) || WzQuestion.STATUS99.equals(wzQuestion.getStatus())){
			return DELETE_JSP;
		}
		//阅读量加1
		wzQuestion.setViewCount(wzQuestion.getViewCount() + 1);
		wzQuestionService.update(wzQuestion);
		if(wzQuestion != null){
			if(!WzQuestion.STATUS7.equals(wzQuestion.getStatus()) && !WzQuestion.STATUS88.equals(wzQuestion.getStatus())){
				wzQuestion.setReplyContent("");
				wzQuestion.setReplyTime(null);
			}
		}
		Long appId = wzQuestion.getAppId();
		map.put("question", wzQuestion);
		map.put("appId", appId);
		AppInfo appInfo = appInfoService.get(appId).getData();
		map.put("appName", appInfo != null ? appInfo.getName() : "");
		String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
        map.put("webPath", webPath);
		//获得图片
		map.put("imageList", wzQuestionImageService.getByQuestion(wzQuestion.getId()).getData());
		return VIEW_PAGE;
	}
	
	/**
	 * Title:登录验证
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月21日
	 * @param request
	 * @param sessionId
	 * @param token
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="login_validate", method= RequestMethod.POST)
    public Response<Void> loginValidate(HttpServletRequest request, @RequestParam String sessionId, @RequestParam String token){
    	logger.debug("===========H5问政验证登录=============>" + String.format("sessionId=%s, token=%s", sessionId, token));
        Response<Void> rs = Response.newInstance();
        UserSession userSession = userSessionService.get(sessionId, token).getData();
        if(userSession == null){
            rs.setCode(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode());
            rs.setSessionId(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
            return rs;
        }
        //读取设置：未登录是否允许问政
        if(StringUtils.isBlank(userSession.getToken())){
            //是否允许游客问政
            WzAppAuthorityDto wzAppAuthorityDto = wzAuthorityService.getAppAuthByAuthName(userSession.getAppId(), WzAuthority.NAME_ALLOW_TOURIST_PUBLISH).getData();
            if(WzAppAuthority.VALUE0.equals(wzAppAuthorityDto.getValue())){
                rs.setCode(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode());
                rs.setSessionId(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
                return rs;
            }
        }
        return rs;
    }
	
	/**
	 * Title:新增、修改
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月21日
	 * @param request
	 * @param map
	 * @param appId
	 * @param id
	 * @return
	 */
	@RequestMapping(value="add/{appId}", method=RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map, @PathVariable("appId") Long appId, @RequestParam(required=false) Long id){
        //读取设置：收集信息
        WzAppAuthorityDto wzAppAuthorityDto = wzAuthorityService.getAppAuthByAuthName(appId, WzAuthority.NAME_IS_COLLECT_USER_INFO).getData();
        if(WzAppAuthority.VALUE1.equals(wzAppAuthorityDto.getValue())){
            map.put("collectList", wzAppAuthorityDto.getCollectInfoList());
        }
        WzQuestion wzQuestion = new WzQuestion();
        if(id != null){
            wzQuestion = wzQuestionService.get(id).getData();
            if(wzQuestion == null || WzQuestion.STATUS88.equals(wzQuestion.getStatus()) || WzQuestion.STATUS99.equals(wzQuestion.getStatus())){
            	return DELETE_JSP;
            }
        }
        map.put("question", wzQuestion);
        map.put("allTypes", WzQuestion.allTypes);
        map.put("appId", appId);
        //区域
        List<ConfigRegion> regionList = configRegionService.getByAppAndType(new Long[]{appId}, ConfigRegion.TYPE2).getData();
        map.put("regionList", regionList);
        if(wzQuestion.getId() != null){
            //获得图片
            map.put("imageList", wzQuestionImageService.getByQuestion(wzQuestion.getId()).getData());
            //获得收集内容
            map.put("collentListValue", wzQuestionCollectInfoService.getInfoByCollect(wzQuestion.getId(), null).getData());
        }
        //返回app默认的区域名称
        AppInfo appInfo = appInfoService.get(appId).getData();
        map.put("appRegionName", appInfo.getLocation());
        return ADD_PAGE;
    }
	
	/**
	 * Title:新增和修改
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月21日
	 * @param request
	 * @param params
	 * @param sessionId
	 * @param token
	 * @param photos
	 * @param collectId
	 * @param collectValue
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="add", method=RequestMethod.POST)
    public Response<Integer> addPost(HttpServletRequest request, 
            WzQuestion params, 
            @RequestParam String sessionId,
            @RequestParam String token,
            @RequestParam(required = false) String[] photos, 
            @RequestParam(required = false) Long[] collectId, 
            @RequestParam(required = false) String[] collectValue){
        Response<Integer> rs = Response.newInstance();
        //验证参数
        if (params == null || params.getAppId() == null || StringUtils.isBlank(sessionId) || params.getType() == null || !WzQuestion.allTypes.containsKey(params.getType()) || StringUtils.isBlank(params.getTitle()) || StringUtils.isBlank(params.getContent()) || StringUtils.isBlank(params.getRegionCode()) || StringUtils.isBlank(params.getRegionName())) {
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzQuestionService.saveOrModify(params.getAppId(), sessionId, token, params, photos, collectId, collectValue);
        return rs;
    }
	
	/**
	 * Title:搜索页面
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月21日
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("search")
    public String search(HttpServletRequest request, Map<String, Object> map, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token){
		map.put("appId", appId);
		map.put("sessionId", sessionId);
		map.put("token", token);
        return LIST_PAGE + "_search";
    }
	
	/**
	 * Title:我的问政列表
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月21日
	 * @param request
	 * @param map
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lastId
	 * @param isAjaxPage
	 * @return
	 */
	@RequestMapping("wenzheng_my_list")
    public String myList(HttpServletRequest request, Map<String, Object> map, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam(required=false) Long lastId, @RequestParam(required=false) String isAjaxPage){
		UserSession userSession = userSessionService.get(sessionId, token).getData();
        int  pageSize = PropertiesConfig.getInteger(PropertyKey.WZ_LIST_PAGE_SIZE);
        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("EQ_appId", appId);
        conditions.put("EQ_creatorId", userSession.getUserId());
        conditions.put("NOTEQ_status", WzQuestion.STATUS99);
        
        ScrollPage<WzQuestionDto> page = new ScrollPage<>();
        page.setPageSize(pageSize);
        page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
        
        Response<List<WzQuestionData>> res = wzQuestionService.queryScrollPage(conditions, page);
        
        String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
        map.put("webPath", webPath);
        
        map.put("list", res.getData());
        map.put("pageSize", pageSize);
        map.put("appId", appId);
        if(StringUtils.isNotBlank(isAjaxPage)){
            return LIST_MY_PAGE + "_page";
        }else{
            return LIST_MY_PAGE;
        }
    }
	
	/**
	 * Title:我的评论列表
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月21日
	 * @param request
	 * @param map
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param queryType
	 * @param queryValue
	 * @param lastId
	 * @param isAjaxPage
	 * @return
	 */
	@RequestMapping("wenzheng_my_reply_list")
    public String myReply(HttpServletRequest request, Map<String, Object> map, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam(required=false) String queryType, @RequestParam(required=false) String queryValue, @RequestParam(required=false) Long lastId, @RequestParam(required=false) String isAjaxPage){
        UserSession userSession = userSessionService.get(sessionId, token).getData();
        if(userSession == null){
            return "wz/wz_login";
        }
        String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
        map.put("webPath", webPath);
        map.put("appId", appId);
        map.put("queryType", queryType);
        if(StringUtils.isNotBlank(isAjaxPage)){
        	int type = "2".equals(queryType) ? 2 : 1;
        	Response<CommonListResult<MyCommentsData>> rs = userInfoReplyService.queryWzScrollPage(userSession.getAppId(), userSession.getSessionCode(), userSession.getToken(), type, UserInfoReply.SOURCETYPE2, lastId);
            map.put("list", rs.getData().getDataList());
            map.put("pageSize", 10);
            return LIST_MY_REPLY_PAGE + "_page";
        }else{
        	 //获得举报类型
            List<Option> reportTypeList = basicService.getOptionListByType(Option.TYPECODE1);
            map.put("reportTypeList", reportTypeList);
            return LIST_MY_REPLY_PAGE;
        }
    }
	
	/**
	 * Title:我的问政删除
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月21日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param id
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="wenzheng_my_del", method=RequestMethod.POST)
    public Response<Void> del(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long id){
        Response<Void> rs = Response.newInstance();
        rs = wzQuestionService.deleteMyQuestion(appId, sessionId, token, id);
        return rs;
    }
	
	/**
	 * Title:我的评论删除
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月21日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param id
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="wenzheng_my_reply_del", method=RequestMethod.POST)
    public Response<Void> myReplyDel(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long id){
        Response<Void> rs = Response.newInstance();
        rs = wzQuestionService.delReply(id);
        return rs;
    }
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月21日
	 * @param request
	 * @param commentId
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="report_validate", method=RequestMethod.POST)
    public Response<Boolean> reportValidate(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long commentId){
        Response<Boolean> rs = Response.newInstance();
        UserSession userSession = userSessionService.get(sessionId, token).getData();
        Response<List<UserReport>> res = userReportService.getByUserAndSourceId(userSession.getAppId(), userSession.getUserId(), commentId, UserReport.SOURCETYPE2);
        rs.setCode(res.getCode());
        rs.setMessage(res.getMessage());
        rs.setData(res.getData().size() > 0);
        return rs;
    }
    
    /**
     * Title:多选举报
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月27日
     * @param request
     * @param reportId
     * @param code
     */
    @ResponseBody
    @RequestMapping(value="report_save", method=RequestMethod.POST)
    public Response<Void> reportSave(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long reportId, @RequestParam(value="codes[]") String[] codes){
        Response<Void> rs = Response.newInstance();
        UserSession userSession = userSessionService.get(sessionId, token).getData();
        String reportCode = "";
        if(codes != null && codes.length > 0){
        	for(String str : codes){
        		if(StringUtils.isNotBlank(reportCode)){
        			reportCode += ",";
        		}
        		reportCode += str;
        	}
        }
        rs = userReportService.saveUserReport(userSession.getAppId(), userSession.getSessionCode(), userSession.getToken(), null, reportId, (byte)1, UserReport.SOURCETYPE2, reportCode);
        return rs;
    }
    
    /**
     * Title:分享
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月21日
     * @param request
     * @param map
     * @param questionId
     * @return
     */
    @RequestMapping(value="share/{id}")
    public String share(HttpServletRequest request, Map<String, Object> map, @PathVariable(value="id") Long questionId){
        WzQuestion wzQuestion = wzQuestionService.get(questionId).getData();

        if(wzQuestion == null || wzQuestion.getStatus() > 7 || wzQuestion.getStatus() < 3){
        	if(WzQuestion.STATUS2.equals(wzQuestion.getStatus())){
        		map.put("errorMsg", "对不起，该问政正在审核中。");
        	}
        	map.put("appId", wzQuestion != null ? wzQuestion.getAppId() : null);
        	return DELETE_JSP;
        }
        map.put("question", wzQuestion);
        
        //评论查询
        int  pageSize = 5;
        ScrollPage<UserInfoReplyDto> page = new ScrollPage<>();
        page.setPageSize(pageSize);
        page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, null));
        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("EQ_sourceId", questionId);
        conditions.put("EQ_sourceType", UserInfoReply.SOURCETYPE2);
        conditions.put("EQ_status", UserInfoReply.STATUS3);
        
        Response<ScrollPage<UserInfoReplyDto>> rs = userInfoReplyService.queryScrollPage(page, conditions, UserInfoReply.SOURCETYPE2);
        List<UserInfoReplyDto> dtos = rs.getData().getPageResults();
        
		List<CommentsData> comments = Lists.newArrayList();
		CommentsData data;
		if (CollectionUtils.isNotEmpty(dtos)) {
			boolean isAnonymous = false;	//是否匿名评论
			for (UserInfoReplyDto dto : dtos) {
				isAnonymous = UserInfoReply.TYPE1.equals(dto.getType());
				data = new CommentsData();
				data.setCommentTime(DateUtil.convertTimeToFormatHore1(dto.getCreateTime().getTime()));
				data.setContent(dto.getContent());
				data.setHeaderImg(isAnonymous ? "" : dto.getImgUrl());
				data.setIsPraised(dto.getIsPraised());
				data.setPassiveReplyContent(dto.getPassiveReplyContent());
				data.setPassiveReplyName(dto.getPassiveReplyName());
				data.setPlace(dto.getPlace());
				data.setPraiseCount(dto.getPraise());
				data.setReplyCount(dto.getReplyCount());
				data.setReplyId(dto.getId());
				data.setUserId(dto.getReplyUserId());
				data.setUserName(isAnonymous ? dto.getAnonymousName() : dto.getNickname());
				comments.add(data);
			}
		}
		map.put("appId", wzQuestion.getAppId());
        map.put("replyList", comments);
        map.put("pageSize", rs.getData().getPageSize());
        map.put("pageType", "share");
        //获得图片
        map.put("imageList", wzQuestionImageService.getByQuestion(wzQuestion.getId()).getData());
        //获得举报类型
        List<Option> reportTypeList = basicService.getOptionListByType(Option.TYPECODE1);
        map.put("reportTypeList", reportTypeList);
        
        AppConfig appConfig = appConfigService.findByAppId(wzQuestion.getAppId()).getData();
        String openUrl = "";
		if(appConfig != null){
			openUrl = appConfig.getDownLoadUrl();
		}
		map.put("openUrl", openUrl);
		map.put("appId", wzQuestion.getAppId());
		AppInfo appInfo = appInfoService.get(wzQuestion.getAppId()).getData();
		map.put("appInName", appInfo.getName());
        return SHARE_PAGE;
    }
    
    /**
     * Title:评论保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月26日
     * @param request
     * @param id
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value="wz_question_reply_save", method=RequestMethod.POST)
    public Response<Void> replySave(HttpServletRequest request, @RequestParam String sessionId, @RequestParam String token, @RequestParam Long id, @RequestParam String content, @RequestParam(required=false) Long commentId){
        Response<Void> rs = Response.newInstance();
        UserSession userSession = userSessionService.get(sessionId, token).getData();
        if(userSession == null){
            rs.setCode(ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc());
            return rs;
        }
        String passiveReplyName = null; 
        Long passiveReplyId = null;
        if(commentId != null && commentId > 0){
            UserInfoReply userInfoReply = userInfoReplyService.get(commentId).getData();
            if(userInfoReply != null){
	            UserInfo userInfo = userInfoService.get(userInfoReply.getReplyUserId()).getData();
	            if(userInfo != null){
	            	passiveReplyName = userInfo.getName();
	            }
	            passiveReplyId = userInfoReply.getReplyUserId();
            }
        }
        rs = userInfoReplyService.add(userSession.getAppId(), userSession.getSessionCode(), userSession.getToken(), id, UserInfoReply.SOURCETYPE2, null, null, null, commentId, passiveReplyName, passiveReplyId, content, false);
        return rs;        
    }
}
