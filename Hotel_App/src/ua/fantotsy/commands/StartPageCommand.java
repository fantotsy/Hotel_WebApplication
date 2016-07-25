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
        // Check whether 'logout' was pressed.
        System.out.println("1.Session.language is " + wrapper.getSessionAttribute("language"));
        System.out.println("logout: " + wrapper.getRequestParameter("logout"));
        System.out.println("1.invalidated: " + wrapper.getSessionInvalidated());
        if (wrapper.getRequestParameter("logout") != null && !wrapper.getSessionInvalidated()) {
            wrapper.setSessionInvalidated(true);
            System.out.println("2.invalidated: " + wrapper.getSessionInvalidated());
            return wrapper.sessionInvalidate();
        }
        // Set default locale.
        System.out.println("2.Session.language is " + wrapper.getSessionAttribute("language") + "\n");
        if (wrapper.getSessionAttribute("language") == null) {
            Locale locale = Locale.ENGLISH;
            String language = locale.getLanguage();
            wrapper.setSessionAttribute("locale", locale);
            wrapper.setSessionAttribute("language", language);
        }
        System.out.println("3.Session.language is " + wrapper.getSessionAttribute("language") + "\n");
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