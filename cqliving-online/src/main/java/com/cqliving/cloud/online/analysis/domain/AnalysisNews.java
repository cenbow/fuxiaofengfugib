package com.cqliving.cloud.online.analysis.domain;


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
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

/**
 * 资讯统计表 Entity
 * Date: 2016-11-08 14:27:42
 * @author Code Generator
 */
@Entity
@Table(name = "analysis_news")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnalysisNews extends AbstractEntity {

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
		
	public static final String VIEWNUM_COLOR = "VIEWNUM_COLOR";
	public static final String COMMENTNUM_COLOR = "COMMENTNUM_COLOR";
	public static final String PRAISENUM_COLOR = "PRAISENUM_COLOR";
	public static final String FAVORITENUM_COLOR = "FAVORITENUM_COLOR";
	public static final String SHARENUM_COLOR = "SHARENUM_COLOR";
	
	public static final String VIEW_NUM = "viewNum";
	public static final String COMMENT_NUM = "commentNum";
	public static final String PRAISE_NUM = "praiseNum";
	public static final String FAVORITE_NUM = "favoriteNum";
	public static final String SHARE_NUM = "shareNum";
	
	public static final Map<String,String> LINE_COLOR = Maps.newHashMap();
	/** 用户类型 */
	public static final Map<Byte, String> allUserTypes = Maps.newLinkedHashMap();
	
	public static final Map<String,String> allNumType = Maps.newLinkedHashMap();
	static {
		allUserTypes.put(USERTYPE9, "全部用户");
		allUserTypes.put(USERTYPE0, "微信");
		allUserTypes.put(USERTYPE1, "微博");
		allUserTypes.put(USERTYPE2, "QQ");
		allUserTypes.put(USERTYPE3, "注册用户");
		allUserTypes.put(USERTYPE4, "其它");
		
		LINE_COLOR.put(VIEWNUM_COLOR, "black");
		LINE_COLOR.put(COMMENTNUM_COLOR, "gray");
		LINE_COLOR.put(PRAISENUM_COLOR, "red");
		LINE_COLOR.put(FAVORITENUM_COLOR, "blue");
		LINE_COLOR.put(SHARENUM_COLOR, "green");
		
		allNumType.put(VIEW_NUM, "浏览量");
		allNumType.put(COMMENT_NUM, "评论量");
		allNumType.put(PRAISE_NUM, "点赞量");
		allNumType.put(FAVORITE_NUM, "收藏量");
		allNumType.put(SHARE_NUM, "分享量");
	}
	
	/** ID */
	private Long id;
	/** app_info表ID */
	private Long appId;
	/** 统计日期 */
	private Date statisticsTime;
	/** 小时数，24小时制 */
	private Integer hour;
	/** 资讯表ID，表info_classify的主键 */
	private Long infoClassifyId;
	/** 用户类型 */
	private Byte userType;
	/** 浏览量 */
	private Integer viewNum;
	/** 评论数 */
	private Integer commentNum;
	/** 点赞数 */
	private Integer praiseNum;
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
	public Date getStatisticsTime(){
		return this.statisticsTime;
	}
	
	public void setStatisticsTime(Date statisticsTime){
		this.statisticsTime = statisticsTime;
	}
	public Integer getHour(){
		return this.hour;
	}
	
	public void setHour(Integer hour){
		this.hour = hour;
	}
	public Long getInfoClassifyId(){
		return this.infoClassifyId;
	}
	
	public void setInfoClassifyId(Long infoClassifyId){
		this.infoClassifyId = infoClassifyId;
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
	public Integer getPraiseNum(){
		return this.praiseNum;
	}
	
	public void setPraiseNum(Integer praiseNum){
		this.praiseNum = praiseNum;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public static Map<String,String> getDay(Date date,int days){
		Map<String,String> map = Maps.newLinkedHashMap();
		for(int i=0;i<days;i++){
			String str = DateUtil.formatDate(DateUtil.addDays(date,-i), DateUtil.FORMAT_YYYY_MM_DD);
			map.put(str,str);
		}
		return map;
	}
}
