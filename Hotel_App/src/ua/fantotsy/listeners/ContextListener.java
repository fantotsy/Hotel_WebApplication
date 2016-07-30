package ua.fantotsy.listeners;

import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Element;

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
 * Class {@code ContextListener} implements several interfaces:
 * {@link ServletContextListener}, {@link HttpSessionListener}
 * and {@link HttpSessionAttributeListener}.
 *
 * @author fantotsy
 * @version 1.0
 */
@WebListener("application context listener")
public class ContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet specification.
    public ContextListener() {
    }

    /**
     * Reads location of the log4j.xml file and constructs a complete,
     * absolute path of this file which is then passed to the static
     * {@link DOMConfigurator#configure(Element)} method. That is way
     * log4j is initialized with the given xml file.
     *
     * @param event instance of {@link ServletContextEvent}.
     */
    public void contextInitialized(ServletContextEvent event) {
        // Initialize log4j here.
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