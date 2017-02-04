package com.org.weixin.system.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.wechat.framework.domain.AbstractEntity;

/**
 * sys_modules Entity
 *
 * Date: 2015-07-23 20:46:52
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "sys_modules")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysModules extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** 主键 */
	private Long id;
	/** 模块名称 */
	private String name;
	/** 模块类型 */
	private String type;
	/** 标题 */
	private String title;
	/** 版本 */
	private String version;
	/** 提供能力 */
	private String ability;
	/** 介绍 */
	private String description;
	/** 作者 */
	private String author;
	/** 链接 */
	private String url;
	/** 配置 */
	private byte settings;
	/** 是否订阅 */
	private String subscribes;
	/** 处理类 */
	private String handles;
	/** 是否需要规则 */
	private byte isrulefields;
	/** 是否系统应用 */
	private byte issystem;
	/** 是否解决方案 */
	private byte issolution;
	/** target */
	private Integer target;
	/** status */
	private byte status;
	
	/**开始时间*/
	private Date startTime;
	
	/**结束时间*/
	private Date endTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getVersion(){
		return this.version;
	}
	
	public void setVersion(String version){
		this.version = version;
	}
	public String getAbility(){
		return this.ability;
	}
	
	public void setAbility(String ability){
		this.ability = ability;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public String getAuthor(){
		return this.author;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	public byte getSettings(){
		return this.settings;
	}
	
	public void setSettings(byte settings){
		this.settings = settings;
	}
	public String getSubscribes(){
		return this.subscribes;
	}
	
	public void setSubscribes(String subscribes){
		this.subscribes = subscribes;
	}
	public String getHandles(){
		return this.handles;
	}
	
	public void setHandles(String handles){
		this.handles = handles;
	}
	public byte getIsrulefields(){
		return this.isrulefields;
	}
	
	public void setIsrulefields(byte isrulefields){
		this.isrulefields = isrulefields;
	}
	public byte getIssystem(){
		return this.issystem;
	}
	
	public void setIssystem(byte issystem){
		this.issystem = issystem;
	}
	public byte getIssolution(){
		return this.issolution;
	}
	
	public void setIssolution(byte issolution){
		this.issolution = issolution;
	}
	public Integer getTarget(){
		return this.target;
	}
	
	public void setTarget(Integer target){
		this.target = target;
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
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
