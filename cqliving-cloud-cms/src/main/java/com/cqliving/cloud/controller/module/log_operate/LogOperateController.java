package com.cqliving.cloud.controller.module.log_operate;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.security.domain.SysResType;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.log.domain.LogOperate;
import com.cqliving.log.service.LogOperateService;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/log_operate")
public class LogOperateController extends CommonController {

    @Autowired
    private LogOperateService logOperateService;
    
    @Autowired
    private AppInfoService appInfoService;

    //操作日志列表
    @RequestMapping(value ="log_operate_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        //不查询其他分类的日志
        searchMap.put("NOTEQ_sysResTypeId", SysResType.OTHERSYSRESTYPEID);
        //只查询显示状态的日志
        searchMap.put("EQ_type", LogOperate.TYPE0);
        
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
		
        PageInfo<LogOperate> pageInfo = getPageInfo(request);
        map.put("pageInfo", logOperateService.query(pageInfo, searchMap, sortMap));
//        SessionUser user = SessionFace.getSessionUser(request);
//        map.put("userType", SysUser.USERTYPE1.equals(user.getUsertype()));
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/log_operate/log_operate_list_page";
        }else{
        	return "tiles.module.log_operate.log_operate_list";
        }
    }

    //查看
    @RequestMapping(value ="log_operate_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        LogOperate logOperate = logOperateService.get(id);
        if(logOperate==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", logOperate);
        return "tiles.module.log_operate.log_operate_view";
    }
}