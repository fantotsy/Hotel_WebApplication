package ua.fantotsy.DAOs;

import org.apache.log4j.Logger;
import ua.fantotsy.datasource.ConnectionPool;
import ua.fantotsy.entities.Category;
import ua.fantotsy.utils.SqlQueriesGetter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides basic implementation of CRUD operations with the help of JDBC.
 * All operations in this class are connected with 'categories' table.
 *
 * @author fantotsy
 * @version 1.0
 */
public class CategoryDao implements ICategoryDao {

    @Override
    public List<Category> getAllCategories() {
        List<Category> listOfCategories = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlQueriesGetter.getInstance().getSQLQuery(SqlQueriesGetter.GET_ALL_CATEGORIES));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), rs.getString("type"), rs.getInt("number_of_beds"), rs.getInt("price"), rs.getInt("apartments"));
                listOfCategories.add(category);
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(CategoryDao.class.getName());
            logger.error(e);
        }
        return listOfCategories;
    }

    @Override
    public List<Category> getAllCategoriesForUser(String arrival, String departure, List<String> types, List<String> capacities) {
        List<Category> listOfCategories = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             CallableStatement cs = connection.prepareCall("{call get_all_categories_for_user(?, ?, ?, ?)}")) {
            cs.setString(1, arrival);
            cs.setString(2, departure);
            cs.setString(3, types.toString());
            cs.setString(4, capacities.toString());
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), rs.getString("type"), rs.getInt("number_of_beds"), rs.getInt("price"), rs.getInt("apartments"));
                listOfCategories.add(category);
            }
            rs.close();
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(CategoryDao.class.getName());
            logger.error(e);
        }
        return listOfCategories;
    }

    @Override
    public List<String> getAllTypes() {
        List<String> listOfTypes = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlQueriesGetter.getInstance().getSQLQuery(SqlQueriesGetter.GET_ALL_TYPES));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String type = rs.getString("type");
                listOfTypes.add(type);
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(CategoryDao.class.getName());
            logger.error(e);
        }
        return listOfTypes;
    }

    @Override
    public List<Integer> getAllCapacities() {
        List<Integer> listOfCapacities = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlQueriesGetter.getInstance().getSQLQuery(SqlQueriesGetter.GET_ALL_CAPACITIES));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int capacity = rs.getInt("number_of_beds");
                listOfCapacities.add(capacity);
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(CategoryDao.class.getName());
            logger.error(e);
        }
        return listOfCapacities;
    }
}