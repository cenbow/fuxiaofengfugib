package com.cqliving.cloud.controller.module.account;

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
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.account.domain.UserActCollecInfo;
import com.cqliving.cloud.online.account.service.UserActCollecInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/account")
public class UserActCollecInfoController extends CommonController {

    @Autowired
    private UserActCollecInfoService userActCollecInfoService;

    //列表
    @RequestMapping(value ="user_act_collec_infolist")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
        ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
        //默认时间范围3个月
    	search_LT_createTime = search_LT_createTime != null ?search_LT_createTime: Calendar.getInstance().getTime();
        map.put("search_LT_createTime", search_LT_createTime);
        searchMap.put("LT_createTime", DateUtils.truncate(DateUtils.addDays(search_LT_createTime, 1), Calendar.DATE));
        search_GTE_createTime = search_GTE_createTime != null ? search_GTE_createTime:DateUtils.addMonths(search_LT_createTime, -3);
        map.put("search_GTE_createTime", search_GTE_createTime);
        searchMap.put("GTE_createTime", search_GTE_createTime);
		
        PageInfo<UserActCollecInfo> pageInfo = getPageInfo(request);
        map.put("pageInfo", userActCollecInfoService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/account/user_act_collec_infolist_page";
        }else{
        	return "tiles.module.account.user_act_collec_infolist";
        }
    }

    //增加-查看
    @RequestMapping(value ="user_act_collec_infoadd", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.account.user_act_collec_infodetail";
    }


    //增加-保存
    @RequestMapping(value ="user_act_collec_infoadd", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,UserActCollecInfo userActCollecInfo){
        //ID
        userActCollecInfo.setId(null);
        Response<Void> res = userActCollecInfoService.save(userActCollecInfo);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="user_act_collec_infoupdate", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserActCollecInfo userActCollecInfo = userActCollecInfoService.get(id).getData();
        if(userActCollecInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userActCollecInfo);
        return "tiles.module.account.user_act_collec_infodetail";
    }

    //修改-保存
    @RequestMapping(value ="user_act_collec_infoupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,UserActCollecInfo userActCollecInfo){
        Response<Void> res = Response.newInstance();
        if(userActCollecInfo==null || userActCollecInfo.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            UserActCollecInfo sourceUserActCollecInfo = userActCollecInfoService.get(userActCollecInfo.getId()).getData();
            if(sourceUserActCollecInfo==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //用户参与活动集合表ID（user_act_list表主键）
            sourceUserActCollecInfo.setUserActListId(userActCollecInfo.getUserActListId());
            //活动集合表ID，（act_info_list表主键）
            sourceUserActCollecInfo.setActInfoListId(userActCollecInfo.getActInfoListId());
            //活动信息收集表ID，（act_collect_info表主键）
            sourceUserActCollecInfo.setActCollectInfoId(userActCollecInfo.getActCollectInfoId());
            //活动信息收集选项表ID，即问题答案（act_collect_info_option表主键）。当收集问题类型为（2:单选,3:多选,4:下拉列表）时，该值有效。
            sourceUserActCollecInfo.setActCollectOptionId(userActCollecInfo.getActCollectOptionId());
            //用户ID
            sourceUserActCollecInfo.setUserId(userActCollecInfo.getUserId());
            //活动信息收集内容，当收集问题类型为（1:填空）时，该值有效。
            sourceUserActCollecInfo.setValue(userActCollecInfo.getValue());
            //创建时间
            sourceUserActCollecInfo.setCreateTime(userActCollecInfo.getCreateTime());
            res= userActCollecInfoService.save(sourceUserActCollecInfo);
            userActCollecInfo = sourceUserActCollecInfo;
        }catch (Exception ex){
            logger.error("Save Method (Update) UserActCollecInfo Error : " + userActCollecInfo.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="user_act_collec_infoview")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserActCollecInfo userActCollecInfo = userActCollecInfoService.get(id).getData();
        if(userActCollecInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userActCollecInfo);
        return "tiles.module.account.user_act_collec_infoview";
    }

    //删除
    @RequestMapping(value ="user_act_collec_infodelete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = userActCollecInfoService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="user_act_collec_infodelete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = userActCollecInfoService.delete(ids);
        return res;
    }
}
