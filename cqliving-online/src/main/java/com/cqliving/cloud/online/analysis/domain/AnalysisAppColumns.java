package com.cqliving.cloud.online.analysis.domain;


import java.util.Map;
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
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

/**
 * 资讯栏目统计表 Entity
 * Date: 2016-11-11 11:56:02
 * @author Code Generator
 */
@Entity
@Table(name = "analysis_app_columns")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnalysisAppColumns extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 微信 */
	public static final Byte USERTYPE0 = 0;
	/** 微博 */
	public static final Byte USERTYPE1 = 1;
	/** QQ */
	public static final Byte USERTYPE2 = 2;
	/** 注册用户 */
	public static final Byte USERTYPE3 = 3;
	/** 其它 */
	public static final Byte USERTYPE4 = 4;
	/** 全部用户 */
	public static final Byte USERTYPE9 = 9;
		
	/** 用户类型 */
	public static final Map<Byte, String> allUserTypes = Maps.newTreeMap();
	static {
		allUserTypes.put(USERTYPE0, "微信");
		allUserTypes.put(USERTYPE1, "微博");
		allUserTypes.put(USERTYPE2, "QQ");
		allUserTypes.put(USERTYPE3, "注册用户");
		allUserTypes.put(USERTYPE4, "其它");
		allUserTypes.put(USERTYPE9, "全部用户");
	}
	
	/** ID */
	private Long id;
	/** app_info表ID */
	private Long appId;
	/** 统计日期 */
	private Date statisticsDate;
	/** 小时数，24小时制 */
	private Integer day;
	/** 资讯栏目表ID，表app_columns的主键 */
	private Long appColumnsId;
	/** 用户类型 */
	private Byte userType;
	/** 浏览量 */
	private Integer viewNum;
	/** 评论数 */
	private Integer commentNum;
	/** 收藏数 */
	private Integer favoriteNum;
	/** 分享数 */
	private Integer shareNum;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Date getStatisticsDate(){
		return this.statisticsDate;
	}
	
	public void setStatisticsDate(Date statisticsDate){
		this.statisticsDate = statisticsDate;
	}
	public Integer getDay(){
		return this.day;
	}
	
	public void setDay(Integer day){
		this.day = day;
	}
	public Long getAppColumnsId(){
		return this.appColumnsId;
	}
	
	public void setAppColumnsId(Long appColumnsId){
		this.appColumnsId = appColumnsId;
	}
	public Byte getUserType(){
		return this.userType;
	}
	
	public void setUserType(Byte userType){
		this.userType = userType;
	}
	public Integer getViewNum(){
		return this.viewNum;
	}
	
	public void setViewNum(Integer viewNum){
		this.viewNum = viewNum;
	}
	public Integer getCommentNum(){
		return this.commentNum;
	}
	
	public void setCommentNum(Integer commentNum){
		this.commentNum = commentNum;
	}
	public Integer getFavoriteNum(){
		return this.favoriteNum;
	}
	
	public void setFavoriteNum(Integer favoriteNum){
		this.favoriteNum = favoriteNum;
	}
	public Integer getShareNum(){
		return this.shareNum;
	}
	
	public void setShareNum(Integer shareNum){
		this.shareNum = shareNum;
	}
	
	public static Map<String,String> getDureDay(Date date){
		Map<String,String> map = Maps.newLinkedHashMap();
		//7天   15天   30天
		map.put(DateUtil.formatDate(DateUtil.addDays(date,-7), DateUtil.FORMAT_YYYY_MM_DD),"7天");
		map.put(DateUtil.formatDate(DateUtil.addDays(date,-15), DateUtil.FORMAT_YYYY_MM_DD),"15天");
		map.put(DateUtil.formatDate(DateUtil.addDays(date,-30), DateUtil.FORMAT_YYYY_MM_DD),"30天");
		return map;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
