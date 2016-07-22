package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.properties.Config;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class GuestsPageCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        List<Guest> listOfGuests = DAOFactory.getDAOGuest().getAllGuests();
        wrapper.setRequestAttribute("listOfGuests", listOfGuests);
        return Config.getInstance().getProperty(Config.MAIN_ADMIN_GUESTS_PAGE);
    }
}