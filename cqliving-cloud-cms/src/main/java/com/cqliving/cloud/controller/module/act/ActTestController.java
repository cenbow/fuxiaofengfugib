package com.cqliving.cloud.controller.module.act;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.domain.ActTemplate;
import com.cqliving.cloud.online.act.domain.ActTest;
import com.cqliving.cloud.online.act.domain.ActTestCollect;
import com.cqliving.cloud.online.act.service.ActCollectInfoService;
import com.cqliving.cloud.online.act.service.ActInfoService;
import com.cqliving.cloud.online.act.service.ActTemplateService;
import com.cqliving.cloud.online.act.service.ActTestClassifyService;
import com.cqliving.cloud.online.act.service.ActTestCollectService;
import com.cqliving.cloud.online.act.service.ActTestService;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/module/act")
public class ActTestController extends CommonController {

	@Autowired
	private ActInfoService actInfoService;
    @Autowired
    private ActTestService actTestService;
    @Autowired
    private ActTemplateService actTemplateService;
    @Autowired
    private ActCollectInfoService actCollectInfoService;
    @Autowired
    private ActTestClassifyService actTestClassifyService;
    @Autowired
    private ActTestCollectService actTestCollectService;

    /**
     * Title:增加-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月14日
     * @param request
     * @param map
     * @param actInfoId
     * @param actInfoListId
     * @return
     */
    @RequestMapping(value ="act_testadd", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map, @RequestParam Long actInfoId, Long actInfoListId){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	map.put("actInfoId", actInfoId);
    	map.put("actInfoListId", actInfoListId);
    	ActInfo actInfo = actInfoService.get(actInfoId).getData();
    	map.put("actInfo", actInfo);
    	
		//活动收集信息集合
    	map.put("collectInfoList", actCollectInfoService.getByApp(sessionUser.getAppId()).getData());
    	//模板集合
    	map.put("templateList", actTemplateService.getByApp(sessionUser.getAppId(), ActTemplate.TYPE4).getData());
		return "tiles.module.act.act_testdetail";
    }


    /**
     * Title:增加-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月14日
     * @param request
     * @param actTest
     * @param collectInfoIds
     * @param collectInfoRequired
     * @param classifyIds
     * @param classifySortNos
     * @param startTimeTmp
     * @param endTimeTmp
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="act_testadd", method = RequestMethod.POST)
    public Response<Long> postAdd(HttpServletRequest request, ActTest actTest, 
    		@RequestParam(required = false, value="collectInfoIds") Long[] collectInfoIds,
    		@RequestParam(required = false, value="collectInfoRequired") Byte[] collectInfoRequired
    		,@RequestParam(required = false, value="classifyId") Long[] classifyIds
//    		,@RequestParam(required = false, value="title") String[] titles
//    		,@RequestParam(required = false, value="content") String[] contents
    		,@RequestParam(required = false, value="sortNo") Integer[] classifySortNos
    		,String startTimeTmp,String endTimeTmp
    		){
    	
    	Response<Long> res = Response.newInstance();
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	if(actTest.getAppId() == null && sessionUser.getAppId() == null){
    		ActInfo actInfo = actInfoService.get(actTest.getActInfoId()).getData();
    		actTest.setAppId(actInfo.getAppId());
    	}else{
            actTest.setAppId(sessionUser.getAppId());
    	}
        //ID
        actTest.setId(null);
        actTest.setActType(ActTest.ACTTYPE4);
        actTest.setCreator(sessionUser.getNickname());
        actTest.setCreatorId(sessionUser.getUserId());
        actTest.setUpdator(sessionUser.getNickname());
        actTest.setUpdatorId(sessionUser.getUserId());
        
        try {
        	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			actTest.setStartTime(format.parse(startTimeTmp));
			actTest.setEndTime(format.parse(endTimeTmp));
		} catch (ParseException e) {
			logger.debug("日期格式错误");
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("日期格式错误");
			return res;
		}
        //收集信息
        List<ActTestCollect> actTestCollectList = Lists.newArrayList();
        if(collectInfoIds != null && collectInfoRequired != null){
        	ActTestCollect actTestCollect = null;
        	int len = collectInfoIds.length;
        	for(int i = 0; i < len; i ++){
        		actTestCollect = new ActTestCollect();
        		actTestCollect.setActCollectInfoId(collectInfoIds[i]);
        		actTestCollect.setIsRequired(collectInfoRequired[i]);
        		actTestCollectList.add(actTestCollect);
        	}
        }
        Response<Void> rs = actTestService.save(actTest, classifyIds, classifySortNos, actTestCollectList);
        if(rs.getCode() < 0){
        	return new Response<Long>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setCode(rs.getCode());
        res.setData(actTest.getId());
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    /**
     * Title:修改-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月14日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="act_testupdate", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map, Long id, Long actInfoId, Long actInfoListId){
    	ActTest actTest = null;
    	if(id != null){
    		actTest = actTestService.get(id).getData();
    	}else if(actInfoId != null && actInfoListId != null){
    		actTest = actTestService.getByInfoList(actInfoId, actInfoListId).getData();
    	}
        if(actTest==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("actTest", actTest);
    	map.put("actInfoId", actTest.getActInfoId());
    	map.put("actInfoListId", actTest.getActInfoListId());
        
        SessionUser sessionUser = SessionFace.getSessionUser(request);
    	//答题所选的收集信息列表
        map.put("actTestCollentList", actTestCollectService.getByActTestId(actTest.getId()).getData());
		//活动收集信息集合
    	map.put("collectInfoList", actCollectInfoService.getByApp(sessionUser.getAppId()).getData());
    	//模板集合
    	map.put("templateList", actTemplateService.getByApp(sessionUser.getAppId(), ActTemplate.TYPE4).getData());
    	//分类集合
		map.put("classifyList", actTestClassifyService.getByActTest(actTest.getActInfoListId(), id).getData());
        return "tiles.module.act.act_testdetail";
    }

    /**
     * Title:修改-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月14日
     * @param request
     * @param map
     * @param actTest
     * @return
     */
    @RequestMapping(value ="act_testupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response<Long> postUpdate(HttpServletRequest request, Map<String, Object> map,ActTest actTest,
		@RequestParam(required = false, value="collectInfoIds") Long[] collectInfoIds,
		@RequestParam(required = false, value="collectInfoRequired") Byte[] collectInfoRequired,
		@RequestParam(required = false, value="classifyId") Long[] classifyIds,
		@RequestParam(required = false, value="sortNo") Integer[] classifySortNos,
		String startTimeTmp, String endTimeTmp
	){
        Response<Long> res = Response.newInstance();
        if(actTest==null || actTest.getId()==null){
            //没有记录
            return new Response<Long>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ActTest sourceActTest = actTestService.get(actTest.getId()).getData();
            if(sourceActTest==null){
                //没有记录
                return new Response<Long>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            Date date = new Date();
            SessionUser sessionUser = SessionFace.getSessionUser(request);
            //类型
            sourceActTest.setType(actTest.getType());
            
            try {
            	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	//活动开始时间
            	sourceActTest.setStartTime(format.parse(startTimeTmp));
            	//活动结束时间
    			sourceActTest.setEndTime(format.parse(endTimeTmp));
    		} catch (ParseException e) {
    			logger.debug("日期格式错误");
    			res.setCode(ErrorCodes.FAILURE);
    			res.setMessage("日期格式错误");
    			return res;
    		}
            //时间限制
            sourceActTest.setLimitAnswerType(actTest.getLimitAnswerType());
            //限制答题时间，当limit_answer_type=0，该值无效
            sourceActTest.setLimitAnswerTimes(actTest.getLimitAnswerTimes());
            //是否登陆后才能操作
            sourceActTest.setLoggedStatus(actTest.getLoggedStatus());
            //是否设置题目分值
            sourceActTest.setIsSetScore(actTest.getIsSetScore());
            //模板CODE（act_template表里面的code）
            sourceActTest.setActTemplateCode(actTest.getActTemplateCode());
            //更新时间
            sourceActTest.setUpdateTime(date);
            //更新人ID
            sourceActTest.setUpdatorId(sessionUser.getUserId());
            //更新人
            sourceActTest.setUpdator(sessionUser.getNickname());
            
            //收集信息
            List<ActTestCollect> actTestCollectList = Lists.newArrayList();
            if(collectInfoIds != null && collectInfoRequired != null){
            	ActTestCollect actTestCollect = null;
            	int len = collectInfoIds.length;
            	for(int i = 0; i < len; i ++){
            		actTestCollect = new ActTestCollect();
            		actTestCollect.setActCollectInfoId(collectInfoIds[i]);
            		actTestCollect.setIsRequired(collectInfoRequired[i]);
            		actTestCollectList.add(actTestCollect);
            	}
            }
            Response<Void> rs= actTestService.save(sourceActTest, classifyIds, classifySortNos, actTestCollectList);
            res.setCode(rs.getCode());
            res.setMessage(rs.getMessage());
            actTest = sourceActTest;
            res.setData(actTest.getId());
        }catch (Exception ex){
            logger.error("Save Method (Update) ActTest Error : " + actTest.toString(), ex);
            //修改失败
            return new Response<Long>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        return res;
    }
}
