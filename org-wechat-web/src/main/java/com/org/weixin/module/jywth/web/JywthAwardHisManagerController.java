package com.org.weixin.module.jywth.web;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.utils.Dates;
import org.wechat.framework.web.support.JsonResult;

import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.encrypt.Crypt;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.google.common.collect.Maps;
import com.org.common.SessionFace;
import com.org.weixin.module.jywth.common.JYWTHConstants;
import com.org.weixin.module.jywth.domain.JywthAwardHis;
import com.org.weixin.module.jywth.service.JywthAwardHisService;

@Controller
@RequestMapping(value = "jywtinf")
public class JywthAwardHisManagerController {

	@Autowired
	private JywthAwardHisService jywthAwardHisService;
	@Autowired
	private SpyMemcachedClient memcachedClient;
	
	private final static String STATISTICS = "/module/jywth/report";
	private final static String SECURITY = "/module/jywth/security";
	
	//领取奖品
	@RequestMapping(value="raffletrue")
	@ResponseBody
	public JsonResult takeAward(HttpServletRequest request,Long rid){
		
		JsonResult jr = new JsonResult();
		
		try {
			jywthAwardHisService.takeAward(rid);
			
			jr.appendData("status", 0);
			return jr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		jr.setCode(-1);
		jr.setSuccess(false);
		jr.appendData("status", 1);
		return jr;
	}
	
	@RequestMapping(value="statistics")
	public String statistics(HttpServletRequest request,String dateStr,String exchangeCode){
		
		String token = (String) SessionFace.getAttribute(request, SessionFace.JYWTH_TOKEN_KEY);
		
		if(StringUtil.isEmpty(token)){
			return "redirect:/jywtinf/settoken.html";
		}
		
		Date now = Dates.now();
		
		if(!StringUtil.isEmpty(dateStr)){
			try {
				now = DateUtil.parseDate(dateStr, DateUtil.FORMAT_YYYY_MM_DD);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		Map<String,String> searchMap = Maps.newHashMap();
		Map<String,Boolean> sortMap = Maps.newHashMap();
		searchMap.put("EQ_dateStr", DateUtil.formatDate(now, DateUtil.FORMAT_YYYY_MM_DD));
		searchMap.put("EQ_exchangeCode", exchangeCode);
		sortMap.put("awardTime",false);
		sortMap.put("takeTime",false);
		
		request.setAttribute("awardList",jywthAwardHisService.queryAwardByConditions(searchMap, sortMap));
		request.setAttribute("allstatus", JywthAwardHis.allTakeStatuss);
		request.setAttribute("awardNumDaily",jywthAwardHisService.queryAwardByDate(now));
		request.setAttribute("verifyAwardNumDaily",jywthAwardHisService.queryVerifyAwardByDate(now));
		request.setAttribute("dateStr", DateUtil.formatDate(now, DateUtil.FORMAT_YYYY_MM_DD));
		
		String error = request.getParameter("error_msg");
		
		request.setAttribute("error_msg",JYWTHConstants.errorMap.get(StringUtil.stringToInteger(error)));
		return STATISTICS;
	}
	
	@RequestMapping(value="getaward")
	public String getaward(HttpServletRequest request,String exchangeCode){
		
        String token = (String) SessionFace.getAttribute(request, SessionFace.JYWTH_TOKEN_KEY);
		
		if(StringUtil.isEmpty(token)){
			return "redirect:/jywtinf/settoken.html";
		}
	    jywthAwardHisService.verifyAward(exchangeCode);
		
		return "redirect:statistics.html";
	}
	
	@RequestMapping(value="settoken")
	public String setToken(HttpServletRequest request,String pwdtoken){
		
         String token = memcachedClient.get(JYWTHConstants.JYWTH_STATICTIS_TOKEN);
		
		if(StringUtil.isEmpty(pwdtoken)){
			  request.setAttribute("error_msg","密码不能为空");
              return SECURITY;
		}
		
		if(StringUtil.isEmpty(token)){
			request.setAttribute("error_msg","请配置密码");
			return SECURITY;
		}
		
		token = Crypt.MD5(token);
		pwdtoken = Crypt.MD5(pwdtoken);
		
		if(!token.equals(pwdtoken)){
			request.setAttribute("error_msg","密码错误");
               return SECURITY;
		}
		SessionFace.setAttribute(request, SessionFace.JYWTH_TOKEN_KEY, pwdtoken);
		return "redirect:statistics.html";
	}
}
