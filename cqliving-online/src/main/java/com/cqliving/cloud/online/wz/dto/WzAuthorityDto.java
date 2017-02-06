package com.cqliving.cloud.online.wz.dto;

public class WzAuthorityDto {
    /** ID */
    private Long id;
    /** 参数名称 */
    private String name;
    /** 参数类型 */
    private String type;
    /** 描述信息 */
    private String description;
    
    private WzAppAuthorityDto wzAppAuthorityDto;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WzAppAuthorityDto getWzAppAuthorityDto() {
        return wzAppAuthorityDto;
    }

    public void setWzAppAuthorityDto(WzAppAuthorityDto wzAppAuthorityDto) {
        this.wzAppAuthorityDto = wzAppAuthorityDto;
    }
    
    
}
