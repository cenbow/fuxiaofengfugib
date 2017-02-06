package com.cqliving.cloud.controller.module.act;

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
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.act.domain.ActTemplate;
import com.cqliving.cloud.online.act.service.ActTemplateService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/act")
public class ActTemplateController extends CommonController {

    @Autowired
    private ActTemplateService actTemplateService;

    //列表
    @RequestMapping(value ="act_templatelist")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
		
        PageInfo<ActTemplate> pageInfo = getPageInfo(request);
        map.put("pageInfo", actTemplateService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allTypes", ActTemplate.allTypes);
        map.put("allCommonTypes", ActTemplate.allCommonTypes);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/act/act_templatelist_page";
        }else{
        	return "tiles.module.act.act_templatelist";
        }
    }

    //增加-查看
    @RequestMapping(value ="act_templateadd", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.act.act_templatedetail";
    }


    //增加-保存
    @RequestMapping(value ="act_templateadd", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ActTemplate actTemplate){
        //ID
        actTemplate.setId(null);
        Response<Void> res = actTemplateService.save(actTemplate);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="act_templateupdate", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActTemplate actTemplate = actTemplateService.get(id).getData();
        if(actTemplate==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actTemplate);
        return "tiles.module.act.act_templatedetail";
    }

    //修改-保存
    @RequestMapping(value ="act_templateupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ActTemplate actTemplate){
        Response<Void> res = Response.newInstance();
        if(actTemplate==null || actTemplate.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ActTemplate sourceActTemplate = actTemplateService.get(actTemplate.getId()).getData();
            if(sourceActTemplate==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //客户端_ID
            sourceActTemplate.setAppId(actTemplate.getAppId());
            //模板CODE
            sourceActTemplate.setTempletCode(actTemplate.getTempletCode());
            //模板图片
            sourceActTemplate.setImageUrl(actTemplate.getImageUrl());
            //模板类型
            sourceActTemplate.setType(actTemplate.getType());
            //模版描述
            sourceActTemplate.setTempletDesc(actTemplate.getTempletDesc());
            //模版名称
            sourceActTemplate.setName(actTemplate.getName());
            //模版公有状态
            sourceActTemplate.setCommonType(actTemplate.getCommonType());
            res= actTemplateService.save(sourceActTemplate);
            actTemplate = sourceActTemplate;
        }catch (Exception ex){
            logger.error("Save Method (Update) ActTemplate Error : " + actTemplate.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="act_templateview")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActTemplate actTemplate = actTemplateService.get(id).getData();
        if(actTemplate==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actTemplate);
        return "tiles.module.act.act_templateview";
    }

    //删除
    @RequestMapping(value ="act_templatedelete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = actTemplateService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="act_templatedelete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = actTemplateService.delete(ids);
        return res;
    }
}
