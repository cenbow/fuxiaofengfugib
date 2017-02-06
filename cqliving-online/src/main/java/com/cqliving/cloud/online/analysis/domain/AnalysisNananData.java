package com.cqliving.cloud.online.analysis.domain;


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
import com.cqliving.tool.common.util.file.Export;

/**
 * 南岸讲学赞栏目统计 Entity
 * Date: 2016-11-10 09:23:30
 * @author Code Generator
 */
@Entity
@Table(name = "analysis_nanan_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnalysisNananData extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 统计期数ID，表analysis_nanan_times的主键 */
	private Long analysisNananTimesId;
	/** 用户ID，表user_account的主键 */
	private Long userId;
	/** 用户手机 */
	@Export(name = "用户手机", order = 0)
	private String userTelephone;
	/** 用户姓名 */
	@Export(name = "用户名", order = 1)
	private String userName;
	/** 评论数 */
	@Export(name = "评论数", order = 2)
	private Integer commentNum;
	/** 点赞数 */
	@Export(name = "点赞数", order = 3)
	private Integer praiseNum;
	/** 分享数 */
	@Export(name = "分享数", order = 4)
	private Integer shareNum;
	/** 分数=分享数*5+评论数*3+点赞数 */
	@Export(name = "得分", order = 5)
	private Integer score;
	/** 创建时间 */
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getAnalysisNananTimesId(){
		return this.analysisNananTimesId;
	}
	
	public void setAnalysisNananTimesId(Long analysisNananTimesId){
		this.analysisNananTimesId = analysisNananTimesId;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getUserTelephone(){
		return this.userTelephone;
	}
	
	public void setUserTelephone(String userTelephone){
		this.userTelephone = userTelephone;
	}
	public String getUserName(){
		return this.userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	public Integer getCommentNum(){
		return this.commentNum;
	}
	
	public void setCommentNum(Integer commentNum){
		this.commentNum = commentNum;
	}
	public Integer getPraiseNum(){
		return this.praiseNum;
	}
	
	public void setPraiseNum(Integer praiseNum){
		this.praiseNum = praiseNum;
	}
	public Integer getShareNum(){
		return this.shareNum;
	}
	
	public void setShareNum(Integer shareNum){
		this.shareNum = shareNum;
	}
	public Integer getScore(){
		return this.score;
	}
	
	public void setScore(Integer score){
		this.score = score;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
