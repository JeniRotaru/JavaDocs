package ro.teamnet.zth.api.database;

import org.junit.Test;
import ro.teamnet.zth.api.em.EntityUtils;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by user on 7/8/2016.
 */
public class DBManagerTest {

    @Test
    public void testGetConnectionMethod() {
        Connection conn = DBManager.getConnection();
        assertNotEquals(null, conn);
    }

    @Test
    public void testCheckConnectionMethod() {
        Connection conn = DBManager.getConnection();
        boolean res = DBManager.checkConnection(conn);
        assertEquals(true, res);
    }

}
