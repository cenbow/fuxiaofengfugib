/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.shopping.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.shopping.dao.ShoppingGoodsDaoCustom;
import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年11月25日
 */
@Repository
public class ShoppingGoodsDaoImpl implements ShoppingGoodsDaoCustom {
	
	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public ScrollPage<ShoppingGoodsDto> queryForScrollPage(ScrollPage<ShoppingGoodsDto> scrollPage, Map<String, Object> conditionMap) {
		//查询数据
		String sql = ""
				+ "SELECT "
				+ "	t.* "
				+ "FROM "
				+ "	("
				+ "		SELECT "
				+ "			ROUND((a.original_price - a.price) / a.original_price * 10000, 0) AS discount, "
				+ "			a.* "
				+ "		FROM "
				+ "			shopping_goods a "
				+ "		WHERE "
				+ "			a. STATUS = " + ShoppingGoods.STATUS3 + " "
				+ "			AND a.online_time <= NOW()"
				+ "	) t";
		scrollPage = mysqlPagedJdbcTemplateV2.queryPage(ShoppingGoodsDto.class, scrollPage, sql, conditionMap);
		return scrollPage;
	}

	@Override
	public List<ShoppingGoodsDto> getMyCart(Map<String, Object> conditionMap, Map<String, Boolean> orders) {
		//查询数据
		String sql = ""
				+ "SELECT "
				+ "	a.id AS cart_id, "
				+ "	a.quantity, "
				+ "	a.user_id, "
				+ "	b.* "
				+ "FROM "
				+ "	order_shop_cart a "
				+ "LEFT JOIN shopping_goods b ON a.goods_id = b.id "
				+ "WHERE "
				+ "	(b.status = " + ShoppingGoods.STATUS3 + " OR b.status = " + ShoppingGoods.STATUS88 + ")";
		List<ShoppingGoodsDto> list = mysqlPagedJdbcTemplateV2.queryForList(ShoppingGoodsDto.class, sql, conditionMap, orders);
		return list;
	}

	/**
     * 分页查询商品信息
        SELECT a.*,b.name FROM shopping_goods a INNER JOIN shopping_category b ON a.category_level_one_id =b.id 
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月1日上午9:43:57
     */
    @Override
    public PageInfo<ShoppingGoodsDto> queryByPage(PageInfo<ShoppingGoodsDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.*,b.name category_name FROM shopping_goods a INNER JOIN shopping_category b ON a.category_level_one_id =b.id ");
        mysqlPagedJdbcTemplateV2.queryForPage(ShoppingGoodsDto.class, sql.toString(), map, pageInfo, orderMap);
        return pageInfo;
    }
    
    @Override
    public ShoppingGoodsDto getById(Long id) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.*,b.name category_name FROM shopping_goods a INNER JOIN shopping_category b ON a.category_level_one_id =b.id ");
        sql.append("where a.id=? ");
        return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(ShoppingGoodsDto.class), id);
    }
}