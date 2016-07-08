package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 7/8/2016.
 */
public class EntityManagerImplTest {

    @Test
    public void testGetTableNameMethod() {
        Department d = new Department();
        d.setDepartmentName("Administration");
        d.setLocation(1700L);
        d.setId(10L);
        Object res = EntityManagerImpl.insert(d);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

}
