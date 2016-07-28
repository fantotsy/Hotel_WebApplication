package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.utils.URNsGetter;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Command which is created when error or exception occurred.
 * This command is subscribed for action '/error'.
 *
 * @author fantotsy
 * @version 1.0
 */

public class ErrorCommand implements ICommand {
    /**
     * This method returns '/WEB-INF/jsp/error.jsp'.
     *
     * @param wrapper request wrapper.
     * @return string, which is used in {@link ua.fantotsy.controllers.ServletController} to
     * define where to redirect current request and response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        return URNsGetter.getInstance().getURN(URNsGetter.ERROR_PAGE);
    }
}