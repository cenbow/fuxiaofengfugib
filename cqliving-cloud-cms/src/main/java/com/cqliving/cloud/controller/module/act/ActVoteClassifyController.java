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
import com.cqliving.cloud.online.act.domain.ActVoteClassify;
import com.cqliving.cloud.online.act.service.ActVoteClassifyService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/act")
public class ActVoteClassifyController extends CommonController {

    @Autowired
    private ActVoteClassifyService actVoteClassifyService;

    //列表
    @RequestMapping(value ="act_vote_classifylist")
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
		
        PageInfo<ActVoteClassify> pageInfo = getPageInfo(request);
        map.put("pageInfo", actVoteClassifyService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/act/act_vote_classifylist_page";
        }else{
        	return "tiles.module.act.act_vote_classifylist";
        }
    }

    //增加-查看
    @RequestMapping(value ="act_vote_classifyadd", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.act.act_vote_classifydetail";
    }


    //增加-保存
    @RequestMapping(value ="act_vote_classifyadd", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ActVoteClassify actVoteClassify){
        //ID
        actVoteClassify.setId(null);
        Response<Void> res = actVoteClassifyService.save(actVoteClassify);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="act_vote_classifyupdate", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActVoteClassify actVoteClassify = actVoteClassifyService.get(id).getData();
        if(actVoteClassify==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actVoteClassify);
        return "tiles.module.act.act_vote_classifydetail";
    }

    //修改-保存
    @RequestMapping(value ="act_vote_classifyupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ActVoteClassify actVoteClassify){
        Response<Void> res = Response.newInstance();
        if(actVoteClassify==null || actVoteClassify.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ActVoteClassify sourceActVoteClassify = actVoteClassifyService.get(actVoteClassify.getId()).getData();
            if(sourceActVoteClassify==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //活动ID（act_info表主键）
            sourceActVoteClassify.setActInfoId(actVoteClassify.getActInfoId());
            //活动集合表ID（act_info_list表主键）
            sourceActVoteClassify.setActInfoListId(actVoteClassify.getActInfoListId());
            //活动投票表ID（act_vote表主键）
            sourceActVoteClassify.setActVoteId(actVoteClassify.getActVoteId());
            //分类标题，不超过8个字
            sourceActVoteClassify.setTitle(actVoteClassify.getTitle());
            //分类主题，不超过50个字 
            sourceActVoteClassify.setSubject(actVoteClassify.getSubject());
            //排序号
            sourceActVoteClassify.setSortNo(actVoteClassify.getSortNo());
            //创建时间
            sourceActVoteClassify.setCreateTime(actVoteClassify.getCreateTime());
            res= actVoteClassifyService.save(sourceActVoteClassify);
            actVoteClassify = sourceActVoteClassify;
        }catch (Exception ex){
            logger.error("Save Method (Update) ActVoteClassify Error : " + actVoteClassify.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="act_vote_classifyview")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActVoteClassify actVoteClassify = actVoteClassifyService.get(id).getData();
        if(actVoteClassify==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actVoteClassify);
        return "tiles.module.act.act_vote_classifyview";
    }

    //删除
    @RequestMapping(value ="act_vote_classifydelete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = actVoteClassifyService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="act_vote_classifydelete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = actVoteClassifyService.delete(ids);
        return res;
    }
}
