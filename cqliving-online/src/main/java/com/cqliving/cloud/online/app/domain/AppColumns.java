package com.cqliving.cloud.online.app.domain;


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
 * 客户端栏目表 Entity
 * Date: 2016-04-28 11:59:18
 * @author Code Generator
 */
@Entity
@Table(name = "app_columns")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppColumns extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 正常 */
	public static final Byte STATUS3 = 3;
	/** 不显示 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "显示");
		allStatuss.put(STATUS88, "不显示");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** 状态 */
	public static final Map<Byte, String> viewMap = Maps.newTreeMap();
	static {
	    viewMap.put(STATUS3, "显示");
	    viewMap.put(STATUS88, "不显示");
	}
	/** 全部可见 */
	public static final String PLVIEWTYPE0 = "0";
	/** 客户端可见 */
	public static final String PLVIEWTYPE1 = "1";
	/** IOS可见 */
	public static final String PLVIEWTYPE2 = "2";
	/** 全部不可见 */
	public static final String PLVIEWTYPE4 = "4";
		
	/** 平台可见类型 */
	public static final Map<String, String> allPlViewTypes = Maps.newTreeMap();
	static {
		allPlViewTypes.put(PLVIEWTYPE0, "全部可见");
		allPlViewTypes.put(PLVIEWTYPE1, "客户端可见");
		allPlViewTypes.put(PLVIEWTYPE2, "IOS可见");
		//allPlViewTypes.put(PLVIEWTYPE3, "WEB可见");
	}
	/** 资讯类 */
	public static final Byte COLUMNSTYPE0 = 0;
	/** 外链类 */
	public static final Byte COLUMNSTYPE2 = 2;
	/** 自定义类 */
	public static final Byte COLUMNSTYPE3 = 3;
		
	/** 栏目类型 */
	public static final Map<Byte, String> allColumnsTypes = Maps.newTreeMap();
	static {
		allColumnsTypes.put(COLUMNSTYPE0, "资讯类");
		allColumnsTypes.put(COLUMNSTYPE2, "外链类");
		allColumnsTypes.put(COLUMNSTYPE3, "自定义类");
	}
	/** 允许评论 */
	public static final Byte COMMENTTYPE0 = 0;
	/** 不允许评论 */
	public static final Byte COMMENTTYPE1 = 1;
		
	/** 允许评论 */
	public static final Map<Byte, String> allCommentTypes = Maps.newTreeMap();
	static {
		allCommentTypes.put(COMMENTTYPE0, "允许评论");
		allCommentTypes.put(COMMENTTYPE1, "不允许评论");
	}
	/** 无需审核 */
	public static final Byte VALIDATETYPE0 = 0;
	/** 需审核 */
	public static final Byte VALIDATETYPE1 = 1;
		
	/** 需审核 */
	public static final Map<Byte, String> allValidateTypes = Maps.newTreeMap();
	static {
		allValidateTypes.put(VALIDATETYPE0, "无需审核");
		allValidateTypes.put(VALIDATETYPE1, "需审核");
	}
	/** 显示日期 */
	public static final Byte VIEWDATE0 = 0;
	/** 不显示日期 */
	public static final Byte VIEWDATE1 = 1;
		
	/** 列表显示日期 */
	public static final Map<Byte, String> allViewDates = Maps.newTreeMap();
	static {
		allViewDates.put(VIEWDATE0, "显示日期");
		allViewDates.put(VIEWDATE1, "不显示日期");
	}
	/** 显示 */
	public static final Byte VIEWCOUNTTYPE0 = 0;
	/** 不显示 */
	public static final Byte VIEWCOUNTTYPE1 = 1;
		
	/** 显示浏览数 */
	public static final Map<Byte, String> allViewCountTypes = Maps.newTreeMap();
	static {
		allViewCountTypes.put(VIEWCOUNTTYPE0, "显示");
		allViewCountTypes.put(VIEWCOUNTTYPE1, "不显示");
	}
	/** 未叶子节点 */
	public static final Byte LEAFTYPE0 = 0;
	/** 叶子节点 */
	public static final Byte LEAFTYPE1 = 1;
		
	/** 是否叶子节点 */
	public static final Map<Byte, String> allLeafTypes = Maps.newTreeMap();
	static {
		allLeafTypes.put(LEAFTYPE0, "未叶子节点");
		allLeafTypes.put(LEAFTYPE1, "叶子节点");
	}
	/** 显示 */
	public static final Byte VIEWREPLYCOUNT0 = 0;
	/** 不显示 */
	public static final Byte VIEWREPLYCOUNT1 = 1;
		
	/** 显示评论数 */
	public static final Map<Byte, String> allViewReplyCounts = Maps.newTreeMap();
	static {
		allViewReplyCounts.put(VIEWREPLYCOUNT0, "显示");
		allViewReplyCounts.put(VIEWREPLYCOUNT1, "不显示");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 栏目名称 */
	private String name;
	/** 父栏目id */
	private Long parentId;
	/** 模板CODE */
	private String templetCode;
	/** 父栏目CODE */
	private String parentCode;
	/** 栏目CODE，增加类型，组合方式：父父栏目ID,父档目ID.自身ID */
	private String code;
	/** 状态 */
	private Byte status;
	/** 版本号 */
	private Integer versionNo;
	/** 排序号 */
	private Integer sortNo;
	/** 栏目图标 */
	private String imageUrl;
	/** 平台可见类型 */
	private String plViewType;
	/** 栏目类型 */
	private Byte columnsType;
	/** 栏目URL */
	private String columnsUrl;
	/** 允许评论 */
	private Byte commentType;
	/** 需审核 */
	private Byte validateType;
	/** 列表显示日期 */
	private Byte viewDate;
	/** 显示浏览数 */
	private Byte viewCountType;
	/** 是否叶子节点 */
	private Byte leafType;
	/** 显示评论数 */
	private Byte viewReplyCount;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人名称 */
	private String creator;
	/** 更新人ID */
	private Long updatorId;
	/** updator */
	private String updator;
	/** 更新时间 */
	private Date updateTime;
	/** 栏目图片名称 */
	private String imageName;
	
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
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Long getParentId(){
		return this.parentId;
	}
	
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	public String getTempletCode(){
		return this.templetCode;
	}
	
	public void setTempletCode(String templetCode){
		this.templetCode = templetCode;
	}
	public String getParentCode(){
		return this.parentCode;
	}
	
	public void setParentCode(String parentCode){
		this.parentCode = parentCode;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Integer getVersionNo(){
		return this.versionNo;
	}
	
	public void setVersionNo(Integer versionNo){
		this.versionNo = versionNo;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public String getPlViewType(){
		return this.plViewType;
	}
	
	public void setPlViewType(String plViewType){
		this.plViewType = plViewType;
	}
	public Byte getColumnsType(){
		return this.columnsType;
	}
	
	public void setColumnsType(Byte columnsType){
		this.columnsType = columnsType;
	}
	public String getColumnsUrl(){
		return this.columnsUrl;
	}
	
	public void setColumnsUrl(String columnsUrl){
		this.columnsUrl = columnsUrl;
	}
	public Byte getCommentType(){
		return this.commentType;
	}
	
	public void setCommentType(Byte commentType){
		this.commentType = commentType;
	}
	public Byte getValidateType(){
		return this.validateType;
	}
	
	public void setValidateType(Byte validateType){
		this.validateType = validateType;
	}
	public Byte getViewDate(){
		return this.viewDate;
	}
	
	public void setViewDate(Byte viewDate){
		this.viewDate = viewDate;
	}
	public Byte getViewCountType(){
		return this.viewCountType;
	}
	
	public void setViewCountType(Byte viewCountType){
		this.viewCountType = viewCountType;
	}
	public Byte getLeafType(){
		return this.leafType;
	}
	
	public void setLeafType(Byte leafType){
		this.leafType = leafType;
	}
	public Byte getViewReplyCount(){
		return this.viewReplyCount;
	}
	
	public void setViewReplyCount(Byte viewReplyCount){
		this.viewReplyCount = viewReplyCount;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getCreatorId(){
		return this.creatorId;
	}
	
	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}
	public String getCreator(){
		return this.creator;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	public Long getUpdatorId(){
		return this.updatorId;
	}
	
	public void setUpdatorId(Long updatorId){
		this.updatorId = updatorId;
	}
	public String getUpdator(){
		return this.updator;
	}
	
	public void setUpdator(String updator){
		this.updator = updator;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
