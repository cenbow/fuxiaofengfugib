package com.cqliving.cloud.online.config.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.config.dao.ConfigSensitiveWordsDaoCustom;
import com.cqliving.cloud.online.config.dto.ConfigSensitiveWordsDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * 敏感词表 JPA Dao
 * Date: 2016-05-07 10:02:24
 * @author Code Generator
 */
public class ConfigSensitiveWordsDaoImpl implements ConfigSensitiveWordsDaoCustom {
    
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    @Override
    public ConfigSensitiveWordsDto getById(Long id) {
        StringBuffer sql = new StringBuffer("SELECT a.*,b.name app_name FROM config_sensitive_words a LEFT JOIN app_info b ON a.app_id=b.id " );
        sql.append(" where a.id=? ");
        return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(ConfigSensitiveWordsDto.class), id);
    }

    @Override
    public PageInfo<ConfigSensitiveWordsDto> queryPage(PageInfo<ConfigSensitiveWordsDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
        String sql = "SELECT a.*,b.name app_name FROM config_sensitive_words a LEFT JOIN app_info b ON a.app_id=b.id " ;
        mysqlPagedJdbcTemplateV2.queryForPage(ConfigSensitiveWordsDto.class, sql.toString(), map, pageInfo, orderMap);
        return pageInfo;
    }
}
