package ua.fantotsy.DAOs;

import ua.fantotsy.datasource.ConnectionPool;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOGuest implements IDAOGuest {
    @Override
    public Boolean containsCertainLogin(String login) {
        Boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("get_certain_login"))) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            result = rs.next();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return result;
    }

    @Override
    public Boolean containsCertainGuest(String login, String password) {
        Boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("get_certain_login_and_password"))) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            result = rs.next();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return result;
    }

    @Override
    public int insertNewGuest(Guest guest) {
        int result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("add_new_guest"))) {
            ps.setString(1, guest.getName());
            ps.setString(2, guest.getLastName());
            ps.setString(3, "+380" + guest.getPhoneNumber());
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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("get_certain_guest"))) {
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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("get_all_guests"));
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