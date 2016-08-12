package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.utils.ActionsGetter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Class {@code CheckOrderDataCommand} is a command,
 * which implements {@link ICommand} and redirects to
 * another command or to guest's main page.
 *
 * @author fantotsy
 * @version 1.0
 */
public class CheckOrderDataCommand implements ICommand {

    /**
     * Checks whether user has made a valid order and
     * determines which string to return.
     *
     * @param wrapper session and request wrapper.
     * @return string for redirection to another page.
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        // Check whether guest has made valid order.
        String dateChosen = (String) wrapper.getSessionAttribute("date_chosen");
        if (dateChosen == null) {
            dateChosen = wrapper.getRequestParameter("date_chosen");
            String arrival = wrapper.getRequestParameter("check-in_date");
            String departure = wrapper.getRequestParameter("check-out_date");
            String[] typesArray = wrapper.getRequestParameters("apartment_type[]");
            String[] capacitiesArray = wrapper.getRequestParameters("apartment_capacity[]");
            if ((arrival == null || departure == null) || (typesArray == null || capacitiesArray == null)) {
                return setErrorMessage(wrapper, "empty_data");
            }
            if (arrival.compareTo(departure) >= 0) {
                return setErrorMessage(wrapper, "arrival_is_later_than_departure");
            }

            wrapper.setSessionAttribute("date_chosen", dateChosen);
            List<String> types = Arrays.asList(typesArray);
            List<String> capacities = Arrays.asList(capacitiesArray);
            wrapper.setSessionAttribute("types", types);
            wrapper.setSessionAttribute("capacities", capacities);
            wrapper.setSessionAttribute("arrival", arrival);
            wrapper.setSessionAttribute("departure", departure);
        }
        return ActionsGetter.getInstance().getAction(ActionsGetter.ORDER_VALID);
    }

    private String setErrorMessage(ISessionRequestWrapper wrapper, String errorMessage) {
        wrapper.setRequestAttribute("error", errorMessage);
        return ActionsGetter.getInstance().getAction(ActionsGetter.GUEST);
    }
}