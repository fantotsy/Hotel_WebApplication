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
    private static IApartmentDao apartmentDaoInstance = new ApartmentDao();
    private static ICategoryDao categoryDaoInstance = new CategoryDao();
    private static IGuestDao guestDaoInstance = new GuestDao();
    private static IReservationDao reservationDaoInstance = new ReservationDao();

    public static IApartmentDao getDAOApartment() {
        return apartmentDaoInstance;
    }

    public static ICategoryDao getDAOCategory() {
        return categoryDaoInstance;
    }

    public static IGuestDao getDAOGuest() {
        return guestDaoInstance;
    }

    public static IReservationDao getDAOReservation() {
        return reservationDaoInstance;
    }
}