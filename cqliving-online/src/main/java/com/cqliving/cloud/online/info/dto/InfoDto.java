/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.info.dto;

import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.cloud.online.info.domain.Information;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月31日
 */
public class InfoDto {
	
	private Information information;
	private InfoClassify infoClassify;
	//列表图
	private String infoClassifyList;
	private String newsCopy;
	private String infoTheme;
	//文件资源
	private InfoFile infoFile;
	//新闻内容资源json字符串
	private String appResource;
	
	public Information getInformation() {
		return information;
	}
	public void setInformation(Information information) {
		this.information = information;
	}
	public InfoClassify getInfoClassify() {
		return infoClassify;
	}
	public void setInfoClassify(InfoClassify infoClassify) {
		this.infoClassify = infoClassify;
	}
	
	public String getInfoClassifyList() {
		return infoClassifyList;
	}
	public void setInfoClassifyList(String infoClassifyList) {
		this.infoClassifyList = infoClassifyList;
	}
	public String getNewsCopy() {
		return newsCopy;
	}
	public void setNewsCopy(String newsCopy) {
		this.newsCopy = newsCopy;
	}
	public String getInfoTheme() {
		return infoTheme;
	}
	public void setInfoTheme(String infoTheme) {
		this.infoTheme = infoTheme;
	}
	public InfoFile getInfoFile() {
		return infoFile;
	}
	public void setInfoFile(InfoFile infoFile) {
		this.infoFile = infoFile;
	}
	public String getAppResource() {
		return appResource;
	}
	public void setAppResource(String appResource) {
		this.appResource = appResource;
	}
}
