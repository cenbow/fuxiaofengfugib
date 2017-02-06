package com.cqliving.cloud.online.app.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.app.dao.AppVersionDaoCustom;
import com.cqliving.cloud.online.app.dto.AppVersionDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * 
 * <p>Title:AppVersionDaoImpl </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author huxiaoping 2016年4月26日下午5:05:17
 *
 */
public class AppVersionDaoImpl implements AppVersionDaoCustom {
    
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

    @Override
    public PageInfo<AppVersionDto> queryByPage(PageInfo<AppVersionDto> pageInfo, Map<String, Object> conditions,
            Map<String, Boolean> orders) {
        StringBuffer sql = new StringBuffer("SELECT a.* ,b.name app_name,c.max_id FROM app_version a LEFT JOIN app_info b ON a.app_id=b.id ");
        sql.append(" LEFT JOIN (SELECT MAX(id) max_id FROM app_version where status = 3 GROUP BY app_id,TYPE) c ON a.id=c.max_id ");
        mysqlPagedJdbcTemplateV2.queryForPage(AppVersionDto.class, sql.toString(), conditions, pageInfo, orders);
        return pageInfo;
    }

    @Override
    public AppVersionDto getById(Long id) {
        String sql = "SELECT a.* ,b.name app_name FROM app_version a LEFT JOIN app_info b ON a.app_id=b.id where a.id=? ";
        return mysqlPagedJdbcTemplateV2.queryForObject(sql, BeanPropertyRowMapper.newInstance(AppVersionDto.class), id);
    }
}