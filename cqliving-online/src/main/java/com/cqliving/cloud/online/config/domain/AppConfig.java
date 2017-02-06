package com.cqliving.cloud.online.config.domain;


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
 * app配置表 Entity
 * Date: 2016-07-16 11:09:01
 * @author Code Generator
 */
@Entity
@Table(name = "app_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppConfig extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** 主键ID */
	private Long id;
	/** app_id */
	private Long appId;
	/** app下载地址 */
	private String downLoadUrl;
	/** Android栏目更新对应最小的客户端版本，如果该值不为空，且当客户端（更新版本号，例如2005，非V2.0.5）版本大于等于该版本时才更新栏目，否则不更新 */
	private String columnVersionAndroid;
	/** IOS栏目更新对应最小的客户端版本，如果该值不为空，且当客户端（更新版本号，例如2005，非V2.0.5）版本大于等于该版本时才更新栏目，否则不更新 */
	private String columnVersionIos;
	
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
	public String getDownLoadUrl(){
		return this.downLoadUrl;
	}
	
	public void setDownLoadUrl(String downLoadUrl){
		this.downLoadUrl = downLoadUrl;
	}
	
	public String getColumnVersionAndroid() {
		return columnVersionAndroid;
	}

	public void setColumnVersionAndroid(String columnVersionAndroid) {
		this.columnVersionAndroid = columnVersionAndroid;
	}

	public String getColumnVersionIos() {
		return columnVersionIos;
	}

	public void setColumnVersionIos(String columnVersionIos) {
		this.columnVersionIos = columnVersionIos;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
