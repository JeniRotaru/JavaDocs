package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EntityUtils {

    public static final String EMPTY_STRING = "";

    private EntityUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param entity
     * @return
     */
    public static String getTableName(Class entity) {
        Table tableAnnotation = (Table) entity.getAnnotation(Table.class);
        return EMPTY_STRING.equals(tableAnnotation.name()) ? entity.getClass().getSimpleName() : tableAnnotation.name();
    }

    /**
     *
     * @param entity
     * @return
     */
    public static List<ColumnInfo> getColumns(Class entity, boolean withJoins) {
        List<ColumnInfo> columns = new ArrayList<>();
        Field[] fields = entity.getDeclaredFields();
        for(Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setColumnName(field.getName());
            columnInfo.setColumnType(field.getType());
            if(column != null) {
                columnInfo.setDbName(column.name());
            } else {
                Id id = field.getAnnotation(Id.class);
                columnInfo.setDbName(id.name());
                columnInfo.setId(true);
            }
            if(withJoins) {
               //ToDo
            }
            columns.add(columnInfo);
        }
        return columns;
    }

    public static List<ColumnInfo> getColumns(Class entity) {
        return getColumns(entity, true);
    }

    public static Object castFromSqlType(Object value, Class wantedType) {
        if(value != null) {
            if(value instanceof BigDecimal) {
                BigDecimal bdValue = (BigDecimal) value;
                return wantedType.equals(Integer.class) ? bdValue.intValue() :
                    wantedType.equals(Long.class) ? bdValue.longValue() :
                        wantedType.equals(Float.class) ? bdValue.floatValue() :
                            wantedType.equals(Double.class) ? bdValue.doubleValue() : value;
            } else {
                return value;
            }
        }
        return null;
    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        List<Field> fields = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field declaredField : declaredFields) {
            if(declaredField.getAnnotation(annotation) != null) {
                fields.add(declaredField);
            }
        }
        return fields;
    }

    public static Object getSqlValue(Object object) throws IllegalAccessException {
        if(object == null) {
            return null;
        }
        if(object.getClass().getAnnotation(Table.class) != null) {
            Field idField = getFieldsByAnnotations(object.getClass(), Id.class).get(0);
            idField.setAccessible(true);
            return idField.get(object);
        } else {
            return object;
        }
    }
}
