package com.cqliving.cloud.online.app.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.app.dao.AppImageVersionDaoCustom;
import com.cqliving.cloud.online.app.domain.AppMarketplaceResource;
import com.cqliving.cloud.online.app.dto.AppImageVersionDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

public class AppImageVersionDaoImpl implements AppImageVersionDaoCustom {
    
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月4日下午5:13:06
     */
    @Override
    public PageInfo<AppImageVersionDto> queryPage(PageInfo<AppImageVersionDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        String sql = "SELECT a.*,b.name app_name FROM app_image_version a LEFT JOIN app_info b ON a.app_id=b.id " ;
        mysqlPagedJdbcTemplateV2.queryForPage(AppImageVersionDto.class, sql.toString(), map, pageInfo, orderMap);
        return pageInfo;
    }
    
    /**
     * 查询单个记录（包括所有图片）
     *
     SELECT a.*,b.image_url loading_url,b.link_url FROM app_image_version a LEFT JOIN app_marketplace_resource b ON a.id=b.version_id AND b.image_type=1 where a.id=?
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月4日下午5:13:06
     */
    @Override
    public AppImageVersionDto queryById(Long id) {
        StringBuffer sql = new StringBuffer("SELECT a.*,b.image_url loading_url,b.link_url,b.show_time FROM app_image_version a LEFT JOIN app_marketplace_resource b ON a.id=b.version_id AND b.image_type=" );
        sql.append(AppMarketplaceResource.IMAGETYPE1);
        sql.append(" where a.id=? ");
        return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(AppImageVersionDto.class), id);
    }

}