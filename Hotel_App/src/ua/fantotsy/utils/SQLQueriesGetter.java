package ua.fantotsy.utils;

import java.util.ResourceBundle;

public final class SQLQueriesGetter {
    private ResourceBundle resourceBundle;
    private static SQLQueriesGetter instance;

    public static final String GET_NUMBERS_OF_APARTMENTS_GROUPED_BY_CATEGORIES = "GET_NUMBERS_OF_APARTMENTS_GROUPED_BY_CATEGORIES";
    public static final String ADD_APARTMENT = "ADD_APARTMENT";
    public static final String REMOVE_APARTMENT = "REMOVE_APARTMENT";
    public static final String GET_AVAILABLE_APARTMENTS = "GET_AVAILABLE_APARTMENTS";
    public static final String GET_ALL_APARTMENT_NUMBERS = "GET_ALL_APARTMENT_NUMBERS";
    public static final String GET_ALL_CATEGORIES = "GET_ALL_CATEGORIES";
    public static final String GET_ALL_AVAILABLE_APARTMENTS_FOR_GUEST = "GET_ALL_AVAILABLE_APARTMENTS_FOR_GUEST";
    public static final String GET_ALL_TYPES = "GET_ALL_TYPES";
    public static final String GET_ALL_CAPACITIES = "GET_ALL_CAPACITIES";
    public static final String GET_CERTAIN_LOGIN = "GET_CERTAIN_LOGIN";
    public static final String GET_CERTAIN_LOGIN_AND_PASSWORD = "GET_CERTAIN_LOGIN_AND_PASSWORD";
    public static final String ADD_NEW_GUEST = "ADD_NEW_GUEST";
    public static final String GET_CERTAIN_GUEST = "GET_CERTAIN_GUEST";
    public static final String GET_ALL_GUESTS = "GET_ALL_GUESTS";
    public static final String ADD_NEW_RESERVATION = "ADD_NEW_RESERVATION";
    public static final String GET_ALL_RESERVATIONS = "GET_ALL_RESERVATIONS";
    public static final String GET_ALL_RESERVATIONS_FOR_CERTAIN_GUEST = "GET_ALL_RESERVATIONS_FOR_CERTAIN_GUEST";
    public static final String REMOVE_RESERVATION = "REMOVE_RESERVATION";

    public static SQLQueriesGetter getInstance() {
        if (instance == null) {
            instance = new SQLQueriesGetter();
            instance.resourceBundle = ResourceBundle.getBundle("ua.fantotsy.properties.SQL.SQLs");
        }
        return instance;
    }

    public String getSQLQuery(String key) {
        return (String) resourceBundle.getObject(key);
    }
}