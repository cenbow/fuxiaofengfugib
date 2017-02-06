/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.controller.common;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.online.app.domain.AppQiniu;
import com.cqliving.cloud.online.app.service.AppQiniuService;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.cloud.online.info.service.InfoFileService;
import com.cqliving.framework.utils.Dates;
import com.cqliving.qiniu.util.QiNiuToken;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;
import com.qiniu.util.StringMap;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月24日
 */
@Controller
@RequestMapping(value="common/qiniu")
public class QiNiuController extends CommonController{
	
	 @Autowired
	 private AppQiniuService appQiniuService;
	 @Autowired
	 private InfoFileService infoFileService;
	 
	//获取TOKEN
    @RequestMapping(value ="token")
    public String token(HttpServletRequest request, HttpServletResponse response,@RequestParam String type,String appId){
    	
    	AppQiniu appQiniu = this.getByAppId(request,StringUtil.stringToLong(appId));
    	
    	if(null == appQiniu)return null;
    	
    	String bucketname=appQiniu.getBucketName();
    	StringMap strategy= new StringMap();
    	
    	strategy.put("scope", bucketname).put("deadline", DateUtil.addMinute(Dates.now(),60).getTime())
    	.put("persistentOps", String.format("avthumb/%s", type));
    	
    	String token = QiNiuToken.getUpToken(bucketname,strategy);
    	//{"uptoken":"xxx:xxx:xxx"}	
    	Map<String,String> map = Maps.newHashMap();
    	map.put("uptoken", token);
    	String uptoken = JSON.toJSONString(map);
    	response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(uptoken);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
    }
    
    private AppQiniu getByAppId(HttpServletRequest request,Long appId){
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	//七牛
    	List<AppQiniu> list = null;
    	
    	if(null != appId && 0 != appId){
    		list = appQiniuService.findByAppId(appId).getData();
    	}

    	if(CollectionUtils.isEmpty(list) && null != sessionUser.getAppId() && 0 != sessionUser.getAppId().longValue()){
    		list = appQiniuService.findByAppId(sessionUser.getAppId()).getData();
    	}
    	
        if(CollectionUtils.isEmpty(list)){
    		list = appQiniuService.findByDefault(AppQiniu.ISDEFAULT1).getData();
    	}
    	
    	if(CollectionUtils.isNotEmpty(list)){
    		return list.get(0);
    	}
    	
    	return null;
    }
    
    @ResponseBody
    @RequestMapping(value="save_info_file")
    public Response<Void> saveInfoFile(HttpServletRequest request,InfoFile infoFile){
    	
    	return infoFileService.save(infoFile);
    }
    
    //获取TOKEN
    @RequestMapping(value ="normal_token")
    public String getNoramlToken(HttpServletRequest request,HttpServletResponse response,String appId){
    	
    	AppQiniu appQiniu = this.getByAppId(request,StringUtil.stringToLong(appId));
    	if(null == appQiniu)return null;
    	String bucketname=appQiniu.getBucketName();
    	String token = QiNiuToken.getUpToken(bucketname);
    	//{"uptoken":"xxx:xxx:xxx"}	
    	Map<String,String> map = Maps.newHashMap();
    	map.put("uptoken", token);
    	String uptoken = JSON.toJSONString(map);
    	response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(uptoken);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
    }
}
