package com.org.weixin.client.bean.base.message.resp;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RespTextMessage extends RespMessage {

	public RespTextMessage(String touser) {
		super(touser,"text");
	}

	public RespTextMessage(String touser,String content){
		this(touser);
		this.text = new Text();
		this.text.setContent(content);
	}
	
	private Text text;

	public Text getText() {
		return text;
	}
	
	public void setText(Text text) {
		this.text = text;
	}

	public static class Text {
		
		private String content;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}

