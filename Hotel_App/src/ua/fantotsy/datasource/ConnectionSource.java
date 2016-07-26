package ua.fantotsy.datasource;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionSource {

    private InitialContext initContext;
    private DataSource ds;
    private static ConnectionSource instance = new ConnectionSource();
    private Logger logger = Logger.getLogger(ConnectionSource.class.getName());

    private ConnectionSource() {
        DOMConfigurator.configure("log4j.xml");

        try {
            initContext = new InitialContext();
            ds = (DataSource) initContext.lookup("java:comp/env/jdbc/hoteldb");
        } catch (NamingException e) {
            logger.error(e);
        }
    }

    public static ConnectionSource getInstance() {
        return instance;
    }

    public Connection getConnection() {
        logger.error("Hello, error!");

        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            logger.error(e);
        }
        return connection;
    }
}