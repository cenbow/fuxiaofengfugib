package com.cqliving.tool.common;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 处理请求基础类
 * 
 * @author OZHIBIN
 * 
 */
@SuppressWarnings("serial")
public class Request implements Serializable {
	// 版本号
	private String vesion;
	private String protocol;
	private Object[] params;

	public String getVesion() {
		return vesion;
	}

	public void setVesion(String vesion) {
		this.vesion = vesion;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "Request [vesion=" + vesion + ", protocol=" + protocol
				+ ", params=" + Arrays.toString(params) + "]";
	}

}
