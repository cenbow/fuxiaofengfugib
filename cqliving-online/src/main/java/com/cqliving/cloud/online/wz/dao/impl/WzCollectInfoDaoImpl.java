package com.cqliving.cloud.online.wz.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.interfacc.dto.WzCollectInfoData;
import com.cqliving.cloud.online.wz.dao.WzCollectInfoDaoCustom;
import com.cqliving.cloud.online.wz.domain.WzAuthority;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlExtendJdbcTemplateV2;
/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年10月8日
 */
public class WzCollectInfoDaoImpl implements WzCollectInfoDaoCustom {

	@Autowired
	private MysqlExtendJdbcTemplateV2 mysqlExtendJdbcTemplateV2;
	
	@Override
	public List<WzCollectInfoData> getUserCollectInfo(Long appId, Long questionId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.*, b.value FROM (");
		sql.append("SELECT c.ID, c.`name` FROM wz_authority a");
		sql.append(" LEFT JOIN wz_app_authority b ON a.ID=b.authority_id");
		sql.append(" LEFT JOIN wz_collect_info c ON b.ID = c.app_authority_id");
		sql.append(" WHERE a.`name` = ? AND b.app_id=?");
		sql.append(") a LEFT JOIN wz_question_collect_info b ON a.ID=b.collect_info_id WHERE b.question_id=?");
		List<WzCollectInfoData> list = mysqlExtendJdbcTemplateV2.queryForList(WzCollectInfoData.class, sql.toString(), WzAuthority.NAME_IS_COLLECT_USER_INFO, appId, questionId);
		return list;
	}

}
