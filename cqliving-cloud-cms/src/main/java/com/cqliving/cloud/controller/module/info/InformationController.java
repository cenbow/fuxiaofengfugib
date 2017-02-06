package com.cqliving.cloud.controller.module.info;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.domain.AppQiniu;
import com.cqliving.cloud.online.app.domain.AppResouse;
import com.cqliving.cloud.online.app.dto.AppColumnsDto;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppColumnsService;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.app.service.AppQiniuService;
import com.cqliving.cloud.online.app.service.AppResouseService;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.InfoCorrelation;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.cloud.online.info.domain.InfoLable;
import com.cqliving.cloud.online.info.domain.InfoSource;
import com.cqliving.cloud.online.info.domain.InfoTempleteImageConfig;
import com.cqliving.cloud.online.info.domain.InfoTheme;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.cloud.online.info.dto.InfoDto;
import com.cqliving.cloud.online.info.dto.InfoSourceResponse;
import com.cqliving.cloud.online.info.service.InfoClassifyListService;
import com.cqliving.cloud.online.info.service.InfoClassifyService;
import com.cqliving.cloud.online.info.service.InfoCorrelationService;
import com.cqliving.cloud.online.info.service.InfoLableService;
import com.cqliving.cloud.online.info.service.InfoSourceService;
import com.cqliving.cloud.online.info.service.InfoTempleteImageConfigService;
import com.cqliving.cloud.online.info.service.InfoThemeService;
import com.cqliving.cloud.online.info.service.InformationContentService;
import com.cqliving.cloud.online.info.service.InformationService;
import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.service.SysUserDataService;
import com.cqliving.cloud.online.survey.domain.SurveyInfo;
import com.cqliving.cloud.online.survey.domain.SurveyQuestion;
import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswer;
import com.cqliving.cloud.online.survey.service.SurveyInfoService;
import com.cqliving.cloud.online.survey.service.SurveyQuestionAnswerService;
import com.cqliving.cloud.online.survey.service.SurveyQuestionService;
import com.cqliving.framework.utils.Dates;
import com.cqliving.framework.utils.mapper.JsonMapper;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.utils.ImageUtil;
import com.cqliving.tool.utils.ImageUtil.ImageCoordinate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;


@Controller
@RequestMapping(value = "/module/info")
public class InformationController extends CommonController {

	@Autowired
	private InfoClassifyService infoClassifyService;
	@Autowired
	private InfoClassifyListService infoClassifyListService;
	@Autowired
	private InformationContentService informationContentService;
    @Autowired
    private InformationService informationService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private AppColumnsService appColumnsService;
    @Autowired
    private SysUserDataService sysUserDataService;
    @Autowired
    private InfoLableService infoLabelService;
    @Autowired
    private InfoTempleteImageConfigService infoTempleteImageConfigService;
    @Autowired
    SurveyInfoService surveyInfoService;
    @Autowired
    SurveyQuestionService surveyQuestionService;
    @Autowired
    SurveyQuestionAnswerService surveyQuestionAnswerService;
    @Autowired
    AppResouseService appResouseService;
    @Autowired
    InfoSourceService infoSourceService;
    @Autowired
	InfoThemeService infoThemeService;
    @Autowired
    InfoCorrelationService infoCorrelationService;
    @Autowired
	private AppQiniuService appQiniuService;
    
    //private final static String INFO_SOURCE_COOKIE_KEY = "infoSource";
    private final static String ADD_VOTE_JSP = "/module/info/vote";
    private final static String ADD_AUDIO_JSP = "/module/info/audio";
    private final static String ADD_ARENA_JSP = "/module/info/arena";
    private final static String ADD_VIDEO_JSP = "/module/info/video";
    private final static String ADD_SURVEY_JSP = "/module/info/survey";
    private final static String ADD_IMAGES_JSP = "/module/info/images";
    private final static String ADD_IMAGE_TEXT_JSP = "/module/info/image_text";
    private final static String SURVEY_QUESTION_MODAL = "/module/info/survey_question";
    private final static String SURVEY_ANSWER_MODAL = "/module/info/survey_answer";
    private final static String ORIGION_NEWS_VIEW = "/module/info/origion_news_view";
    
    public void init(HttpServletRequest request){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	List<AppInfoDto> listApps = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
    	//所有APP
    	request.setAttribute("allApps", listApps);
    	//列表显示类型
    	request.setAttribute("listViewTypes",InfoClassify.allListViewTypes);
    	
    	String appColumnsJson = new JsonMapper().toJson(this.findAppColumnByAppId(request,listApps.get(0).getId()));
    	request.setAttribute("appColumns",appColumnsJson);
    	//平台可见类型
    	request.setAttribute("allPlViewTypes", Information.allPlViewTypes);
    	//栏目新闻显示状态
    	request.setAttribute("allClassfieViewStatuss", InfoClassify.allClassfieViewStatuss);
    	//新闻内容类型
    	request.setAttribute("allContextTypes",Information.allContextTypes);
    	
    	//七牛
    	List<AppQiniu> list = null;
    	if(null == sessionUser.getAppId() || 0 == sessionUser.getAppId().longValue()){
    		list = appQiniuService.findByDefault(AppQiniu.ISDEFAULT1).getData();
    	}else{
    		list = appQiniuService.findByAppId(sessionUser.getAppId()).getData();
    	}
    	if(!CollectionUtils.isEmpty(list)){
        	request.setAttribute("appQiniu", list.get(0));
    	}
    	
    }

    @RequestMapping(value="common/appcolumn_appid")
    @ResponseBody
    public Response<List<AppColumnsDto>> findAppColumnByAppId(HttpServletRequest request,Long appId){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	//APP栏目
    	Map<String,Object> conditions = Maps.newHashMap();
    	conditions.put("EQ_appId",appId);
    	conditions.put("EQ_columnsType", AppColumns.COLUMNSTYPE0);
    	conditions.put("appendAll",false);
    	conditions.put("filter_sysUserDataValue",sysUserDataService.findValueByUserId(sessionUser.getUserId(), SysUserData.TYPE2).getData());
    	return appColumnsService.getByConditions(conditions);
    }
    
    //增加-保存
    @RequestMapping(value ={"info_add","info_copy","info_add_draft","save_for_audit","save_for_push","info_add_video","info_add_audio"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Information> postAdd(HttpServletRequest request,HttpServletResponse response,Information information,
    		InfoClassify infoClassify,String infoClassifyList,String appResourses,String news_copy,String infoTheme,
    		InfoFile infoFile,Long infoFileId){
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Long userId = sessionUser.getUserId();
    	String name = sessionUser.getNickname();
    	Date now = Dates.now();
    	
    	Long informationId = infoClassify.getInformationId();
    	information.setId(informationId);
    	information.setVideoStatus(Information.VIDEOSTATUS3);
    	infoClassify.setClassfieViewStatus(InfoClassify.CLASSFIEVIEWSTATUS0);
    	if(null != informationId && 0!=informationId.longValue()){//修改
    		 Information sourceInformation = informationService.get(informationId).getData();
    		 InfoClassify sourceInfoClassify = infoClassifyService.get(infoClassify.getId()).getData();
    		 if(sourceInformation==null || sourceInfoClassify == null){
                 //没有记录
                 return new Response<Information>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
             }
    		 //contextType修改，就删除infocontent表
    		 if(null != information.getContextType() && null != sourceInformation.getContextType()
    				 && sourceInformation.getContextType().byteValue() != information.getContextType().byteValue()){
    			 this.deleteInfoContent(request,informationId);
    		 }
    		 information.setCreateTime(sourceInformation.getCreateTime());
             information.setCreatorId(sourceInformation.getCreatorId());
             information.setCreator(sourceInformation.getCreator());
             information.setUpdator(sourceInformation.getUpdator());
             information.setViewCount(sourceInformation.getViewCount());
             information.setReplyCount(sourceInformation.getReplyCount());
             information.setPraiseCount(sourceInformation.getPraiseCount());
             information.setClassifyId(sourceInformation.getClassifyId());
             
             infoClassify.setAppId(sourceInfoClassify.getAppId());
             infoClassify.setCreateTime(sourceInfoClassify.getCreateTime());
             infoClassify.setCreator(sourceInfoClassify.getCreator());
             infoClassify.setCreatorId(sourceInfoClassify.getCreatorId());
             infoClassify.setReplyCount(sourceInfoClassify.getReplyCount());
             infoClassify.setSortNo(sourceInfoClassify.getSortNo());
             infoClassify.setViewCount(sourceInfoClassify.getViewCount());
             infoClassify.setAddSpecialStatus(sourceInfoClassify.getAddSpecialStatus());
             infoClassify.setOnlineTimeDate(infoClassify.getOnlineTime());
             infoClassify.setIsRecommend(null == sourceInfoClassify.getIsRecommend() ? 0 : sourceInfoClassify.getIsRecommend());
             infoClassify.setSendStatus(sourceInfoClassify.getSendStatus());
    	}else{//新增
    		information.setCreateTime(now);
            information.setCreatorId(userId);
            information.setCreator(name);
            information.setUpdator(name);
            information.setViewCount(0);
            information.setReplyCount(0);
            information.setPraiseCount(0);
            
            infoClassify.setAppId(information.getAppId());
            infoClassify.setCreateTime(information.getCreateTime());
            infoClassify.setCreator(information.getCreator());
            infoClassify.setCreatorId(information.getCreatorId());
            infoClassify.setReplyCount(0);
            infoClassify.setSortNo(Integer.MAX_VALUE);
            infoClassify.setViewCount(0);
            infoClassify.setAddSpecialStatus(InfoClassify.ADDSPECIALSTATUS0);
            infoClassify.setOnlineTimeDate(infoClassify.getOnlineTime());
            infoClassify.setSendStatus(InfoClassify.SENDSTATUS0);
            infoClassify.setIsRecommend((byte)0);
    	}
    	
    	information.setUpdateTime(now);
        information.setUpdatorId(userId);
        information.setUpdator(name);
        
        infoClassify.setUpdateTime(now);
        infoClassify.setUpdator(name);
        infoClassify.setUpdatorId(userId);
        
        String infoLabel = information.getInfoLabel();
        
        if(!StringUtil.isEmpty(infoLabel)){
        	infoLabel = ","+infoLabel+",";
        	information.setInfoLabel(infoLabel);
        }
        
        if(null == information.getAppId() || 0 == information.getAppId()){
        	information.setAppId(sessionUser.getAppId());
        	infoClassify.setAppId(sessionUser.getAppId());
        }
		
        if(!StringUtil.isEmpty(news_copy) && "news_copy".equals(news_copy)){//复制新闻
        	infoClassify.setCreateTime(now);
        	infoClassify.setCreatorId(userId);
        	infoClassify.setCreator(name);
		}
        
        //Cookie
        //this.setCookie(response,information.getInfoSource());
        
        Response<Information> res = null;
        if(information.getType().byteValue() == Information.TYPE2){//专题新闻的保存及修改
        	information.setContextType((byte)6);
        	JsonMapper jm = new JsonMapper();
      		List<InfoTheme> infoThemes = jm.fromJson(infoTheme, jm.createCollectionType(List.class, InfoTheme.class));
      		res = informationService.saveSpecialInfo(information, infoClassify, infoThemes,infoClassifyList);
            if(res.getCode() < 0){
            	return new Response<Information>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
            }
        }else{
        	 InfoDto infoDto = new InfoDto();
        	 infoDto.setInfoClassify(infoClassify);
        	 infoDto.setInfoFile(infoFile);
        	 infoDto.setInformation(information);
        	 infoDto.setInfoTheme(infoTheme);
        	 infoDto.setInfoClassifyList(infoClassifyList);
        	 infoDto.setNewsCopy(news_copy);
        	 infoDto.setAppResource(appResourses);
        	 res = informationService.createInfomation(infoDto);
             if(res.getCode() < 0){
             	return new Response<Information>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
             }
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        //特殊处理
        res.setSessionId(infoClassify.getId().toString());
        //删除缓存
        informationService.delCacheByInformationId(infoClassify.getInformationId());
        return res;
    }
    
    @RequestMapping(value={"common/update_status","common/update_audit","common/update_push"})
    public String updateStatus(HttpServletRequest request,@RequestParam Long infoClassifyId,@RequestParam Byte status){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	infoClassifyService.updateStatus(infoClassifyId, status, DateUtil.now(), sessionUser.getUserId(), sessionUser.getNickname());
    	
    	String recommendList = request.getParameter("recommendList");
    	if(!StringUtil.isEmpty(recommendList))return String.format("redirect:%s",recommendList);
    	
    	return "redirect:/module/infoClassify/info_classify_list.html";
    	
    }
    
    //修改-新增
    @RequestMapping(value ="info_add", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map, Long id,Byte contextType,String news_copy){
        
    	    map.put("contextType", contextType);
        map.put("news_copy", news_copy);
        Long defaultAppid = null;
        String back_url = (String) request.getAttribute("back_url");
        if(StringUtil.isEmpty(back_url)){
	        	back_url = request.getParameter("back_url");
	        	if(StringUtil.isEmpty(back_url)){
	        		back_url = request.getParameter("recommendList");
	            	if(StringUtil.isEmpty(back_url)){
	            		back_url = "/module/infoClassify/info_classify_list.html";
	            	}
	        	}
        }
        request.setAttribute("back_url",back_url);
	    	if(null !=id && 0!=id.longValue()){
	    		InfoClassify sourceInfoClassify = infoClassifyService.get(id).getData();
	            if(sourceInfoClassify==null){
	                //没有记录
	                return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
	            }
	            Information information = informationService.get(sourceInfoClassify.getInformationId()).getData();
	            if(information==null){
	                //没有记录
	                return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
	            }
	            defaultAppid = sourceInfoClassify.getAppId();
	            map.put("item", information);
	            map.put("infoClassify", sourceInfoClassify);
	            map.put("infoClassifyList", infoClassifyListService.findByInfoId(sourceInfoClassify.getId()).getData());
	           
	            byte contxtT = information.getContextType().byteValue();
	            if(contxtT == Information.CONTEXTTYPE4.byteValue() || contxtT == Information.CONTEXTTYPE5.byteValue()){
	            	map.put("contextType", contxtT);
	            }
	            
	            if(Information.CONTEXTTYPE2.byteValue() != contxtT){//不是原创，则必须加载新闻资源
	            	setInformationContent(request,sourceInfoClassify);
	            }
	            request.setAttribute("appColumnsDomain",appColumnsService.get(sourceInfoClassify.getColumnsId()).getData());
	            if(!StringUtil.isEmpty(news_copy)){
	            	 map.put("imgConfig", this.findImgConfigByContextType(sourceInfoClassify.getColumnsId(), 
	            			 sourceInfoClassify.getAppId(),sourceInfoClassify.getListViewType()));
	            }
	    	}else{
	    		request.setAttribute("onlineTime",Dates.now());
	    	}
    	    //初始数据
        init(request);
        //新闻来源获取
        //this.getCookie(request); 新需求要求不带入最后一次来源
        if(null == defaultAppid){
        	   List<AppInfoDto> allApps = (List<AppInfoDto>) request.getAttribute("allApps");
        	   defaultAppid = allApps.get(0).getId();
        }
        map.put("defaultAppid", defaultAppid);
        map.put("recommendList", request.getParameter("recommendList"));
        return "tiles.module.info.info_detail";
    }

    private void setInformationContent(HttpServletRequest request,InfoClassify sourceInfoClassify){
    	List<AppResouse> list = appResouseService.findByInformationId(sourceInfoClassify.getInformationId()).getData();
        request.setAttribute("appResourse",list);
    }
    
    //查找列表图片规格
    private InfoTempleteImageConfig findImgConfigByContextType(Long appColumnsId,Long appId,Byte listViewType){
    	//imgConfig listType列表图片类型{0:单图,1:大图,2:三图,3:轮播图}
    	//listViewType:列表显示类型{0:无图,1:单图,2:大图,3:多图,4:轮播}
    	Response<List<InfoTempleteImageConfig>> rp = infoTempleteImageConfigService.getByAppColumnsId(appColumnsId,appId);
    	List<InfoTempleteImageConfig> imgConfig = rp.getData();
    	if(null == listViewType){
    		return imgConfig.get(0);
    	}
    	if(CollectionUtils.isEmpty(imgConfig)){
    		return null;
    	}
    	for(InfoTempleteImageConfig config : imgConfig){
    		if(config.getListType().intValue() == listViewType.intValue()-1){
    			return config;
    		}
    	}
    	return null;
    }
    
    //查看
    @RequestMapping(value ="common/find_info_source")
    @ResponseBody
    public InfoSourceResponse findSource(HttpServletRequest request,String keyword,Long appId){
    	
    	InfoSourceResponse isr = new InfoSourceResponse();
    	Map<String,Object> conditions = Maps.newHashMap();
    	conditions.put("LIKE_name",keyword);
    	conditions.put("EQ_appId",appId);
    	conditions.put("EQ_status", InfoSource.status3);
    	if(null == appId || 0 == appId.longValue())return isr;
    	
    	Response<List<InfoSource>> rp = infoSourceService.findByConditions(conditions);
    	isr.setData(rp.getData());
    	isr.setCode(rp.getCode());
    	
    	return isr;
    	
    }
    
    /*private void setCookie(HttpServletResponse response,String cookieValue){
    	try {
			Cookie cookie = new Cookie(INFO_SOURCE_COOKIE_KEY,URLEncoder.encode(cookieValue, "utf-8"));
			cookie.setPath("/");
			cookie.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }*/

    /*private void getCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
    	
    	for(Cookie cookie : cookies){
    		String name = cookie.getName();
    		if(name.equals(INFO_SOURCE_COOKIE_KEY)){
    			try {
					String value = URLDecoder.decode(cookie.getValue(), "utf-8");
					request.setAttribute(INFO_SOURCE_COOKIE_KEY,value);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
    		}
    	}
    }*/
    
    @RequestMapping(value="common/info_label_byappid")
    @ResponseBody
    public Response<List<InfoLable>> findByAppId(HttpServletRequest request,Long appId){
    	
        if(null == appId || 0l == appId){
        	appId = SessionFace.getSessionUser(request).getAppId();
        }    	
    	return infoLabelService.findByAppId(appId, BusinessType.SOURCE_TYPE_1); 
    	
    }
    
    //根据栏目取图片规格
    @RequestMapping(value="common/info_templete_image_config")
    @ResponseBody
    public Response<List<InfoTempleteImageConfig>> getByAppColumnsId(HttpServletRequest request,Long appColumnsId,Long appId){
    	Response<List<InfoTempleteImageConfig>> rp = infoTempleteImageConfigService.getByAppColumnsId(appColumnsId,appId);
    	return rp;
    }
    
    //添加图文
    @RequestMapping(value="add_image_text")
    public String addImageText(HttpServletRequest request,Long infoContentId){
    	
    	request.setAttribute("appResourse",appResouseService.findByInfoContentId(infoContentId).getData());
    	
    	return ADD_IMAGE_TEXT_JSP;
    }
    //添加多图
    @RequestMapping(value="add_images")
    public String addImages(HttpServletRequest request,Long infoContentId){
    	
    	
    	request.setAttribute("appResourse",appResouseService.findByInfoContentId(infoContentId).getData());
    	
    	return ADD_IMAGES_JSP;
    }
    //添加音频
    @RequestMapping(value="add_audio")
    public String addAudio(HttpServletRequest request,Long infoContentId){
    	
    	request.setAttribute("appResourse",appResouseService.findByInfoContentId(infoContentId).getData());
    	
    	return ADD_AUDIO_JSP;
    }
    //添加视频
    @RequestMapping(value="add_video")
    public String addVideo(HttpServletRequest request,Long infoContentId){
    	
    	request.setAttribute("appResourse",appResouseService.findByInfoContentId(infoContentId).getData());
    	
    	return ADD_VIDEO_JSP;
    }
    //添加投票
    @RequestMapping(value="add_vote")
    public String addVote(HttpServletRequest request,Long infoContentId){
    	
    	request.setAttribute("surveyInfoDto",surveyInfoService.findByInfoContentId(infoContentId).getData());
    	
    	return ADD_VOTE_JSP;
    }
    //添加打擂
    @RequestMapping(value="add_arena")
    public String addArena(HttpServletRequest request,Long infoContentId){
    	
    	request.setAttribute("surveyInfoDto",surveyInfoService.findByInfoContentId(infoContentId).getData());
    	
    	return ADD_ARENA_JSP;
    }
    //添加调研
    @RequestMapping(value="add_survey")
    public String addSurvey(HttpServletRequest request,Long infoContentId){
    	
    	request.setAttribute("surveyInfoDto",surveyInfoService.findByInfoContentId(infoContentId).getData());
    	
    	return ADD_SURVEY_JSP;
    }
    
    @ResponseBody
    @RequestMapping(value="common/save_app_resourse")
    public Response<AppResouse> saveAppResouse(HttpServletRequest request,String appResourses,InfoFile infoFile){
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	
    	infoFile.setCreateTime(Dates.now());
    	infoFile.setCreator(sessionUser.getNickname());
    	infoFile.setCreatorId(sessionUser.getUserId());
    	
    	JsonMapper jm = new JsonMapper();
    	AppResouse[] resourse = jm.fromJson(appResourses, AppResouse[].class);
    	
    	Response<AppResouse> rp = appResouseService.saveAppResouse(resourse,infoFile);
    	//设置图片数量
    	Integer imgNum = informationService.setMultiImgNum(resourse[0].getInformationId()).getData();
    	
    	rp.setSessionId(imgNum.toString());
    	
    	return rp;
    }
    
    @RequestMapping(value="common/save_vote")
    @ResponseBody
    public Response<SurveyInfo> saveVote(HttpServletRequest request,SurveyInfo surveyInfo,String surveyAnswers){
    	
    	ObjectMapper om = new ObjectMapper();
    	om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    	SurveyQuestionAnswer[] an = null;
    	try {
			an = om.readValue(surveyAnswers, SurveyQuestionAnswer[].class);
		} catch ( IOException e) {
			e.printStackTrace();
		}
    	
    	return surveyInfoService.saveVote(surveyInfo,an);
    }
    
    @RequestMapping(value="common/save_survey")
    @ResponseBody
    public Response<SurveyInfo> saveSurvey(HttpServletRequest request,SurveyInfo surveyInfo,@RequestParam(value="questionIds[]")Long[] questionIds){
    	
    	return surveyInfoService.saveSurvey(surveyInfo, questionIds);
    }
    
    //保存打雷
    @RequestMapping(value="common/save_arena")
    @ResponseBody
    public Response<SurveyInfo> saveArena(HttpServletRequest request,SurveyInfo surveyInfo,String answers){
    	
    	SurveyQuestionAnswer[] surveyAnswers = null;
    	
    	ObjectMapper om = new ObjectMapper();
    	om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    	try {
    		surveyAnswers = om.readValue(answers, SurveyQuestionAnswer[].class);
		} catch ( IOException e) {
			e.printStackTrace();
		}
    	return surveyInfoService.saveArena(surveyInfo, Arrays.asList(surveyAnswers));
    	
    }
    
    //调研问题查找
    @RequestMapping(value="common/survey_question")
    public String surveyQuestion(HttpServletRequest request,SurveyQuestion surveyQuestion){
    	
    	Long id =  surveyQuestion.getId();

		SurveyQuestion sqlSurveyQuestion = null;

    	if(null != id && 0!=id){
    		sqlSurveyQuestion = surveyQuestionService.get(id).getData();
    	}
    	
    	if(null == sqlSurveyQuestion)sqlSurveyQuestion = surveyQuestion;
    	
    	request.setAttribute("question",sqlSurveyQuestion);
    	
    	return SURVEY_QUESTION_MODAL;
    }
    //调研问题保存
    @RequestMapping(value="common/save_survey_question")
    @ResponseBody
    public Response<SurveyQuestion> saveSurveyQuestion(HttpServletRequest request,SurveyQuestion surveyQuestion){
    	
    	Response<SurveyQuestion> rp = Response.newInstance();
    	Response<Void> resp = surveyQuestionService.save(surveyQuestion);
    	rp.setData(surveyQuestion);
    	rp.setCode(resp.getCode());
    	return rp;
    	
    }
    //调研问题删除
    @RequestMapping(value="common/del_survey_question")
    @ResponseBody
    public Response<Void> delSurveyQuestion(HttpServletRequest request,Long questionId){
    	
    	return surveyQuestionService.deleteSurveyQuestion(questionId);
    	
    }
    //调研答案查找
    @RequestMapping(value="common/survey_answer")
    public String surveyAnswer(HttpServletRequest request,SurveyQuestionAnswer surveyQuestionAnswer){
    	
    	Long answerId = surveyQuestionAnswer.getId();
    	
    	SurveyQuestionAnswer sqlSurveyQuestionAnswer = null;
    	if(null != answerId && 0 != answerId){
    		 sqlSurveyQuestionAnswer = surveyQuestionAnswerService.get(answerId).getData();
    	}
    	
    	if(null == sqlSurveyQuestionAnswer)
    		sqlSurveyQuestionAnswer = surveyQuestionAnswer;
    	
    	request.setAttribute("answer",sqlSurveyQuestionAnswer);
    	
    	return SURVEY_ANSWER_MODAL;
    }
    
    //调研答案保存
    @RequestMapping(value="common/save_survey_answer")
    @ResponseBody
    public Response<SurveyQuestionAnswer> saveSurveyAnswer(HttpServletRequest request,SurveyQuestionAnswer surveyQuestionAnswer){

    	Response<SurveyQuestionAnswer> rp = Response.newInstance();
    	surveyQuestionAnswer.setCreateTime(Dates.now());
    	Response<Void> resp = surveyQuestionAnswerService.save(surveyQuestionAnswer);
    	rp.setData(surveyQuestionAnswer);
    	rp.setCode(resp.getCode());
    	rp.setMessage(resp.getMessage());
    	return rp;
    	
    }
    
    //调研答案删除
    @RequestMapping(value="common/del_survey_answer")
    @ResponseBody
    public Response<Void> delSurveyAnswer(HttpServletRequest request,Long answerId){

    	return surveyQuestionAnswerService.updateStatus(answerId,SurveyQuestionAnswer.STATUS99);
    	
    }
    

    //删除原创新闻
    @ResponseBody
    @RequestMapping(value="common/delete_origion_news")
    public Response<Void> deleteOrigionNews(@RequestParam Long infocontentId,Long informationId,Byte isViewWechat,Long appId){
    	//删除缓存
    	if(null != informationId){
        informationService.delCacheByInformationId(informationId);
    	}
    	return informationService.deleteOrigionNews(infocontentId,isViewWechat,appId);
    	
    }
    
    //删除新闻内容
    public Response<Void> deleteInfoContent(HttpServletRequest request,@RequestParam Long infoId){
    	
    	return informationService.deleteInfoContent(infoId);
    }
    
    //原创回显到新闻里面
    @RequestMapping(value="common/origion_news_view")
    public String origionNewsView(HttpServletRequest request,@RequestParam Long infoId){
    	
    	request.setAttribute("contentAllTypes",InformationContent.allTypes);
    	request.setAttribute("contentAllTypesI18N",InformationContent.allTypesI18n);
    	request.setAttribute("informationContents",informationContentService.findByInfoId(infoId).getData());
    	return ORIGION_NEWS_VIEW;
    }
    
    @RequestMapping(value="common/info_content_sort")
    @ResponseBody
    public Response<Void> infoContentSort(HttpServletRequest request,@RequestParam(value="sorts[]") Integer[] sorts,
    		@RequestParam(value="contentIds[]") Long[] contentIds,Long informationId){
    	//删除缓存
    	if(null != informationId){
    		informationService.delCacheByInformationId(informationId);
    	}
    	return informationContentService.infoContentSort(sorts, contentIds);
    }
    
    @ResponseBody
    @RequestMapping(value="common/cut_image")
    public Response<String> cutImge(HttpServletRequest request,ImageCoordinate imageCoordinate,String fileUrlPath){
    	
    	Response<String> rp = Response.newInstance();
    	try {
			String fileLocalPath = PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH);
			String fileUrl = PropertiesConfig.getString(PropertyKey.FILE_URL_PATH);
			String newFileLocalPath = fileUrlPath.replace(fileUrl,fileLocalPath);
			String outPath = ImageUtil.cutImage(newFileLocalPath, "", imageCoordinate);
			outPath = outPath.replace(fileLocalPath, fileUrl);
			rp.setData(outPath);
		} catch (Exception e) {
			e.printStackTrace();
			rp.setCode(ErrorCodes.FAILURE);
			rp.setMessage("图片裁剪失败");
		}
    	return rp;
    	
    }
    
    //新增及修改
  	@RequestMapping(value="special_info_add",method = RequestMethod.GET)
  	public String addSpecial(HttpServletRequest request,Long id,Map<String,Object> map,String back_url){
  		//初始化一些东西，要想知道是什么个人自己看，我这儿坚决抵制伸手党
  		init(request);
  		
  		request.setAttribute("special_info_add","special_info_add");
  		
  		if(null != id && 0 != id){
  			InfoClassify infoClassify = infoClassifyService.get(id).getData();
              if(null == infoClassify){
              	 //没有记录
                  return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
              }
  			request.setAttribute("infoThemes",infoThemeService.findByInfoClassifyId(id).getData());
  			request.setAttribute("item",informationService.get(infoClassify.getInformationId()).getData());
            request.setAttribute("infoClassify",infoClassify);		
            request.setAttribute("appColumnsDomain",appColumnsService.get(infoClassify.getColumnsId()).getData());
            request.setAttribute("infoClassifyList", infoClassifyListService.findByInfoId(infoClassify.getId()).getData());
  		}
  		//this.getCookie(request);  不带入最后一次输入来源
  		if(StringUtil.isEmpty(back_url)){
  			back_url = "/module/infoClassify/info_classify_list_special.html";
  		}
  		request.setAttribute("back_url",back_url);
  		request.setAttribute("onlineTime",Dates.now());
  		return "tiles.module.info.info_detail";
  	}
  	
  	//删除专题分类
  	@ResponseBody
  	@RequestMapping(value="common/dele_info_theme")
  	public Response<Void> delInfoTheme(HttpServletRequest request,Long infoThemeId){
  		
  		return informationService.delInfoTheme(infoThemeId);
  	}
  	
  	
    //添加子新闻
    @RequestMapping(value ="add_special_sub_info", method = RequestMethod.GET)
    public String addSpecialSubInfo(HttpServletRequest request, Map<String, Object> map,@RequestParam Long icid,Long refInfoId){
        
    	map.put("info_Themes",infoThemeService.findByInfoClassifyId(icid).getData());
    	
    	if(null != refInfoId && 0 != refInfoId){
    		//专题新闻分类ID
            List<InfoCorrelation> infoCorres =  infoCorrelationService.findByInfoIdAndRefId(icid, refInfoId).getData();
            
            if(CollectionUtils.isEmpty(infoCorres)){
            	 //没有记录
                return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
            }
            map.put("infoCorrelation",infoCorres.get(0));
    	}
    	String appId = request.getParameter("appId");
    	request.setAttribute("back_url",String.format("/module/infoClassify/info_classify_list_special_sub.html?mid=%s&appId=%s",icid,appId));
    	return this.update(request, map,refInfoId, null, null);
    }
    
    //推荐新闻编辑(先复制再编辑)
    @RequestMapping(value="info_classify_recommed_edit")
    public String infoClassifyRecommedEdit(HttpServletRequest request,@RequestParam Long commentId){
    	
    	InfoClassify infoClassify = infoClassifyService.infoClassifyRecommedEdit(commentId).getData();
    	
    	String recommendList = "/module/infoClassify/info_classify_list_recommend.html?navLm=XWTJGL";
    	
    	return String.format("redirect:/module/info/info_add.html?id=%s&recommendList=%s",infoClassify.getId(),recommendList);
    	
    }
    
    @ResponseBody
    @RequestMapping(value="common/qiniu_by_appid")
    public Response<AppQiniu> findAppQiniuByAppId(HttpServletRequest request,Long appId){
    	
    	Response<AppQiniu> rp = Response.newInstance();
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	//七牛
    	List<AppQiniu> list = null;
    	if(null != appId && 0 != appId){
    		list = appQiniuService.findByAppId(appId).getData();
    	}
    	if(CollectionUtils.isEmpty(list) && null != sessionUser.getAppId() && 0 != sessionUser.getAppId().longValue()){
    		list = appQiniuService.findByAppId(sessionUser.getAppId()).getData();
    	}
        if(CollectionUtils.isEmpty(list)){
    		list = appQiniuService.findByDefault(AppQiniu.ISDEFAULT1).getData();
    	}
    	if(!CollectionUtils.isEmpty(list)){
    		rp.setData(list.get(0));
    	}
    	return rp;
    }
    
    
    @RequestMapping(value="common/info_view")
    public String infomationView(HttpServletRequest request,@RequestParam Long infoId){
    	
    	String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
    	
    	InfoClassify infoClassify = infoClassifyService.get(infoId).getData();
    	Information information = informationService.get(infoClassify.getInformationId()).getData();
    	
    	String uri = "info/detail/"+infoId+".html?noev=noev";
    	if(Information.TYPE2.byteValue() == information.getType()){
    		uri = "topic/detail/"+infoId+".html?noev=noev";
    	}
    	request.setAttribute("infoViewUrl",webPath + uri);
    	
    	return "/module/info/info_view";
    }
    
    @RequestMapping(value="common/info_theme_byclassifyid")
    public String loadInfoTheme(HttpServletRequest request,@RequestParam Long infoClassifyId){
    	
    	request.setAttribute("infoThemes",infoThemeService.findByInfoClassifyId(infoClassifyId).getData());
    	
    	return "/module/info/special_info_theme";
    }
}
