package com.cqliving.cloud.security.annotation;

public enum ResourceStatus {
	Disable((byte)0,"禁用"),Enable((byte)1,"启用");
	private byte value;
	private String descn;
	ResourceStatus(byte val,String descn){
		this.value = val ;
		this.descn = descn;
	}
	
	public byte getValue() {
		return value;
	}

	public void setValue(byte value) {
		this.value = value;
	}

	public String getDescn() {
		return descn;
	}
	public void setDescn(String descn) {
		this.descn = descn;
	}
	
	
}
