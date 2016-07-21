package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;

import javax.servlet.ServletException;
import java.io.IOException;

public class SetLocaleCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
return null;
//        String languageCountry = (String) request.getAttribute("language");
//        String[] separatedLanguageCountry = languageCountry.split("_");
//        String language = separatedLanguageCountry[0];
//        String country = separatedLanguageCountry[1];
//        Locale locale = new Locale(language, country);
//        HttpSession session = request.getSession(true);
//        session.setAttribute("locale", locale);
//        session.setAttribute("language", language);
//        request.getRequestDispatcher(Config.getInstance().getProperty(Config.START_PAGE)).forward(request, response);
    }
}