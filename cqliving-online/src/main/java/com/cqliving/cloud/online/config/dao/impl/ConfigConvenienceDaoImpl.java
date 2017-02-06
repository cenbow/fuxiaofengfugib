package com.cqliving.cloud.online.config.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.online.config.dao.ConfigConvenienceDaoCustom;
import com.cqliving.cloud.online.config.domain.ConfigCommonType;
import com.cqliving.cloud.online.config.domain.ConfigConvenience;
import com.cqliving.cloud.online.config.dto.ConfigConvenienceDto;
import com.cqliving.framework.common.dao.jdbc.MysqlPagedJdbcTemplate;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年7月13日
 */
@Repository
public class ConfigConvenienceDaoImpl implements ConfigConvenienceDaoCustom {

    @Autowired
    private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
	@Override
	public PageInfo<ConfigConvenienceDto> queryPage(PageInfo<ConfigConvenienceDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap) {
		String sql = "SELECT a.*,b.name as app_name, c.name as convenience_type_name FROM config_convenience a LEFT JOIN app_info b ON a.app_id=b.id LEFT JOIN config_common_type c on a.convenience_type_id=c.id WHERE a.status=" + ConfigConvenience.STATUS3 ;
		mysqlPagedJdbcTemplateV2.queryForPage(ConfigConvenienceDto.class, sql.toString(), map, pageInfo, orderMap);
		return pageInfo;
	}

	@Override
	public List<ConfigConvenienceDto> getByApp(Long appId) {
		String sql = ""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.sort_no type_sort_no, "
				+ "	b.name convenience_type_name, "
				+ "	b.image_url type_img_url "
				+ "FROM	"
				+ "	config_convenience a "
				+ "LEFT JOIN config_common_type b ON a.convenience_type_id = b.id "
				+ "WHERE "
				+ "	a.app_id = " + appId + " "
				+ "	AND a.status = " + ConfigConvenience.STATUS3 + " "
				+ "	AND b.status = " + ConfigCommonType.STATUS3 + " "
				+ "	AND b.source_type = " + BusinessType.SOURCE_TYPE_8 + " "
				+ "ORDER BY "
				+ "	b.sort_no, "
				+ "	a.sort_no";
//        return mysqlPagedJdbcTemplate.queryForList(sql, ConfigConvenienceDto.class);
        return mysqlPagedJdbcTemplateV2.queryForList(sql, ConfigConvenienceDto.class);
	}

}