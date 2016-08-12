package ua.fantotsy.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Calendar;

/**
 * Class {@code Utils} is subsidiary and contains methods, which are used
 * in methods from other classes to ease their code.
 *
 * @author fantotsy
 * @version 1.0
 */
public final class Utils {

    /**
     * Returns an encryption of specific string (password) using MD5.
     *
     * @param password password, which has to be encrypted.
     * @return encryption of certain password.
     */
    public static String encryptionMD5(String password) {
        try {
            String encryption = null;
            if (password != null) {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                byte[] array = md.digest(password.getBytes());
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < array.length; ++i) {
                    stringBuffer.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
                }
                encryption = stringBuffer.toString();
            }
            return encryption;
        } catch (java.security.NoSuchAlgorithmException e) {
            Logger logger = Logger.getLogger(Utils.class.getName());
            logger.error(e);
        }
        return null;
    }

    /**
     * Returns today's date and the date one year after today.
     *
     * @return array of two strings, which are today's date and the date one year after today.
     */
    public static String[] getDateLimits() {
        String[] result = new String[2];
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        String dayString;
        String monthString;
        if (Integer.toString(day).length() == 1) {
            dayString = "0" + day;
        } else {
            dayString = "" + day;
        }
        if (Integer.toString(month).length() == 1) {
            monthString = "0" + month;
        } else {
            monthString = "" + month;
        }
        String today = year + "-" + monthString + "-" + dayString;
        String yearLater = (year + 1) + "-" + monthString + "-" + dayString;
        result[0] = today;
        result[1] = yearLater;
        return result;
    }

    /**
     * Sets the JNDI context and data source.
     * {@code setUpClass} is used in JUnit test classes to provide valid
     * {@link javax.sql.DataSource} while getting connection
     * from {@link ua.fantotsy.datasource.ConnectionPool}.
     *
     * @param className the name of specific class.
     */
    public static void setUpClass(String className) throws Exception {
        // Setup the JNDI context and the datasource
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setMaxTotal(32);
            dataSource.setMaxIdle(8);
            dataSource.setMaxWaitMillis(10000);
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            dataSource.setUrl("jdbc:mysql://localhost:3306/hoteldb");
            dataSource.setValidationQuery("SELECT 1");

            // Configure and populate initial context
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
            Context initialContext = new InitialContext();

            initialContext.createSubcontext("java:");
            initialContext.createSubcontext("java:/comp");
            initialContext.createSubcontext("java:/comp/env");
            initialContext.createSubcontext("java:/comp/env/jdbc");

            initialContext.bind("java:/comp/env/jdbc/hoteldb", dataSource);
        } catch (Exception e) {
            Logger logger = Logger.getLogger(className);
            logger.error(e);
        }
    }
}