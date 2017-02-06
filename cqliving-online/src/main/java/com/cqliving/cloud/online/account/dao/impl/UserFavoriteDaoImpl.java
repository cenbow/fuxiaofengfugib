/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
/**
 * 
 */
package com.cqliving.cloud.online.account.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.account.dao.UserFavoriteDaoCustom;
import com.cqliving.cloud.online.account.domain.UserFavorite;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.cloud.online.joke.domain.JokeInfo;
import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author Tangtao on 2016年5月2日
 */
@Repository
public class UserFavoriteDaoImpl implements UserFavoriteDaoCustom {
	
//	@Resource(name = "onlinePagedJdbcTemplate")
//	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public ScrollPage<InfoClassifyDto> getMyFavoritesInfoPage(Long lastId, Long appId, Long userId) {
		//查询数据
		String sql = ""
				+ "SELECT "
				+ "	* "
				+ "FROM	"
				+ "	("
				+ "		SELECT "
				+ "			a.id, "
				+ "			a.information_id, "
				+ "			a.comment_type, "
				+ "			a.list_view_type, "
				+ "			a.online_time, "
				+ "			a.sort_no, "
				+ "			a.title, "
				+ "			a.list_title, "	//资讯列表标题 By Tangtao 2016-11-30
				+ "			b.info_source, "
				+ "			b.reply_count total_reply_count, "
				+ "			b.view_count detail_view_count, "
				+ "			b.type, "
				+ "			b.context_type, "
				+ "			b.info_label, "
				+ "			b.content_url, "
				+ "			b.multiple_img_count, "
				+ "			b.init_count, "
				+ "			b.add_type, "
				+ "			b.top_time, "
				+ "			b.synopsis, "
				+ "			c.image_url img_urls, "
				+ "			d.app_id, "
				+ "			d.id user_favorite_id, "
				+ "			d.status user_favorite_status, "
				+ "			d.user_id, "
				+ "			d.source_type "
				+ "		FROM "
				+ "			( "
				+ "				SELECT "
				+ "					id, "
				+ "					app_id, "
				+ "					user_id, "
				+ "					source_id, "
				+ "					source_type, "
				+ "					status "
				+ "				FROM "
				+ "					user_favorite "
				+ "				WHERE "
				+ "					app_id = " + appId + " "
				+ "					AND user_id = " + userId + " "
				+ "					" + (lastId == null ? "" : "AND id < " + lastId) + " "
				+ "					AND status = " + UserFavorite.STATUS3 + " "
				+ "				ORDER BY "
				+ "					id DESC "
				+ "				LIMIT 10 "
				+ "			) d "
				+ "		LEFT JOIN info_classify a ON d.source_id = a.id "
				+ "		LEFT JOIN information b ON a.information_id = b.id "
				+ "		LEFT JOIN info_classify_list c ON a.id = c.classify_id "
				+ "		WHERE "
				+ "			d.source_type = " + UserFavorite.SOURCETYPE1 + " "
				+ "			AND a.status = " + InfoClassify.STATUS3 + " "
				+ "		UNION ALL "
				+ "		SELECT "
				+ "			f.id, "
				+ "			NULL information_id, "
				+ "			NULL comment_type, "
				+ "			NULL list_view_type, "
				+ "			f.online_time, "
				+ "			0 sort_no, "
				+ "			CONCAT(g.name, '-段子') title, "
				+ "			CONCAT(g.name, '-段子') list_title, "	//列表标题 By Tangtao 2016-11-30
				+ "			NULL info_source, "
				+ "			f.reply_count total_reply_count, "
				+ "			0 detail_view_count, "
				+ "			NULL type, "
				+ "			NULL context_type, "
				+ "			NULL info_label, "
				+ "			NULL content_url, "
				+ "			NULL multiple_img_count, "
				+ "			NULL init_count, "
				+ "			NULL add_type, "
				+ "			NULL top_time, "
				+ "			f.content synopsis, "
				+ "			NULL img_urls, "
				+ "			e.app_id, "
				+ "			e.id user_favorite_id, "
				+ "			e.status user_favorite_status, "
				+ "			e.user_id, "
				+ "			e.source_type "
				+ "		FROM "
				+ "			("
				+ "				SELECT "
				+ "					id, "
				+ "					app_id, "
				+ "					user_id, "
				+ "					source_id, "
				+ "					source_type, "
				+ "					status "
				+ "				FROM "
				+ "					user_favorite "
				+ "				WHERE "
				+ "					app_id = " + appId + " "
				+ "					AND user_id = " + userId + " "
				+ "					" + (lastId == null ? "" : "AND id < " + lastId) + " "
				+ "					AND status = " + UserFavorite.STATUS3 + " "
				+ "				ORDER BY "
				+ "					id DESC "
				+ "				LIMIT 10"
				+ "			) e "
				+ "		LEFT JOIN joke_info f ON e.source_id = f.id "
				+ "		LEFT JOIN app_info g ON e.app_id = g.id "
				+ "		WHERE "
				+ "			e.source_type = " + UserFavorite.SOURCETYPE5 + " "
				+ "			AND f.status = " + JokeInfo.STATUS3 + " "
				+ "	) t "
				+ "ORDER BY "
				+ "	user_favorite_id DESC";
//		List<InfoClassifyDto> pageResults = mysqlPagedJdbcTemplate.queryForList(sql, InfoClassifyDto.class);
		List<InfoClassifyDto> pageResults = mysqlPagedJdbcTemplateV2.queryForList(sql, InfoClassifyDto.class);
		ScrollPage<InfoClassifyDto> page = new ScrollPage<InfoClassifyDto>();
		page.setPageResults(pageResults);
		return page;
	}

	@Override
	public ScrollPage<ShoppingGoodsDto> getMyFavoritesShoppingPage(Long lastFavoriteId, Long appId, Long userId) {
		//查询数据
		String sql = ""
				+ "SELECT "
				+ "	a.id AS favorite_id, "
				+ "	a.source_id, "
				+ "	b.list_image_url, "
				+ "	b.name, "
				+ "	b.labels, "
				+ "	b.price, "
				+ "	b.original_price, "
				+ "	b.collect_count "
				+ "FROM "
				+ "	user_favorite a "
				+ "LEFT JOIN shopping_goods b ON a.source_id = b.id "
				+ "WHERE "
				+ "	a.source_type = " + UserFavorite.SOURCETYPE13 + " "
				+ "	AND a.status = " + UserFavorite.STATUS3 + " "
				+ "	AND b.status = " + ShoppingGoods.STATUS3 + " "
				+ "	AND a.user_id = " + userId;
		ScrollPage<ShoppingGoodsDto> scrollPage = new ScrollPage<ShoppingGoodsDto>();
		scrollPage.setPageSize(10);
		scrollPage.addScrollPageOrder(new ScrollPageOrder("favorite_id", ScrollPage.DESC, lastFavoriteId));
		mysqlPagedJdbcTemplateV2.queryPage(ShoppingGoodsDto.class, scrollPage, sql, null);
		return scrollPage;
	}

}