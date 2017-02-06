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

import com.cqliving.cloud.common.InformationUtil;
import com.cqliving.tool.common.util.StringUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月26日
 */
public class ImgReplaceDivTag extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		
		if(!StringUtil.isEmpty(content)){
			if(replace == 1){
				super.getJspContext().getOut().print(InformationUtil.getNoImgHtml(content, appName));
			}else{
				super.getJspContext().getOut().print(content);
			}
		}
		super.doTag();
	}

	//是否处理0：不替换，1：替换
	private int replace;
	private String content;
    private String appName;
    
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReplace() {
		return replace;
	}

	public void setReplace(int replace) {
		this.replace = replace;
	}
	
}
