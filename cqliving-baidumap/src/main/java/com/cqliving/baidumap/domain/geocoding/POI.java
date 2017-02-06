/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.baidumap.domain.geocoding;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.cqliving.baidumap.domain.Point;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月18日
 */
public class POI {
	
	/** 地址信息 */
	private String addr;
	/** 数据来源 */
	private String cp;
	/** 和当前坐标点的方向 */
	private String direction;
	/** 离坐标点距离 */
	private String distance;
	/** POI 的名称 */
	private String name;
	/** POI 类型，如’ 办公大厦,商务大厦’ */
	private String poiType;
	/** POI 坐标 */
	private Point point;
	/** 电话 */
	private String tel;
	/** POI 唯一标识 */
	private String uid;
	/** 邮编 */
	private String zip;
	/** 当前位置结合POI的语义化结果描述 */
	private String sematic_description;
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPoiType() {
		return poiType;
	}
	public void setPoiType(String poiType) {
		this.poiType = poiType;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getSematic_description() {
		return sematic_description;
	}
	public void setSematic_description(String sematic_description) {
		this.sematic_description = sematic_description;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}