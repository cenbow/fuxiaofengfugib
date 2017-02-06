/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.account.domain.UserActList;
import com.cqliving.cloud.online.account.domain.UserActVote;
import com.cqliving.cloud.online.account.domain.UserShareHistory;
import com.cqliving.cloud.online.account.service.UserActListService;
import com.cqliving.cloud.online.account.service.UserActTestService;
import com.cqliving.cloud.online.account.service.UserActVoteService;
import com.cqliving.cloud.online.account.service.UserShareHistoryService;
import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.act.domain.ActTest;
import com.cqliving.cloud.online.act.domain.ActTestClassify;
import com.cqliving.cloud.online.act.domain.ActVote;
import com.cqliving.cloud.online.act.domain.ActVoteItem;
import com.cqliving.cloud.online.act.domain.UserActTestClassify;
import com.cqliving.cloud.online.act.dto.ActAnswerResult;
import com.cqliving.cloud.online.act.dto.ActInfoDto;
import com.cqliving.cloud.online.act.dto.ActTestQuestionDtoResult;
import com.cqliving.cloud.online.act.dto.ActVoteClassifyDto;
import com.cqliving.cloud.online.act.dto.ActVoteDto;
import com.cqliving.cloud.online.act.service.ActInfoListService;
import com.cqliving.cloud.online.act.service.ActInfoService;
import com.cqliving.cloud.online.act.service.ActTestAnswerService;
import com.cqliving.cloud.online.act.service.ActTestClassifyService;
import com.cqliving.cloud.online.act.service.ActTestQuestionService;
import com.cqliving.cloud.online.act.service.ActTestService;
import com.cqliving.cloud.online.act.service.ActVoteClassifyService;
import com.cqliving.cloud.online.act.service.ActVoteItemService;
import com.cqliving.cloud.online.act.service.ActVoteService;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.cloud.online.config.service.AppConfigService;
import com.cqliving.cloud.online.interfacc.dto.ActInfoData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.utils.RequestUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title:活动
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月27日
 */
@Controller
@RequestMapping(value="act")
public class ActivityController extends CommonController{
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

	@Autowired
	UserShareHistoryService userShareHistoryService;
	@Autowired
	UserActVoteService userActVoteService;
	@Autowired
	ActVoteService actVoteService;
	@Autowired
	ActVoteClassifyService actVoteClassifyService;
	@Autowired
	ActVoteItemService actVoteItemService;
	@Autowired
	AppInfoService appInfoService;
	
	@Autowired
	private ActInfoListService actInfoListService;
	@Autowired
	private ActTestService actTestService;
	@Autowired
	private ActTestQuestionService actTestQuestionService;
	@Autowired
	private ActTestAnswerService actTestAnswerService;
	@Autowired
	private ActTestClassifyService actTestClassifyService;
	@Autowired
	private ActInfoService actInfoService;
	@Autowired
	private UserActListService userActListService;
	@Autowired
	private UserActTestService userActTestService;
	@Autowired
	private AppConfigService appConfigService;
	
	private final static String VOTE_CLASSIFY_DETAIL = "/act/vote/vote_classify_detail";
	private final static String VOTE_DETAIL = "/act/vote/vote_detail";
	private final static String VOTE_ITEM_DETAIL = "/act/vote/vote_item_detail";
	private final static String VOTE_ITEM = "/act/vote/vote_item";
	
	private final static String ACT_LIST = "/act/act_list";
	private final static String ACT_LIST_PAGE = "/act/act_list_page";
	private final static String ANSWER_PAGE = "/act/answer/answer";
	private final static String ANSWER_SHARE_PAGE = "/act/answer/answer_share";
	private final static String ANSWER_CLASSIFY_LIST_PAGE = "/act/answer/answer_classify_list";
	private final static String ANSWER_QUESTION_LIST_PAGE = "/act/answer/answer_question_list";
	
	private final static String NOTICE = "/act/act_notice";
	private final static String NOTICE_SHARE = "/act/act_notice_share";
	private final static String ACT_CONTENT = "/act/act_content";
	
    @RequestMapping(value="vote/share")
	@ResponseBody
	public Response<Void> canShare(HttpServletRequest request,@RequestParam Long voteId,@RequestParam Long appId,String sessionCode,String token){
		
		return userShareHistoryService.canShareVote(voteId, appId, sessionCode, token);
	}
	
	@RequestMapping(value="vote/share_succ")
	@ResponseBody
	public Response<Void> share(HttpServletRequest request,UserShareHistory userShareHistory,String token){
		
		return userShareHistoryService.share(userShareHistory, token);
	}
	
	@RequestMapping(value="user/vote")
	@ResponseBody
	public Response<Void> saveUserVote(HttpServletRequest request,UserActVote userActVote,
			String sessionCode,String token,UserActList userActList,@RequestParam(value="itemIds[]") Long[] itemIds){
		
		userActList.setIp(RequestUtil.getRequestIpAddr(request));
		
		return userActVoteService.saveUserVote(userActVote, userActList, itemIds, sessionCode, token);
	}
	
	@RequestMapping(value="vote_detail/{aili}")
	public String voteDetail(HttpServletRequest request,@PathVariable(value="aili") Long actInfoListId){
		
		if(!this.usable(actInfoListId))return CommonController.DELETE_JSP; 
		
		ActVote actVote = actVoteService.findByActInfoListId(actInfoListId).getData();
		if(null == actVote)return CommonController.DELETE_JSP;
		Long actVoteId = actVote.getId();
		ActVoteDto actVoteDto = actVoteService.findDetailById(actVoteId).getData();
		if(CollectionUtils.isEmpty(actVoteDto.getActVoteClassifyDtos())){
			return CommonController.DELETE_JSP;
		}
		request.setAttribute("actVoteDto", actVoteDto);
		request.setAttribute("actInfo",actInfoService.get(actVote.getActInfoId()).getData());
		AppInfo appInfo = appInfoService.get(actVoteDto.getAppId()).getData();
		request.setAttribute("appInfo", appInfo);
		request.setAttribute("appId", actVoteDto.getAppId());
		request.setAttribute("joinTotal",userActListService.findTotalByInfoListId(actInfoListId).getData());
		
		request.setAttribute("appConfig",appConfigService.findByAppId(actVoteDto.getAppId()).getData());
		
		//过期提示
		canVote(request,actVoteDto);
		
		if(ActVote.TYPE1.byteValue() == actVoteDto.getType().byteValue()){
			return VOTE_DETAIL;
		}
		return VOTE_CLASSIFY_DETAIL;
	}
	
	private boolean usable(Long actInfoListId){
		
		if(null == actInfoListId)return false;
		
		ActInfoList actInfoList = actInfoListService.get(actInfoListId).getData();
		if(null == actInfoList || ActInfoList.STATUS99.byteValue() == actInfoList.getStatus().byteValue())return false;
		
		ActInfo actInfo = actInfoService.get(actInfoList.getActInfoId()).getData();
		if(null == actInfo || ActInfo.STATUS3.byteValue() != actInfo.getStatus().byteValue())return false;
		
		return true;
	}
	
	
	private boolean canVote(HttpServletRequest request,ActVoteDto actVoteDto){
		
		Date beginTime = actVoteDto.getStartTime();
		Date endTime = actVoteDto.getEndTime();
		Date now = Dates.now();
		
		if(null == beginTime || null == endTime || beginTime.after(now)){
			request.setAttribute("canVote","活动未开始，不能投票");
			return false;
		}
		if(endTime.before(now)){
			request.setAttribute("canVote","活动已过期，不能投票");
			return false;
		}
		
		return true;
	}
	
	
	@RequestMapping(value="vote_classify/{actVoteClassifyId}")
	public String voteClassify(HttpServletRequest request,@PathVariable Long actVoteClassifyId){
		
		ActVoteClassifyDto actVoteClassifyDto = actVoteClassifyService.findByClassifyId(actVoteClassifyId).getData();
		request.setAttribute("voteClassifyDto", actVoteClassifyDto);
		request.setAttribute("actVoteDto",actVoteService.get(actVoteClassifyDto.getActVoteId()).getData());
		
		return VOTE_ITEM;
	}
	
	@RequestMapping(value="vote_item/{actVoteItemId}")
	public String voteItemDetail (HttpServletRequest request,@PathVariable Long actVoteItemId,@RequestParam Long voteId){
		
		ActVoteItem actVoteItem = actVoteItemService.get(actVoteItemId).getData();
		
		ActVote actVote = actVoteService.get(voteId).getData();
		
		if(null == actVote)return CommonController.DELETE_JSP;
		
		ActVoteDto actVoteDto = new ActVoteDto();
		BeanUtils.copyProperties(actVote, actVoteDto);;
		canVote(request,actVoteDto);
		request.setAttribute("actVoteItem",actVoteItem);
		request.setAttribute("appId", actVote.getAppId());
		return VOTE_ITEM_DETAIL;
	}
	
	@RequestMapping(value="vote_join_num")
	public void getJoinNum(HttpServletRequest request,HttpServletResponse response,Long actInfoListId){
		
		Long joinNum = userActListService.findTotalByInfoListId(actInfoListId).getData();
		if(null == joinNum)joinNum = 0L;
		try {
			response.getWriter().print(joinNum);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				response.getWriter().print(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//查询剩余的投票数
	@RequestMapping(value="surpluse_vote")
	public void surplusVote(HttpServletRequest request,HttpServletResponse response,String ptoken,@RequestParam String psessionId,@RequestParam Long voteClassifyId){
		
		Integer surplus = userActVoteService.surplusVote(psessionId, ptoken, voteClassifyId).getData();
		String str = "无限制";
		if(null != surplus && surplus.intValue() != -1)
			str = String.format("你还可以投<span class=\"name_color\">%d</span>次",surplus);
		
		if(null == surplus)
		    str="投票不存在";
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			
		}
	}
	
	/**
	 * Title:活动列表
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月7日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param rangeType
	 * @param type
	 * @param lastId
	 * @param lastSortNo
	 * @param lastStartTimestamp
	 * @return
	 */
	@RequestMapping(value="/act_list")
	public String list(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, String token,
			Byte rangeType, 
			Byte type, 
			Long lastId, 
			Integer lastSortNo, 
			String lastStartTimestamp,
			String isAjaxPage){
		logger.debug("===================== 活动H5列表 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, rangeType=%d, type=%d, lastId=%d, lastSortNo=%d, lastStartTimestamp=%s", 
						appId, sessionId, token, rangeType, type, lastId, lastSortNo, lastStartTimestamp));
		//检查参数的必要性
		if (appId == null || StringUtils.isBlank(sessionId)) {
			return DELETE_JSP;
		}
		request.setAttribute("allTypes", ActInfoList.allTypes);
		request.setAttribute("appId", appId);
		if(StringUtils.isNotBlank("isAjaxPage") && "1".equals(isAjaxPage)){
			Response<CommonListResult<ActInfoData>> response = actInfoListService.getScrollPage(appId, sessionId, token, rangeType, type, lastId, lastSortNo, lastStartTimestamp, null);	
			request.setAttribute("list", response.getData().getDataList());
			return ACT_LIST_PAGE;
		}else{
			return ACT_LIST;
		}
	}
	
	/**
	 * Title:答题页面
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月21日
	 * @param request
	 * @param map
	 * @param sessionId
	 * @param token
	 * @param actInfoListId
	 * @return
	 */
	@RequestMapping("/answer/answer/{id}")
	public String answer(HttpServletRequest request, Map<String, Object> map, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam(required=false) String token, @PathVariable("id") Long actInfoListId, @RequestParam(value="isLoad", required=false) String isLoad){
		logger.debug("=======H5活动答题页面=========>>:" + String.format("appId=%d,sessionId=%s, token=%s, actInfoListId=%d", appId, sessionId, token, actInfoListId));
		Response<ActAnswerResult> result = actTestAnswerService.answer(sessionId, token, actInfoListId, isLoad);
		if(result.getCode() == -99 && result.getData() == null){
			return DELETE_JSP;
		}
		
		String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
		map.put("webPath", webPath);
		map.put("item", result.getData());
		map.put("actInfoListId", actInfoListId);
		map.put("appId", appId);
		map.put("sessionId", sessionId);
		map.put("token", token);
		if("true".equals(isLoad)){
		    boolean overdue = result.getData().getEndTime().after(new Date());
		    map.put("overdue", overdue);//过期返回false
		    return "/act/answer/answer_page";
		}else{
			AppInfo appInfo = appInfoService.get(appId).getData();
			map.put("appName", appInfo.getName());
		    return ANSWER_PAGE;
		}
	}
	
	/**
	 * Title:保存收集信息，准备开始答题
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月24日
	 * @param request
	 * @param map
	 * @param actInfoId
	 * @param actInfoListId
	 * @param optionIds 收集信息单选，多选，下拉类型的ID
	 * @param optionValues 收集信息单选，多选，下拉类型的值  【多选用“|”符号隔开】
	 * @param inputIds 收集信息输入类型的id
	 * @param inputValues 收集信息输入类型的值
	 * @return actTestId
	 */
	@ResponseBody
	@RequestMapping(value="/answer/answer_collect_save", method=RequestMethod.POST)
	public Response<Long> collectSave(HttpServletRequest request, Map<String, Object> map, 
			@RequestParam Long appId, @RequestParam String sessionId, @RequestParam String token,
			@RequestParam Long actInfoId, @RequestParam Long actInfoListId,
			Long[] optionIds, String[] optionValues,
			Long[] inputIds, String[] inputValues
	){
		String ip = ActivityController.getIp(request);
		logger.debug("====收集信息===>>>" + String.format("ip=%s", ip));
		Response<Long> res = userActListService.save(appId, sessionId, token, actInfoId, actInfoListId, optionIds, optionValues, inputIds, inputValues, ip);
		return res;
	}
	
	/**
	 * Title:决定去答题页面还是问题分类页面
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月24日
	 * @param request
	 * @param map
	 * @param actInfoId
	 * @param actTestId
	 * @param actInfoListId
	 * @return
	 */
	@RequestMapping(value="/answer/answer_classify_list")
	public String startAnswer(HttpServletRequest request, Map<String, Object> map, @RequestParam String sessionId, @RequestParam(required=false) String token, @RequestParam Long actInfoId, @RequestParam Long actTestId, @RequestParam Long actInfoListId){
		//决定是直接去答题页面还是去选择答卷页面
		List<ActTestClassify> actTestClassifyList = actTestClassifyService.getByActTest(actInfoListId, actTestId).getData();
		if(actTestClassifyList == null){
			return DELETE_JSP;
		}else if(actTestClassifyList.size() == 1){
			//直接去答题页面
			return this.questionList(request, map, sessionId, token, actTestClassifyList.get(0).getId(), 1);
		}else{
			//分类答题
			ActInfo actInfo = actInfoService.get(actInfoId).getData();
			if(actInfo == null){
				return DELETE_JSP;
			}
			map.put("appId", actInfo.getAppId());
			map.put("actInfo", actInfo);
			map.put("classifyList", actTestClassifyList);

			String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
			map.put("webPath", webPath);

			map.put("sessionId", sessionId);
			map.put("token", token);
			return ANSWER_CLASSIFY_LIST_PAGE;
		}
	}
	
	/**
	 * Title:答题列表
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月27日
	 * @param request
	 * @param map
	 * @param sessionId
	 * @param token
	 * @param classifyId
	 * @param type 1:开始答题;2:查看结果;3:继续答题
	 * @return
	 */
	@RequestMapping(value="/answer/answer_question_list")
	public String questionList(HttpServletRequest request, Map<String, Object> map, @RequestParam String sessionId, @RequestParam(required=false) String token, @RequestParam Long classifyId, Integer type){
		//获得活动配置
		ActTest actTest = actTestService.getByActTestClassify(classifyId).getData();
		if(actTest == null){
			return DELETE_JSP;
		}
		ActInfo actInfo = actInfoService.get(actTest.getActInfoId()).getData();
		if(actInfo == null){
			return DELETE_JSP;
		}
		ActTestClassify actTestClassify = actTestClassifyService.get(classifyId).getData();
		if(actTestClassify == null){
			return DELETE_JSP;
		}
		String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
		map.put("webPath", webPath);
		map.put("title", actTestClassify.getTitle());
		map.put("actInifoTitle", actInfo.getTitle());
		//获得问题和答题以及答案
		Response<ActTestQuestionDtoResult> res = actTestQuestionService.getQuestionAndAnswer(sessionId, token, classifyId, type);
		if(res.getCode() == -99){
			return DELETE_JSP;
		}
		map.put("appId", actTest.getAppId());
		map.put("actTest", actTest);
		map.put("classifyId", classifyId);
		map.put("item", res.getData());
		map.put("type", type);
		
		map.put("sessionId", sessionId);
		map.put("token", token);
		return ANSWER_QUESTION_LIST_PAGE;
	}
	
	/**
	 * Title:答题保存
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月29日
	 * @param request
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param actTestClassifyId
	 * @param startTime
	 * @param isFinish 1:完成,2:保存
	 * @param questionIds
	 * @param answerIds
	 * @param inputQuestionIds
	 * @param inputAnswerValues
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/answer/answer_question_save", method=RequestMethod.POST)
	public Response<Map<String, Object>> answerSave(HttpServletRequest request, @RequestParam Long appId, @RequestParam String sessionId, @RequestParam(required=false) String token
			, @RequestParam Long actTestClassifyId, @RequestParam Long startTime, @RequestParam Integer isFinish
			, @RequestParam(value="questionId", required=false) Long[] questionIds, @RequestParam(value="answerId", required=false) String[] answerIds
			, @RequestParam(value="inputQuestionId", required=false) Long[] inputQuestionIds, @RequestParam(value="inputAnswerValue", required=false) String[] inputAnswerValues
	){
		logger.debug("===答题保存=====>" + String.format("appId=%d, sessionId=%s, token=%s, isFinish=%d, startTime=%s", appId, sessionId, token, isFinish, startTime));
		isFinish = isFinish == null ? 0 : isFinish;
		Response<UserActTestClassify> res = userActTestService.save(appId, sessionId, token, actTestClassifyId, startTime, isFinish, questionIds, answerIds, inputQuestionIds, inputAnswerValues);
		UserActTestClassify userActTestClassify = res.getData();
		long diff = 0L;
		Map<String, Object> map = Maps.newHashMap();
		if(isFinish.equals(1) && userActTestClassify != null){//是答题提交的时候才返回这些，保存不需要返回此内容
			diff = userActTestClassify.getCreateTime().getTime() - userActTestClassify.getStartTime().getTime();
			long nd = 1000*24*60*60;//一天的毫秒数
			long nh = 1000*60*60;//一小时的毫秒数
			long nm = 1000*60;//一分钟的毫秒数
			diff = diff%nd%nh/nm;//计算差多少分钟
			map.put("diff", diff);
			map.put("score", userActTestClassify.getScore());
		}
		Response<Map<String, Object>> rs = Response.newInstance();
		rs.setData(map);
		rs.setCode(res.getCode());
		rs.setMessage(res.getMessage());
		return rs;
	}
	
	/**
	 * Title:获得IP
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月30日
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
	/**
	 * Title:答题分享
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月30日
	 * @param request
	 * @param map
	 * @param actInfoListId
	 * @return
	 */
	@RequestMapping("/answer_share/{id}")
	public String answerShare(HttpServletRequest request, Map<String, Object> map, @PathVariable("id") Long actInfoListId){
		logger.debug("========活动答题分享========>>:" + String.format("actInfoListId=%d", actInfoListId));
		ActInfoDto actInfoDto = actInfoService.findByActTest(actInfoListId).getData();
		if(actInfoDto == null){
			return DELETE_JSP;
		}
		if(!ActInfo.STATUS3.equals(actInfoDto.getStatus())){//活动如果不是发布状态
			return DELETE_JSP;
		}
		ActAnswerResult result = new ActAnswerResult();
		result.setTitle(actInfoDto.getTitle());
		List<String> actImageUrls = Lists.newArrayList();
		actImageUrls.add(actInfoDto.getActImageUrl());
		result.setActImageUrls(actImageUrls);
		result.setDigest(actInfoDto.getDigest());
		result.setContent(actInfoDto.getContent());
		result.setStartTimeStr(DateFormatUtils.format(actInfoDto.getActStartTime(), "yyyy年MM月dd日 HH:mm"));
		result.setEndTimeStr(DateFormatUtils.format(actInfoDto.getActEndTime(), "yyyy年MM月dd日 HH:mm"));
		map.put("item", result);
		AppConfig appConfig = appConfigService.findByAppId(actInfoDto.getAppId()).getData();
        String openUrl = "";
		if(appConfig != null){
			openUrl = appConfig.getDownLoadUrl();
		}
		map.put("openUrl", openUrl);
		AppInfo appInfo = appInfoService.get(actInfoDto.getAppId()).getData();
		map.put("appInName", appInfo.getName());
		map.put("appId", appInfo.getId());
		return ANSWER_SHARE_PAGE;
	}
	
	/**
	 * Title:公告、分享
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月7日
	 * @param request
	 * @param actInfoListId
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	@RequestMapping("/act_notice/{id}")
	public String notice(HttpServletRequest request, @PathVariable("id") Long actInfoListId, String share){
		logger.debug("================活动公告H5=====================>>>" + String.format("id=%d, share=%s", actInfoListId, share));
		Response<ActInfoDto> res = actInfoService.findByActInfoListId(actInfoListId);
		if(res.getData() == null){
			return DELETE_JSP;
		}
		if(!ActInfo.STATUS3.equals(res.getData().getStatus())){//活动如果不是发布状态
			return DELETE_JSP;
		}
		Long appId = res.getData().getAppId();
		request.setAttribute("item", res.getData());
		request.setAttribute("appId", appId);
		AppInfo appInfo = appInfoService.get(res.getData().getAppId()).getData();
		request.setAttribute("appName", appInfo.getName());
		if("share".equals(share)){
			String openUrl = "";
			AppConfig appConfig = appConfigService.findByAppId(res.getData().getAppId()).getData();
			if(appConfig != null){
				openUrl = appConfig.getDownLoadUrl();
			}
			request.setAttribute("openUrl", openUrl);
			return NOTICE_SHARE;
		}
		return NOTICE;
	}
	
	/**
	 * Title:活动内容
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月13日
	 * @param request
	 * @return
	 */
	@RequestMapping("/act_content/{id}")
	public String actContent(HttpServletRequest request, @PathVariable("id") Long id){
		ActInfo actInfo = actInfoService.get(id).getData();
		if(actInfo == null || !actInfo.getStatus().equals(ActInfo.STATUS3)){
			return DELETE_JSP;
		}
		Long appId = actInfo.getAppId();
		request.setAttribute("content", actInfo.getContent());
		request.setAttribute("appId", appId);
		AppInfo appInfo = appInfoService.get(appId).getData();
		request.setAttribute("appName", appInfo != null ? appInfo.getName() : "");
		return ACT_CONTENT;
	}
}
