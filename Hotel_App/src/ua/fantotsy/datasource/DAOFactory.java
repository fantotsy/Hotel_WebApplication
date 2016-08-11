package ua.fantotsy.datasource;

import ua.fantotsy.DAOs.*;

/**
 * Class {@code DaoFactory} has special static methods, which
 * return specific DAO interfaces.
 *
 * @author fantotsy
 * @version 1.0
 */
public class DaoFactory {

    public static IApartmentDao getDAOApartment() {
        return new ApartmentDao();
    }

    public static ICategoryDao getDAOCategory() {
        return new CategoryDao();
    }

    public static IGuestDao getDAOGuest() {
        return new GuestDao();
    }

    public static IReservationDao getDAOReservation() {
        return new ReservationDao();
    }
}