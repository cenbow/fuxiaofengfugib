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
import com.cqliving.cloud.online.act.domain.ActVoteItem;
import com.cqliving.cloud.online.act.service.ActVoteItemService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/act")
public class ActVoteItemController extends CommonController {

    @Autowired
    private ActVoteItemService actVoteItemService;

    //列表
    @RequestMapping(value ="act_vote_itemlist")
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
		
        PageInfo<ActVoteItem> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", ActVoteItem.STATUS99);//排除逻辑删除状态
        map.put("pageInfo", actVoteItemService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allStatuss", ActVoteItem.allStatuss);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/act/act_vote_itemlist_page";
        }else{
        	return "tiles.module.act.act_vote_itemlist";
        }
    }

    //增加-查看
    @RequestMapping(value ="act_vote_itemadd", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.act.act_vote_itemdetail";
    }


    //增加-保存
    @RequestMapping(value ="act_vote_itemadd", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ActVoteItem actVoteItem){
        //ID
        actVoteItem.setId(null);
        Response<Void> res = actVoteItemService.save(actVoteItem);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="act_vote_itemupdate", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActVoteItem actVoteItem = actVoteItemService.get(id).getData();
        if(actVoteItem==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actVoteItem);
        return "tiles.module.act.act_vote_itemdetail";
    }

    //修改-保存
    @RequestMapping(value ="act_vote_itemupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ActVoteItem actVoteItem){
        Response<Void> res = Response.newInstance();
        if(actVoteItem==null || actVoteItem.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ActVoteItem sourceActVoteItem = actVoteItemService.get(actVoteItem.getId()).getData();
            if(sourceActVoteItem==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //活动投票分类表ID（act_vote_classify表主键）
            sourceActVoteItem.setActVoteClassifyId(actVoteItem.getActVoteClassifyId());
            //选项编号
            sourceActVoteItem.setNumber(actVoteItem.getNumber());
            //选项标题，后台限制最多80个字
            sourceActVoteItem.setItemTitle(actVoteItem.getItemTitle());
            //选项描述
            sourceActVoteItem.setItemDesc(actVoteItem.getItemDesc());
            //实际量
            sourceActVoteItem.setActValue(actVoteItem.getActValue());
            //初始量
            sourceActVoteItem.setInitValue(actVoteItem.getInitValue());
            //选项图片
            sourceActVoteItem.setImageUrl(actVoteItem.getImageUrl());
            //视频URL
            sourceActVoteItem.setVideoUrl(actVoteItem.getVideoUrl());
            //内容,包含HTML标签的富文本内容
            sourceActVoteItem.setContent(actVoteItem.getContent());
            //状态
            sourceActVoteItem.setStatus(actVoteItem.getStatus());
            //排序号
            sourceActVoteItem.setSortNo(actVoteItem.getSortNo());
            //创建时间
            sourceActVoteItem.setCreateTime(actVoteItem.getCreateTime());
            res= actVoteItemService.save(sourceActVoteItem);
            actVoteItem = sourceActVoteItem;
        }catch (Exception ex){
            logger.error("Save Method (Update) ActVoteItem Error : " + actVoteItem.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="act_vote_itemview")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActVoteItem actVoteItem = actVoteItemService.get(id).getData();
        if(actVoteItem==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actVoteItem);
        return "tiles.module.act.act_vote_itemview";
    }

    //删除
    @RequestMapping(value ="act_vote_itemdelete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = actVoteItemService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="act_vote_itemdelete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = actVoteItemService.deleteLogic(ids);
        return res;
    }
}
