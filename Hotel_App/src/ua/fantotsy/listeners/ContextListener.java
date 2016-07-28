package ua.fantotsy.listeners;

import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.File;

/**
 * This class implements the ServletContextListener interface.
 *
 * @author fantotsy
 * @version 1.0
 */

@WebListener("application context listener")
public class ContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet specification
    public ContextListener() {
    }

    /**
     * In this method, we read location of the log4j.xml file
     * and construct a complete, absolute path of this file
     * which is then passed to the static configure() method
     * of the DOMConfigurator class. That’s way log4j is
     * initialized with the given properties file.
     *
     * @param event instance of {@link ServletContextEvent}.
     */
    public void contextInitialized(ServletContextEvent event) {
        // initialize log4j here
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4jConfigLocaltion");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

        DOMConfigurator.configure(fullPath);
    }

    public void contextDestroyed(ServletContextEvent event) {

    }

    public void sessionCreated(HttpSessionEvent event) {

    }

    public void sessionDestroyed(HttpSessionEvent event) {

    }

    public void attributeAdded(HttpSessionBindingEvent event) {

    }

    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    public void attributeReplaced(HttpSessionBindingEvent event) {

    }
}