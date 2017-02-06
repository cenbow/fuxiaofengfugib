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
import com.cqliving.cloud.online.config.domain.ConfigHotline;
import com.cqliving.cloud.online.config.dto.ConfigHotlineDto;
import com.cqliving.cloud.online.config.service.ConfigHotlineService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/config_hotline")
public class ConfigHotlineController extends CommonController {

    @Autowired
    private ConfigHotlineService configHotlineService;
    @Autowired
    private AppInfoService appInfoService;

    //列表
    @RequestMapping(value ="config_hotline_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("sortNo", true);
        sortMap.put("id", false);
		
        PageInfo<ConfigHotlineDto> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", ConfigHotline.STATUS99);//排除逻辑删除状态
        
        String searchAppid = (String)searchMap.get("EQ_appId");
        /*if(StringUtils.isBlank(searchAppid)){
            //处理APP下拉框
            SessionUser user = SessionFace.getSessionUser(request);
            List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
            if(null!=appList && appList.size()>1){
                map.put("appList", appList);
            }else{
                map.put("appId", user.getAppId());
            }
            userDate(user, appList, searchMap);
        }*/
        
        //处理APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }else{
            map.put("appId", user.getAppId());
        }
        if(StringUtils.isBlank(searchAppid)){
            //过滤App的处理
            userDate(user, appList, searchMap);
        }
        
        map.put("pageInfo", configHotlineService.queryByPage(pageInfo, searchMap, sortMap).getData());
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/config/config_hotline_list_page";
        }else{
        	return "tiles.module.config.config_hotline_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="config_hotline_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }else{
            map.put("appId", user.getAppId());
        }
    	return getReturnUrl(request,map,"tiles.module.config.config_hotline_detail");
    }


    //增加-保存
    @RequestMapping(value ="config_hotline_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ConfigHotline configHotline,String[] phones){
        if(null==configHotline){
            return new Response<Void>(-1,"请输入热线相关信息");
        }
        StringBuffer phone = new StringBuffer();
        if(null == phones || phones.length<1){
            return new Response<Void>(-1,"请填写电话号码");
        }else{
            for (String str : phones) {
                if(StringUtils.isNotBlank(str)){
                    phone.append(str+",");
                }
            }
            if(phone.length()>0){
                phone.delete(phone.length()-1, phone.length());
            }else{
                return new Response<Void>(-1,"请填写电话号码");
            }
        }
        //ID
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            configHotline.setId(null);
            //客户端_ID
            configHotline.setAppId(null==configHotline.getAppId()?user.getAppId():configHotline.getAppId());
            //状态
            configHotline.setStatus(ConfigHotline.STATUS3);
            //电话
            configHotline.setPhone(phone.toString());
            //当前时间
            Date now = new Date();
            //创建时间
            configHotline.setCreateTime(now);
            //创建人ID
            configHotline.setCreatorId(user.getUserId());
            //创建人
            configHotline.setCreator(user.getNickname());
            //更新时间
            configHotline.setUpdateTime(now);
            //更新人ID
            configHotline.setUpdatorId(user.getUserId());
            //更新人
            configHotline.setUpdator(user.getNickname());
            //保存
            Response<Void> res = configHotlineService.save(configHotline);
            if(res.getCode() < 0){
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
            }
            res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
            return res;
        }else{
            return new Response<Void>(-1,"请先登录"); 
        }        
    }

    //修改-查看
    @RequestMapping(value ="config_hotline_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ConfigHotline configHotline = configHotlineService.get(id).getData();
        if(configHotline==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", configHotline);
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }else{
            map.put("appId", user.getAppId());
        }
        return getReturnUrl(request,map,"tiles.module.config.config_hotline_detail");
    }

    //修改-保存
    @RequestMapping(value ="config_hotline_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ConfigHotline configHotline,String[] phones){
        Response<Void> res = Response.newInstance();
        if(configHotline==null || configHotline.getId()==null){
            //没有记录
            return new Response<Void>(-1,"请输入热线相关信息");
        }
        try{
            StringBuffer phone = new StringBuffer();
            if(null == phones || phones.length<1){
                return new Response<Void>(-1,"请填写电话号码");
            }else{
                for (String str : phones) {
                    if(StringUtils.isNotBlank(str)){
                        phone.append(str+",");
                    }
                }
                if(phone.length()>0){
                    phone.delete(phone.length()-1, phone.length());
                }else{
                    return new Response<Void>(-1,"请填写电话号码");
                }
            }
            
            ConfigHotline sourceConfigHotline = configHotlineService.get(configHotline.getId()).getData();
            if(sourceConfigHotline==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            configHotline.setAppId(null==configHotline.getAppId()?sourceConfigHotline.getAppId():configHotline.getAppId());
            configHotline.setHotlineTypeId(null==configHotline.getHotlineTypeId()?sourceConfigHotline.getHotlineTypeId():configHotline.getHotlineTypeId());
            configHotline.setPhone(phone.toString());
            configHotline.setStatus(sourceConfigHotline.getStatus());
            //创建时间
            configHotline.setCreateTime(sourceConfigHotline.getCreateTime());
            //创建人ID
            configHotline.setCreatorId(sourceConfigHotline.getCreatorId());
            //创建人
            configHotline.setCreator(sourceConfigHotline.getCreator());
            SessionUser user = SessionFace.getSessionUser(request);
            if(null!=user){
                //更新时间
                configHotline.setUpdateTime(new Date());
                //更新人ID
                configHotline.setUpdatorId(user.getUserId());
                //更新人
                configHotline.setUpdator(user.getNickname());
                //res = configHotlineService.save(configHotline);
            }else{
                return new Response<Void>(-1,"请先登录"); 
            } 
            //保存
            res= configHotlineService.save(configHotline);
            if(res.getCode() < 0){
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
            }
        }catch (Exception ex){
            logger.error("Save Method (Update) ConfigHotline Error : " + configHotline.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="config_hotline_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ConfigHotlineDto configHotline = configHotlineService.getById(id).getData();
        if(configHotline==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", configHotline);
        return getReturnUrl(request,map,"tiles.module.config.config_hotline_view");
    }

    //删除
    @RequestMapping(value ="config_hotline_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            Response<Void> res = configHotlineService.deleteLogic(user.getNickname(),user.getUserId(),id);
            return res;
        }else{
            return new Response<Void>(-1,"请先登录"); 
        }
    }

	//批量删除
    @RequestMapping(value ="config_hotline_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            Response<Void> res = configHotlineService.deleteLogic(user.getNickname(),user.getUserId(),ids);
            return res;
        }else{
            return new Response<Void>(-1,"请先登录"); 
        }
    }
    
    //修改排序号
    @RequestMapping(value ="/common/update_sort_no")
    @ResponseBody
    public Response<Void> updateSortNo(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,Integer sortNo){
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        Response<Void> res = configHotlineService.updateSortNo(sortNo,sessionUser.getNickname(),sessionUser.getUserId(),id);
        return res;
    }
}
