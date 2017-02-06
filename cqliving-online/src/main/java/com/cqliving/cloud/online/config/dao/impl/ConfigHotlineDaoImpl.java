package com.cqliving.cloud.online.config.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.config.dao.ConfigHotlineDaoCustom;
import com.cqliving.cloud.online.config.dto.ConfigHotlineDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

public class ConfigHotlineDaoImpl implements ConfigHotlineDaoCustom {
    
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    /**
     * 分页查询热线
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月7日
     */
    @Override
    public PageInfo<ConfigHotlineDto> queryByPage(PageInfo<ConfigHotlineDto> pageInfo, Map<String, Object> conditions,
            Map<String, Boolean> orders) {
        StringBuffer sql = new StringBuffer("SELECT a.*,b.name app_name,c.name type_name FROM config_hotline a  ");
        sql.append(" LEFT JOIN app_info b ON a.app_id = b.id LEFT JOIN config_common_type c ON a.hotline_type_id = c.id ");
        mysqlPagedJdbcTemplateV2.queryForPage(ConfigHotlineDto.class, sql.toString(), conditions, pageInfo, orders);
        return pageInfo;
    }

    /**
     * 查询详情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月7日
     */
    @Override
    public ConfigHotlineDto getById(Long id) {
        StringBuffer sql = new StringBuffer("SELECT a.*,b.name app_name,c.name type_name FROM config_hotline a  ");
        sql.append("LEFT JOIN app_info b ON a.app_id = b.id LEFT JOIN config_common_type c ON a.hotline_type_id = c.id where a.id=? ");
        return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(ConfigHotlineDto.class), id);
    }

}
