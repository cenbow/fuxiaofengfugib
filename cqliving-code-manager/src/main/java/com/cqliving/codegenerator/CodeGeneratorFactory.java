package com.cqliving.codegenerator;

public interface CodeGeneratorFactory {

	void generateTable(String database, String tableName);

	void generateTables(String database, String... tableNames);

}
