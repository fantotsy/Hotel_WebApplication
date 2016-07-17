package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.properties.Config;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GuestMainPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] dateLimits = Utils.getDateLimits();
        System.out.println(dateLimits[0]);
        System.out.println(dateLimits[1]);
        List<String> listOfTypes = DAOFactory.getDAOCategory().getAllTypes();
        List<Integer> listOfCapacities = DAOFactory.getDAOCategory().getAllCapacities();
        request.setAttribute("today", dateLimits[0]);
        request.setAttribute("year_later", dateLimits[1]);
        request.setAttribute("listOfTypes", listOfTypes);
        request.setAttribute("listOfCapacities", listOfCapacities);
        request.getRequestDispatcher(Config.getInstance().getProperty(Config.MAIN_GUEST_PAGE))
                .forward(request, response);
    }
}