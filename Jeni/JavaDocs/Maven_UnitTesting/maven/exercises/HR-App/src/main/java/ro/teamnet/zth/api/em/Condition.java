package ro.teamnet.zth.api.em;

/**
 * Created by user on 7/7/2016.
 */
public class Condition {

    private String columnName;
    private Object value;

    public String getColumnName() {
        return columnName;
    }

    public Object getValue() {
        return value;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
