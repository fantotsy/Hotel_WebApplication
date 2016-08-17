package ua.fantotsy.DAOs;

import java.util.List;
import java.util.Map;

public interface IApartmentDao {
    Map<Integer, Integer> getNumbersOfApartmentsGroupedByCategories();

    int addApartment(int category, int apartmentNumber);

    int removeApartment(int apartmentNumber);

    Map<Integer, Integer> getAvailableApartments(String arrival, String departure, List<String> types, List<String> capacities);

    Map<Integer, Integer> getAllApartmentNumbers();

    Map<Integer, Integer> getAvailableApartmentsOnDate(String arrival, String departure);
}