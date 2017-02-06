/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.analysis.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.analysis.dao.AnalysisInformationDaoCustom;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoColumns;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoDetail;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoStatistics;
import com.cqliving.cloud.online.analysis.util.ChartSql;
import com.cqliving.framework.common.dao.jdbc.MysqlPagedJdbcTemplate;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.utils.SqlHelper;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

/**
 * Title:数据统计
 * @author yuwu on 2016年06月21日
 */
@Repository
public class AnalysisInformationDaoImpl implements AnalysisInformationDaoCustom {

    @Autowired
    private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	/***
     * 1、编辑分栏目统计表
     * @author yuwu on 2016年06月21日
     * @param appId      (必填)客户端ID
     * @param colunmsId  (非必填)栏目Id
     * @param userName   (非必填)编辑人员名称
     * @param startTime  (非必填)新闻发布开始时间
     * @param endTime    (非必填)新闻发布结束时间
     * @return
     * -- 1、编辑分栏目统计表
		select t1.columns_id,t1.creator_id as user_id,t3.user_name,t3.nickname as nick_name ,t2.name as column_name
		,count(t1.id) as news_count,sum(view_count) as view_count,sum(reply_count) as reply_count
		,sum(ifnull(join_count,0)) as join_count
		from info_classify t1
		left join `app_columns` t2 on t1.`columns_id` = t2.id
		left join  sys_user t3 on t1.creator_id = t3.id
		-- t4 统计指定APP下每个资讯用户的参与量
		left join (select t2.information_id,count(1) as join_count from user_survey_history t1 
			left join survey_info t2 on t1.survey_id = t2.id where t1.app_id = 25
			group by t2.`information_id`) t4 on t1.`information_id` = t4.information_id
		where t1.`app_id` = 25 
		and t1.columns_id = 5
		and t1.online_time >= str_to_date('2016-06-15','%Y-%m-%d') 
		and t1.online_time < str_to_date('2016-06-21','%Y-%m-%d')
		group by t1.creator_id,t1.columns_id;
     */
    public List<AnalyInfoColumns> queryAnalyInfoColumnsList(Long appId,Long colunmsId,String userName,Date startTime,Date endTime){
    	String sql = ChartSql.queryAnalyInfoColumnsSql();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(mysqlPagedJdbcTemplate);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        Map<String,Object> param = Maps.newHashMap();
        param.put("EQ_appId", appId);
        param.put("EQ_columnsId", colunmsId);
        param.put("LIKE_nickname", userName);
        param.put("GET_startTime", startTime);
        param.put("LT_endTime", endTime);
        String newSql = SqlHelper.processSql(parameters,param,sql);
        
        PageInfo<AnalyInfoColumns> pageInfo = new PageInfo<AnalyInfoColumns>();
        mysqlPagedJdbcTemplate.queryForPageNamed(AnalyInfoColumns.class, newSql, param, pageInfo, null);
        List<AnalyInfoColumns> list = namedParameterJdbcTemplate.query(newSql, parameters, BeanPropertyRowMapper.newInstance(AnalyInfoColumns.class));
        return list;
    }
    public PageInfo<AnalyInfoColumns> queryAnalyInfoColumnsPage(PageInfo<AnalyInfoColumns> pageInfo, Map<String, Object> param,Map<String, Boolean> orderMap){
    	String sql = ChartSql.queryAnalyInfoColumnsSql();
        mysqlPagedJdbcTemplate.queryForPageNamed(AnalyInfoColumns.class, sql, param, pageInfo, orderMap);
        return pageInfo;
    }
    
    /***
     * 2、新闻详情数据
     * @author yuwu on 2016年06月21日
     * @param appId      (必填)客户端ID
     * @param colunmsId  (非必填)栏目Id
     * @param userName   (非必填)编辑人员名称
     * @param title      (非必填)新闻标题
     * @param startTime  (非必填)新闻发布开始时间
     * @param endTime    (非必填)新闻发布结束时间
     * @return
     */
    public List<AnalyInfoDetail> queryAnalyInfoDetailList(Long appId,Long colunmsId,String userName,String title,Date startTime,Date endTime){
    	String sql = ChartSql.queryAnalyInfoDetailSql();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(mysqlPagedJdbcTemplate);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        Map<String,Object> param = Maps.newHashMap();
        param.put("EQ_appId", appId);
        param.put("EQ_columnsId", colunmsId);
        param.put("LIKE_nickname", userName);
        param.put("LIKE_title", title);
        param.put("GTE_onlineTime", startTime);
        param.put("LT_onlineTime", endTime);
        String newSql = SqlHelper.processSql(parameters,param,sql);
        List<AnalyInfoDetail> list = namedParameterJdbcTemplate.query(newSql, parameters, BeanPropertyRowMapper.newInstance(AnalyInfoDetail.class));
        return list;
    }
    @Override
    public PageInfo<AnalyInfoDetail> queryAnalyInfoDetailPage(PageInfo<AnalyInfoDetail> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap){
    	String sql = ChartSql.queryAnalyInfoDetailSql();
        mysqlPagedJdbcTemplate.queryForPageNamed(AnalyInfoDetail.class, sql, map, pageInfo, orderMap);
        return pageInfo;
    }
	
    /***
     * 3、编辑发布新闻情况统计
     * @author yuwu on 2016年06月21日
     * @param appId      (必填)客户端ID
     * @param userName   (非必填)编辑人员名称
     * @param startTime  (非必填)统计--开始时间
     * @param endTime    (非必填)统计--结束时间
     * @return
     */
    @Override
    public List<AnalyInfoStatistics> queryAnalyInfoStatisticsList(Long appId,String userName,Date startTime,Date endTime){
    	String sql = ChartSql.queryAnalyInfoStatisticsSql();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(mysqlPagedJdbcTemplate);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        Map<String,Object> param = Maps.newHashMap();
        param.put("EQ_appId", appId);
        param.put("LIKE_nickname", userName);
        param.put("GTE_onlineTime", startTime);
        param.put("LT_onlineTime", endTime);
        String newSql = SqlHelper.processSql(parameters,param,sql);
        List<AnalyInfoStatistics> list = namedParameterJdbcTemplate.query(newSql, parameters, BeanPropertyRowMapper.newInstance(AnalyInfoStatistics.class));
        return list;
    }
    @Override
    public PageInfo<AnalyInfoStatistics> queryAnalyInfoStatisticsPage(PageInfo<AnalyInfoStatistics> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap){
    	String sql = ChartSql.queryAnalyInfoStatisticsSql();
        mysqlPagedJdbcTemplate.queryForPageNamed(AnalyInfoStatistics.class, sql, map, pageInfo, orderMap);
        return pageInfo;
    }
    
    @Override
    public void callProcInfoStatistics(){
    	Date yestesday = DateUtil.add(Calendar.getInstance().getTime(), Calendar.DAY_OF_MONTH, -1);
    	String date = DateUtil.format(yestesday,DateUtil.FORMAT_YYYY_MM_DD);
    	this.mysqlPagedJdbcTemplate.execute("call PROC_INFO_STATISTICS('"+date+"')");
    	
    	//有返回值的调用,参考：http://sunbin123.iteye.com/blog/1007556
    	/*this.mysqlPagedJdbcTemplate.execute(new CallableStatementCreator() {
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				String storedProc = "{call PROC_INFO_STATISTICS(?)}";// 调用的sql
				CallableStatement cs = con.prepareCall(storedProc);
				cs.setString(1, date);// 设置输入参数的值
				cs.registerOutParameter(2, OracleTypes.VARCHAR);// 注册输出参数的类型
				return cs;
			}
		}, new CallableStatementCallback<Object>() {
			public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute();
				return cs.getString(2);// 获取输出参数的值
			}
		});*/
    }
}