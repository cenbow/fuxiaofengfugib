package com.cqliving.cloud.online.consult.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.consult.dao.ConsultInfoDaoCustom;
import com.cqliving.cloud.online.consult.dto.ConsultInfoDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.ScrollPage;

public class ConsultInfoDaoImpl implements ConsultInfoDaoCustom {

    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    /**
     * 我要咨询列表(滚动分页)
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月29日下午3:11:59
     */
    @Override
    public ScrollPage<ConsultInfoDto> queryConsultScrollPage(ScrollPage<ConsultInfoDto> page, Map<String, Object> conditions) {
        String sql = "SELECT * from consult_info ";
        page = mysqlPagedJdbcTemplateV2.queryPage(ConsultInfoDto.class, page, sql, conditions);
        return page;
    }

}
