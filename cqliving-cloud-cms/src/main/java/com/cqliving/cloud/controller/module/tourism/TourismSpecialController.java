package com.cqliving.cloud.controller.module.tourism;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.service.ConfigRegionService;
import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.service.SysUserDataService;
import com.cqliving.cloud.online.tourism.domain.TourismInfo;
import com.cqliving.cloud.online.tourism.domain.TourismSpecial;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.cloud.online.tourism.service.TourismSpecialService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/tourism")
public class TourismSpecialController extends CommonController {

    @Autowired
    private TourismSpecialService tourismSpecialService;
    @Autowired
	private ConfigRegionService configRegionService;
    @Autowired
	private AppInfoService appInfoService;
    @Autowired
	SysUserDataService sysUserDataService;
    
    private void init(HttpServletRequest request,Long appId){
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	//获取操作用户数据权限范围
    	List<AppInfoDto> appInfoDtos = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
    	if (CollectionUtils.isNotEmpty(appInfoDtos) && appInfoDtos.size() >= 2) {
    		request.setAttribute("appList", appInfoDtos);
    	}
    	if (appId == null) {	
    		appId = sessionUser.getAppId();
    		if (appId == null) {	//超管，默认获取第一个app
    			appId = appInfoDtos.get(0).getId();
    		}
		}
    	request.setAttribute("search_EQ_appId", appId);
    }
    
    //列表
    @RequestMapping(value ="tourism_special_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value = "search_EQ_appId", required = false) Long appId
        ,@RequestParam(value = "tourismId", required = true) Long tourismId
    	) {
    	this.init(request, appId);
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        if (!searchMap.containsKey("EQ_appId")) {
    		searchMap.put("EQ_appId", request.getAttribute("search_EQ_appId"));
        }
		
        PageInfo<TourismInfoDto> pageInfo = getPageInfo(request);
        searchMap.put("EQ_tourismId",tourismId);//专题ID
        sortMap.put("tourism_special_sort_no",true);
        sortMap.put("id",true);
        map.put("pageInfo", tourismSpecialService.queryForSpecialSub(pageInfo, searchMap, sortMap).getData());
        
        map.put("allStatuss", TourismSpecial.allStatuss);
        map.put("allTypes", TourismInfo.allTypes);                
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/tourism/tourism_special_list_page";
        }else{
        	//获取全部旅游区域
            Response<List<ConfigRegion>> regionResponse = configRegionService.getByAppAndType(null, BusinessType.SOURCE_TYPE_10);
            map.put("regionList", regionResponse.getData());
        	return "tiles.module.tourism.tourism_special_list";
        }
    }

    //修改排序
    @RequestMapping(value="update_sort_no")
    @ResponseBody
    public Response<Void> updateSortNo(@RequestParam(value="i") Long tourismSpecialId,@RequestParam(value="s") Integer sortNo){
    	
        return	tourismSpecialService.updateSortNo(tourismSpecialId, sortNo);
    }
    
    //加入专题弹出层
    @RequestMapping(value ="join_special")
    public String joinSpecial(HttpServletRequest request, Map<String, Object> map,@RequestParam Long specialId,
    		@RequestParam(value = "p", required = false) String isAjaxPage
            ){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
    	PageInfo<TourismInfoDto> pageInfo = getPageInfo(request);
    	Long[] appIds = sysUserDataService.findValueByUserId(sessionUser.getUserId(),SysUserData.TYPE1).getData();
    	searchMap.put("EQ_specialId",specialId);
    	searchMap.put("IN_appId", appIds);
    	sortMap.put("sort_no",true);
        sortMap.put("id",true);
    	tourismSpecialService.queryForNoJoinSpecial(pageInfo, searchMap, sortMap);
    	 map.put("allStatuss", TourismSpecial.allStatuss);
         map.put("allTypes", TourismInfo.allTypes);  
         map.put("pageInfo",pageInfo);
         map.put("specialId", specialId);
    	if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/tourism/tourism_info_list_modal_page";
        }
    	//获取全部旅游区域
        Response<List<ConfigRegion>> regionResponse = configRegionService.getByAppAndType(appIds, BusinessType.SOURCE_TYPE_10);
        map.put("regionList", regionResponse.getData());
    	return "/module/tourism/tourism_info_list_modal";
    }
    
    //加入专题
    @RequestMapping(value="common/join_special",method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> joinSepcial(HttpServletRequest request,TourismSpecial tourismSpecial,@RequestParam(value="refIds[]") Long[] refIds){
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	tourismSpecial.setCreateTime(Dates.now());
    	tourismSpecial.setCreator(sessionUser.getNickname());
    	tourismSpecial.setCreatorId(sessionUser.getUserId());
    	tourismSpecial.setSortNo(Integer.MAX_VALUE);
    	tourismSpecial.setStatus(TourismSpecial.STATUS1);
    	
    	return tourismSpecialService.joinSecial(tourismSpecial, refIds);
    }

    @RequestMapping(value ={"tourism_special_publish","tourism_special_offline"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> publish(@RequestParam(value = "id") Long id,@RequestParam Byte status){
    	
    	return tourismSpecialService.updateStatus(status, id);
    }
    
    @RequestMapping(value ={"tourism_special_publish_batch","tourism_special_offline_batch"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> publishBatch(@RequestParam("ids[]") Long[] ids,@RequestParam Byte status){
    	
    	return tourismSpecialService.updateStatus(status, ids);
    }
    
    //删除
    @RequestMapping(value ="tourism_special_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = tourismSpecialService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="tourism_special_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = tourismSpecialService.delete(ids);
        return res;
    }
}
