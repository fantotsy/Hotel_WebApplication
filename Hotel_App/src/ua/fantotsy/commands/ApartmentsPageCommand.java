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
import java.util.Map;

public class ApartmentsPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("category_id");
        if (categoryId != null) {
            int apartmentNumber = Integer.parseInt(request.getParameter("apartment_number"));
            Map<Integer, Integer> listOfApartments = DAOFactory.getDAOApartment().getAllApartmentNumbers();
            System.out.println(request.getParameter("add_apartment"));
            System.out.println(request.getParameter("remove_apartment"));
            //String command;
            Integer apartment = listOfApartments.getOrDefault(apartmentNumber, -1);
            if (request.getParameter("add_apartment") != null) {
                if (apartment == -1) {
                    DAOFactory.getDAOApartment().addApartment(apartmentNumber, Integer.parseInt(categoryId));
                } else {
                    request.setAttribute("error", new String[]{categoryId.toString(), "current apartment exists"});
                }
            }
            if (request.getParameter("remove_apartment") != null) {
                if (apartment != -1 && listOfApartments.get(apartmentNumber) == Integer.parseInt(categoryId)) {
                    DAOFactory.getDAOApartment().removeApartment(apartmentNumber);
                } else {
                    request.setAttribute("error", new String[]{categoryId.toString(), "current apartment does not exist"});
                }
            }
        }

        String[] error = (String[]) request.getAttribute("error");
        List<Category> listOfCategories = DAOFactory.getDAOCategory().getAllCategories();

        request.setAttribute("error", error);
        request.setAttribute("listOfCategories", listOfCategories);
        request.getRequestDispatcher(Config.getInstance().getProperty(Config.MAIN_ADMIN_APARTMENTS_PAGE)).forward(request, response);
    }
}