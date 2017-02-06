package com.cqliving.cloud.controller.module.topic;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.common.UpAndDownloadUtils;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.CommentAppConfig;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.domain.ConfigCommonType;
import com.cqliving.cloud.online.config.service.CommentAppConfigService;
import com.cqliving.cloud.online.config.service.CommentConfigService;
import com.cqliving.cloud.online.config.service.ConfigCommonTypeService;
import com.cqliving.cloud.online.topic.domain.TopicDefaultImage;
import com.cqliving.cloud.online.topic.domain.TopicImage;
import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.cloud.online.topic.service.TopicDefaultImageService;
import com.cqliving.cloud.online.topic.service.TopicImageService;
import com.cqliving.cloud.online.topic.service.TopicInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.utils.ImageUtil;
import com.google.common.collect.Maps;

/**
 * Title:话题
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年7月14日
 */
@Controller
@RequestMapping(value = "/module/topic")
public class TopicInfoController extends CommonController {
	
	private static Logger logger = LoggerFactory.getLogger(TopicInfoController.class);

    @Autowired
    private TopicInfoService topicInfoService;
    @Autowired
    private TopicImageService topicImageService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
	private CommentAppConfigService commentAppConfigService;
	@Autowired
	private CommentConfigService commentConfigService;
	@Autowired
	private TopicDefaultImageService topicDefaultImageService;
	@Autowired
	private ConfigCommonTypeService configCommonTypeService;

    //列表
    @RequestMapping(value ="topic_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
        ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime
    	) {
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
        map.put("appList",appList);
        
        Long appId = sessionUser.getAppId() == null ? (appList != null && appList.size() > 0) ? appList.get(0).getId() : null : sessionUser.getAppId();
    	List<ConfigCommonType> typeList =  configCommonTypeService.getBySourceType(appId, ConfigCommonType.SOURCETYPE7).getData();
        map.put("typesList", typeList);
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
        if(StringUtils.isBlank(request.getParameter("search_EQ_appId")) && CollectionUtils.isNotEmpty(appList) && appList.size() > 0){
         	searchMap.put("EQ_appId", appId);
         	request.setAttribute("search_EQ_appId", appId);
        }
        if(StringUtils.isNotBlank(request.getParameter("search_LIKE_types"))){
        	searchMap.put("LIKE_types", "," + request.getParameter("search_LIKE_types") + ",");
        }
        map.put("search_LT_createTime", search_LT_createTime);
        searchMap.put("LT_createTime", search_LT_createTime);
        map.put("search_GTE_createTime", search_GTE_createTime);
        searchMap.put("GTE_createTime", search_GTE_createTime);
        
        PageInfo<TopicInfo> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", TopicInfo.STATUS99);//排除逻辑删除状态
        map.put("pageInfo", topicInfoService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allStatuss", TopicInfo.allStatuss);
        map.put("allIsTops", TopicInfo.allIsTops);
        map.put("allIsRecommends", TopicInfo.allIsRecommends);
        map.put("allSourceTypes", TopicInfo.allSourceTypes);
        map.put("allIsAudits", TopicInfo.allIsAudits);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/topic/topic_list_page";
        }else{
        	return "tiles.module.topic.topic_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="topic_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
        map.put("appList",appList);
        if(sessionUser.getAppId() != null && appList != null && appList.size() == 1){
        	map.put("appId", sessionUser.getAppId());
            List<ConfigCommonType> typeList =  configCommonTypeService.getBySourceType(sessionUser.getAppId(), ConfigCommonType.SOURCETYPE7).getData();
            map.put("typeList", typeList);
        }
    	return getReturnUrl(request,map,"tiles.module.topic.topic_detail");
    }


    //增加-保存
    @RequestMapping(value ="topic_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,TopicInfo topicInfo, @RequestParam(value="imageUrl", required=false) String[] imageUrls){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Date now = new Date();
    	Long appId = topicInfo.getAppId();
    	if(sessionUser.getAppId() != null && sessionUser.getAppId() > 0){
    		appId = sessionUser.getAppId();
    	}
    	CommentConfig commentConfig = commentConfigService.getByTypeAndName(BusinessType.SOURCE_TYPE_7, CommentConfig.TOPIC_INFO_NEED_AUDIT).getData();
    	AppInfo appInfo = appInfoService.get(appId).getData();
        //ID
        topicInfo.setId(null);
    	topicInfo.setAppId(appId);
        if(commentConfig != null && CommentConfig.VALUE1.equals(commentConfig.getValue())){
        	topicInfo.setStatus(TopicInfo.STATUS1);
        	topicInfo.setIsAudit(TopicInfo.ISAUDIT0);
        }else{
        	topicInfo.setStatus(TopicInfo.STATUS3);
        	topicInfo.setIsAudit(TopicInfo.ISAUDIT1);
        }
        topicInfo.setTypes("," + topicInfo.getTypes() + ",");
        topicInfo.setSourceType(TopicInfo.SOURCETYPE2);
        topicInfo.setIsTop(TopicInfo.ISTOP0);
        topicInfo.setIsRecommend(TopicInfo.ISRECOMMEND0);
        topicInfo.setReplyCount(0);
        topicInfo.setAuditTime(now);
        topicInfo.setCreateTime(now);
        topicInfo.setCreatorId(sessionUser.getUserId());
        topicInfo.setCreator(appInfo.getName());
        topicInfo.setUpdateTime(now);
        topicInfo.setUpdatorId(sessionUser.getUserId());
        topicInfo.setUpdator(sessionUser.getNickname());
        
        Response<Void> res = topicInfoService.saveByAdmin(topicInfo, imageUrls);
        if(res.getCode() < 0){
        	return new Response<Void>(res.getCode(), res.getMessage());
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="topic_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        TopicInfo topicInfo = topicInfoService.get(id).getData();
        if(topicInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", topicInfo);
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
        map.put("appList",appList);
        map.put("appId", topicInfo.getAppId());
        List<ConfigCommonType> typeList =  configCommonTypeService.getBySourceType(topicInfo.getAppId(), ConfigCommonType.SOURCETYPE7).getData();
        map.put("typeList", typeList);
        //图片
        List<TopicImage> topicImageList = topicImageService.getByTopicInfoId(id).getData();
        map.put("imageList", topicImageList);
        return getReturnUrl(request,map,"tiles.module.topic.topic_detail");
    }

    //修改-保存
    @RequestMapping(value ="topic_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,TopicInfo topicInfo, @RequestParam(value="imageUrl", required=false) String[] imageUrls){
        Response<Void> res = Response.newInstance();
        if(topicInfo==null || topicInfo.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            TopicInfo sourceTopicInfo = topicInfoService.get(topicInfo.getId()).getData();
            if(sourceTopicInfo==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            SessionUser sessionUser = SessionFace.getSessionUser(request);
            Date now = new Date();
            sourceTopicInfo.setTypes("," + topicInfo.getTypes() + ",");
            sourceTopicInfo.setAppId(topicInfo.getAppId());
            sourceTopicInfo.setName(topicInfo.getName());
            sourceTopicInfo.setContent(topicInfo.getContent());
            topicInfo.setUpdateTime(now);
            topicInfo.setUpdatorId(sessionUser.getUserId());
            topicInfo.setUpdator(sessionUser.getNickname());
            res= topicInfoService.saveByAdmin(sourceTopicInfo, imageUrls);
            topicInfo = sourceTopicInfo;
        }catch (Exception ex){
            logger.error("Save Method (Update) TopicInfo Error : " + topicInfo.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="common/topic_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        TopicInfo topicInfo = topicInfoService.get(id).getData();
        if(topicInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", topicInfo);
        AppInfo appInfo = appInfoService.get(topicInfo.getAppId()).getData();
        map.put("appName", appInfo.getName());
        //图片
        List<TopicImage> topicImageList = topicImageService.getByTopicInfoId(id).getData();
        map.put("imageList", topicImageList);
        
        map.put("allSourceTypes", TopicInfo.allSourceTypes);
        return getReturnUrl(request,map,"tiles.module.topic.topic_view");
    }

    //删除
    @RequestMapping(value ="topic_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = topicInfoService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="topic_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = topicInfoService.deleteLogic(ids);
        return res;
    }
    
    /**
     * Title:审核、批量审核
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月15日
     * @param request
     * @param map
     * @param ids
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="topic_check", method = RequestMethod.POST)
    public Response<Void> check(HttpServletRequest request, Map<String, Object> map, @RequestParam("id") Long[] ids, @RequestParam Byte status){
        Response<Void> res = topicInfoService.updateStatus(status, ids);
        return res;
    }
    
    /**
     * Title:发布、批量发布
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月15日
     * @param request
     * @param map
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="topic_publish", method = RequestMethod.POST)
    public Response<Void> publish(HttpServletRequest request, Map<String, Object> map, @RequestParam("id") Long[] ids){
    	Response<Void> res = topicInfoService.updateStatus(TopicInfo.STATUS3, ids);
    	return res;
    }
    
    /**
     * Title:上线、下线、批量上线
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月15日
     * @param request
     * @param map
     * @param ids
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="topic_online", method = RequestMethod.POST)
    public Response<Void> online(HttpServletRequest request, Map<String, Object> map, @RequestParam("id") Long[] ids, @RequestParam Byte status){
    	Response<Void> res = topicInfoService.updateStatus(status, ids);
    	return res;
    }
    
    /**
     * Title:置顶、取消置顶
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月18日
     * @param request
     * @param map
     * @param ids
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="topic_top", method = RequestMethod.POST)
    public Response<Void> top(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id, @RequestParam Byte status, String imageUrl){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Response<Void> res = topicInfoService.updateTopStatus(status, id, imageUrl, sessionUser.getUserId(), sessionUser.getNickname());
    	return res;
    }
    
    /**
     * Title:获得置顶、推荐到首页的图片
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月18日
     * @param request
     * @param map
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value ="common/topic_image/{id}")
    public String image(HttpServletRequest request, Map<String, Object> map, @PathVariable Long id, @RequestParam Integer type){
    	TopicInfo topicInfo = topicInfoService.get(id).getData();
    	String imageUrl = "";
    	if(type != null && type.equals(1)){//置顶
    		imageUrl = StringUtils.isNotBlank(topicInfo.getTopImageUrl()) ? topicInfo.getTopImageUrl() : topicInfo.getRecommendImageUrl();
    	}else if(type != null && type.equals(2)){//推荐到首页
    		imageUrl = StringUtils.isNotBlank(topicInfo.getRecommendImageUrl()) ? topicInfo.getRecommendImageUrl() : topicInfo.getTopImageUrl();
    	}
    	map.put("url", imageUrl);
    	return getReturnUrl(request,map,"tiles.module.topic.topic_image");
    }
    
    /**
     * Title:推荐、取消推荐
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月18日
     * @param request
     * @param map
     * @param id
     * @param status
     * @param imageUrl
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="topic_recommend", method = RequestMethod.POST)
    public Response<Void> recommend(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id, @RequestParam Byte status, String imageUrl){
    	Response<Void> res = topicInfoService.updateRecommendStatus(status, id, imageUrl);
    	return res;
    }
    
    /**
     * <p>Description: 话题配置</p>
     * @author Tangtao on 2016年7月25日
     * @param request
     * @param model
     * @param appId
     * @return
     */
    @RequestMapping(value = "topic_app_config", method = RequestMethod.GET)
    public String config(HttpServletRequest request, Model model, Long appId) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	//获取操作用户数据权限范围
    	List<AppInfoDto> appInfoDtos = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
    	if (CollectionUtils.isNotEmpty(appInfoDtos) && appInfoDtos.size() > 1) {
    		model.addAttribute("appInfos", appInfoDtos);
    	}
    	if (appId == null) {
    		appId = sessionUser.getAppId() == null ? appInfoDtos.get(0).getId() : sessionUser.getAppId();
		}
    	model.addAttribute("appId", appId);
    	
    	//获取段子app配置
    	Response<List<CommentAppConfig>> response1 = commentAppConfigService.getByAppAndType(appId, BusinessType.SOURCE_TYPE_7);
    	//获取段子系统配置
    	Response<List<CommentConfig>> response2 = commentConfigService.getByType(BusinessType.SOURCE_TYPE_7);
    	//设置返回页面的数据，如果app单独有此配置，覆盖系统配置
    	Map<Long, CommentConfig> map = Maps.newLinkedHashMap();
    	for (CommentConfig config : response2.getData()) {
    		map.put(config.getId(), config);
			for (CommentAppConfig appConfig : response1.getData()) {
				if (appConfig.getCommentConfigId().equals(config.getId())) {
					CommentConfig value = map.get(config.getId());
					value.setValue(appConfig.getValue());
					break;
				}
			}
		}
    	model.addAttribute("configMap", map);
    	
    	//获取默认话题图片
    	Response<TopicDefaultImage> defaultImageResponse = topicDefaultImageService.getByApp(appId);
    	if (defaultImageResponse.getCode() == 0 && defaultImageResponse.getData() != null && StringUtils.isNotBlank(defaultImageResponse.getData().getImageUrl())) {
			String defaultImage = defaultImageResponse.getData().getImageUrl();
			model.addAttribute("defaultImage", defaultImage);
		}
        return "tiles.module.topic.topic_app_config";
    }
    
    /**
     * <p>Description: 保存话题配置</p>
     * @author Tangtao on 2016年7月25日
     * @param request
     * @param appId
     * @param defaultImage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "topic_app_config_save", method = {RequestMethod.POST})
    public Response<Void> configSave(HttpServletRequest request, Long appId, String defaultImage) {
    	Response<Void> response = Response.newInstance();
    	Map<String, String[]> map = request.getParameterMap();
    	Map<Long, Byte> parma = Maps.newHashMap();
    	for (String configKey : map.keySet()) {
			if (!configKey.startsWith("topicConfig_")) {
				continue;
			}
			parma.put(Long.valueOf(configKey.split("_")[1]), Byte.valueOf(map.get(configKey)[0]));
		}
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	appId = appId == null ? sessionUser.getAppId() : appId;
    	response = topicDefaultImageService.save(appId, sessionUser.getUserId(), sessionUser.getNickname(), parma, defaultImage);
    	return response;
    }
    
    /**
     * Title:话题图片上传，裁剪
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月2日
     * @param request
     * @param response
     * @param imageName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "common/upload")
	public Response<Map<String,Object>> upLoad(HttpServletRequest request, HttpServletResponse response,String imageName) {
		Response<Map<String,Object>> result = new Response<Map<String,Object>>();
		try {
			// 获取登录用户
			SessionUser user = SessionFace.getSessionUser(request);
			String modulePath = "common";
			String fileLocalPath = PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH);
			Map<String, Object> data = UpAndDownloadUtils.saveUploadFile(request, fileLocalPath,super.handleModulePath(modulePath, user),user.getUserId(),imageName);
			String filePath = data.get("filePath").toString();
			if(StringUtils.isNotBlank(filePath)){
				File file = new File(fileLocalPath + filePath);
				int w = 226, h = 226;	//切图基准：{多图: 226x226}
				//切图 begin
		        String cutFilePath = ImageUtil.appendSuffixBySize(w, h, file.getPath());
		        File cutFile = new File(cutFilePath);
		        ImageUtil.cutForceSize(w, h, file, cutFile);
		        //切图 end
		        filePath = cutFilePath.replace(fileLocalPath, "");
		        data.put("filePath", filePath.replace("\\", "/"));
			}
			result.setData(data);
		}catch (BusinessException e) {
			result.setMessage(e.getMessage());
			result.setCode(-1);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			result.setMessage("系统异常");
		}
		return result;
	}
}
