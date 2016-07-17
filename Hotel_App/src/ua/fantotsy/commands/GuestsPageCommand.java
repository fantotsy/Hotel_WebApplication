package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.properties.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GuestsPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Guest> listOfGuests = DAOFactory.getDAOGuest().getAllGuests();
        request.setAttribute("listOfGuests", listOfGuests);
        request.getRequestDispatcher(Config.getInstance().getProperty(Config.MAIN_ADMIN_GUESTS_PAGE)).forward(request, response);
    }
}
