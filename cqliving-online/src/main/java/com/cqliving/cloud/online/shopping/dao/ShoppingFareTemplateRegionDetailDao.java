package com.cqliving.cloud.online.shopping.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateRegionDetail;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 运费模板明细地区表 JPA Dao
 * Date: 2016-11-17 15:41:29
 * @author Code Generator
 */
public interface ShoppingFareTemplateRegionDetailDao extends EntityJpaDao<ShoppingFareTemplateRegionDetail, Long> {
	
	@Query(value="from ShoppingFareTemplateRegionDetail where fareTemplateDetailId=?1")
	public List<ShoppingFareTemplateRegionDetail> findByTmpDetailId(Long tmpDetailId);
}
