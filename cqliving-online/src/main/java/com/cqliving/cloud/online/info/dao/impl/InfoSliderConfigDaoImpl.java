package com.cqliving.cloud.online.info.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.info.dao.InfoSliderConfigDaoCustom;
import com.cqliving.cloud.online.info.domain.InfoTempleteImageConfig;
import com.cqliving.cloud.online.info.dto.InfoSliderConfigDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;

public class InfoSliderConfigDaoImpl implements InfoSliderConfigDaoCustom {
    
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    /**
     * 
    SELECT t3.ID, t1.app_id, t1.id columns_id,t1.name columns_name, IFNULL(t3.value,5) VALUE, 
    t3.create_time, t3.creator_id, t3.creator, t3.update_time, t3.updator_id, t3.updator 
    FROM app_columns t1
    INNER JOIN info_templete_image_config t2 ON t1.templet_code = t2.templet_code AND t1.app_id = t2.app_id
    LEFT JOIN info_slider_config t3 ON t1.app_id = t3.app_id  AND t1.id = t3.columns_id
    WHERE t2.list_type = 3 AND t1.status =3 AND t1.app_id = 674
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年8月30日下午2:07:57
     */
    @Override
    public List<InfoSliderConfigDto> getListByAppId(Long appId) {

       StringBuilder sql = new StringBuilder();
       sql.append("SELECT t3.ID, t1.app_id, t1.id columns_id,t1.name columns_name, IFNULL(t3.value,5) VALUE, ");
       sql.append("t3.create_time, t3.creator_id, t3.creator, t3.update_time, t3.updator_id, t3.updator ");
       sql.append("FROM app_columns t1 ");
       sql.append("INNER JOIN info_templete_image_config t2 ON t1.templet_code = t2.templet_code AND t1.app_id = t2.app_id ");
       sql.append("LEFT JOIN info_slider_config t3 ON t1.app_id = t3.app_id  AND t1.id = t3.columns_id ");
       sql.append("WHERE t2.list_type = ? AND t1.status = ? and t1.app_id = ? ORDER BY t1.sort_no ASC ");
       
       return mysqlPagedJdbcTemplateV2.queryForList(InfoSliderConfigDto.class, sql.toString(),InfoTempleteImageConfig.LISTTYPE3,AppColumns.STATUS3,appId);
    }
}
