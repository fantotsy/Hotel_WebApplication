package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;

import javax.servlet.ServletException;
import java.io.IOException;

public class GuestsPageCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        return null;
        //List<Guest> listOfGuests = DAOFactory.getDAOGuest().getAllGuests();
        //request.setAttribute("listOfGuests", listOfGuests);
        //request.getRequestDispatcher(Config.getInstance().getProperty(Config.MAIN_ADMIN_GUESTS_PAGE)).forward(request, response);
    }
}
