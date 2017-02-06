package com.cqliving.cloud.online.info.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 资讯表 JPA Dao
 * Date: 2016-04-15 09:44:20
 * @author Code Generator
 */
public interface InformationDao extends EntityJpaDao<Information, Long>, InformationDaoCustom {
	
	@Modifying
	@Query(value="update Information set praiseCount = ifnull(praiseCount,0) + 1 where id = ?1 ")
	public int addPraiseCount(Long id);
	@Modifying
	@Query("update Information set replyCount = ifnull(replyCount,0) + ?2 where id = ?1")
	int addReplyCount(Long infoId,int num);
	@Modifying
	@Query("update Information set viewCount = ifnull(viewCount,0) + ?2 where id = ?1")
	int addViewCount(Long infoId,int num);
	@Modifying
	@Query("update Information set multipleImgCount = ?1 where id = ?2")
	public int setMultiImgNum(int num,Long id);
	@Modifying
	@Query("update Information set classifyId = ?1 where id = ?2")
	public int updateClassifyId(Long classifyId,Long infoId);
}
