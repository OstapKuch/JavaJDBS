package com.iot.model.metadata;

public class ColumnMetaData {
    private String columnName;
    private String dataType;
    private String columnSize;
    private String decimalDigits;
    private boolean isNullable;
    private boolean isAutoIncrement;
    private boolean isPrimaryKey;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(String columnSize) {
        this.columnSize = columnSize;
    }

    public String getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(String decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    @Override
    public String toString() {
        String str = String.format("%-15s  %-12s  %-8s  %s  %s",
                columnName,
                dataType + "(" + columnSize + ")",
                (isNullable ? "NULL" : "NOT NULL"),
                (isPrimaryKey ? "PK" : ""),
                (isAutoIncrement ? "  AutoIncrement" : ""));
        return str;
    }
}
