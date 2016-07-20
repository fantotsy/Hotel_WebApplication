package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.properties.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class SetLocaleCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String languageCountry = (String) request.getAttribute("language");
        String[] separatedLanguageCountry = languageCountry.split("_");
        String language = separatedLanguageCountry[0];
        String country = separatedLanguageCountry[1];
        Locale locale = new Locale(language, country);
        HttpSession session = request.getSession(true);
        session.setAttribute("locale", locale);
        session.setAttribute("language", language);
        request.getRequestDispatcher(Config.getInstance().getProperty(Config.START_PAGE)).forward(request, response);
    }
}