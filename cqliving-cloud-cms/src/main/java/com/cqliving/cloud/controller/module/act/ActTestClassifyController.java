package com.cqliving.cloud.controller.module.act;

import java.util.Date;
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
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.act.domain.ActTestClassify;
import com.cqliving.cloud.online.act.service.ActTestClassifyService;
import com.cqliving.tool.common.Response;

@Controller
@RequestMapping(value = "/module/act/common")
public class ActTestClassifyController extends CommonController {

    @Autowired
    private ActTestClassifyService actTestClassifyService;

    /**
     * Title:保存和修改
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月13日
     * @param request
     * @param map
     * @param actTestClassify
     * @return
     */
    @RequestMapping(value ="act_test_classifyadd", method = RequestMethod.POST)
    @ResponseBody
    public Response<Long> postAdd(HttpServletRequest request, Map<String, Object> map,ActTestClassify actTestClassify){
        //ID
        Response<Void> rs = Response.newInstance();
    	if(actTestClassify.getId() != null){
    		ActTestClassify classify = actTestClassifyService.get(actTestClassify.getId()).getData();
    		classify.setSubject(actTestClassify.getSubject());
    		classify.setTitle(actTestClassify.getTitle());
    		classify.setIsSetScore(actTestClassify.getIsSetScore());
    		rs = actTestClassifyService.update(classify);
    	}else{
    		actTestClassify.setId(null);
    		actTestClassify.setCreateTime(new Date());
    		rs = actTestClassifyService.save(actTestClassify);
    	}
        
        Response<Long> res = Response.newInstance();
        res.setCode(rs.getCode());
        res.setMessage(rs.getMessage());
        res.setData(actTestClassify.getId());
        if(res.getCode() < 0){
        	return new Response<Long>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }
    
    /**
     * Title:删除
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月1日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="act_test_classifydelete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map, @RequestParam(value = "id") Long id){
        Response<Void> res = actTestClassifyService.delete(id);
        return res;
    }
}
