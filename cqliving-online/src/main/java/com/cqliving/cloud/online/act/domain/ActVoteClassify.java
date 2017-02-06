package com.cqliving.cloud.online.act.domain;


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

import com.cqliving.framework.common.domain.AbstractEntity;

/**
 * 活动投票分类表 Entity
 * Date: 2016-06-07 09:23:15
 * @author Code Generator
 */
@Entity
@Table(name = "act_vote_classify")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActVoteClassify extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public final static Byte ISIMAGEVOTE1 = 1;
	public final static Byte ISIMAGEVOTE0 = 0;
	public final static Byte STATUS3 = 3;//正常
	public final static Byte STATUS99 = 99;//删除
	/** ID */
	private Long id;
	/** 活动ID（act_info表主键） */
	private Long actInfoId;
	/** 活动集合表ID（act_info_list表主键） */
	private Long actInfoListId;
	/** 活动投票表ID（act_vote表主键） */
	private Long actVoteId;
	/** 分类标题，不超过8个字 */
	private String title;
	/** 分类主题，不超过50个字  */
	private String subject;
	/** 排序号 */
	private Integer sortNo;
	/** 创建时间 */
	private Date createTime;
	/** 选项是否带图片{0:不带图片,1:带图片} */
	private Byte  isImageVote;
	/**状态{3:正常,99:删除}*/
	private Byte status;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getActInfoId(){
		return this.actInfoId;
	}
	
	public void setActInfoId(Long actInfoId){
		this.actInfoId = actInfoId;
	}
	public Long getActInfoListId(){
		return this.actInfoListId;
	}
	
	public void setActInfoListId(Long actInfoListId){
		this.actInfoListId = actInfoListId;
	}
	public Long getActVoteId(){
		return this.actVoteId;
	}
	
	public void setActVoteId(Long actVoteId){
		this.actVoteId = actVoteId;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getSubject(){
		return this.subject;
	}
	
	public void setSubject(String subject){
		this.subject = subject;
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
	
	public Byte getIsImageVote() {
		return isImageVote;
	}

	public void setIsImageVote(Byte isImageVote) {
		this.isImageVote = isImageVote;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
