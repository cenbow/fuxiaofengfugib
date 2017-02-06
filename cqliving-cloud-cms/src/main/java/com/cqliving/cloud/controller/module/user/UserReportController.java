package com.cqliving.cloud.controller.module.user;

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

import com.cqliving.basic.domain.Option;
import com.cqliving.basic.service.BasicService;
import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.account.domain.UserReport;
import com.cqliving.cloud.online.account.dto.UserReportDto;
import com.cqliving.cloud.online.account.service.UserReportService;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/userReport")
public class UserReportController extends CommonController {

    @Autowired
    private UserReportService userReportService;
    @Autowired
    private BasicService basicService;
    
    @Autowired
    private AppInfoService appInfoService;
    
    //列表
    @RequestMapping(value ="user_report_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
		
        PageInfo<UserReport> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", UserReport.STATUS99);//排除逻辑删除状态
        
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
        
        map.put("pageInfo", userReportService.queryForPage(pageInfo, searchMap, sortMap).getData());
        //获得举报类型
        List<Option> reportTypeList = basicService.getOptionListByType(Option.TYPECODE1);
        map.put("reportTypeList", reportTypeList);
        map.put("allSourceTypes", UserReport.allSourceTypes);
        map.put("allStatuss", UserReport.allStatuss);
        map.put("allAuditingTypes", UserReport.allAuditingTypes);
        map.put("AUDITINGTYPE0", UserReport.AUDITINGTYPE0);
        map.put("STATUS99", UserReport.STATUS99);
        map.put("allTypes", UserReport.allTypesView);
        map.put("TYPE1", UserReport.OPERATETYPE1);
        map.put("noPass", UserReport.STATUS_1);
        map.put("pass", UserReport.STATUS3);
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/user/user_report_list_page";
        }else{
        	return "tiles.module.user.user_report_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="user_report_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.user.user_report_detail";
    }


    //增加-保存
    @RequestMapping(value ="user_report_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,UserReport userReport){
        //ID
        userReport.setId(null);
        Response<Void> res = userReportService.save(userReport);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="user_report_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserReport userReport = userReportService.get(id).getData();
        if(userReport==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userReport);
        return "tiles.module.user.user_report_detail";
    }

    //修改-保存
    @RequestMapping(value ="user_report_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,UserReport userReport){
        Response<Void> res = Response.newInstance();
        if(userReport==null || userReport.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            UserReport sourceUserReport = userReportService.get(userReport.getId()).getData();
            if(sourceUserReport==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //来源APP
            sourceUserReport.setAppId(userReport.getAppId());
            //会话code
            sourceUserReport.setSessionCode(userReport.getSessionCode());
            //评论人ID
            sourceUserReport.setUserId(userReport.getUserId());
            //举报人姓名
            sourceUserReport.setName(userReport.getName());
            //评论内容,预留，用户前台暂时无填写
            sourceUserReport.setContent(userReport.getContent());
            //举报内容CODE,参照字典表
            sourceUserReport.setReportCode(userReport.getReportCode());
            //举报来源类型
            sourceUserReport.setSourceType(userReport.getSourceType());
            //举报来源ID
            sourceUserReport.setSourceId(userReport.getSourceId());
            //状态
            sourceUserReport.setStatus(userReport.getStatus());
            //审阅状态
            sourceUserReport.setAuditingType(userReport.getAuditingType());
            //创建时间
            sourceUserReport.setCreateTime(userReport.getCreateTime());
            //审核人ID
            sourceUserReport.setAuditingId(userReport.getAuditingId());
            //审核人姓名
            sourceUserReport.setAuditingtor(userReport.getAuditingtor());
            //审核时间
            sourceUserReport.setAuditingTime(userReport.getAuditingTime());
            res= userReportService.save(sourceUserReport);
            userReport = sourceUserReport;
        }catch (Exception ex){
            logger.error("Save Method (Update) UserReport Error : " + userReport.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="user_report_view")
    public String show(HttpServletRequest request, Map<String, Object> map,
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "sourceType") Byte sourceType,
            @RequestParam(value = "type") Byte type){
        UserReportDto userReport = userReportService.getByIdAndSourceType(id,sourceType,type).getData();
        if(userReport==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userReport);
        //获得举报类型
        List<Option> reportTypeList = basicService.getOptionListByType(Option.TYPECODE1);
        map.put("reportTypeList", reportTypeList);
        map.put("allSourceTypes", UserReport.allSourceTypes);
        map.put("allSTypeTitle", UserReport.allSTypeTitle);
        map.put("allStatuss", UserReport.allStatuss);
        map.put("allAuditingTypes", UserReport.allAuditingTypes);
        map.put("TYPE1", UserReport.OPERATETYPE1);
        return getReturnUrl(request,map,"tiles.module.user.user_report_view");
        //return "tiles.module.user.user_report_view";
    }

    //删除
    @RequestMapping(value ="user_report_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = userReportService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="user_report_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = userReportService.deleteLogic(ids);
        return res;
    }
    
    //审核
    @RequestMapping(value ="auditing" , method = RequestMethod.GET)
    public String to_auditing(HttpServletRequest request, Map<String, Object> map,
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "sourceType") Byte sourceType,
            @RequestParam(value = "type") Byte type){
        UserReportDto userReport = userReportService.getByIdAndSourceType(id,sourceType,type).getData();
        if(userReport==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userReport);
        //获得举报类型
        List<Option> reportTypeList = basicService.getOptionListByType(Option.TYPECODE1);
        map.put("reportTypeList", reportTypeList);
        map.put("allSourceTypes", UserReport.allSourceTypes);
        map.put("allSTypeTitle", UserReport.allSTypeTitle);
        
        map.put("TYPE1", UserReport.OPERATETYPE1);
        return getReturnUrl(request,map,"tiles.module.user.user_report_auditing");
        //return "tiles.module.user.user_report_auditing";
    }
    
    //审核
    @RequestMapping(value ="auditing", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> auditing(HttpServletRequest request, Map<String, Object> map,UserReport report){
        if(report==null||report.getId()==null){
            //没有记录
            return new Response<Void>(-1,"数据不存在!");
        }
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            report.setAuditingId(user.getUserId());
            report.setAuditingtor(StringUtils.isBlank(user.getNickname())?user.getUsername():user.getNickname());
            Response<Void> res = userReportService.auditing(report, report.getId());
            return res;
        }
        return new Response<Void>(-1,"请先登录!");
    }
    
    //审核
    @RequestMapping(value ="auditing_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> auditingBatch(HttpServletRequest request, Map<String, Object> map,UserReport report,@RequestParam("ids[]") Long[] ids){
        if(report==null||report.getId()==null){
            //没有记录
            return new Response<Void>(-1,"数据不存在!");
        }
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            report.setAuditingId(user.getUserId());
            report.setAuditingtor(StringUtils.isBlank(user.getNickname())?user.getUsername():user.getNickname());
            Response<Void> res = userReportService.auditing(report, ids);
            return res;
        }
        return new Response<Void>(-1,"请先登录!");
    }
}
