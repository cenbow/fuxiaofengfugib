package com.cqliving.cloud.online.app.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.app.dao.AppInfoDaoCustom;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

public class AppInfoDaoImpl implements AppInfoDaoCustom {

    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    /**
     * 分页查询区县APP信息
     * 
     * 
        SELECT a.*,b.USERNAME,b.status,b.id user_id,c.id weatherId,d.id qiniuId FROM app_info a 
        INNER JOIN (SELECT * FROM sys_user WHERE USERTYPE = 3 GROUP BY app_id ) b ON a.id = b.app_id 
        LEFT JOIN app_weather c ON a.id=c.app_id 
        LEFT JOIN app_qiniu d ON a.id=d.app_id
     */
    @Override
    public PageInfo<AppInfoDto> queryPage(PageInfo<AppInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT a.*,b.USERNAME,b.status,b.id user_id,c.id weatherId,d.id qiniuId FROM app_info a ");
        sql.append(" INNER JOIN (SELECT * FROM sys_user WHERE USERTYPE = ").append(SysUser.USERTYPE3).append(" GROUP BY app_id ) b ON a.id = b.app_id ");
        sql.append(" LEFT JOIN app_weather c ON a.id=c.app_id ");
        sql.append(" LEFT JOIN app_qiniu d ON a.id=d.app_id ");
        mysqlPagedJdbcTemplateV2.queryForPage(AppInfoDto.class, sql.toString(), map, pageInfo, orderMap);
        return pageInfo;
    }
    
    /**
     * 查询某用户对应数据权限表的App
     * SELECT a.*,b.USERNAME,b.status,b.id user_id FROM app_info a ,sys_user b ,sys_user_data c  WHERE a.id=c.value AND b.id=c.user_id AND b.STATUS =1 GROUP BY a.id
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月29日上午9:47:42
     */
    @Override
    public PageInfo<AppInfoDto> queryPageByUser(PageInfo<AppInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.*,b.USERNAME,b.status,b.id user_id FROM app_info a ,sys_user b ,sys_user_data c  WHERE a.id=c.value AND b.id=c.user_id AND b.STATUS = ").append(SysUser.STATUS1);
        sql.append(" ${WHERE} GROUP BY a.id");
        mysqlPagedJdbcTemplateV2.queryForPage(AppInfoDto.class, sql.toString(), map, pageInfo, orderMap);
        return pageInfo;
    }
}
