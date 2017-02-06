package com.cqliving.cloud.online.act.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.act.dao.ActInfoDaoCustom;
import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.act.dto.ActInfoDto;
import com.cqliving.framework.common.dao.jdbc.MysqlPagedJdbcTemplate;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.google.common.collect.Lists;

public class ActInfoDaoImpl implements ActInfoDaoCustom {
    
    @Autowired
    private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
    
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    /**
     * 分页查询
     
        SELECT a.*,c.act_types,c.show_types,c.act_status,c.act_info_id,d.join_no,d.participants_no,f.activation_state ,CASE WHEN a.sort_no IS NULL THEN 9999 ELSE a.sort_no END sort_no1 FROM act_info a LEFT JOIN (
        -- 查询活动类型以及活动各个类型的状态
        SELECT GROUP_CONCAT(b.type) act_types,GROUP_CONCAT(show_type) show_types , GROUP_CONCAT(b.status) act_status, b.act_info_id  FROM act_info_list b WHERE  b.status <> 99 GROUP BY b.act_info_id ORDER BY b.id ASC 
        ) c ON a.id=c.act_info_id 
        LEFT JOIN (
        -- 查询参加活动的人数和人次
        SELECT act_info_id, COUNT(DISTINCT user_id) participants_no,SUM(join_count) join_no FROM user_act_list a GROUP BY a.act_info_id
        )d ON a.id=d.act_info_id
        -- LEFT JOIN (
        -- 查询分享
        -- SELECT COUNT(1) share_no,source_id FROM user_share_history WHERE source_type = 4 GROUP BY source_id 
        -- ) e ON a.id=e.source_id
        -- 查询激活状态
        LEFT JOIN (
        SELECT act_info_id,MAX(STATUS) activation_state FROM act_info_list WHERE STATUS <> 99 GROUP BY act_info_id
        ) f ON a.id=f.act_info_id

     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月7日上午10:37:12
     */
    @Override
    public PageInfo<ActInfoDto> queryPage(PageInfo<ActInfoDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        StringBuffer sql = new StringBuffer("SELECT a.*,c.act_types,c.show_types,c.act_status,c.act_info_id,d.join_no,d.participants_no,f.activation_state ,CASE WHEN a.sort_no IS NULL THEN 9999 ELSE a.sort_no END sort_no1 FROM act_info a LEFT JOIN (") ;
        sql.append(" SELECT GROUP_CONCAT(b.type) act_types,GROUP_CONCAT(show_type) show_types ,GROUP_CONCAT(b.status) act_status, b.act_info_id  FROM act_info_list b WHERE  b.status <> ").append(ActInfo.STATUS99).append(" GROUP BY b.act_info_id ");
        sql.append(" ORDER BY b.id ASC ");
        sql.append(" ) c on a.id=c.act_info_id ");
        sql.append(" left join ( ");
        sql.append(" SELECT act_info_id, COUNT(DISTINCT user_id) participants_no,SUM(join_count) join_no FROM user_act_list a GROUP BY a.act_info_id ");
        sql.append(" )d on a.id=d.act_info_id ");
        /*sql.append(" LEFT JOIN ( ");
        sql.append(" SELECT COUNT(1) share_no,source_id FROM user_share_history WHERE source_type = ").append(UserShareHistory.SOURCETYPE4).append(" GROUP BY source_id ");
        sql.append(" ) e ON a.id=e.source_id ");*/
        sql.append(" LEFT JOIN ( ");
        sql.append(" SELECT act_info_id,MAX(STATUS) activation_state FROM act_info_list WHERE STATUS <> ");
        sql.append(ActInfoList.STATUS99);
        sql.append(" GROUP BY act_info_id ");
        sql.append(" ) f ON a.id=f.act_info_id ");
        mysqlPagedJdbcTemplateV2.queryForPage(ActInfoDto.class, sql.toString(), map, pageInfo, orderMap);
        return pageInfo;
    }
    
    /* (non-Javadoc)
     * @see com.cqliving.cloud.online.act.dao.ActInfoDaoCustom#findById(java.lang.Long)
     */
    @Override
    public ActInfoDto findById(Long id) {
        
        StringBuilder sql = new StringBuilder();
        sql.append("select ai.*,ail.id act_info_list_id ,ail.link_url,ail.end_time act_end_time,");
        sql.append("ail.start_time act_start_time,ail.type act_type,ail.show_type, ");
        sql.append("ail.`status` act_status from act_info ai left join ");
        sql.append("act_info_list ail on ai.ID = ail.act_info_id where ai.id=? ORDER BY ail.create_time");
        
        List<ActInfoDto> list= mysqlPagedJdbcTemplateV2.queryForList(ActInfoDto.class, sql.toString(), id);
        
        if(CollectionUtils.isEmpty(list)) return null;
        
        List<ActInfoList> actInfoList = Lists.newArrayList();
        
        for(ActInfoDto actInfoDto : list){
            ActInfoList actlist = new ActInfoList();
            
            if(null == actInfoDto.getActInfoListId() || 0 == actInfoDto.getActInfoListId().longValue())break;
            
            if(null == actInfoDto.getActStatus() ||  ActInfoList.STATUS99.byteValue() == Byte.parseByte(actInfoDto.getActStatus())){
            	continue;
            }
            actlist.setId(actInfoDto.getActInfoListId());
            actlist.setEndTime(actInfoDto.getActEndTime());
            actlist.setLinkUrl(actInfoDto.getLinkUrl());
            actlist.setStartTime(actInfoDto.getActStartTime());
            actlist.setStatus(Byte.parseByte(actInfoDto.getActStatus()));
            actlist.setType(actInfoDto.getActType());
            actlist.setShowType(actInfoDto.getShowType());
            actInfoList.add(actlist);
        }
        
        ActInfoDto actInfoDto = list.get(0);
        actInfoDto.setActInfoList(actInfoList);
        return actInfoDto;
    }
    
    public ActInfoDto findByActInfoListId(Long actInfoListId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select");
        sql.append(" b.*,");
        sql.append(" a.id act_info_list_id, a.link_url, a.end_time act_end_time, a.start_time act_start_time, a.type act_type, a.`status` act_status ");
        sql.append(" from act_info_list a LEFT JOIN act_info b ON a.act_info_id=b.ID WHERE a.`status`!=? AND a.ID=? limit 1");
        ActInfoDto dto = null;
        try {
        	dto = mysqlPagedJdbcTemplate.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(ActInfoDto.class), ActInfo.STATUS99, actInfoListId);
		} catch (EmptyResultDataAccessException e) {
		}
        return dto;
    }
    
    public ActInfoDto findByActTest(Long actInfoListId){
    	StringBuilder sql = new StringBuilder();
    	sql.append("select b.*, a.start_time act_start_time, a.end_time act_end_time from act_test a left join act_info b on a.act_info_id=b.ID where a.act_info_list_id=? limit 1");
    	ActInfoDto actInfoDto = null;
        try {
        	actInfoDto = mysqlPagedJdbcTemplate.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(ActInfoDto.class), actInfoListId);
		} catch (EmptyResultDataAccessException e) {
		}
        return actInfoDto;
    }

}
