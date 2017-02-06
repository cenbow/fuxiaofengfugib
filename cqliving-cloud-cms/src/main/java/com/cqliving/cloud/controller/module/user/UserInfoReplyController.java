package com.cqliving.cloud.controller.module.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.dto.UserInfoReplyDto;
import com.cqliving.cloud.online.account.service.UserInfoReplyService;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.file.ExcelEntityUtil;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/user_info_reply")
public class UserInfoReplyController extends CommonController {

    @Autowired
    private UserInfoReplyService userInfoReplyService;
    
    @Autowired
    private AppInfoService appInfoService;

    //资讯评论审核列表
    @RequestMapping(value ="user_info_reply_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
        map.put("sourceType", UserInfoReply.SOURCETYPE1);
        map.put("navigation", "资讯评论审核");
        map.put("action", "user_info_reply_list.html");
        map.put("typeSpecial", Information.TYPE2);  //专题新闻
        return to_list(request, map, isAjaxPage, UserInfoReply.SOURCETYPE1);
    }
    //问政评论审核列表
    @RequestMapping(value ="wz_reply_list")
    public String wzlist(HttpServletRequest request, Map<String, Object> map,
            @RequestParam(value = "p", required = false) String isAjaxPage
            ) {
        map.put("sourceType", UserInfoReply.SOURCETYPE2);
        map.put("navigation", "问政评论审核");
        map.put("action", "wz_reply_list.html");
        return to_list(request, map, isAjaxPage, UserInfoReply.SOURCETYPE2);
    }
    //商情评论审核列表
    @RequestMapping(value ="shop_reply_list")
    public String shoplist(HttpServletRequest request, Map<String, Object> map,
            @RequestParam(value = "p", required = false) String isAjaxPage
            ) {
        map.put("sourceType", UserInfoReply.SOURCETYPE3);
        map.put("navigation", "商情评论审核");
        map.put("action", "shop_reply_list.html");
        return to_list(request, map, isAjaxPage, UserInfoReply.SOURCETYPE3);
    }
    
    //随手拍审核列表
    @RequestMapping(value ="shooot_reply_list")
    public String shoootlist(HttpServletRequest request, Map<String, Object> map,
            @RequestParam(value = "p", required = false) String isAjaxPage
            ) {
        map.put("sourceType", UserInfoReply.SOURCETYPE4);
        map.put("navigation", "随手拍评论审核");
        map.put("action", "shooot_reply_list.html");
        return to_list(request, map, isAjaxPage, UserInfoReply.SOURCETYPE4);
    }
    
    //段子评论审核列表
    @RequestMapping(value ="joke_reply_list")
    public String jokelist(HttpServletRequest request, Map<String, Object> map,
            @RequestParam(value = "p", required = false) String isAjaxPage
            ) {
        map.put("sourceType", UserInfoReply.SOURCETYPE5);
        map.put("navigation", "段子评论审核");
        map.put("action", "joke_reply_list.html");
        return to_list(request, map, isAjaxPage, UserInfoReply.SOURCETYPE5);
    }
    
    //活动评论审核列表
    @RequestMapping(value ="act_reply_list")
    public String actlist(HttpServletRequest request, Map<String, Object> map,
            @RequestParam(value = "p", required = false) String isAjaxPage
            ) {
        map.put("sourceType", UserInfoReply.SOURCETYPE6);
        map.put("navigation", "活动评论审核");
        map.put("action", "act_reply_list.html");
        return to_list(request, map, isAjaxPage, UserInfoReply.SOURCETYPE6);
    }
    
    //话题评论审核列表
    @RequestMapping(value ="topic_reply_list")
    public String topiclist(HttpServletRequest request, Map<String, Object> map,
            @RequestParam(value = "p", required = false) String isAjaxPage
            ) {
        map.put("sourceType", UserInfoReply.SOURCETYPE7);
        map.put("navigation", "话题评论审核");
        map.put("action", "topic_reply_list.html");
        return to_list(request, map, isAjaxPage, UserInfoReply.SOURCETYPE7);
    }
    
    //旅游评论审核列表
    @RequestMapping(value ="tourism_reply_list")
    public String tourismlist(HttpServletRequest request, Map<String, Object> map,
            @RequestParam(value = "p", required = false) String isAjaxPage
            ) {
        map.put("sourceType", UserInfoReply.SOURCETYPE10);
        map.put("navigation", "话题评论审核");
        map.put("action", "tourism_reply_list.html");
        return to_list(request, map, isAjaxPage, UserInfoReply.SOURCETYPE10);
    }
    
    private String to_list(HttpServletRequest request, Map<String, Object> map,
            String isAjaxPage,Byte sourceType){
        Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        searchMap.put("NOTEQ_status", UserInfoReply.STATUS99);//排除逻辑删除状态
        
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("statusNew", false);
        sortMap.put("createTime", false);
        sortMap.put("id", false);
        //数据权限
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        userDate(user, appList, searchMap);
        PageInfo<UserInfoReplyDto> pageInfo = getPageInfo(request);
        map.put("pageInfo", userInfoReplyService.queryByPage(pageInfo, searchMap, sortMap,sourceType).getData());
        map.put("allStatuss", UserInfoReply.allStatuss);
        map.put("allSourceTypes", UserInfoReply.allSourceTypes);
        map.put("SOURCETYPE1", UserInfoReply.SOURCETYPE1);
        map.put("SOURCETYPE2", UserInfoReply.SOURCETYPE2);
        map.put("SOURCETYPE3", UserInfoReply.SOURCETYPE3);
        map.put("SOURCETYPE4", UserInfoReply.SOURCETYPE4);
        map.put("SOURCETYPE5", UserInfoReply.SOURCETYPE5);
        map.put("SOURCETYPE6", UserInfoReply.SOURCETYPE6);
        map.put("SOURCETYPE7", UserInfoReply.SOURCETYPE7);
        map.put("SOURCETYPE10", UserInfoReply.SOURCETYPE10);
        
        //查询按钮和点击页面是ajax操作。
        map.put("allTypes", UserInfoReply.allTypes);
        map.put("noAuditing", UserInfoReply.STATUS2);
        map.put("noPass", UserInfoReply.STATUS_1);
        map.put("pass", UserInfoReply.STATUS3);
        if(StringUtils.isNotBlank(isAjaxPage)){
            return "/module/userInfoReply/user_info_reply_list_page";
        }else{
            map.put("statusDeleted", UserInfoReply.STATUS99);
            return "tiles.module.userInfoReply.user_info_reply_list";
        }
    }
    
    //导出
    //@RequestMapping(value = "/common/user_info_reply_export")
    @RequestMapping(value ={"news_export","wz_export","shop_export","shoot_export","joke_export","act_export","topic_export","tourism_export"})
    public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        searchMap.put("NOTEQ_status", UserInfoReply.STATUS99);//排除逻辑删除状态
        
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("statusNew", false);
        sortMap.put("createTime", false);
        sortMap.put("id", false);
        
        PageInfo<UserInfoReplyDto> pageInfo = new PageInfo<UserInfoReplyDto>(Integer.MAX_VALUE, 1);
        String sourceType = (String)searchMap.get("EQ_sourceType"); 
        //数据权限
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        userDate(user, appList, searchMap);
        
        userInfoReplyService.queryByPage(pageInfo, searchMap, sortMap ,StringUtils.isNotBlank(sourceType)?Byte.valueOf(sourceType.trim()):UserInfoReply.SOURCETYPE1).getData();
        ExcelEntityUtil.doExportForXls(response, "评论审核列表", pageInfo.getPageResults());
    }

    //增加-查看
    //@RequestMapping(value ="/common/user_info_reply_add", method = RequestMethod.GET)
    @RequestMapping(value ={"news_reply_add","wz_reply_add","shop_reply_add","shoot_reply_add","joke_reply_add","act_reply_add","topic_reply_add","tourism_reply_add"}, method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.userInfoReply.user_info_reply_detail";
    }


    //增加-保存
    //@RequestMapping(value ="/common/user_info_reply_add", method = RequestMethod.POST)
    @RequestMapping(value ={"news_reply_add","wz_reply_add","shop_reply_add","shoot_reply_add","joke_reply_add","act_reply_add","topic_reply_add","tourism_reply_add"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,UserInfoReply userInfoReply){
        //ID
        userInfoReply.setId(null);
            //新闻资讯ID，对应info_classify表的主键
        userInfoReply.setInfoClassifyId(null);
            //回复源类型
        userInfoReply.setSourceType(null);
        Response<Void> res = userInfoReplyService.save(userInfoReply);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    //@RequestMapping(value ="/common/user_info_reply_update", method = RequestMethod.GET)
    @RequestMapping(value ={"news_reply_update","wz_reply_update","shop_reply_update","shoot_reply_update","joke_reply_update","act_reply_update","topic_reply_update","tourism_reply_update"}, method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserInfoReply userInfoReply = userInfoReplyService.get(id).getData();
        if(userInfoReply==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userInfoReply);
        return "tiles.module.userInfoReply.user_info_reply_detail";
    }

    //修改-保存
    //@RequestMapping(value ="/common/user_info_reply_update", method = RequestMethod.POST)
    @RequestMapping(value ={"news_reply_update","wz_reply_update","shop_reply_update","shoot_reply_update","joke_reply_update","act_reply_update","topic_reply_update","tourism_reply_update"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,UserInfoReply userInfoReply){
        Response<Void> res = Response.newInstance();
        if(userInfoReply==null || userInfoReply.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            UserInfoReply sourceUserInfoReply = userInfoReplyService.get(userInfoReply.getId()).getData();
            if(sourceUserInfoReply==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //会话code
            sourceUserInfoReply.setSessionCode(userInfoReply.getSessionCode());
            //评论人ID
            sourceUserInfoReply.setReplyUserId(userInfoReply.getReplyUserId());
            //评论人姓名
            sourceUserInfoReply.setName(userInfoReply.getName());
            //评论内容
            sourceUserInfoReply.setContent(userInfoReply.getContent());
            //评论图片
            sourceUserInfoReply.setImageUrl(userInfoReply.getImageUrl());
            //评论位置
            sourceUserInfoReply.setPlace(userInfoReply.getPlace());
            //最后经纬度
            sourceUserInfoReply.setLat(userInfoReply.getLat());
            sourceUserInfoReply.setLng(userInfoReply.getLng());
            //source_type=1时，对应对应information表的主键。source_type=2时，对应wz_question表的主键
            sourceUserInfoReply.setSourceId(userInfoReply.getSourceId());
            //APPID，评论用户使用APP_id
            sourceUserInfoReply.setAppId(userInfoReply.getAppId());
            //类型
            sourceUserInfoReply.setType(userInfoReply.getType());
            //被回复人名称
            sourceUserInfoReply.setPassiveReplyName(userInfoReply.getPassiveReplyName());
            //被回复人ID
            sourceUserInfoReply.setPassiveReplyId(userInfoReply.getPassiveReplyId());
            //被回复人显示状态
            sourceUserInfoReply.setPassiveRelpsyStatus(userInfoReply.getPassiveRelpsyStatus());
            //状态
            sourceUserInfoReply.setStatus(userInfoReply.getStatus());
            //创建时间
            sourceUserInfoReply.setCreateTime(userInfoReply.getCreateTime());
            //用户更新时间
            sourceUserInfoReply.setUpdateTime(userInfoReply.getUpdateTime());
            //更新人ID
            sourceUserInfoReply.setUpdatorId(userInfoReply.getUpdatorId());
            //更新人姓名
            sourceUserInfoReply.setUpdator(userInfoReply.getUpdator());
            //审阅状态
            sourceUserInfoReply.setAuditingType(userInfoReply.getAuditingType());
            //审核人ID
            sourceUserInfoReply.setAuditingId(userInfoReply.getAuditingId());
            //审核人姓名
            sourceUserInfoReply.setAuditingtor(userInfoReply.getAuditingtor());
            //审核时间
            sourceUserInfoReply.setAuditingTime(userInfoReply.getAuditingTime());
            //评论点赞数
            sourceUserInfoReply.setPraise(userInfoReply.getPraise());
            res= userInfoReplyService.save(sourceUserInfoReply);
            userInfoReply = sourceUserInfoReply;
        }catch (Exception ex){
            logger.error("Save Method (Update) UserInfoReply Error : " + userInfoReply.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    //@RequestMapping(value ="/common/user_info_reply_view")
    @RequestMapping(value ={"news_reply_view","wz_reply_view","shop_reply_view","shoot_reply_view","joke_reply_view","act_reply_view","topic_reply_view","tourism_reply_view"})
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserInfoReply userInfoReply = userInfoReplyService.get(id).getData();
        if(userInfoReply==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userInfoReply);
        return "tiles.module.userInfoReply.user_info_reply_view";
    }

    //审核
    //@RequestMapping(value ="/common/auditing", method = RequestMethod.POST)
    @RequestMapping(value ={"news_auditing","wz_auditing","shop_auditing","shoot_auditing","joke_auditing","act_auditing","topic_auditing","tourism_auditing"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> auditing(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids,@RequestParam("sourceIds[]") Long[] sourceIds,Byte status,String auditingContent,Byte sourceType){
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            Response<Void> res = userInfoReplyService.auditing(status, auditingContent,user.getUserId(),user.getNickname(), sourceIds, sourceType, ids);
            return res;
        }
        return null;
    }
    
    //审核
    //@RequestMapping(value ="/common/auditing_batch", method = RequestMethod.POST)
    @RequestMapping(value ={"news_auditing_batch","wz_auditing_batch","shop_auditing_batch","shoot_auditing_batch","joke_auditing_batch","act_auditing_batch","topic_auditing_batch","tourism_auditing_batch"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> auditingBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids,@RequestParam("sourceIds[]") Long[] sourceIds,Byte status,String auditingContent,Byte sourceType){
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            Response<Void> res = userInfoReplyService.auditing(status, auditingContent,user.getUserId(),user.getNickname(), sourceIds, sourceType, ids);
            return res;
        }
        return null;
    }
    
    //删除
    //@RequestMapping(value ="/common/user_info_reply_delete", method = RequestMethod.POST)
    @RequestMapping(value ={"news_reply_delete","wz_reply_delete","shop_reply_delete","shoot_reply_delete","joke_reply_delete","act_reply_delete","topic_reply_delete","tourism_reply_delete"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = userInfoReplyService.deleteLogic(id);
        return res;
    }

	//批量删除
    //@RequestMapping(value ="/common/user_info_reply_delete_batch", method = RequestMethod.POST)
    @RequestMapping(value ={"news_reply_delete_batch","wz_reply_delete_batch","shop_reply_delete_batch","shoot_reply_delete_batch","joke_reply_delete_batch","act_reply_delete_batch","topic_reply_delete_batch","tourism_reply_delete_batch"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = userInfoReplyService.deleteLogic(ids);
        return res;
    }
}
