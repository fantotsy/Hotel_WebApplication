package ua.fantotsy.datasource;

import ua.fantotsy.DAOs.*;

public class DAOFactory {
    public static IDAOApartment getDAOApartment() {
        return new DAOApartment();
    }

    public static IDAOCategory getDAOCategory() {
        return new DAOCategory();
    }

    public static IDAOGuest getDAOGuest() {
        return new DAOGuest();
    }

    public static IDAOReservation getDAOReservation() {
        return new DAOReservation();
    }
}