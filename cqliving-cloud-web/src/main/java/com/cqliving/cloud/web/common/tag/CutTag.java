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

import com.cqliving.tool.common.util.StringUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年8月19日
 */
public class CutTag extends SimpleTagSupport{

	private String str;
	private Integer length;
	
	@Override
	public void doTag() throws JspException, IOException {
		
		if(StringUtil.isEmpty(str)){
			super.getJspContext().getOut().print("");
		}else{
			int strLen = StringUtil.countLength(str);
			if(strLen>length){
				str = StringUtil.cutString(str, length) + "...";
			}
			super.getJspContext().getOut().print(str);
		}
		super.doTag();
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
}
