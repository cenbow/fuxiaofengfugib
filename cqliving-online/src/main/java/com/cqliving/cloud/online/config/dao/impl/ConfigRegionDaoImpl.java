package com.cqliving.cloud.online.config.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.config.dao.ConfigRegionDaoCustom;
import com.cqliving.cloud.online.config.dto.ConfigRegionDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

public class ConfigRegionDaoImpl implements ConfigRegionDaoCustom{

    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    /**
     * 分页查询商情区域
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月7日
     */
    @Override
    public PageInfo<ConfigRegionDto> queryByPage(PageInfo<ConfigRegionDto> pageInfo, Map<String, Object> conditions,
            Map<String, Boolean> orders) {
        String sql = "SELECT a.*,b.name type_name FROM config_region a LEFT JOIN shop_type b ON a.shop_type_id = b.id ";
        mysqlPagedJdbcTemplateV2.queryForPage(ConfigRegionDto.class, sql, conditions, pageInfo, orders);
        return pageInfo;
    }

}
