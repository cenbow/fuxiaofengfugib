package com.cqliving.cloud.online.act.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.act.dao.ActInfoListDaoCustom;
import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.act.dto.ActInfoListDto;
import com.cqliving.cloud.online.interfacc.dto.ActExportDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月29日
 */
@Repository
public class ActInfoListDaoImpl implements ActInfoListDaoCustom {
    
//    @Autowired
//    private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    @Override
	public ScrollPage<ActInfoListDto> queryForScrollPage(ScrollPage<ActInfoListDto> scrollPage, Map<String, Object> conditions) {
		//查询数据
//		String sql = ""
//				+ "SELECT "
//				+ "	a.*, "
//				+ "	b.end_time act_info_end_time, "
//				+ "	b.list_image_url, "
//				+ "	b.title, "
//				+ "	b.digest, "
//				+ "	b.is_recommend, "
//				+ "	b.recommend_image_url, "
//				+ "	IFNULL(b.sort_no, " + Integer.MAX_VALUE + ") sort_no "
//				+ "FROM	"
//				+ "	act_info_list a "
//				+ "LEFT JOIN act_info b ON a.act_info_id = b.id "
//				+ "WHERE "
//				+ "	a. STATUS = " + ActInfoList.STATUS3 + " "
//				+ " AND	b. STATUS = " + ActInfo.STATUS3 + " "
//				+ "ORDER BY "
//				+ "	sort_no, "
//				+ "	a.start_time DESC, "
//				+ "	a.id DESC";
		String sql = ""
				+ "SELECT "
				+ "	t.* "
				+ "FROM ("
				+ "	SELECT "
				+ "		a.*, "
				+ "		b.end_time act_info_end_time, "
				+ "		b.list_image_url, "
				+ "		b.title, "
				+ "		b.digest, "
				+ "		b.is_recommend, "
				+ "		b.recommend_image_url, "
				+ "		IFNULL(b.sort_no, " + Integer.MAX_VALUE + ") as sort_no "
				+ "	FROM	"
				+ "		act_info_list a "
				+ "	LEFT JOIN act_info b ON a.act_info_id = b.id "
				+ "	WHERE "
				+ "		a.status = " + ActInfoList.STATUS3 + " "
				+ " 	AND b.status = " + ActInfo.STATUS3 + ""
				+ ") t";
//		mysqlPagedJdbcTemplate.queryPage(ActInfoListDto.class, scrollPage, sql.toString(), conditions);
		mysqlPagedJdbcTemplateV2.queryPage(ActInfoListDto.class, scrollPage, sql.toString(), conditions);
		return scrollPage;
	}

	@Override
	public List<ActExportDto> actExportList(Long classfyId) {
		List<ActExportDto> actExport=new ArrayList<>();
		String sql ="SELECT classify.subject vote_title,"+
			       " item.item_title,"+
			       " count(useract.act_vote_item_id) vote_num"+
				   " FROM act_vote vote"+
				   " INNER JOIN act_vote_classify classify ON vote.ID = classify.act_vote_id"+
				   " INNER JOIN act_vote_item item ON classify.ID = item.act_vote_classify_id"+
				   " LEFT JOIN user_act_vote useract ON useract.act_vote_item_id = item.ID"+
				   " WHERE vote.`status` = 3"+
				   " AND vote.act_info_list_id = "+classfyId+
				   " GROUP BY item.ID"+
				   " ORDER BY count(useract.act_vote_item_id) desc,vote_title";
		actExport=mysqlPagedJdbcTemplateV2.queryForList(sql, ActExportDto.class);
		return actExport;
	}

}