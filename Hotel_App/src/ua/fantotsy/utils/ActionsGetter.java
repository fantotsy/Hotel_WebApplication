package ua.fantotsy.utils;

import java.util.ResourceBundle;

/**
 * This class consists of logic, which gets specific actions by their names.
 *
 * @author fantotsy
 * @version 1.0
 */

public final class ActionsGetter {
    private ResourceBundle resourceBundle;
    private static ActionsGetter instance;

    public static final String ROOT = "ROOT";
    public static final String INDEX = "INDEX";
    public static final String REGISTRATION = "REGISTRATION";
    public static final String MAIN = "MAIN";
    public static final String GUEST = "GUEST";
    public static final String ADMIN = "ADMIN";
    public static final String APARTMENTS = "APARTMENTS";
    public static final String RESERVATIONS = "RESERVATIONS";
    public static final String GUESTS = "GUESTS";
    public static final String BOOKING = "BOOKING";
    public static final String ORDER_VALID = "ORDER_VALID";
    public static final String ERROR = "ERROR";

    public static ActionsGetter getInstance() {
        if (instance == null) {
            instance = new ActionsGetter();
            instance.resourceBundle = ResourceBundle.getBundle("ua.fantotsy.properties.actions.actions");
        }
        return instance;
    }

    public String getAction(String key) {
        return (String) resourceBundle.getObject(key);
    }
}