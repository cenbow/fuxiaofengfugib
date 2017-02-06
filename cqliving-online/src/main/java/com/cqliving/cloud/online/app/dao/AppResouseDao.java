package com.cqliving.cloud.online.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.app.domain.AppResouse;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 资讯资源表【文字,图文,视频,音频】 JPA Dao
 * Date: 2016-04-15 09:43:54
 * @author Code Generator
 */
public interface AppResouseDao extends EntityJpaDao<AppResouse, Long> {

	@Query(value="from AppResouse where informationContentId=?1 and status=3 order by sortNo asc")
	public List<AppResouse> findByInfoContentId(Long informationContentId);
	
	@Query(value="from AppResouse where informationId=?1 and status=3 order by sortNo asc")
	public List<AppResouse> findByInformationId(Long informationId);
}
