package ua.fantotsy.datasource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionSource {

    private Context initContext;
    private DataSource ds;
    private static ConnectionSource instance = new ConnectionSource();
    private final Logger LOGGER = LogManager.getLogger(ConnectionSource.class.getName());

    private ConnectionSource() {
        try {
            initContext = new InitialContext();
            ds = (DataSource) initContext.lookup("java:comp/env/jdbc/hoteldb");
        } catch (NamingException e) {
            LOGGER.error(e);
        }
    }

    public static ConnectionSource getInstance() {
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return connection;
    }
}