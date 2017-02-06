package com.cqliving.cloud.controller.module.shoot;

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
import com.cqliving.cloud.online.shoot.domain.ShootImages;
import com.cqliving.cloud.online.shoot.service.ShootImagesService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/shoot")
public class ShootImagesController extends CommonController {

    @Autowired
    private ShootImagesService shootImagesService;

    //列表
    @RequestMapping(value ="shoot_imageslist")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
        ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime
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
		
        PageInfo<ShootImages> pageInfo = getPageInfo(request);
        map.put("pageInfo", shootImagesService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/shoot/shoot_imageslist_page";
        }else{
        	return "tiles.module.shoot.shoot_imageslist";
        }
    }

    //增加-查看
    @RequestMapping(value ="shoot_imagesadd", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.shoot.shoot_imagesdetail";
    }


    //增加-保存
    @RequestMapping(value ="shoot_imagesadd", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ShootImages shootImages){
        //ID
        shootImages.setId(null);
        Response<Void> res = shootImagesService.save(shootImages);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="shoot_imagesupdate", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ShootImages shootImages = shootImagesService.get(id).getData();
        if(shootImages==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", shootImages);
        return "tiles.module.shoot.shoot_imagesdetail";
    }

    //修改-保存
    @RequestMapping(value ="shoot_imagesupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ShootImages shootImages){
        Response<Void> res = Response.newInstance();
        if(shootImages==null || shootImages.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ShootImages sourceShootImages = shootImagesService.get(shootImages.getId()).getData();
            if(sourceShootImages==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //随手拍_ID
            sourceShootImages.setShootId(shootImages.getShootId());
            //系统文件表ID（对应INFO_IFLE表主键）
            sourceShootImages.setInfoFileId(shootImages.getInfoFileId());
            //图片URL
            sourceShootImages.setImageUrl(shootImages.getImageUrl());
            //创建时间
            sourceShootImages.setCreateTime(shootImages.getCreateTime());
            res= shootImagesService.save(sourceShootImages);
            shootImages = sourceShootImages;
        }catch (Exception ex){
            logger.error("Save Method (Update) ShootImages Error : " + shootImages.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="shoot_imagesview")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ShootImages shootImages = shootImagesService.get(id).getData();
        if(shootImages==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", shootImages);
        return "tiles.module.shoot.shoot_imagesview";
    }

    //删除
    @RequestMapping(value ="shoot_imagesdelete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = shootImagesService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="shoot_imagesdelete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = shootImagesService.delete(ids);
        return res;
    }
}
