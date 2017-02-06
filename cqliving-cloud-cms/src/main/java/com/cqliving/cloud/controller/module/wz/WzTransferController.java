package com.cqliving.cloud.controller.module.wz;

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
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.interfacc.dto.WzCollectInfoData;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.cloud.online.wz.domain.WzAppAuthority;
import com.cqliving.cloud.online.wz.domain.WzAuthority;
import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.cloud.online.wz.domain.WzQuestionImage;
import com.cqliving.cloud.online.wz.domain.WzTransfer;
import com.cqliving.cloud.online.wz.domain.WzTransferData;
import com.cqliving.cloud.online.wz.domain.WzTransferDto;
import com.cqliving.cloud.online.wz.domain.WzUser;
import com.cqliving.cloud.online.wz.dto.WzAppAuthorityDto;
import com.cqliving.cloud.online.wz.service.WzAuthorityService;
import com.cqliving.cloud.online.wz.service.WzQuestionImageService;
import com.cqliving.cloud.online.wz.service.WzQuestionService;
import com.cqliving.cloud.online.wz.service.WzTransferService;
import com.cqliving.cloud.online.wz.service.WzUserService;
import com.cqliving.cloud.security.service.SysUserService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

/**
 * Title:问政子帐号
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月19日
 */
@Controller
@RequestMapping(value = "/module/wz/child")
public class WzTransferController extends CommonController {

    @Autowired
    private WzQuestionService wzQuestionService;
    @Autowired
    private WzTransferService wzTransferService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private WzAuthorityService wzAuthorityService;
    @Autowired
    private WzUserService wzUserService;
    @Autowired
    private WzQuestionImageService wzQuestionImageService;
    
    /**
     * Title:子帐号列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月16日
     * @param request
     * @param map
     * @param isAjaxPage
     * @param search_GTE_createTime
     * @param search_LT_createTime
     * @return
     */
    @RequestMapping(value ="wz_question_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
        @RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
        ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime
        ) {
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        SysUser sysUser = sysUserService.get(sessionUser.getUserId());
        map.put("sysUser", sysUser);
        

        Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
        searchMap.put("EQ_appId", sessionUser.getAppId());
        searchMap.put("EQ_currentUserId", sessionUser.getUserId());
        
        //默认时间范围3个月
        map.put("search_LT_createTime", search_LT_createTime);
        searchMap.put("LT_createTime", search_LT_createTime);
        map.put("search_GTE_createTime", search_GTE_createTime);
        searchMap.put("GTE_createTime", search_GTE_createTime);
        
        PageInfo<WzTransferDto> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", WzTransfer.STATUS99);//排除逻辑删除状态
        map.put("pageInfo", wzTransferService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allTypes", WzQuestion.allTypes);
        map.put("allStatuss", WzTransfer.allStatuss);
        map.put("allResults", WzTransfer.allResults);
        
//      问政子帐号:转交时用
        List<WzUser> wzUserList = wzUserService.getByAppId(sessionUser.getAppId()).getData();
        map.put("wzUserList", wzUserList);

        //是否总帐号发布
        boolean isLastPublish = false;//默认是不需要总负责人发布的。
        WzAppAuthorityDto wzAppAuthorityDto = wzAuthorityService.getAppAuthByAuthName(sessionUser.getAppId(), WzAuthority.NAME_REPLY_IS_LAST_PUBLISH).getData();
        isLastPublish = wzAppAuthorityDto != null && wzAppAuthorityDto.getValue().equals(WzAppAuthority.VALUE1); 
        map.put("isLastPublish", isLastPublish);
        
//      发布状态下的问政是否允许删除
        wzAppAuthorityDto = wzAuthorityService.getAppAuthByAuthName(sessionUser.getAppId(), WzAuthority.NAME_ALREADY_PUBLISH_ALLOW_DEL).getData();
        map.put("isAllowDel", wzAppAuthorityDto != null && wzAppAuthorityDto.getValue().equals(WzAppAuthority.VALUE1));
        
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
            return "/module/wz/child/wz_question_list_page";
        }else{
            return "tiles.module.wz.child.wz_question_list";
        }
    }
    
    /**
     * Title:查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月21日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="wz_question_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        WzTransfer wzTransfer = wzTransferService.get(id).getData();
        if(wzTransfer==null || WzTransfer.STATUS99.equals(wzTransfer.getStatus())){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        WzQuestion wzQuestion = wzQuestionService.get(wzTransfer.getQuestionId()).getData();
        if(wzQuestion==null || WzQuestion.STATUS99.equals(wzQuestion.getStatus())){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }

        Long appId = wzQuestion.getAppId();
        Long questionId = wzQuestion.getId();
        
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        SysUser sysUser = sysUserService.get(sessionUser.getUserId());
        map.put("sysUser", sysUser);
        
//      发布状态下的问政是否允许删除
        WzAppAuthorityDto wzAppAuthorityDto = wzAuthorityService.getAppAuthByAuthName(sessionUser.getAppId(), WzAuthority.NAME_ALREADY_PUBLISH_ALLOW_DEL).getData();
        map.put("isAllowDel", wzAppAuthorityDto != null && wzAppAuthorityDto.getValue().equals(WzAppAuthority.VALUE1));
        
      //获得收集信息
        List<WzCollectInfoData> collectList = wzQuestionService.getUserCollectInfo(appId, questionId).getData();
        map.put("collectList", collectList);
        
        //问政子帐号:转交时用
        List<WzUser> wzUserList = wzUserService.getByAppId(sessionUser.getAppId()).getData();
        map.put("wzUserList", wzUserList);
        
        //是否总帐号发布
        boolean isLastPublish = false;//默认是不需要总负责人发布的。
        wzAppAuthorityDto = wzAuthorityService.getAppAuthByAuthName(sessionUser.getAppId(), WzAuthority.NAME_REPLY_IS_LAST_PUBLISH).getData();
        isLastPublish = wzAppAuthorityDto != null && wzAppAuthorityDto.getValue().equals(WzAppAuthority.VALUE1); 
        map.put("isLastPublish", isLastPublish);
        
        //获得转交记录
        List<WzTransferData> wzTransferList = wzTransferService.getByQuestion(wzQuestion.getId()).getData();
        map.put("wzTransferList", wzTransferList);
        
        List<WzQuestionImage> imageList = wzQuestionImageService.getByQuestion(id).getData();
        map.put("item", wzQuestion);
        map.put("imageList", imageList);
        map.put("wzTransfer", wzTransfer);
        
        return "tiles.module.wz.child.wz_question_view";
    }
    
    /**
     * Title:回复-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月20日
     * @return
     */
    @RequestMapping(value ="wz_question_reply", method = RequestMethod.GET)
    @ResponseBody
    public Response<WzQuestion> replay(HttpServletRequest request, @RequestParam Long id){
        WzTransfer wzTransfer = wzTransferService.get(id).getData();
        Response<WzQuestion> rs = Response.newInstance();
        if(wzTransfer != null){
            rs = wzQuestionService.get(wzTransfer.getQuestionId());
        }
        return rs;
    }
    
    /**
     * Title:回复
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月20日
     * @param request
     * @param id
     * @param auditingDepartment
     * @param content
     * @return
     */
    @RequestMapping(value ="wz_question_reply", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> replySave(HttpServletRequest request, @RequestParam Long id,  @RequestParam String auditingDepartment, @RequestParam String content){
        Response<Void> rs = Response.newInstance();
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        if(id == null || StringUtils.isBlank(auditingDepartment) || StringUtils.isBlank(content)){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzTransferService.replySave(id, auditingDepartment, content, sessionUser.getUserId());
        return rs;
    }
    /**
     * Title:提交
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月21日
     * @param request
     * @param questionId
     * @return
     */
    @RequestMapping(value ="wz_question_submit", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> submitSave(HttpServletRequest request, @RequestParam Long id){
        Response<Void> rs = Response.newInstance();
        if(id == null){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        Long ids[] = new Long[1];
        ids[0] = id;
        rs = wzTransferService.submitBatchSave(ids);
        return rs;
    }
    /**
     * Title:批量提交
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月21日
     * @param request
     * @param questionId
     * @return
     */
    @RequestMapping(value ="wz_question_submit_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> submitBatchSave(HttpServletRequest request, @RequestParam("ids[]") Long[] ids){
        Response<Void> rs = Response.newInstance();
        if(ids == null){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzTransferService.submitBatchSave(ids);
        return rs;
    }
    /**
     * Title:发布
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月21日
     * @param request
     * @param questionId
     * @return
     */
    @RequestMapping(value ="wz_question_publish", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> publishSave(HttpServletRequest request, @RequestParam Long id){
        Response<Void> rs = Response.newInstance();
        if(id == null){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        Long ids[] = new Long[1];
        ids[0] = id;
        rs = wzTransferService.publishBatchSave(ids);
        return rs;
    }
    /**
     * Title:批量发布
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月21日
     * @param request
     * @param questionId
     * @return
     */
    @RequestMapping(value ="wz_question_publish_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> publishBatchSave(HttpServletRequest request, @RequestParam("ids[]") Long[] ids){
        Response<Void> rs = Response.newInstance();
        if(ids == null){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzTransferService.publishBatchSave(ids);
        return rs;
    }
    
    /**
     * Title:转交
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月20日
     * @param request
     * @param id
     * @param currentUserId
     * @param auditingDepartment
     * @param description
     * @return
     */
    @RequestMapping(value ="wz_question_transfer", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> transferSave(HttpServletRequest request, @RequestParam Long id, @RequestParam Long currentUserId, @RequestParam String auditingDepartment, @RequestParam String description){
        Response<Void> rs = Response.newInstance();
        if(id == null || currentUserId == null){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        rs = wzTransferService.transferSave(id, currentUserId, auditingDepartment, description, sessionUser.getUserId(), sessionUser.getNickname());
        return rs;
    }
    /**
     * Title:驳回
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月20日
     * @param request
     * @param id
     * @param content
     * @return
     */
    @RequestMapping(value ="wz_question_return", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> returnSave(HttpServletRequest request, @RequestParam Long id, @RequestParam String content){
        Response<Void> rs = Response.newInstance();
        if(id == null){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        WzTransfer wzTransfer = wzTransferService.get(id).getData();
        if(wzTransfer == null){
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政不存在。");
            return rs;
        }
        
        WzQuestion wzQuestion = wzQuestionService.get(wzTransfer.getQuestionId()).getData();
        if(wzQuestion == null){
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政不存在。");
            return rs;
        }
        wzTransfer.setStatus(WzTransfer.STATUS3);
        wzTransfer.setResult(WzTransfer.RESULT1);
        rs = wzTransferService.update(wzTransfer);
        
        wzQuestion.setStatus(WzQuestion.STATUS6);
        wzQuestion.setRejectContent(content);
        rs = wzQuestionService.update(wzQuestion);
        
        return rs;
    }
    
    
    /**
     * Title:删除
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月16日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="wz_question_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = Response.newInstance(); 
                
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        WzAppAuthorityDto wzAppAuthorityDto = wzAuthorityService.getAppAuthByAuthName(sessionUser.getAppId(), WzAuthority.NAME_ALREADY_PUBLISH_ALLOW_DEL).getData();

        boolean hasPublishAllowDel = wzAppAuthorityDto != null && wzAppAuthorityDto.getValue().equals(WzAppAuthority.VALUE0);
        Long[] ids = new Long[1];
        ids[0] = id;
        res = wzTransferService.del(ids, hasPublishAllowDel);
        return res;
    }

    /**
     * Title:批量删除
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月16日
     * @param request
     * @param map
     * @param ids
     * @return
     */
    @RequestMapping(value ="wz_question_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = Response.newInstance(); 
        
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        WzAppAuthorityDto wzAppAuthorityDto = wzAuthorityService.getAppAuthByAuthName(sessionUser.getAppId(), WzAuthority.NAME_ALREADY_PUBLISH_ALLOW_DEL).getData();
        
        // hasPublishAllowDel == true 已经发布的问政不允许删除
        boolean hasPublishAllowDel = wzAppAuthorityDto != null && wzAppAuthorityDto.getValue().equals(WzAppAuthority.VALUE0);
        res = wzTransferService.del(ids, hasPublishAllowDel);
        return res;
    }
    

    /**
     * Title:发布后保存回复信息
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月29日
     * @param request
     * @param questionId
     * @param auditingDepartment
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="common/publishAfterSaveReply", method = RequestMethod.POST)
    public Response<Void> publishAfterSaveReply(HttpServletRequest request, @RequestParam Long id,  @RequestParam String auditingDepartment, @RequestParam String content){
        Response<Void> rs = Response.newInstance();
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        if(id == null || StringUtils.isBlank(auditingDepartment) || StringUtils.isBlank(content)){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzTransferService.saveAfterPublisReply(id, auditingDepartment, content, sessionUser.getUserId(), sessionUser.getNickname());
        return rs;
    }
}
