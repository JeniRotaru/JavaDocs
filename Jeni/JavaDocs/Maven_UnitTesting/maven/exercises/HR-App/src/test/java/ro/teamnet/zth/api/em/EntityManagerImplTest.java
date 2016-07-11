package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 7/8/2016.
 */
public class EntityManagerImplTest {

    EntityManagerImpl manager = new EntityManagerImpl();

    @Test
    public void testGetTableNameMethod() {
        Department d = new Department();
        d.setDepartmentName("BlaBla");
        d.setLocation(1000L);
        d.setId(278L);

        Department res = (Department)(manager.insert(d));
        assertEquals(d, res);
    }

}
