package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.properties.Config;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Locale;

public class StartPageCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
//        if(wrapper.getRequestParameter("logout") == null)
//            throw new NullPointerException();

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

        //Check whether locale was changed.
        String languageCountry = wrapper.getRequestParameter("language");
        if (languageCountry != null) {
            String[] separatedLanguageCountry = languageCountry.split("_");
            String language = separatedLanguageCountry[0];
            String country = separatedLanguageCountry[1];
            Locale locale = new Locale(language, country);
            wrapper.setSessionAttribute("locale", locale);
            wrapper.setSessionAttribute("language", language);
        }
        return Config.getInstance().getProperty(Config.START_PAGE);
    }
}