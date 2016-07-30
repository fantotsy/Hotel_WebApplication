package ua.fantotsy.datasource;

import ua.fantotsy.DAOs.*;

/**
 * Class {@code DAOFactory} has special static methods, which
 * return specific DAO interfaces.
 *
 * @author fantotsy
 * @version 1.0
 */
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