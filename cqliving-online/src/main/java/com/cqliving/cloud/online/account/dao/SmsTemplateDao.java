package com.cqliving.cloud.online.account.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.account.domain.SmsTemplate;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 短信模版表 JPA Dao
 * Date: 2016-05-18 20:40:17
 * @author Code Generator
 */
public interface SmsTemplateDao extends EntityJpaDao<SmsTemplate, Long> {

	/**
	 * <p>Description: 获取短信模板</p>
	 * @author Tangtao on 2016年5月18日
	 * @param appId
	 * @param type
	 * @return
	 */
	@Query("from SmsTemplate where appId = ?1 and type = ?2 order by id desc")
	List<SmsTemplate> getByAppAndType(Long appId, Byte type);
	
	/**
	 * <p>Description: 获取短信模板通过appId</p>
	 * @author huxiaoping on 2016年6月1日
	 * @param appId
	 * @return
	 */
	@Query("from SmsTemplate where appId = ?1 order by type asc")
	List<SmsTemplate> getByAppId(Long appId);
	
}
