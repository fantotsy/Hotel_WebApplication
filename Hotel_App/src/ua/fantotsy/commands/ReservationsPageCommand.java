package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.properties.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReservationsPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reservation> listOfReservations = DAOFactory.getDAOReservation().getAllReservations();
        request.setAttribute("listOfReservations", listOfReservations);
        request.getRequestDispatcher(Config.getInstance().getProperty(Config.MAIN_ADMIN_RESERVATIONS_PAGE)).forward(request, response);
    }
}