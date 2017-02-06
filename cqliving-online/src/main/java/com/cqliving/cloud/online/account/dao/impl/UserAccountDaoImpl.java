package com.cqliving.cloud.online.account.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.account.dao.UserAccountDaoCustom;
import com.cqliving.cloud.online.account.dto.UserDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

public class UserAccountDaoImpl implements UserAccountDaoCustom {
    
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月9日下午5:13:06
     * 
     SELECT a.id,a.user_name,a.telephone,a.email,a.source,a.type,a.status,a.regist_time,a.lng,a.lat,a.place,c.last_login_time,c.login_ip,c.app_id
    ,b.img_url,b.name,b.sex,b.speciality,b.update_time,d.name app_name 
     FROM user_account a LEFT JOIN user_info b ON a.id=b.id INNER JOIN user_login_info c ON a.id = c.user_id  LEFT JOIN app_info d ON c.app_id=d.id 
     */
    @Override
    public PageInfo<UserDto> queryPage(PageInfo<UserDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.id,a.user_name,a.telephone,a.email,a.source,a.type,a.status,a.regist_time,a.lng,a.lat,a.place,c.last_login_time,c.login_ip,c.app_id ");
        sql.append(",b.img_url,b.name,b.sex,b.speciality,b.update_time,d.name app_name ");
        sql.append("FROM user_account a LEFT JOIN user_info b ON a.id=b.id INNER JOIN user_login_info c ON a.id = c.user_id  LEFT JOIN app_info d ON c.app_id=d.id ");
        mysqlPagedJdbcTemplateV2.queryForPage(UserDto.class, sql.toString(), map, pageInfo, orderMap);
        return pageInfo;
    }
    
    /**
     * 查询单个记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月9日下午5:13:06
     */
    @Override
    public UserDto getById(Long id,Long AppId) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.id,a.user_name,a.telephone,a.email,a.source,a.type,a.status,a.regist_time,a.lng,a.lat,a.place,c.last_login_time,c.login_ip,c.app_id ");
        sql.append(",b.img_url,b.name,b.sex,b.speciality,b.update_time,d.name app_name ");
        sql.append("FROM user_account a LEFT JOIN user_info b ON a.id=b.id INNER JOIN user_login_info c ON a.id = c.user_id  LEFT JOIN app_info d ON c.app_id=d.id ");
        sql.append("where a.id=? and c.app_id=?");
        return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(UserDto.class),id,AppId);
    }
}
