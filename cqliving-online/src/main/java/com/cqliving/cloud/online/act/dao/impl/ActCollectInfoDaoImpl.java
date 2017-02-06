package com.cqliving.cloud.online.act.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.act.dao.ActCollectInfoDaoCustom;
import com.cqliving.cloud.online.act.dto.ActCollectInfoDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

public class ActCollectInfoDaoImpl implements ActCollectInfoDaoCustom {

    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
    @Override
    public PageInfo<ActCollectInfoDto> queryPage(PageInfo<ActCollectInfoDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        StringBuffer sql = new StringBuffer("SELECT a.*,b.name app_name FROM act_collect_info a LEFT JOIN app_info b ON a.app_id = b.id ") ;
        mysqlPagedJdbcTemplateV2.queryForPage(ActCollectInfoDto.class, sql.toString(), map, pageInfo, orderMap);
        return pageInfo;
    }

    /**
     * 通过id查询单个信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
    @Override
    public ActCollectInfoDto findById(Long id) {
        StringBuffer sql = new StringBuffer("SELECT a.*,b.name app_name FROM act_collect_info a LEFT JOIN app_info b ON a.app_id = b.id ") ;
        sql.append(" where a.id=? ");
        return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(ActCollectInfoDto.class), id);
    }

}
