package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DaoFactory;
import ua.fantotsy.entities.Category;
import ua.fantotsy.utils.UrlGetter;

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
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        if (isDeleteOrAddPressed(wrapper)) {
            String categoryId = wrapper.getRequestParameter("category_id");
            int apartmentNumber = Integer.parseInt(wrapper.getRequestParameter("apartment_number"));
            Map<Integer, Integer> mapOfApartments = DaoFactory.getDAOApartment().getAllApartmentNumbers();
            Integer categoryIdOfApartment = mapOfApartments.get(apartmentNumber);
            String pressedButtonName = getPressedButtonName(wrapper);
            if (pressedButtonName.equals("add")) {
                if (categoryIdOfApartment == null) {
                    DaoFactory.getDAOApartment().addApartment(apartmentNumber, Integer.parseInt(categoryId));
                } else {
                    wrapper.setRequestAttribute("error", new String[]{categoryId, "current_apartment_exists"});
                }
            }
            if (pressedButtonName.equals("delete")) {
                if (categoryIdOfApartment != null && (categoryIdOfApartment == Integer.parseInt(categoryId))) {
                    DaoFactory.getDAOApartment().removeApartment(apartmentNumber);
                } else {
                    wrapper.setRequestAttribute("error", new String[]{categoryId, "current_apartment_does_not_exist"});
                }
            }
        }
        List<Category> listOfCategories = DaoFactory.getDAOCategory().getAllCategories();
        wrapper.setRequestAttribute("listOfCategories", listOfCategories);
        return UrlGetter.getInstance().getUrl(UrlGetter.MAIN_ADMIN_APARTMENTS_PAGE);
    }

    private boolean isDeleteOrAddPressed(ISessionRequestWrapper wrapper) {
        String categoryId = wrapper.getRequestParameter("category_id");
        return (categoryId != null);
    }

    private String getPressedButtonName(ISessionRequestWrapper wrapper) {
        if (wrapper.getRequestParameter("add_apartment") != null) {
            return "add";
        } else if (wrapper.getRequestParameter("remove_apartment") != null) {
            return "delete";
        } else {
            return "none";
        }
    }
}