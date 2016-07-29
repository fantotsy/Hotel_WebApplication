package ua.fantotsy.DAOs;

import org.apache.log4j.Logger;
import ua.fantotsy.datasource.ConnectionPool;
import ua.fantotsy.utils.SQLQueriesGetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides basic implementation of CRUD operations with the help of JDBC.
 * All operations in this class are connected with 'apartments' table.
 *
 * @author fantotsy
 * @version 1.0
 */

public class DAOApartment implements IDAOApartment {
    private Logger logger = Logger.getLogger(DAOApartment.class.getName());

    @Override
    public Map<Integer, Integer> getNumbersOfApartmentsGroupedByCategories() {
        Map<Integer, Integer> quantityOfApartmentsGroupedByCategories = new HashMap<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.GET_NUMBERS_OF_APARTMENTS_GROUPED_BY_CATEGORIES));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                quantityOfApartmentsGroupedByCategories.put(rs.getInt("category_id"), rs.getInt("number_of_apartments"));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return quantityOfApartmentsGroupedByCategories;
    }

    @Override
    public int addApartment(int apartmentNumber, int category) {
        int result = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.ADD_APARTMENT))) {
            ps.setInt(1, apartmentNumber);
            ps.setInt(2, category);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public int removeApartment(int apartmentNumber) {
        int result = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.REMOVE_APARTMENT))) {
            ps.setInt(1, apartmentNumber);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getAvailableApartments(String arrival, String departure, List<String> types, List<String> capacities) {
        Map<Integer, Integer> result = new HashMap<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.GET_AVAILABLE_APARTMENTS))) {
            ps.setString(1, arrival);
            ps.setString(2, arrival);
            ps.setString(3, departure);
            ps.setString(4, departure);
            ResultSet rs = null;
            for (String type : types) {
                for (String capacity : capacities) {
                    ps.setString(5, type);
                    ps.setString(6, capacity);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        int apartment = rs.getInt("apartment_id");
                        int category = rs.getInt("category_id");
                        result.put(apartment, category);
                    }
                }
            }
            rs.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getAllApartmentNumbers() {
        Map<Integer, Integer> listOfApartments = new HashMap<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.GET_ALL_APARTMENT_NUMBERS));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listOfApartments.put(rs.getInt("apartment_id"), rs.getInt("category_id"));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return listOfApartments;
    }
}