package com.cqliving.cloud.controller.module.config;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

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
import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.domain.AppTemplet;
import com.cqliving.cloud.online.app.dto.AppColumnsDto;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppColumnsService;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.app.service.AppTempletService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.mapper.JsonMapper;
import com.cqliving.tool.common.Response;


@Controller
@RequestMapping(value = "/module/columns")
public class AppColumnsController extends CommonController {

    @Autowired
    private AppColumnsService appColumnsService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private AppTempletService appTempletService;

    /**
     * 列表
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月29日下午3:48:41
     */
    @RequestMapping(value ="app_columns_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
		searchMap.put("NOTEQ_status", AppColumns.STATUS99);
		
		String searchAppid = (String)searchMap.get("EQ_appId");
        //处理App查询条件以及页面上展示的app下拉框
		SessionUser user = SessionFace.getSessionUser(request);
		List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
		if(null!=appList&&appList.size()>1){
		    map.put("appList", appList);
		}
        if(StringUtils.isBlank(searchAppid)){
           if(null!=appList&&appList.size()>0){
               searchMap.put("EQ_appId", appList.get(0).getId());
           }else{
               searchMap.put("EQ_appId", user.getAppId());
           }
        }
        Map<String, Boolean> sortMap = new LinkedHashMap<String, Boolean>();
        sortMap.put("sortNo", true);
        sortMap.put("id", true);
        
        List<AppColumnsDto> list = appColumnsService.getList(searchMap, sortMap).getData();
        
        JsonMapper jsonMapper = new JsonMapper();
        map.put("resources",jsonMapper.toJson(list));
        map.put("allColumnsTypes",jsonMapper.toJson(AppColumns.allColumnsTypes));
        map.put("viewMap",jsonMapper.toJson(AppColumns.viewMap));
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/cfg/app_columns_list_page";
        }else{
        	return "tiles.module.cfg.app_columns_list";
        }
    }

    /**
     * 跳转查询模板页面
     * @Description
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月18日下午3:43:44
     */
    @RequestMapping(value ="/common/app_templet")
    public String toTemplet(HttpServletRequest request, Map<String, Object> map,AppColumns appColumns,Byte columnsType,Long appId,Boolean flag){
        PageInfo<AppTemplet> templetPage = new PageInfo<AppTemplet>(Integer.MAX_VALUE, 1);
        Map<String, Boolean> orderBys = new HashMap<String, Boolean>();
        orderBys.put("id", false);
        Map<String, Object> searchMap = new TreeMap<String, Object>();
        searchMap.put("EQ_appId", null==appId ? SessionFace.getSessionUser(request).getAppId() : appId);
        if(AppColumns.COLUMNSTYPE0.equals(columnsType)){
            searchMap.put("EQ_type", AppTemplet.TYPE1);
        }else if(AppColumns.COLUMNSTYPE2.equals(columnsType)){
            searchMap.put("EQ_type", AppTemplet.TYPE2);
        }else if(AppColumns.COLUMNSTYPE3.equals(columnsType)){
            searchMap.put("EQ_type", AppTemplet.TYPE3);
        }
        appTempletService.queryForPage(templetPage, searchMap, orderBys);
        if(null!=templetPage&&null!=templetPage.getPageResults()&&templetPage.getPageResults().size()>0){
            map.put("templetList", templetPage.getPageResults());
        }
        map.put("item", appColumns);
        map.put("flag", flag);
        map.put("COLUMNSTYPE3", AppColumns.COLUMNSTYPE3);
        return "/module/cfg/app_templet";
    }
    
    /**
     * 增加-查看
     * @Description 添加子栏目需要把当前id，code传入，作为即将添加这个栏目的父栏目和 code
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月29日下午3:43:44
     */
    @RequestMapping(value ="app_columns_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map,AppColumns appColumns){
        appColumns.setImageName(getUUID().toLowerCase());
        map.put("item", appColumns);
        /** 平台可见类型 */
        map.put("allPlViewTypes", AppColumns.allPlViewTypes);
        map.put("COLUMNSTYPE0", AppColumns.COLUMNSTYPE0);
        map.put("COLUMNSTYPE2", AppColumns.COLUMNSTYPE2);
        map.put("COLUMNSTYPE3", AppColumns.COLUMNSTYPE3);
        /** 栏目类型 */
        map.put("allColumnsTypes", AppColumns.allColumnsTypes);
        /** 状态 */
        map.put("allStatuss", AppColumns.viewMap);
        /** 允许评论 */
        map.put("allCommentTypes", AppColumns.allCommentTypes);
        /** 需审核 */
        map.put("allValidateTypes", AppColumns.allValidateTypes);
        /** 列表显示日期 */
        map.put("allViewDates", AppColumns.allViewDates);
        /** 显示浏览数 */
        map.put("allViewCountTypes", AppColumns.allViewCountTypes);
        /** 显示评论数 */
        map.put("allViewReplyCounts", AppColumns.allViewReplyCounts);
        return "tiles.module.cfg.app_columns_detail";
    }

    /**
     * 增加-保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月29日下午3:44:56
     */
    @RequestMapping(value ="app_columns_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,
                                AppColumns appColumns){
        //ID
        appColumns.setId(null);
        //创建时间
        appColumns.setCreateTime(new Date());
        //更新时间
        appColumns.setUpdateTime(appColumns.getCreateTime());
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            //创建人ID
            appColumns.setCreatorId(user.getUserId());
            //创建人名称
            appColumns.setCreator(StringUtils.isBlank(user.getNickname())?user.getUsername():user.getNickname());
            //更新人ID
            appColumns.setUpdatorId(user.getUserId());
            //updator
            appColumns.setUpdator(appColumns.getCreator());
        }
        // 叶子节点
        appColumns.setLeafType(AppColumns.LEAFTYPE1);
        appColumns.setPlViewType(AppColumns.PLVIEWTYPE1+","+AppColumns.PLVIEWTYPE2);
        appColumns.setSortNo(1);
        appColumns.setAppId(null==appColumns.getAppId()?user.getAppId():appColumns.getAppId());
        if(AppColumns.COLUMNSTYPE2.equals(appColumns.getColumnsType())){
            appColumns.setTempletCode("NOMAL_LINK");
        }
        //默认不展示
        appColumns.setStatus(AppColumns.STATUS88);
        appColumns.setViewDate(appColumns.getViewDate()==null?AppColumns.VIEWDATE0:appColumns.getViewDate());
        appColumns.setViewReplyCount(appColumns.getViewReplyCount()==null?AppColumns.VIEWREPLYCOUNT0:appColumns.getViewReplyCount());
        appColumns.setViewCountType(appColumns.getViewCountType()==null?AppColumns.VIEWCOUNTTYPE0:appColumns.getViewCountType());
        Response<Void> res = appColumnsService.saveColumns(appColumns);
        if(res.getCode() < 0){
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="app_columns_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        AppColumns appColumns = appColumnsService.get(id).getData();
        if(appColumns==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("allPlViewTypes", AppColumns.allPlViewTypes);
        map.put("COLUMNSTYPE0", AppColumns.COLUMNSTYPE0);
        map.put("COLUMNSTYPE2", AppColumns.COLUMNSTYPE2);
        map.put("COLUMNSTYPE3", AppColumns.COLUMNSTYPE3);
        appColumns.setImageName(getUUID().toLowerCase());
        map.put("item", appColumns);
        /** 栏目类型 */
        map.put("allColumnsTypes", AppColumns.allColumnsTypes);
        /** 状态 */
        map.put("allStatuss", AppColumns.viewMap);
        /** 允许评论 */
        map.put("allCommentTypes", AppColumns.allCommentTypes);
        /** 需审核 */
        map.put("allValidateTypes", AppColumns.allValidateTypes);
        /** 列表显示日期 */
        map.put("allViewDates", AppColumns.allViewDates);
        /** 显示浏览数 */
        map.put("allViewCountTypes", AppColumns.allViewCountTypes);
        /** 显示评论数 */
        map.put("allViewReplyCounts", AppColumns.allViewReplyCounts);
        return "tiles.module.cfg.app_columns_detail";
    }

    //修改-保存
    @RequestMapping(value ="app_columns_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,
                                AppColumns appColumns){
        Response<Void> res = Response.newInstance();
        if(appColumns==null || appColumns.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            AppColumns sourceAppColumns = appColumnsService.get(appColumns.getId()).getData();
            if(sourceAppColumns==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            appColumns.setCreateTime(sourceAppColumns.getCreateTime());
            appColumns.setCreator(sourceAppColumns.getCreator());
            appColumns.setCreatorId(sourceAppColumns.getCreatorId());
            appColumns.setCode(sourceAppColumns.getCode());
            appColumns.setParentCode(sourceAppColumns.getParentCode());
            appColumns.setParentId(sourceAppColumns.getParentId());
            appColumns.setAppId(null==appColumns.getAppId()?sourceAppColumns.getAppId():appColumns.getAppId());
            appColumns.setVersionNo(sourceAppColumns.getVersionNo());
            appColumns.setSortNo(sourceAppColumns.getSortNo());
            appColumns.setLeafType(sourceAppColumns.getLeafType());
            appColumns.setUpdateTime(new Date());
            appColumns.setPlViewType(StringUtils.isNotBlank(appColumns.getPlViewType())?appColumns.getPlViewType():sourceAppColumns.getPlViewType());
            appColumns.setStatus(null==appColumns.getStatus()?sourceAppColumns.getStatus():appColumns.getStatus());
            appColumns.setColumnsType(null==appColumns.getColumnsType()?sourceAppColumns.getColumnsType():appColumns.getColumnsType());
            //appColumns.setColumnsUrl(StringUtils.isNotBlank(appColumns.getColumnsUrl())?appColumns.getColumnsUrl():sourceAppColumns.getColumnsUrl());
            appColumns.setCommentType(null==appColumns.getCommentType()?sourceAppColumns.getCommentType():appColumns.getCommentType());
            appColumns.setValidateType(null==appColumns.getValidateType()?sourceAppColumns.getValidateType():appColumns.getValidateType());
            appColumns.setViewDate(null==appColumns.getViewDate()?sourceAppColumns.getViewDate():appColumns.getViewDate());
            appColumns.setViewCountType(null==appColumns.getViewCountType()?sourceAppColumns.getViewCountType():appColumns.getViewCountType());
            appColumns.setViewReplyCount(null==appColumns.getViewReplyCount()?sourceAppColumns.getViewReplyCount():appColumns.getViewReplyCount());
            appColumns.setTempletCode(StringUtils.isNotBlank(appColumns.getTempletCode())?appColumns.getTempletCode():sourceAppColumns.getTempletCode());
            appColumns.setStatus(null==appColumns.getStatus()?AppColumns.STATUS88:appColumns.getStatus());
            if(sourceAppColumns.getImageUrl().equals(appColumns.getImageUrl())){
                appColumns.setImageName(sourceAppColumns.getImageName());
            }
            
            SessionUser user = SessionFace.getSessionUser(request);
            if(null!=user){
                //更新人ID
            	appColumns.setUpdatorId(user.getUserId());
                //updator
            	appColumns.setUpdator(StringUtils.isBlank(user.getNickname())?user.getUsername():user.getNickname());
            }
            if(AppColumns.COLUMNSTYPE2.equals(appColumns.getColumnsType())){
                appColumns.setTempletCode("NOMAL_LINK");
            }
            res = appColumnsService.updateColumns(appColumns);
            if(res.getCode() < 0){
                return new Response<Void>(-1,StringUtils.isNotBlank(res.getMessage())?res.getMessage():"修改失败!");
            }
        }catch (Exception ex){
            logger.error("Save Method (Update) AppColumns Error : " + appColumns.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("修改成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="app_columns_view")
    public String show(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        AppColumns appColumns = appColumnsService.get(id).getData();
        if(appColumns==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("appid", appColumns.getAppId());
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList&&appList.size()>1){
            map.put("appList", appList);
        }
        map.put("item", appColumns);
        map.put("allPlViewTypes", AppColumns.allPlViewTypes);
        return "tiles.module.cfg.app_columns_view";
    }
    
    //删除
    @RequestMapping(value ="app_columns_delete")
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = Response.newInstance();
        if(id==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        AppColumns appColumns = new AppColumns();
        appColumns.setId(id);
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
           //更新人ID
           appColumns.setUpdatorId(user.getUserId());
           //updator
           appColumns.setUpdator(user.getNickname());
        }
        res = appColumnsService.delete(appColumns);
        return res;
    }
    
    //发送
    @RequestMapping(value ="send")
    @ResponseBody
    public Response<Void> send(HttpServletRequest request, Map<String, Object> map,Long appId){
        Response<Void> res = Response.newInstance();
        res = appColumnsService.send(appId==null?SessionFace.getSessionUser(request).getAppId():appId);
        return res;
    }
    
    //修改排序和结构
    @RequestMapping(value ="update_sort")
    @ResponseBody
    public Response<Void> sort(HttpServletRequest request, Map<String, Object> map,
                           @RequestParam(value = "ids[]") Long[] ids,
                           @RequestParam(value = "sortNums[]") Integer[] sortNums,
                           @RequestParam(value = "parentIds[]") Long[] parentIds){
    	
    	return appColumnsService.sort(ids, sortNums, parentIds);
        
    }
    
    /**
     * 获取uuid
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月19日下午7:38:28
     */
    private String getUUID(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "1")+new Date().getTime();
        return uuid;
    }
}