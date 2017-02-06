package com.cqliving.cloud.controller.module.user;

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

import com.cqliving.cloud.common.AppInfoUtil;
import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.dto.UserDto;
import com.cqliving.cloud.online.account.service.UserAccountService;
import com.cqliving.cloud.online.account.service.UserInfoService;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.encrypt.Crypt;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/userAccount")
public class UserAccountController extends CommonController {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private UserInfoService userInfoService;

    //列表
    @RequestMapping(value ="user_account_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
		
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
        
        PageInfo<UserDto> pageInfo = getPageInfo(request);
//        searchMap.put("NOTEQ_status", UserAccount.STATUS1);//排除逻辑删除状态
        
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("registTime", false);
        sortMap.put("id", false);
        
        map.put("pageInfo", userAccountService.queryPage(pageInfo, searchMap, sortMap).getData());
        map.put("allStatuss", UserAccount.allStatuss);
        map.put("allTypes", UserAccount.allTypes);
        map.put("TYPE2", UserAccount.TYPE2);//游客
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/user/user_account_list_page";
        }else{
        	return "tiles.module.user.user_account_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="user_account_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        //处理app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        return "tiles.module.user.user_account_detail";
    }


    //增加-保存
    @RequestMapping(value ="user_account_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,UserAccount userAccount,UserInfo userInfo){
        userAccount.setId(null);
        userAccount.setAppId(null==userAccount.getAppId()?SessionFace.getSessionUser(request).getAppId():userAccount.getAppId());
        userAccount.setSource("CMS");
        userAccount.setPassword(Crypt.MD5(userAccount.getPassword()).toLowerCase());
        userAccount.setType(UserAccount.TYPE1);
        userAccount.setStatus(UserAccount.STATUS0);
        userAccount.setRegistTime(new Date());
        userInfo.setUpdateTime(new Date());
        userInfo.setId(null);
        userInfo.setSex(UserInfo.SEX0);
        //查询app 
        Response<AppInfo> appInfoResponse = appInfoService.get(userAccount.getAppId());
        //生成匿名名称
        userInfo.setAnonymousName(AppInfoUtil.getRandomNickname(appInfoResponse.getData().getNamePrefix()));
        Response<Void> res = userAccountService.saveUser(userAccount,userInfo);
        if(res.getCode() < 0){
            return new Response<Void>(-1,StringUtils.isNotBlank(res.getMessage())?res.getMessage():(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE)));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="user_account_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,Long appId){
        SessionUser sysUser = SessionFace.getSessionUser(request);
        appId = appId==null?sysUser.getAppId():appId;
        UserDto user = userAccountService.getById(id,appId).getData();
        if(user==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", user);
        //处理app下拉框
        List<AppInfoDto> appList = appInfoService.getBySysUser(sysUser.getUsertype(), sysUser.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        return "tiles.module.user.user_account_detail";
    }

    //修改-保存
    @RequestMapping(value ="user_account_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,UserAccount userAccount,UserInfo userInfo){
        Response<Void> res = Response.newInstance();
        if(userAccount==null || userAccount.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            UserAccount sourceUserAccount = userAccountService.get(userAccount.getId()).getData();
            if(sourceUserAccount==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            if(StringUtils.isNotBlank(userAccount.getPassword())){
                userAccount.setPassword(Crypt.MD5(userAccount.getPassword()).toLowerCase());
            }else{
                userAccount.setPassword(sourceUserAccount.getPassword());
            }
            userAccount.setAppId(null==userAccount.getAppId()?sourceUserAccount.getAppId():userAccount.getAppId());
            userAccount.setSource(sourceUserAccount.getSource());
            userAccount.setType(sourceUserAccount.getType());
            userAccount.setStatus(sourceUserAccount.getStatus());
            userAccount.setRegistTime(sourceUserAccount.getRegistTime());
            
            //更改匿名名称
            //appId没有修改就不改匿名名称
            if(sourceUserAccount.getAppId().equals(userAccount.getAppId())){
                UserInfo user = userInfoService.get(sourceUserAccount.getId()).getData();
                userInfo.setAnonymousName(user==null?"":user.getAnonymousName());
            }else{
                //查询app 
                Response<AppInfo> appInfoResponse = appInfoService.get(userAccount.getAppId());
                //生成匿名名称
                userInfo.setAnonymousName(AppInfoUtil.getRandomNickname(appInfoResponse.getData()==null?"":appInfoResponse.getData().getNamePrefix()));
            }
            userInfo.setId(sourceUserAccount.getId());
            userInfo.setUpdateTime(new Date());
            userInfo.setSex(UserInfo.SEX0);
            res = userAccountService.saveUser(userAccount,userInfo);
        }catch (Exception ex){
            logger.error("Save Method (Update) UserAccount Error : " + userAccount.toString()+";"+userInfo.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        if(res.getCode() < 0){
            return new Response<Void>(-1,StringUtils.isNotBlank(res.getMessage())?res.getMessage():(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE)));
        }
        res.setMessage("修改成功!");
        return res;
    }
    
    //重置密码-查看
    @RequestMapping(value ="reset_pwd", method = RequestMethod.GET)
    public String resetPwd(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserAccount user = userAccountService.get(id).getData();
        if(user==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", user);
        return "tiles.module.user.reset_pwd";
    }
    
    //重置密码-保存
    @RequestMapping(value ="reset_pwd", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postResetPwd(HttpServletRequest request, Map<String, Object> map,Long id ,String password, String confirmPwd){
        Response<Void> res = Response.newInstance();
        if(id==null || password==null || confirmPwd == null){
            //没有记录
            return new Response<Void>(-1, "请填写完整密码");
        }
        try{
            if(!password.equals(confirmPwd)){
                //没有记录
                return new Response<Void>(-1, "密码不一致");
            }
            res = userAccountService.resetPwd(id,Crypt.MD5(password).toLowerCase());
        }catch (Exception ex){
            logger.error("Save Method (Update) UserAccount Error : id is" + id +";password is "+password+";confirmPwd is " + confirmPwd, ex);
            //修改失败
            return new Response<Void>(-1,"重置密码失败");
        }
        res.setMessage("重置密码成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="user_account_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,Long appId){
        appId = appId==null?SessionFace.getSessionUser(request).getAppId():appId;
        UserDto user = userAccountService.getById(id,appId).getData();
        if(user==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", user);
        return "tiles.module.user.user_account_view";
    }

    //删除
    @RequestMapping(value ="user_account_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = userAccountService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="user_account_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = userAccountService.deleteLogic(ids);
        return res;
    }
    //启用
    @RequestMapping(value ="start_using", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> startUsing(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = userAccountService.updateStatus(UserAccount.STATUS0,id);
        return res;
    }
    
    //批量启用
    @RequestMapping(value ="start_using_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> startUsingBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = userAccountService.updateStatus(UserAccount.STATUS0,ids);
        return res;
    }
}
