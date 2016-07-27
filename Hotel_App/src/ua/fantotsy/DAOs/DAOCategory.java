package ua.fantotsy.DAOs;

import org.apache.log4j.Logger;
import ua.fantotsy.datasource.ConnectionPool;
import ua.fantotsy.entities.Category;
import ua.fantotsy.utils.SQLQueriesGetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCategory implements IDAOCategory {
    private Logger logger = Logger.getLogger(DAOCategory.class.getName());
    private Connection connection = ConnectionPool.getInstance().getConnection();

    @Override
    public List<Category> getAllCategories() {
        List<Category> listOfCategories = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.GET_ALL_CATEGORIES));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), rs.getString("type"), rs.getInt("number_of_beds"), rs.getInt("price"), rs.getInt("apartments"));
                listOfCategories.add(category);
            }
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return listOfCategories;
    }

    @Override
    public List<Category> getAllCategoriesForUser(String arrival, String departure, List<String> types, List<String> capacities) {
        List<Category> listOfCategories = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.GET_ALL_AVAILABLE_APARTMENTS_FOR_GUEST))) {
            ps.setString(1, arrival);
            ps.setString(2, arrival);
            ps.setString(3, departure);
            ps.setString(4, departure);
            ResultSet rs;
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
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return listOfCategories;
    }

    @Override
    public List<String> getAllTypes() {
        List<String> listOfTypes = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.GET_ALL_TYPES));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String type = rs.getString("type");
                listOfTypes.add(type);
            }
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return listOfTypes;
    }

    @Override
    public List<Integer> getAllCapacities() {
        List<Integer> listOfCapacities = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueriesGetter.getInstance().getSQLQuery(SQLQueriesGetter.GET_ALL_CAPACITIES));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int capacity = rs.getInt("number_of_beds");
                listOfCapacities.add(capacity);
            }
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return listOfCapacities;
    }
}