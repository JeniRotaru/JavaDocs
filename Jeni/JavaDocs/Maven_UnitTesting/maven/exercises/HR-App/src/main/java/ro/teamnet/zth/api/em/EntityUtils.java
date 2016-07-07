package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 7/7/2016.
 */
public class EntityUtils {

    private EntityUtils() throws UnsupportedOperationException{}

    public static String getTableName (Class entity) {
        Table table = (Table)entity.getAnnotation(Table.class);

        if (table == null)
            return entity.getName();
        else
            return table.name();
    }

    public static List<ColumnInfo> getColumns (Class entity) {
        List<ColumnInfo> list = new ArrayList<ColumnInfo>();

        for (Field field : entity.getDeclaredFields()) {
            ColumnInfo c = new ColumnInfo();
            c.setColumnName(field.getName());
            c.setColumnType(field.getType());

            Column column  = (Column)field.getAnnotation(Column.class);
            Id id = (Id)field.getAnnotation(Id.class);
            if (column == null){ //field e atnotat cu @Id;
                c.setDbName(id.name());
                c.setId(true);
            }else{ //field e atnotat cu @Column;
                c.setDbName(column.name());
                c.setId(false);
            }
            list.add(c);
        }
        return list;
    }

    public static Object castFromSqlType(Object value, Class wantedType) {
        if (value.getClass().equals(BigDecimal.class) && wantedType.getClass().equals(Integer.class))
            return wantedType.getClass();
        if (value.getClass().equals(BigDecimal.class) && wantedType.getClass().equals(Long.class))
            return wantedType.getClass();
        if (value.getClass().equals(BigDecimal.class) && wantedType.getClass().equals(Float.class))
            return wantedType.getClass();
        if (value.getClass().equals(BigDecimal.class) && wantedType.getClass().equals(Double.class))
            return wantedType.getClass();
        return value.getClass();
    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        List<Field> list = new ArrayList<Field>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.getAnnotation(annotation) != null){
                list.add(field);
            }
        }

        return list;
    }

    public static Object getSqlValue(Object object) throws IllegalAccessException {

        if (object.getClass().getAnnotation(Table.class) != null) {
            for (Field field : object.getClass().getDeclaredFields()) {
                Id id = (Id) field.getAnnotation(Id.class);
                if (id != null) {
                    field.setAccessible(true);
                    return field.get(object);
                }
            }
        }
        return object;
    }
}
