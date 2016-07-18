package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.properties.Config;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Check whether 'register' button was pressed.
        String register = request.getParameter("register");
        boolean isPressed = true;
        if (register == null || !register.equals("pressed")) {
            isPressed = false;
        }

        if (isPressed) {
            String login = request.getParameter("login");
            String name = request.getParameter("name");
            String lastName = request.getParameter("surname");
            String password = Utils.encryptionMD5(request.getParameter("password"));
            String phoneNumber = request.getParameter("phone");
            String email = request.getParameter("email");
            Guest newGuest = new Guest(name, lastName, phoneNumber, email, login, password);
            String passwordConfirmation = Utils.encryptionMD5(request.getParameter("password_confirmation"));

            int validationResult = dataValidation(newGuest, passwordConfirmation);
            if (validationResult == 1) {
                request.setAttribute("error", "login exists");
                request.setAttribute("guest_data", newGuest);
            } else {
                if (validationResult == 2) {
                    request.setAttribute("error", "different password and confirmation");
                    request.setAttribute("guest_data", newGuest);
                } else {
                    DAOFactory.getDAOGuest().insertNewGuest(newGuest);
                    request.setAttribute("login", login);
                    request.setAttribute("password", Utils.decryptionMD5(password));
                    request.setAttribute("result", "guest inserted");
                }
            }
        }
        request.getRequestDispatcher(Config.getInstance().getProperty(Config.REGISTRATION_PAGE)).forward(request, response);
    }

    /*
        Method returns:
            0 if data is valid;
            1 if current login exists in data base;
            2 if password and confirmation of password are different.
     */
    private int dataValidation(Guest guest, String passwordConfirmation) {
        int containsLogin = DAOFactory.getDAOGuest().containsCertainLogin(guest.getLogin());
        if (containsLogin == 1) {
            guest.setLogin(null);
            return 1;
        }
        if (!guest.getPassword().equals(passwordConfirmation)) {
            guest.setPassword(null);
            return 2;
        }
        return 0;
    }
}