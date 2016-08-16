package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DaoFactory;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.utils.UrlGetter;

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
        wrapper.setRequestAttribute("listOfReservations", listOfReservations);
        return UrlGetter.getInstance().getUrl(UrlGetter.MAIN_ADMIN_RESERVATIONS_PAGE);
    }
}