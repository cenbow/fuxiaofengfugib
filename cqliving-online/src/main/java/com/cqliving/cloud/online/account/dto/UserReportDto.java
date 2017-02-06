package com.cqliving.cloud.online.account.dto;


import java.util.Date;

/**
 * 用户举报表 Dto
 * Date: 2016-05-31
 * @author huxiaoping
 */
public class UserReportDto {
	/** ID */
	private Long id;
	/** 来源APP */
	private Long appId;
	/** 会话code */
	private String sessionCode;
	/** 评论人ID */
	private Long userId;
	/** 举报人姓名 */
	private String name;
	/** 评论内容,预留，用户前台暂时无填写 */
	private String content;
	/** 举报内容CODE,参照字典表 */
	private String reportCode;
	/** 举报来源类型 */
	private Byte sourceType;
	/** 举报来源ID */
	private Long sourceId;
	/** 状态 */
	private Byte status;
	/** 审阅状态 */
	private Byte auditingType;
	/** 创建时间 */
	private Date createTime;
	/** 审核人ID */
	private Long auditingId;
	/** 审核人姓名 */
	private String auditingtor;
	/** 审核时间 */
	private Date auditingTime;
	/** 审核描述 */
    private String auditingContent;
	
	/** 客户端名称 */
	private String appName;
	/** 来源标题（新闻）或内容 （评论）*/
	private String source;
	/** 类型 */
    private Byte operateType;
	
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
	public String getSessionCode(){
		return this.sessionCode;
	}
	
	public void setSessionCode(String sessionCode){
		this.sessionCode = sessionCode;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public String getReportCode(){
		return this.reportCode;
	}
	
	public void setReportCode(String reportCode){
		this.reportCode = reportCode;
	}
	public Byte getSourceType(){
		return this.sourceType;
	}
	
	public void setSourceType(Byte sourceType){
		this.sourceType = sourceType;
	}
	public Long getSourceId(){
		return this.sourceId;
	}
	
	public void setSourceId(Long sourceId){
		this.sourceId = sourceId;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Byte getAuditingType(){
		return this.auditingType;
	}
	
	public void setAuditingType(Byte auditingType){
		this.auditingType = auditingType;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getAuditingId(){
		return this.auditingId;
	}
	
	public void setAuditingId(Long auditingId){
		this.auditingId = auditingId;
	}
	public String getAuditingtor(){
		return this.auditingtor;
	}
	
	public void setAuditingtor(String auditingtor){
		this.auditingtor = auditingtor;
	}
	public Date getAuditingTime(){
		return this.auditingTime;
	}
	
	public void setAuditingTime(Date auditingTime){
		this.auditingTime = auditingTime;
	}

    public String getAuditingContent() {
        return auditingContent;
    }

    public void setAuditingContent(String auditingContent) {
        this.auditingContent = auditingContent;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Byte getOperateType() {
        return operateType;
    }

    public void setOperateType(Byte operateType) {
        this.operateType = operateType;
    }
}