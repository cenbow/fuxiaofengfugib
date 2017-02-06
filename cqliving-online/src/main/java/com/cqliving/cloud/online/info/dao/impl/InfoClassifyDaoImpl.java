/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.info.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.online.config.domain.RecommendInfo;
import com.cqliving.cloud.online.info.dao.InfoClassifyDaoCustom;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.framework.common.dao.jdbc.MysqlPagedJdbcTemplate;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.tool.common.util.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月28日
 */
@Repository
public class InfoClassifyDaoImpl implements InfoClassifyDaoCustom {
	
	@Resource(name = "onlinePagedJdbcTemplate")
	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public PageInfo<InfoClassifyDto> queryDtoForPage(PageInfo<InfoClassifyDto> pageInfo, Map<String, Object> map, Map<String, Boolean> sortMap) {
		//查询数据
		StringBuilder sql = new StringBuilder();
		sql.append(""
				+ "SELECT "
				+ "	a.id, "
				+ " 	a.app_id, "
				+ " 	a.title, "
//				+ " 	a.view_count, "
				+ " 	b.view_count, "	//CMS列表的浏览量显示information表的数据 By Tangtao 2017-01-24
				+ " 	a.reply_count, "
				+ " 	a.online_time, "
				+ " 	a.status, "
				+ " 	CASE "
				+ " 		WHEN a. STATUS = 0 THEN 1 "
				+ " 		WHEN a. STATUS = - 1 THEN 1 "
				+ " 		WHEN a. STATUS = 1 THEN 1 "
				+ " 		WHEN a. STATUS = 3 THEN 1 "
				+ " 		WHEN a. STATUS = 88 THEN 2 "
				+ " 		ELSE a.status "
				+ " 	END status_no,"	//状态序号 By Tagntao 2016-12-01
				+ " 	a.sort_no, "
				+ " 	a.information_id, "
				+ " 	a.list_view_type, "
				+ " 	a.columns_id, "
				+ " 	a.creator, "
				+ " 	a.add_special_status, "
				+ " 	a.send_status, "
				+ " 	a.create_time, "
				+ " 	a.update_time, "
				+ " 	a.comment_validate_type, "
				+ " 	a.comment_type, "
				+ " 	a.is_recommend, "
				+ "	a.hlw_old_guid, "
				+ "	a.special_theme, "		//专题主题 By Tangtao 2016-11-24
				+ " 	a.updator, "	//编辑人 By Tangtao 2016-11-24
				+ " 	a.update_time, "	//编辑时间 By Tangtao 2016-11-25
				+ " 	a.list_title, "	//列表标题 By Tangtao 2016-11-25
				+ " 	b.context_type, "
				+ " 	b.type, "
				+ " 	b.validate_type, "
				+ " 	b.keywords, "
				+ " 	b.info_label, "
				+ "	b.synopsis, "
				+ " 	c.name columns_name "
				+ "FROM "
				+ " 	info_classify a "
				+ "LEFT JOIN information b ON a.information_id = b.id "
				+ "LEFT JOIN app_columns c ON a.columns_id = c.id");
		
		String orLikeTitle = (String) map.get("or_LIKE_specialTheme");
		if(!StringUtil.isEmpty(orLikeTitle)){
			sql.append(" where (a.title like '%").append(orLikeTitle).append("%' ");
			sql.append(" or a.special_theme like '%").append(orLikeTitle).append("%') ");
			sql.append(" ${WHERE} ");
			map.remove("LIKE_title");
			map.remove("or_LIKE_specialTheme");
		}
		mysqlPagedJdbcTemplateV2.queryForPage(InfoClassifyDto.class, sql.toString(), map, pageInfo, sortMap);
		return pageInfo; 
	}

	@Override
	public PageInfo<InfoClassifyDto> queryDtoForCopyPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		String title = "";
		if (searchMap.containsKey("LIKE_title")) {
			title = (String) searchMap.get("LIKE_title");
			searchMap.remove("LIKE_title");
		}
		//查询数据
		StringBuilder sql = new StringBuilder();
//		sql.append(""
//				+ "SELECT "
//				+ "    a.*, "
//				+ "    c.name columns_name "
//				+ "FROM	"
//				+ "    info_classify a "
//				+ "LEFT JOIN app_columns c ON a.columns_id = c.id "
//				+ "WHERE "
//				+ "    EXISTS ("
//				+ " SELECT	"
//				+ " 1 "
//				+ " FROM "
//				+ " info_classify b "
//				+ " WHERE "
//				+ " a.information_id = b.information_id "
//				+ " AND b.title LIKE '%" + title + "%'"
//				+ ") "
//				+ "ORDER BY "
//				+ "    a.online_time DESC, "
//				+ "    a.id DESC");
		sql.append(""
				+ "SELECT "
				+ "    a.*, "
				+ "    c.name columns_name "
				+ "FROM	"
				+ "    info_classify a "
				+ "LEFT JOIN app_columns c ON a.columns_id = c.id "
				+ "WHERE "
				+ "    EXISTS ("
				+ " 		SELECT	"
				+ " 			1 "
				+ " 		FROM "
				+ " 			info_classify b "
				+ " 		WHERE "
				+ " 			a.information_id = b.information_id "
				+ " 		AND a.id <> b.id "
				+ " 		AND (b.title LIKE '%" + title + "%' or a.title LIKE '%" + title + "%') "
				+ "	) "
				+ "	AND a.status <> " + InfoClassify.STATUS99);
//		mysqlPagedJdbcTemplate.queryForPage(InfoClassifyDto.class, sql.toString(), searchMap, pageInfo, sortMap);
		mysqlPagedJdbcTemplateV2.queryForPage(InfoClassifyDto.class, sql.toString().toUpperCase().replaceAll("	", " "), searchMap, pageInfo, sortMap);
		return pageInfo;
	}

	@Override
	public PageInfo<InfoClassifyDto> queryDtoForRecommendPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		//查询数据
		StringBuilder sql = new StringBuilder();
//		sql.append(""
//				+ "SELECT "
//				+ "    a.id comment_id, "
//				+ "    a.status recommend_status, "
//				+ "    a.source_app_id source_recommend_app_id, "
//				+ "    a.target_app_id, "
//				+ "    a.comment_time,	"
//				+ "    b.id, "
//				+ "    b.title, "
//				+ "    b.view_count, "
//				+ "    b.reply_count, "
//				+ "    b.online_time, "
//				+ "    b.list_view_type, "
//				+ "    b.status, "
//				+ "    c.name columns_name, "
//				+ "    d.type, "
//				+ "    d.context_type "
//				+ "FROM "
//				+ "    info_classify_comment a "
//				+ "LEFT JOIN info_classify b ON a.source_info_classify_id = b.id "
//				+ "LEFT JOIN app_columns c ON b.columns_id = c.id "
//				+ "LEFT JOIN information d ON b.information_id = d.id "
//				+ "ORDER BY "
//				+ "    a.status, "
//				+ "    a.comment_time DESC");
		sql.append(""
				+ "SELECT "
				+ "    a.id comment_id, "
				+ "    a.status recommend_status, "
				+ "    a.source_app_id source_recommend_app_id, "
				+ "    a.target_app_id, "
				+ "    a.comment_time,	"
				+ "    b.id, "
				+ "    b.title, "
				+ "    b.view_count, "
				+ "    b.reply_count, "
				+ "    b.online_time, "
				+ "    b.list_view_type, "
				+ "    b.status, "
				+ "    c.name columns_name, "
				+ "    d.type, "
				+ "    d.context_type "
				+ "FROM "
				+ "    info_classify_comment a "
				+ "LEFT JOIN info_classify b ON a.source_info_classify_id = b.id "
				+ "LEFT JOIN app_columns c ON b.columns_id = c.id "
				+ "LEFT JOIN information d ON b.information_id = d.id");
//		mysqlPagedJdbcTemplate.queryForPage(InfoClassifyDto.class, sql.toString(), searchMap, pageInfo, sortMap);
		mysqlPagedJdbcTemplateV2.queryForPage(InfoClassifyDto.class, sql.toString(), searchMap, pageInfo, sortMap);
		return pageInfo;
	}

	@Override
	public PageInfo<InfoClassifyDto> queryDtoForSpecialSubPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		//查询数据
		StringBuilder sql = new StringBuilder();
//		sql.append(""
//				+ "SELECT "
//				+ "    a.id correlation_id, "
//				+ "    a.app_id, "
//				+ "    a.sort_no, "
//				+ "    a.theme_id, "
//				+ "    a.info_classify_id, "
//				+ "    b.id, "
//				+ "    b.title, "
//				+ "    b.view_count, "
//				+ "    b.reply_count, "
//				+ "    b.online_time, "
//				+ "    b.status, "
//				+ "    b.add_special_status, "
//				+ "    b.list_view_type, "
//				+ "    b.creator, "
//				+ "    c.name theme_name "
//				+ "FROM	"
//				+ "    info_correlation a "
//				+ "LEFT JOIN info_classify b ON a.ref_info_classify_id = b.id "
//				+ "LEFT JOIN info_theme c ON a.theme_id = c.id "
//				+ "ORDER BY "
//				+ "    a.sort_no,	"
//				+ "    b.online_time DESC");
		sql.append(""
				+ "SELECT "
				+ "   a.id correlation_id, "
				+ "   a.app_id, "
				+ "   a.sort_no, "
				+ "   a.theme_id, "
				+ "   a.info_classify_id, "
				+ "   b.id, "
				+ "	b.information_id, "
				+ "   b.title, "
				+ "   b.view_count, "
				+ "   b.reply_count, "
				+ "   b.online_time, "
				+ "   b.status, "
				+ " 	CASE "
				+ " 		WHEN b.status = 0 THEN 1 "
				+ " 		WHEN b.status = - 1 THEN 1 "
				+ " 		WHEN b.status = 1 THEN 1 "
				+ " 		WHEN b.status = 3 THEN 1 "
				+ " 		WHEN b.status = 88 THEN 2 "
				+ " 		ELSE b.status "
				+ " 	END status_no,"	//状态序号 By Tagntao 2016-11-25
				+ "   b.add_special_status, "
				+ "   b.list_view_type, "
				+ "   b.creator, "
				+ "   b.updator, "	//修改人 By Tangtao 2016-12-01
				+ "	b.update_time, "	//修改时间 By Tagntao 2016-11-25
				+ " 	b.send_status, "	//推送状态 By Tagntao 2016-11-25
				+ "   c.name theme_name, "
				+ "	d.synopsis "	//摘要 By Tagntao 2016-11-25
				+ "FROM	"
				+ "	info_correlation a "
				+ "LEFT JOIN info_classify b ON a.ref_info_classify_id = b.id "
				+ "LEFT JOIN info_theme c ON a.theme_id = c.id "
				+ "LEFT JOIN information d ON b.information_id = d.id");
//		mysqlPagedJdbcTemplate.queryForPage(InfoClassifyDto.class, sql.toString(), searchMap, pageInfo, sortMap);
		mysqlPagedJdbcTemplateV2.queryForPage(InfoClassifyDto.class, sql.toString().toUpperCase().replaceAll("	", " "), searchMap, pageInfo, sortMap);
		return pageInfo;
	}

	@Override
	public ScrollPage<InfoClassifyDto> queryDtoForScrollPage(ScrollPage<InfoClassifyDto> page, Map<String, Object> conditions, Boolean isCarousel) {
		//查询数据
		StringBuilder sql = new StringBuilder();
		Map<String, Object> map = Maps.newHashMap(conditions);
//		sql.append(""
//				+ "SELECT "
//				+ "    a.*, "
//				+ "    b.info_source, "
//				+ "    b.reply_count total_reply_count, "
//				+ "    b.type, "
//				+ "    b.context_type, "
//				+ "    b.info_label, "
//				+ "    b.content_url, "
//				+ "    b.multiple_img_count, "
//				+ "    b.init_count, "
//				+ "    b.add_type, "
//				+ "    b.top_time, "
//				+ "    b.view_count detail_view_count, "
//				+ "    b.synopsis, "
//				+ "    c.image_url img_urls "
//				+ "FROM "
//				+ "    info_classify a "
//				+ "LEFT JOIN information b ON a.information_id = b.id "
//				+ "LEFT JOIN info_classify_list c ON a.id = c.classify_id "
//				+ "WHERE "
//				+ "    a.online_time < NOW() "
//				+ "AND a.add_special_status = " + InfoClassify.ADDSPECIALSTATUS0 + " "
//				+ "AND b.video_status = " + Information.VIDEOSTATUS3+ " "
//				+ "ORDER BY "
//				+ "    a.sort_no, "
//				+ "    a.online_time DESC, "
//				+ "    a.id DESC");
		sql.append(""
				+ "SELECT "
				+ "    a.*, "
				+ "    b.info_source, "
				+ "    b.reply_count total_reply_count, "
				+ "    b.type, "
				+ "    b.context_type, "
				+ "    b.info_label, "
				+ "    b.content_url, "
				+ "    b.multiple_img_count, "
				+ "    b.init_count, "
				+ "    b.add_type, "
				+ "    b.top_time, "
				+ "    b.view_count detail_view_count, "
				+ "    b.synopsis, "
				+ "    c.image_url img_urls "
				+ "FROM "
				+ "    info_classify a "
				+ "LEFT JOIN information b ON a.information_id = b.id "
				+ "LEFT JOIN info_classify_list c ON a.id = c.classify_id "
				+ "WHERE "
				+ "    a.online_time < NOW() "
				+ "AND a.add_special_status = " + InfoClassify.ADDSPECIALSTATUS0 + " "
				+ "AND b.video_status = " + Information.VIDEOSTATUS3);
		if (isCarousel != null) {
			if (isCarousel) {	//轮播
				map.put("EQ_listViewType", InfoClassify.LISTVIEWTYPE4);
			} else {	//非轮播
				map.put("NOTEQ_listViewType", InfoClassify.LISTVIEWTYPE4);
			}
		}
//		mysqlPagedJdbcTemplate.queryPage(InfoClassifyDto.class, page, sql.toString(), map);
		mysqlPagedJdbcTemplateV2.queryPage(InfoClassifyDto.class, page, sql.toString(), map);
		return page;
	}

	@Override
	public ScrollPage<InfoClassifyDto> queryWxlDtoForScrollPage(ScrollPage<InfoClassifyDto> page, Map<String, Object> conditions, Boolean isCarousel, boolean onlyWechat) {
		//查询数据
		StringBuilder sql = new StringBuilder();
		Map<String, Object> map = Maps.newHashMap(conditions);
		sql.append(""
				+ "SELECT "
				+ "    a.*, "
				+ "    b.info_source, "
				+ "    b.reply_count total_reply_count, "
				+ "    b.type, "
				+ "    b.context_type, "
				+ "    b.info_label, "
				+ "    b.content_url, "
				+ "    b.multiple_img_count, "
				+ "    b.init_count, "
				+ "    b.add_type, "
				+ "    b.top_time, "
				+ "    b.view_count detail_view_count, "
				+ "    b.synopsis, "
				+ "    c.image_url img_urls "
				+ "FROM "
				+ "    info_classify a "
				+ "LEFT JOIN information b ON a.information_id = b.id "
				+ "LEFT JOIN info_classify_list c ON a.id = c.classify_id "
				+ "WHERE "
				+ "    a.online_time < NOW() "
				+ "AND a.add_special_status = " + InfoClassify.ADDSPECIALSTATUS0 + " "
				+ "AND b.video_status = " + Information.VIDEOSTATUS3 + " "
				+ "AND b.type = " + Information.TYPE0 + " "
				+ "AND b.context_type <> " + Information.CONTEXTTYPE3 + " ");
		if (onlyWechat) {	//是否只查询推荐到微信小程序的新闻
			sql.append("AND a.is_view_wechat = " + InfoClassify.ISVIEWWECHAT1);
		}
		if (isCarousel != null) {
			if (isCarousel) {	//轮播
				map.put("EQ_listViewType", InfoClassify.LISTVIEWTYPE4);
			} else {	//非轮播
				map.put("NOTEQ_listViewType", InfoClassify.LISTVIEWTYPE4);
			}
		}
		mysqlPagedJdbcTemplateV2.queryPage(InfoClassifyDto.class, page, sql.toString(), map);
		return page;
	}

	@Override
	public ScrollPage<InfoClassifyDto> querySpecialSubDtoForScrollPage(ScrollPage<InfoClassifyDto> page, Map<String, Object> conditions) {
		//查询数据
		StringBuilder sql = new StringBuilder();
//		sql.append(""
//				+ "SELECT "
//				+ "    a.*, "
//				+ "    b.info_source, "
//				+ "    b.reply_count total_reply_count, "
//				+ "    b.view_count detail_view_count, "
//				+ "    b.type,b.synopsis, "
//				+ "    b.context_type, "
//				+ "    b.info_label, "
//				+ "    b.content_url, "
//				+ "    b.init_count, "
//				+ "    b.add_type, "
//				+ "    b.top_time, "
//				+ "    c.image_url img_urls, "
//				+ "    d.info_classify_id, "
//				+ "    d.theme_id, "
//				+ "    d.sort_no corre_sort_no  "
//				+ "FROM	"
//				+ "    info_correlation d "
//				+ "inner JOIN info_classify a ON d.ref_info_classify_id = a.id AND a.app_id = d.app_id "
//				+ "inner JOIN information b ON a.information_id = b.id "
//				+ "left JOIN info_classify_list c ON a.id = c.classify_id "
//				+ "WHERE "
//				+ "    a.online_time < NOW() and a.status=3 "
//				+ "AND a.add_special_status = " + InfoClassify.ADDSPECIALSTATUS1 + " "
//				+ "AND b.video_status = " + Information.VIDEOSTATUS3 + " "
//				+ " ORDER BY "
//				+ "    a.sort_no, "
//				+ "    a.online_time DESC, "
//				+ "    a.id DESC");
		sql.append(""
				+ "SELECT "
				+ "    a.*, "
				+ "    b.info_source,b.multiple_img_count, "
				+ "    b.reply_count total_reply_count, "
				+ "    b.view_count detail_view_count, "
				+ "    b.type,b.synopsis, "
				+ "    b.context_type, "
				+ "    b.info_label, "
				+ "    b.content_url, "
				+ "    b.init_count, "
				+ "    b.add_type, "
				+ "    b.top_time, "
				+ "    c.image_url img_urls, "
				+ "    d.info_classify_id, "
				+ "    d.theme_id, "
				+ "    d.sort_no corre_sort_no  "
				+ "FROM	"
				+ "    info_correlation d "
				+ "inner JOIN info_classify a ON d.ref_info_classify_id = a.id AND a.app_id = d.app_id "
				+ "inner JOIN information b ON a.information_id = b.id "
				+ "left JOIN info_classify_list c ON a.id = c.classify_id "
				+ "WHERE "
				+ "    a.online_time < NOW() and a.status=3 and d.status=3  "
				+ "AND a.add_special_status = " + InfoClassify.ADDSPECIALSTATUS1 + " "
				+ "AND b.video_status = " + Information.VIDEOSTATUS3);
//		mysqlPagedJdbcTemplate.queryPage(InfoClassifyDto.class, page, sql.toString(), conditions);
		mysqlPagedJdbcTemplateV2.queryPage(InfoClassifyDto.class, page, sql.toString(), conditions);
		return page;
	}

	@Override
	public List<InfoClassifyDto> getRecommended(Long appId) {
//		String sql = ""
//				+ "SELECT "
//				+ "    a.*, "
//				+ "    b.info_source, "
//				+ "    b.reply_count total_reply_count, "
//				+ "    b.type, "
//				+ "    b.context_type, "
//				+ "    b.info_label, "
//				+ "    b.content_url, "
//				+ "    b.multiple_img_count, "
//				+ "    b.init_count, "
//				+ "    b.add_type, "
//				+ "    b.top_time, "
//				+ "    b.view_count detail_view_count, "
//				+ "    b.synopsis, "
//				+ "    c.image_url img_urls "
//				+ "FROM "
//				+ "    recommend_info z "
//				+ "LEFT JOIN info_classify a ON a.id = z.source_id "
//				+ "LEFT JOIN information b ON a.information_id = b.id "
//				+ "LEFT JOIN info_classify_list c ON a.id = c.classify_id "
//				+ "WHERE "
//				+ "    a.app_id = " + appId + " "
//				+ "    AND z.source_type = " + BusinessType.SOURCE_TYPE_1 + " "
//				+ "    AND z.status = " + RecommendInfo.STATUS3 + " "
//				+ "    AND a.status = " + InfoClassify.STATUS3 + " "
//				+ "    AND a.online_time < NOW() "
//				+ "    AND a.add_special_status = " + InfoClassify.ADDSPECIALSTATUS0 + " "
//				+ "    AND b.video_status = " + Information.VIDEOSTATUS3 + " "
//				+ "    AND a.list_view_type <> " + InfoClassify.LISTVIEWTYPE4 + " "
//				+ "ORDER BY "
//				+ "    z.sort_no, "
//				+ "    z.update_time DESC, "
//				+ "    z.id DESC";
		String sql = ""
				+ "SELECT "
				+ "    a.*, "
				+ "    b.info_source, "
				+ "    b.reply_count total_reply_count, "
				+ "    b.type, "
				+ "    b.context_type, "
				+ "    b.info_label, "
				+ "    b.content_url, "
				+ "    b.multiple_img_count, "
				+ "    b.init_count, "
				+ "    b.add_type, "
				+ "    b.top_time, "
				+ "    b.view_count detail_view_count, "
				+ "    b.synopsis, "
				+ "    c.image_url img_urls "
				+ "FROM "
				+ "    recommend_info z "
				+ "LEFT JOIN info_classify a ON a.id = z.source_id "
				+ "LEFT JOIN information b ON a.information_id = b.id "
				+ "LEFT JOIN info_classify_list c ON a.id = c.classify_id "
				+ "WHERE "
				+ "    a.app_id = " + appId + " "
				+ "    AND z.source_type = " + BusinessType.SOURCE_TYPE_1 + " "
				+ "    AND z.status = " + RecommendInfo.STATUS3 + " "
				+ "    AND a.status = " + InfoClassify.STATUS3 + " "
				+ "    AND a.online_time < NOW() "
				+ "    AND a.add_special_status = " + InfoClassify.ADDSPECIALSTATUS0 + " "
				+ "    AND b.video_status = " + Information.VIDEOSTATUS3 + " "
				+ "    AND a.list_view_type <> " + InfoClassify.LISTVIEWTYPE4 + " "
				+ "ORDER BY "
				+ "    z.sort_no, "
				+ "    z.update_time DESC, "
				+ "    z.id DESC";
//		return mysqlPagedJdbcTemplate.queryForList(InfoClassifyDto.class, sql);
		return mysqlPagedJdbcTemplateV2.queryForList(InfoClassifyDto.class, sql);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.dao.InfoClassifyDaoCustom#queryInfoClassifyCorrelationPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	public PageInfo<InfoClassifyDto> queryInfoClassifyCorrelationPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> orderMap) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ic.*,info.validate_type,info.keywords  ");
		sql.append("from info_classify ic INNER JOIN information info on ");
		sql.append("ic.information_id = info.ID ");
		sql.append(" where ic.status=3 ");
		
		Long infoClassifyId = (Long) searchMap.get("EQ_infoClassifyId");
		searchMap.remove("EQ_infoClassifyId");
		sql.append("and not exists (select icr.ref_info_classify_id from info_correlation icr ");
		sql.append(" where icr.ref_info_classify_id=ic.id and icr.info_classify_id =  ");
		sql.append(infoClassifyId).append(" ) ");
		/*sql.append("LEFT JOIN info_correlation icc on icc.ref_info_classify_id = ic.ID ");
		sql.append(" WHERE icc.info_classify_id is NULL  ");*/
		
		String keyWords = (String) searchMap.get("LIKE_keywords");
		if(StringUtil.isEmpty(keyWords)){
			keyWords = (String) searchMap.get("LIKE_title");
		}
		if(StringUtil.isEmpty(keyWords)){
			keyWords = (String) searchMap.get("LIKE_contentText");
		}
		
		Object appIds = searchMap.get("IN_appId");
		if(!StringUtil.isEmpty(keyWords)){
			String[] arrKeyWord = keyWords.split(",");
			sql.append(" and ( ");
			List<Object> params = Lists.newArrayList();
			for(int i=0,m=arrKeyWord.length;i<m;i++){
				
				arrKeyWord[i] = "%"+arrKeyWord[i]+"%";
				sql.append("ic.title like ? ");
				sql.append("or info.keywords like ? ");
				sql.append("or info.content_text like ? ");
				sql.append("or ic.list_title like ? ");
				if(i<m-1){
					sql.append("or ");
				}
				params.add(arrKeyWord[i]);
				params.add(arrKeyWord[i]);
				params.add(arrKeyWord[i]);
				params.add(arrKeyWord[i]);
			}
			sql.append(" ) ");
			
			if(null != appIds) {
				Long[] appIdarra = (Long[])appIds;
				sql.append(" and ic.app_id in( ");
				for(int i=0;i< appIdarra.length;i++){
					sql.append("?");
					if(i<appIdarra.length -1 ){
						sql.append(",");
					}
					params.add(appIdarra[i]);
				}
				sql.append(") ");
			}
			//sql.append(" group by ic.id ");
			mysqlPagedJdbcTemplateV2.queryForPage(InfoClassifyDto.class, sql.toString(), pageInfo, params.toArray(new Object[]{}));
			return pageInfo;
		}
		//sql.append(" group by ic.id  ");
		mysqlPagedJdbcTemplateV2.queryForPage(InfoClassifyDto.class, sql.toString(), searchMap, pageInfo, orderMap);
		return pageInfo;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.dao.InfoClassifyDaoCustom#queryHadCorrInfoClassifyPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	public PageInfo<InfoClassifyDto> queryHadCorrInfoClassifyPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> orderMap) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ic.*,info.validate_type,info.keywords,icr.info_classify_id,  ");
		sql.append("info.reply_count info_reply_count ");
		sql.append("from info_classify ic INNER JOIN information info on ");
		sql.append("ic.information_id = info.ID LEFT JOIN info_correlation icr on  ");
		sql.append("ic.ID = icr.ref_info_classify_id ");
		mysqlPagedJdbcTemplateV2.queryForPage(InfoClassifyDto.class, sql.toString(), searchMap, pageInfo, orderMap);
		return pageInfo;
	}

	@Override
	public List<Long> findclassifyIds(Long informationId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select id from info_classify where information_id=?");
		return mysqlPagedJdbcTemplateV2.query(sql.toString(),SingleColumnRowMapper.newInstance(Long.class), informationId);
	}

	@Override
	public void updateIsViewWechat(Long infocontentId,Byte isViewWechat) {
		StringBuilder sql = new StringBuilder();
		sql.append("update info_classify classify,information_content content set classify.`is_view_wechat`=?  ");
		sql.append("where classify.`information_id`=content.`information_id` and content.id = ? ");
		mysqlPagedJdbcTemplateV2.update(sql.toString(), isViewWechat,infocontentId);
	}

}