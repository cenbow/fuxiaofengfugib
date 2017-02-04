package com.org.weixin.client.bean.base.message.resp;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class RespMessage {
	
	public RespMessage() {
		super();
	}
	
	protected RespMessage(String touser,String msgtype) {
		super();
		this.touser = touser;
		this.msgtype = msgtype;
	}

	private String touser;
	
	private String msgtype;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	protected void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}