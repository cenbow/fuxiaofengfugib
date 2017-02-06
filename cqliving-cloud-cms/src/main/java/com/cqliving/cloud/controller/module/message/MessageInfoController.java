package com.cqliving.cloud.controller.module.message;

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
import com.cqliving.cloud.online.message.domain.MessageInfo;
import com.cqliving.cloud.online.message.domain.MessageReceive;
import com.cqliving.cloud.online.message.dto.MessageInfoDto;
import com.cqliving.cloud.online.message.service.MessageInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/message")
public class MessageInfoController extends CommonController {

    @Autowired
    private MessageInfoService messageInfoService;
    
    @Autowired
    private AppInfoService appInfoService;

    //列表
    @RequestMapping(value ="message_info_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("sendTime", false);
		
        PageInfo<MessageInfoDto> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", MessageInfo.STATUS99);//排除逻辑删除状态
        searchMap.put("EQ_appId", SessionFace.getSessionUser(request).getAppId());
        map.put("pageInfo", messageInfoService.queryDtoForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allSendTypes", MessageInfo.allSendTypes);
        map.put("allStatuss", MessageInfo.allStatuss);
        map.put("statusDeleted", MessageInfo.STATUS99);
        map.put("dateNow", DateUtil.now());
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/message/message_info_list_page";
        }else{
        	return "tiles.module.message.message_info_list";
        }
    }
    
    //站内信
    @RequestMapping(value ="letter_list")
    public String letterList(HttpServletRequest request, Map<String, Object> map,
            @RequestParam(value = "p", required = false) String isAjaxPage
            ) {
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
            PageInfo<MessageInfoDto> pageInfo = getPageInfo(request);
            searchMap.put("NOTEQ_readStatus", MessageReceive.STATUS99);//排除逻辑删除状态
            searchMap.put("EQ_receiverId", user.getUserId());
            
            Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
            sortMap.put("id", false);
            
            map.put("pageInfo", messageInfoService.queryLetterForPage(pageInfo, searchMap, sortMap).getData());
        }
        
        map.put("allStatuss", MessageReceive.allStatuss);
        
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
            return "/module/message/letter_list_page";
        }else{
            return "tiles.module.message.letter_list";
        }
    }
    
    //查看
    @RequestMapping(value ="letter_detail_view")
    public String letterShow(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        MessageInfoDto messageInfo = messageInfoService.getById(id).getData();
        if(messageInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", messageInfo);
        map.put("allStatuss", MessageReceive.allStatuss);
        return "tiles.module.message.letter_view";
    }

    //增加-查看
    @RequestMapping(value = "message_info_add", method = {RequestMethod.GET})
    public String add(HttpServletRequest request, Map<String, Object> map) {
    	map.put("sendTypeSms", MessageInfo.SENDTYPE3);
    	map.put("allSendTypes", MessageInfo.allSendTypes);
    	map.put("allReceiverTypes", MessageInfo.allReceiverTypes);
    	map.put("dateNow", DateUtil.now());
    	//处理app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if (null != appList && appList.size() > 1) {
            map.put("appList", appList);
        }
        return getReturnUrl(request, map, "tiles.module.message.message_info_detail");
//        return "tiles.module.message.message_info_detail";
    }


    //增加-保存
    @ResponseBody
    @RequestMapping(value ="message_info_add", method = RequestMethod.POST)
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,MessageInfo messageInfo){
        //id
        messageInfo.setId(null);
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        Response<Void> res = messageInfoService.add(messageInfo, sessionUser.getUserId(), sessionUser.getNickname());
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="message_info_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        MessageInfo messageInfo = messageInfoService.get(id).getData();
        if(messageInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", messageInfo);
        return "tiles.module.message.message_info_detail";
    }

    //修改-保存
    @RequestMapping(value ="message_info_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,MessageInfo messageInfo){
        Response<Void> res = Response.newInstance();
        if(messageInfo==null || messageInfo.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            MessageInfo sourceMessageInfo = messageInfoService.get(messageInfo.getId()).getData();
            if(sourceMessageInfo==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //APP_ID
            sourceMessageInfo.setAppId(messageInfo.getAppId());
            //标题
            sourceMessageInfo.setTitle(messageInfo.getTitle());
            //发送时间
            sourceMessageInfo.setSendTime(messageInfo.getSendTime());
            //内容
            sourceMessageInfo.setContext(messageInfo.getContext());
            //接收人类型
            sourceMessageInfo.setReceiverType(messageInfo.getReceiverType());
            //发送类型
            sourceMessageInfo.setSendType(messageInfo.getSendType());
            //状态
            sourceMessageInfo.setStatus(messageInfo.getStatus());
            //创建时间
            sourceMessageInfo.setCreateTime(messageInfo.getCreateTime());
            //创建人
            sourceMessageInfo.setCreatorId(messageInfo.getCreatorId());
            //创建人姓名
            sourceMessageInfo.setCreator(messageInfo.getCreator());
            //更新时间
            sourceMessageInfo.setUpdateTime(messageInfo.getUpdateTime());
            //更新人ID
            sourceMessageInfo.setUpdatorId(messageInfo.getUpdatorId());
            //更新人
            sourceMessageInfo.setUpdator(messageInfo.getUpdator());
            //接收客户端名称，多个用,分隔
            sourceMessageInfo.setReceiverAppId(messageInfo.getReceiverAppId());
            res= messageInfoService.save(sourceMessageInfo);
            messageInfo = sourceMessageInfo;
        }catch (Exception ex){
            logger.error("Save Method (Update) MessageInfo Error : " + messageInfo.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="message_info_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        MessageInfo messageInfo = messageInfoService.get(id).getData();
        if(messageInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", messageInfo);
        return "tiles.module.message.message_info_view";
    }

    //删除
    @RequestMapping(value ="message_info_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = messageInfoService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="message_info_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = messageInfoService.deleteLogic(ids);
        return res;
    }
    
    /**
     * <p>Description: 发送</p>
     * @author Tangtao on 2016年5月9日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="message_info_send", method = RequestMethod.POST)
    public Response<Void> send(HttpServletRequest request, Map<String, Object> map,@RequestParam Long id) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        Response<Void> res = messageInfoService.send(id, sessionUser.getUserId(), sessionUser.getNickname());
        return res;
    }
    
}
