package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.utils.URNsGetter;

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
        return URNsGetter.getInstance().getURN(URNsGetter.MAIN_ADMIN_PAGE);
    }
}