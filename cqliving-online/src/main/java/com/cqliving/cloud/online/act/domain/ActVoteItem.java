package com.cqliving.cloud.online.act.domain;


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
 * 活动投票分类选项表 Entity
 * Date: 2016-06-07 09:23:54
 * @author Code Generator
 */
@Entity
@Table(name = "act_vote_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActVoteItem extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 转码中 */
	public static final Byte STATUS2 = 2;
	/** 正常 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS2, "转码中");
		allStatuss.put(STATUS3, "正常");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 活动投票分类表ID（act_vote_classify表主键） */
	private Long actVoteClassifyId;
	/** 选项编号 */
	private String number;
	/** 选项标题，后台限制最多80个字 */
	private String itemTitle;
	/** 选项描述 */
	private String itemDesc;
	/** 实际量 */
	private Integer actValue;
	/** 初始量 */
	private Integer initValue;
	/** 选项图片 */
	private String imageUrl;
	/** 视频URL */
	private String videoUrl;
	/** 内容,包含HTML标签的富文本内容 */
	private String content;
	/** 状态 */
	private Byte status;
	/** 排序号 */
	private Integer sortNo;
	/** 创建时间 */
	private Date createTime;
	/** 七牛资源表 */
	private Long infoFileId;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getActVoteClassifyId(){
		return this.actVoteClassifyId;
	}
	
	public void setActVoteClassifyId(Long actVoteClassifyId){
		this.actVoteClassifyId = actVoteClassifyId;
	}
	public String getNumber(){
		return this.number;
	}
	
	public void setNumber(String number){
		this.number = number;
	}
	public String getItemTitle(){
		return this.itemTitle;
	}
	
	public void setItemTitle(String itemTitle){
		this.itemTitle = itemTitle;
	}
	public String getItemDesc(){
		return this.itemDesc;
	}
	
	public void setItemDesc(String itemDesc){
		this.itemDesc = itemDesc;
	}
	public Integer getActValue(){
		return this.actValue;
	}
	
	public void setActValue(Integer actValue){
		this.actValue = actValue;
	}
	public Integer getInitValue(){
		return this.initValue;
	}
	
	public void setInitValue(Integer initValue){
		this.initValue = initValue;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public String getVideoUrl(){
		return this.videoUrl;
	}
	
	public void setVideoUrl(String videoUrl){
		this.videoUrl = videoUrl;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Long getInfoFileId() {
		return infoFileId;
	}

	public void setInfoFileId(Long infoFileId) {
		this.infoFileId = infoFileId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActVoteItem other = (ActVoteItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}
