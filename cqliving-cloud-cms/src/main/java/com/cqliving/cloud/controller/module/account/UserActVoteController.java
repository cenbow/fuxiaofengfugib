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
import com.cqliving.cloud.online.account.domain.UserActVote;
import com.cqliving.cloud.online.account.service.UserActVoteService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/account")
public class UserActVoteController extends CommonController {

    @Autowired
    private UserActVoteService userActVoteService;

    //列表
    @RequestMapping(value ="user_act_votelist")
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
		
        PageInfo<UserActVote> pageInfo = getPageInfo(request);
        map.put("pageInfo", userActVoteService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/account/user_act_votelist_page";
        }else{
        	return "tiles.module.account.user_act_votelist";
        }
    }

    //增加-查看
    @RequestMapping(value ="user_act_voteadd", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.account.user_act_votedetail";
    }


    //增加-保存
    @RequestMapping(value ="user_act_voteadd", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,UserActVote userActVote){
        //ID
        userActVote.setId(null);
        Response<Void> res = userActVoteService.save(userActVote);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="user_act_voteupdate", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserActVote userActVote = userActVoteService.get(id).getData();
        if(userActVote==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userActVote);
        return "tiles.module.account.user_act_votedetail";
    }

    //修改-保存
    @RequestMapping(value ="user_act_voteupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,UserActVote userActVote){
        Response<Void> res = Response.newInstance();
        if(userActVote==null || userActVote.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            UserActVote sourceUserActVote = userActVoteService.get(userActVote.getId()).getData();
            if(sourceUserActVote==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //用户参与活动集合表ID（user_act_list表主键）
            sourceUserActVote.setUserActListId(userActVote.getUserActListId());
            //活动投票分类表ID（act_vote_classify表主键）
            sourceUserActVote.setActVoteClassifyId(userActVote.getActVoteClassifyId());
            //活动分类选项表ID（act_vote_item表主键）
            sourceUserActVote.setActVoteItemId(userActVote.getActVoteItemId());
            //用户ID
            sourceUserActVote.setUserId(userActVote.getUserId());
            //创建时间
            sourceUserActVote.setCreateTime(userActVote.getCreateTime());
            res= userActVoteService.save(sourceUserActVote);
            userActVote = sourceUserActVote;
        }catch (Exception ex){
            logger.error("Save Method (Update) UserActVote Error : " + userActVote.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="user_act_voteview")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserActVote userActVote = userActVoteService.get(id).getData();
        if(userActVote==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userActVote);
        return "tiles.module.account.user_act_voteview";
    }

    //删除
    @RequestMapping(value ="user_act_votedelete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = userActVoteService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="user_act_votedelete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = userActVoteService.delete(ids);
        return res;
    }
}
