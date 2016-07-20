package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.properties.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class StartPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (request.getParameter("logout") != null) {
            session.invalidate();
        }

        String languageCountry = request.getParameter("language");
        if (languageCountry == null) {
            if (session.getAttribute("locale") == null) {
                Locale locale = Locale.ENGLISH;
                String language = locale.getLanguage();
                session.setAttribute("locale", locale);
                session.setAttribute("language", language);
            }
            request.getRequestDispatcher(Config.getInstance().getProperty(Config.START_PAGE)).forward(request, response);
        } else {
            request.setAttribute("language", languageCountry);
            request.getRequestDispatcher("/set_locale").forward(request, response);
        }
    }
}