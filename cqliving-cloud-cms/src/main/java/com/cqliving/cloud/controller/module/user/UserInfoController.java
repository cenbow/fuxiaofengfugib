package com.cqliving.cloud.controller.module.user;

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
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.service.UserInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/userInfo")
public class UserInfoController extends CommonController {

    @Autowired
    private UserInfoService userInfoService;

    //列表
    @RequestMapping(value ="user_info_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
		
        PageInfo<UserInfo> pageInfo = getPageInfo(request);
        map.put("pageInfo", userInfoService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/userInfo/user_info_list_page";
        }else{
        	return "tiles.module.user.user_info_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="user_info_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.user.user_info_detail";
    }


    //增加-保存
    @RequestMapping(value ="user_info_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,UserInfo userInfo){
        //ID,同URSERID
        userInfo.setId(null);
        Response<Void> res = userInfoService.save(userInfo);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="user_info_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserInfo userInfo = userInfoService.get(id).getData();
        if(userInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userInfo);
        return "tiles.module.user.user_info_detail";
    }

    //修改-保存
    @RequestMapping(value ="user_info_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,UserInfo userInfo){
        Response<Void> res = Response.newInstance();
        if(userInfo==null || userInfo.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            UserInfo sourceUserInfo = userInfoService.get(userInfo.getId()).getData();
            if(sourceUserInfo==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //姓名
            sourceUserInfo.setName(userInfo.getName());
            //头像
            sourceUserInfo.setImgUrl(userInfo.getImgUrl());
            //性别
            sourceUserInfo.setSex(userInfo.getSex());
            //个性签名
            sourceUserInfo.setSpeciality(userInfo.getSpeciality());
            //最后修改时间
            sourceUserInfo.setUpdateTime(userInfo.getUpdateTime());
            res= userInfoService.save(sourceUserInfo);
            userInfo = sourceUserInfo;
        }catch (Exception ex){
            logger.error("Save Method (Update) UserInfo Error : " + userInfo.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="user_info_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserInfo userInfo = userInfoService.get(id).getData();
        if(userInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userInfo);
        return "tiles.module.user.user_info_view";
    }

    //删除
    @RequestMapping(value ="user_info_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = userInfoService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="user_info_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = userInfoService.delete(ids);
        return res;
    }
}
