package com.org.weixin.client.bean.advanced.massmessage;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MassMPnewsMessage extends MassMessage{

	private Map<String, String> mpnews;

	public MassMPnewsMessage(String media_id) {
		super();
		mpnews = new HashMap<String, String>();
		mpnews.put("media_id",media_id);
		super.msgtype = "mpnews";
	}

	public Map<String, String> getMpnews() {
		return mpnews;
	}

	public void setMpnews(Map<String, String> mpnews) {
		this.mpnews = mpnews;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}