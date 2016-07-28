package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Apartment;
import ua.fantotsy.entities.Category;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.utils.URNsGetter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Command which is created by pressing 'Book' button, which is located
 * in 'web/WEB-INF/jsp/main_guest_booking.jsp'. This command is subscribed for action '/order_valid'.
 *
 * @author fantotsy
 * @version 1.0
 */

public class GuestBookingPageCommand implements ICommand {
    /**
     * This method checks whether order made by guest is valid.
     * If it is valid, a new booking inserts into data base.
     *
     * @param wrapper request wrapper.
     * @return string, which is used in {@link ua.fantotsy.controllers.ServletController} to
     * define where to redirect current request and response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        String categoryId = wrapper.getRequestParameter("category_id");
        String arrival = (String) wrapper.getSessionAttribute("arrival");
        String departure = (String) wrapper.getSessionAttribute("departure");
        List<String> types = (List<String>) wrapper.getSessionAttribute("types");
        List<String> capacities = (List<String>) wrapper.getSessionAttribute("capacities");
        String[] bookedApartmentsArray = wrapper.getRequestParameters("booked_apartments[]");
        if (bookedApartmentsArray == null) {
            wrapper.setRequestAttribute("error", "apartments_array_is_empty");
            wrapper.setRequestAttribute("category_id", categoryId);
        } else {
            List<String> bookedApartments = Arrays.asList(bookedApartmentsArray);
            // Insert all reservations from 'bookedApartments' in data base.
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

        return URNsGetter.getInstance().getURN(URNsGetter.MAIN_GUEST_BOOKING_PAGE);
    }
}