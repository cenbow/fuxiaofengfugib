package com.cqliving.cloud.controller.module.info;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppColumnsService;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.InfoClassifyComment;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.cloud.online.info.manager.InformationManager;
import com.cqliving.cloud.online.info.service.InfoClassifyCommentService;
import com.cqliving.cloud.online.info.service.InfoClassifyService;
import com.cqliving.cloud.online.info.service.InfoCorrelationService;
import com.cqliving.cloud.online.info.service.InfoThemeService;
import com.cqliving.cloud.online.info.service.InformationService;
import com.cqliving.cloud.online.security.domain.SysRole;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.service.SysUserDataService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.Dates;
import com.cqliving.framework.utils.mapper.JsonMapper;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/infoClassify")
public class InfoClassifyController extends CommonController {

	@Autowired
	private AppColumnsService appColumnsService;
	@Autowired
	private AppInfoService appInfoService;
    @Autowired
    private InfoClassifyService infoClassifyService;
    @Autowired
    InformationService informationService;
    @Autowired
    private InfoClassifyCommentService infoClassifyCommentService;
    @Autowired
    private InfoCorrelationService infoCorrelationService;
    @Autowired
    private InformationManager informationManager;
    @Autowired
    private InfoThemeService infoThemeService;
    @Autowired
    private SysUserDataService sysUserDataService;

    //列表
    @RequestMapping(value ="info_classify_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value="search_GTE_onlineTime", required=false) Date search_GTE_onlineTime
        ,@RequestParam(value="search_LT_onlineTime", required=false) Date search_LT_onlineTime
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        searchMap.put("EQ_addSpecialStatus", InfoClassify.ADDSPECIALSTATUS0);
        searchMap.put("LT_status", InfoClassify.STATUS99);
        //标签条件搜索
        if (searchMap.containsKey("LIKE_infoLabel") && StringUtils.isNotBlank(String.valueOf(searchMap.get("LIKE_infoLabel")))) {
        	searchMap.put("LIKE_infoLabel", "," + String.valueOf(searchMap.get("LIKE_infoLabel")) + ",");
		}
        if (searchMap.containsKey("EQ_columnsId") && StringUtils.isNotBlank(String.valueOf(searchMap.get("EQ_columnsId")))) {	
        	//存在栏目条件搜索时，优先按序号排序
//        	sortMap.put("status_no", true);
        	sortMap.put("sort_no", true);
        	sortMap.put("online_time", false);
        } else {	
        	//不存在栏目条件搜索时，按更新时间排序
        	sortMap.put("status_no", true);		//By Tangtao 2016-11-30
        	sortMap.put("update_time", false);
        }
        
        //获取操作用户APP数据权限范围
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        request.setAttribute("isWxbRole",this.isWxbRole(sessionUser));
        
    	List<AppInfoDto> appInfoDtos = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
    	if (CollectionUtils.isNotEmpty(appInfoDtos) && appInfoDtos.size() > 1) {
    		map.put("appList", appInfoDtos);
    	}
    	Long appId = sessionUser.getAppId();
    	if (appId == null) {	//超管，默认获取第一个app
    		appId = appInfoDtos.get(0).getId();
		}
    	map.put("search_EQ_appId", appId);
    	if (!searchMap.containsKey("EQ_appId")) {
    		searchMap.put("EQ_appId", appId);
        } else {
        	appId = Long.valueOf((String) searchMap.get("EQ_appId"));
        }
    	if (searchMap.containsKey("EQ_listViewType")) {	//处理非轮播数据条件
    		if (searchMap.get("EQ_listViewType") != null && searchMap.get("EQ_listViewType").toString().equals("-4")) {
    			searchMap.put("NOTEQ_listViewType", InfoClassify.LISTVIEWTYPE4);
    			searchMap.remove("EQ_listViewType");
			}
        } 
    	
    	//控制栏目数据权限
    	Long[] appColumnsIds = sysUserDataService.findValueByUserId(sessionUser.getUserId(), SysUserData.TYPE2).getData();
    	if (ArrayUtils.isNotEmpty(appColumnsIds)) {
    		searchMap.put("IN_columnsId", appColumnsIds);
		}
        
        PageInfo<InfoClassifyDto> pageInfo = getPageInfo(request);
        pageInfo =  infoClassifyService.queryDtoForPage(pageInfo, searchMap, sortMap).getData();
        List<InfoClassifyDto> list = pageInfo.getPageResults();
        for (InfoClassifyDto dto : list) {
        	//计算浏览量和评论量
			InformationDto informationDto = new InformationDto();
			informationDto.setId(dto.getInformationId());
			informationDto.setInfoClassifyId(dto.getId());
			informationDto.setViewCount(dto.getViewCount());
			informationDto.setReplyCount(dto.getReplyCount());
			informationDto.setInitCount(dto.getInitCount());
			informationDto.setTopTime(dto.getTopTime());
			informationDto.setOnlineTime(dto.getOnlineTime());
			informationDto.setAddType(dto.getAddType());
			informationManager.setViewAndReplyCount(informationDto, false);
			dto.setViewCount(informationDto.getViewCount());
			dto.setReplyCount(informationDto.getReplyCount());
		}
        map.put("pageInfo", pageInfo);
        
        map.put("allIsRecommends", InfoClassify.allIsRecommends);
        map.put("allClassfieViewStatuss", InfoClassify.allClassfieViewStatuss);
        map.put("allContextTypes", Information.allContextTypes);
        map.put("allListViewTypes", InfoClassify.allListViewTypes);
        map.put("allStatuss", InfoClassify.allStatuss);
        map.put("allTypes", Information.allTypes);	//新闻类型
        map.put("statusSave", InfoClassify.STATUS1 + "," + InfoClassify.STATUS88);	//保存状态
        map.put("statusOnline", InfoClassify.STATUS3);	//已发布状态
        map.put("statusDeleted", InfoClassify.STATUS99);	//已删除状态
        map.put("typeSpecial", Information.TYPE2);	//专题新闻
        map.put("typeNormal", Information.TYPE0);	//普通新闻
        map.put("appAllList", appInfoService.getAll().getData());
        if (searchMap.containsKey("EQ_columnsId") && StringUtils.isNotBlank((String) searchMap.get("EQ_columnsId"))) {
        	map.put("columnsId", searchMap.get("EQ_columnsId"));
        	map.put("columnsName", appColumnsService.get(Long.valueOf((String) searchMap.get("EQ_columnsId"))).getData().getName());
		}
        //APP栏目
    	searchMap = Maps.newHashMap();
    	searchMap.put("EQ_appId", appId);
    	searchMap.put("EQ_columnsType", AppColumns.COLUMNSTYPE0);
    	//控制栏目数据权限
    	searchMap.put("filter_sysUserDataValue", appColumnsIds);
    	String appColumnsJson = new JsonMapper().toJson(appColumnsService.getByConditions(searchMap));
    	map.put("appColumns", appColumnsJson);
    	map.put("appId", appId);
    	
	                    
        //查询按钮和点击页面是ajax操作。
        if (StringUtils.isNotBlank(isAjaxPage)) {
        	return "/module/infoClassify/info_classify_list_page";
        } else {
        	return "tiles.module.infoClassify.info_classify_list";
        }
    }
    
    private boolean isWxbRole(SessionUser sessionUser){
    	    final long wxbrole_id = 9;
      	Set<SysRole> userRoles = sessionUser.getRole();
        if(CollectionUtils.isNotEmpty(userRoles)){
        	    for(SysRole sysRole : userRoles){
        	    	   if(wxbrole_id == sysRole.getId().longValue()){
        	    		   return true;
        	    	   }
        	    }
        }
        return false;
    }
    
    /**
     * <p>Description: 复制新闻列表</p>
     * @author Tangtao on 2016年5月4日
     * @param request
     * @param map
     * @param isAjaxPage
     * @return
     */
    @RequestMapping(value ="info_classify_list_copy")
    public String copyList(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        
        //当未输入标题时，不显示任何数据
        String title = (String) searchMap.get("LIKE_title");
        if (StringUtils.isBlank(title)) {
        	searchMap.put("LT_id", 0);
		}
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        searchMap.put("LT_status", InfoClassify.STATUS99);
        sortMap.put("online_time", false);
        sortMap.put("id", false);
        
        //获取操作用户数据权限范围
    	List<AppInfoDto> appInfoDtos = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
    	if (CollectionUtils.isNotEmpty(appInfoDtos) && appInfoDtos.size() > 1) {
    		map.put("appList", appInfoDtos);
    	}
    	Long appId = sessionUser.getAppId();
    	if (appId == null) {	//超管，默认获取第一个app
    		appId = appInfoDtos.get(0).getId();
		}
    	map.put("search_EQ_appId", appId);
    	if (!searchMap.containsKey("EQ_appId")) {
    		searchMap.put("EQ_appId", appId);
        } 
        
        PageInfo<InfoClassifyDto> pageInfo = getPageInfo(request);
        map.put("pageInfo", infoClassifyService.queryDtoForCopyPage(pageInfo, searchMap, sortMap).getData());
        
//        map.put("allClassfieViewStatuss", InfoClassify.allClassfieViewStatuss);
        map.put("allListViewTypes", InfoClassify.allListViewTypes);
        map.put("allStatuss", InfoClassify.allStatuss);
        map.put("statusSave", InfoClassify.STATUS1 + ","+ InfoClassify.STATUS88);
        map.put("statusOnline", InfoClassify.STATUS3);
        map.put("statusDeleted", InfoClassify.STATUS99);
	                    
        //查询按钮和点击页面是ajax操作。
        if (StringUtils.isNotBlank(isAjaxPage)) {
        	return "/module/infoClassify/info_classify_copy_list_page";
        } else {
        	return "tiles.module.infoClassify.info_classify_copy_list";
        }
    }
    
    /**
     * <p>Description: 专题新闻列表</p>
     * @author Tangtao on 2016年5月6日
     * @param request
     * @param map
     * @param isAjaxPage
     * @return
     */
    @RequestMapping(value ="info_classify_list_special")
    public String specialList(HttpServletRequest request, Map<String, Object> map, @RequestParam(value = "p", required = false) String isAjaxPage) {
    	
    	Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
    	Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	searchMap.put("LT_status", InfoClassify.STATUS99);
    	searchMap.put("EQ_type", Information.TYPE2);
//    	sortMap.put("online_time", false);
    	if (searchMap.containsKey("EQ_columnsId") && StringUtils.isNotBlank(String.valueOf(searchMap.get("EQ_columnsId")))) {	
        	//存在栏目条件搜索时，优先按序号排序
        	sortMap.put("sort_no", true);
        	sortMap.put("online_time", false);
        } else {	
        	//不存在栏目条件搜索时，按更新时间排序
        	sortMap.put("status_no", true);		
        	sortMap.put("update_time", false);
        }
    	
    	//获取操作用户数据权限范围
    	List<AppInfoDto> appInfoDtos = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
    	if (CollectionUtils.isNotEmpty(appInfoDtos) && appInfoDtos.size() > 1) {
    		map.put("appList", appInfoDtos);
    	}
    	Long appId = sessionUser.getAppId();
    	if (appId == null) {	//超管，默认获取第一个app
    		appId = appInfoDtos.get(0).getId();
    	}
    	map.put("search_EQ_appId", appId);
    	if (!searchMap.containsKey("EQ_appId")) {
    		searchMap.put("EQ_appId", appId);
    	} 
    	
    	//控制栏目数据权限
    	Long[] appColumnsIds = sysUserDataService.findValueByUserId(sessionUser.getUserId(), SysUserData.TYPE2).getData();
    	if (ArrayUtils.isNotEmpty(appColumnsIds)) {
    		searchMap.put("IN_columnsId", appColumnsIds);
		}
    	
    	PageInfo<InfoClassifyDto> pageInfo = getPageInfo(request);
    	map.put("pageInfo", infoClassifyService.queryDtoForPage(pageInfo, searchMap, sortMap).getData());
    	
        map.put("allClassfieViewStatuss", InfoClassify.allClassfieViewStatuss);
    	map.put("allListViewTypes", InfoClassify.allListViewTypes);
    	map.put("allSendStatus", InfoClassify.allSendStatuss);
    	map.put("allCommentValidateTypes", InfoClassify.allCommentValidateTypes);
    	map.put("allCommentTypes", InfoClassify.allCommentTypes);
    	map.put("allStatuss", InfoClassify.allStatuss);
    	map.put("statusSave",InfoClassify.STATUS1 + ","+ InfoClassify.STATUS88);
    	map.put("statusOnline", InfoClassify.STATUS3);
    	map.put("statusDeleted", InfoClassify.STATUS99);
    	
    	//APP栏目
    	searchMap = Maps.newHashMap();
    	searchMap.put("EQ_appId", appId);
    	searchMap.put("EQ_columnsType", AppColumns.COLUMNSTYPE0);
    	//控制栏目数据权限
    	searchMap.put("filter_sysUserDataValue", appColumnsIds);
    	String appColumnsJson = new JsonMapper().toJson(appColumnsService.getByConditions(searchMap));
    	map.put("appColumns", appColumnsJson);
    	
    	//查询按钮和点击页面是ajax操作。
    	if (StringUtils.isNotBlank(isAjaxPage)) {
    		return "/module/infoClassify/info_classify_special_list_page";
    	} else {
    		return "tiles.module.infoClassify.info_classify_special_list";
    	}
    }
    
    /**
     * <p>Description: 专题子新闻列表</p>
     * @author Tangtao on 2016年5月12日
     * @param request
     * @param map
     * @param specialId 专题新闻 id
     * @param isAjaxPage
     * @return
     */
    @RequestMapping(value ="info_classify_list_special_sub")
    public String specialSubList(HttpServletRequest request, Map<String, Object> map, 
    		@RequestParam("mid") Long specialId,
    		@RequestParam Long appId,
    		@RequestParam(value = "p", required = false) String isAjaxPage) {
    	
    	Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
    	Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
//    	searchMap.put("EQ_appId", sessionUser.getAppId());
    	searchMap.put("EQ_appId", appId);
    	searchMap.put("NOTEQ_status", InfoClassify.STATUS99);
    	searchMap.put("EQ_infoClassifyId", specialId);
    	searchMap.put("GT_themeId", 0);	//非相关新闻
    	searchMap.put("EQ_addSpecialStatus", InfoClassify.ADDSPECIALSTATUS1);	//已加入专题
    	if (searchMap.containsKey("EQ_themeId") && StringUtils.isNotBlank(String.valueOf(searchMap.get("EQ_themeId")))) {	
    		//存在按分类条件搜索的时候，按序号、发布时间排序
          	sortMap.put("sort_no", true);
          	sortMap.put("online_time", false);
    	} else {
    		//无分类条件搜索的时候，按状态序号、修改时间排序
    		sortMap.put("status_no", true);
    		sortMap.put("update_time", false);
    	}
    	
    	PageInfo<InfoClassifyDto> pageInfo = getPageInfo(request);
    	pageInfo = infoClassifyService.queryDtoForSpecialSubPage(pageInfo, searchMap, sortMap).getData();
    	List<InfoClassifyDto> list = pageInfo.getPageResults();
        for (InfoClassifyDto dto : list) {
        	//计算浏览量和评论量
			InformationDto informationDto = new InformationDto();
			informationDto.setId(dto.getInformationId());
			informationDto.setInfoClassifyId(dto.getInfoClassifyId());
			informationDto.setViewCount(dto.getViewCount());
			informationDto.setReplyCount(dto.getReplyCount());
			informationDto.setInitCount(dto.getInitCount());
			informationDto.setTopTime(dto.getTopTime());
			informationDto.setOnlineTime(dto.getOnlineTime());
			informationDto.setAddType(dto.getAddType());
			informationManager.setViewAndReplyCount(informationDto, false);
			dto.setViewCount(informationDto.getViewCount());
			dto.setReplyCount(informationDto.getReplyCount());
		}
        map.put("pageInfo", pageInfo);
    	
    	map.put("appId", appId);
    	map.put("mid", specialId);
    	map.put("allListViewTypes", InfoClassify.allListViewTypes);
    	map.put("allStatuss", InfoClassify.allStatuss);
    	map.put("statusSave", InfoClassify.STATUS1 + ","+ InfoClassify.STATUS88);
    	map.put("statusOnline", InfoClassify.STATUS3);
    	map.put("statusDeleted", InfoClassify.STATUS99);
    	map.put("themeList", infoThemeService.findByInfoClassifyId(specialId).getData());	//专题分类
    	if (searchMap.containsKey("EQ_themeId")) {
        	map.put("themeId", searchMap.get("EQ_themeId"));
		}
    	//APP栏目
    	searchMap = Maps.newHashMap();
    	searchMap.put("EQ_appId", appId);
    	searchMap.put("EQ_columnsType", AppColumns.COLUMNSTYPE0);
    	//控制栏目数据权限
    	searchMap.put("filter_sysUserDataValue", sysUserDataService.findValueByUserId(sessionUser.getUserId(), SysUserData.TYPE2).getData());
    	String appColumnsJson = new JsonMapper().toJson(appColumnsService.getByConditions(searchMap));
    	map.put("appColumns", appColumnsJson);
    	
    	//查询按钮和点击页面是ajax操作。
    	if (StringUtils.isNotBlank(isAjaxPage)) {
    		return "/module/infoClassify/info_classify_special_sub_list_page";
    	} else {
    		return "tiles.module.infoClassify.info_classify_special_sub_list";
    	}
    }
    
    /**
     * <p>Description: 推荐新闻列表</p>
     * @author Tangtao on 2016年5月11日
     * @param request
     * @param map
     * @param isAjaxPage
     * @return
     */
    @RequestMapping(value ="info_classify_list_recommend")
    public String recommendList(HttpServletRequest request, Map<String, Object> map, @RequestParam(value = "p", required = false) String isAjaxPage) {
    	
    	Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
    	Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	searchMap.put("EQ_targetAppId", sessionUser.getAppId());
    	searchMap.put("NOTEQ_status", InfoClassify.STATUS99);
    	sortMap.put("recommend_status", true);
    	sortMap.put("comment_time", false);
    	
    	PageInfo<InfoClassifyDto> pageInfo = getPageInfo(request);
    	map.put("pageInfo", infoClassifyService.queryDtoForRecommendPage(pageInfo, searchMap, sortMap).getData());
    	
    	map.put("allContextTypes", Information.allContextTypes);
    	map.put("allListViewTypes", InfoClassify.allListViewTypes);
    	map.put("allStatuss", InfoClassifyComment.allStatuss);
    	map.put("appList", appInfoService.getAll().getData());
    	map.put("status0", InfoClassifyComment.STATUS0);
//    	map.put("statusOnline", InfoClassify.STATUS3);
//    	map.put("statusDeleted", InfoClassify.STATUS99);
    	//APP栏目
    	searchMap = Maps.newHashMap();
    	searchMap.put("EQ_appId",sessionUser.getAppId());
    	searchMap.put("EQ_columnsType", AppColumns.COLUMNSTYPE0);
    	//控制栏目数据权限
    	searchMap.put("filter_sysUserDataValue", sysUserDataService.findValueByUserId(sessionUser.getUserId(), SysUserData.TYPE2).getData());
    	String appColumnsJson = new JsonMapper().toJson(appColumnsService.getByConditions(searchMap));
    	map.put("appColumns", appColumnsJson);
    	
    	//查询按钮和点击页面是ajax操作。
    	if (StringUtils.isNotBlank(isAjaxPage)) {
    		return "/module/infoClassify/info_classify_recommend_list_page";
    	} else {
    		return "tiles.module.infoClassify.info_classify_recommend_list";
    	}
    }

   

    //删除
    @ResponseBody
    @RequestMapping(value ="info_classify_delete")
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Response<Void> res = infoClassifyService.delete(id, DateUtil.now(), sessionUser.getUserId(), sessionUser.getNickname());
    	//删除缓存
        informationService.delCache(id);
        return res;
    }
    
    /**
	 * <p>Description: 批量删除</p>
	 * @author Tangtao on 2016年5月4日
	 * @param request
	 * @param map
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="info_classify_del_batch")
	public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map, @RequestParam("ids[]") Long[] ids) {
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		Response<Void> res = infoClassifyService.delBatch(Arrays.asList(ids), DateUtil.now(), sessionUser.getUserId(), sessionUser.getNickname());
		//删除缓存
	    informationService.delCache(ids);
		return res;
	}

	/**
     * <p>Description: 忽略推荐</p>
     * @author Tangtao on 2016年5月11日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="info_classify_recommend_ignore")
    public Response<Void> ignore(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id){
    	Response<Void> res = infoClassifyCommentService.ignore(id);
    	return res;
    }
    
    /**
     * <p>Description: 发布</p>
     * @author Tangtao on 2016年4月29日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="info_classify_publish")
    public Response<Void> publish(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        Response<Void> res = infoClassifyService.publish(id, DateUtil.now(), sessionUser.getUserId(), sessionUser.getNickname());
        //删除缓存
        informationService.delCache(id);
        return res;
    }
    
    /**
     * <p>Description: 专题子新闻发布</p>
     * @author Tangtao on 2016年12月6日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="info_correlation_publish")
    public Response<Void> publishCorrelation(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Response<Void> res = infoClassifyService.publishCorrelation(id, DateUtil.now(), sessionUser.getUserId(), sessionUser.getNickname());
    	//删除缓存
    	informationService.delCache(id);
    	return res;
    }
    
    /**
     * <p>Description: 批量发布</p>
     * @author Tangtao on 2016年5月4日
     * @param request
     * @param map
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="info_classify_publish_batch")
    public Response<Void> publishBatch(HttpServletRequest request, Map<String, Object> map, @RequestParam("ids[]") Long[] ids) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Response<Void> res = infoClassifyService.publishBatch(Arrays.asList(ids), DateUtil.now(), sessionUser.getUserId(), sessionUser.getNickname());
    	//删除缓存
        informationService.delCache(ids);
    	return res;
    }
    
    /**
     * <p>Description: 撤稿</p>
     * @author Tangtao on 2016年5月4日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="info_classify_offline")
    public Response<Void> offline(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Response<Void> res = infoClassifyService.offline(id, DateUtil.now(), sessionUser.getUserId(), sessionUser.getNickname());
    	//删除缓存
        informationService.delCache(id);
    	return res;
    }
    
    /**
	 * <p>Description: 批量下线</p>
	 * @author Tangtao on 2016年5月4日
	 * @param request
	 * @param map
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="info_classify_offline_batch")
	public Response<Void> offlineBatch(HttpServletRequest request, Map<String, Object> map, @RequestParam("ids[]") Long[] ids) {
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		Response<Void> res = infoClassifyService.offlineBatch(Arrays.asList(ids), DateUtil.now(), sessionUser.getUserId(), sessionUser.getNickname());
		//删除缓存
	    informationService.delCache(ids);
		return res;
	}

	/**
	 * <p>Description: 专题子新闻下线</p>
	 * @author Tangtao on 2016年12月6日
	 * @param request
	 * @param map
	 * @param id
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value ="info_correlation_offline")
    public Response<Void> offlineCorrelation(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Response<Void> res = infoClassifyService.offlineCorrelation(id, DateUtil.now(), sessionUser.getUserId(), sessionUser.getNickname());
    	//删除缓存
        informationService.delCache(id);
    	return res;
    }
	
	/**
	 * <p>Description: 专题子新闻批量下线</p>
	 * @author Tangtao on 2016年12月6日
	 * @param request
	 * @param map
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="info_correlation_offline_batch")
	public Response<Void> offlineCorrelationBatch(HttpServletRequest request, Map<String, Object> map, @RequestParam("ids[]") Long[] ids) {
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		Response<Void> res = infoClassifyService.offlineCorrelationBatch(Arrays.asList(ids), DateUtil.now(), sessionUser.getUserId(), sessionUser.getNickname());
		//删除缓存
	    informationService.delCache(ids);
		return res;
	}
    
    /**
     * <p>Description: 清空排序</p>
     * @author Tangtao on 2016年5月10日
     * @param request
     * @param map
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="info_classify_clear_sort_no")
    public Response<Void> clearSortNo(HttpServletRequest request, Map<String, Object> map, @RequestParam("ids[]") Long[] ids){
    	Response<Void> res = infoClassifyService.clearSortNo(Arrays.asList(ids));
    	return res;
    }
    
    /**
     * <p>Description: 清空专题子新闻排序</p>
     * @author Tangtao on 2016年5月12日
     * @param request
     * @param map
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="info_classify_clear_special_sort_no")
    public Response<Void> clearSpecialSortNo(HttpServletRequest request, Map<String, Object> map, @RequestParam("ids[]") Long[] ids){
    	Response<Void> res = infoCorrelationService.clearSortNo(Arrays.asList(ids));
    	return res;
    }
    
    /**
     * <p>Description: 修改排序</p>
     * @author Tangtao on 2016年5月10日
     * @param request
     * @param map
     * @param id
     * @param sortNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="info_classify_modify_sort_no")
    public Response<Void> modifySortNo(HttpServletRequest request, Map<String, Object> map, 
    		@RequestParam("i") Long id, 
    		@RequestParam(value = "s", required = false) Integer sortNo) {
    	Response<Void> res = infoClassifyService.modifySortNo(id, sortNo);
    	return res;
    }
    
    /**
     * <p>Description: 修改专题子新闻排序</p>
     * @author Tangtao on 2016年5月12日
     * @param request
     * @param map
     * @param id
     * @param sortNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="info_classify_modify_special_sort_no")
    public Response<Void> modifySpecialSortNo(HttpServletRequest request, Map<String, Object> map, 
    		@RequestParam("i") Long id, 
    		@RequestParam(value = "s", required = false) Integer sortNo) {
    	Response<Void> res = infoCorrelationService.modifySortNo(id, sortNo);
    	return res;
    }
    
    /**
     * <p>Description: 发布到App</p>
     * @author Tangtao on 2016年5月12日
     * @param request
     * @param map
     * @param infoClasifyId
     * @param infoClassifyCommentId
     * @param appColumnId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="info_classify_publish_to_column")
    public Response<Void> publishToColumn(HttpServletRequest request, Map<String, Object> map, 
    		@RequestParam("i") Long infoClasifyId, 
    		@RequestParam("c") Long infoClassifyCommentId, 
    		@RequestParam("a") Long appColumnId) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	String userName = sessionUser.getNickname();
    	Long userId = sessionUser.getUserId();
    	Response<Void> res = infoClassifyCommentService.publishToColumn(sessionUser.getAppId(), infoClasifyId, infoClassifyCommentId, appColumnId, userName, userId);
    	return res;
    }
    
    /**
     * <p>Description: 移出专题</p>
     * @author Tangtao on 2016年5月13日
     * @param request
     * @param map
     * @param type
     * @param infoClasifyIds
     * @param infoCorrelationIds
     * @param appColumnId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="info_classify_move_out")
    public Response<Void> moveOut(HttpServletRequest request, Map<String, Object> map, 
    		@RequestParam("t") Integer type, 
    		@RequestParam("i[]") Long[] infoClasifyIds, 
    		@RequestParam("c[]") Long[] infoCorrelationIds, 
    		@RequestParam(value = "a", required = false) Long appColumnId) {
    	Response<Void> response = Response.newInstance();
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	if (type.equals(1)) {	//恢复栏目显示
    		response = infoCorrelationService.moveOut(infoClasifyIds, infoCorrelationIds, DateUtil.now(), sessionUser.getUserId(), sessionUser.getNickname());
    	} else if (type.equals(2)) {	//下线
    		response = infoClassifyService.offlineBatch(Arrays.asList(infoClasifyIds), DateUtil.now(), sessionUser.getUserId(), sessionUser.getNickname());
		} else if (type.equals(3)) {	//重新选择栏目
			response = infoCorrelationService.moveOut(infoClasifyIds, infoCorrelationIds, appColumnId, DateUtil.now(), sessionUser.getUserId(), sessionUser.getNickname());
		} else {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
		}
    	return response;
    }
    
    /**
     * <p>Description: 推荐到App</p>
     * @author Tangtao on 2016年5月10日
     * @param request
     * @param map
     * @param id
     * @param appIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="info_classify_recommend")
    public Response<Void> recommend(HttpServletRequest request, Map<String, Object> map, 
    		@RequestParam("i") Long id, 
    		@RequestParam("a[]") Long[] appIds) {
    	Response<Void> res = infoClassifyService.recommend(id, appIds);
    	return res;
    }
    
    /**
     * <p>Description: 推送</p>
     * @author Tangtao on 2016年5月10日
     * @param request
     * @param map
     * @param id
     * @param title
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="info_classify_push")
    public Response<Void> push(HttpServletRequest request, Map<String, Object> map, 
    		@RequestParam("i") Long id, 
    		@RequestParam(value = "t", required = false) String title) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Long userId = sessionUser.getUserId();
    	String nickName = sessionUser.getNickname();
    	Response<Void> response = infoClassifyService.push(id, title, "", userId, nickName);	//只填标题，不需要摘要 By Tangtao 2016-11-23
    	return response;
    }
    
    /**
     * <p>Description: 获取app的资讯栏目</p>
     * @author Tangtao on 2016年8月3日
     * @param request
     * @param appId
     * @return
     */
    @ResponseBody
    @RequestMapping({"common/getColumns"})
    public Response<String> getColumns(HttpServletRequest request, Long appId) {
    	Response<String> response = Response.newInstance();
    	Map<String, Object> searchMap = Maps.newHashMap();
    	searchMap.put("EQ_appId", appId);
    	searchMap.put("EQ_columnsType", AppColumns.COLUMNSTYPE0);
    	String data = new JsonMapper().toJson(appColumnsService.getByConditions(searchMap));
    	response.setData(data);
    	return response;
    }
    
    /**
     * Title:推荐到首页
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月17日
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"recommend_to_home"}, method=RequestMethod.POST)
    public Response<Void> postRecommendToHome(HttpServletRequest request, @RequestParam Long id){
    	Response<Void> res = Response.newInstance();
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	res = infoClassifyService.recommendToHome(id, sessionUser.getUserId(), sessionUser.getNickname());
    	return res;
    }
    
    @RequestMapping(value="classify_news_list")
    public String newsList(HttpServletRequest request,@RequestParam(value = "p", required = false) String isAjaxPage){
    	    Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
    	    searchMap.put("LT_status", InfoClassify.STATUS99);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
	    	sortMap.put("sort_no", true);
	    	sortMap.put("online_time", false);
	    	sortMap.put("id",false);
    	    PageInfo<InfoClassifyDto> pageInfo = getPageInfo(request);
        pageInfo =  infoClassifyService.queryDtoForPage(pageInfo, searchMap, sortMap).getData();
        request.setAttribute("pageInfo",pageInfo);
        request.setAttribute("statusSave", InfoClassify.STATUS1 + "," + InfoClassify.STATUS88);	//保存状态
        request.setAttribute("statusOnline", InfoClassify.STATUS3);	//已发布状态
        request.setAttribute("allContextTypes", Information.allContextTypes);
        request.setAttribute("allListViewTypes", InfoClassify.allListViewTypes);
        request.setAttribute("allStatuss", InfoClassify.allStatuss);
        List<AppInfoDto> appInfoDtos = appInfoService.getBySysUser(SysUser.USERTYPE1, SessionFace.getSessionUser(request).getUserId()).getData();
    	    request.setAttribute("appInfoDtos",appInfoDtos);
        //查询按钮和点击页面是ajax操作。
        if (StringUtils.isNotBlank(isAjaxPage)) {
         	return "/module/infoClassify/classify_news_list_page";
        } else {
         	return "tiles.module.infoClassify.classify_news_list";
        }
    }
    
    @RequestMapping(value="change_classify_appcolumn")
    @ResponseBody
    public Response<Void> moveNews(HttpServletRequest request,@RequestParam(value="infoClassifyIds[]") Long[] infoClassifyIds,Long appColumnsId){
    	     SessionUser sessionUser = SessionFace.getSessionUser(request);
    	     return infoClassifyService.moveInfoClassify(infoClassifyIds, appColumnsId, sessionUser.getNickname(), sessionUser.getUserId(), Dates.now());
    }
}
