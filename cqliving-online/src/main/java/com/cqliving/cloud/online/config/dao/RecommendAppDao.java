package com.cqliving.cloud.online.config.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.config.domain.RecommendApp;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 区县推荐APP表，在重庆APP中使用 JPA Dao
 * Date: 2016-10-26 17:18:11
 * @author Code Generator
 */
public interface RecommendAppDao extends EntityJpaDao<RecommendApp, Long>, RecommendAppDaoCustom {
	
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update RecommendApp set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);

	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年10月26日
	 * @param id
	 * @param sortNo
	 */
	@Modifying
	@Query("update RecommendApp set sortNo = ?2 where id in ?1")
	void modifySortNo(Long id, Integer sortNo);

}