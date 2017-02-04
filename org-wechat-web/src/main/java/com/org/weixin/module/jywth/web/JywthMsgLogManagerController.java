package com.org.weixin.module.jywth.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.web.support.JsonResult;

import com.cqliving.tool.common.util.PhoneUtil;
import com.org.weixin.module.jywth.domain.JywthMsgLog;
import com.org.weixin.module.jywth.service.JywthAwardHisService;
import com.org.weixin.module.jywth.service.JywthMsgLogService;

@Controller
@RequestMapping(value = "jywtinf")
public class JywthMsgLogManagerController {

	@Autowired
	private JywthMsgLogService jywthMsgLogService;
	@Autowired
	JywthAwardHisService jywthAwardHisService;
	
	private Logger logger = LoggerFactory.getLogger(JywthMsgLogManagerController.class);
	
	@RequestMapping(value="sendcode")
	@ResponseBody
    public synchronized JsonResult sendCodeSMS(HttpServletRequest reuqest,@RequestParam  String m){
		JsonResult jr = new JsonResult();
		
		try {
			
			logger.info(">>>>>>>>>>>>>>>>发送验证码请求参数："+m);
			
			if(!PhoneUtil.validatePhone(m)){
				jr.setCode(-1);
				jr.setSuccess(false);
				jr.setMessage("请输入正确的手机号");
				return jr;
			}
			
			if(jywthAwardHisService.hasGetAwardToday(m)){
				jr.setCode(-1);
				jr.setSuccess(false);
				jr.setMessage("你今天已领取过红包啦");
				return jr;
			}
			
			JywthMsgLog jywthMsgLog = jywthMsgLogService.sendSmsCode(m);
			
			jr.setCode(jywthMsgLog.getIsSuccess());
			jr.setSuccess(jywthMsgLog.getIsSuccess() == JywthMsgLog.ISSUCCESS0 ? true : false);
		} catch (Exception e) {
			jr.setCode(-1);
			jr.setSuccess(false);
			jr.setMessage("短信发送失败");
			e.printStackTrace();
		}
		return jr;
    }
	
	@RequestMapping(value="authcode")
	@ResponseBody
	public synchronized JsonResult sendAuthCodeSms(HttpServletRequest reuqest,String phone,String code){
		JsonResult jr = new JsonResult();
		
		try {
			JywthMsgLog jywthMsgLog = jywthMsgLogService.sendAuthCodeSms(phone, code);
			
			jr.setCode(jywthMsgLog.getIsSuccess());
			jr.setSuccess(jywthMsgLog.getIsSuccess() == JywthMsgLog.ISSUCCESS0 ? true : false);
			jr.appendData("smsResult",jywthMsgLog);
		} catch (Exception e) {
			jr.setCode(-1);
			jr.setSuccess(false);
			jr.setMessage("短信发送失败");
			e.printStackTrace();
		}
		return jr;
	}
}
