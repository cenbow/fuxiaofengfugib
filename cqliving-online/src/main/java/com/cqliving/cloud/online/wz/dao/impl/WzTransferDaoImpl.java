package com.cqliving.cloud.online.wz.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.wz.dao.WzTransferDaoCustom;
import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.cloud.online.wz.domain.WzTransfer;
import com.cqliving.cloud.online.wz.domain.WzTransferDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月10日
 */
public class WzTransferDaoImpl implements WzTransferDaoCustom{
    
//    @Resource(name = "onlinePagedJdbcTemplate")
//    private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    /**
     * Title:问政子帐号列表分页查询
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月20日
     * @param pageInfo
     * @param map
     * @param orderMap
     * @return
     */
    public PageInfo<WzTransferDto> queryDtoForScrollPage(PageInfo<WzTransferDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap) {
        String sql = "select a.*, b.type as type, b.app_id as app_id, b.region_name as region_name, b.title as title from wz_transfer a left join wz_question b on  a.question_id = b.id where a.status<>" + WzTransfer.STATUS99 + " and b.status<>" + WzQuestion.STATUS99;
//        sql += " order by create_time desc";
        mysqlPagedJdbcTemplateV2.queryForPage(WzTransferDto.class, sql, map, pageInfo, orderMap);
        return pageInfo;
    }

}
