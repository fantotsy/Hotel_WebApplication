package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Apartment;
import ua.fantotsy.entities.Category;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.properties.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GuestBookingPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("category_id");
        HttpSession session = request.getSession(true);
        String arrival = (String) session.getAttribute("arrival");
        String departure = (String) session.getAttribute("departure");
        List<String> types = (List<String>) session.getAttribute("types");
        List<String> capacities = (List<String>) session.getAttribute("capacities");
        String[] bookedApartmentsArray = request.getParameterValues("booked_apartments[]");
        if (bookedApartmentsArray == null) {
            request.setAttribute("error", "apartments array is empty");
        } else {
            List<String> bookedApartments = Arrays.asList(request.getParameterValues("booked_apartments[]"));

            for (String apartment : bookedApartments) {
                Guest guest = (Guest) session.getAttribute("guestInfo");
                Apartment bookedApartment = new Apartment(Integer.parseInt(apartment));
                Reservation reservation = new Reservation(guest, bookedApartment, arrival, departure);
                DAOFactory.getDAOReservation().insertNewReservation(reservation);
            }
        }

        System.out.println("Params:\n");
        System.out.println(arrival);
        System.out.println(departure);
        System.out.println(types);
        System.out.println(capacities);
        List<Category> listOfCategories = DAOFactory.getDAOCategory().getAllCategoriesForUser(arrival, departure, types, capacities);
        Map<Integer, Integer> listOfApartments = DAOFactory.getDAOApartment().getAvailableApartments(arrival, departure, types, capacities);

        request.setAttribute("listOfCategories", listOfCategories);
        request.setAttribute("listOfApartments", listOfApartments);
        request.setAttribute("category_id", categoryId);

        request.getRequestDispatcher(Config.getInstance().getProperty(Config.MAIN_GUEST_BOOKING_PAGE)).forward(request, response);
    }
}