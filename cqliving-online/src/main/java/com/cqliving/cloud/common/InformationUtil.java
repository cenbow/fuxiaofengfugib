/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.common;

import java.text.NumberFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.tool.common.util.HtmlRegexpUtil;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月25日
 */
public class InformationUtil {
	
//	private static final int CUT_SHARE_TITLE_LEN = 20;

	public static String getNoImgHtml(String str,String appName){
		String desHtml = "<div class=\"detail_pic_none btn_click\" style=\"height: 618px;\" imgSrc=\"img_src\"><span>appName</span><span class=\"detail_pic_none_click\">点击查看图片</span></div>";
		desHtml = desHtml.replace("appName", appName);
		List<String> imgs = HtmlRegexpUtil.findImageTags(str);
		for(String i : imgs){
			String replaceHtml = desHtml.replace("img_src",HtmlRegexpUtil.findImages(i).get(0));
			str = str.replace(i, replaceHtml);
		}
		return str;
	}
	
    public static String getRedirectUrl(Byte contextType,Byte type,String contentUrl,Long infoId){
		
        String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
		if(null != contextType && contextType.byteValue() == Information.CONTEXTTYPE3.byteValue()){
			return ActivityUtil.appendHttp(contentUrl);
		}
		
		if(null != type && type.byteValue() == Information.TYPE2.byteValue()){
			return webPath + "topic/detail/"+infoId+".html";
		}
		return webPath+"info/detail/"+infoId+".html";
	}
    
    public static String getShareUrl(Byte contextType,Byte type,String contentUrl,Long infoId,String downLoadUrl){
		
        String redirectUrl = getRedirectUrl(contextType,type,contentUrl,infoId);
        
        if(StringUtil.isEmpty(redirectUrl))return "";
        
        if(Information.CONTEXTTYPE3.byteValue() == contextType.byteValue()){
        	   return redirectUrl;
        }
        if(redirectUrl.lastIndexOf("?") <=-1){
         	redirectUrl += "?";	
        }else{
         	redirectUrl += "&";
        }
        redirectUrl += "share=share";
        return redirectUrl;
	}
    
    /**
     * <p>Description: 获取分享标题</p>
     * @author Tangtao on 2016年11月23日
     * @param appId
     * @param title
     * @return
     */
    public static String getShareTitle(Long appId, String title) {
    	if (appId != null && StringUtils.isNotBlank(title)) {
			if (appId.equals(1L)) {	//重庆
				//分享标题不进行截字操作 By Tangtao 2016-12-27
//				int strLen = StringUtil.countLength(title);
//				if (strLen> CUT_SHARE_TITLE_LEN){
//					title = StringUtil.cutString(title,CUT_SHARE_TITLE_LEN) + "...";
//				}
				return title + "-重庆APP";
			}
		}
		return title;
	}

	public static Integer syntaxViewCount(InformationDto information,InfoClassifyDto infoClassifyDto){
    	
    	if(null == information){
    		if(null == infoClassifyDto) return 0;
    		information = new InformationDto();
    		BeanUtils.copyProperties(infoClassifyDto, information);
    		information.setViewCount(infoClassifyDto.getDetailViewCount());
    	}
    	int count = 0;
    	if (Information.ADDTYPE0.equals(information.getAddType())) {	//一次添加
    		count = information.getInitCount().intValue() + information.getViewCount();
		} else if (Information.ADDTYPE1.equals(information.getAddType())) {	//逐步添加
			Long secondDiff = 0L;
			if (information.getOnlineTime() != null) {
				secondDiff = (DateUtil.now().getTime() - information.getOnlineTime().getTime()) / 1000;
			}
			if (secondDiff.intValue() >= information.getTopTime()) {
				count = information.getInitCount().intValue() + information.getViewCount();
			} else {
				count = information.getInitCount().intValue() * secondDiff.intValue() / information.getTopTime() + information.getViewCount();
			}
		} 
    	return count;
    }
    
    //获取缓存新闻缓存的key
    public static String getRedisCacheKey(String propertyKey,Byte businessType,Long infoClassifyId){
    	
    	return String.format("%s_%d:%d",propertyKey,businessType,infoClassifyId);
    }
    
    //获取回复数量的key
    public static String getInformationReplyCacheKey(String propertyKey,Byte businessType,Long infomationId){
    	return String.format("%s_reply_info_%d:%d",propertyKey,businessType,infomationId);
    }
    
    //获取回复数量的key
    public static String getInformationPatternKey(String propertyKey,Byte businessType){
    	return String.format("%s_reply_info_%d:",propertyKey,businessType);
    }
    
    //获取回复数量的key
    public static String getInfoClassifyReplyCacheKey(String propertyKey,Byte businessType,Long infoClassifyId){
    	return String.format("%s_reply_infoclassify_%d:%d",propertyKey,businessType,infoClassifyId);
    }
    
    //获取回复数的正则key
    public static String getRedisReplyPatternKey(String propertyKey,Byte businessType){
    	return String.format("%s_reply_infoclassify_%d:",propertyKey,businessType);
    }
    
    public static String getInformationViewPatternKey(String propertyKey,Byte businessType){
    	return String.format("%s_view_information_%d:",propertyKey,businessType);
    }
    
    public static String getInfoClassifyViewPatternKey(String propertyKey,Byte businessType){
    	return String.format("%s_view_infoclassify_%d:",propertyKey,businessType);
    }
    
    public static String getInfomationViewKey(String propertyKey,Byte businessType,Long informationId){
    	return String.format("%s_view_information_%d:%d",propertyKey,businessType,informationId);
    }
    
    public static String getInfoClassifyViewKey(String propertyKey,Byte businessType,Long infoClassifyId){
    	return String.format("%s_view_infoclassify_%d:%d",propertyKey,businessType,infoClassifyId);
    }
    
    /**
     * <p>Description: 处理浏览量。浏览量超过1万时，前端显示N.NN万</p>
     * @author Tangtao on 2016年12月5日
     * @param viewCount
     * @return
     */
    public static String handleViewCount(Integer viewCount) {
    	if (viewCount == null) {
			return "0";
		}
    	if (viewCount < 10000) {
			return viewCount.toString();
		}
    	NumberFormat format = NumberFormat.getInstance();
    	format.setMaximumFractionDigits(2);
    	String vc = format.format(viewCount.doubleValue() / 10000);
    	return vc + "万";
    }
	
}