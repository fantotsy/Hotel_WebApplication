package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.properties.Config;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GuestMainPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cancelReservation = request.getParameter("reservation_id");
        if (cancelReservation != null) {
            DAOFactory.getDAOReservation().deleteCertainReservation(Integer.parseInt(cancelReservation));
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("date_chosen", null);
        String[] dateLimits = Utils.getDateLimits();
        List<String> listOfTypes = DAOFactory.getDAOCategory().getAllTypes();
        List<Integer> listOfCapacities = DAOFactory.getDAOCategory().getAllCapacities();
        Guest certainGuest = (Guest) session.getAttribute("guestInfo");
        List<Reservation> listOfReservations = DAOFactory.getDAOReservation().getCertainReservations(certainGuest.getGuestId());

        request.setAttribute("today", dateLimits[0]);
        request.setAttribute("year_later", dateLimits[1]);
        request.setAttribute("listOfTypes", listOfTypes);
        request.setAttribute("listOfCapacities", listOfCapacities);
        request.setAttribute("listOfReservations", listOfReservations);

        request.getRequestDispatcher(Config.getInstance().getProperty(Config.MAIN_GUEST_PAGE)).forward(request, response);
    }
}