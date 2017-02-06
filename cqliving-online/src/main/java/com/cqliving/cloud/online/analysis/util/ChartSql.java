/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.cqliving.cloud.online.analysis.util;


/**
 * Title:各种统计报名的SQL
 * @author yuwu on 2016年06月21日
 */
public class ChartSql {
    /**
        -- 1、编辑分栏目统计表
		SELECT t1.columns_id,
		       t1.creator_id AS user_id,
		       t3.username AS user_name,
		       t3.nickname AS nickname,
		       t2.name AS columns_name ,
		       count(t1.id) AS info_count,
		       sum(ifnull(t5.view_total_count,0)) AS view_total_count,
		       sum(ifnull(t6.reply_count,0)) AS reply_count ,
		       sum(ifnull(t4.active_Count,0)) AS active_count
		FROM info_classify t1
		LEFT JOIN `app_columns` t2 ON t1.`columns_id` = t2.id
		LEFT JOIN sys_user t3 ON t1.creator_id = t3.id 
		LEFT JOIN
		  (SELECT t2.information_id,
		         COUNT(t1.id) AS active_Count
		    FROM user_survey_history t1
		   INNER JOIN survey_info t2 ON t1.survey_id = t2.id
		   WHERE T1.APP_ID = 1
		  	AND t1.create_time >= str_to_date('2016-11-15','%Y-%m-%d')
		  	AND t1.create_time <= str_to_date('2016-12-21','%Y-%m-%d')
		   GROUP BY t2.information_id
		   ) t4 ON t1.`information_id` = t4.information_id
		LEFT JOIN
		  (SELECT t1.information_id,count(t1.id) AS view_Total_Count
		   FROM user_view_recode t1
		   WHERE t1.source_type = 0
		     AND t1.`app_id` = 1
			 AND t1.create_time >= str_to_date('2016-11-15','%Y-%m-%d')
		  	 AND t1.create_time <= str_to_date('2016-12-21','%Y-%m-%d')
		   GROUP BY t1.information_id) t5 ON t1.information_id = t5.information_id
		LEFT JOIN
		  (SELECT t1.source_id AS information_id,COUNT(t1.id) AS reply_Count
		   FROM user_info_reply t1
		   WHERE t1.source_type = 1
		     AND t1.app_id = 1
		  	 AND t1.status = 3
			 AND t1.create_time >= str_to_date('2016-11-15','%Y-%m-%d')
		  	 AND t1.create_time <= str_to_date('2016-12-21','%Y-%m-%d')
		   GROUP BY t1.source_id) t6 ON t1.information_id = t6.information_id
		WHERE t1.`app_id` = 1 
		-- and t1.columns_id = 5
		  AND t1.status = 3
		  AND t3.nickname LIKE '%%'
		  AND t1.online_time >= str_to_date('2016-11-15','%Y-%m-%d')
		  AND t1.online_time <= str_to_date('2016-12-21','%Y-%m-%d')
		GROUP BY t1.creator_id,t1.columns_id
		order by sum(ifnull(t5.view_total_count,0)) desc;
     */
    public static String queryAnalyInfoColumnsSql() {
        StringBuilder sql = new StringBuilder();
        
        //sql.append(" -- 1、编辑分栏目统计表 ");
        sql.append(" SELECT t1.columns_id, ");
        sql.append("        t1.creator_id AS user_id, ");
        sql.append("        t3.username AS user_name, ");
        sql.append("        t3.nickname AS nickname, ");
        sql.append("        t2.name AS columns_name , ");
        sql.append("        count(t1.id) AS info_count, ");
        sql.append("        sum(ifnull(t5.view_total_count,0)) AS view_total_count, ");
        sql.append("        sum(ifnull(t6.reply_count,0)) AS reply_count , ");
        sql.append("        sum(ifnull(t4.active_Count,0)) AS active_count ");
        sql.append(" FROM info_classify t1 ");
        sql.append(" LEFT JOIN `app_columns` t2 ON t1.`columns_id` = t2.id ");
        sql.append(" LEFT JOIN sys_user t3 ON t1.creator_id = t3.id  ");
        sql.append(" LEFT JOIN ");
        sql.append("   (SELECT t2.information_id, ");
        sql.append("          COUNT(t1.id) AS active_Count ");
        sql.append("     FROM user_survey_history t1 ");
        sql.append("    INNER JOIN survey_info t2 ON t1.survey_id = t2.id ");
        sql.append("    WHERE T1.APP_ID = :EQ_appId ");
        sql.append("      AND t1.create_time >= :GTE_createTime  ");
        sql.append("      AND t1.create_time <= :LT_createTime ");
        sql.append("    GROUP BY t2.information_id ");
        sql.append("    ) t4 ON t1.`information_id` = t4.information_id ");
        sql.append(" LEFT JOIN ");
        sql.append("   (SELECT t1.information_id,count(t1.id) AS view_Total_Count ");
        sql.append("    FROM user_view_recode t1 ");
        sql.append("    WHERE t1.source_type = 0 ");
        sql.append("      AND t1.`app_id` = :EQ_appId ");
        sql.append("      AND t1.create_time >= :GTE_createTime  ");
        sql.append("      AND t1.create_time <= :LT_createTime ");
        sql.append("    GROUP BY t1.information_id) t5 ON t1.information_id = t5.information_id ");
        sql.append(" LEFT JOIN ");
        sql.append("   (SELECT t1.source_id AS information_id,COUNT(t1.id) AS reply_Count ");
        sql.append("    FROM user_info_reply t1 ");
        sql.append("    WHERE t1.source_type = 1 ");
        sql.append("      AND t1.app_id = :EQ_appId ");
        sql.append("      AND t1.status = 3 ");
        sql.append("      AND t1.create_time >= :GTE_createTime  ");
        sql.append("      AND t1.create_time <= :LT_createTime ");
        sql.append("    GROUP BY t1.source_id) t6 ON t1.information_id = t6.information_id ");
        sql.append(" where t1.`app_id` = :EQ_appId ");
        sql.append("    and t1.columns_id = :EQ_columnsId ");
        sql.append("    and t3.nickname like concat('%',:LIKE_nickname,'%') ");
        sql.append("    and t1.online_time >= :GTE_onlineTime  ");
        sql.append("    and t1.online_time <= :LT_onlineTime ");
        sql.append("    group by t1.creator_id,t1.columns_id ");
        sql.append("    order by sum(ifnull(t5.view_total_count,0)) desc ");
        
        return sql.toString();
    }
    
    /**
		-- 2、新闻详情统计表
		SELECT t1.id,
		       t1.creator_id AS user_id,
		       t3.username AS user_name,
		       t3.nickname AS nickname,
		       t2.name AS COLUMN_NAME,
		       t1.title,
		       DATE_FORMAT(t1.online_time,'%Y-%m-%d %H:%i:%s') AS online_time,
		       ifnull(t5.view_total_count,0) AS view_total_count,
		       ifnull(T6.reply_count,0) AS reply_count,
		       ifnull(t4.active_Count,0) AS active_count
		FROM info_classify t1
		LEFT JOIN `app_columns` t2 ON t1.`columns_id` = t2.id
		LEFT JOIN sys_user t3 ON t1.creator_id = t3.id -- t4 统计指定APP下每个原创资讯里面投票活动、用户的参与量
		LEFT JOIN
		  (SELECT t2.information_id,count(t1.id) AS active_Count -- 参与次数
				-- ,COUNT(DISTINCT t1. user_id) AS active_User_Count -- 参加人数
		   FROM user_survey_history t1
		   INNER JOIN survey_info t2 ON t1.survey_id = t2.id
		   WHERE T1.APP_ID = 1
			 AND t1.create_time >= str_to_date('2016-11-15','%Y-%m-%d')
		  	 AND t1.create_time <= str_to_date('2016-12-21','%Y-%m-%d')
		   GROUP BY t2.information_id ) t4 ON t1.`information_id` = t4.information_id
		LEFT JOIN
		  (SELECT t1.information_id,count(t1.id) AS view_Total_Count -- 浏览次数
				-- ,COUNT(DISTINCT IFNULL(t1. user_id, t1.ip_addr)) AS view_User_Count -- 查看人数
		   FROM user_view_recode t1
		   -- INNER JOIN info_classify t2 ON t1.source_id = t2.id
		   WHERE t1.source_type = 0
		     AND t1.`app_id` = 1
			 AND t1.create_time >= str_to_date('2016-11-15','%Y-%m-%d')
		  	 AND t1.create_time <= str_to_date('2016-12-21','%Y-%m-%d')
		     -- AND t2.status = 3
		   GROUP BY t1.information_id) t5 ON t1.information_id = t5.information_id
		LEFT JOIN
		  (SELECT t1.source_id AS information_id,COUNT(t1.id) AS reply_Count -- 回复次数
				-- ,COUNT(DISTINCT t1. reply_user_id) AS reply_User_Count -- 回复人数
		   FROM user_info_reply t1
		   -- INNER JOIN info_classify t2 ON t1.info_classify_id = t2.id
		   WHERE t1.source_type = 1
		     AND t1.app_id = 1
		  	 AND t1.status = 3
			 AND t1.create_time >= str_to_date('2016-11-15','%Y-%m-%d')
		  	 AND t1.create_time <= str_to_date('2016-12-21','%Y-%m-%d')
		   GROUP BY t1.source_id) t6 ON t1.information_id = t6.information_id
		WHERE t1.`app_id` = 1 
			-- and t1.columns_id = 5
			and t3.nickname like '%%'
			and t1.title like '%重庆%'
			AND t1.status = 3
			AND t1.online_time >= str_to_date('2016-12-01','%Y-%m-%d')
			AND t1.online_time <= str_to_date('2016-12-21','%Y-%m-%d')
			order by ifnull(t5.view_total_count,0) desc;
	 */
	public static String queryAnalyInfoDetailSql() {
	    StringBuilder sql = new StringBuilder();
	    //sql.append(" -- 2、新闻详情统计表 ");
	    sql.append(" SELECT t1.id, ");
        sql.append("        t1.creator_id AS user_id, ");
        sql.append("        t3.username AS user_name, ");
        sql.append("        t3.nickname AS nickname, ");
        sql.append("        t2.name AS columns_name, ");
        sql.append("        t1.title, ");
        sql.append("        DATE_FORMAT(t1.online_time,'%Y-%m-%d %H:%i:%s') AS online_time, ");
        sql.append("        ifnull(t5.view_total_count,0) AS view_total_count, ");
        sql.append("        ifnull(T6.reply_count,0) AS reply_count, ");
        sql.append("        ifnull(t4.active_Count,0) AS active_count ");
        sql.append(" FROM info_classify t1 ");
        sql.append(" LEFT JOIN `app_columns` t2 ON t1.`columns_id` = t2.id ");
        sql.append(" LEFT JOIN sys_user t3 ON t1.creator_id = t3.id ");
        sql.append(" LEFT JOIN ");
        sql.append("   (SELECT t2.information_id,count(t1.id) AS active_Count ");
        sql.append("    FROM user_survey_history t1 ");
        sql.append("    INNER JOIN survey_info t2 ON t1.survey_id = t2.id ");
        sql.append("    WHERE T1.APP_ID = :EQ_appId ");
        sql.append("      AND t1.create_time >= :GTE_createTime  ");
        sql.append("      AND t1.create_time <= :LT_createTime ");
        sql.append("    GROUP BY t2.information_id ) t4 ON t1.`information_id` = t4.information_id ");
        sql.append(" LEFT JOIN ");
        sql.append("   (SELECT t1.information_id,count(t1.id) AS view_Total_Count ");
        sql.append("    FROM user_view_recode t1 ");
        sql.append("    WHERE t1.source_type = 0 ");
        sql.append("      AND t1.`app_id` = :EQ_appId ");
        sql.append("      AND t1.create_time >= :GTE_createTime  ");
        sql.append("      AND t1.create_time <= :LT_createTime ");
        sql.append("    GROUP BY t1.information_id) t5 ON t1.information_id = t5.information_id ");
        sql.append(" LEFT JOIN ");
        sql.append("   (SELECT t1.source_id AS information_id,COUNT(t1.id) AS reply_Count ");
        sql.append("    FROM user_info_reply t1 ");
        sql.append("    WHERE t1.source_type = 1 ");
        sql.append("      AND t1.app_id = :EQ_appId ");
        sql.append("      AND t1.status = 3 ");
        sql.append("      AND t1.create_time >= :GTE_createTime  ");
        sql.append("      AND t1.create_time <= :LT_createTime ");
        sql.append("    GROUP BY t1.source_id) t6 ON t1.information_id = t6.information_id ");
	    sql.append(" where t1.`app_id` = :EQ_appId  ");
	    sql.append("     and t1.columns_id = :EQ_columnsId ");
	    sql.append("     and t3.nickname like concat('%',:LIKE_nickname,'%') ");
	    sql.append("     and t1.title like concat('%',:LIKE_title,'%') ");
	    sql.append("     and t1.online_time >= :GTE_onlineTime  ");
	    sql.append("     and t1.online_time <= :LT_onlineTime ");
        sql.append(" 	order by ifnull(t5.view_total_count,0) desc ");
	    return sql.toString();
	}
	
	
	/**
		-- 3、编辑发布新闻情况统计
		SELECT t2.user_id,
		       t1.username as user_name,
		       t1.nickname as nickname,
		       IFNULL(sum(t2.info_count), 0) AS info_count,
		       IFNULL(sum(t2.view_Total_Count), 0) AS view_Total_Count,
		       IFNULL(sum(t2.view_User_Count), 0) AS view_User_Count,
		       IFNULL(sum(t2.reply_Count), 0) AS reply_Count,
		       IFNULL(sum(t2.reply_User_Count), 0) AS reply_User_Count,
		       IFNULL(sum(t2.active_Count), 0) AS active_Count,
		       IFNULL(sum(t2.active_User_Count), 0) AS active_User_Count
		  FROM (SELECT * FROM sys_user t1 WHERE t1.USERTYPE != 1) t1
		  left join analysis_information t2
		    on t1.id = t2. USER_ID
		 where t1.app_id = 25
		   and t1.nickname like '%h%'
		   and t2.statistics_time >= str_to_date('2016-06-15', '%Y-%m-%d')
		   and t2.statistics_time < str_to_date('2016-06-26', '%Y-%m-%d')
		   group by t2.user_id,t1.username,t1.nickname
	 */
	public static String queryAnalyInfoStatisticsSql() {
	    StringBuilder sql = new StringBuilder();
	    //sql.append(" -- 3、编辑发布新闻情况统计 ");
	    sql.append(" SELECT t2.user_id, ");
	    sql.append("        t1.username as user_name, ");
	    sql.append("        t1.nickname as nickname, ");
	    sql.append("        IFNULL(sum(t2.info_count), 0) AS info_count, ");
	    sql.append("        IFNULL(sum(t2.view_Total_Count), 0) AS view_Total_Count, ");
	    sql.append("        IFNULL(sum(t2.view_User_Count), 0) AS view_User_Count, ");
	    sql.append("        IFNULL(sum(t2.reply_Count), 0) AS reply_Count, ");
	    sql.append("        IFNULL(sum(t2.reply_User_Count), 0) AS reply_User_Count, ");
	    sql.append("        IFNULL(sum(t2.active_Count), 0) AS active_Count, ");
	    sql.append("        IFNULL(sum(t2.active_User_Count), 0) AS active_User_Count ");
	    sql.append("   FROM (SELECT * FROM sys_user t1 WHERE t1.USERTYPE != 1) t1 ");
	    sql.append("   left join analysis_information t2 ");
	    sql.append("     on t1.id = t2. USER_ID ");
	    sql.append("  where t1.app_id = :EQ_appId ");
	    sql.append("    and t1.nickname like concat('%',:LIKE_nickname,'%') ");
	    sql.append("    and t2.statistics_time >= :GTE_onlineTime ");
	    sql.append("    and t2.statistics_time < :LT_onlineTime ");
	    sql.append("    group by t2.user_id,t1.username,t1.nickname ");
	    
	    return sql.toString();
	}
}
