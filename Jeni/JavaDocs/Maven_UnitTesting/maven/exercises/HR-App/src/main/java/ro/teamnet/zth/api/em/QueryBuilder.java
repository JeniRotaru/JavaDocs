package ro.teamnet.zth.api.em;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  ->Query = reprezinta o "cerere" de informatii catre o baza de date;
 *  ->Aceasta se poate realiza prin mai multe metode(exp.:selectarea parametrilor dintr-un meniu);
 */

public class QueryBuilder {

    private Object tableName;
    private List<ColumnInfo> queryColumns = new ArrayList<ColumnInfo>();
    private QueryType queryType;
    private List<Condition> conditions = new ArrayList<Condition>();

    /*
     * Returneaza valoare pentru un query;
     */
    public String getValueForQuery(Object value) {

        if (value.getClass().equals(String.class)) {
            return "'"+value+"'";
        }
        if (value.getClass().equals(Date.class)) {
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return "TO_DATE('"+dateFormat.format((Date)value)+"','mm-dd-YYYY'";
        }
        return "";
    }

    /*
     * Seteaza conditii pentru un query;
     */
    public QueryBuilder addCondition(Condition condition) {
        this.conditions.add(condition);
        return this;
    }

    /*
     * Seteaza numele tabelei pentru un query;
     */
    public QueryBuilder setTableName(Object tableName) {
        this.tableName = tableName;
        return this;
    }

    /*
     * Seteaza toate coloanele pentru un query;
     */
    public  QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns) {
        this.queryColumns.addAll(queryColumns);
        return this;
    }

    /*
     * Seteaza tipul pentru un query;
     */
    public QueryBuilder setQueryType(QueryType queryType) {
        this.queryType = queryType;
        return this;
    }

    /*
     * Creaza un query de tip SELECT;
     * Exp.: SELECT [nume_coloana1, nume_coloana2, ...]
     *       FROM [nume_tabela]
     *       WHERE [conditie1=valoare_conditie1 AND conditie2=valoare_conditie2 AND ...]; => Optionala!!!
     */
    private String createSelectQuery() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT ");
        /* Se itereaza prin lista de coloane alese pentru query
           si se obtine numele coloanei/coloanelor
         */
        int sizeQueryColumns = queryColumns.size();
        for (ColumnInfo column : this.queryColumns) {
            sb.append(column.getColumnName());
            sizeQueryColumns--;
            if (sizeQueryColumns != 0) {
                sb.append(", ");
            }
        }

        sb.append(" FROM ");
        /* Se obtine numele tabelei */
        sb.append(this.tableName);

        /* Daca exista conditii pentru clauza WHERE */
        int sizeConditions = conditions.size();
        if (sizeConditions != 0) {
            sb.append(" WHERE ");
            for (Condition condition : this.conditions) {
                sb.append(condition.getColumnName());
                sb.append("=");
                sb.append(getValueForQuery(condition.getValue()));
                sizeConditions--;
                if (sizeConditions != 0) {
                    sb.append(" AND");
                }
            }
            sb.append(";");
        }
        else { /* NU exista conditii pentru clauza WHERE */
            sb.append(";");
        }

        return sb.toString();
    }

    /*
     * Creaza un query de tip DELETE;
     * Exp.: DELETE [nume_coloana1, nume_coloana2, ...]
     *       FROM [nume_tabela];
     */
    private String createDeleteQuery() {
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE ");
        int sizeQueryColumns = queryColumns.size();
        for (ColumnInfo column : this.queryColumns) {
            sb.append(column.getColumnName());
            sizeQueryColumns--;
            if (sizeQueryColumns != 0)
                sb.append(", ");
        }

        sb.append(" FROM ");
        sb.append(this.tableName);
        sb.append(";");

        return sb.toString();
    }

    /*
     * Creaza un query de tip UPDATE;
     * Exp.: UPDATE [nume_tabela]
     *       SET [nume_coloana1, nume_coloana2, ...]
     *       WHERE [conditie1=valoare_conditie1 AND conditie2=valoare_conditie2 AND ...];
     */
    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder();

        sb.append("UPDATE ");
        sb.append(this.tableName);

        sb.append(" SET ");
        int sizeQueryColumns = queryColumns.size();
        for (ColumnInfo column : this.queryColumns) {
            sb.append(column.getColumnName());
            sizeQueryColumns--;
            if (sizeQueryColumns != 0)
                sb.append(", ");
        }

        int sizeConditions = conditions.size();
        if (sizeConditions != 0) {
            sb.append(" WHERE ");
            for (Condition condition : this.conditions) {
                sb.append(condition.getColumnName());
                sb.append("=");
                sb.append(getValueForQuery(condition.getValue()));
                sizeConditions--;
                if (sizeConditions != 0) {
                    sb.append(" AND");
                }
            }
            sb.append(";");
        }

        return sb.toString();
    }

    /*
     * Creaza un query de tip INSERT;
     * Exp.: INSERT INTO [nume_tabela] (nume_coloana1, nume_coloana2, ...)
     *       VALUES ('valoare1', 'valoare2', ...);
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO ");
        sb.append(this.tableName);
        sb.append("(");
        int sizeQueryColumns = queryColumns.size();
        for (ColumnInfo column : this.queryColumns) {
            sb.append(column.getColumnName());
            sizeQueryColumns--;
            if (sizeQueryColumns != 0)
                sb.append(", ");
        }
        sb.append(") VALUES (");
        int sizeQueryColumns1 = queryColumns.size();
        for (ColumnInfo column : this.queryColumns) {
            sb.append(getValueForQuery(column.getValue()));
            sizeQueryColumns1--;
            if (sizeQueryColumns1 != 0)
                sb.append(", ");
        }
        sb.append(");");

        return sb.toString();
    }

    public String createQuery() {
        if (this.queryType.equals("SELECT"))
            return this.createSelectQuery();
        if (this.queryType.equals("DELETE"))
            return this.createDeleteQuery();
        if (this.queryType.equals("INSERT"))
            return this.createInsertQuery();
        if (this.queryType.equals("UPDATE"))
            return this.createUpdateQuery();
        return null;
    }
}
