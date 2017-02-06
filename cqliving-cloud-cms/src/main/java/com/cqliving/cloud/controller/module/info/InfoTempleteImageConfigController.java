package com.cqliving.cloud.controller.module.info;

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
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.info.domain.InfoTempleteImageConfig;
import com.cqliving.cloud.online.info.dto.InfoTempleteImageConfigDto;
import com.cqliving.cloud.online.info.service.InfoTempleteImageConfigService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/infoeTmpleteImageConfig")
public class InfoTempleteImageConfigController extends CommonController {

    @Autowired
    private InfoTempleteImageConfigService infoTempleteImageConfigService;
    @Autowired
    private AppInfoService appInfoService;

    //列表
    @RequestMapping(value ="info_templete_image_config_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
        String searchAppid = (String)searchMap.get("EQ_appId");
        //处理APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        if(StringUtils.isBlank(searchAppid)){
            userDate(user, appList, searchMap);
        }
		
        PageInfo<InfoTempleteImageConfig> pageInfo = getPageInfo(request);
        map.put("pageInfo", infoTempleteImageConfigService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/info/info_templete_image_config_list_page";
        }else{
        	return "tiles.module.info.info_templete_image_config_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="info_templete_image_config_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        //处理APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        return "tiles.module.info.info_templete_image_config_detail";
    }


    //增加-保存
    @RequestMapping(value ="info_templete_image_config_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,InfoTempleteImageConfig infoTempleteImageConfig){
        //ID
        infoTempleteImageConfig.setId(null);
        Response<Void> res = infoTempleteImageConfigService.save(infoTempleteImageConfig);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="info_templete_image_config_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        InfoTempleteImageConfig infoTempleteImageConfig = infoTempleteImageConfigService.get(id).getData();
        if(infoTempleteImageConfig==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", infoTempleteImageConfig);
        //处理APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        return "tiles.module.info.info_templete_image_config_detail";
    }

    //修改-保存
    @RequestMapping(value ="info_templete_image_config_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,InfoTempleteImageConfig infoTempleteImageConfig){
        Response<Void> res = Response.newInstance();
        if(infoTempleteImageConfig==null || infoTempleteImageConfig.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            InfoTempleteImageConfig sourceInfoTempleteImageConfig = infoTempleteImageConfigService.get(infoTempleteImageConfig.getId()).getData();
            if(sourceInfoTempleteImageConfig==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //模板名称
            sourceInfoTempleteImageConfig.setName(infoTempleteImageConfig.getName());
            //模板CODE
            sourceInfoTempleteImageConfig.setTempletCode(infoTempleteImageConfig.getTempletCode());
            //是否等比压缩，不限制是以
            sourceInfoTempleteImageConfig.setLimitType(infoTempleteImageConfig.getLimitType());
            //宽
            sourceInfoTempleteImageConfig.setWidth(infoTempleteImageConfig.getWidth());
            //高
            sourceInfoTempleteImageConfig.setHight(infoTempleteImageConfig.getHight());
            //备注说明
            sourceInfoTempleteImageConfig.setContext(infoTempleteImageConfig.getContext());
            //列表图片类型
            sourceInfoTempleteImageConfig.setListType(infoTempleteImageConfig.getListType());
            res= infoTempleteImageConfigService.save(sourceInfoTempleteImageConfig);
            infoTempleteImageConfig = sourceInfoTempleteImageConfig;
        }catch (Exception ex){
            logger.error("Save Method (Update) InfoTempleteImageConfig Error : " + infoTempleteImageConfig.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="info_templete_image_config_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        InfoTempleteImageConfigDto infoTempleteImageConfig = infoTempleteImageConfigService.getById(id).getData();
        if(infoTempleteImageConfig==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", infoTempleteImageConfig);
        map.put("allLimitTypes", InfoTempleteImageConfig.allLimitTypes);
        map.put("allListTypes", InfoTempleteImageConfig.allListTypes);
        return "tiles.module.info.info_templete_image_config_view";
    }

    //删除
    @RequestMapping(value ="info_templete_image_config_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = infoTempleteImageConfigService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="info_templete_image_config_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = infoTempleteImageConfigService.delete(ids);
        return res;
    }
}
