package ro.teamnet.zth.api.database;

import java.sql.*;


public final class DBManager {

    private static final String CONNECTION_STRING =
//        "jdbc:mysql://" + DBProperties.IP + "/" + DBProperties.SCHEMA;
        "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":" + DBProperties.SCHEMA;

    private static final String CONNECTION_CHECK_QUERY = "SELECT 1 FROM DUAL";

    private static boolean isDriverRegistered = false;

    /** Execution of default constructor is not allowed */
    private DBManager() {
        throw new UnsupportedOperationException();
    }

    private static void registerDriver() throws ClassNotFoundException {
        if(!isDriverRegistered) {
            Class.forName(DBProperties.DRIVER_CLASS);
            isDriverRegistered = true;
        }
    }

    /**
     * Create a new connection
     * @return - A new Connection
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        registerDriver();

        return DriverManager.getConnection(
            CONNECTION_STRING,
            DBProperties.USER,
            DBProperties.PASS);
    }

    /**
     * Check if a connection is active
     * @param connection - A connection
     * @return - true if the connection is active
     */
    public static boolean checkConnection(Connection connection) {
        boolean result = false;

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(CONNECTION_CHECK_QUERY);
            if(rs.next()) {
                //get first result from query
                result = rs.getLong(1)==1l;
            }
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
