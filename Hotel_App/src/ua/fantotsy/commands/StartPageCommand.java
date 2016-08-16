package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.utils.UrlGetter;

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
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        if (isLogoutPressed(wrapper)) {
            return wrapper.sessionInvalidate();
        } else {
            if (!isLocaleAlreadyChosen(wrapper)) {
                setDefaultLocale(wrapper);
            }
            if (isLoginIconPressed(wrapper)) {
                setChosenLocale(wrapper);
            }
            return UrlGetter.getInstance().getUrl(UrlGetter.START_PAGE);
        }
    }

    private boolean isLogoutPressed(ISessionRequestWrapper wrapper) {
        String logout = wrapper.getRequestParameter("logout");
        Boolean isSessionInvalidated = wrapper.getSessionInvalidated();
        return (logout != null && !isSessionInvalidated);
    }

    private boolean isLocaleAlreadyChosen(ISessionRequestWrapper wrapper) {
        return (wrapper.getSessionAttribute("language") != null);
    }

    private void setDefaultLocale(ISessionRequestWrapper wrapper) {
        Locale locale = Locale.ENGLISH;
        String language = locale.getLanguage();
        wrapper.setSessionAttribute("locale", locale);
        wrapper.setSessionAttribute("language", language);
    }

    private boolean isLoginIconPressed(ISessionRequestWrapper wrapper) {
        String languageCountry = wrapper.getRequestParameter("language");
        return (languageCountry != null);
    }

    private void setChosenLocale(ISessionRequestWrapper wrapper) {
        String languageCountry = wrapper.getRequestParameter("language");
        String[] separatedLanguageCountry = languageCountry.split("_");
        String language = separatedLanguageCountry[0];
        String country = separatedLanguageCountry[1];
        Locale locale = new Locale(language, country);
        wrapper.setSessionAttribute("locale", locale);
        wrapper.setSessionAttribute("language", language);
    }
}