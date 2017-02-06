package com.cqliving.cloud.online.account.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.account.dao.UserReportDaoCustom;
import com.cqliving.cloud.online.account.domain.UserReport;
import com.cqliving.cloud.online.account.dto.UserReportDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;

public class UserReportDaoImpl implements UserReportDaoCustom {
    
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

    /**
     * 获取单个举报信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月31日上午10:26:49
     */
    @Override
    public UserReportDto getByIdAndSourceType(Long id, Byte sourceType,Byte type) {
        StringBuffer sql = new StringBuffer("SELECT a.* ," );
        
        if(UserReport.OPERATETYPE1.equals(type)){//评论
            sql.append(" c.content source ");
        }else{
            if(UserReport.SOURCETYPE1.equals(sourceType)){//新闻
                sql.append(" c.title source ");
            }else if(UserReport.SOURCETYPE2.equals(sourceType)){//问政
                sql.append(" c.title source ");
            }else if(UserReport.SOURCETYPE3.equals(sourceType)){//商情
                sql.append(" c.name source ");
            }else if(UserReport.SOURCETYPE4.equals(sourceType)){//随手拍
                sql.append(" c.content source ");
            }else if(UserReport.SOURCETYPE5.equals(sourceType)){//段子
                sql.append(" c.content source ");
            }else if(UserReport.SOURCETYPE6.equals(sourceType)){//活动
                sql.append(" d.title source ");
            }else if(UserReport.SOURCETYPE7.equals(sourceType)){//话题
                sql.append(" c.name source ");
            }else if(UserReport.SOURCETYPE10.equals(sourceType)){//旅游
                sql.append(" c.name source ");
            }
        }
        
        sql.append(" FROM user_report a ");
        
        if(UserReport.OPERATETYPE1.equals(type)){//评论
            sql.append(" LEFT JOIN user_info_reply c ON a.source_id = c.id ");
        }else{
            if(UserReport.SOURCETYPE1.equals(sourceType)){//新闻
                sql.append(" LEFT JOIN info_classify c ON a.source_id = c.id ");
            }else if(UserReport.SOURCETYPE2.equals(sourceType)){//问政
                sql.append(" LEFT JOIN wz_question c ON a.source_id = c.id ");
            }else if(UserReport.SOURCETYPE3.equals(sourceType)){//商情
                sql.append(" LEFT JOIN shop_info c ON a.source_id = c.id ");
            }else if(UserReport.SOURCETYPE4.equals(sourceType)){//随手拍
                sql.append(" LEFT JOIN shoot_info c ON a.source_id = c.id ");
            }else if(UserReport.SOURCETYPE5.equals(sourceType)){//段子
                sql.append(" LEFT JOIN joke_info c ON a.source_id = c.id ");
            }else if(UserReport.SOURCETYPE6.equals(sourceType)){//活动
                sql.append(" LEFT JOIN act_info_list c ON a.source_id = c.id INNER JOIN act_info d ON d.id=c.act_info_id ");
            }else if(UserReport.SOURCETYPE7.equals(sourceType)){//话题
                sql.append(" LEFT JOIN topic_info c ON a.source_id = c.id ");
            }else if(UserReport.SOURCETYPE10.equals(sourceType)){//旅游
                sql.append(" LEFT JOIN tourism_info c ON a.source_id = c.id");
            }
        }
        
        sql.append(" where a.id = ? ");
        return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(UserReportDto.class), id);
    }
}