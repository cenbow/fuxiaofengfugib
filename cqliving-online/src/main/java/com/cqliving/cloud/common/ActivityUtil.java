/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.common;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.tool.common.util.StringUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年7月19日
 */
public class ActivityUtil {

	public static String getRedirectUtil(Byte actInfoListType,String linkUrl,Long actInfoListId,String urlParam){
		
		String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
		
		String url = webPath+"act/vote_detail/"+actInfoListId+".html";
		if(null != actInfoListType && actInfoListType.byteValue() == ActInfoList.TYPE2.byteValue()){
			return ActivityUtil.appendHttp(linkUrl);
		}else if(ActInfoList.TYPE1.equals(actInfoListType)){
			url =  webPath + "act/act_notice/" + actInfoListId + ".html";
		}else if(ActInfoList.TYPE4.equals(actInfoListType)){
			url =  webPath + "act/answer/answer/" + actInfoListId + ".html";
		}
		
		if(!StringUtil.isEmpty(urlParam))url += urlParam;
		
		return url;
	}
	
	public static String getShareUrl(Byte actInfoListType,String linkUrl,Long actInfoListId,String downLoadUrl,String urlParam){
         String redirectUrl = "";
         if(ActInfoList.TYPE4.equals(actInfoListType)){//答题分享
        	 String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
        	 redirectUrl = webPath + "act/answer_share/" + actInfoListId + ".html";
         }else{
        	 redirectUrl = getRedirectUtil(actInfoListType,linkUrl,actInfoListId,urlParam);
         }
        
        if(StringUtil.isEmpty(redirectUrl))return "";
        
        if(redirectUrl.lastIndexOf("?") <=-1){
        	redirectUrl += "?";	
        }else{
        	redirectUrl += "&";
        }
        redirectUrl += "share=share&openUrl="+downLoadUrl;
        return redirectUrl;
	}
	
	public static String appendHttp(String url){
		if(StringUtil.isEmpty(url))return "";
		String http = "http://";
		String https = "https://";
		if(url.toLowerCase().contains(http) || url.toLowerCase().contains(https)){
			return url;
		}
		return http+url;
	}
}
