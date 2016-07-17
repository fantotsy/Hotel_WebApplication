package ua.fantotsy.properties;

import java.util.ResourceBundle;

public final class Config {
    private static Config instance;
    private ResourceBundle rb;
    private static final String BUNDLE_NAME = "ua.fantotsy.properties.config";
    public static final String START_PAGE = "START_PAGE";
    public static final String REGISTRATION_PAGE = "REGISTRATION_PAGE";
//    public static final String MAIN_ADMIN = "MAIN_ADMIN";
//    public static final String MAIN_USER = "MAIN_USER";
//    public static final String ADMIN_DELETE_INSERT = "ADMIN_DELETE_INSERT";
//    public static final String ADMIN_RESERVATIONS = "ADMIN_RESERVATIONS";
//    public static final String ADMIN_GUESTS = "ADMIN_GUESTS";


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