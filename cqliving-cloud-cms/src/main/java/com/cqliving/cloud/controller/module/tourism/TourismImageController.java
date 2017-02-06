package com.cqliving.cloud.controller.module.tourism;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.tourism.domain.TourismImage;
import com.cqliving.cloud.online.tourism.service.TourismImageService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/tourism")
public class TourismImageController extends CommonController {

    @Autowired
    private TourismImageService tourismImageService;

    //列表
    @RequestMapping(value ="tourism_image_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
        ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime
        ,@RequestParam(value="search_GTE_updateTime", required=false) Date search_GTE_updateTime
        ,@RequestParam(value="search_LT_updateTime", required=false) Date search_LT_updateTime
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
        //默认时间范围3个月
    	search_LT_createTime = search_LT_createTime != null ?search_LT_createTime: Calendar.getInstance().getTime();
        map.put("search_LT_createTime", search_LT_createTime);
        searchMap.put("LT_createTime", DateUtils.truncate(DateUtils.addDays(search_LT_createTime, 1), Calendar.DATE));
        search_GTE_createTime = search_GTE_createTime != null ? search_GTE_createTime:DateUtils.addMonths(search_LT_createTime, -3);
        map.put("search_GTE_createTime", search_GTE_createTime);
        searchMap.put("GTE_createTime", search_GTE_createTime);
        //默认时间范围3个月
    	search_LT_updateTime = search_LT_updateTime != null ?search_LT_updateTime: Calendar.getInstance().getTime();
        map.put("search_LT_updateTime", search_LT_updateTime);
        searchMap.put("LT_updateTime", DateUtils.truncate(DateUtils.addDays(search_LT_updateTime, 1), Calendar.DATE));
        search_GTE_updateTime = search_GTE_updateTime != null ? search_GTE_updateTime:DateUtils.addMonths(search_LT_updateTime, -3);
        map.put("search_GTE_updateTime", search_GTE_updateTime);
        searchMap.put("GTE_updateTime", search_GTE_updateTime);
		
        PageInfo<TourismImage> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", TourismImage.STATUS99);//排除逻辑删除状态
        map.put("pageInfo", tourismImageService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allStatuss", TourismImage.allStatuss);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/tourism/tourism_image_list_page";
        }else{
        	return "tiles.module.tourism.tourism_image_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="tourism_image_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	return getReturnUrl(request,map,"tiles.module.tourism.tourism_image_detail");
    }


    //增加-保存
    @RequestMapping(value ="tourism_image_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,TourismImage tourismImage){
        //ID
        tourismImage.setId(null);
        Response<Void> res = tourismImageService.save(tourismImage);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="tourism_image_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        TourismImage tourismImage = tourismImageService.get(id).getData();
        if(tourismImage==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", tourismImage);
        return getReturnUrl(request,map,"tiles.module.tourism.tourism_image_detail");
    }

    //修改-保存
    @RequestMapping(value ="tourism_image_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,TourismImage tourismImage){
        Response<Void> res = Response.newInstance();
        if(tourismImage==null || tourismImage.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            TourismImage sourceTourismImage = tourismImageService.get(tourismImage.getId()).getData();
            if(sourceTourismImage==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //客户端_ID
            sourceTourismImage.setAppId(tourismImage.getAppId());
            //旅游ID
            sourceTourismImage.setTourismId(tourismImage.getTourismId());
            //图片地址
            sourceTourismImage.setUrl(tourismImage.getUrl());
            //排序号
            sourceTourismImage.setSortNo(tourismImage.getSortNo());
            //状态
            sourceTourismImage.setStatus(tourismImage.getStatus());
            //创建时间
            sourceTourismImage.setCreateTime(tourismImage.getCreateTime());
            //创建人ID
            sourceTourismImage.setCreatorId(tourismImage.getCreatorId());
            //创建人
            sourceTourismImage.setCreator(tourismImage.getCreator());
            //更新时间
            sourceTourismImage.setUpdateTime(tourismImage.getUpdateTime());
            //更新人ID
            sourceTourismImage.setUpdatorId(tourismImage.getUpdatorId());
            //更新人
            sourceTourismImage.setUpdator(tourismImage.getUpdator());
            res= tourismImageService.save(sourceTourismImage);
            tourismImage = sourceTourismImage;
        }catch (Exception ex){
            logger.error("Save Method (Update) TourismImage Error : " + tourismImage.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="tourism_image_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        TourismImage tourismImage = tourismImageService.get(id).getData();
        if(tourismImage==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", tourismImage);
        return getReturnUrl(request,map,"tiles.module.tourism.tourism_image_view");
    }

    //删除
    @RequestMapping(value ="tourism_image_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = tourismImageService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="tourism_image_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = tourismImageService.deleteLogic(ids);
        return res;
    }
}
