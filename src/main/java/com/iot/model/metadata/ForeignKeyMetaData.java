package com.iot.model.metadata;

public class ForeignKeyMetaData {
    String pkColunmName;
    String pkTableName;
    String fkColumnName;

    public String getPkColunmName() {
        return pkColunmName;
    }

    public void setPkColunmName(String pkColunmName) {
        this.pkColunmName = pkColunmName;
    }

    public String getPkTableName() {
        return pkTableName;
    }

    public void setPkTableName(String pkTableName) {
        this.pkTableName = pkTableName;
    }

    public String getFkColumnName() {
        return fkColumnName;
    }

    public void setFkColumnName(String fkColumnName) {
        this.fkColumnName = fkColumnName;
    }

    @Override
    public String toString() {
        return "FK_COLUMN: " + fkColumnName + ",  PK_TABLE: " + pkTableName + ",  PK_COLUMN_NAME: " + pkColunmName;
    }
}
