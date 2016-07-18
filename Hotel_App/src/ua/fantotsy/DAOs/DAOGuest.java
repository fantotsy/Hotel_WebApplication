package ua.fantotsy.DAOs;

import ua.fantotsy.datasource.ConnectionSource;
import ua.fantotsy.entities.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOGuest implements IDAOGuest {
    private static final String GET_CERTAIN_LOGIN = "SELECT login FROM guests WHERE login=?";
    private static final String GET_CERTAIN_LOGIN_AND_PASSWORD = "SELECT login, password FROM guests WHERE login=? AND password=?";
    private static final String INSERT_NEW_GUEST = "INSERT INTO guests " +
            "(name, last_name, phone_number, email, login, password) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_CERTAIN_GUEST = "SELECT * FROM guests WHERE login=?";
    private static final String GET_ALL_GUESTS = "SELECT name, last_name, phone_number, email, login FROM guests";

    @Override
    public int containsCertainLogin(String login) {
        int result = 0;
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_CERTAIN_LOGIN)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            boolean booleanResult = rs.next();
            if (booleanResult) {
                result = 1;
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return result;
    }

    @Override
    public int containsCertainGuest(String login, String password) {
        int result = 1;
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_CERTAIN_LOGIN_AND_PASSWORD)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            boolean booleanResult = rs.next();
            if (booleanResult) {
                result = 0;
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return result;
    }

    @Override
    public int insertNewGuest(Guest guest) {
        int result;
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_NEW_GUEST)) {
            ps.setString(1, guest.getName());
            ps.setString(2, guest.getLastName());
            ps.setString(3, guest.getPhoneNumber());
            ps.setString(4, guest.getEmail());
            ps.setString(5, guest.getLogin());
            ps.setString(6, guest.getPassword());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return result;
    }

    @Override
    public Guest getCertainGuest(String enteredLogin) {
        Guest guest = null;
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_CERTAIN_GUEST)) {
            ps.setString(1, enteredLogin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                guest = new Guest(rs.getInt("guest_id"), rs.getString("name"), rs.getString("last_name"), rs.getString("phone_number"), rs.getString("email"), rs.getString("login"));
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return guest;
    }

    @Override
    public List<Guest> getAllGuests() {
        List<Guest> listOfGuests = new ArrayList<>();
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_GUESTS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Guest guest = new Guest(rs.getString("name"), rs.getString("last_name"), rs.getString("phone_number"), rs.getString("email"), rs.getString("login"));
                listOfGuests.add(guest);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return listOfGuests;
    }
}