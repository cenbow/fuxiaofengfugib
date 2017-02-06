package com.cqliving.cloud.controller.module.tourism;

import java.util.Date;
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

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.service.ConfigRegionService;
import com.cqliving.cloud.online.tourism.domain.TourismImage;
import com.cqliving.cloud.online.tourism.domain.TourismInfo;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.cloud.online.tourism.service.TourismImageService;
import com.cqliving.cloud.online.tourism.service.TourismInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/tourism")
public class TourismInfoController extends CommonController {

	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private ConfigRegionService configRegionService;
    @Autowired
    private TourismInfoService tourismInfoService;
    @Autowired
    private TourismImageService tourismImageService;

    //列表
    @RequestMapping(value ="tourism_info_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    		@RequestParam(value = "p", required = false) String isAjaxPage, 
    		@RequestParam(value = "search_EQ_appId", required = false) Long appId) {
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        
        //获取操作用户数据权限范围
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
    	
    	//查询列表数据
    	PageInfo<TourismInfoDto> pageInfo = getPageInfo(request);
    	searchMap.put("NOTEQ_status", TourismInfo.STATUS99);//排除逻辑删除状态
    	sortMap.put("sortNo", true);
    	sortMap.put("updateTime", false);
    	sortMap.put("id", false);
    	map.put("pageInfo", tourismInfoService.queryDtoForPage(pageInfo, searchMap, sortMap).getData());
    	map.put("allTypes", TourismInfo.allTypes);
    	map.put("allIsShows", TourismInfo.allIsShows);
    	map.put("allStatuss", TourismInfo.allStatuss);
    	map.put("maxSortNo", Integer.MAX_VALUE);
    	//获取全部旅游区域
        Response<List<ConfigRegion>> regionResponse = configRegionService.getByAppAndType(null, BusinessType.SOURCE_TYPE_10);
        map.put("regionList", regionResponse.getData());      
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/tourism/tourism_info_list_page";
        }else{
        	return "tiles.module.tourism.tourism_info_list";
        }
    }

    /**
     * Title:增加-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月24日
     * @param request
     * @param map
     * @param type
     * @return
     */
    @RequestMapping(value ="tourism_info_add/{type}", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map, @PathVariable(value="type") Byte type){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        Long appId = sessionUser.getAppId();
        if (appId == null) {
        	return super.operFailure("请登录App管理员操作", map);
		}
        if(!TourismInfo.allTypes.containsKey(type)){
        	return super.operFailure("地址有误", map);
        }
        map.put("appId", appId);
        map.put("sourceType", type);
        map.put("allTypes", TourismInfo.allTypes);
        map.put("TYPE1", TourismInfo.TYPE1);
        map.put("TYPE2", TourismInfo.TYPE2);
    	//查询百度地图Key
  		Response<AppInfo> appInforResponse = appInfoService.get(appId);
  		map.put("baiduLbsKey", appInforResponse.getData().getBaiduLbsKey());
  		//获取区域
        Response<List<ConfigRegion>> regionResponse = configRegionService.getByAppAndType(new Long[]{appId}, BusinessType.SOURCE_TYPE_10);
        map.put("allRegion", regionResponse.getData());
        
    	return getReturnUrl(request,map,"tiles.module.tourism.tourism_info_detail");
    }


    /**
     * Title:增加-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月24日
     * @param request
     * @param map
     * @param tourismInfo
     * @param images
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="tourism_info_add/{sourceType}", method = RequestMethod.POST)
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map, TourismInfo tourismInfo, String images, @PathVariable("sourceType") Byte sourceType){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Date date = new Date();
        //ID
        tourismInfo.setId(null);
        tourismInfo.setIsShow(TourismInfo.ISSHOW1);
        tourismInfo.setSortNo(tourismInfo.getSortNo() == null ? Integer.MAX_VALUE : tourismInfo.getSortNo());
        tourismInfo.setIsLink((tourismInfo.getIsLink() != null && TourismInfo.allIsLinks.containsKey(tourismInfo.getIsLink())) ? tourismInfo.getIsLink() : TourismInfo.ISLINK0);
        tourismInfo.setStatus(TourismInfo.STATUS1);
        tourismInfo.setType(sourceType);
        
        tourismInfo.setCreateTime(date);
        tourismInfo.setCreator(sessionUser.getNickname());
        tourismInfo.setCreatorId(sessionUser.getUserId());
        tourismInfo.setUpdateTime(date);
        tourismInfo.setUpdator(sessionUser.getNickname());
        tourismInfo.setUpdatorId(sessionUser.getUserId());
        
        Response<Void> res = tourismInfoService.saveByAdmin(tourismInfo, images, sessionUser.getUserId(), sessionUser.getNickname());
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    /**
     * Title:修改-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月25日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="tourism_info_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        TourismInfo tourismInfo = tourismInfoService.get(id).getData();
        if(tourismInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        List<TourismImage> images = tourismImageService.findByTourismId(tourismInfo.getId()).getData();
        Long appId = tourismInfo.getAppId();
        map.put("appId", appId);
        map.put("item", tourismInfo);
        map.put("images", images);
        map.put("sourceType", tourismInfo.getType());
        map.put("allTypes", TourismInfo.allTypes);
        map.put("TYPE1", TourismInfo.TYPE1);
        map.put("TYPE2", TourismInfo.TYPE2);
    	//查询百度地图Key
  		Response<AppInfo> appInforResponse = appInfoService.get(appId);
  		map.put("baiduLbsKey", appInforResponse.getData().getBaiduLbsKey());
  		//获取区域
        Response<List<ConfigRegion>> regionResponse = configRegionService.getByAppAndType(new Long[]{appId}, BusinessType.SOURCE_TYPE_10);
        map.put("allRegion", regionResponse.getData());
        return getReturnUrl(request,map,"tiles.module.tourism.tourism_info_detail");
    }

    /**
     * Title:修改-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月25日
     * @param request
     * @param map
     * @param tourismInfo
     * @param images
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="tourism_info_update", method = RequestMethod.POST)
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,TourismInfo tourismInfo, String images){
        Response<Void> res = Response.newInstance();
        if(tourismInfo==null || tourismInfo.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            TourismInfo sourceTourismInfo = tourismInfoService.get(tourismInfo.getId()).getData();
            if(sourceTourismInfo==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            Date date = new Date();
            SessionUser sessionUser = SessionFace.getSessionUser(request);
            //名称
            sourceTourismInfo.setName(tourismInfo.getName());
            //是否外链
            if(tourismInfo.getIsLink() != null){
            	sourceTourismInfo.setIsLink(tourismInfo.getIsLink());
            	sourceTourismInfo.setLinkUrl(tourismInfo.getLinkUrl());
            }
            //所处位置
            sourceTourismInfo.setPlace(tourismInfo.getPlace());
            //所处位置纬度
            sourceTourismInfo.setLat(tourismInfo.getLat());
            //所处位置经度
            sourceTourismInfo.setLng(tourismInfo.getLng());
            //所属区域CODE
            sourceTourismInfo.setRegionCode(tourismInfo.getRegionCode());
            //列表图地址
            sourceTourismInfo.setImageUrl(tourismInfo.getImageUrl());
            //排序号
            if(tourismInfo.getSortNo() != null){
            	sourceTourismInfo.setSortNo(tourismInfo.getSortNo());
            }
            //景区价格
            sourceTourismInfo.setPrice(tourismInfo.getPrice());
            //开放时间，特意把time字段放前面
            sourceTourismInfo.setTimeOpen(tourismInfo.getTimeOpen());
            //气候类型
            sourceTourismInfo.setClimateType(tourismInfo.getClimateType());
            //联系电话,最多三个用逗号分隔
            sourceTourismInfo.setTelephone(tourismInfo.getTelephone());
            //地点
            sourceTourismInfo.setAddress(tourismInfo.getAddress());
            //专题描述
            sourceTourismInfo.setDescription(tourismInfo.getDescription());
            //景点介绍内容
            sourceTourismInfo.setContent(tourismInfo.getContent());
            //景点线路
            sourceTourismInfo.setScenicRoute(tourismInfo.getScenicRoute());
            //摘要，如果为空，则取content字段并截字
            sourceTourismInfo.setSynopsis(tourismInfo.getSynopsis());
            //更新时间
            sourceTourismInfo.setUpdateTime(date);
            //更新人ID
            sourceTourismInfo.setUpdatorId(sessionUser.getUserId());
            //更新人
            sourceTourismInfo.setUpdator(sessionUser.getNickname());
            res= tourismInfoService.saveByAdmin(sourceTourismInfo, images, sessionUser.getUserId(), sessionUser.getNickname());
            tourismInfo = sourceTourismInfo;
        }catch (Exception ex){
            logger.error("Save Method (Update) TourismInfo Error : " + tourismInfo.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        return res;
    }

    /**
     * Title:预览
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月26日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="common/tourism_info_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        TourismInfo tourismInfo = tourismInfoService.get(id).getData();
        if(tourismInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", tourismInfo);
        String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
        //景点预览地址
        String uri = webPath + "tourism/tourism_detail/"+tourismInfo.getId()+".html";
        if(TourismInfo.TYPE2.equals(tourismInfo.getType())){//专题预览地址
        	uri = webPath + "tourism/special_detail/"+tourismInfo.getId()+".html";
        }
    	map.put("viewUrl", uri + "?view=view");//view参数是固定的。区分是预览还是H5页面
        return getReturnUrl(request,map,"tiles.module.tourism.tourism_info_view");
    }

    /**
     * Title:删除、批量删除
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月25日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="tourism_info_delete", method = RequestMethod.POST)
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id", required=false) Long id, @RequestParam(value="ids[]", required=false) Long[] ids){
    	if(id == null && (ids == null || ids.length == 0)){
    		return new Response<Void>(ErrorCodes.FAILURE, "参数有误");
    	}
    	if(id != null && id > 0){
    		ids = new Long[]{id};
    	}
        Response<Void> res = tourismInfoService.deleteLogic(ids);
        return res;
    }
    
    /**
     * <p>Description: 修改排序</p>
     * @author Tangtao on 2016年8月24日
     * @param request
     * @param map
     * @param id
     * @param sortNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="modify_sort_no")
    public Response<Void> modifySortNo(HttpServletRequest request, Map<String, Object> map, 
    		@RequestParam("i") Long id, 
    		@RequestParam(value = "s", required = false) Integer sortNo) {
    	Response<Void> res = tourismInfoService.modifySortNo(id, sortNo);
    	return res;
    }
    
    /**
     * Title:发布
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月25日
     * @param request
     * @param map
     * @param id
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="tourism_info_publish", method = RequestMethod.POST)
    public Response<Void> publish(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id", required=false) Long id, @RequestParam(value="ids[]", required=false) Long[] ids){
    	if(id == null && (ids == null || ids.length == 0)){
    		return new Response<Void>(ErrorCodes.FAILURE, "参数有误");
    	}
    	if(id != null && id > 0){
    		ids = new Long[]{id};
    	}
    	Response<Void> res = tourismInfoService.updateStatus(TourismInfo.STATUS3, ids);
    	return res;
    }
    /**
     * Title:下线
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月25日
     * @param request
     * @param map
     * @param id
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="tourism_info_offline", method = RequestMethod.POST)
    public Response<Void> offline(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id", required=false) Long id, @RequestParam(value="ids[]", required=false) Long[] ids){
    	if(id == null && (ids == null || ids.length == 0)){
    		return new Response<Void>(ErrorCodes.FAILURE, "参数有误");
    	}
    	if(id != null && id > 0){
    		ids = new Long[]{id};
    	}
    	Response<Void> res = tourismInfoService.updateStatus(TourismInfo.STATUS88, ids);
    	return res;
    }
    
    //修改排序号
    @RequestMapping(value ="/common/update_sort_no")
    @ResponseBody
    public Response<Void> updateSortNo(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,Integer sortNo){
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        Response<Void> res = tourismInfoService.updateSortNo(sortNo==null?Integer.MAX_VALUE:sortNo,sessionUser.getNickname(),sessionUser.getUserId(),id);
        return res;
    }
    
}