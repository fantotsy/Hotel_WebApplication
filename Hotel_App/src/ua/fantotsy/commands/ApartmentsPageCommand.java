package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;

import javax.servlet.ServletException;
import java.io.IOException;

public class ApartmentsPageCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
return null;
//        String categoryId = request.getParameter("category_id");
//        if (categoryId != null) {
//            int apartmentNumber = Integer.parseInt(request.getParameter("apartment_number"));
//            Map<Integer, Integer> listOfApartments = DAOFactory.getDAOApartment().getAllApartmentNumbers();
//            System.out.println(request.getParameter("add_apartment"));
//            System.out.println(request.getParameter("remove_apartment"));
//            //String command;
//            Integer apartment = listOfApartments.getOrDefault(apartmentNumber, -1);
//            if (request.getParameter("add_apartment") != null) {
//                if (apartment == -1) {
//                    DAOFactory.getDAOApartment().addApartment(apartmentNumber, Integer.parseInt(categoryId));
//                } else {
//                    request.setAttribute("error", new String[]{categoryId.toString(), "current apartment exists"});
//                }
//            }
//            if (request.getParameter("remove_apartment") != null) {
//                if (apartment != -1 && listOfApartments.get(apartmentNumber) == Integer.parseInt(categoryId)) {
//                    DAOFactory.getDAOApartment().removeApartment(apartmentNumber);
//                } else {
//                    request.setAttribute("error", new String[]{categoryId.toString(), "current apartment does not exist"});
//                }
//            }
//        }
//
//        String[] error = (String[]) request.getAttribute("error");
//        List<Category> listOfCategories = DAOFactory.getDAOCategory().getAllCategories();
//
//        request.setAttribute("error", error);
//        request.setAttribute("listOfCategories", listOfCategories);
//        request.getRequestDispatcher(Config.getInstance().getProperty(Config.MAIN_ADMIN_APARTMENTS_PAGE)).forward(request, response);
    }
}