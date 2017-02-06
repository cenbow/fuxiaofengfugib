package com.cqliving.framework.common.dao.jdbc.v2;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cqliving.framework.common.dao.jdbc.ExtendJdbcTemplate;

public class MysqlExtendJdbcTemplateV2 extends JdbcTemplate implements ExtendJdbcTemplate {

	static final Logger logger = Logger.getLogger(MysqlExtendJdbcTemplateV2.class);

	static final String PAGE_QUERY_STRING = " %s limit %d, %d ";

	public long queryForCount(String sql, Object... object) {
		if(QueryConditionV2.isOutermostGroupBy(sql)){//如果SQL最后面是group by，则统计会报错，所以后面加一层
			sql = String.format(QueryConditionV2.COUNT_QUERY_STRING, sql);
		}
		Number number = queryForObject(sql, Long.class, object);
		return (number != null ? number.longValue() : 0);
	}

	public long queryForCount(String sql, Map<String, Object> conditions) {
		QueryConditionV2 countQuery = QueryConditionV2.createCountQuery(sql, conditions);
		return queryForCount(countQuery.sql, countQuery.params);
	}

	@Override
	public <T> List<T> queryForList(Class<T> transClass, String sql, Object... object) {
		return super.query(sql, object, BeanPropertyRowMapper.newInstance(transClass));
	}

	@Override
	public <T> List<T> queryForList(Class<T> transClass, String sql, Map<String, Object> conditions) {
		return queryForList(transClass, sql, conditions, null);
	}

	@Override
	public <T> List<T> queryForList(Class<T> transClass, String sql, Map<String, Object> conditions,
			Map<String, Boolean> orders) {
		QueryConditionV2 result = QueryConditionV2.createResultQuery(sql, conditions, orders);
		return queryForList(transClass, result.sql, result.params);
	}

	@Override
	public <T> Page<T> queryForPage(Class<T> transClass, String sql, Pageable pageable, Object... object) {
		return queryForPage(transClass, sql, pageable, null, object);
	}

	@Override
	public <T> Page<T> queryForPage(Class<T> transClass, String sql, Pageable pageable, Map<String, Boolean> orderBys,
			Object... object) {
		long total = Long.MAX_VALUE;
		//如果查询全部数据，则不需要再去统计一次，减少SQL查询，提高性能
		if(pageable.getPageSize() != Integer.MAX_VALUE){
			QueryConditionV2 count = QueryConditionV2.createCountQuery(sql, object);
			total = queryForCount(count.sql, count.params);
			if (total < 1) {
				return new PageImpl<T>(Collections.<T> emptyList(),pageable,total);
			}
		}

		if (orderBys != null && !orderBys.isEmpty()) {
			StringBuilder sqlbuilder = new StringBuilder(sql);
			QueryConditionV2.orderBy(sqlbuilder, orderBys);
			sql = sqlbuilder.toString();
		}
		sql = pageableQuery(sql, pageable);
		List<T> content = this.queryForList(transClass, sql, object);
		return new PageImpl<T>(content, pageable, total);
	}

	@Override
	public <T> Page<T> queryForPage(Class<T> transClass, String sql, Map<String, Object> conditions, Pageable pageable) {
		return queryForPage(transClass, sql, conditions, pageable, null);
	}

	@Override
	public <T> Page<T> queryForPage(Class<T> transClass, String sql, Map<String, Object> conditions, Pageable pageable,
			Map<String, Boolean> orders) {
		long total = Long.MAX_VALUE;
		//add by yuwu 20160825如果查询全部数据，则不需要再去统计一次，减少SQL查询，提高性能
		if(pageable.getPageSize() != Integer.MAX_VALUE){
			QueryConditionV2 count = QueryConditionV2.createCountQuery(sql, conditions);
			total = queryForCount(count.sql, count.params);
			if (total < 1) {
				return new PageImpl<T>(Collections.<T> emptyList(),pageable,total);
			}
		}

		QueryConditionV2 result = QueryConditionV2.createResultQuery(sql, conditions, orders);
		sql = pageableQuery(result.sql, pageable);
		List<T> content = queryForList(transClass, sql, result.params);
		return new PageImpl<T>(content, pageable, total);
	}

	protected String pageableQuery(String sql, Pageable pageable) {
		int page = pageable.getPageNumber();
		int size = pageable.getPageSize();
		//如果查询全部数据，则不需要再去统计一次，减少SQL查询，提高性能
		if(pageable.getPageSize() != Integer.MAX_VALUE){
			return String.format(PAGE_QUERY_STRING, sql, (page * size), size);
		}else{
			return sql;
		}
	}

}
