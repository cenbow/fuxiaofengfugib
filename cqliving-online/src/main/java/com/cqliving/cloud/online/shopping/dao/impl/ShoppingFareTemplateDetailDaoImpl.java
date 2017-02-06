package com.cqliving.cloud.online.shopping.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.shopping.dao.ShoppingFareTemplateDetailDaoCustom;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateDetail;
import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlExtendJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月28日
 */
public class ShoppingFareTemplateDetailDaoImpl implements ShoppingFareTemplateDetailDaoCustom {
	
	@Autowired
    private MysqlExtendJdbcTemplateV2 mysqlExtendJdbcTemplateV2;

	@Override
	public ShoppingFareTemplateDetail getFareByUserAddress(Long shoppingUserAddressId, Long shippingFareTemplateId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" a.base_fare_count, a.base_fare, a.add_fare_count, a.add_fare");
		sql.append(" FROM shopping_fare_template_detail a");
		sql.append(" LEFT JOIN shopping_fare_template_region_detail b ON a.ID=b.fare_template_detail_id");
		sql.append(" WHERE");
		sql.append(" a.fare_template_id=?");//根据商品选择的运费模板
		sql.append(" AND b.region_id IN(SELECT region_level_two_id FROM shopping_user_address c WHERE c.ID=? AND c.status=?)");
		sql.append(" AND b.region_level=? AND a.`status`=?");
		try {
			return mysqlExtendJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(ShoppingFareTemplateDetail.class), shippingFareTemplateId, shoppingUserAddressId, ShoppingUserAddress.STATUS3, 2, ShoppingFareTemplateDetail.STATUS3);
		} catch (EmptyResultDataAccessException e) {
		}
		return null;
	}

	@Override
	public ShoppingFareTemplateDetail getBaseFareByShoppingFareTempleteId(Long shoppingFareTempleteId) {
		StringBuilder sql = new StringBuilder("SELECT a.base_fare_count, a.base_fare, a.add_fare_count, a.add_fare FROM shopping_fare_template_detail a WHERE a.fare_template_id=? AND a.`status`=? AND a.region_names IS NULL;");
		try {
			return mysqlExtendJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(ShoppingFareTemplateDetail.class), shoppingFareTempleteId, ShoppingFareTemplateDetail.STATUS3);
		} catch (EmptyResultDataAccessException e) {
		}
		return null;
	}
	
	
}
