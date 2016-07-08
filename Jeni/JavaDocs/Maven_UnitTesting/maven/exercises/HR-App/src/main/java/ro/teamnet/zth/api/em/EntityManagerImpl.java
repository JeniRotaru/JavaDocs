package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by user on 7/8/2016.
 */
public class EntityManagerImpl implements EntityManager {
    @Override
    public int getNextIdVal(String tableName, String columnIdName) { //Catalin
        return 0;
    }

    @Override
    public <T> T findById(Class<T> entityClass, Long id) { //Nicoleta
        return null;
    }

    @Override
    public <T> Object insert(T entity) { //Jeni
        /* Create a connection to DB */;
        Long i = -1L;
        long ii;
        try(Connection conn = DBManager.getConnection()){
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> listColumns = EntityUtils.getColumns(entity.getClass());

            for (ColumnInfo column : listColumns) {
                if (column.isId() == true) {
                    ii = getNextIdVal(tableName, column.getColumnName());
                    i = Long.valueOf(ii);
                    column.setValue(i);
                }
                else {
                    Field field = entity.getClass().getDeclaredField(column.getColumnName());
                    field.isAccessible();
                    column.setValue(field.get(entity));
                }
            }
            /* create a QueryBuilder object  in which you have to set the name of the table, columns, query type */
            QueryBuilder qb = new QueryBuilder();
            qb.setTableName(tableName);
            qb.addQueryColumns(listColumns);
            qb.setQueryType(QueryType.INSERT);
            /* call createQuery() */
            String query = qb.createQuery();
            /* create a Statement object and execute the query */
            try (Statement stmt = conn.createStatement( )){
                stmt.executeQuery(query);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }catch(SQLException | NoSuchFieldException | IllegalAccessException ex){
            ex.printStackTrace();
        }

        return findById((Class<Object>) entity, i);
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) { //Ana
        return null;
    }
}
