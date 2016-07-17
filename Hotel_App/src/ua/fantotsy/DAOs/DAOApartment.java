package ua.fantotsy.DAOs;

import ua.fantotsy.datasource.ConnectionSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOApartment implements IDAOApartment {
    private static final String GET_NUMBERS_OF_APARTMENTS_GROUPED_BY_CATEGORIES
            = "SELECT COUNT(apartment_id) AS number_of_apartments, category_id  FROM apartments GROUP BY category_id";
    private static final String ADD_APARTMENT = "INSERT INTO apartments (apartment_id, category_id) VALUES (?, ?)";
    private static final String REMOVE_APARTMENT = "DELETE FROM apartments WHERE apartment_id = ?";
    private static final String GET_AVAILABLE_APARTMENTS = "SELECT categories.category_id, apartment_id " +
            "FROM apartments JOIN categories ON categories.category_id = apartments.category_id " +
            "WHERE apartment_id IN (SELECT apartment_id FROM apartments WHERE apartment_id NOT IN " +
            "(SELECT apartment_id FROM reservations WHERE(arrival <= ? AND departure >= ?) " +
            "OR (arrival <= ? AND departure >= ?))) AND type=? AND number_of_beds=?";
    private static final String GET_ALL_APARTMENT_NUMBERS = "SELECT apartment_id, category_id FROM apartments";

    @Override
    public Map<Integer, Integer> getNumbersOfApartmentsGroupedByCategories() {
        Map<Integer, Integer> quantityOfApartmentsGroupedByCategories = new HashMap<>();
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_NUMBERS_OF_APARTMENTS_GROUPED_BY_CATEGORIES);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                quantityOfApartmentsGroupedByCategories.put(rs.getInt("category_id"), rs.getInt("number_of_apartments"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return quantityOfApartmentsGroupedByCategories;
    }

    @Override
    public int addApartment(int apartmentNumber, int category) {
        int result = 0;
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(ADD_APARTMENT)) {
            ps.setInt(1, apartmentNumber);
            ps.setInt(2, category);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return result;
    }

    @Override
    public int removeApartment(int apartmentNumber) {
        int result = 0;
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(REMOVE_APARTMENT)) {
            ps.setInt(1, apartmentNumber);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getAvailableApartments(String arrival, String departure, List<String> types, List<String> capacities) {
        Map<Integer, Integer> result = new HashMap<>();
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_AVAILABLE_APARTMENTS)) {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getAllApartmentNumbers() {
        Map<Integer, Integer> listOfApartments = new HashMap<>();
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_APARTMENT_NUMBERS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listOfApartments.put(rs.getInt("apartment_id"), rs.getInt("category_id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return listOfApartments;
    }
}