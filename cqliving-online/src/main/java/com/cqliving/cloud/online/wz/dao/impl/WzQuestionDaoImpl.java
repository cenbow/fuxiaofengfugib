package com.cqliving.cloud.online.wz.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.wz.dao.WzQuestionDaoCustom;
import com.cqliving.cloud.online.wz.dto.WzQuestionDto;
import com.cqliving.cloud.online.wz.dto.WzQuestionExcelDownload;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlExtendJdbcTemplateV2;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.ScrollPage;
/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月10日
 */
public class WzQuestionDaoImpl implements WzQuestionDaoCustom{
    
//    @Resource(name = "onlinePagedJdbcTemplate")
//    private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    @Autowired
    private MysqlExtendJdbcTemplateV2 mysqlExtendJdbcTemplateV2;
    
    @Override
    public ScrollPage<WzQuestionDto> queryDtoForScrollPage(ScrollPage<WzQuestionDto> page, Map<String, Object> conditions) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.* FROM wz_question a");
//        sql.append(" ORDER BY create_time DESC, id DESC");
        mysqlPagedJdbcTemplateV2.queryPage(WzQuestionDto.class, page, sql.toString(), conditions);
        return page;
    }

	@Override
	public List<WzQuestionExcelDownload> excelDownload(Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.* FROM wz_question a");
		return mysqlExtendJdbcTemplateV2.queryForList(WzQuestionExcelDownload.class, sql.toString(), searchMap, sortMap);
	}

}
