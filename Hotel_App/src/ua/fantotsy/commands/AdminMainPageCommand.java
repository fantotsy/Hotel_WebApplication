package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.utils.UrnGetter;
import ua.fantotsy.utils.Utils;

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

    /**
     * Sets into session special token for CSRF protection.
     *
     * @param wrapper session and request wrapper.
     * @return string for redirection to another page.
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        String sessionId = wrapper.getSessionId();
        String antiCsrfToken = Utils.encryptionMD5(sessionId);
        wrapper.setRequestAttribute("antiCsrfToken", antiCsrfToken);
        return UrnGetter.getInstance().getUrn(UrnGetter.MAIN_ADMIN_PAGE);
    }
}