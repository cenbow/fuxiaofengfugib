/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.info.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.info.dao.InfoTempleteImageConfigDaoCustom;
import com.cqliving.cloud.online.info.domain.InfoTempleteImageConfig;
import com.cqliving.cloud.online.info.dto.InfoTempleteImageConfigDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月4日
 */
public class InfoTempleteImageConfigDaoImpl implements InfoTempleteImageConfigDaoCustom {

    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.dao.InfoTempleteImageConfigDaoCustom#getByAppColumnsId(java.lang.Long)
	 */
	@Override
	public List<InfoTempleteImageConfig> getByAppId(Long appId) {

       StringBuilder sql = new StringBuilder();
       sql.append("select im.* ");
       sql.append("from app_templet atm,info_templete_image_config im ");
       sql.append("where atm.templet_code = im.templet_code and atm.app_id=? ");
       
	   return mysqlPagedJdbcTemplateV2.queryForList(InfoTempleteImageConfig.class, sql.toString(),appId);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.dao.InfoTempleteImageConfigDaoCustom#getByAppColumnsId(java.lang.Long)
	 */
	@Override
	public List<InfoTempleteImageConfig> getByAppColumnsId(Long appColumnsId,Long appId) {
		  StringBuilder sql = new StringBuilder();
	       sql.append("select im.* ");
	       sql.append("from app_columns atm,info_templete_image_config im ");
	       sql.append("where atm.templet_code = im.templet_code and atm.id=? and im.app_id= ? ");
	       
		 return mysqlPagedJdbcTemplateV2.queryForList(InfoTempleteImageConfig.class, sql.toString(),appColumnsId,appId);
	}
	
	/**
     * 查询详情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年8月5日
     */
    @Override
    public InfoTempleteImageConfigDto getById(Long id) {
        StringBuffer sql = new StringBuffer("SELECT a.*,b.name app_name FROM info_templete_image_config a  ");
        sql.append("LEFT JOIN app_info b ON a.app_id = b.id where a.id=? ");
        return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(InfoTempleteImageConfigDto.class), id);
    }

}
