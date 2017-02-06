package com.cqliving.cloud.online.info.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.info.dao.InfoLableDaoCustom;
import com.cqliving.cloud.online.info.dto.InfoLableDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

public class InfoLableDaoImpl implements InfoLableDaoCustom {
    
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月6日下午5:13:06
     */
    @Override
    public PageInfo<InfoLableDto> queryInfoLabelDtoPage(PageInfo<InfoLableDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
        String sql = "SELECT a.*,b.name app_name FROM info_lable a , app_info b WHERE a.app_id=b.id " ;
        mysqlPagedJdbcTemplateV2.queryForPage(InfoLableDto.class, sql, map, pageInfo, orderMap);
        return pageInfo;
    }
    
    /**
     * 查询单个记录
     * 
     * SELECT * FROM info_lable a , app_info b WHERE a.app_id=b.id AND a.id = 6
     */
    @Override
    public InfoLableDto getById(Long id){
        String sql = "SELECT a.*,b.name app_name FROM info_lable a , app_info b WHERE a.app_id=b.id AND a.id = ?";
        return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(InfoLableDto.class), id);
    }
}
