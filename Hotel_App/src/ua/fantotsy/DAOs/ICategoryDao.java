package ua.fantotsy.DAOs;

import ua.fantotsy.entities.Category;

import java.util.List;

public interface ICategoryDao {
    List<Category> getAllCategories();

    List<String> getAllTypes();

    List<Integer> getAllCapacities();

    List<Category> getAllCategoriesForUser(String arrival, String departure, List<String> types, List<String> capacities);
}