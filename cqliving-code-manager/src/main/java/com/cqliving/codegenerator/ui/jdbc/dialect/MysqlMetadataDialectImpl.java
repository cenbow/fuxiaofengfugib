package com.cqliving.codegenerator.ui.jdbc.dialect;

/**
 * Created by Administrator on 2015/5/19.
 */
public class MysqlMetadataDialectImpl implements MetadataDialect {

    private String database;

    public MysqlMetadataDialectImpl(String dbUrl){
        database = dbUrl.substring(dbUrl.lastIndexOf("/")+1, dbUrl.indexOf("?"));
    }

    @Override
    public String tablesSql() {
        return "select table_name, table_comment from information_schema.tables where table_schema='"+database+"' order by table_name;";
    }

    @Override
    public String colSql(String tableName) {
        return "select COLUMN_NAME as name, COLUMN_COMMENT AS comments, IS_NULLABLE AS nullable\n" +
                "from information_schema.COLUMNS where table_name='"+tableName+"' and table_schema = '"+database+"';";
    }
}
