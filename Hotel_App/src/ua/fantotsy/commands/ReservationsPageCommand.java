package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;

import javax.servlet.ServletException;
import java.io.IOException;

public class ReservationsPageCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        return null;
        //List<Reservation> listOfReservations = DAOFactory.getDAOReservation().getAllReservations();
        //request.setAttribute("listOfReservations", listOfReservations);
        //request.getRequestDispatcher(Config.getInstance().getProperty(Config.MAIN_ADMIN_RESERVATIONS_PAGE)).forward(request, response);
    }
}