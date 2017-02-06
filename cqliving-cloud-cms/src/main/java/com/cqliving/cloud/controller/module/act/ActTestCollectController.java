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
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.act.domain.ActTestCollect;
import com.cqliving.cloud.online.act.service.ActTestCollectService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/act")
public class ActTestCollectController extends CommonController {

    @Autowired
    private ActTestCollectService actTestCollectService;

    //列表
    @RequestMapping(value ="act_test_collectlist")
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
		
        PageInfo<ActTestCollect> pageInfo = getPageInfo(request);
        map.put("pageInfo", actTestCollectService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allIsRequireds", ActTestCollect.allIsRequireds);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/act/act_test_collectlist_page";
        }else{
        	return "tiles.module.act.act_test_collectlist";
        }
    }

    //增加-查看
    @RequestMapping(value ="act_test_collectadd", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.act.act_test_collectdetail";
    }


    //增加-保存
    @RequestMapping(value ="act_test_collectadd", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ActTestCollect actTestCollect){
        //ID
        actTestCollect.setId(null);
        Response<Void> res = actTestCollectService.save(actTestCollect);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="act_test_collectupdate", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActTestCollect actTestCollect = actTestCollectService.get(id).getData();
        if(actTestCollect==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actTestCollect);
        return "tiles.module.act.act_test_collectdetail";
    }

    //修改-保存
    @RequestMapping(value ="act_test_collectupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ActTestCollect actTestCollect){
        Response<Void> res = Response.newInstance();
        if(actTestCollect==null || actTestCollect.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ActTestCollect sourceActTestCollect = actTestCollectService.get(actTestCollect.getId()).getData();
            if(sourceActTestCollect==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //活动答题表ID（act_test表主键）
            sourceActTestCollect.setActTestId(actTestCollect.getActTestId());
            //信息收集表ID（act_collect_info表主键）
            sourceActTestCollect.setActCollectInfoId(actTestCollect.getActCollectInfoId());
            //是否必填
            sourceActTestCollect.setIsRequired(actTestCollect.getIsRequired());
            //创建时间
            sourceActTestCollect.setCreateTime(actTestCollect.getCreateTime());
            res= actTestCollectService.save(sourceActTestCollect);
            actTestCollect = sourceActTestCollect;
        }catch (Exception ex){
            logger.error("Save Method (Update) ActTestCollect Error : " + actTestCollect.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="act_test_collectview")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActTestCollect actTestCollect = actTestCollectService.get(id).getData();
        if(actTestCollect==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actTestCollect);
        return "tiles.module.act.act_test_collectview";
    }

    //删除
    @RequestMapping(value ="act_test_collectdelete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = actTestCollectService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="act_test_collectdelete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = actTestCollectService.delete(ids);
        return res;
    }
}
