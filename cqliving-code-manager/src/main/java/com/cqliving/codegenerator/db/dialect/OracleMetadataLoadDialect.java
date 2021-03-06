package com.cqliving.codegenerator.db.dialect;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.cqliving.codegenerator.db.Database;
import com.cqliving.codegenerator.db.metadata.ColumnMetadata;
import com.cqliving.codegenerator.db.metadata.TableMetadata;

/**
 * Oracle 实现
 * 
 * @author zhangpu
 */
public class OracleMetadataLoadDialect implements MetadataLoadDialect {

	private static final Logger logger = LoggerFactory
			.getLogger(OracleMetadataLoadDialect.class);

	/** 列相关元数据SQL */
	protected final static String COLUMN_METADATA_SQL = "Select t1.column_name as name,t1.data_type as type,case when data_type = 'NUMBER' then t1.data_precision else t1.data_length end length,t1.nullable,t2.comments From user_tab_cols t1 left join user_col_comments t2 on t1.column_name=t2.column_name and t1.table_name=t2.table_name where t1.table_name=?";
	/** 表备注元数据SQL */
	protected final static String TABLE_METADATA_SQL = "select COMMENTS from user_tab_comments where table_name = ?";

	protected final static String SELECT_ALL_TABLES = "SELECT TABLE_NAME FROM USER_TABLES ORDER BY TABLE_NAME";

	private JdbcTemplate jdbcTemplate;

	public OracleMetadataLoadDialect(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<String> loadTables() {
		List<String> tables = jdbcTemplate.queryForList(SELECT_ALL_TABLES,
				String.class);
		return tables;
	}

	@Override
	public TableMetadata loadTableMetadata(String tableName, String database) {
		TableMetadata tableMetadata = new TableMetadata();
		tableMetadata.setName(tableName);
		SqlRowSet rs = jdbcTemplate.queryForRowSet(COLUMN_METADATA_SQL,
				tableName);
		List<ColumnMetadata> columnMetadatas = new LinkedList<ColumnMetadata>();
		ColumnMetadata columnMetadata = null;
		while (rs.next()) {
			columnMetadata = new ColumnMetadata();
			String name = rs.getString("NAME");
			if("status".equalsIgnoreCase(name)){//判断表是否包含status字段
				tableMetadata.setIsContainsStatusField(true);
			}
			columnMetadata.setName(name);
			columnMetadata.setLength(rs.getInt("LENGTH"));
			columnMetadata.setDataType(transformDataType(rs.getString("TYPE"),
					columnMetadata.getLength()));
			columnMetadata.setNullable(rs.getString("nullable")
					.equalsIgnoreCase("Y"));
			String comment = rs.getString("comments");
			Map<String, String> options = parseJsonComment(comment);
			columnMetadata.setOptions(options);
			comment = getCanonicalComment(comment);
			columnMetadata.setCommon(StringUtils.isBlank(comment) ? name
					: comment);
			columnMetadatas.add(columnMetadata);
		}
		tableMetadata.setColumnMetadatas(columnMetadatas);
		String tableComment = jdbcTemplate.queryForObject(TABLE_METADATA_SQL,
				String.class, tableName);
		tableMetadata.setComment(StringUtils.isBlank(tableComment) ? tableName
				: tableComment);
		logger.debug("Load metadata success--> " + tableName);
		return tableMetadata;
	}

	@Override
	public String getEntityIdDeclare(String tableName) {
		String declare = "@GeneratedValue(generator = \"sequence\")\n"
				+ "	@GenericGenerator(name = \"sequence\", strategy = \"sequence\", parameters = { @Parameter(name = \"sequence\", value = \"SEQ_"
				+ tableName + "\") })";
        declare="@GeneratedValue(strategy = GenerationType.AUTO)";
		return declare;
	}

	private int transformDataType(String xtype, int length) {

		if (xtype.equals("NUMBER")) {
			return length <= 4 ? ColumnMetadata.DATATYPE_INT
					: ColumnMetadata.DATATYPE_LONG;
		} else if (xtype.equals("DATE")) {
			return ColumnMetadata.DATATYPE_DATE;
		} else {
			return ColumnMetadata.DATATYPE_STRING;
		}

	}

	private Map<String, String> parseJsonComment(String comment) {
		try {
			String json = null;
			Matcher m = Pattern.compile("\\{.+\\}").matcher(comment);
			if (m.find()) {
				json = m.group();
			}
			if (StringUtils.isBlank(json)) {
				return null;
			}
			Map<String, String> data = new LinkedHashMap<String, String>();
			json = StringUtils.substring(json, 1, json.length() - 1);
			for (String item : StringUtils.split(json, ",")) {
				String[] fields = StringUtils.split(item, ":");
				data.put(fields[0], fields[1]);
			}
			return data;
		} catch (Exception e) {
			logger.warn("parse property comment to options Map fail. --> "
					+ comment, e);
			return null;
		}
	}

	/**
	 * 解析字段的备注中的定义的可选值，转换为MAP,用于界面开发 字段备注格式： 字段名称 (key:value,key:value...)
	 * 
	 * @return
	 */
	private Map<String, String> parseCommentToOptionMap(String comment) {

		try {
			Matcher m = Pattern.compile("\\(.+\\)").matcher(comment);
			if (m.find()) {
				String data = m.group();
				data = StringUtils.substringAfter(data, "(");
				data = StringUtils.substringBefore(data, ")");
				String[] enties = data.split(",");
				Map<String, String> options = new TreeMap<String, String>();
				if (enties != null && enties.length > 0) {
					for (String entity : enties) {
						options.put(entity.split(":")[0], entity.split(":")[1]);
					}
				}
				return options;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.warn("parse property comment to options Map fail. --> "
					+ comment, e);
			return null;
		}
	}

	private String getCanonicalComment(String comment) {
		if (StringUtils.contains(comment, "{")) {
			comment = StringUtils.trimToEmpty(StringUtils.substringBefore(
					comment, "{"));
		}
		if (StringUtils.contains(comment, "(")) {
			comment = StringUtils.trimToEmpty(StringUtils.substringBefore(
					comment, "("));
		}
		return comment;
	}

	@Override
	public Database getDatabase() {
		return Database.ORACLE;
	}

}
