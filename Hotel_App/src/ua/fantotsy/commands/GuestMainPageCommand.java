package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.utils.URNsGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * Class {@code GuestMainPageCommand} is a command, which implements
 * {@link ICommand} and redirects to guest's main page.
 *
 * @author fantotsy
 * @version 1.0
 */
public class GuestMainPageCommand implements ICommand {

    /**
     * Checks whether guest has made a cancellation of existing booking.
     * Then {@code execute} determines which string to return.
     *
     * @param wrapper session and request wrapper.
     * @return session and request wrapper.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        // Check whether 'Cancel' button was pressed.
        String canceledReservationId = wrapper.getRequestParameter("reservation_id");
        if (canceledReservationId != null) {
            DAOFactory.getDAOReservation().deleteCertainReservation(Integer.parseInt(canceledReservationId));
        }
        wrapper.setSessionAttribute("date_chosen", null);
        String[] dateLimits = Utils.getDateLimits();
        List<String> listOfTypes = DAOFactory.getDAOCategory().getAllTypes();
        List<Integer> listOfCapacities = DAOFactory.getDAOCategory().getAllCapacities();
        Guest certainGuest = (Guest) wrapper.getSessionAttribute("guestInfo");
        List<Reservation> listOfReservations = DAOFactory.getDAOReservation().getCertainReservations(certainGuest.getGuestId());

        wrapper.setRequestAttribute("today", dateLimits[0]);
        wrapper.setRequestAttribute("yearLater", dateLimits[1]);
        wrapper.setRequestAttribute("listOfTypes", listOfTypes);
        wrapper.setRequestAttribute("listOfCapacities", listOfCapacities);
        wrapper.setRequestAttribute("listOfReservations", listOfReservations);

        return URNsGetter.getInstance().getURN(URNsGetter.MAIN_GUEST_PAGE);
    }
}