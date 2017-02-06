package com.cqliving.cloud.online.actcustom.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.actcustom.dao.ActCustomQrcodeColumnDaoCustom;
import com.cqliving.cloud.online.actcustom.domain.ActCustomQrcodeColumn;
import com.cqliving.cloud.online.actcustom.dto.ActCustomColumnDto;
import com.cqliving.cloud.online.actcustom.dto.ActCustomQrcodeColumnDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlExtendJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月21日
 */
public class ActCustomQrcodeColumnDaoImpl implements ActCustomQrcodeColumnDaoCustom {

	@Autowired
	private MysqlExtendJdbcTemplateV2 mysqlExtendJdbcTemplateV2;
	
	@Override
	public List<ActCustomQrcodeColumnDto> getColumnsByQrcode(String qrcode, boolean isListView) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" a.ID as id, a.`name` as name, a.type as type, a.description as description");
		sql.append(", b.act_qrcode_code as act_qrcode_code, b.act_custom_column_id as act_custom_column_id, b.sort_no as sort_no, b.length as length, b.is_required as is_required, b.is_list_view as is_list_view");
		sql.append(" FROM act_custom_column a");
		sql.append(" INNER JOIN act_custom_qrcode_column b");
		sql.append(" ON a.ID=b.act_custom_column_id AND b.act_qrcode_code=?");
		if(isListView){
			sql.append(" AND b.is_list_view=?");
		}
		sql.append(" ORDER BY b.sort_no asc");
		List<ActCustomQrcodeColumnDto> list = null;
		if(isListView){
			list = mysqlExtendJdbcTemplateV2.queryForList(ActCustomQrcodeColumnDto.class, sql.toString(), qrcode, ActCustomQrcodeColumn.ISLISTVIEW1);
		}else{
			list = mysqlExtendJdbcTemplateV2.queryForList(ActCustomQrcodeColumnDto.class, sql.toString(), qrcode);
		}
		return list;
	}

	@Override
	public ActCustomQrcodeColumnDto getDtoByIdAndCode(Long actCustomColumnId, String qrcode) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" a.`name` as name, a.type as type, a.description as description");
		sql.append(", b.ID as id, b.act_qrcode_code as act_qrcode_code, b.act_custom_column_id as act_custom_column_id, b.sort_no as sort_no, b.length as length, b.is_required as is_required, b.is_list_view as is_list_view");
		sql.append(" FROM act_custom_column a");
		sql.append(" INNER JOIN act_custom_qrcode_column b");
		sql.append(" ON a.ID=b.act_custom_column_id AND a.id=? and b.act_qrcode_code=?");
		sql.append(" ORDER BY b.sort_no");
		try {
			ActCustomQrcodeColumnDto dto = mysqlExtendJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(ActCustomQrcodeColumnDto.class), actCustomColumnId, qrcode);
			return dto;
		} catch (EmptyResultDataAccessException e) {
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getSignList(String qrcode, StringBuilder fields) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(fields);
		sql.append(" FROM user_act_custom_signup AS a ");
		sql.append(" INNER JOIN user_act_custom_column AS b ON a.ID=b.user_act_custom_signup_id AND a.act_qrcode_code=?");
		sql.append(" INNER JOIN act_custom_column AS c ON b.act_custom_column_id=c.ID");
		sql.append(" INNER JOIN act_custom_qrcode_column AS d ON d.act_custom_column_id=c.ID AND d.act_qrcode_code=a.act_qrcode_code");
		sql.append(" GROUP BY a.user_id");
		sql.append(" ORDER BY a.create_time DESC");
		
		List<Map<String, Object>> list = mysqlExtendJdbcTemplateV2.queryForList(sql.toString(), qrcode);
		return list;
	}

	@Override
	public List<ActCustomColumnDto> getByUserSignInfo(String qrcode, Long userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select b.`value`, c.`name`, c.type ");
		sql.append(" from user_act_custom_signup a INNER JOIN user_act_custom_column b on a.ID=b.user_act_custom_signup_id and a.user_id=? and a.act_qrcode_code=?");
		sql.append(" INNER JOIN act_custom_column c on b.act_custom_column_id=c.ID");
		List<ActCustomColumnDto> list = mysqlExtendJdbcTemplateV2.queryForList(ActCustomColumnDto.class, sql.toString(), userId, qrcode);
		return list;
	}

}
