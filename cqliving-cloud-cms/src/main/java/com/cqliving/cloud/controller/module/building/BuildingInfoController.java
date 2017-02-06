package com.cqliving.cloud.controller.module.building;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.building.domain.BuildingImage;
import com.cqliving.cloud.online.building.domain.BuildingInfo;
import com.cqliving.cloud.online.building.service.BuildingImageService;
import com.cqliving.cloud.online.building.service.BuildingInfoService;
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.service.ConfigRegionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title:手机置业
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年10月10日
 */
/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年10月12日
 */
@Controller
@RequestMapping(value = "/module/building")
public class BuildingInfoController extends CommonController {

    @Autowired
    private BuildingInfoService buildingInfoService;
    @Autowired
    private ConfigRegionService configRegionService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private BuildingImageService buildingImageService;
    
    /**
     * Title:列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月10日
     * @param request
     * @param map
     * @param isAjaxPage
     * @return
     */
    @RequestMapping(value ="building_info_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage,
    	@RequestParam(value = "search_EQ_appId", required = false) Long appId
    	) {
    	Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        
//      获取操作用户数据权限范围
    	List<AppInfoDto> appInfoDtos = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
    	if (CollectionUtils.isNotEmpty(appInfoDtos) && appInfoDtos.size() > 1) {
    		map.put("appList", appInfoDtos);
    	}
    	if (appId == null) {	
    		appId = sessionUser.getAppId();
    		if (appId == null) {	//超管，默认获取第一个app
    			appId = appInfoDtos.get(0).getId();
    		}
		}
    	map.put("search_EQ_appId", appId);
    	if (!searchMap.containsKey("EQ_appId")) {
    		searchMap.put("EQ_appId", appId);
        } 
        
        PageInfo<BuildingInfo> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", BuildingInfo.STATUS99);//排除逻辑删除状态
        sortMap.put("sortNo", true);
        sortMap.put("createTime", false);
        map.put("pageInfo", buildingInfoService.queryForPage(pageInfo, searchMap, sortMap).getData());
        map.put("allStatuss", BuildingInfo.allStatuss);
  		//默认排序
  		map.put("defaultSortNo", Integer.MAX_VALUE);
//  	获取区域
        Response<List<ConfigRegion>> regionResponse = configRegionService.findByType(BusinessType.SOURCE_TYPE_11);
        map.put("allRegion", regionResponse.getData());
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/building/building_info_list_page";
        }else{
        	return "tiles.module.building.building_info_list";
        }
    }

    /**
     * Title:增加-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月10日
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value ="building_info_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        Long appId = sessionUser.getAppId();
        if (appId == null) {
        	return super.operFailure("请登录App管理员操作", map);
		}
        this.getCommonData(map, appId);
    	return getReturnUrl(request,map,"tiles.module.building.building_info_detail");
    }
    
    
    /**
     * Title:获取相同数据，新增和修改调用
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月10日
     * @param map
     * @param appId
     */
    private void getCommonData(Map<String, Object> map, Long appId){
    	//获取区域
        Response<List<ConfigRegion>> regionResponse = configRegionService.findByType(BusinessType.SOURCE_TYPE_11);
        map.put("allRegion", regionResponse.getData());
        //查询百度地图Key
  		Response<AppInfo> appInforResponse = appInfoService.get(appId);
  		map.put("baiduLbsKey", appInforResponse.getData().getBaiduLbsKey());
  		//显示价格单位
  		map.put("allViewUnits", BuildingInfo.allViewUnits);
  		//户型
  		map.put("allHouseTypes", BuildingInfo.allHouseTypes);
  		//默认排序
  		map.put("defaultSortNo", Integer.MAX_VALUE);
  		//类型
  		map.put("allTypes", BuildingInfo.allTypes);
  		//appId
  		map.put("appId", appId);
    }


    /**
     * Title:增加-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月12日
     * @param request
     * @param map
     * @param buildingInfo
     * @param images
     * @param descType
     * @param descArea
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="building_info_add", method = RequestMethod.POST)
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map, BuildingInfo buildingInfo, 
    		String[] images,
    		String[] descType,
    		String[] descArea
    	){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        //ID
        buildingInfo.setId(null);
        buildingInfo.setAppId(sessionUser.getAppId());
        buildingInfo.setPraiseCount(0);
        buildingInfo.setReplyCount(0);
        buildingInfo.setType(buildingInfo.getType() != null || BuildingInfo.allTypes.containsKey(buildingInfo.getType()) ? buildingInfo.getType() : BuildingInfo.TYPE1);
        Response<Void> res = buildingInfoService.save(buildingInfo, images, descType, descArea, sessionUser.getUserId(), sessionUser.getNickname());
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    /**
     * Title:修改-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月12日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="building_info_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        BuildingInfo buildingInfo = buildingInfoService.get(id).getData();
        if(buildingInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        buildingInfo.setAveragePrice(buildingInfo.getAveragePrice() == null ? null : buildingInfo.getAveragePrice()/100);
        map.put("item", buildingInfo);
        this.getCommonData(map, buildingInfo.getAppId());
        this.getCommonImageList(map, buildingInfo.getId());
        return getReturnUrl(request,map,"tiles.module.building.building_info_detail");
    }
    
    /**
     * Title:返回图片集合
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月12日
     * @param map
     * @param buildingInfoId
     */
    private void getCommonImageList(Map<String, Object> map, Long buildingInfoId){
    	//获得图片集
        List<BuildingImage> imagesList = buildingImageService.getByBuilding(buildingInfoId, null).getData();
        List<BuildingImage> imageList1 = Lists.newArrayList();//轮播图
        List<BuildingImage> imageList2 = Lists.newArrayList();//户型图
        if(imagesList != null && imagesList.size() > 0){
        	for(BuildingImage img : imagesList){
        		if(BuildingImage.TYPE1.equals(img.getType())){
        			imageList1.add(img);
        		}else if(BuildingImage.TYPE2.equals(img.getType())){
        			imageList2.add(img);
        		}
        	}
        }
        map.put("imageList1", imageList1);//轮播图
        map.put("imageList2", imageList2);//户型图
    }

    /**
     * Title:修改-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月12日
     * @param request
     * @param map
     * @param buildingInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="building_info_update", method = RequestMethod.POST)
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,BuildingInfo buildingInfo,
    		String[] images,
    		String[] descType,
    		String[] descArea
    ){
        Response<Void> res = Response.newInstance();
        if(buildingInfo==null || buildingInfo.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        BuildingInfo sourceBuildingInfo = buildingInfoService.get(buildingInfo.getId()).getData();
        if(sourceBuildingInfo==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        buildingInfo.setType(buildingInfo.getType() != null || BuildingInfo.allTypes.containsKey(buildingInfo.getType()) ? buildingInfo.getType() : BuildingInfo.TYPE1);
        buildingInfo.setPraiseCount(sourceBuildingInfo.getPraiseCount());
        buildingInfo.setReplyCount(sourceBuildingInfo.getReplyCount());
        buildingInfo.setId(sourceBuildingInfo.getId());
        buildingInfo.setAppId(sourceBuildingInfo.getAppId());
        buildingInfo.setCreateTime(sourceBuildingInfo.getCreateTime());
        buildingInfo.setCreator(sourceBuildingInfo.getCreator());
        buildingInfo.setCreatorId(sourceBuildingInfo.getCreatorId());
        buildingInfo.setStatus(sourceBuildingInfo.getStatus());
        try {
        	res= buildingInfoService.save(buildingInfo, images, descType, descArea, sessionUser.getUserId(), sessionUser.getNickname());
        	res.setMessage("保存成功");
		} catch (Exception e) {
			res.setMessage("保存失败");
		}
        return res;
    }

    /**
     * Title:查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月12日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="building_info_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        BuildingInfo buildingInfo = buildingInfoService.get(id).getData();
        if(buildingInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", buildingInfo);
        this.getCommonImageList(map, buildingInfo.getId());
        //查询百度地图Key
  		Response<AppInfo> appInforResponse = appInfoService.get(buildingInfo.getAppId());
  		map.put("baiduLbsKey", appInforResponse.getData().getBaiduLbsKey());
  		//显示价格单位
  		map.put("allViewUnits", BuildingInfo.allViewUnits);
  		//户型
  		map.put("allHouseTypes", BuildingInfo.allHouseTypes);
  		//类型
  		map.put("allTypes", BuildingInfo.allTypes);
  		//默认排序
  		map.put("defaultSortNo", Integer.MAX_VALUE);
  		map.put("allStatuss", BuildingInfo.allStatuss);
        return getReturnUrl(request,map,"tiles.module.building.building_info_view");
    }

    /**
     * Title:删除、批量删除
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月12日
     * @param request
     * @param map
     * @param id
     * @param ids
     * @return
     */
    @RequestMapping(value ="building_info_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map, @RequestParam(value="id", required=false)Long id, @RequestParam(value="ids[]", required=false)Long[] ids){
    	if(id == null && !(ids != null && ids.length > 0)){
    		return new Response<>(ErrorCodes.FAILURE, "请选择要删除的记录");
    	}
    	if(id != null){
    		ids = new Long[]{id};
    	}
        Response<Void> res = buildingInfoService.deleteLogic(ids);
        return res;
    }

    /**
     * Title:上线、下线、批量上线、批量下线
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月12日
     * @param request
     * @param type
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="building_info_online/{type}")
    public Response<Void> online(HttpServletRequest request, @PathVariable Byte type, @RequestParam(required=false)Long id, @RequestParam(value="ids[]", required=false)Long[] ids){
    	Response<Void> res = Response.newInstance();
    	Byte status = type.equals((byte)1) ? BuildingInfo.STATUS3 : type.equals((byte)2) ? BuildingInfo.STATUS88 : BuildingInfo.STATUS1;
    	if(status.equals(BuildingInfo.STATUS1) || (id == null && !(ids != null && ids.length > 0))){
    		res.setCode(ErrorCodes.FAILURE);
    		res.setMessage("参数错误");
    		return res;
    	}
    	if(id != null){
    		ids = new Long[]{id};
    	}
    	res = buildingInfoService.updateStatus(status, ids);
    	return res;
    }

    /**
     * Title:排序
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月12日
     * @param request
     * @param map
     * @param id
     * @param sortNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="building_info_sort")
    public Response<Void> modifySortNo(HttpServletRequest request, Map<String, Object> map, 
    		@RequestParam("i") Long id, 
    		@RequestParam(value = "s", required = false) Integer sortNo) {
    	Response<Void> res = buildingInfoService.modifySortNo(id, sortNo);
    	return res;
    }
}
