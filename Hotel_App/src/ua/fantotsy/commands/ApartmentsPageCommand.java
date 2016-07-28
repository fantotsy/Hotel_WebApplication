package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Category;
import ua.fantotsy.utils.URNsGetter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Command which is created by pressing 'Apartments' button, which is located
 * in 'web/WEB-INF/jsp/main_admin.jsp'. This command is subscribed for action '/apartments'.
 *
 * @author fantotsy
 * @version 1.0
 */

public class ApartmentsPageCommand implements ICommand {
    /**
     * This method checks whether user got here from 'web/WEB-INF/jsp/main_admin.jsp' or from
     * 'web/WEB-INF/jsp/main_admin_apartments.jsp'. Then further action is determined:
     * to redirect on 'web/WEB-INF/jsp/main_admin.jsp' or to process either 'Add' or 'Delete' button
     * respectively.
     * The result of pressing 'Add' button is insertion of new apartment into data base.
     * The result of pressing 'Delete' button is extraction of existing apartment from data base.
     *
     * @param wrapper request wrapper.
     * @return string, which is used in {@link ua.fantotsy.controllers.ServletController} to
     * define where to redirect current request and response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        // Check whether 'delete' or 'add' button was pressed.
        String categoryId = wrapper.getRequestParameter("category_id");
        if (categoryId != null) {
            int apartmentNumber = Integer.parseInt(wrapper.getRequestParameter("apartment_number"));
            Map<Integer, Integer> mapOfApartments = DAOFactory.getDAOApartment().getAllApartmentNumbers();

            Integer apartment = mapOfApartments.getOrDefault(apartmentNumber, -1);
            // Check which button was pressed.
            if (wrapper.getRequestParameter("add_apartment") != null) {
                if (apartment == -1) {
                    DAOFactory.getDAOApartment().addApartment(apartmentNumber, Integer.parseInt(categoryId));
                } else {
                    wrapper.setRequestAttribute("error", new String[]{categoryId, "current_apartment_exists"});
                }
            }
            if (wrapper.getRequestParameter("remove_apartment") != null) {
                if (apartment != -1 && mapOfApartments.get(apartmentNumber) == Integer.parseInt(categoryId)) {
                    DAOFactory.getDAOApartment().removeApartment(apartmentNumber);
                } else {
                    wrapper.setRequestAttribute("error", new String[]{categoryId, "current_apartment_does_not_exist"});
                }
            }
        }

        List<Category> listOfCategories = DAOFactory.getDAOCategory().getAllCategories();

        wrapper.setRequestAttribute("listOfCategories", listOfCategories);
        return URNsGetter.getInstance().getURN(URNsGetter.MAIN_ADMIN_APARTMENTS_PAGE);
    }
}