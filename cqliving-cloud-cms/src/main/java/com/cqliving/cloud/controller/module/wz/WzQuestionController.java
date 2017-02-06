package com.cqliving.cloud.controller.module.wz;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.cqliving.cloud.common.CommonSysRoleEnum;
import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.interfacc.dto.WzCollectInfoData;
import com.cqliving.cloud.online.security.domain.SysRole;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.cloud.online.wz.domain.WzAppAuthority;
import com.cqliving.cloud.online.wz.domain.WzAuthority;
import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.cloud.online.wz.domain.WzQuestionImage;
import com.cqliving.cloud.online.wz.domain.WzTransferData;
import com.cqliving.cloud.online.wz.domain.WzUser;
import com.cqliving.cloud.online.wz.dto.WzAppAuthorityDto;
import com.cqliving.cloud.online.wz.dto.WzQuestionExcelDownload;
import com.cqliving.cloud.online.wz.service.WzAuthorityService;
import com.cqliving.cloud.online.wz.service.WzQuestionImageService;
import com.cqliving.cloud.online.wz.service.WzQuestionService;
import com.cqliving.cloud.online.wz.service.WzTransferService;
import com.cqliving.cloud.online.wz.service.WzUserService;
import com.cqliving.cloud.security.service.SysUserService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.file.ExcelEntityUtil;
import com.google.common.collect.Maps;

/**
 * Title:问政
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月16日
 */
@Controller
@RequestMapping(value = "/module/wz")
public class WzQuestionController extends CommonController {

    @Autowired
    private WzQuestionService wzQuestionService;
    @Autowired
    private WzAuthorityService wzAuthorityService;
    @Autowired
    private WzQuestionImageService wzQuestionImageService;
    @Autowired
    private WzUserService wzUserService;
    @Autowired
    private WzTransferService wzTransferService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private AppInfoService appInfoService;

    /**
     * Title:列表
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
    public String list(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
        ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime
        , @RequestParam(value="isDownload", required = false) String isDownload
    	) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Long appId = sessionUser.getAppId();
    	Long userId = sessionUser.getUserId();
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        if(searchMap.containsKey("appId") && searchMap.get("appId") != null && StringUtils.isNotBlank(searchMap.get("appId").toString())){
        	try {
        		appId = Long.parseLong(searchMap.get("appId").toString());
			} catch (NumberFormatException e) {
				logger.error("appId参数异常:" + searchMap.get("appId"));
			}
        }
        
    	//发布状态下的问政是否允许删除 
        WzAppAuthorityDto wzAppAuthorityDto = null;
        String replyContent = "";
        List<WzUser> wzUserList = null; //问政子帐号 //fixed 这个可以放到需要的时候去查询。这里如果是多个app权限的管理员登录时可能不准确
        WzAppAuthorityDto config = null;
        if(appId != null){
        	wzAppAuthorityDto = wzAuthorityService.getAppAuthByAuthName(appId, WzAuthority.NAME_ALREADY_PUBLISH_ALLOW_DEL).getData();
//        	 获得设置自动回复内容，如果是自动回复就说明这条问政不需要转交、回复等功能，就已经完结了。
            replyContent = wzQuestionService.getAutoReplyContent(appId);
            if(StringUtils.isBlank(replyContent)){
                wzUserList = wzUserService.getByAppId(appId).getData();
            }
            // 配置问政是否需要审核
            config = wzAuthorityService.getAppAuthByAuthName(appId, WzAuthority.NAME_QUESTION_IS_CHECK).getData();
            searchMap.put("EQ_appId", appId);
        }
        searchMap.put("LT_createTime", search_LT_createTime);
        searchMap.put("GTE_createTime", search_GTE_createTime);
		
        PageInfo<WzQuestion> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", WzQuestion.STATUS99);//排除逻辑删除状态
        
        if("true".equals(isDownload)){
        	List<WzQuestionExcelDownload> list = wzQuestionService.excelDownload(searchMap, sortMap).getData();
            String exportFileName = "问政列表导出";
            ExcelEntityUtil.doExportForXls(response, exportFileName, list);
            return null;
        }else{
            map.put("isAllowDel", wzAppAuthorityDto != null && wzAppAuthorityDto.getValue().equals(WzAppAuthority.VALUE1+""));
            map.put("wzUserList", wzUserList);
            map.put("isCheck", config != null && config.getValue().equals(WzAppAuthority.VALUE1+""));
            map.put("search_LT_createTime", search_LT_createTime);
            map.put("search_GTE_createTime", search_GTE_createTime);
            map.put("pageInfo", wzQuestionService.queryForPage(pageInfo, searchMap, sortMap).getData());
            map.put("allTypes", WzQuestion.allTypes);
            map.put("allStatuss", WzQuestion.allStatuss);
            
        	SysUser sysUser = sysUserService.get(userId);
        	map.put("sysUser", sysUser);
            List<AppInfoDto> appList = appInfoService.getBySysUser(sessionUser.getUsertype(), userId).getData();
            map.put("appList",appList);
        	//查询按钮和点击页面是ajax操作。
        	if(StringUtils.isNotBlank(isAjaxPage)){
        		return "/module/wz/wz_question_list_page";
        	}else{
        		return "tiles.module.wz.wz_question_list";
        	}
        }
        
    }

    /**
     * Title:修改-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月19日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="wz_question_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        WzQuestion wzQuestion = wzQuestionService.get(id).getData();
        if(wzQuestion==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", wzQuestion);
        
        //问政子帐号
        List<WzUser> wzUserList = null;
        //获得设置：是否有转交功能；也就是判断是否有子帐号
        String replyContent = wzQuestionService.getAutoReplyContent(sessionUser.getAppId());
        if(StringUtils.isBlank(replyContent)){
            wzUserList = wzUserService.getByAppId(sessionUser.getAppId()).getData();
        }
        map.put("wzUserList", wzUserList);
        
        // 配置问政是否需要审核
        WzAppAuthorityDto config = wzAuthorityService.getAppAuthByAuthName(sessionUser.getAppId(), WzAuthority.NAME_QUESTION_IS_CHECK).getData();
        map.put("isCheck", config != null && config.getValue().equals(WzAppAuthority.VALUE1+""));
        
        //是否子帐号
        WzUser wzUser = wzUserService.get(sessionUser.getUserId()).getData();
        map.put("isWzUser", wzUser != null);
        
        return "tiles.module.wz.wz_question_detail";
    }

    //修改-保存
    @RequestMapping(value ="wz_question_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,WzQuestion wzQuestion){
        Response<Void> res = Response.newInstance();
        if(wzQuestion==null || wzQuestion.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            WzQuestion sourceWzQuestion = wzQuestionService.get(wzQuestion.getId()).getData();
            if(sourceWzQuestion==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //客户端_ID
            sourceWzQuestion.setAppId(wzQuestion.getAppId());
            //事件类型
            sourceWzQuestion.setType(wzQuestion.getType());
            //状态
            sourceWzQuestion.setStatus(wzQuestion.getStatus());
            //所属区域CODE，对应wz_region表CODE
            sourceWzQuestion.setRegionCode(wzQuestion.getRegionCode());
            //所属区域名称
            sourceWzQuestion.setRegionName(wzQuestion.getRegionName());
            //标题
            sourceWzQuestion.setTitle(wzQuestion.getTitle());
            //内容
            sourceWzQuestion.setContent(wzQuestion.getContent());
            //资讯浏览量
            sourceWzQuestion.setViewCount(wzQuestion.getViewCount());
            //资讯回复量
            sourceWzQuestion.setReplyCount(wzQuestion.getReplyCount());
            //创建时间
            sourceWzQuestion.setCreateTime(wzQuestion.getCreateTime());
            //创建人ID
            sourceWzQuestion.setCreatorId(wzQuestion.getCreatorId());
            //创建人名称
            sourceWzQuestion.setCreator(wzQuestion.getCreator());
            //拒绝内容
            sourceWzQuestion.setRejectContent(wzQuestion.getRejectContent());
            //回复内容
            sourceWzQuestion.setReplyContent(wzQuestion.getReplyContent());
            //回复时间
            sourceWzQuestion.setReplyTime(wzQuestion.getReplyTime());
            //更新时间
            sourceWzQuestion.setUpdateTime(wzQuestion.getUpdateTime());
            //更新人ID
            sourceWzQuestion.setUpdatorId(wzQuestion.getUpdatorId());
            //更新人
            sourceWzQuestion.setUpdator(wzQuestion.getUpdator());
            //审核人ID
            sourceWzQuestion.setAuditingId(wzQuestion.getAuditingId());
            //审核人姓名
            sourceWzQuestion.setAuditingtor(wzQuestion.getAuditingtor());
            //审核时间（即受理时间）
            sourceWzQuestion.setAuditingTime(wzQuestion.getAuditingTime());
            //受理部门，用于在前台展示
            sourceWzQuestion.setAuditingDepartment(wzQuestion.getAuditingDepartment());
            //评论位置
            sourceWzQuestion.setPlace(wzQuestion.getPlace());
            //最后经纬度
            sourceWzQuestion.setLat(wzQuestion.getLat());
            sourceWzQuestion.setLng(wzQuestion.getLng());
            //转交时间
            sourceWzQuestion.setTransferTime(wzQuestion.getTransferTime());
            res= wzQuestionService.save(sourceWzQuestion);
            wzQuestion = sourceWzQuestion;
        }catch (Exception ex){
            logger.error("Save Method (Update) WzQuestion Error : " + wzQuestion.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    /**
     * Title:查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月16日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="wz_question_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        WzQuestion wzQuestion = wzQuestionService.get(id).getData();
        if(wzQuestion==null || WzQuestion.STATUS99.equals(wzQuestion.getStatus())){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        SysUser sysUser = sysUserService.get(sessionUser.getUserId());
        map.put("sysUser", sysUser);
        
        Long appId = wzQuestion.getAppId();
        Long questionId = wzQuestion.getId();
//      配置问政是否需要审核
        WzAppAuthorityDto config = wzAuthorityService.getAppAuthByAuthName(appId, WzAuthority.NAME_QUESTION_IS_CHECK).getData();
        map.put("isCheck", config != null && config.getValue().equals(WzAppAuthority.VALUE1+""));
        
//      发布状态下的问政是否允许删除
        WzAppAuthorityDto wzAppAuthorityDto = wzAuthorityService.getAppAuthByAuthName(appId, WzAuthority.NAME_ALREADY_PUBLISH_ALLOW_DEL).getData();
        map.put("isAllowDel", wzAppAuthorityDto != null && wzAppAuthorityDto.getValue().equals(WzAppAuthority.VALUE1));
        
        //获得收集信息
        List<WzCollectInfoData> collectList = wzQuestionService.getUserCollectInfo(appId, questionId).getData();
        map.put("collectList", collectList);
        
        //问政子帐号
        List<WzUser> wzUserList = null;
        //获得设置自动回复内容，如果是自动回复就说明这条问政不需要转交、回复等功能，就已经完结了。
        String replyContent = wzQuestionService.getAutoReplyContent(appId);
        if(StringUtils.isBlank(replyContent)){
            wzUserList = wzUserService.getByAppId(appId).getData();
        }
        map.put("wzUserList", wzUserList);
        
        //是否总帐号发布
        boolean isLastPublish = false;//默认是不需要总负责人发布的。
        wzAppAuthorityDto = wzAuthorityService.getAppAuthByAuthName(appId, WzAuthority.NAME_REPLY_IS_LAST_PUBLISH).getData();
        isLastPublish = wzAppAuthorityDto != null && wzAppAuthorityDto.getValue().equals(WzAppAuthority.VALUE1); 
        map.put("isLastPublish", isLastPublish);
        
        //获得转交记录
        List<WzTransferData> wzTransferList = wzTransferService.getByQuestion(id).getData();
        map.put("wzTransferList", wzTransferList);
        
        List<WzQuestionImage> imageList = wzQuestionImageService.getByQuestion(id).getData();
        map.put("item", wzQuestion);
        map.put("imageList", imageList);
        
        return "tiles.module.wz.wz_question_view";
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
        WzQuestion wzQuestion = wzQuestionService.get(id).getData();
        //发布状态下的问政不允许删除
        if(wzQuestion.getStatus().equals(WzQuestion.STATUS7) && wzAppAuthorityDto != null && wzAppAuthorityDto.getValue().equals(WzAppAuthority.VALUE0+"")){
            res.setCode(-1);
            res.setMessage("问政已发布，不允许删除。");
            return res;
        }
        res = wzQuestionService.updateStatus(WzQuestion.STATUS99, new Long[]{id});
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
        
        WzQuestion wzQuestion;
        for(Long id : ids){
            wzQuestion = wzQuestionService.get(id).getData();
          //发布状态下的问政不允许删除
            if(wzQuestion.getStatus().equals(WzQuestion.STATUS7) && wzAppAuthorityDto != null && wzAppAuthorityDto.getValue().equals(WzAppAuthority.VALUE0+"")){
                res.setCode(-1);
                res.setMessage("问政已发布，不允许删除。");
                return res;
            }
        }
        res = wzQuestionService.updateStatus(WzQuestion.STATUS99, ids);
        return res;
    }
    
    /**
     * Title:审核
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月18日
     * @param request
     * @param questionId
     * @param content
     * @param checkStatus
     * @return
     */
    @RequestMapping(value ="wz_question_check", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> check(HttpServletRequest request, @RequestParam Long[] questionId, @RequestParam String checkStatus, String content){
        Response<Void> rs = Response.newInstance();
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        if(StringUtils.isBlank(checkStatus)){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        Byte status = checkStatus.equals("1") ? WzQuestion.STATUS3 : WzQuestion.STATUS_1;
        rs = wzQuestionService.check(sessionUser.getAppId(), sessionUser.getUserId(), sessionUser.getNickname(), questionId, status, content, sessionUser.getNickname());
        return rs;
    }
    
    /**
     * Title:批量审核
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月19日
     * @param request
     * @param questionIds
     * @param content
     * @param checkStatus
     * @return
     */
    @RequestMapping(value ="wz_question_check_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> checkBatch(HttpServletRequest request, @RequestParam Long[] questionId, @RequestParam String content, @RequestParam String checkStatus){
        Response<Void> rs = Response.newInstance();
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        if(StringUtils.isBlank(checkStatus)){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        Byte status = checkStatus.equals("1") ? WzQuestion.STATUS3 : WzQuestion.STATUS_1;
        rs = wzQuestionService.check(sessionUser.getAppId(), sessionUser.getUserId(), sessionUser.getNickname(), questionId, status, content, sessionUser.getNickname());
        return rs;
    }
    
    /**
     * Title:转交
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月20日
     * @param request
     * @param questionId
     * @param currentUserId
     * @param auditingDepartment
     * @param description
     * @return
     */
    @RequestMapping(value ="wz_question_transfer", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> transferSave(HttpServletRequest request, @RequestParam Long questionId, @RequestParam Long currentUserId, @RequestParam String auditingDepartment, @RequestParam String description){
        Response<Void> rs = Response.newInstance();
        if(questionId == null || currentUserId == null){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        rs = wzQuestionService.transferSave(questionId, currentUserId, auditingDepartment, description, sessionUser.getUserId(), sessionUser.getNickname());
        return rs;
    }
    
    /**
     * Title:回复-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月20日
     * @return
     */
    @RequestMapping(value ="wz_question_reply", method = RequestMethod.GET)
    @ResponseBody
    public Response<WzQuestion> replay(HttpServletRequest request, @RequestParam Long questionId){
        Response<WzQuestion> rs = wzQuestionService.get(questionId);
        return rs;
    }
    
    /**
     * Title:回复
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月20日
     * @param request
     * @param questionId
     * @param auditingDepartment
     * @param content
     * @return
     */
    @RequestMapping(value ="wz_question_reply", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> replySave(HttpServletRequest request, @RequestParam Long questionId,  @RequestParam String auditingDepartment, @RequestParam String content){
        Response<Void> rs = Response.newInstance();
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        if(questionId == null || StringUtils.isBlank(auditingDepartment) || StringUtils.isBlank(content)){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        rs = wzQuestionService.replySave(sessionUser.getAppId(), questionId, auditingDepartment, content, sessionUser.getUserId());
        
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
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        if(id == null){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        //验证是不是问政管理员
        boolean isWzAdmin = validateRole(sessionUser, CommonSysRoleEnum.WENZHENG.getCode());
        WzAppAuthorityDto wzAppAuthorityDto = wzAuthorityService.getAppAuthByAuthName(sessionUser.getAppId(), WzAuthority.NAME_REPLY_IS_LAST_PUBLISH).getData();
        if(wzAppAuthorityDto != null && wzAppAuthorityDto.getValue().equals(WzAppAuthority.VALUE1) && !isWzAdmin){
            //问政信息设置为只有总负责人发布 且当前用户不是问政管理员
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("当前用户无权限发布。");
            return rs;
        }
        rs = wzQuestionService.publishSave(new Long[]{id});
        return rs;
    }
    
    /**
     * 
     * Title:批量发布
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月23日
     * @param request
     * @param ids
     * @return
     */
    @RequestMapping(value ="wz_question_publish_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> publishBatchSave(HttpServletRequest request, @RequestParam(value="ids[]") Long[] ids){
        Response<Void> rs = Response.newInstance();
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        if(ids == null || ids.length < 1){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        //验证是不是问政管理员
        boolean isWzAdmin = validateRole(sessionUser, CommonSysRoleEnum.WENZHENG.getCode());
        WzAppAuthorityDto wzAppAuthorityDto = wzAuthorityService.getAppAuthByAuthName(sessionUser.getAppId(), WzAuthority.NAME_REPLY_IS_LAST_PUBLISH).getData();
        if(wzAppAuthorityDto != null && wzAppAuthorityDto.getValue().equals(WzAppAuthority.VALUE1) && !isWzAdmin){
            //问政信息设置为只有总负责人发布 且当前用户不是问政管理员
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("当前用户无权限发布。");
            return rs;
        }
        rs = wzQuestionService.publishSave(ids);
        return rs;
    }
    
    /**
     * Title:判断角色
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月21日
     * @param sessionUser
     * @param roleCode
     * @return
     */
    private boolean validateRole(SessionUser sessionUser, String roleCode){
        Set<SysRole> setRole = sessionUser.getRole();
        boolean result = false;
        for(SysRole role : setRole){
            if(role.getCommonRoleCode() != null && role.getCommonRoleCode().equals(roleCode)){
                result = true;
            }
        }
        return result;
    }
    
    /**
     * Title:上线
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月1日
     * @param request
     * @param ids
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="wz_question_online", method = RequestMethod.POST)
    public Response<Void> onLine(HttpServletRequest request, @RequestParam(value="ids") Long id){
        Response<Void> rs = Response.newInstance();
        if(id == null){
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("参数有误");
            return rs;
        }
        rs = wzQuestionService.updateStatus(WzQuestion.STATUS7, id);
        return rs;
    }
    
    /**
     * Title:下线
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月1日
     * @param request
     * @param ids
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="wz_question_offline", method = RequestMethod.POST)
    public Response<Void> offLine(HttpServletRequest request, @RequestParam(value="ids") Long id){
        Response<Void> rs = Response.newInstance();
        if(id == null){
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("参数有误");
            return rs;
        }
        rs = wzQuestionService.updateStatus(WzQuestion.STATUS88, id);
        return rs;
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
    public Response<Void> publishAfterSaveReply(HttpServletRequest request, @RequestParam Long questionId,  @RequestParam String auditingDepartment, @RequestParam String content){
        Response<Void> rs = Response.newInstance();
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        if(questionId == null || StringUtils.isBlank(auditingDepartment) || StringUtils.isBlank(content)){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        WzQuestion wzQuestion = wzQuestionService.get(questionId).getData();
        if(wzQuestion == null){
        	rs.setCode(ErrorCodes.FAILURE);
        	rs.setMessage("问政不存在");
        	return rs;
        }
        wzQuestion.setAuditingDepartment(auditingDepartment);
        wzQuestion.setReplyContent(content);
        wzQuestion.setUpdateTime(new Date());
        wzQuestion.setUpdator(sessionUser.getNickname());
        wzQuestion.setUpdatorId(sessionUser.getUserId());
        rs = wzQuestionService.update(wzQuestion);
        return rs;
    }
    
}
