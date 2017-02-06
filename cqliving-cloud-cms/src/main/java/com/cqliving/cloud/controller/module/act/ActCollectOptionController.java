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
import com.cqliving.cloud.online.act.domain.ActCollectOption;
import com.cqliving.cloud.online.act.service.ActCollectOptionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/act")
public class ActCollectOptionController extends CommonController {

    @Autowired
    private ActCollectOptionService actCollectOptionService;

    //列表
    @RequestMapping(value ="act_collect_option_list")
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
		
        PageInfo<ActCollectOption> pageInfo = getPageInfo(request);
        map.put("pageInfo", actCollectOptionService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/act/act_collect_option_list_page";
        }else{
        	return "tiles.module.act.act_collect_option_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="act_collect_option_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.act.act_collect_option_detail";
    }


    //增加-保存
    @RequestMapping(value ="act_collect_option_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ActCollectOption actCollectOption){
        //ID
        actCollectOption.setId(null);
        Response<Void> res = actCollectOptionService.save(actCollectOption);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="act_collect_option_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActCollectOption actCollectOption = actCollectOptionService.get(id).getData();
        if(actCollectOption==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actCollectOption);
        return "tiles.module.act.act_collect_option_detail";
    }

    //修改-保存
    @RequestMapping(value ="act_collect_option_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ActCollectOption actCollectOption){
        Response<Void> res = Response.newInstance();
        if(actCollectOption==null || actCollectOption.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ActCollectOption sourceActCollectOption = actCollectOptionService.get(actCollectOption.getId()).getData();
            if(sourceActCollectOption==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //信息收集表ID
            sourceActCollectOption.setActCollectInfoId(actCollectOption.getActCollectInfoId());
            //选项值，50个字
            sourceActCollectOption.setValue(actCollectOption.getValue());
            //创建时间
            sourceActCollectOption.setCreateTime(actCollectOption.getCreateTime());
            //创建人
            sourceActCollectOption.setCreatorId(actCollectOption.getCreatorId());
            //创建人姓名
            sourceActCollectOption.setCreator(actCollectOption.getCreator());
            //更新时间
            sourceActCollectOption.setUpdateTime(actCollectOption.getUpdateTime());
            //更新人ID
            sourceActCollectOption.setUpdatorId(actCollectOption.getUpdatorId());
            //更新人
            sourceActCollectOption.setUpdator(actCollectOption.getUpdator());
            res= actCollectOptionService.save(sourceActCollectOption);
            actCollectOption = sourceActCollectOption;
        }catch (Exception ex){
            logger.error("Save Method (Update) ActCollectOption Error : " + actCollectOption.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="act_collect_option_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActCollectOption actCollectOption = actCollectOptionService.get(id).getData();
        if(actCollectOption==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actCollectOption);
        return "tiles.module.act.act_collect_option_view";
    }

    //删除
    @RequestMapping(value ="act_collect_option_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = actCollectOptionService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="act_collect_option_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = actCollectOptionService.delete(ids);
        return res;
    }
}
