package com.org.weixin.system.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ModuleInfoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 表示进行活动过滤，需要进行微信鉴权*/
	public static final byte STATUE_WEIXIN_TRUE = (byte)1;
	
	/**表示活动已删除！*/
	public static final byte STATUE_WEIXIN_DEL = (byte)-99;
	
		

	/** 主键 */
	private Long id;
	/** 过滤链接 */
	private String url;
	/** 处理类 */
	private String handles;
	/** 状态 */
	private byte status;	
	
	/**开始时间*/
	private Date startTime;
	
	/**结束时间*/
	private Date endTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHandles() {
		return handles;
	}

	public void setHandles(String handles) {
		this.handles = handles;
	}
	
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

