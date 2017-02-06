package com.cqliving.cloud.online.interfacc.dto;

import java.util.List;

public class ReginDto {
private Long code;
private String name;
private List<ReginDto> regionList;
public Long getCode() {
	return code;
}
public void setCode(Long code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<ReginDto> getRegionList() {
	return regionList;
}
public void setRegionList(List<ReginDto> regionList) {
	this.regionList = regionList;
}

}
