package com.org.weixin.client.bean.advanced.massmessage;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MassTextMessage extends MassMessage{

	private Map<String, String> text;

	public MassTextMessage(String content) {
		super();
		text = new HashMap<String, String>();
		text.put("content",content);
		super.msgtype = "text";
	}

	public Map<String, String> getText() {
		return text;
	}

	public void setText(Map<String, String> text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}