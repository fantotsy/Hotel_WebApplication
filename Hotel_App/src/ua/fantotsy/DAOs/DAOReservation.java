package ua.fantotsy.DAOs;

import ua.fantotsy.datasource.ConnectionSource;
import ua.fantotsy.entities.Apartment;
import ua.fantotsy.entities.Category;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.entities.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOReservation implements IDAOReservation {
    private static final String GET_AVAILABLE_APARTMENTS =
            "SELECT COUNT(apartment_id) AS number_of_apartments, category_id " +
                    "FROM apartments WHERE apartment_id IN " +
                    "(SELECT apartment_id FROM apartments WHERE apartment_id NOT IN " +
                    "(SELECT apartment_id FROM reservations WHERE(arrival < ? AND departure > ?) " +
                    "OR (arrival < ? AND departure > ?))) GROUP BY category_id";
    private static final String INSERT_NEW_RESERVATION =
            "INSERT INTO reservations (guest_id, apartment_id, arrival, departure, total_price) " +
                    "VALUES (?, ?, ?, ?, (SELECT total_price FROM (SELECT (SELECT TIMESTAMPDIFF(DAY, ?, ?) " +
                    "AS duration) * (SELECT price FROM categories JOIN apartments " +
                    "ON categories.category_id = apartments.category_id WHERE apartment_id=?) AS total_price)T))";
    private static final String GET_ALL_RESERVATIONS = "SELECT apartment_id, arrival, departure, " +
            "total_price, name, last_name, phone_number, email, login FROM reservations " +
            "JOIN guests ON guests.guest_id = reservations.guest_id";
    private static final String GET_CERTAIN_RESERVATIONS = "SELECT reservation_id, reservations.apartment_id, arrival, " +
            "departure, total_price, T.type, T.number_of_beds FROM reservations JOIN (SELECT categories.category_id, type, " +
            "number_of_beds, apartment_id FROM categories JOIN apartments ON categories.category_id = apartments.category_id)T " +
            "ON reservations.apartment_id = T.apartment_id WHERE guest_id = ?";
    private static final String DELETE_CERTAIN_RESERVATION = "DELETE FROM reservations WHERE reservation_id = ?";

    @Override
    public Map<Integer, Integer> getNumbersOfApartmentsOnDate(String arrival, String departure) {
        Map<Integer, Integer> numbersOfApartmentsOnDate = new HashMap<>();
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_AVAILABLE_APARTMENTS)) {
            ps.setString(1, arrival);
            ps.setString(2, arrival);
            ps.setString(3, departure);
            ps.setString(4, departure);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                numbersOfApartmentsOnDate.put(rs.getInt("category_id"), rs.getInt("number_of_apartments"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return numbersOfApartmentsOnDate;
    }

    @Override
    public int insertNewReservation(Reservation reservation) {
        int result;
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_NEW_RESERVATION)) {
            ps.setInt(1, reservation.getGuest().getGuestId());
            ps.setInt(2, reservation.getApartment().getApartmentId());
            ps.setString(3, reservation.getArrival());
            ps.setString(4, reservation.getDeparture());
            ps.setString(5, reservation.getArrival());
            ps.setString(6, reservation.getDeparture());
            ps.setInt(7, reservation.getApartment().getApartmentId());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return result;
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> listOfReservations = new ArrayList<>();
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_RESERVATIONS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Guest guest = new Guest(rs.getString("name"), rs.getString("last_name"), rs.getString("login"));
                Apartment apartment = new Apartment(rs.getInt("apartment_id"));
                Reservation reservation = new Reservation(guest, apartment, rs.getString("arrival"), rs.getString("departure"), rs.getInt("total_price"));
                listOfReservations.add(reservation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return listOfReservations;
    }

    @Override
    public List<Reservation> getCertainReservations(Integer guestId) {
        List<Reservation> listOfReservations = new ArrayList<>();
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_CERTAIN_RESERVATIONS)) {
            ps.setInt(1, guestId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getString("type"), rs.getInt("number_of_beds"));
                Apartment apartment = new Apartment(rs.getInt("apartment_id"), category);
                Reservation reservation = new Reservation(rs.getInt("reservation_id"), apartment, rs.getString("arrival"), rs.getString("departure"), rs.getInt("total_price"));
                listOfReservations.add(reservation);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return listOfReservations;
    }

    @Override
    public int deleteCertainReservation(Integer reservationId) {
        int result = 0;
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_CERTAIN_RESERVATION)) {
            ps.setInt(1, reservationId);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return result;
    }
}