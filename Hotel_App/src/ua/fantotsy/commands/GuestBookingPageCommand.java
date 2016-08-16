package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DaoFactory;
import ua.fantotsy.entities.Apartment;
import ua.fantotsy.entities.Category;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.utils.UrlGetter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Class {@code GuestBookingPageCommand} is a command, which implements
 * {@link ICommand} and redirects to another command or page.
 *
 * @author fantotsy
 * @version 1.0
 */
public class GuestBookingPageCommand implements ICommand {

    /**
     * Checks whether order made by guest is valid. In case of
     * its validation, a new booking inserts into data base.
     *
     * @param wrapper session and request wrapper.
     * @return string for redirection to another page.
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
                DaoFactory.getDAOReservation().insertNewReservation(reservation);
            }
        }

        List<Category> listOfCategories = DaoFactory.getDAOCategory().getAllCategoriesForUser(arrival, departure, types, capacities);
        Map<Integer, Integer> listOfApartments = DaoFactory.getDAOApartment().getAvailableApartments(arrival, departure, types, capacities);

        wrapper.setRequestAttribute("listOfCategories", listOfCategories);
        wrapper.setRequestAttribute("listOfApartments", listOfApartments);

        return UrlGetter.getInstance().getUrl(UrlGetter.MAIN_GUEST_BOOKING_PAGE);
    }
}