package com.cqliving.cloud.controller.module.security;

import java.util.HashMap;
import java.util.HashSet;
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
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.security.domain.SysResource;
import com.cqliving.cloud.online.security.domain.SysRole;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.cloud.online.security.dto.SysResourceDto;
import com.cqliving.cloud.online.security.manager.SysRoleService;
import com.cqliving.cloud.online.security.service.SysResTypeService;
import com.cqliving.cloud.security.cache.SecurityCache;
import com.cqliving.cloud.security.service.SysResourceService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.mapper.JsonMapper;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.StringUtil;

@Controller
@RequestMapping(value = "/module/security")
public class SysRoleController extends CommonController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private SecurityCache securityCache;
    @Autowired
    SysResTypeService sysResTypeService;
    
    //列表
    @RequestMapping(value ="sys_role_list")
    public String list(HttpServletRequest request, Map<String, Object> map,PageInfo<SysRole> pageInfo,
    		@RequestParam(value = "p", required = false) String isAjaxPage) {
    	
    	Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        
        Object appId = searchMap.get("EQ_appId");
        
        if(null == appId || StringUtil.isEmpty(appId.toString())){
        	searchMap.put("EQ_appId",sessionUser.getAppId());
        }
       
        map.put("pageInfo", sysRoleService.query(pageInfo, searchMap, sortMap));
        map.put("allApps",appInfoService.getAll().getData());
        
        //查询按钮和点击页面是ajax操作
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/security/sys_role_list_page";
        }else{
        	return "tiles.module.security.sys_role_list";
        }
    }

    //增加-查看
   /* @RequestMapping(value ="sys_role_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){

    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	
    	Byte userType= sessionUser.getUsertype();
    	
    	map.put("allApps",appInfoService.getAll().getData());
    	
        Map<String, Object> searchMap = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("sortNum", true);
        searchMap.put("EQ_status",SysResource.STATUS1);
        if(null != userType && userType.byteValue() == SysUser.USERTYPE3.byteValue()){
        	searchMap.put("EQ_userId", SessionFace.getSessionUser(request).getUserId());
        }
        
        //获取资源"URL",(byte)1)
        map.put("rescJson",
                JsonMapper.nonDefaultMapper().toJson(sysResourceService.findByConditions(searchMap, sortMap)));
    	
        return "tiles.module.security.sys_role_detail";
    }*/


    //增加-保存
    @RequestMapping(value ="sys_role_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,SysRole sysRole,
    		@RequestParam(value = "resIds[]", required = false) Long[] resIds){
        try{
            //主键ID
            sysRole.setId(null);
            //拥有的资源
            sysRole.setRescs(new HashSet<SysResource>());
            if(resIds!=null) {
                for (Long resid : resIds) {
                    SysResource res = sysResourceService.get(resid);
                    sysRole.getRescs().add(res);
                }
            }
            sysRoleService.save(sysRole);
        }catch (Exception ex){
            logger.error("Save Method (inster) SysRole Error : " + sysRole.toString(), ex);
            //增加失败
            return new Response<Void>(ErrorCodes.FAILURE,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new Response<Void>(ErrorCodes.SUCCESS,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
    }

    //修改-查看
   /* @RequestMapping(value ="sys_role_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        SysRole sysRole = sysRoleService.get(id);
        if(sysRole==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysRole);
        map.put("allApps",appInfoService.getAll().getData());
        
        Map<String, Object> searchMap = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("sortNum", true);
        searchMap.put("EQ_status",SysResource.STATUS1);
        
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        
        Byte userType= sessionUser.getUsertype();
        if(null != userType && userType.byteValue() == SysUser.USERTYPE3.byteValue()){
        	searchMap.put("EQ_userId", SessionFace.getSessionUser(request).getUserId());
        }
        
        //获取资源"URL",(byte)1)
        map.put("rescJson",JsonMapper.nonDefaultMapper().toJson(sysResourceService.findByConditions(searchMap, sortMap)));
    	return "tiles.module.security.sys_role_detail";
    }*/

    //修改-保存
    @RequestMapping(value ="sys_role_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,
                                SysRole sysRole,
                                @RequestParam(value = "resIds[]", required = false) Long[] resIds){
        if(sysRole==null || sysRole.getId()==null){
            //没有记录
            return new Response<Void>(ErrorCodes.FAILURE,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            SysRole sourceSysRole = sysRoleService.get(sysRole.getId());
            if(sourceSysRole==null){
                //没有记录
                return new Response<Void>(ErrorCodes.FAILURE,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //角色名称
            sourceSysRole.setRoleName(sysRole.getRoleName());
            //描述信息
            sourceSysRole.setDescn(sysRole.getDescn());
            
            //拥有的资源
            sourceSysRole.setRescs(new HashSet<SysResource>());
            if(resIds!=null) {
                for (Long resid : resIds) {
                    SysResource res = sysResourceService.get(resid);
                    sourceSysRole.getRescs().add(res);
                }
            }
            
            sysRoleService.update(sourceSysRole);
            
            securityCache.clearAllAuthorization();
            securityCache.clearMenu(sourceSysRole.getId());
            sysRole = sourceSysRole;
        }catch (Exception ex){
            logger.error("Save Method (Update) SysRole Error : " + sysRole.toString(), ex);
            //修改失败
            return new Response<Void>(ErrorCodes.FAILURE,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new Response<Void>(ErrorCodes.SUCCESS,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
    }

    //查看
    @RequestMapping(value ="sys_role_view")
    public String show(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        SysRole sysRole = sysRoleService.get(id);
        if(sysRole==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        if(null != sysRole.getAppId()){
        	map.put("app",appInfoService.get(sysRole.getAppId()).getData());
        }
        map.put("item", sysRole);
        return "tiles.module.security.sys_role_view";
    }

    @RequestMapping(value="common/sysrole")
    @ResponseBody
    public Response<List<SysRole>> findByAppId(HttpServletRequest request,Long appId,Byte usertype){
    	Response<List<SysRole>> rp = Response.newInstance();
    	rp.setData(sysRoleService.findByAppId(appId,usertype));
    	return rp;
    }
    
    @RequestMapping(value="sys_role_delete")
    @ResponseBody
    public Response<Void> deleteRole(HttpServletRequest request,@RequestParam Long roleId){
    	
    	return sysRoleService.deleteRole(roleId);
    }
    
    //增加-查看
    @RequestMapping(value ="sys_role_add", method = RequestMethod.GET)
    public String new_add(HttpServletRequest request, Map<String, Object> map){
    	//初始化数据
    	initData(request);
    	
        return "tiles.module.security.new_sys_role_detail";
    }
    
    @RequestMapping(value ="common/new_find_sys_resc")
    @ResponseBody
    public Map<Long,List<SysResourceDto>> findResourse(HttpServletRequest request, Map<String, Object> map,Long sysTypeId){
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Byte userType= sessionUser.getUsertype();
    	Map<String, Object> searchMap = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("sortNum", true);
        searchMap.put("EQ_status",SysResource.STATUS1);
        //searchMap.put("EQ_sysResTypeId", sysTypeId);
        if(null != userType && userType.byteValue() == SysUser.USERTYPE3.byteValue()){
        	searchMap.put("EQ_userId", SessionFace.getSessionUser(request).getUserId());
        }
        //获取资源"URL",(byte)1)
        return sysResourceService.findByGroupResType(searchMap, sortMap);
    }
    
  //修改-查看
    @RequestMapping(value ="sys_role_update", method = RequestMethod.GET)
    public String new_update(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        SysRole sysRole = sysRoleService.get(id);
        if(sysRole==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysRole);
        map.put("roleRescs",JsonMapper.nonDefaultMapper().toJson(sysRole.getRescs()));
        //初始化数据
        initData(request);
       
    	return "tiles.module.security.new_sys_role_detail";
    }
    
    private void  initData(HttpServletRequest request){
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Byte userType = sessionUser.getUsertype();
    	if(null != userType && userType.byteValue() == SysUser.USERTYPE3.byteValue()){
    		request.setAttribute("sysResTypes",sysResTypeService.findExistsResByUserId(sessionUser.getUserId()).getData());
        }else{
        	request.setAttribute("sysResTypes",sysResTypeService.findExistsRes().getData());
        }
    	request.setAttribute("allApps",appInfoService.getAll().getData());
    }
}
