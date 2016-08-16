package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DaoFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.utils.UrlGetter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * Class {@code GuestsPageCommand} is a command, which implements
 * {@link ICommand} and redirects to guests.
 *
 * @author fantotsy
 * @version 1.0
 */
public class GuestsPageCommand implements ICommand {

    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        List<Guest> listOfGuests = DaoFactory.getDAOGuest().getAllGuests();
        wrapper.setRequestAttribute("listOfGuests", listOfGuests);
        return UrlGetter.getInstance().getUrl(UrlGetter.MAIN_ADMIN_GUESTS_PAGE);
    }
}