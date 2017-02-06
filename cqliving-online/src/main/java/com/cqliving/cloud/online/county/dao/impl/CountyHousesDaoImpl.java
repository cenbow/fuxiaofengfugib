package com.cqliving.cloud.online.county.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.county.dao.CountyHousesDaoCustom;
import com.cqliving.cloud.online.county.domain.CountyHouses;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.ScrollPage;

public class CountyHousesDaoImpl implements CountyHousesDaoCustom {

    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    /**
     * 获取区县楼盘(滚动分页)
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月29日下午3:11:59
     */
    @Override
    public ScrollPage<CountyHouses> getScrollPage(ScrollPage<CountyHouses> page, Map<String, Object> conditions) {
        String sql = "SELECT * from county_houses ";
        page = mysqlPagedJdbcTemplateV2.queryPage(CountyHouses.class, page, sql, conditions);
        return page;
    }

}
