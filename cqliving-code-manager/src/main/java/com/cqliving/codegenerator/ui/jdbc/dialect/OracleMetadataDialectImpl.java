package com.cqliving.codegenerator.ui.jdbc.dialect;

/**
 * Created by Administrator on 2015/5/19.
 */
public class OracleMetadataDialectImpl implements MetadataDialect {
    @Override
    public String tablesSql() {
        return "select table_name, comments as table_comment from user_tab_comments";
    }

    @Override
    public String colSql(String tableName) {
        return "select tc.column_name as name, comments, nullable "+
                "from user_tab_columns tc, user_col_comments cc "+
                "where cc.table_name = tc.table_name and tc.column_name = cc.column_name and tc.table_name='"+tableName+"'";
    }
}
