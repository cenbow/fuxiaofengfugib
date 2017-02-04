package com.org.weixin.client.bean.advanced.massmessage;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.org.weixin.client.bean.base.media.Uploadvideo;

/**
 * 仅适用于对 openid 发送接口
 */
public class MassVideoMessage extends MassMessage{

	private Uploadvideo video;

	/**
	 * @param media_id  MessageAPI mediaUploadvideo 返回的media_id
	 */
	public MassVideoMessage(Uploadvideo uploadvideo) {
		super();
		video = uploadvideo;
		super.msgtype = "video";
	}

	public Uploadvideo getVideo() {
		return video;
	}

	public void setVideo(Uploadvideo video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
