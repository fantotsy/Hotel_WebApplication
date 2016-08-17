package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DaoFactory;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.utils.UrlGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Class {@code ReservationsPageCommand} is a command, which implements
 * {@link ICommand} and redirects to reservations page.
 *
 * @author fantotsy
 * @version 1.0
 */
public class ReservationsPageCommand implements ICommand {

    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        String[] dateLimits = Utils.getDateLimits();
        wrapper.setRequestAttribute("today", dateLimits[0]);
        wrapper.setRequestAttribute("yearLater", dateLimits[1]);
        String reservationId = wrapper.getRequestParameter("reservation_id");

        if (wrapper.getRequestParameter("reservation_id") != null) {
            String arrival = wrapper.getRequestParameter("check-in_date");
            String departure = wrapper.getRequestParameter("check-out_date");
            if (arrival == null || departure == null) {
                wrapper.setRequestAttribute("error", new String[]{reservationId, "empty_data"});
                return setErrorMessage(wrapper);
            }
            if (arrival.compareTo(departure) >= 0) {
                wrapper.setRequestAttribute("error", new String[]{reservationId, "arrival_is_later_than_departure"});
                return setErrorMessage(wrapper);
            }
            Map<Integer, Integer> listOfApartments = DaoFactory.getDAOApartment().getAvailableApartmentsOnDate(arrival, departure);
            if (listOfApartments.containsKey(Integer.parseInt(wrapper.getRequestParameter("apartment_id")))) {
                DaoFactory.getDAOReservation().updateReservation(Integer.parseInt(reservationId), arrival, departure);
            } else {
                wrapper.setRequestAttribute("error", new String[]{reservationId, "error"});
                return setErrorMessage(wrapper);
            }
        }

        List<Reservation> listOfReservations = DaoFactory.getDAOReservation().getAllReservations();
        wrapper.setRequestAttribute("listOfReservations", listOfReservations);
        return UrlGetter.getInstance().getUrl(UrlGetter.MAIN_ADMIN_RESERVATIONS_PAGE);
    }

    private String setErrorMessage(ISessionRequestWrapper wrapper) {
        List<Reservation> listOfReservations = DaoFactory.getDAOReservation().getAllReservations();
        wrapper.setRequestAttribute("listOfReservations", listOfReservations);
        return UrlGetter.getInstance().getUrl(UrlGetter.MAIN_ADMIN_RESERVATIONS_PAGE);
    }
}