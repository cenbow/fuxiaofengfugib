/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.framework.common.dao.jdbc.v2;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.StringUtils;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.framework.utils.Dates;
import com.cqliving.framework.utils.PageUtil;
import com.cqliving.framework.utils.SqlHelper;
/**
 * Title:提供了分页查询的模板类
 * <p>Description:提供自定义SQL分页查询功能</p>
 * <p></p>
 * Copyright (c) CQLIVING 2013-2016
 * @author tangqiang on 2014年12月11日
 */
public class MysqlPagedJdbcTemplateV2 extends MysqlExtendJdbcTemplateV2 {
	
	/**
	 * <p>Description:分页查询</p>
	 * @param transClass 查询结果类型
	 * @param sql 
	 * @param pageInfo 主要接收currentPage和countOfCurrentPage参数
	 * @param object 查询条件
	 * @return
	 * @author tangqiang on 2015年1月13日
	 */
	public <T> Page<T> queryForPage(Class<T> transClass, String sql, PageInfo<T> pageInfo, Object... object) {
		Pageable pageable = new PageRequest(pageInfo.getCurrentPage()-1,pageInfo.getCountOfCurrentPage());
		Page<T> page = this.queryForPage(transClass, sql, pageable, object);
		PageUtil.pageToPageInfo(pageInfo, page);
		return page;
	}

	/**
	 * <p>Description:分页查询</p>
	 * @param transClass 查询结果类型
	 * @param sql
	 * @param conditions 查询条件
	 * @param pageInfo 主要接收currentPage和countOfCurrentPage参数
	 * @return
	 * @author tangqiang on 2015年1月13日
	 */
	public <T> Page<T> queryForPage(Class<T> transClass, String sql, Map<String, Object> conditions, PageInfo<T> pageInfo) {
		Pageable pageable = new PageRequest(pageInfo.getCurrentPage()-1,pageInfo.getCountOfCurrentPage());
		Page<T> page = queryForPage(transClass, sql, conditions, pageable, null);
		PageUtil.pageToPageInfo(pageInfo, page);
		return page;
	}

	/**
	 * <p>Description:分页查询</p>
	 * @param transClass 查询结果类型
	 * @param sql
	 * @param conditions 查询条件
	 * @param pageInfo 主要接收currentPage和countOfCurrentPage参数
	 * @param orders 排序对象，key为要排序的列名，当VALUE为true时升序排列，VALUE为false的时候降序排列
	 * @return
	 * @author tangqiang on 2015年1月13日
	 */
	public <T> Page<T> queryForPage(Class<T> transClass, String sql, Map<String, Object> conditions, PageInfo<T> pageInfo,
			Map<String, Boolean> orders) {
		Pageable pageable = new PageRequest(pageInfo.getCurrentPage()-1,pageInfo.getCountOfCurrentPage());
		Page<T> page = queryForPage(transClass, sql, conditions, pageable, orders);
		PageUtil.pageToPageInfo(pageInfo, page);
		return page;
	}
	
	/**
	 * <p>Description:分页查询，
	 * 参数是以占位符的方式传递</p>
	 * @param transClass 查询结果类型
	 * @param sql 这个查询SQL里面包含占位符信息
	 * @param conditions 查询条件
	 * @param pageInfo 主要接收currentPage和countOfCurrentPage参数
	 * @param orders 排序对象，key为要排序的列名，当VALUE为true时升序排列，VALUE为false的时候降序排列
	 * @return
	 * @author YUWU on 2016年06月24日
	 */
	@SuppressWarnings("deprecation")
	public <T> Page<T> queryForPageNamed(Class<T> transClass, String sql, Map<String, Object> conditions, PageInfo<T> pageInfo,
			Map<String, Boolean> orders) {
		Pageable pageable = new PageRequest(pageInfo.getCurrentPage()-1,pageInfo.getCountOfCurrentPage());
		
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String newSql = SqlHelper.processSql(parameters,conditions,sql);
        QueryConditionV2 count = QueryConditionV2.createCountQuery(newSql, new Object());
        //获取总行数
        Long total = namedParameterJdbcTemplate.queryForLong(count.sql, parameters);

		if (total < 1) {
			return new PageImpl<T>(Collections.<T> emptyList(),pageable,total);
		}
		//添加排序
		StringBuilder querysql = new StringBuilder(newSql);
		if (orders != null && !orders.isEmpty()) {
			QueryConditionV2.orderBy(querysql, orders);
		}
		newSql = querysql.toString();
		//添加limit分页
		newSql = pageableQuery(newSql, pageable);
		List<T> content = namedParameterJdbcTemplate.query(newSql, parameters, BeanPropertyRowMapper.newInstance(transClass));
		Page<T> page =  new PageImpl<T>(content, pageable, total);
		PageUtil.pageToPageInfo(pageInfo, page);
		return page;
	}
	
	/**
	 * 通过原始QL转换后查询Count
	 * 
	 * @param nativeSql
	 * @param ql
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unused")
	private long queryCount( String sql, Object... args) {
		String countQueryString = "select count(*) " + removeSelect(removeOrders(sql));
		return this.queryForCount(countQueryString, args);
	}
	
	/**
	 * 去除select 子句，未考虑union的情况
	 */
	private static String removeSelect(String sql) {
		int beginPos = sql.toLowerCase().indexOf("from");
		return sql.substring(beginPos);
	}
	
	/**
	 * 去除orderby 子句
	 */
	private static String removeOrders(String sql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	/**
	 * list 查询
	 */
	@Override
	public <T> List<T> queryForList(String sql, Class<T> elementType) throws DataAccessException {
		return query(sql, BeanPropertyRowMapper.newInstance(elementType));
	}
	
	/**
	 * <p>Description:自定义瀑布流分页查询</p>
	 * @param resultClass 查询结果封装类
	 * @param scrollPage 分页对象 @see {@link ScrollPage}
	 * @param sql
	 * @return
	 */
	public <T> ScrollPage<T> queryPage(Class<T> resultClass,ScrollPage<T> scrollPage,String sql,Map<String, Object> conditions){
		HashMap<String,String> columnsMap = QueryConditionV2.getColumnsMap(sql);
		StringBuilder pageSql = new StringBuilder(sql);
		//获取查询条件
		StringBuilder searchFilterSql = getSearchFilterSql(scrollPage,columnsMap);
		//将查询条件加入到原SQL中
		QueryConditionV2.concatSearchFilterSql(pageSql,searchFilterSql);
		
		List<T> result = null;
		if(conditions == null || conditions.isEmpty()){
		    //最外层拼排序条件
		    addOrderByStr(pageSql,scrollPage,columnsMap);
		    //拼limit
		    addLimitStr(pageSql,scrollPage.getPageSize());
			result = queryForList(pageSql.toString(),resultClass);
		}else{
		    //先拼conditions里面的条件,然后再拼limit
		    QueryConditionV2 tempSql = QueryConditionV2.createResultQuery(pageSql.toString(), conditions, null,columnsMap);
		    StringBuilder sb = new StringBuilder(tempSql.sql);
		    //最外层拼排序条件
            addOrderByStr(sb,scrollPage,columnsMap);
            //拼limit
		    addLimitStr(sb,scrollPage.getPageSize());
			result = queryForList(resultClass, sb.toString(), tempSql.params);
		}
		scrollPage.setPageResults(result);
		return scrollPage;
	}
	
	/**
	 * Title:瀑布流分页返回拼好的查询条件
	 * @author yuwu on 2016年9月6日
	 * @param scrollPage
	 * @param columnsMap
	 * @return
	 */
	private <T> StringBuilder getSearchFilterSql(ScrollPage<T> scrollPage,HashMap<String,String> columnsMap){
		StringBuilder searchFilterSql = new StringBuilder();
		
		List<ScrollPageOrder> listOrderColumn = scrollPage.getListOrderColumn();
		if(CollectionUtils.isNotEmpty(listOrderColumn)){
			boolean checkValueIsNull = checkListValueIsNull(scrollPage.getListOrderColumn());
			if(!checkValueIsNull){//不全部为null
				int size = listOrderColumn.size();
				searchFilterSql.append(" and (");
				for(int i = size ; i > 0 ; i--){
					searchFilterSql.append("(");
					for(int j = 0 ; j < i ; j++){
						ScrollPageOrder orderBy = listOrderColumn.get(j);
						if(j == (i - 1)){//最后一个条件
							processSql(columnsMap,orderBy,searchFilterSql);
						}else{
							processSql(columnsMap,orderBy,searchFilterSql,"=");
						}
						this.addCondition(searchFilterSql,j,i,"and ","");
					}
					this.addCondition(searchFilterSql,size-i ,size,") or ",")");
				}
				searchFilterSql.append(") ");
			}
		}else{
			ScrollPageOrder orderUniqueColumn = scrollPage.getOrderUniqueColumn();
			ScrollPageOrder orderBuessColumn = scrollPage.getOrderBuessColumn();
			if(orderBuessColumn != null && orderUniqueColumn != null && !StringUtils.isEmpty(orderBuessColumn.getValue()) && !StringUtils.isEmpty(orderUniqueColumn.getValue())){
	            //拼一个如下SQL: and ((orderBuessColumn = 'valueBuess' and orderUniqueColumn <||> 'valueUnique') or orderBuessColumn <||> 'valueBuess')
				searchFilterSql.append(" and (( ");
	            processSql(columnsMap,orderBuessColumn,searchFilterSql,"=");
	            searchFilterSql.append(" and ");
	            processSql(columnsMap,orderUniqueColumn,searchFilterSql);
	            searchFilterSql.append(" ) or ");
	            processSql(columnsMap,orderBuessColumn,searchFilterSql);
	            searchFilterSql.append(" ) ");
	        }else if(orderUniqueColumn != null && !StringUtils.isEmpty(orderUniqueColumn.getValue())){
	            //拼一个如下SQL: and orderUniqueColumn<'valueUnique'
	        	searchFilterSql.append(" and ");
	            processSql(columnsMap,orderUniqueColumn,searchFilterSql);
	        }
		}
		return searchFilterSql;
	}
	/**
	 * Title:判断listOrderColumn里面的对象的值是否不都为null
	 * @author yuwu on 2016年5月30日
	 * @param listOrderColumn
	 * @return {true:全部为null,false:不全部为null}
	 */
	private boolean checkListValueIsNull(List<ScrollPageOrder> listOrderColumn){
		if(CollectionUtils.isNotEmpty(listOrderColumn)){
			for(int i = 0 ; i < listOrderColumn.size() ; i++){
				if(!StringUtils.isEmpty(listOrderColumn.get(i).getValue())){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Title:往StringBuilder里面添加字符串。
	 * 判断当前索引是否最后一个索引，如果是最后一个则添加lastAppendStr,否则添加currentAppendStr
	 * @author yuwu on 2016年5月30日
	 * @param pageSql
	 * @param currentIndex
	 * @param lastIndex
	 * @param currentAppendStr
	 * @param lastAppendStr
	 */
	private void addCondition(StringBuilder sb,int currentIndex,int lastIndex,String currentAppendStr,String lastAppendStr){
		if(currentIndex + 1 >= lastIndex){
			sb.append(lastAppendStr);
		}else{
			sb.append(currentAppendStr);
		}
	}
	/**
	 * Title:最外层拼排序条件
	 * @author yuwu on 2016年4月8日
	 * @param sql
	 * @param scrollPage
	 */
    private <T> void addOrderByStr(StringBuilder sql,ScrollPage<T> scrollPage,HashMap<String,String> columnsMap){
    	List<ScrollPageOrder> listOrderColumn = scrollPage.getListOrderColumn();
		if(CollectionUtils.isNotEmpty(listOrderColumn)){
			sql.append(" order by ");
			int size = listOrderColumn.size();
			for(int i = 0 ; i < listOrderColumn.size() ; i++){
				ScrollPageOrder orderBy = listOrderColumn.get(i);
				//取出新列名
				String newColumnName = QueryConditionV2.getNewColumnName(columnsMap,orderBy.getColumnName());
				sql.append(newColumnName).append(" ").append(orderBy.getOrderByType()).append(" ");
				this.addCondition(sql,i,size," , ","");
			}
		}else{
			ScrollPageOrder orderBuessColumn = scrollPage.getOrderBuessColumn();
	        ScrollPageOrder orderUniqueColumn = scrollPage.getOrderUniqueColumn();
	        if(orderBuessColumn != null){
	        	//取出新列名
				String newColumnName = QueryConditionV2.getNewColumnName(columnsMap,orderBuessColumn.getColumnName());
	            sql.append(" order by ").append(newColumnName).append(" ").append(orderBuessColumn.getOrderByType()).append(" ");
	        }
	        if(orderUniqueColumn != null){
	        	//取出新列名
				String newColumnName = QueryConditionV2.getNewColumnName(columnsMap,orderUniqueColumn.getColumnName());
	            //如果orderBuessColumn不为空，则说明已经存在order by关键字
	            sql.append(orderBuessColumn == null?" order by " : " ,");
	            sql.append(newColumnName).append(" ").append(orderUniqueColumn.getOrderByType()).append(" ");
	        }
		}
    }
    
    /**
     * Title:拼限制条件
     * @author yuwu on 2016年4月8日
     * @param sb
     * @param pageSize
     */
	private void addLimitStr(StringBuilder sb,int pageSize){
	    sb.append(" limit ").append("0,").append(pageSize);
	}
	
	private void processSql(HashMap<String,String> columnsMap,ScrollPageOrder order,StringBuilder pageSql){
	    String operate = order.getOrderByType().equalsIgnoreCase(ScrollPage.ASC) ? ">":"<";
        processSql(columnsMap,order,pageSql,operate);
	}
	/***
	 * 拼瀑布试分页SQL
	 * Title:
	 * <p>Description:</p>
	 * Copyright (c) CQLIVING 2013-2016
	 * @author yuwu on 2016年1月30日
	 * @param order
	 * @param pageSql
	 * @param operate
	 */
	private void processSql(HashMap<String,String> columnsMap,ScrollPageOrder order,StringBuilder pageSql,String operate){
		Object value = order.getValue();
		//取出新列名
		String newColumnName = QueryConditionV2.getNewColumnName(columnsMap,order.getColumnName());
		if(value == null){
			pageSql.append(newColumnName).append(" IS NULL ");
		}else{
			if(value instanceof Date){
		        //DATE_FORMAT(create_time,'%Y-%m-%d %H:%i:%s') = 'yyyy-MM-dd HH:mm:ss'
		        pageSql.append("DATE_FORMAT(").append(newColumnName).append(",'%Y-%m-%d %H:%i:%s') ").append(operate).append("'");
	            pageSql.append(Dates.format((Date)value,Dates.YYYY_MM_DD_HH_MM_SS)).append("' ");
		    }else{
		        pageSql.append(newColumnName).append(operate).append("'");
	            pageSql.append(value.toString()).append("' ");
		    }
		}
	}
}
