/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.web.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.tool.common.util.SpringUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月26日
 */
public class AppTag extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		
		AppInfoService appInfoService = SpringUtil.getBeanByClass(AppInfoService.class);
		if(null != appInfoService){
			AppInfo appInfo = appInfoService.get(appId).getData();
			super.getJspContext().getOut().print(appInfo.getName());
		}
		super.doTag();
	}

	private Long appId;

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
}
