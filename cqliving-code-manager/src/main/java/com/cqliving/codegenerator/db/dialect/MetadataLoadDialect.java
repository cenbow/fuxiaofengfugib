package com.cqliving.codegenerator.db.dialect;

import java.util.List;

import com.cqliving.codegenerator.db.Database;
import com.cqliving.codegenerator.db.metadata.TableMetadata;

public interface MetadataLoadDialect {

	Database getDatabase();

	TableMetadata loadTableMetadata(String tableName, String database);

	String getEntityIdDeclare(String tableName);

	List<String> loadTables();

}
