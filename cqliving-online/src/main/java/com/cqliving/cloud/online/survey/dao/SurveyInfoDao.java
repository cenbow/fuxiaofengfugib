package com.cqliving.cloud.online.survey.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.survey.domain.SurveyInfo;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 调研表 JPA Dao
 * Date: 2016-04-15 09:46:50
 * @author Code Generator
 */
public interface SurveyInfoDao extends EntityJpaDao<SurveyInfo, Long>,SurveyInfoDaoCustom {

	/**
	 * <p>Description: 获取调研</p>
	 * @author Tangtao on 2016年5月12日
	 * @param informationId
	 * @return
	 */
	@Query("from SurveyInfo where informationId = ?1 and status=3 order by id desc")
	List<SurveyInfo> getByInformation(Long informationId);

}
