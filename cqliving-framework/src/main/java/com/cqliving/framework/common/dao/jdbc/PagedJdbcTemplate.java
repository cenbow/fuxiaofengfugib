package com.cqliving.framework.common.dao.jdbc;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * JdbcTemplate分页查询扩展
 * 
 * 废弃原来的低版本的ObjectRowMapper扩展
 * 查询结果通过RowMapper直接映射为对象(映射基本规则使用查询的列名与对象的属性名匹配，并实现自动类型转换)
 * 
 * 为了简单，如果是复杂多表关联查询，请使用视图。
 * 
 * @author zhangpu
 * 
 */

public class PagedJdbcTemplate extends JdbcTemplate {

	/** ORACLE 分页查询SQL模版 */
	private static final String ORACLE_PAGESQL_TEMPLATE = "SELECT * FROM (SELECT XX.*,rownum ROW_NUM FROM (${sql}) XX ) ZZ where ZZ.ROW_NUM BETWEEN ${startNum} AND ${endNum}";

	/**
	 * Oracle jdbc 分页查询简单封装
	 * 
	 * @param <T>
	 * @param pageInfo
	 * @param sql
	 * @param requiredType
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public <T> PageInfo<T> queryOracle(PageInfo<T> pageInfo, String sql, Class<T> requiredType) {
		String orderBy = "";
		if (StringUtils.contains(sql, "order by")) {
			orderBy = sql.substring(sql.indexOf("order by"));
			sql = sql.substring(0, sql.indexOf("order by"));
		}
		String sqlFrom = sql.substring(sql.indexOf("from"));
		String sqlCount = "select count(*) " + sqlFrom;
		// 总记录数
		long totalCount = super.queryForLong(sqlCount);
		int startNum = pageInfo.getCountOfCurrentPage() * (pageInfo.getCurrentPage() - 1) + 1;
		int endNum = pageInfo.getCountOfCurrentPage() * (pageInfo.getCurrentPage());
		if (endNum > totalCount) {
			endNum = (int) totalCount;
		}
		long totalPage = (totalCount % pageInfo.getCountOfCurrentPage() == 0 ? totalCount / pageInfo.getCountOfCurrentPage() : totalCount
				/ pageInfo.getCountOfCurrentPage() + 1);

		String pageSql = ORACLE_PAGESQL_TEMPLATE + " " + orderBy;
		pageSql = StringUtils.replace(pageSql, "${sql}", sql);
		pageSql = StringUtils.replace(pageSql, "${startNum}", String.valueOf(startNum));
		pageSql = StringUtils.replace(pageSql, "${endNum}", String.valueOf(endNum));

		//List<T> result = query(pageSql, BeanPropertyRowMapper.newInstance(requiredType));
		//增强JDBC的ORACLE分页查询方法，支持MAP方式的数据行返回  王海龙 2013-11-27
		List<T> result = null;
		if (requiredType.getName().equals(Map.class.getName())) {
			result = (List<T>) queryForList(pageSql);
		} else {
			result = query(pageSql, BeanPropertyRowMapper.newInstance(requiredType));
		}
		
		
		pageInfo.setPageResults(result);
		pageInfo.setTotalCount(totalCount);
		pageInfo.setTotalPage(totalPage);
		return pageInfo;
	}

	/**
	 * JDBC分页查询
	 * 
	 * @param pageInfo
	 * @param sql
	 * @param dto
	 * @param pageColumn
	 *            分页用的列(大数据查询时该列最好有索引)
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public <T> PageInfo<T> queryMSSQL(PageInfo<T> pageInfo, String sql, Class<T> dtoEntity, String pageColumn) {
		try {
			String sqlCount = "select count(*) from (" + sql + ") as t1";
			// 总记录数
			long totalCount = super.queryForLong(sqlCount);
			int startNum = pageInfo.getCountOfCurrentPage() * (pageInfo.getCurrentPage() - 1);
			int endNum = pageInfo.getCountOfCurrentPage() * (pageInfo.getCurrentPage());
			if (endNum > totalCount) {
				endNum = (int) totalCount;
			}
			long totalPage = (totalCount % pageInfo.getCountOfCurrentPage() == 0 ? totalCount / pageInfo.getCountOfCurrentPage() : totalCount
					/ pageInfo.getCountOfCurrentPage() + 1);

			String pageSql = sql + " limit " + startNum + "," + pageInfo.getCountOfCurrentPage();
			List<T> result = query(pageSql, BeanPropertyRowMapper.newInstance(dtoEntity));
			pageInfo.setPageResults(result);
			pageInfo.setTotalCount(totalCount);
			pageInfo.setTotalPage(totalPage);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return pageInfo;
	}

	/**
	 * JDBC分页查询
	 * 
	 * @param pageInfo
	 * @param sql
     * @param params
	 * @param dtoEntity
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public <T> PageInfo<T> queryMySql(PageInfo<T> pageInfo, String sql, Object[] params, Class<T> dtoEntity) {
		try {
			String sqlFrom = sql.substring(sql.indexOf("from"));
			String sqlCount = "select count(*) " + sqlFrom;
			// 总记录数
			long totalCount = super.queryForLong(sqlCount, params);
			int startNum = pageInfo.getCountOfCurrentPage() * (pageInfo.getCurrentPage() - 1);
			int endNum = pageInfo.getCountOfCurrentPage() * (pageInfo.getCurrentPage());
			if (endNum > totalCount) {
				endNum = (int) totalCount;
			}
			long totalPage = (totalCount % pageInfo.getCountOfCurrentPage() == 0 ? totalCount / pageInfo.getCountOfCurrentPage() : totalCount
					/ pageInfo.getCountOfCurrentPage() + 1);

			String pageSql = sql + " limit " + startNum + ", " + pageInfo.getCountOfCurrentPage();
			List<T> result = query(pageSql, params, BeanPropertyRowMapper.newInstance(dtoEntity));

			pageInfo.setPageResults(result);
			pageInfo.setTotalCount(totalCount);
			pageInfo.setTotalPage(totalPage);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return pageInfo;
	}

	/**
	 * list 查询
	 */
	@Override
	public <T> List<T> queryForList(String sql, Class<T> elementType) throws DataAccessException {
		return query(sql, BeanPropertyRowMapper.newInstance(elementType));
	}
	
	/**
	 * 查询COUNT
	 * @param sql
	 * @param object
	 * @return
	 */
	public long queryForCount(String sql, Object... object) {
		Number number = queryForObject(removeOrders(sql), Long.class, object);
		return (number != null ? number.longValue() : 0);
	}

	/**
	 * 去除orderby 子句
	 */
	private String removeOrders(String sql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
}
