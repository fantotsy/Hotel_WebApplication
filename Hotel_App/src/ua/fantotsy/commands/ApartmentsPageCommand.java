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
 * Class {@code ApartmentsPageCommand} is a command,
 * which implements {@link ICommand} and redirects to apartments page.
 *
 * @author fantotsy
 * @version 1.0
 */
public class ApartmentsPageCommand implements ICommand {

    /**
     * Checks whether 'Add' or 'Delete' button was pressed or
     * some logic error occurred and appends {@code wrapper} if needed.
     *
     * @param wrapper session and request wrapper.
     * @return string for redirection to another page.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
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