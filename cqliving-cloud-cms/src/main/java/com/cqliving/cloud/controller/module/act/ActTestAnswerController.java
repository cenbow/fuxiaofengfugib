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
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.act.domain.ActTestAnswer;
import com.cqliving.cloud.online.act.service.ActTestAnswerService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/act/common")
public class ActTestAnswerController extends CommonController {

    @Autowired
    private ActTestAnswerService actTestAnswerService;

    //列表
    @RequestMapping(value ="act_test_answerlist")
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
		
        PageInfo<ActTestAnswer> pageInfo = getPageInfo(request);
        map.put("pageInfo", actTestAnswerService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allTypes", ActTestAnswer.allTypes);
        map.put("allIsRightAnswers", ActTestAnswer.allIsRightAnswers);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/act/act_test_answerlist_page";
        }else{
        	return "tiles.module.act.act_test_answerlist";
        }
    }

    //增加-查看
    @RequestMapping(value ="act_test_answeradd", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.act.act_test_answerdetail";
    }


    /**
     * Title:增加-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月16日
     * @param request
     * @param map
     * @param actTestAnswer
     * @return
     */
    @RequestMapping(value ="act_test_answeradd", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ActTestAnswer actTestAnswer){
        //ID
        actTestAnswer.setId(null);
        actTestAnswer.setType(ActTestAnswer.TYPE0);
        actTestAnswer.setCreateTime(new Date());
        Response<Void> res = actTestAnswerService.saveAndModify(actTestAnswer);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    /**
     * Title:修改-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月16日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="act_test_answerupdate", method = RequestMethod.GET)
    public Response<ActTestAnswer> update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<ActTestAnswer> res = actTestAnswerService.get(id);
        if(res.getData()==null){
            //没有记录
            res.setCode(ErrorCodes.FAILURE);
            res.setMessage(Constant.I18nMessage.RECORD_NOT_FOUND);
        }
        return res;
    }

    /**
     * Title:修改-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月16日
     * @param request
     * @param map
     * @param actTestAnswer
     * @return
     */
    @RequestMapping(value ="act_test_answerupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ActTestAnswer actTestAnswer){
        Response<Void> res = Response.newInstance();
        if(actTestAnswer==null || actTestAnswer.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        ActTestAnswer sourceActTestAnswer = actTestAnswerService.get(actTestAnswer.getId()).getData();
        if(sourceActTestAnswer==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }

        //答案
        sourceActTestAnswer.setAnswer(actTestAnswer.getAnswer());
        //答案描述
        sourceActTestAnswer.setAnswerDesc(actTestAnswer.getAnswerDesc());
        //答案图片，多个用,分隔
        sourceActTestAnswer.setImageUrl(actTestAnswer.getImageUrl());
        //答案类型
        sourceActTestAnswer.setType(ActTestAnswer.TYPE0);
        //是否正确答案
        sourceActTestAnswer.setIsRightAnswer(actTestAnswer.getIsRightAnswer());
        //排序号
        sourceActTestAnswer.setSortNo(actTestAnswer.getSortNo());
        res= actTestAnswerService.saveAndModify(sourceActTestAnswer);
        actTestAnswer = sourceActTestAnswer;
        return res;
    }

    /**
     * Title:删除
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月16日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="act_test_answerdelete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = actTestAnswerService.delete(id);
        return res;
    }
}
