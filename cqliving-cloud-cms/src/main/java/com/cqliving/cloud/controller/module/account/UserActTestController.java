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
import com.cqliving.cloud.online.account.domain.UserActTest;
import com.cqliving.cloud.online.account.service.UserActTestService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/account")
public class UserActTestController extends CommonController {

    @Autowired
    private UserActTestService userActTestService;

    //列表
    @RequestMapping(value ="user_act_testlist")
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
		
        PageInfo<UserActTest> pageInfo = getPageInfo(request);
        map.put("pageInfo", userActTestService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/account/user_act_testlist_page";
        }else{
        	return "tiles.module.account.user_act_testlist";
        }
    }

    //增加-查看
    @RequestMapping(value ="user_act_testadd", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.account.user_act_testdetail";
    }


    //增加-保存
    @RequestMapping(value ="user_act_testadd", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,UserActTest userActTest){
        //ID
        userActTest.setId(null);
        Response<Void> res = userActTestService.save(userActTest);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="user_act_testupdate", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserActTest userActTest = userActTestService.get(id).getData();
        if(userActTest==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userActTest);
        return "tiles.module.account.user_act_testdetail";
    }

    //修改-保存
    @RequestMapping(value ="user_act_testupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,UserActTest userActTest){
        Response<Void> res = Response.newInstance();
        if(userActTest==null || userActTest.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            UserActTest sourceUserActTest = userActTestService.get(userActTest.getId()).getData();
            if(sourceUserActTest==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //用户参与活动集合表ID（user_act_list表主键）
            sourceUserActTest.setTestClassifyHistoryId(userActTest.getTestClassifyHistoryId());
            //活动答题问题表ID（act_test_question表主键）
            sourceUserActTest.setActTestQuestionId(userActTest.getActTestQuestionId());
            //活动答题答案ID（act_test_answer表主键）
            sourceUserActTest.setActTestAnswerId(userActTest.getActTestAnswerId());
            //用户ID
            sourceUserActTest.setUserId(userActTest.getUserId());
            //参与时间
            sourceUserActTest.setCreateTime(userActTest.getCreateTime());
            //答案内容
            sourceUserActTest.setAnswerContent(userActTest.getAnswerContent());
            res= userActTestService.save(sourceUserActTest);
            userActTest = sourceUserActTest;
        }catch (Exception ex){
            logger.error("Save Method (Update) UserActTest Error : " + userActTest.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="user_act_testview")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        UserActTest userActTest = userActTestService.get(id).getData();
        if(userActTest==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", userActTest);
        return "tiles.module.account.user_act_testview";
    }

    //删除
    @RequestMapping(value ="user_act_testdelete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = userActTestService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="user_act_testdelete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = userActTestService.delete(ids);
        return res;
    }
}
