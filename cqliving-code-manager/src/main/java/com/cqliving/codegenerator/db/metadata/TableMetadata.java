package com.cqliving.codegenerator.db.metadata;

import java.util.LinkedList;
import java.util.List;

public class TableMetadata {

	private String name;
	private String comment;
	private boolean isContainsStatusField = false;//实体是否有status字段，如果有该字段，则会生成逻辑删除方法，及修改状态方法
	/** 列信息 */
	private List<ColumnMetadata> columnMetadatas = new LinkedList<ColumnMetadata>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean getIsContainsStatusField() {
		return isContainsStatusField;
	}
	public void setIsContainsStatusField(boolean isContainsStatusField) {
		this.isContainsStatusField = isContainsStatusField;
	}
	public List<ColumnMetadata> getColumnMetadatas() {
		return columnMetadatas;
	}
	public void setColumnMetadatas(List<ColumnMetadata> columnMetadatas) {
		this.columnMetadatas = columnMetadatas;
	}

    public int indexOfPropertyName(String propertyName){
        for(int j=0; j<columnMetadatas.size(); j++){
            ColumnMetadata columnMetadata = columnMetadatas.get(j);
            if(columnMetadata.getPropertyName().equalsIgnoreCase(propertyName)){
                return j;
            }
        }
        return -1;
    }

    public int indexOfColumnName(String columnName){
        for(int j=0; j<columnMetadatas.size(); j++){
            ColumnMetadata columnMetadata = columnMetadatas.get(j);
            if(columnMetadata.getName().equalsIgnoreCase(columnName)){
                return j;
            }
        }
        return -1;
    }

}
