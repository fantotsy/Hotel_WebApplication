package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.utils.UrlGetter;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Class {@code AdminMainPageCommand} is a command,
 * which implements {@link ICommand} and redirects to admin's main page.
 *
 * @author fantotsy
 * @version 1.0
 */
public class AdminMainPageCommand implements ICommand {

    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        return UrlGetter.getInstance().getUrl(UrlGetter.MAIN_ADMIN_PAGE);
    }
}