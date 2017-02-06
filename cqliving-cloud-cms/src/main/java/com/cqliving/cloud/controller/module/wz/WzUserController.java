package com.cqliving.cloud.controller.module.wz;

import java.util.Date;
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

import com.cqliving.cloud.common.CommonSysRoleEnum;
import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.Encryption;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.UploadController;
import com.cqliving.cloud.online.security.domain.SysRole;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.cloud.online.security.manager.SysRoleService;
import com.cqliving.cloud.online.wz.domain.WzUser;
import com.cqliving.cloud.online.wz.service.WzUserService;
import com.cqliving.cloud.security.service.SysUserService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@Controller
@RequestMapping(value = "/module/wz")
public class WzUserController extends UploadController {

    @Autowired
    private WzUserService wzUserService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * Title:列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月18日
     * @param request
     * @param map
     * @param isAjaxPage
     * @return
     */
    @RequestMapping(value ="wz_user_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
        SessionUser sessionUser = SessionFace.getSessionUser(request);
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
		searchMap.put("EQ_appId", sessionUser.getAppId());
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
		
        PageInfo<WzUser> pageInfo = getPageInfo(request);
        map.put("pageInfo", wzUserService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allStatuss", SysUser.allStatuss);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/wz/wz_user_list_page";
        }else{
        	return "tiles.module.wz.wz_user_list";
        }
    }

    /**
     * Title:增加-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月18日
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value ="wz_user_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.wz.wz_user_detail";
    }


    /**
     * Title:增加-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月18日
     * @param request
     * @param map
     * @param sysUser
     * @param wzUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="wz_user_add", method = RequestMethod.POST)
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,SysUser sysUser, WzUser wzUser){
    	Response<Void> rs = Response.newInstance();
        try {
            SessionUser sessionUser = SessionFace.getSessionUser(request);
            boolean checkExists = sysUserService.checkUsername(sysUser.getUsername());
            if (checkExists){
                //已有同名记录存在
            	rs.setCode(ErrorCodes.FAILURE);
            	rs.setMessage("已有同名记录存在");
            	return rs;
            }
            if (StringUtils.isNotBlank(sysUser.getPassword())) {
                Encryption.entryptPassword(sysUser);
            }else{
            	rs.setCode(ErrorCodes.FAILURE);
            	rs.setMessage("请输入密码");
            	return rs;
            }
            //用户类型为操作员
            sysUser.setUsertype(SysUser.USERTYPE2);
            
            //用户所属角色    现在是随便默认一个，以后要确定一个问政子帐号的角色后更改。
            Set<SysRole> roles = Sets.newHashSet();
            roles.add(sysRoleService.getByAppIdAndCode(sessionUser.getAppId(), CommonSysRoleEnum.WENZHENG_SUB.code));
            sysUser.setRole(roles);
            
            //创建时间
            sysUser.setCreateDate(new Date());
            sysUser.setAppId(sessionUser.getAppId());
            sysUser.setNickname(wzUser.getOrgName());
            sysUserService.createSysUser(sysUser);
            if(sysUser != null && sysUser.getId() != null){
                wzUser.setAppId(sysUser.getAppId());
                wzUser.setId(sysUser.getId());
                wzUserService.save(wzUser);
            }
        } catch (Exception e) {
            logger.error("Save Method (inster) SysUser、WzUser Error : " + sysUser.toString(), e);
            //增加失败
            rs.setCode(ErrorCodes.FAILURE);
        	rs.setMessage("新增失败");
        }
        return rs;
    }
    
    /**
     * Title:修改-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月18日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="wz_user_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        WzUser wzUser = wzUserService.get(id).getData();
        SysUser sysUser = sysUserService.get(id);
        if(wzUser==null || sysUser == null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("wzUser", wzUser);
        map.put("item", sysUser);
        return "tiles.module.wz.wz_user_detail";
    }

    /**
     * Title:修改-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月18日
     * @param request
     * @param map
     * @param wzUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="wz_user_update", method = RequestMethod.POST)
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map, SysUser sysUser, WzUser wzUser){
    	Response<Void> rs = Response.newInstance();
        if(wzUser==null || wzUser.getId()==null || sysUser==null || sysUser.getId()==null){
            //没有记录
        	rs.setCode(ErrorCodes.FAILURE);
        	rs.setMessage("数据不存在");
        	return rs;
        }
        try{
            WzUser sourceWzUser = wzUserService.get(wzUser.getId()).getData();
            SysUser sourceSysUser = sysUserService.get(sysUser.getId());
            if(sourceWzUser==null || sourceSysUser == null){
                //没有记录
            	rs.setCode(ErrorCodes.FAILURE);
            	rs.setMessage("数据不存在");
            	return rs;
            }
            
            sourceWzUser.setOrgName(wzUser.getOrgName());
            
            sourceSysUser.setEmail(sysUser.getEmail());
            sourceSysUser.setMobile(sysUser.getMobile());
            sourceSysUser.setQqCode(sysUser.getQqCode());
            sourceSysUser.setPosition(sysUser.getPosition());
            sourceSysUser.setImgUrl(sysUser.getImgUrl());
            sourceSysUser.setDescn(sysUser.getDescn());
            sourceSysUser.setStatus(sysUser.getStatus());

            sysUserService.createSysUser(sourceSysUser);
            SessionUser sessionUser = SessionFace.getOnlineSessionUser(sourceSysUser.getUsername());
            if(sessionUser!=null){
                sessionUser.update(sourceSysUser);
            }
            wzUserService.save(sourceWzUser);
        }catch (Exception ex){
            logger.error("Save Method (Update) WzUser Error : " + wzUser.toString(), ex);
            //修改失败
        	rs.setCode(ErrorCodes.FAILURE);
        	rs.setMessage("保存失败");
        	return rs;
        }
        return rs;
    }

    /**
     * Title:查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月18日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="wz_user_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        WzUser wzUser = wzUserService.get(id).getData();
        SysUser sysUser = sysUserService.get(id);
        if(wzUser==null || sysUser == null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysUser);
        map.put("wzItem", wzUser);
        
        return "tiles.module.wz.wz_user_view";
    }
    
    /**
     * Title:验证是否重复
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月17日
     * @param request
     * @param username
     * @return
     */
    @RequestMapping(value ="wz_user_check_username", method = RequestMethod.GET)
    @ResponseBody
    public Boolean checkUserName(HttpServletRequest request, @RequestParam String username){
        boolean status = sysUserService.checkUsername(username);
        return status;
    }
    
    /**
     * Title:修改密码
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月18日
     * @param request
     * @param id
     * @param password
     * @param repassword
     * @return
     */
    @RequestMapping(value ="wz_user_modify_password", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> modifyPassword(HttpServletRequest request, @RequestParam Long id, @RequestParam String password, @RequestParam String repassword){
        Response<Void> rs = Response.newInstance();
        if(StringUtils.isBlank(password) || StringUtils.isBlank(repassword) || !password.equals(repassword)){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        SysUser sysUser = sysUserService.get(id);
        sysUser.setPassword(password);
        Encryption.entryptPassword(sysUser);
        sysUserService.save(sysUser);
        return rs;
    }
    
}
