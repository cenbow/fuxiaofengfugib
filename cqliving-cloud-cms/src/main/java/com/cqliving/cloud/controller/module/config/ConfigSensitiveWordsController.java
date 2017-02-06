package com.cqliving.cloud.controller.module.config;

import java.util.Date;
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
import com.cqliving.cloud.online.config.domain.ConfigSensitiveWords;
import com.cqliving.cloud.online.config.dto.ConfigSensitiveWordsDto;
import com.cqliving.cloud.online.config.service.ConfigSensitiveWordsService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/sensitiveWords")
public class ConfigSensitiveWordsController extends CommonController {

    @Autowired
    private ConfigSensitiveWordsService configSensitiveWordsService;
    
    @Autowired
    private AppInfoService appInfoService;
    //列表
    @RequestMapping(value ="config_sensitive_words_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("updateTime", false);
        sortMap.put("id", false);
        
        Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        PageInfo<ConfigSensitiveWordsDto> pageInfo = getPageInfo(request);
        
        searchMap.put("NOTEQ_status", ConfigSensitiveWords.STATUS99);//排除逻辑删除状态
        //处理APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        
        String searchAppid = (String)searchMap.get("EQ_appId");
        if(StringUtils.isBlank(searchAppid)){
            userDate(user, appList, searchMap);
        }
        
        map.put("pageInfo", configSensitiveWordsService.queryPage(pageInfo, searchMap, sortMap).getData());
        map.put("allTypes", ConfigSensitiveWords.allTypes);
        
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/cfg/config_sensitive_words_list_page";
        }else{
        	return "tiles.module.cfg.config_sensitive_words_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="config_sensitive_words_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        map.put("allTypes", ConfigSensitiveWords.allTypes);
        map.put("action", "/module/sensitiveWords/config_sensitive_words_add.html");
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        return getReturnUrl(request,map,"tiles.module.cfg.config_sensitive_words_detail");
    }


    //增加-保存
    @RequestMapping(value ="config_sensitive_words_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ConfigSensitiveWords configSensitiveWords){
        //ID
        configSensitiveWords.setId(null);
            //状态
        configSensitiveWords.setStatus(ConfigSensitiveWords.STATUS3);
            //创建时间
        configSensitiveWords.setCreateTime(new Date());
        //更新时间
        configSensitiveWords.setUpdateTime(configSensitiveWords.getCreateTime());
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            //创建人ID
            configSensitiveWords.setCreatorId(user.getUserId());
            //创建人名称
            configSensitiveWords.setCreator(user.getNickname());
            //更新人ID
            configSensitiveWords.setUpdatorId(user.getUserId());
            //更新人
            configSensitiveWords.setUpdator(user.getNickname());
            configSensitiveWords.setAppId(configSensitiveWords.getAppId()==null?user.getAppId():configSensitiveWords.getAppId());
        }
        Response<Void> res = configSensitiveWordsService.save(configSensitiveWords);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="config_sensitive_words_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ConfigSensitiveWords configSensitiveWords = configSensitiveWordsService.get(id).getData();
        if(configSensitiveWords==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", configSensitiveWords);
        map.put("allTypes", ConfigSensitiveWords.allTypes);
        map.put("action", "/module/sensitiveWords/config_sensitive_words_update.html");
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        return getReturnUrl(request,map,"tiles.module.cfg.config_sensitive_words_detail");
    }

    //修改-保存
    @RequestMapping(value ="config_sensitive_words_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ConfigSensitiveWords configSensitiveWords){
        Response<Void> res = Response.newInstance();
        if(configSensitiveWords==null || configSensitiveWords.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ConfigSensitiveWords sourceConfigSensitiveWords = configSensitiveWordsService.get(configSensitiveWords.getId()).getData();
            if(sourceConfigSensitiveWords==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            sourceConfigSensitiveWords.setAppId(configSensitiveWords.getAppId()==null?sourceConfigSensitiveWords.getAppId():configSensitiveWords.getAppId());
            //敏感词
            sourceConfigSensitiveWords.setName(configSensitiveWords.getName());
            //类型
            sourceConfigSensitiveWords.setType(configSensitiveWords.getType());
            sourceConfigSensitiveWords.setUpdateTime(new Date());
            SessionUser user = SessionFace.getSessionUser(request);
            if(null!=user){
                //更新人ID
                sourceConfigSensitiveWords.setUpdatorId(user.getUserId());
                //updator
                sourceConfigSensitiveWords.setUpdator(user.getNickname());
            }
            res= configSensitiveWordsService.save(sourceConfigSensitiveWords);
            configSensitiveWords = sourceConfigSensitiveWords;
        }catch (Exception ex){
            logger.error("Save Method (Update) ConfigSensitiveWords Error : " + configSensitiveWords.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="config_sensitive_words_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ConfigSensitiveWordsDto configSensitiveWords = configSensitiveWordsService.getById(id).getData();
        if(configSensitiveWords==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", configSensitiveWords);
        map.put("allTypes", ConfigSensitiveWords.allTypes);
        return getReturnUrl(request,map,"tiles.module.cfg.config_sensitive_words_view");
    }

    //删除
    @RequestMapping(value ="config_sensitive_words_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = configSensitiveWordsService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="config_sensitive_words_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = configSensitiveWordsService.deleteLogic(ids);
        return res;
    }
}
