package ua.fantotsy.datasource;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class {@code ConnectionPool} is a connection pool, which is used
 * for creating the pool of connections to data base.
 *
 * @author fantotsy
 * @version 1.0
 */
public final class ConnectionPool {
    private static ConnectionPool instance;
    private DataSource dataSource;

    private ConnectionPool() {
        try {
            Context context = new InitialContext();
            Context envContext = (Context) context.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("/jdbc/hoteldb");
        } catch (NamingException e) {
            Logger logger = Logger.getLogger(ConnectionPool.class.getName());
            logger.error(e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(ConnectionPool.class.getName());
            logger.error(e);
        }
        return connection;
    }
}