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

import com.cqliving.cloud.online.shopping.dao.ShoppingRecommendDaoCustom;
import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.cloud.online.shopping.domain.ShoppingRecommend;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.cloud.online.shopping.dto.ShoppingRecommendDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年11月21日
 */
@Repository
public class ShoppingRecommendDaoImpl implements ShoppingRecommendDaoCustom {
	
	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public List<ShoppingGoodsDto> getCarouselByAppId(Long appId) {
		//查询数据
		String sql = ""
				+ "SELECT "
				+ "	a.app_id, "
				+ "	a.image_url AS list_image_url, "
				+ "	a.sort_no, "
				+ "	b.id, "
				+ "	b.name, "
				+ "	b.synopsis "
				+ "FROM "
				+ "	shopping_recommend a "
				+ "INNER JOIN shopping_goods b ON a.shopping_goods_id = b.id "
				+ "WHERE "
				+ "	a.type = " + ShoppingRecommend.TYPE2 + " "
				+ "AND a.status = " + ShoppingRecommend.STATUS3 + " "
				+ "AND b.status = " + ShoppingGoods.STATUS3;
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		Map<String, Boolean> orders = Maps.newLinkedHashMap();
		orders.put("sort_no", true);
		List<ShoppingGoodsDto> list = mysqlPagedJdbcTemplateV2.queryForList(ShoppingGoodsDto.class, sql, conditions, orders);
		return list;
	}

	@Override
	public List<ShoppingGoodsDto> getIndex(Map<String, Object> conditionMap, Map<String, Boolean> orderMap) {
		//查询数据
		String sql = ""
				+ "SELECT "
				+ "	a.app_id, "
				+ "	a.sort_no, "
				+ "	b.id, "
				+ "	b.list_image_url, "
				+ "	b.name, "
				+ "	b.labels, "
				+ "	b.price, "
				+ "	b.original_price, "
				+ "	b.synopsis, "
				+ "	b.collect_count, "
				+ "	b.stock, "
				+ "	c.id AS category_level_one_id, "
				+ "	c.name category_level_one_name "
				+ "FROM "
				+ "	shopping_recommend a "
				+ "LEFT JOIN shopping_goods b ON a.shopping_goods_id = b.id "
				+ "LEFT JOIN shopping_category c ON b.category_level_one_id = c.id "
				+ "WHERE "
				+ "	a.type = " + ShoppingRecommend.TYPE1 + " "
				+ "	AND a.status = " + ShoppingRecommend.STATUS3 + " "
				+ "	AND b.status = " + ShoppingGoods.STATUS3;
		List<ShoppingGoodsDto> list = mysqlPagedJdbcTemplateV2.queryForList(ShoppingGoodsDto.class, sql, conditionMap, orderMap);
		return list;
	}

	/**
     * 分页查询商城首页信息
        SELECT a.*,b.name shopping_goods_name,c.id shopping_category_id,c.name shopping_category_name FROM shopping_recommend a 
        LEFT JOIN shopping_goods b ON a.shopping_goods_id = b.id 
        LEFT JOIN shopping_category c ON b.category_level_one_id = c.id
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月22日下午2:45:25
     */
    @Override
    public PageInfo<ShoppingRecommendDto> queryDtoForPage(PageInfo<ShoppingRecommendDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.*,b.name shopping_goods_name,c.id shopping_category_id,c.name shopping_category_name FROM shopping_recommend a ");
        sql.append("LEFT JOIN shopping_goods b ON a.shopping_goods_id = b.id ");
        sql.append("LEFT JOIN shopping_category c ON b.category_level_one_id = c.id ");
        mysqlPagedJdbcTemplateV2.queryForPage(ShoppingRecommendDto.class, sql.toString(), map, pageInfo, orderMap);
        return pageInfo;
    }
    
    @Override
    public ShoppingRecommendDto getById(Long id) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.*,b.name shopping_goods_name,c.id shopping_category_id,c.name shopping_category_name,d.name app_name FROM shopping_recommend a ");
        sql.append("LEFT JOIN shopping_goods b ON a.shopping_goods_id = b.id ");
        sql.append("LEFT JOIN shopping_category c ON b.category_level_one_id = c.id ");
        sql.append("LEFT JOIN app_info d ON a.app_id = d.id ");
        sql.append("where a.id=? ");
        return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(ShoppingRecommendDto.class), id);
    }

}