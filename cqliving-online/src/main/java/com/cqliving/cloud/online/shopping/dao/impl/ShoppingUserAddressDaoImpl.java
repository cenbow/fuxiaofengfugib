package com.cqliving.cloud.online.shopping.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.shopping.dao.ShoppingUserAddressDaoCustom;
import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlExtendJdbcTemplateV2;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.google.common.collect.Maps;

public class ShoppingUserAddressDaoImpl implements ShoppingUserAddressDaoCustom{
	
	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	@Autowired
	private MysqlExtendJdbcTemplateV2 mysqlExtendJdbcTemplateV2;
	/**
	 * 查询收货地址列表
	 */
	@Override
	public ScrollPage<ShoppingUserAddress> queryAddressPage(ScrollPage<ShoppingUserAddress> page, Long appId, Long userId) {
		String sql = "SELECT sua.* FROM shopping_user_address sua";
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_userId", userId);
		conditions.put("NOTEQ_status", ShoppingUserAddress.STATUS99);
		mysqlPagedJdbcTemplateV2.queryPage(ShoppingUserAddress.class, page, sql.toString(), conditions);
		return page;
	}
	
	@Override
	public ShoppingUserAddress getUserDefault(Long appId, Long userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from shopping_user_address where app_id=? and user_id=? and status=? and is_default=?");
		try {
			return mysqlExtendJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(ShoppingUserAddress.class), appId, userId, ShoppingUserAddress.STATUS3, ShoppingUserAddress.ISDEFAULT1);
		} catch (EmptyResultDataAccessException e) {
		}
		return null;
	}
	
	
}
