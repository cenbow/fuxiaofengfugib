package com.cqliving.cloud.online.config.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 区域表 JPA Dao
 * Date: 2016-05-16 17:31:38
 * @author Code Generator
 */
public interface ConfigRegionDao extends EntityJpaDao<ConfigRegion, Long>, ConfigRegionDaoCustom{

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年5月20日
	 * @param regionCode
	 * @return
	 */
	@Query("from ConfigRegion where appId = ?1 and code = ?2 order by id desc")
	List<ConfigRegion> getByCode(Long appId, String regionCode);
	
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ConfigRegion set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    @Modifying
    @Query("update ConfigRegion set sortNo=?1 where id in ?2")
    public int updateSortNo(Integer sortNo,List<Long> ids);
    
    @Query("from ConfigRegion where type = ?1 and status = 3 order by sortNo asc")
    public List<ConfigRegion> findByType(Byte type);
}
