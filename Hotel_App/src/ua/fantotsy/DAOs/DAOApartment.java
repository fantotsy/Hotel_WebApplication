package ua.fantotsy.DAOs;

import ua.fantotsy.datasource.ConnectionSource;
import ua.fantotsy.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOApartment implements IDAOApartment {
    @Override
    public Map<Integer, Integer> getNumbersOfApartmentsGroupedByCategories() {
        Map<Integer, Integer> quantityOfApartmentsGroupedByCategories = new HashMap<>();
        try (Connection connection = ConnectionSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("get_numbers_of_apartments_grouped_by_categories"));
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
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("add_apartment"))) {
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
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("remove_apartment"))) {
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
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("get_available_apartments"))) {
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
             PreparedStatement ps = connection.prepareStatement(Utils.getSQLQuery("get_all_apartment_numbers"));
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