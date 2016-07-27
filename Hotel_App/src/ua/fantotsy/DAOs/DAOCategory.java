package ua.fantotsy.DAOs;

import ua.fantotsy.datasource.ConnectionPool;
import ua.fantotsy.entities.Category;
import ua.fantotsy.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCategory implements IDAOCategory {
    @Override
    public List<Category> getAllCategories() {
        List<Category> listOfCategories = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("get_all_categories"));
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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("get_all_available_apartments_for_guest"))) {
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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("get_all_types"));
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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("get_all_capacities"));
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