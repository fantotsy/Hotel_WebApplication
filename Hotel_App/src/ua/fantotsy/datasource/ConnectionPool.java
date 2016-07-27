package ua.fantotsy.datasource;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static ConnectionPool instance = new ConnectionPool();
    private InitialContext initialContext;
    private DataSource dataSource;
    private Logger logger = Logger.getLogger(ConnectionPool.class.getName());

    private ConnectionPool() {
        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/hoteldb");
        } catch (NamingException ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() {
        logger.error("Errooooorrr!");
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}