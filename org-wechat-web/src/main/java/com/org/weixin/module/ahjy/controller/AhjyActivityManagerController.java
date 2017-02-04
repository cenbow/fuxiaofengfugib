package com.org.weixin.module.ahjy.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.web.support.JsonResult;

import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.weixin.module.ahjy.common.AhjyConstants;
import com.org.weixin.module.ahjy.domain.AhjyActivity;
import com.org.weixin.module.ahjy.qrcode.QRcodeProvider;
import com.org.weixin.module.ahjy.service.AhjyActivityService;

@Controller
@RequestMapping(value = "ahjy/1")
public class AhjyActivityManagerController{

	@Autowired
	private AhjyActivityService ahjyActivityService;
	@Autowired
	private SpyMemcachedClient memcachedClient;
	
	private final static String LOGO_PATH = "ahjy_logo.png"; 
	
	@RequestMapping(value="create_acti")
	@ResponseBody
	public JsonResult getQRcode(HttpServletRequest request,@PathVariable String accId){
		
		JsonResult jr = new JsonResult();
		String filePath = memcachedClient.get(AhjyConstants.FILE_LOCAL_URL);
		String fileUrl = memcachedClient.get(AhjyConstants.FILE_VISIT_URL);
		String qrcodeRedirectUrl = memcachedClient.get(AhjyConstants.QRCODE_SCAN_REDIRECT_URL);
		AhjyActivity ahjyActivity = ahjyActivityService.createActivity();
		String fileName = getActivityFileName(ahjyActivity.getId());
		//放到缓存中
		String memKey = fileName.split("\\.")[0];
		setActivityInMemcached(memKey,ahjyActivity);
		File file = FileUtils.getFile(filePath, fileName);
		if(file.exists()){
			jr.appendData("activity_qrcode", fileUrl+fileName);
			return jr;
		}
		qrcodeRedirectUrl = qrcodeRedirectUrl.replace("{accId}", accId).replace("{activityId}",ahjyActivity.getId().toString());
		try {
			String filestr = filePath + fileName;
			QRcodeProvider.textQrcode(qrcodeRedirectUrl, filestr);
			//QRcodeProvider.logoQrcode(qrcodeRedirectUrl, filestr, LOGO_PATH);
			jr.appendData("activity_qrcode", fileUrl+fileName);
			return jr;
		} catch (Exception e) {
			e.printStackTrace();
			jr.setCode(-1111);
			jr.setSuccess(false);
			jr.setMessage("生成二维码失败！");
			return jr;
		}
	}
	
	
	private String getActivityFileName(Long actiId){
		StringBuilder buil = new StringBuilder("ahjyActivity_");
		buil.append(actiId).append(".png");
		
		return buil.toString();
	}
	
	private AhjyActivity setActivityInMemcached(String key,AhjyActivity newAhjyActivity){
		
		AhjyActivity ahjyActivity = memcachedClient.get(key);
		
		if(null == ahjyActivity){
			memcachedClient.set(key, newAhjyActivity);
		}
		
		return memcachedClient.get(key);
	}
}
