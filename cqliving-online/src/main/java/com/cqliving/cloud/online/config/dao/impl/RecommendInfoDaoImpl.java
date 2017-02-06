package com.cqliving.cloud.online.config.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.config.dao.RecommendInfoDaoCustom;
import com.cqliving.cloud.online.config.domain.RecommendInfo;
import com.cqliving.cloud.online.config.dto.RecommendInfoDto;
import com.cqliving.cloud.online.shop.domain.ShopInfo;
import com.cqliving.cloud.online.shop.dto.ShopInfoDto;
import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.framework.common.dao.jdbc.MysqlExtendJdbcTemplate;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年8月1日
 */
public class RecommendInfoDaoImpl implements RecommendInfoDaoCustom {

//	@Autowired
//	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	@Autowired
	private MysqlExtendJdbcTemplate mysqlExtendJdbcTemplate;

	@Override
	public PageInfo<RecommendInfoDto> queryForPage(PageInfo<RecommendInfoDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap, Byte sourceType) {
		StringBuilder sql = new StringBuilder();
		switch (sourceType) {
		case 1://新闻
			sql.append("select");
			sql.append(" a.ID as id,a.app_id as app_id, a.source_id as source_id, a.source_type as source_type, a.name as name, a.image_url as image_url, a.link_url as link_url, a.sort_no as sort_no, a.status as status, a.create_time as create_time, a.creator_id as creator_id, a.creator as creator, a.update_time as update_time, a.updator_id as updator_id, a.updator as updator");
			sql.append(", b.title as source_name");
			sql.append(" from recommend_info a left join info_classify b on a.source_id=b.id WHERE a.`status`<>"+RecommendInfo.STATUS99+" and b.`status`<>" + TopicInfo.STATUS99);
			break;
		case 7://话题
			sql.append("select");
			sql.append(" a.ID as id,a.app_id as app_id, a.source_id as source_id, a.source_type as source_type, a.name as name, a.image_url as image_url, a.link_url as link_url, a.sort_no as sort_no, a.status as status, a.create_time as create_time, a.creator_id as creator_id, a.creator as creator, a.update_time as update_time, a.updator_id as updator_id, a.updator as updator");
			sql.append(", b.name as source_name");
			sql.append(" from recommend_info a left join topic_info b on a.source_id=b.id WHERE a.`status`<>"+RecommendInfo.STATUS99+" and b.`status`<>" + TopicInfo.STATUS99);
			break;
		case 3://商情
		    sql.append("select");
		    sql.append(" a.ID as id,a.app_id as app_id, a.source_id as source_id, a.source_type as source_type, a.name as name, a.image_url as image_url, a.link_url as link_url, a.sort_no as sort_no, a.status as status, a.create_time as create_time, a.creator_id as creator_id, a.creator as creator, a.update_time as update_time, a.updator_id as updator_id, a.updator as updator");
		    sql.append(", b.name as source_name");
		    sql.append(" from recommend_info a left join shop_info b on a.source_id=b.id WHERE a.status<>"+RecommendInfo.STATUS99+" and b.status<>" + ShopInfo.STATUS99);
		    break;
		default:
			return pageInfo;
		}
		mysqlPagedJdbcTemplateV2.queryForPage(RecommendInfoDto.class, sql.toString(), map, pageInfo, orderMap);
		return pageInfo;
	}

	@Override
	public RecommendInfoDto getDetail(Long id, Byte sourceType) {
		StringBuilder sql = new StringBuilder();
		switch (sourceType) {
		case 7:
			sql.append("select");
			sql.append(" a.ID, a.app_id, a.source_id, a.source_type, a.name, a.image_url, a.link_url, a.sort_no, a.status, a.create_time, a.creator_id, a.creator, a.update_time, a.updator_id, a.updator");
			sql.append(", b.name source_name");
			sql.append(" from recommend_info a left join topic_info b on a.source_id=b.id where a.id=?");
			break;
		case 3:
		    sql.append("select");
		    sql.append(" a.ID, a.app_id, a.source_id, a.source_type, a.name, a.image_url, a.link_url, a.sort_no, a.status, a.create_time, a.creator_id, a.creator, a.update_time, a.updator_id, a.updator");
		    sql.append(", b.name source_name");
		    sql.append(" from recommend_info a left join shop_info b on a.source_id=b.id where a.id=?");
		    break;
		default:
			return null;
		}
		return mysqlExtendJdbcTemplate.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(RecommendInfoDto.class), id);
	}
	
	/**
     * 获取推荐到首页的商情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月13日下午3:34:53
     */
	@Override
    public List<ShopInfoDto> getShopRecommendIndex(Long appId){
        String sql = "SELECT b.*,a.image_url FROM Recommend_Info a,Shop_Info b WHERE a.source_Id = b.id AND a.app_Id = ? AND a.status = 3  AND a.source_Type = 3  AND b.status = 3 ORDER BY a.sort_No, a.update_Time DESC  ";
        return mysqlPagedJdbcTemplateV2.queryForList(ShopInfoDto.class, sql.toString(), appId);
    }
	
}
