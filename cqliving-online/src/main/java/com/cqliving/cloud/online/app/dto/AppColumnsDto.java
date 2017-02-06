package com.cqliving.cloud.online.app.dto;

import java.util.Date;
import java.util.Set;

public class AppColumnsDto implements Comparable<AppColumnsDto> {

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
	/** 位置CODE */
	private String placeCode;
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
	/** 状态 */
	private Byte status;
	/** 版本号 */
	private Integer versionNo;
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

	/** App名称 */
	private String appName;

	// tree-view
	// 一下属性为 bootstrp treeview 提供
	private Set<AppColumnsDto> nodes;
	private String text;
	private Boolean selectable = true;
	private TreeNodeState state = new TreeNodeState();

	private Set<AppColumnsDto> subs;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getTempletCode() {
		return templetCode;
	}

	public void setTempletCode(String templetCode) {
		this.templetCode = templetCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPlViewType() {
		return plViewType;
	}

	public void setPlViewType(String plViewType) {
		this.plViewType = plViewType;
	}

	public Byte getColumnsType() {
		return columnsType;
	}

	public void setColumnsType(Byte columnsType) {
		this.columnsType = columnsType;
	}

	public String getColumnsUrl() {
		return columnsUrl;
	}

	public void setColumnsUrl(String columnsUrl) {
		this.columnsUrl = columnsUrl;
	}

	public Byte getCommentType() {
		return commentType;
	}

	public void setCommentType(Byte commentType) {
		this.commentType = commentType;
	}

	public Byte getValidateType() {
		return validateType;
	}

	public void setValidateType(Byte validateType) {
		this.validateType = validateType;
	}

	public Byte getViewDate() {
		return viewDate;
	}

	public void setViewDate(Byte viewDate) {
		this.viewDate = viewDate;
	}

	public Byte getViewCountType() {
		return viewCountType;
	}

	public void setViewCountType(Byte viewCountType) {
		this.viewCountType = viewCountType;
	}

	public Byte getLeafType() {
		return leafType;
	}

	public void setLeafType(Byte leafType) {
		this.leafType = leafType;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	public Byte getViewReplyCount() {
		return viewReplyCount;
	}

	public void setViewReplyCount(Byte viewReplyCount) {
		this.viewReplyCount = viewReplyCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Long getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Long updatorId) {
		this.updatorId = updatorId;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Set<AppColumnsDto> getSubs() {
		return subs;
	}

	public void setSubs(Set<AppColumnsDto> subs) {
		this.subs = subs;
	}

	public Set<AppColumnsDto> getNodes() {
		return nodes;
	}

	public void setNodes(Set<AppColumnsDto> nodes) {
		this.nodes = nodes;
	}

	public Boolean getSelectable() {
		return selectable;
	}

	public void setSelectable(Boolean selectable) {
		this.selectable = selectable;
	}

	public TreeNodeState getState() {
		return state;
	}

	public void setState(TreeNodeState state) {
		this.state = state;
	}

	@Override
	public int compareTo(AppColumnsDto dto) {
		// AppColumnsDto dto = (AppColumnsDto)o;
		// if(this.sortNo.intValue()>dto.getSortNo().intValue()){
		// return 1;
		// }
		// if(this.sortNo.intValue()<dto.getSortNo().intValue()){
		// return -1;
		// }
		if (this.sortNo.intValue() == dto.getSortNo().intValue()) {
			return this.id.compareTo(dto.id);
		}
		// return -1;
		return this.sortNo.intValue() - dto.getSortNo().intValue();
	}
}