package com.org.weixin.client.bean.advanced.massmessage;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MassImageMessage extends MassMessage{

	private Map<String, String> image;

	public MassImageMessage(String media_id) {
		super();
		image = new HashMap<String, String>();
		image.put("media_id",media_id);
		super.msgtype = "image";
	}

	public Map<String, String> getImage() {
		return image;
	}

	public void setImage(Map<String, String> image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}