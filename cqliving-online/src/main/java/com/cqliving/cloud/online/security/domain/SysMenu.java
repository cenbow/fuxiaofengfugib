package com.cqliving.cloud.online.security.domain;


import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 系统菜单表 Entity
 * Date: 2016-04-15 11:00:35
 * @author Code Generator
 */
@Entity
@Table(name = "sys_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysMenu extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 默认显示 */
	public static final Byte SHOWMODE1 = 1;
	/** 默认隐藏 */
	public static final Byte SHOWMODE2 = 2;
		
	/** 显示方式 */
	public static final Map<Byte, String> allShowModes = Maps.newTreeMap();
	static {
		allShowModes.put(SHOWMODE1, "默认显示");
		allShowModes.put(SHOWMODE2, "默认隐藏");
	}
	/** 启用 */
	public static final Byte STATUS1 = 1;
	/** 停用 */
	public static final Byte STATUS0 = 0;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS1, "启用");
		allStatuss.put(STATUS0, "停用");
	}
	
	/** 主键ID */
	private Long id;
	/** 父菜单 */
	private Long parentId;
	/** 菜单名称 */
	private String title;
	/** 菜单名称拼音首字母 */
	private String titleFirstSpell;
	/** 菜单图标 */
	private String icon;
	/** 显示方式 */
	private Byte showMode;
	/** 描述 */
	private String descn;
	/** 排序 */
	private Integer sortNum;
	/** 状态 */
	private Byte status;
	/** 资源ID */
	private Long resourceId;
	/** 创建时间 */
	private Date createDate;
	
	/*private Set<SysMenu> subMenus;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    @OrderBy("sortNum ASC")
    @Where(clause = "status=1")
    public Set<SysMenu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(Set<SysMenu> subMenus) {
        this.subMenus = subMenus;
    }*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getParentId(){
		return this.parentId;
	}
	
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitleFirstSpell(){
		return this.titleFirstSpell;
	}
	
	public void setTitleFirstSpell(String titleFirstSpell){
		this.titleFirstSpell = titleFirstSpell;
	}
	public String getIcon(){
		return this.icon;
	}
	
	public void setIcon(String icon){
		this.icon = icon;
	}
	public Byte getShowMode(){
		return this.showMode;
	}
	
	public void setShowMode(Byte showMode){
		this.showMode = showMode;
	}
	public String getDescn(){
		return this.descn;
	}
	
	public void setDescn(String descn){
		this.descn = descn;
	}
	public Integer getSortNum(){
		return this.sortNum;
	}
	
	public void setSortNum(Integer sortNum){
		this.sortNum = sortNum;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Long getResourceId(){
		return this.resourceId;
	}
	
	public void setResourceId(Long resourceId){
		this.resourceId = resourceId;
	}
	public Date getCreateDate(){
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
