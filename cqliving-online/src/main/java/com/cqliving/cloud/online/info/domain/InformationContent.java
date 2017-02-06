package com.cqliving.cloud.online.info.domain;


import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.cloud.online.app.domain.AppResouse;
import com.cqliving.cloud.online.survey.dto.SurveyInfoDto;
import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 资讯内容表 Entity
 * Date: 2016-04-29 16:22:24
 * @author Code Generator
 */
@Entity
@Table(name = "INFORMATION_CONTENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InformationContent extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 普通文本 */
	public static final Byte TYPE0 = 0;
	/** 视频 */
	public static final Byte TYPE1 = 1;
	/** 音频 */
	public static final Byte TYPE2 = 2;
	/** 投票 */
	public static final Byte TYPE3 = 3;
	/** 打擂 */
	public static final Byte TYPE4 = 4;
	/** 调查 */
	public static final Byte TYPE5 = 5;
	/** 多图 */
	public static final Byte TYPE6 = 6;
	/** 图集 */
	public static final Byte TYPE7 = 7;
	public static final Byte STATUS3 = 3;
	public static final Byte STATUS99 = 99;
	/** 来源类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	public static final Map<Byte, String> allTypesI18n = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "普通文本");
		allTypes.put(TYPE1, "视频");
		allTypes.put(TYPE2, "音频");
		allTypes.put(TYPE3, "投票");
		allTypes.put(TYPE4, "打擂");
		allTypes.put(TYPE5, "调研");
		allTypes.put(TYPE6, "多图");
		allTypes.put(TYPE7, "图集");
		
		allTypesI18n.put(TYPE0, "image_text");
		allTypesI18n.put(TYPE1, "video");
		allTypesI18n.put(TYPE2, "audio");
		allTypesI18n.put(TYPE3, "vote");
		allTypesI18n.put(TYPE4, "arena");
		allTypesI18n.put(TYPE5, "survey");
		allTypesI18n.put(TYPE6, "images");
		allTypesI18n.put(TYPE7, "atlas");
	}
	
	/** ID */
	private Long id;
	/** 资讯ID */
	private Long informationId;
	/** 客户端_ID */
	private Long appId;
	/** 来源类型 */
	private Byte type;
	/** 活动标题 */
	private String title;
	/** 排序号 */
	private Integer sortNo;
	/** 状态3:正常 ,99:删除 */
	private Byte status;
	//投票
	List<SurveyInfoDto> votes;
	//打擂
	List<SurveyInfoDto> arenas;
	List<AppResouse> appResource;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getInformationId(){
		return this.informationId;
	}
	
	public void setInformationId(Long informationId){
		this.informationId = informationId;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public Integer getSortNo(){
		return this.sortNo;
	}
	
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}
	@Transient
	public List<AppResouse> getAppResource() {
		return appResource;
	}
	@Transient
	public void setAppResource(List<AppResouse> appResource) {
		this.appResource = appResource;
	}
	@Transient
	public List<SurveyInfoDto> getVotes() {
		return votes;
	}
	@Transient
	public void setVotes(List<SurveyInfoDto> votes) {
		this.votes = votes;
	}
	@Transient
	public List<SurveyInfoDto> getArenas() {
		return arenas;
	}
	@Transient
	public void setArenas(List<SurveyInfoDto> arenas) {
		this.arenas = arenas;
	}
	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((informationId == null) ? 0 : informationId.hashCode());
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
		InformationContent other = (InformationContent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (informationId == null) {
			if (other.informationId != null)
				return false;
		} else if (!informationId.equals(other.informationId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
