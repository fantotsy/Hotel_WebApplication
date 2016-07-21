package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Category;
import ua.fantotsy.properties.Config;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApartmentsPageCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        String categoryId = wrapper.getRequestParameter("category_id");
        if (categoryId != null) {
            int apartmentNumber = Integer.parseInt(wrapper.getRequestParameter("apartment_number"));
            Map<Integer, Integer> listOfApartments = DAOFactory.getDAOApartment().getAllApartmentNumbers();
            System.out.println(wrapper.getRequestParameter("add_apartment"));
            System.out.println(wrapper.getRequestParameter("remove_apartment"));
            //String command;
            Integer apartment = listOfApartments.getOrDefault(apartmentNumber, -1);
            if (wrapper.getRequestParameter("add_apartment") != null) {
                if (apartment == -1) {
                    DAOFactory.getDAOApartment().addApartment(apartmentNumber, Integer.parseInt(categoryId));
                } else {
                    wrapper.setRequestAttribute("error", new String[]{categoryId.toString(), "current apartment exists"});
                }
            }
            if (wrapper.getRequestParameter("remove_apartment") != null) {
                if (apartment != -1 && listOfApartments.get(apartmentNumber) == Integer.parseInt(categoryId)) {
                    DAOFactory.getDAOApartment().removeApartment(apartmentNumber);
                } else {
                    wrapper.setRequestAttribute("error", new String[]{categoryId.toString(), "current apartment does not exist"});
                }
            }
        }

        List<Category> listOfCategories = DAOFactory.getDAOCategory().getAllCategories();

        wrapper.setRequestAttribute("listOfCategories", listOfCategories);
        return Config.getInstance().getProperty(Config.MAIN_ADMIN_APARTMENTS_PAGE);
    }
}