package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.entities.Reservation;
import ua.fantotsy.properties.Config;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class GuestMainPageCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        String cancelReservation = wrapper.getRequestParameter("reservation_id");
        if (cancelReservation != null) {
            DAOFactory.getDAOReservation().deleteCertainReservation(Integer.parseInt(cancelReservation));
        }

        wrapper.setSessionAttribute("date_chosen", null);
        String[] dateLimits = Utils.getDateLimits();
        List<String> listOfTypes = DAOFactory.getDAOCategory().getAllTypes();
        List<Integer> listOfCapacities = DAOFactory.getDAOCategory().getAllCapacities();
        Guest certainGuest = (Guest) wrapper.getSessionAttribute("guestInfo");
        List<Reservation> listOfReservations = DAOFactory.getDAOReservation().getCertainReservations(certainGuest.getGuestId());

        wrapper.setRequestAttribute("today", dateLimits[0]);
        wrapper.setRequestAttribute("year_later", dateLimits[1]);
        wrapper.setRequestAttribute("listOfTypes", listOfTypes);
        wrapper.setRequestAttribute("listOfCapacities", listOfCapacities);
        wrapper.setRequestAttribute("listOfReservations", listOfReservations);

        return Config.getInstance().getProperty(Config.MAIN_GUEST_PAGE);
    }
}