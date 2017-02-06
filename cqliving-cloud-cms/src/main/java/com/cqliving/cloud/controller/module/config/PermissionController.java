package com.cqliving.cloud.controller.module.config;

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
import com.cqliving.cloud.online.config.domain.AppPermission;
import com.cqliving.cloud.online.config.domain.Permission;
import com.cqliving.cloud.online.config.service.PermissionService;
import com.cqliving.cloud.online.security.dto.SysResTypeDto;
import com.cqliving.cloud.online.security.dto.TypesDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/config")
public class PermissionController extends CommonController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private AppInfoService appInfoService;

    //列表
    @RequestMapping(value ="permission_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
        SessionUser user = SessionFace.getSessionUser(request);		
        PageInfo<AppInfoDto> pageInfo = getPageInfo(request);
        map.put("pageInfo", appInfoService.queryPageByUser(user.getUsertype(),user.getUserId(),pageInfo, searchMap, sortMap).getData());
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/config/permission_list_page";
        }else{
        	return "tiles.module.config.permission_list";
        }
    }

    //修改-查看
    @RequestMapping(value ="permission_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        //appId
        map.put("id", id);
        //
        List<SysResTypeDto> types = permissionService.findAllPermission(id).getData();
        map.put("types", types);
        map.put("ISPERMISSION1", AppPermission.ISPERMISSION1);
        map.put("ISLOGIN1", AppPermission.ISLOGIN1);
        map.put("ISSIGN1", AppPermission.ISSIGN1);
        map.put("ISSESSIONID1", AppPermission.ISSESSIONID1);
        map.put("ISREQUESTTIMES1", AppPermission.ISREQUESTTIMES1);
        return getReturnUrl(request,map,"tiles.module.config.permission_detail");
    }

    //修改-保存
    @RequestMapping(value ="permission_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,Long id,TypesDto types){
        Response<Void> res = Response.newInstance();
        if(id==null || types==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            SessionUser user = SessionFace.getSessionUser(request);
            res = permissionService.saveAppPermission(id, types,user.getUserId(),user.getNickname());
        }catch (Exception ex){
            logger.error("Save Method (Update) Permission Error : " + types.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        if(res.getCode()>-1){
            res.setMessage("保存成功！");
        }
        return res;
    }

    //查看
    @RequestMapping(value ="permission_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Permission permission = permissionService.get(id).getData();
        if(permission==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", permission);
        return getReturnUrl(request,map,"tiles.module.config.permission_view");
    }

    //删除
    @RequestMapping(value ="permission_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = permissionService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="permission_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = permissionService.delete(ids);
        return res;
    }
}
