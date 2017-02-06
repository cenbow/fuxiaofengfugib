package com.cqliving.cloud.online.info.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 资讯内容表 JPA Dao
 * Date: 2016-04-15 09:44:24
 * @author Code Generator
 */
public interface InformationContentDao extends EntityJpaDao<InformationContent, Long>,InformationContentDaoCustom {

	@Query(value="from InformationContent where informationId=?1 and status=3 order by sortNo asc")
	public List<InformationContent> findByInformationId(Long informationId);
	
	@Modifying
	@Query(value="update InformationContent set sortNo=?2 where id = ?1")
	public int sort(Long id,Integer sortNo);

	@Modifying
	@Query(value="update InformationContent set status=?2 where id = ?1")
	public int updateStatus(Long id,Byte status);
	/**
	 * <p>Description: 获取资讯内容</p>
	 * @author Tangtao on 2016年5月12日
	 * @param informationId
	 * @return
	 */
	@Query("from InformationContent where informationId = ?1 order by sortNo")
	List<InformationContent> getByInformation(Long informationId);
	
	@Modifying
	@Query(value = "update InformationContent set appId=?1,informationId=?2,title=?3,type=?4,sortNo=?5 where id = ?6 " )
	public int updateInfoContent(Long appId,Long infomationId,String title,Byte type,Integer sortNo,Long infoContentId);
}
