package com.cqliving.cloud.online.wz.dto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月12日
 */
public class WzCollectInfoDto {

    /** ID **/
    private Long id;
    /** 参数名称 */
    private String name;
    /** 是否必填 */
    private Byte isRequired;
    
    public WzCollectInfoDto(Long id, String name, Byte isRequired){
        this.id = id;
        this.name = name;
        this.isRequired = isRequired;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	public Byte getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Byte isRequired) {
		this.isRequired = isRequired;
	}
    
    
}
