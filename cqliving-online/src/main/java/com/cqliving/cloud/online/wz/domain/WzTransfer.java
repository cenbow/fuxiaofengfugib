package com.cqliving.cloud.online.wz.domain;


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
 * 问题转交流程表 Entity
 * Date: 2016-05-10 09:49:54
 * @author Code Generator
 */
@Entity
@Table(name = "wz_transfer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WzTransfer extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 待处理 */
	public static final Byte STATUS1 = 1;
	/** 保存中 */
	public static final Byte STATUS2 = 2;
	/** 已处理 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 问题操作后状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS1, "待处理");
		allStatuss.put(STATUS2, "保存中");
		allStatuss.put(STATUS3, "已处理");
		allStatuss.put(STATUS99, "删除");
	}
	/** 拒绝 */
	public static final Byte RESULT1 = 1;
	/** 已转交 */
	public static final Byte RESULT2 = 2;
	/** 已提交 */
	public static final Byte RESULT3 = 3;
	/** 已发布 */
	public static final Byte RESULT4 = 4;
		
	/** 处理结果,当状态为status=3时有效 */
	public static final Map<Byte, String> allResults = Maps.newTreeMap();
	static {
		allResults.put(RESULT1, "拒绝");
		allResults.put(RESULT2, "已转交");
		allResults.put(RESULT3, "已提交");
		allResults.put(RESULT4, "已发布");
	}
	
	/** ID */
	private Long id;
	/** 问题ID */
	private Long questionId;
	/** 问题操作后状态 */
	private Byte status;
	/** 处理结果,当状态为status=3时有效 */
	private Byte result;
	/** 创建时间 */
	private Date createTime;
	/** 创建人名称 */
	private String creator;
	/** 转交人ID，即本条记录创建人ID */
	private Long creatorId;
	/** 待处理人 */
	private Long currentUserId;
	/** 回复内容 */
	private String replayContent;
	/** 描述 */
	private String description;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getQuestionId(){
		return this.questionId;
	}
	
	public void setQuestionId(Long questionId){
		this.questionId = questionId;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}

	public Byte getResult() {
        return result;
    }

    public void setResult(Byte result) {
        this.result = result;
    }

    public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public String getCreator(){
		return this.creator;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	public Long getCreatorId(){
		return this.creatorId;
	}
	
	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}
	public Long getCurrentUserId(){
		return this.currentUserId;
	}
	
	public void setCurrentUserId(Long currentUserId){
		this.currentUserId = currentUserId;
	}
	public String getReplayContent(){
		return this.replayContent;
	}
	
	public void setReplayContent(String replayContent){
		this.replayContent = replayContent;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
