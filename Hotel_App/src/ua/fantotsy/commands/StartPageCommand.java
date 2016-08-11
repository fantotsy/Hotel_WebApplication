package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.utils.UrnGetter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Locale;

/**
 * Class {@code StartPageCommand} is a command, which implements
 * {@link ICommand} and redirects to start page.
 *
 * @author fantotsy
 * @version 1.0
 */
public class StartPageCommand implements ICommand {

    /**
     * Checks whether user was logged out. If yes, {@code execute} returns special
     * string to invalidate current session. If not, {@code execute} sets locale
     * settings and returns string for redirection to start page.
     *
     * @param wrapper session and request wrapper.
     * @return string for redirection to another page.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        // Check whether 'logout' was pressed.
        if (wrapper.getRequestParameter("logout") != null && !wrapper.getSessionInvalidated()) {
            return wrapper.sessionInvalidate();
        }

        // Set default locale.
        if (wrapper.getSessionAttribute("language") == null) {
            Locale locale = Locale.ENGLISH;
            String language = locale.getLanguage();
            wrapper.setSessionAttribute("locale", locale);
            wrapper.setSessionAttribute("language", language);
        }

        // Check whether locale was changed.
        String languageCountry = wrapper.getRequestParameter("language");
        if (languageCountry != null) {
            String[] separatedLanguageCountry = languageCountry.split("_");
            String language = separatedLanguageCountry[0];
            String country = separatedLanguageCountry[1];
            Locale locale = new Locale(language, country);
            wrapper.setSessionAttribute("locale", locale);
            wrapper.setSessionAttribute("language", language);
        }
        return UrnGetter.getInstance().getUrn(UrnGetter.START_PAGE);
    }
}