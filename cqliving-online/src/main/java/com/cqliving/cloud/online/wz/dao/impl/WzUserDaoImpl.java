package com.cqliving.cloud.online.wz.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.cloud.online.wz.dao.WzUserDaoCustom;
import com.cqliving.cloud.online.wz.domain.WzUser;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlExtendJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2017年2月3日
 */
public class WzUserDaoImpl implements WzUserDaoCustom {
    
    @Autowired
    private MysqlExtendJdbcTemplateV2 mysqlExtendJdbcTemplateV2;

    @Override
    public List<WzUser> getByAppId(Long appId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.* from wz_user a inner join sys_user b on a.id=b.id");
        sql.append(" and a.app_id=? and b.status=?");
        return mysqlExtendJdbcTemplateV2.queryForList(WzUser.class, sql.toString(), appId, SysUser.STATUS1);
    }

}
