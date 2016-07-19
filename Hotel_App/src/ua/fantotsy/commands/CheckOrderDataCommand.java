package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CheckOrderDataCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String dateChosen = (String) session.getAttribute("date_chosen");

        //If guest have not made valid order
        if (dateChosen == null) {
            dateChosen = request.getParameter("date_chosen");
            session.setAttribute("date_chosen", dateChosen);
            String arrival = request.getParameter("check-in_date");
            String departure = request.getParameter("check-out_date");
            Object typesObject = request.getParameterValues("apartment_type[]");
            Object capacitiesObject = request.getParameterValues("apartment_capacity[]");
            //Если дата заселения не раньше даты выселения.
            if (arrival != null && departure != null && arrival.compareTo(departure) >= 0) {
                request.setAttribute("error", "arrival is later than departure");
                session.setAttribute("date_chosen", "false");
                request.getRequestDispatcher("/guest").forward(request, response);
            }
            if (typesObject == null || capacitiesObject == null) {
                request.setAttribute("error", "empty types or capacities");
                session.setAttribute("date_chosen", "false");
                request.getRequestDispatcher("/guest").forward(request, response);
            }

            List<String> types = Arrays.asList(request.getParameterValues("apartment_type[]"));
            List<String> capacities = Arrays.asList(request.getParameterValues("apartment_capacity[]"));
            session.setAttribute("types", types);
            session.setAttribute("capacities", capacities);
            session.setAttribute("arrival", arrival);
            session.setAttribute("departure", departure);
        }

        request.getRequestDispatcher("/order_valid").forward(request, response);
    }
}