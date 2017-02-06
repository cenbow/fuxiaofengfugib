package com.cqliving.cloud.controller.module.act;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.act.service.ActInfoListService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/act")
public class ActInfoListController extends CommonController {

    @Autowired
    private ActInfoListService actInfoListService;

    //列表
    @RequestMapping(value ="act_info_listlist")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value="search_GTE_startTime", required=false) Date search_GTE_startTime
        ,@RequestParam(value="search_LT_startTime", required=false) Date search_LT_startTime
        ,@RequestParam(value="search_GTE_endTime", required=false) Date search_GTE_endTime
        ,@RequestParam(value="search_LT_endTime", required=false) Date search_LT_endTime
        ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
        ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime
        ,@RequestParam(value="search_GTE_updateTime", required=false) Date search_GTE_updateTime
        ,@RequestParam(value="search_LT_updateTime", required=false) Date search_LT_updateTime
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
        //默认时间范围3个月
    	search_LT_startTime = search_LT_startTime != null ?search_LT_startTime: Calendar.getInstance().getTime();
        map.put("search_LT_startTime", search_LT_startTime);
        searchMap.put("LT_startTime", DateUtils.truncate(DateUtils.addDays(search_LT_startTime, 1), Calendar.DATE));
        search_GTE_startTime = search_GTE_startTime != null ? search_GTE_startTime:DateUtils.addMonths(search_LT_startTime, -3);
        map.put("search_GTE_startTime", search_GTE_startTime);
        searchMap.put("GTE_startTime", search_GTE_startTime);
        //默认时间范围3个月
    	search_LT_endTime = search_LT_endTime != null ?search_LT_endTime: Calendar.getInstance().getTime();
        map.put("search_LT_endTime", search_LT_endTime);
        searchMap.put("LT_endTime", DateUtils.truncate(DateUtils.addDays(search_LT_endTime, 1), Calendar.DATE));
        search_GTE_endTime = search_GTE_endTime != null ? search_GTE_endTime:DateUtils.addMonths(search_LT_endTime, -3);
        map.put("search_GTE_endTime", search_GTE_endTime);
        searchMap.put("GTE_endTime", search_GTE_endTime);
        //默认时间范围3个月
    	search_LT_createTime = search_LT_createTime != null ?search_LT_createTime: Calendar.getInstance().getTime();
        map.put("search_LT_createTime", search_LT_createTime);
        searchMap.put("LT_createTime", DateUtils.truncate(DateUtils.addDays(search_LT_createTime, 1), Calendar.DATE));
        search_GTE_createTime = search_GTE_createTime != null ? search_GTE_createTime:DateUtils.addMonths(search_LT_createTime, -3);
        map.put("search_GTE_createTime", search_GTE_createTime);
        searchMap.put("GTE_createTime", search_GTE_createTime);
        //默认时间范围3个月
    	search_LT_updateTime = search_LT_updateTime != null ?search_LT_updateTime: Calendar.getInstance().getTime();
        map.put("search_LT_updateTime", search_LT_updateTime);
        searchMap.put("LT_updateTime", DateUtils.truncate(DateUtils.addDays(search_LT_updateTime, 1), Calendar.DATE));
        search_GTE_updateTime = search_GTE_updateTime != null ? search_GTE_updateTime:DateUtils.addMonths(search_LT_updateTime, -3);
        map.put("search_GTE_updateTime", search_GTE_updateTime);
        searchMap.put("GTE_updateTime", search_GTE_updateTime);
		
        PageInfo<ActInfoList> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", ActInfoList.STATUS99);//排除逻辑删除状态
        map.put("pageInfo", actInfoListService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allTypes", ActInfoList.allTypes);
        map.put("allStatuss", ActInfoList.allStatuss);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/act/act_info_listlist_page";
        }else{
        	return "tiles.module.act.act_info_listlist";
        }
    }

    //增加-查看
    @RequestMapping(value ="act_info_listadd", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.act.act_info_listdetail";
    }


    //增加-保存
    @RequestMapping(value ="act_info_listadd", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ActInfoList actInfoList){
        //ID
        actInfoList.setId(null);
        Response<Void> res = actInfoListService.save(actInfoList);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="act_info_listupdate", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActInfoList actInfoList = actInfoListService.get(id).getData();
        if(actInfoList==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actInfoList);
        return "tiles.module.act.act_info_listdetail";
    }

    //修改-保存
    @RequestMapping(value ="act_info_listupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ActInfoList actInfoList){
        Response<Void> res = Response.newInstance();
        if(actInfoList==null || actInfoList.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ActInfoList sourceActInfoList = actInfoListService.get(actInfoList.getId()).getData();
            if(sourceActInfoList==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //客户端_ID
            sourceActInfoList.setAppId(actInfoList.getAppId());
            //活动ID（act_info表主键）
            sourceActInfoList.setActInfoId(actInfoList.getActInfoId());
            //活动类型
            sourceActInfoList.setType(actInfoList.getType());
            //状态
            sourceActInfoList.setStatus(actInfoList.getStatus());
            //外链地址
            sourceActInfoList.setLinkUrl(actInfoList.getLinkUrl());
            //活动开始时间
            sourceActInfoList.setStartTime(actInfoList.getStartTime());
            //活动结束时间
            sourceActInfoList.setEndTime(actInfoList.getEndTime());
            //创建时间
            sourceActInfoList.setCreateTime(actInfoList.getCreateTime());
            //创建人
            sourceActInfoList.setCreatorId(actInfoList.getCreatorId());
            //创建人姓名
            sourceActInfoList.setCreator(actInfoList.getCreator());
            //更新时间
            sourceActInfoList.setUpdateTime(actInfoList.getUpdateTime());
            //更新人ID
            sourceActInfoList.setUpdatorId(actInfoList.getUpdatorId());
            //更新人
            sourceActInfoList.setUpdator(actInfoList.getUpdator());
            res= actInfoListService.save(sourceActInfoList);
            actInfoList = sourceActInfoList;
        }catch (Exception ex){
            logger.error("Save Method (Update) ActInfoList Error : " + actInfoList.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="act_info_listview")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActInfoList actInfoList = actInfoListService.get(id).getData();
        if(actInfoList==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actInfoList);
        return "tiles.module.act.act_info_listview";
    }

    //删除
    @RequestMapping(value ="act_info_listdelete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
     	
     	Long userId = sessionUser.getUserId();
     	String nickName = sessionUser.getNickname();
    	
    	Response<Void> res = actInfoListService.deleteLogic(nickName,userId,id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="act_info_listdelete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
     	
     	Long userId = sessionUser.getUserId();
     	String nickName = sessionUser.getNickname();
    	
    	Response<Void> res = actInfoListService.deleteLogic(nickName,userId,ids);
        return res;
    }
}
