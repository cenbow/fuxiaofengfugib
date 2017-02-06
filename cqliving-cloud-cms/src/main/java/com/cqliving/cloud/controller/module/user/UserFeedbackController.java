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

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.account.domain.UserFeedback;
import com.cqliving.cloud.online.account.dto.UserFeedbackDto;
import com.cqliving.cloud.online.account.service.UserFeedbackService;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/userFeedback")
public class UserFeedbackController extends CommonController {

    @Autowired
    private UserFeedbackService userFeedbackService;
    @Autowired
    private AppInfoService appInfoService;
    //列表
    @RequestMapping(value ="user_feedback_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        // 待处理的放在前面
        sortMap.put("status", true);
        sortMap.put("create_time", false);
        sortMap.put("id", false);
		
        PageInfo<UserFeedbackDto> pageInfo = getPageInfo(request);
        
        searchMap.put("NOTEQ_status", UserFeedback.STATUS99);//排除逻辑删除状态
        
        String searchAppid = (String)searchMap.get("EQ_appId");
        if(StringUtils.isBlank(searchAppid)){
            //处理APP下拉框
            SessionUser user = SessionFace.getSessionUser(request);
            List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
            if(null!=appList && appList.size()>1){
                map.put("appList", appList);
            }
            userDate(user, appList, searchMap);
        }
        
        map.put("pageInfo", userFeedbackService.queryByPage(pageInfo, searchMap, sortMap).getData());
        
        //状态
        map.put("allStatuss", UserFeedback.allStatuss);
        //删除
        map.put("STATUS99", UserFeedback.STATUS99);
        //删除
        map.put("STATUS3", UserFeedback.STATUS3);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/user/user_feedback_list_page";
        }else{
        	return "tiles.module.user.user_feedback_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="user_feedback_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.user.user_feedback_detail";
    }


    //增加-保存
    @RequestMapping(value ="user_feedback_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,UserFeedback userFeedback){
        //ID
        userFeedback.setId(null);
            //回复时间
        userFeedback.setReplyTime(null);
        Response<Void> res = userFeedbackService.save(userFeedback);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //回复-查看
    @RequestMapping(value ="reply", method = RequestMethod.GET)
    public String reply(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserFeedback userFeedback = userFeedbackService.get(id).getData();
        if(userFeedback==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userFeedback);
//        return "tiles.module.user.user_feedback_detail";
        return getReturnUrl(request,map,"tiles.module.user.user_feedback_detail");
    }

    //回复-保存
    @RequestMapping(value ="reply", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postReply(HttpServletRequest request, Map<String, Object> map,UserFeedback userFeedback){
        Response<Void> res = Response.newInstance();
        if(userFeedback==null || userFeedback.getId()==null || StringUtils.isBlank(userFeedback.getReplyContent())){
            //没有记录
            return new Response<Void>(-1,"请填写回复内容!");
        }
        try{
            UserFeedback sourceUserFeedback = userFeedbackService.get(userFeedback.getId()).getData();
            if(sourceUserFeedback==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            //状态
            userFeedback.setStatus(UserFeedback.STATUS3);
            SessionUser user = SessionFace.getSessionUser(request);
            //审核人ID
            userFeedback.setAuditingId(user.getUserId());
            //审核人姓名
            userFeedback.setAuditingtor(user.getNickname());
            //审核时间
            userFeedback.setAuditingTime(new Date());
            res= userFeedbackService.reply(userFeedback);
        }catch (Exception ex){
            logger.error("回复失败 : " + userFeedback.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("回复成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="user_feedback_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserFeedbackDto userFeedback = userFeedbackService.getById(id).getData();
        if(userFeedback==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userFeedback);
        //状态
        map.put("allStatuss", UserFeedback.allStatuss);
//        return "tiles.module.user.user_feedback_view";
        return getReturnUrl(request,map,"tiles.module.user.user_feedback_view");
    }

    //删除
    @RequestMapping(value ="user_feedback_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = userFeedbackService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="user_feedback_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = userFeedbackService.deleteLogic(ids);
        return res;
    }
}
