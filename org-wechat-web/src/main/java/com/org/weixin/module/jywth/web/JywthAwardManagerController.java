package com.org.weixin.module.jywth.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.exception.BusinessException;
import org.wechat.framework.web.support.JsonResult;

import com.org.weixin.module.jywth.domain.JywthAward;
import com.org.weixin.module.jywth.service.JywthAwardService;

@Controller
@RequestMapping(value = "jywtinf")
public class JywthAwardManagerController {

	@Autowired
	private JywthAwardService jywthAwardService;
	
	private Logger logger = LoggerFactory.getLogger(JywthAwardManagerController.class);
	
	@ResponseBody
	@RequestMapping(value="raffle")
	public synchronized JsonResult getAward(String m,String c){
		JsonResult jr = new JsonResult();
		
		logger.info(String.format("接收参数：-%s->>>>>>>>>%s<<<", m,c));
		
		try {
			JywthAward jywthAward = jywthAwardService.getAward(m, c);
			jr.appendData("name", jywthAward.getName());
			jr.appendData("status", 1);
			jr.appendData("rid",jywthAward.getAwardHisId());
			return jr;
		} catch (Exception e) {
			jr.setMessage(e.getMessage());
			e.printStackTrace();
			jr.setCode(-1);
			jr.setSuccess(false);
			if(BusinessException.class.isAssignableFrom(e.getClass())){
				BusinessException busi = (BusinessException) e;
				jr.appendData("status",busi.getErrorCode());
				jr.setCode(0);
				jr.setSuccess(true);
			}
		}
		
		return jr;
	}
}
