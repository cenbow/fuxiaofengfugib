package com.org.weixin.client.bean.advanced.massmessage;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MassMusicMessage extends MassMessage{

	private Map<String, String> music;

	public MassMusicMessage(String media_id) {
		super();
		music = new HashMap<String, String>();
		music.put("media_id",media_id);
		super.msgtype = "music";
	}

	public Map<String, String> getMusic() {
		return music;
	}

	public void setMusic(Map<String, String> music) {
		this.music = music;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}