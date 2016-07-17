package ua.fantotsy.DAOs;

import ua.fantotsy.datasource.ConnectionSource;
import ua.fantotsy.entities.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCategory implements IDAOCategory {
    private static final String READ_ALL_QUERY = "SELECT categories.category_id, type, number_of_beds, price, " +
            "COUNT(apartment_id) AS apartments FROM categories LEFT JOIN apartments " +
            "ON categories.category_id = apartments.category_id GROUP BY categories.category_id";
    private static final String READ_ALL_QUERY_USER = "SELECT categories.category_id, type, number_of_beds, price, " +
            "COUNT(apartment_id) AS apartments FROM apartments JOIN categories " +
            "ON apartments.category_id = categories.category_id WHERE apartment_id IN " +
            "(SELECT apartment_id FROM apartments WHERE apartment_id NOT IN " +
            "(SELECT apartment_id FROM reservations WHERE (arrival <= ? AND departure >= ?) " +
            "OR (arrival <= ? AND departure >= ?))) AND categories.type=? AND categories.number_of_beds=? GROUP BY category_id";
    private static final String READ_ALL_TYPES_QUERY = "SELECT type FROM categories GROUP BY type";
    private static final String READ_ALL_CAPACITIES_QUERY = "SELECT number_of_beds FROM categories GROUP BY number_of_beds";

    @Override
    public List<Category> getAllCategories() {
        List<Category> listOfCategories = new ArrayList<>();
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(READ_ALL_QUERY);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), rs.getString("type"), rs.getInt("number_of_beds"), rs.getInt("price"), rs.getInt("apartments"));
                listOfCategories.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return listOfCategories;
    }

    public List<Category> getAllCategoriesForUser(String arrival, String departure, List<String> types, List<String> capacities) {
        List<Category> listOfCategories = new ArrayList<>();
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(READ_ALL_QUERY_USER)) {
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
                        Category category = new Category(rs.getInt("category_id"), rs.getString("type"), rs.getInt("number_of_beds"), rs.getInt("price"), rs.getInt("apartments"));
                        listOfCategories.add(category);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return listOfCategories;
    }

    @Override
    public List<String> getAllTypes() {
        List<String> listOfTypes = new ArrayList<>();
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(READ_ALL_TYPES_QUERY);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String type = rs.getString("type");
                listOfTypes.add(type);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return listOfTypes;
    }

    @Override
    public List<Integer> getAllCapacities() {
        List<Integer> listOfCapacities = new ArrayList<>();
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(READ_ALL_CAPACITIES_QUERY);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int capacity = rs.getInt("number_of_beds");
                listOfCapacities.add(capacity);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return listOfCapacities;
    }
}