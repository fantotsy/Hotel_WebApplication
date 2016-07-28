package ua.fantotsy.datasource;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private Logger logger = Logger.getLogger(ConnectionPool.class.getName());
    private static ConnectionPool instance = new ConnectionPool();
    private Context initialContext;
    private DataSource dataSource;

    private ConnectionPool() {
        try {
            initialContext = new InitialContext();
            Context webContext = (Context)initialContext.lookup("java:/comp/env");
            dataSource = (DataSource) webContext.lookup("/jdbc/hoteldb");
        } catch (NamingException e) {
            logger.error(e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e);
        }
        return connection;
    }
}