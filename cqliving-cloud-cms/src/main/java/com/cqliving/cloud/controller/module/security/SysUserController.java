package com.cqliving.cloud.controller.module.security;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.cqliving.cloud.common.Encryption;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.security.domain.SysRole;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.cloud.online.security.manager.SysRoleService;
import com.cqliving.cloud.security.annotation.ResourceType;
import com.cqliving.cloud.security.annotation.SystemResource;
import com.cqliving.cloud.security.service.SysUserService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.JsonMapper;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.utils.RequestUtil;



@Controller
@RequestMapping(value = "/module/security")
public class SysUserController extends CommonController {
    
	@Autowired
    private SysUserService sysUserService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private SysRoleService sysRoleService;
	
    private void initData(HttpServletRequest request){
    	
    	request.setAttribute("allApps",appInfoService.getAll().getData());
    	request.setAttribute("allUsertypes",SysUser.allUsertypes);
    	request.setAttribute("allStatuss",SysUser.allStatuss);
    }
    
    
    //列表
    @RequestMapping(value ="sys_user_list")
    @SystemResource(title="用户列表",resString="/module/security/sys_user_list.html",permissionValue="/module/security/sys_user_list.html")
    public String list(HttpServletRequest request, Map<String, Object> map,PageInfo<SysUser> pageInfo) {

    	String p = request.getParameter("p");
    	Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	
    	 Object appId = searchMap.get("EQ_appId");
         
         if(null == appId || StringUtil.isEmpty(appId.toString())){
         	searchMap.put("EQ_appId",sessionUser.getAppId());
         }
    	
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        map.put("pageInfo", sysUserService.query(pageInfo, searchMap, sortMap));
        //初始化数据
        initData(request);
        
        if(!StringUtil.isEmpty(p)){
        	return "/module/security/sys_user_list_page";
        }
        
        return "tiles.module.security.sys_user_list";
    }

    //增加-查看
    @RequestMapping(value ="sys_user_add", method = RequestMethod.GET)
    @SystemResource(title="增加用户",restype=ResourceType.BUTTON,resString="/module/security/sys_user_add.html",permissionValue="/module/security/sys_user_add.html",parentValue="/module/security/sys_user_list.html",sortNum=1)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	List<SysRole> roles = sysRoleService.findByAppId(SessionFace.getSessionUser(request).getAppId(),null);
    	map.put("roles", roles);
    	//初始化数据
    	initData(request);
    	
    	request.setAttribute("now",Dates.now());
    	
        return "tiles.module.security.sys_user_detail";
    }


    //增加-保存
    @RequestMapping(value ="sys_user_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,
                                SysUser sysUser){
        try{
            if (StringUtils.isNotBlank(sysUser.getUsername())) {
                Boolean validateUserName = Encryption.validateUserName(sysUser.getUsername());
                if(!validateUserName){
                    return new Response<Void>(ErrorCodes.FAILURE,"管理账号格式不正确，只能数字字母，最少6位");
                }
            }else{
                return new Response<Void>(ErrorCodes.FAILURE,"请填写用户名");
            }
        	boolean checkExists = sysUserService.checkUsername(sysUser.getUsername());
        	if (checkExists){
        		//已有同名记录存在
        		return new Response<Void>(ErrorCodes.FAILURE,new RequestContext(request).getMessage(Constant.I18nMessage.CHECK_RECORD_EXISTS));
        	}
			if (StringUtils.isNotBlank(sysUser.getPassword())) {
			    Encryption.entryptPassword(sysUser);
			}else{
				return new Response<Void>(ErrorCodes.FAILURE,new RequestContext(request).getMessage(Constant.I18nMessage.NEED_ENTER_PASSWORD));
			}
        	//用户所属角色
        	sysUser.setRole(this.loadRoleFormRequest(request));
        	
            //主键ID
            sysUser.setId(null);
            //创建时间
            sysUser.setCreateDate(new Date());
            //最后登录时间
            sysUser.setLastLoginDate(new Date());
            //最后登录IP
            sysUser.setLastLoginIp(RequestUtil.getRequestIpAddr(request));
            
            SessionUser sessionUser = SessionFace.getSessionUser(request);
            
            if(null != sessionUser.getAppId()){
            	sysUser.setUsertype(SysUser.USERTYPE2);
            	sysUser.setAppId(sessionUser.getAppId());
            }
            
            if(StringUtil.isEmpty(sysUser.getNickname()))
            	sysUser.setNickname(sysUser.getUsername());
            
            sysUserService.createSysUser(sysUser);
        }catch (Exception ex){
            logger.error("Save Method (inster) SysUser Error : " + sysUser.toString(), ex);
            //增加失败
            return new Response<Void>(ErrorCodes.FAILURE,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new Response<Void>(ErrorCodes.SUCCESS,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
    }

    //修改-查看
    @RequestMapping(value ="sys_user_update", method = RequestMethod.GET)
    @SystemResource(title="修改用户",restype=ResourceType.BUTTON,resString="/module/security/sys_user_update.html",permissionValue="/module/security/sys_user_update.html",parentValue="/module/security/sys_user_list.html",sortNum=2)
    public String update(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        SysUser sysUser = sysUserService.get(id);
        if(sysUser==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysUser);
    	map.put("userRoles", new JsonMapper().toJson(sysUser.getRole()));
        List<SysRole> roles = sysRoleService.findByAppId(SessionFace.getSessionUser(request).getAppId(),null);
    	map.put("roles", roles);
    	//初始化数据
    	initData(request);
    	
    	return "tiles.module.security.sys_user_detail";
    }

    //修改-保存
    @RequestMapping(value ="sys_user_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,
                                SysUser sysUser){
        if(sysUser==null ||
                sysUser.getId()==null){
            //没有记录
            return new Response<Void>(ErrorCodes.FAILURE,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            SysUser sourceSysUser = sysUserService.get(sysUser.getId());
            if(sourceSysUser==null){
                //没有记录
                return new Response<Void>(ErrorCodes.FAILURE,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
        	//用户所属角色
            sourceSysUser.setRole(this.loadRoleFormRequest(request));
            //操作员姓名
            
            if(!StringUtil.isEmpty(sysUser.getNickname()))
            	sourceSysUser.setNickname(sysUser.getNickname());
            //电子邮件
            sourceSysUser.setEmail(sysUser.getEmail());
            //状态
            sourceSysUser.setStatus(sysUser.getStatus());
            //描述
            sourceSysUser.setDescn(sysUser.getDescn());
            //过期时间
            sourceSysUser.setExpiredDate(sysUser.getExpiredDate());
            //解锁时间
            sourceSysUser.setUnlockDate(sysUser.getUnlockDate());
            sourceSysUser.setQqCode(sysUser.getQqCode());
            sourceSysUser.setAppId(sysUser.getAppId());
            sourceSysUser.setMobile(sysUser.getMobile());
            sourceSysUser.setPosition(sysUser.getPosition());
            sourceSysUser.setUsertype(sysUser.getUsertype());
            
            SessionUser su = SessionFace.getSessionUser(request);
            if(null != su.getAppId()){
            	sysUser.setUsertype(SysUser.USERTYPE2);
            	sysUser.setAppId(su.getAppId());
            }
            
            sysUserService.createSysUser(sourceSysUser);
            SessionUser sessionUser = SessionFace.getOnlineSessionUser(sourceSysUser.getUsername());
            if(sessionUser!=null){
                sessionUser.update(sourceSysUser);
            }
            sysUser = sourceSysUser;
        }catch (Exception ex){
            logger.error("Save Method (Update) SysUser Error : " + sysUser.toString(), ex);
            //修改失败
            return new Response<Void>(ErrorCodes.FAILURE,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new Response<Void>(ErrorCodes.SUCCESS,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
    }

    //查看
    @RequestMapping(value ="sys_user_view")
    @SystemResource(title="查看用户",restype=ResourceType.BUTTON,resString="/module/security/sys_user_view.html",permissionValue="/module/security/sys_user_view.html",parentValue="/module/security/sys_user_list.html",sortNum=3)
    public String show(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        SysUser sysUser = sysUserService.get(id);
        if(sysUser==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysUser);
        initData(request);
        return "tiles.module.security.sys_user_view";
    }



    //修改状态
    @RequestMapping(value ="sys_user_status")
    public String status(HttpServletRequest request, Map<String, Object> map,
                                @RequestParam(value = "id") Long id,
                                @RequestParam(value="status", required=true) Integer status){

        try{
            if(0==sysUserService.updateStatusById(id, status)){
                //没有记录
                return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
            }
        }catch (Exception ex){
            logger.error("Del Method (Del) SysUser Error : " + id, ex);
            //删除失败提示
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.DEL_FAILURE), map);
        }
        //返回页
        String ref = request.getHeader("referer");
        if(StringUtils.isBlank(ref)){
            ref = "/module/security/sys_user_list.html";
        }
        //操作提示
        return super.operSuccess(new RequestContext(request).getMessage(Constant.I18nMessage.DEL_SUCCESS), ref, map);
    }


    //重置密码-查看
    @RequestMapping(value ="sys_user_pass", method = RequestMethod.GET)
    public String resetPass(HttpServletRequest request, Map<String, Object> map, 
    		@RequestParam(value = "id", required=true) Long id){
        SysUser sysUser = sysUserService.get(id);
        if(sysUser==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysUser);
    	
        return "tiles.module.security.sys_user_pass";
    }


    //重置密码-修改
    @RequestMapping(value ="sys_user_pass", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> resetPassSave(HttpServletRequest request, Map<String, Object> map,
    		@RequestParam(value = "id", required=true) Long id){
	  	String password = request.getParameter("newpassword");
	  	String password2 = request.getParameter("newpassword2");
	  	if (password == null || password2 == null || !password.equals(password2)) {
	  		return new Response<Void>(ErrorCodes.FAILURE,new RequestContext(request).getMessage(Constant.I18nMessage.USER_PASS_FORMAT_ERR));
	  	}else{
	  		
	        SysUser sysUser = sysUserService.get(id);
	        if(sysUser==null){
	            //没有记录
	            return new Response<Void>(ErrorCodes.FAILURE,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
	        }
	        sysUser.setPassword(password);
	        Encryption.entryptPassword(sysUser);
	        sysUserService.save(sysUser);
	        
	        return new Response<Void>(ErrorCodes.SUCCESS,sysUser.getUsername()+" "+new RequestContext(request).getMessage(Constant.I18nMessage.USER_PASS_UPDATE_SUCCESS));
	  	}
    }

    //修改密码-查看
    @RequestMapping(value ="change_pass", method = RequestMethod.GET)
    @SystemResource(title="修改密码",restype=ResourceType.BUTTON,resString="/module/security/sys_user_pass.html",permissionValue="/module/security/sys_user_pass.html",parentValue="/module/security/sys_user_list.html",sortNum=4)
    public String changePass(HttpServletRequest request, Map<String, Object> map){
        SysUser sysUser = sysUserService.get(1L);
        if(sysUser==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysUser);
    	
        return "tiles.module.security.change_pass";
    }
    
    //修改密码-保存
    @RequestMapping(value ="change_pass", method = RequestMethod.POST)
    public String changePassSave(HttpServletRequest request, Map<String, Object> map){
    	String orginalPassword = request.getParameter("oldpassword");
    	String password = request.getParameter("newpassword");
	  	String password2 = request.getParameter("newpassword2");
	  	if (StringUtils.isBlank(password) || StringUtils.isBlank(password2) || !password.equals(password2)) {
	  		return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.USER_PASS_FORMAT_ERR), map);
	  	}else{
            SysUser sysUser = sysUserService.get(SessionFace.getSessionUser(request).getUserId());
	        if(sysUser==null){
	            //没有记录
	            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
	        }else{
	        	boolean result = Encryption.validatePassword(sysUser, orginalPassword);
	        	if (result){
	    	        sysUser.setPassword(password);
	    	        Encryption.entryptPassword(sysUser);
	    	        sysUserService.save(sysUser);
	    			String location = "sys_user_list.html";
	    	        return super.operSuccess(sysUser.getUsername()+" "+new RequestContext(request).getMessage(Constant.I18nMessage.USER_PASS_UPDATE_SUCCESS),location, map);
	        	}else{
	        		return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.USER_PASS_ORIGINAL_CHECK_ERR), map);
	        	}
	        }
	  	}
    }  
    
    /**
	 * 
	 * Title:检查是否用户已经存在
	 * <p>Description:</p>
	 * @param request
	 * @return
	 */
	@RequestMapping("common/check_name")
	@ResponseBody
	public Response<Void> checkWaitress(HttpServletRequest request)
	{
		String username = request.getParameter("username");
		Response<Void> result = new Response<Void>();
		Boolean validateUserName = Encryption.validateUserName(username);
        if(!validateUserName){
            result.setMessage("管理账号格式不正确，只能数字字母，最少6位");
            result.setCode(ErrorCodes.FAILURE);
            return result;
        }
        boolean status = sysUserService.checkUsername(username);
		if(status)
		{
			result.setMessage("用户名已存在");
			result.setCode(ErrorCodes.FAILURE);
		}else{
			result.setMessage("用户名可用");
			result.setCode(ErrorCodes.SUCCESS);
		}
		return result;
	}

	private Set<SysRole> loadRoleFormRequest(HttpServletRequest request) {
		
		String[] roleIds = request.getParameterValues("roleIds");
		
		if(StringUtil.isEmpty(roleIds))return null;
		
		Set<SysRole> roles = new HashSet<SysRole>();
		
		for(String str : roleIds){
			Long roleId = Long.valueOf(str);
			roles.add(sysRoleService.get(roleId));
		}

		return roles;
	}
	
	/**
	 * Title:登录用户修改自己的密码
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月16日
	 * @return
	 */
	@RequestMapping(value = {"common/modify_my_pass"}, method=RequestMethod.GET)
	public String modifyMyPass(HttpServletRequest request){
		return "tiles.module.security.modify_my_pass";
	}
	
	/**
	 * Title:登录用户修改自己的密码
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月16日
	 * @param request
	 * @param oldPassword
	 * @param password
	 * @param rePassword
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"common/modify_my_pass"}, method=RequestMethod.POST)
	public Response<Void> postModifyMyPass(HttpServletRequest request, @RequestParam String oldPassword, @RequestParam String password, @RequestParam String rePassword){
		Response<Void> res = Response.newInstance();
		try {
			if(StringUtils.isBlank(password) || !password.equals(rePassword)){
				res.setCode(ErrorCodes.FAILURE);
				res.setMessage("两次密码输入不一致");
				return res;
			}
			SessionUser sessionUser = SessionFace.getSessionUser(request);
			if(sessionUser == null){
				res.setCode(ErrorCodes.FAILURE);
				res.setMessage("用户为登录");
				return res;
			}
			SysUser sysUser = sysUserService.get(sessionUser.getUserId());
			if(sysUser == null){
				res.setCode(ErrorCodes.FAILURE);
				res.setMessage("用户未登录");
				return res;
			}
			if(!Encryption.validatePassword(sysUser, oldPassword)){
				res.setCode(ErrorCodes.FAILURE);
				res.setMessage("原密码错误");
				return res;
			}
			sysUser.setPassword(password);
			Encryption.entryptPassword(sysUser);
			sysUserService.save(sysUser);
		} catch (Exception e) {
			logger.error("用户修改自己的密码失败：" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("修改失败");
		}
		return res;
	}
	
}
