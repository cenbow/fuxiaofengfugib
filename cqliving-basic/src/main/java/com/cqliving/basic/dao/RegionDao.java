package com.cqliving.basic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.basic.domain.Region;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 行政区域表; InnoDB free: 140288 kB JPA Dao
 *
 * Date: 2014-03-26 10:34:29
 *
 * @author Acooly Code Generator
 *
 */
public interface RegionDao extends EntityJpaDao<Region,Long> {
	
	/**
	 * 查询1级列表
	 * <p>Description:</p>
	 * @return
	 */
	@Query(value = "FROM Region WHERE pcode=0 AND hieraechy=1 ORDER BY code")
	List<Region> queryByOneLevelList();
	
	/**
	 * 查询除1级外的区域列表
	 * 
	 * <p>Description:</p>
	 * @return
	 */
	@Query(value = "FROM Region WHERE hieraechy>1 ORDER BY pcode")
	List<Region> queryByOtherLevelList();

	/**
	 * 根据区域编码，取该编码下级区域列表
	 * @param pcode
	 * @return
	 */
	List<Region> findByPcode(String pcode);

}
