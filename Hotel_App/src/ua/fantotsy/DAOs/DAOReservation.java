package ua.fantotsy.DAOs;

import org.apache.log4j.Logger;
import ua.fantotsy.datasource.ConnectionPool;
import ua.fantotsy.entities.Apartment;
import ua.fantotsy.entities.Category;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.utils.SQLQueriesGetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides basic implementation of CRUD operations with the help of JDBC.
 * All operations in this class are connected with 'reservations' table.
 *
 * @author fantotsy
 * @version 1.0
 */

public class DAOReservation implements IDAOReservation {
    private Logger logger = Logger.getLogger(DAOReservation.class.getName());

    @Override
    public int insertNewReservation(Reservation reservation) {
        int result = -1;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.ADD_NEW_RESERVATION))) {
            ps.setInt(1, reservation.getGuest().getGuestId());
            ps.setInt(2, reservation.getApartment().getApartmentId());
            ps.setString(3, reservation.getArrival());
            ps.setString(4, reservation.getDeparture());
            ps.setString(5, reservation.getArrival());
            ps.setString(6, reservation.getDeparture());
            ps.setInt(7, reservation.getApartment().getApartmentId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> listOfReservations = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.GET_ALL_RESERVATIONS));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Guest guest = new Guest(rs.getString("name"), rs.getString("last_name"), rs.getString("login"));
                Apartment apartment = new Apartment(rs.getInt("apartment_id"));
                Reservation reservation = new Reservation(guest, apartment, rs.getString("arrival"), rs.getString("departure"), rs.getInt("total_price"));
                listOfReservations.add(reservation);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return listOfReservations;
    }

    @Override
    public List<Reservation> getCertainReservations(Integer guestId) {
        List<Reservation> listOfReservations = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.GET_ALL_RESERVATIONS_FOR_CERTAIN_GUEST))) {
            ps.setInt(1, guestId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getString("type"), rs.getInt("number_of_beds"));
                Apartment apartment = new Apartment(rs.getInt("apartment_id"), category);
                Reservation reservation = new Reservation(rs.getInt("reservation_id"), apartment, rs.getString("arrival"), rs.getString("departure"), rs.getInt("total_price"));
                listOfReservations.add(reservation);
            }
            rs.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return listOfReservations;
    }

    @Override
    public int deleteCertainReservation(Integer reservationId) {
        int result = -1;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.REMOVE_RESERVATION))) {
            ps.setInt(1, reservationId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }
}