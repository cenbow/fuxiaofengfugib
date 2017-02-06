package com.cqliving.cloud.controller.module.config;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppMarketplaceResource;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.app.service.AppMarketplaceResourceService;
import com.cqliving.cloud.online.shopping.domain.ShoppingRecommend;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;

@Controller
@RequestMapping(value = "/module/advert")
public class AppMarketplaceResourceController extends CommonController {

    @Autowired
    private AppMarketplaceResourceService appMarketplaceResourceService;
    @Autowired
    private AppInfoService appInfoService;

    //列表
    @RequestMapping(value ="advert_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        
        String searchAppid = (String)searchMap.get("EQ_appId");
        //处理App查询条件以及页面上展示的app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList&&appList.size()>1){
            map.put("appList", appList);
        }
        if(StringUtils.isBlank(searchAppid)){
            if(null!=appList&&appList.size()>0){
                searchMap.put("EQ_appId", appList.get(0).getId());
            }else{
                searchMap.put("EQ_appId", user.getAppId());
            }
        }
        
        //排序
        Map<String, Boolean> sortMap = new LinkedHashMap<String, Boolean>();
        sortMap.put("sortNo", true);
        sortMap.put("id", true);
        
        PageInfo<AppMarketplaceResource> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", AppMarketplaceResource.STATUS99);//排除逻辑删除状态
        searchMap.put("EQ_imageType", AppMarketplaceResource.IMAGETYPE2);//查询首页广告图
        map.put("pageInfo", appMarketplaceResourceService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allStatuss", AppMarketplaceResource.allStatuss);
        map.put("STATUS3", AppMarketplaceResource.STATUS3);
        map.put("STATUS88", AppMarketplaceResource.STATUS88);
        map.put("maxSortNo", Integer.MAX_VALUE);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/config/advert_list_page";
        }else{
        	return "tiles.module.config.advert_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="advert_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	return getReturnUrl(request,map,"tiles.module.config.advert_detail");
    }


    //增加-保存
    @RequestMapping(value ="advert_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,AppMarketplaceResource appMarketplaceResource){
        SessionUser user = SessionFace.getSessionUser(request);
        //主键
        appMarketplaceResource.setId(null);
        //APP_ID
        appMarketplaceResource.setAppId(null==appMarketplaceResource.getAppId()?user.getAppId():appMarketplaceResource.getAppId());
        //图片版本ID
        appMarketplaceResource.setVersionId(0l);
        //图片类型
        appMarketplaceResource.setImageType(AppMarketplaceResource.IMAGETYPE2);
        //上线状态
        appMarketplaceResource.setStatus(AppMarketplaceResource.STATUS3);
        //排序号
        appMarketplaceResource.setSortNo(Integer.MAX_VALUE);
        Date now = new Date();
        //创建时间
        appMarketplaceResource.setCreateTime(now);
        //创建人ID
        appMarketplaceResource.setCreatorId(user.getUserId());
        //创建人名称
        appMarketplaceResource.setCreator(user.getNickname());
        //更新人ID
        appMarketplaceResource.setUpdatorId(user.getUserId());
        //更新人
        appMarketplaceResource.setUpdator(user.getNickname());
        //更新时间
        appMarketplaceResource.setUpdateTime(now);
        Response<Void> res = appMarketplaceResourceService.save(appMarketplaceResource);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="advert_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        AppMarketplaceResource appMarketplaceResource = appMarketplaceResourceService.get(id).getData();
        if(appMarketplaceResource==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", appMarketplaceResource);
        return getReturnUrl(request,map,"tiles.module.config.advert_detail");
    }

    //修改-保存
    @RequestMapping(value ="advert_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,AppMarketplaceResource appMarketplaceResource){
        Response<Void> res = Response.newInstance();
        if(appMarketplaceResource==null || appMarketplaceResource.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            AppMarketplaceResource sourceAppMarketplaceResource = appMarketplaceResourceService.get(appMarketplaceResource.getId()).getData();
            if(sourceAppMarketplaceResource==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //图片地址
            sourceAppMarketplaceResource.setImageUrl(appMarketplaceResource.getImageUrl());
            //广告链接地址
            sourceAppMarketplaceResource.setLinkUrl(appMarketplaceResource.getLinkUrl());
            res= appMarketplaceResourceService.save(sourceAppMarketplaceResource);
            if(res.getCode() < 0){
                return new Response<Void>(-1,"修改失败");
            }
            appMarketplaceResource = sourceAppMarketplaceResource;
        }catch (Exception ex){
            logger.error("Save Method (Update) AppMarketplaceResource Error : " + appMarketplaceResource.toString(), ex);
            //修改失败
            return new Response<Void>(-1,"修改失败");
        }
        res.setMessage("修改成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="advert_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        AppMarketplaceResource appMarketplaceResource = appMarketplaceResourceService.get(id).getData();
        if(appMarketplaceResource==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", appMarketplaceResource);
        return getReturnUrl(request,map,"tiles.module.config.advert_view");
    }

    //删除
    @RequestMapping(value ="advert_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = appMarketplaceResourceService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="advert_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = appMarketplaceResourceService.deleteLogic(ids);
        return res;
    }
    
  //修改排序号
    @RequestMapping(value ="/common/update_sort_no")
    @ResponseBody
    public Response<Void> updateSortNo(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,Integer sortNo){
        Response<Void> res = appMarketplaceResourceService.updateSortNo(null==sortNo?Integer.MAX_VALUE:sortNo,id);
        return res;
    }
    
    //上线
    @RequestMapping(value ={"on_line","shuffling_figure_on_line"})
    @ResponseBody
    public Response<Void> onLine(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = appMarketplaceResourceService.updateStatus(ShoppingRecommend.STATUS3,ids);
        return res;
    }
    
    //下线
    @RequestMapping(value = {"out_line","shuffling_figure_out_line"})
    @ResponseBody
    public Response<Void> outLine(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = appMarketplaceResourceService.updateStatus(ShoppingRecommend.STATUS88,ids);
        return res;
    }
}
