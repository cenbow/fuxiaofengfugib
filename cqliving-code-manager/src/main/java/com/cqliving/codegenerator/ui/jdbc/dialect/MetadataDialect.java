package com.cqliving.codegenerator.ui.jdbc.dialect;

/**
 * Created by Administrator on 2015/5/19.
 */
public interface MetadataDialect {

    String tablesSql();

    String colSql(String tableName);
}
