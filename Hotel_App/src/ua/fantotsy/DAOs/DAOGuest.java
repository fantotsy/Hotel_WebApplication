package ua.fantotsy.DAOs;

import org.apache.log4j.Logger;
import ua.fantotsy.datasource.ConnectionPool;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.utils.SQLQueriesGetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides basic implementation of CRUD operations with the help of JDBC.
 * All operations in this class are connected with 'guests' table.
 *
 * @author fantotsy
 * @version 1.0
 */
public class DAOGuest implements IDAOGuest {
    private Logger logger = Logger.getLogger(DAOGuest.class.getName());

    @Override
    public Boolean containsCertainLogin(String login) {
        Boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.GET_CERTAIN_LOGIN))) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            result = rs.next();
            rs.close();
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
        return result;
    }

    @Override
    public Boolean containsCertainGuest(String login, String password) {
        Boolean result = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.GET_CERTAIN_LOGIN_AND_PASSWORD))) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            result = rs.next();
            rs.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public int insertNewGuest(Guest guest) {
        int result = -1;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.ADD_NEW_GUEST))) {
            ps.setString(1, guest.getName());
            ps.setString(2, guest.getLastName());
            ps.setString(3, "+380" + guest.getPhoneNumber());
            ps.setString(4, guest.getEmail());
            ps.setString(5, guest.getLogin());
            ps.setString(6, guest.getPassword());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public Guest getCertainGuest(String enteredLogin) {
        Guest guest = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.GET_CERTAIN_GUEST))) {
            ps.setString(1, enteredLogin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                guest = new Guest(rs.getInt("guest_id"), rs.getString("name"), rs.getString("last_name"), rs.getString("phone_number"), rs.getString("email"), rs.getString("login"));
            }
            rs.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return guest;
    }

    @Override
    public List<Guest> getAllGuests() {
        List<Guest> listOfGuests = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.GET_ALL_GUESTS));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Guest guest = new Guest(rs.getString("name"), rs.getString("last_name"), rs.getString("phone_number"), rs.getString("email"), rs.getString("login"));
                listOfGuests.add(guest);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return listOfGuests;
    }
}