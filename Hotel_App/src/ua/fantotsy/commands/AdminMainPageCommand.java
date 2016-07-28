package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.utils.URNsGetter;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Command which is created by pressing 'Sign in' button, which is located
 * in 'web/WEB-INF/jsp/index.jsp'. This command is subscribed for action '/admin'.
 *
 * @author fantotsy
 * @version 1.0
 */

public class AdminMainPageCommand implements ICommand {
    /**
     * This method returns '/WEB-INF/jsp/main_admin.jsp'.
     *
     * @param wrapper request wrapper.
     * @return string, which is used in {@link ua.fantotsy.controllers.ServletController} to
     * define where to redirect current request and response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        return URNsGetter.getInstance().getURN(URNsGetter.MAIN_ADMIN_PAGE);
    }
}