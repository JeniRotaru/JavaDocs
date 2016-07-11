package ro.teamnet.zth.api.em;

import java.util.List;

/**
 * Created by user on 7/8/2016.
 */
public interface EntityManager {

    Long getNextIdVal(String tableName, String columnIdName);
    <T> T findById(Class<T> entityClass, Long id);
    <T> Object insert(T entity);
    <T> List<T> findAll(Class<T> entityClass);

}
