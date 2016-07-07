package ro.teamnet.zth.api.em;

/**
 * Created by user on 7/7/2016.
 */
public class ColumnInfo {

    private String columnName;
    private Class columnType;
    private String dbName;
    private boolean isId;
    private Object value;

    public String getColumnName() {
        return columnName;
    }

    public Class getColumnType() {
        return columnType;
    }

    public String getDbName() {
        return dbName;
    }

    public boolean isId() {
        return isId;
    }

    public Object getValue() {
        return value;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setColumnType(Class columnType) {
        this.columnType = columnType;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setId(boolean id) {
        isId = id;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
