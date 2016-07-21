package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CheckOrderDataCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        String dateChosen = (String) wrapper.getSessionAttribute("date_chosen");

        //If guest have not made valid order
        if (dateChosen == null) {
            dateChosen = wrapper.getRequestParameter("date_chosen");
            wrapper.setSessionAttribute("date_chosen", dateChosen);
            String arrival = wrapper.getRequestParameter("check-in_date");
            String departure = wrapper.getRequestParameter("check-out_date");
            Object typesObject = wrapper.getRequestParameters("apartment_type[]");
            Object capacitiesObject = wrapper.getRequestParameters("apartment_capacity[]");

            if (arrival != null && departure != null && arrival.compareTo(departure) >= 0) {
                wrapper.setRequestAttribute("error", "arrival is later than departure");
                wrapper.setSessionAttribute("date_chosen", "false");
                return "/guest";
            }
            if (typesObject == null || capacitiesObject == null) {
                wrapper.setRequestAttribute("error", "empty types or capacities");
                wrapper.setSessionAttribute("date_chosen", "false");
                return "/guest";
            }

            List<String> types = Arrays.asList(wrapper.getRequestParameters("apartment_type[]"));
            List<String> capacities = Arrays.asList(wrapper.getRequestParameters("apartment_capacity[]"));
            wrapper.setSessionAttribute("types", types);
            wrapper.setSessionAttribute("capacities", capacities);
            wrapper.setSessionAttribute("arrival", arrival);
            wrapper.setSessionAttribute("departure", departure);
        }

        return "/order_valid";
    }
}