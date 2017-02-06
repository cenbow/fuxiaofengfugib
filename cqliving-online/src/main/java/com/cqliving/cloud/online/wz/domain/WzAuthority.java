package com.cqliving.cloud.online.wz.domain;


import java.util.List;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 问政权限配置表 Entity
 * Date: 2016-05-09 17:26:06
 * @author Code Generator
 */
@Entity
@Table(name = "wz_authority")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WzAuthority extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	/** 问政信息是否审核 **/
    public static final String NAME_QUESTION_IS_CHECK = "question_is_check";
    /** 问政评论是否审核 **/
    public static final String NAME_COMMENT_IS_CHECK = "comment_is_check";
    /** 回复信息是否总负责人发布 **/
    public static final String NAME_REPLY_IS_LAST_PUBLISH = "reply_is_last_publish";
    /** 发布状态下的问政是否可删 **/
    public static final String NAME_ALREADY_PUBLISH_ALLOW_DEL = "already_publish_allow_del";
    /** 自动回复 **/
    public static final String NAME_AUTO_REPLY = "auto_reply";
    /** 问政信息是否允许游客发布 **/
    public static final String NAME_ALLOW_TOURIST_PUBLISH = "allow_tourist_publish";
    /** 问政是否收集信息  **/
    public static final String NAME_IS_COLLECT_USER_INFO = "question_is_collect_user_info";

	/** 选择型(是和否) */
	public static final int TYPE1 = 1;
	/** 文本型(输入框) */
	public static final int TYPE2 = 2;
	/** 参数收集型 */
	public static final int TYPE3 = 3;
		
	/** 参数类型 */
	public static final Map<Integer, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE1, "选择型(是和否)");
		allTypes.put(TYPE2, "文本型(输入框)");
		allTypes.put(TYPE3, "参数收集型");
	}
	
	/** ID */
	private Long id;
	/** 参数名称 */
	private String name;
	/** 参数类型 */
	private String type;
	/** 描述信息 */
	private String description;
	
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

    WzAuthority(){}
	
	WzAuthority(Long appId, String name, String type, String description){
	    this.name = name;
	    this.type = type;
	    this.description = description;
	}
	
	/**
	 * Title:初始化设置数据
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月16日
	 * @return
	 */
	public final static List<WzAuthority> initData1(Long appId){
	    List<WzAuthority> list = Lists.newArrayList();
	    list.add(new WzAuthority(appId, "question_is_check", "1", "问政信息是否审核"));
	    list.add(new WzAuthority(appId, "comment_is_check", "1", "问政评论是否审核"));
	    list.add(new WzAuthority(appId, "reply_is_last_publish", "1", "回复信息是否总负责人发布"));
	    list.add(new WzAuthority(appId, "already_publish_allow_del", "1", "发布状态下的问政是否可删"));
	    list.add(new WzAuthority(appId, "auto_reply", "2", "自动回复"));
	    list.add(new WzAuthority(appId, "allow_tourist_publish", "1", "问政信息是否允许游客发布"));
	    list.add(new WzAuthority(appId, "question_is_collect_user_info", "3", "问政是否收集信息"));
	    return list;
	}
}
