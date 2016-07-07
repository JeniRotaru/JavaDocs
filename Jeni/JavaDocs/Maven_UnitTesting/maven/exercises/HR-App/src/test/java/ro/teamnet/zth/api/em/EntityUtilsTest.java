package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.appl.domain.Department;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 7/7/2016.
 */
public class EntityUtilsTest {

    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

    @Test
    public void testGetColumnsMethod() {
        List<ColumnInfo> cActual = new ArrayList<ColumnInfo>();
        cActual = EntityUtils.getColumns(Department.class);
        assertEquals(3, cActual.size());
    }

    @Test
    public void testcastFromSqlTypeMethod() {
        Department d = new Department();
        d.setDepartmentName("abc");
        Object actual = EntityUtils.castFromSqlType(d.getDepartmentName(), String.class);
        assertEquals(String.class, actual);
    }

    @Test
    public void testgetFieldsByAnnotationsMethod() {
        List<Field> listActual = new ArrayList<Field>();
        listActual = EntityUtils.getFieldsByAnnotations(Department.class, Column.class);
        assertEquals(2, listActual.size());
    }

    @Test
    public void testgetSqlValueMethod() throws IllegalAccessException {
        Department d = new Department();
        d.setDepartmentName("abc");
        Object actual = EntityUtils.getSqlValue(d.getDepartmentName());
        assertEquals("abc", actual);
    }
}
