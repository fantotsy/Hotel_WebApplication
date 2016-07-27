package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.utils.URNsGetter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class ReservationsPageCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        List<Reservation> listOfReservations = DAOFactory.getDAOReservation().getAllReservations();
        wrapper.setRequestAttribute("listOfReservations", listOfReservations);
        return URNsGetter.getInstance().getURN(URNsGetter.MAIN_ADMIN_RESERVATIONS_PAGE);
    }
}