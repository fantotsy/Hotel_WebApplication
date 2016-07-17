package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Category;
import ua.fantotsy.properties.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ApartmentsPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] error = (String[]) request.getAttribute("error");
        List<Category> listOfCategories = DAOFactory.getDAOCategory().getAllCategories();

        request.setAttribute("error", error);
        request.setAttribute("listOfCategories", listOfCategories);
        request.getRequestDispatcher(Config.getInstance().getProperty(Config.MAIN_ADMIN_APARTMENTS_PAGE)).forward(request, response);
    }
}