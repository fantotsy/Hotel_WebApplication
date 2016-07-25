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
        // Check whether guest have made valid order.
        String dateChosen = (String) wrapper.getSessionAttribute("date_chosen");
        if (dateChosen == null) {
            dateChosen = wrapper.getRequestParameter("date_chosen");
            String arrival = wrapper.getRequestParameter("check-in_date");
            String departure = wrapper.getRequestParameter("check-out_date");
            String[] typesObject = wrapper.getRequestParameters("apartment_type[]");
            String[] capacitiesObject = wrapper.getRequestParameters("apartment_capacity[]");

            if (arrival.compareTo(departure) >= 0) {
                return setErrorMessage(wrapper, "arrival_is_later_than_departure");
            }
            if (typesObject == null || capacitiesObject == null) {
                return setErrorMessage(wrapper, "empty_types_or_capacities");
            }

            wrapper.setSessionAttribute("date_chosen", dateChosen);
            List<String> types = Arrays.asList(typesObject);
            List<String> capacities = Arrays.asList(capacitiesObject);
            wrapper.setSessionAttribute("types", types);
            wrapper.setSessionAttribute("capacities", capacities);
            wrapper.setSessionAttribute("arrival", arrival);
            wrapper.setSessionAttribute("departure", departure);
        }
        return "/order_valid";
    }

    private String setErrorMessage(ISessionRequestWrapper wrapper, String errorMessage) {
        wrapper.setRequestAttribute("error", errorMessage);
        return "/guest";
    }
}