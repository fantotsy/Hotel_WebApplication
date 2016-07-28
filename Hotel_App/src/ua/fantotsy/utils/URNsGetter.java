package ua.fantotsy.utils;

import java.util.ResourceBundle;

/**
 * This class consists of logic, which gets specific
 * Uniform Resource Names (URNs) by their names.
 *
 * @author fantotsy
 * @version 1.0
 */

public final class URNsGetter {
    private ResourceBundle resourceBundle;
    private static URNsGetter instance;

    public static final String START_PAGE = "START_PAGE";
    public static final String REGISTRATION_PAGE = "REGISTRATION_PAGE";
    public static final String MAIN_GUEST_PAGE = "MAIN_GUEST_PAGE";
    public static final String MAIN_ADMIN_PAGE = "MAIN_ADMIN_PAGE";
    public static final String MAIN_ADMIN_APARTMENTS_PAGE = "MAIN_ADMIN_APARTMENTS_PAGE";
    public static final String MAIN_ADMIN_RESERVATIONS_PAGE = "MAIN_ADMIN_RESERVATIONS_PAGE";
    public static final String MAIN_ADMIN_GUESTS_PAGE = "MAIN_ADMIN_GUESTS_PAGE";
    public static final String MAIN_GUEST_BOOKING_PAGE = "MAIN_GUEST_BOOKING_PAGE";
    public static final String ERROR_PAGE = "ERROR_PAGE";
    public static final String ADMIN_CSS = "ADMIN_CSS";
    public static final String ERROR_CSS = "ERROR_CSS";
    public static final String GUEST_CSS = "GUEST_CSS";
    public static final String HEADER_CSS = "HEADER_CSS";
    public static final String INDEX_CSS = "INDEX_CSS";
    public static final String REGISTRATION_CSS = "REGISTRATION_CSS";
    public static final String ADMIN_ICON_IMG = "ADMIN_ICON_IMG";
    public static final String ENGLISH_LANGUAGE_IMG = "ENGLISH_LANGUAGE_IMG";
    public static final String SAD_CAT_ERROR_IMG = "SAD_CAT_ERROR_IMG";
    public static final String UKRAINIAN_LANGUAGE_IMG = "UKRAINIAN_LANGUAGE_IMG";
    public static final String USER_ICON_IMG = "USER_ICON_IMG";
    public static final String FAVICON_ICO = "FAVICON_ICO";

    public static URNsGetter getInstance() {
        if (instance == null) {
            instance = new URNsGetter();
            instance.resourceBundle = ResourceBundle.getBundle("ua.fantotsy.properties.URN.URNs");
        }
        return instance;
    }

    public String getURN(String key) {
        return (String) resourceBundle.getObject(key);
    }
}