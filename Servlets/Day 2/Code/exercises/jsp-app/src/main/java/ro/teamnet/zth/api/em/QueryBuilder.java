package ro.teamnet.zth.api.em;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private QueryType queryType;
    private List<Condition> conditions;

    /**
     * Create QueryBuilder object
     */
    public QueryBuilder() {

    }

    public QueryBuilder addCondition(Condition condition) {
        if (this.conditions == null){
            this.conditions = new ArrayList<>();
        }
        this.conditions.add(condition);
        return this;
    }

    /**
     * Set the table name for query
     * @param tableName - table name
     */
    public QueryBuilder setTableName(Object tableName) {
        this.tableName = tableName;
        return this;
    }

    /**
     *
     * @param queryColumns
     */
    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns) {
        if (this.queryColumns == null){
            this.queryColumns = new ArrayList<>();
        }
        this.queryColumns.addAll(queryColumns);
        return this;
    }


    /**
     *
     * @param queryType
     */
    public QueryBuilder setQueryType(QueryType queryType) {
        this.queryType = queryType;
        return this;
    }

    /**
     * Create a SQL query
     * @return - a valid SQL query
     */
    public String createQuery() {
        if (QueryType.SELECT.equals(this.queryType)){
            return createSelectQuery();
        } else if (QueryType.INSERT.equals(this.queryType)) {
            return createInsertQuery();
        } else if (QueryType.UPDATE.equals(this.queryType)) {
            return createUpdateQuery();
        } else if (QueryType.DELETE.equals(this.queryType)) {
            return createDeleteQuery();
        }
        return null;
    }

    private String createSelectQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        boolean isFirst = true;
        for(ColumnInfo columnInfo : queryColumns) {
            if(!isFirst) {
                sql.append(",");
            }
            sql.append(tableName + "." + columnInfo.getDbName());
            isFirst = false;
        }
        sql.append(" from " + tableName);

        boolean whereAdded = false;
        if(conditions != null && !conditions.isEmpty()) {
            for(Condition condition : conditions) {
                sql.append(whereAdded ? " and" : " where ").append(condition.getColumnName()).append("=")
                    .append(getValueForQuery(condition.getValue()));
                whereAdded = true;
            }
        }
        return sql.toString();
    }

    private String createDeleteQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ").append(tableName);
        boolean whereAdded = false;
        if (conditions != null  && !conditions.isEmpty()){
            for (Condition condition : conditions) {
                sql.append(whereAdded ? " and" : " where ").append(condition.getColumnName()).append("=").append(getValueForQuery(condition.getValue()));
                whereAdded = true;
            }
        }
        return sql.toString();
    }

    private String createUpdateQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(tableName).append(" set ");
        boolean first = true;
        for (ColumnInfo column : queryColumns) {
            if (!column.isId()) {
                if (!first) {
                    sql.append(",");
                } else {
                    first = false;
                }
                sql.append(column.getDbName()).append("=").append(getValueForQuery(column.getValue()));
            }
        }

        boolean whereAdded = false;
        if (conditions != null  && !conditions.isEmpty()){
            for (Condition condition : conditions) {
                sql.append(whereAdded ? " and" : " where ").append(condition.getColumnName()).append("=").append(getValueForQuery(condition.getValue()));
                whereAdded = true;
            }
        }
        return sql.toString();
    }

    private String createInsertQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(tableName).append(" (");
        StringBuilder sqlValues = new StringBuilder(" values (");
        boolean first = true;
        for (ColumnInfo columnInfo : queryColumns) {
            if (columnInfo.isId()) {
                continue;
            }
            if (!first) {
                sql.append(",");
                sqlValues.append(",");
            } else {
                first = false;
            }
            sql.append(columnInfo.getDbName());
            sqlValues.append(getValueForQuery(columnInfo.getValue()));
        }

        sql.append(") ");
        sqlValues.append(")");
        sql.append(sqlValues);

        return sql.toString();
    }

    private String getValueForQuery(Object value) {
        if (value == null){
            return null;
        }
        if (value instanceof String){
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }
}
