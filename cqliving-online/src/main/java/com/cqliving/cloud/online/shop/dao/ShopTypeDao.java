package com.cqliving.cloud.online.shop.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.shop.domain.ShopType;
import com.cqliving.cloud.online.shop.dto.ShopTypeDto;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * 商铺类型表 JPA Dao
 * Date: 2016-05-18 11:31:09
 * @author Code Generator
 */
public interface ShopTypeDao extends EntityJpaDao<ShopType, Long>, ShopTypeDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ShopType set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);

	public PageInfo<ShopTypeDto> queryDtoForPage(PageInfo<ShopTypeDto> pageInfo, Map<String, Object> searchMap,
			Map<String, Boolean> sortMap);

}
