package ua.fantotsy.properties;

import java.util.ResourceBundle;

public final class Config {
    private static Config instance;
    private ResourceBundle rb;
    private static final String BUNDLE_NAME = "ua.fantotsy.properties.config";
    public static final String START_PAGE = "START_PAGE";
    public static final String REGISTRATION_PAGE = "REGISTRATION_PAGE";
    public static final String MAIN_GUEST_PAGE = "MAIN_GUEST_PAGE";
    public static final String MAIN_ADMIN_PAGE = "MAIN_ADMIN_PAGE";
    public static final String MAIN_ADMIN_APARTMENTS_PAGE = "MAIN_ADMIN_APARTMENTS_PAGE";
    public static final String MAIN_ADMIN_RESERVATIONS_PAGE = "MAIN_ADMIN_RESERVATIONS_PAGE";
    public static final String MAIN_ADMIN_GUESTS_PAGE = "MAIN_ADMIN_GUESTS_PAGE";
    public static final String MAIN_GUEST_BOOKING_PAGE = "MAIN_GUEST_BOOKING_PAGE";
    public static final String ERROR_PAGE = "ERROR_PAGE";

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
            instance.rb = ResourceBundle.getBundle(BUNDLE_NAME);
        }

        return instance;
    }

    public String getProperty(String key) {
        return (String) rb.getObject(key);
    }
}