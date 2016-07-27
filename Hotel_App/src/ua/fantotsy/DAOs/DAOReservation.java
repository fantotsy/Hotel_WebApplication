package ua.fantotsy.DAOs;

import ua.fantotsy.datasource.ConnectionPool;
import ua.fantotsy.entities.Apartment;
import ua.fantotsy.entities.Category;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOReservation implements IDAOReservation {
    @Override
    public int insertNewReservation(Reservation reservation) {
        int result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("add_new_reservation"))) {
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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("get_all_reservations"));
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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("get_all_reservations_for_certain_guest"))) {
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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("remove_reservation"))) {
            ps.setInt(1, reservationId);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return result;
    }
}