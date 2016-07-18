package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Category;
import ua.fantotsy.properties.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GuestBookingPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String arrival = (String) session.getAttribute("arrival");
        String departure = (String) session.getAttribute("departure");
        List<String> types = (List<String>) session.getAttribute("types");
        List<String> capacities = (List<String>) session.getAttribute("capacities");
        System.out.println(arrival);
        System.out.println(departure);

        List<Category> listOfCategories = DAOFactory.getDAOCategory().getAllCategoriesForUser(arrival, departure, types, capacities);
        Map<Integer, Integer> listOfApartments = DAOFactory.getDAOApartment().getAvailableApartments(arrival, departure, types, capacities);

        request.setAttribute("listOfCategories", listOfCategories);
        request.setAttribute("listOfApartments", listOfApartments);
        request.getRequestDispatcher(Config.getInstance().getProperty(Config.MAIN_GUEST_BOOKING_PAGE)).forward(request, response);
    }
}