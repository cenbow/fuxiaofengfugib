package com.cqliving.cloud.online.shopping.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplate;

/**
 * 运费模板表 JPA Dao
 * Date: 2016-11-17 15:41:20
 * @author Code Generator
 */
public interface ShoppingFareTemplateDao extends EntityJpaDao<ShoppingFareTemplate, Long>,ShoppingFareTemplateDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ShoppingFareTemplate set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
}
