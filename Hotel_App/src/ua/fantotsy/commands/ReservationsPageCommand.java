package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DaoFactory;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.utils.UrnGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

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
        List<Reservation> listOfReservations = DaoFactory.getDAOReservation().getAllReservations();
        String sessionId = wrapper.getSessionId();
        String antiCsrfToken = Utils.encryptionMD5(sessionId);

        wrapper.setRequestAttribute("listOfReservations", listOfReservations);
        wrapper.setRequestAttribute("anti_csrf_token", antiCsrfToken);
        return UrnGetter.getInstance().getUrn(UrnGetter.MAIN_ADMIN_RESERVATIONS_PAGE);
    }
}