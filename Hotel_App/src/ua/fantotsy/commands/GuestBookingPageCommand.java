package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Apartment;
import ua.fantotsy.entities.Category;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.properties.Config;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GuestBookingPageCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        String categoryId = wrapper.getRequestParameter("category_id");
        String arrival = (String) wrapper.getSessionAttribute("arrival");
        String departure = (String) wrapper.getSessionAttribute("departure");
        List<String> types = (List<String>) wrapper.getSessionAttribute("types");
        List<String> capacities = (List<String>) wrapper.getSessionAttribute("capacities");
        String[] bookedApartmentsArray = wrapper.getRequestParameters("booked_apartments[]");
        if (bookedApartmentsArray == null) {
            wrapper.setRequestAttribute("error", "apartments array is empty");
        } else {
            List<String> bookedApartments = Arrays.asList(wrapper.getRequestParameters("booked_apartments[]"));

            for (String apartment : bookedApartments) {
                Guest guest = (Guest) wrapper.getSessionAttribute("guestInfo");
                Apartment bookedApartment = new Apartment(Integer.parseInt(apartment));
                Reservation reservation = new Reservation(guest, bookedApartment, arrival, departure);
                DAOFactory.getDAOReservation().insertNewReservation(reservation);
            }
        }

        List<Category> listOfCategories = DAOFactory.getDAOCategory().getAllCategoriesForUser(arrival, departure, types, capacities);
        Map<Integer, Integer> listOfApartments = DAOFactory.getDAOApartment().getAvailableApartments(arrival, departure, types, capacities);

        wrapper.setRequestAttribute("listOfCategories", listOfCategories);
        wrapper.setRequestAttribute("listOfApartments", listOfApartments);
        wrapper.setRequestAttribute("category_id", categoryId);

        return Config.getInstance().getProperty(Config.MAIN_GUEST_BOOKING_PAGE);
    }
}