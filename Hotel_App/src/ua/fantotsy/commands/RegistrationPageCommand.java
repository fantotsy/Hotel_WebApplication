package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.properties.Config;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;

public class RegistrationPageCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {

        //Check whether 'register' button was pressed.
        String register = wrapper.getRequestParameter("register");
        boolean isPressed = true;
        if (register == null || !register.equals("pressed")) {
            isPressed = false;
        }

        if (isPressed) {
            String login = wrapper.getRequestParameter("login");
            String name = wrapper.getRequestParameter("name");
            String lastName = wrapper.getRequestParameter("surname");
            String password = Utils.encryptionMD5(wrapper.getRequestParameter("password"));
            String phoneNumber = wrapper.getRequestParameter("phone");
            String email = wrapper.getRequestParameter("email");
            Guest newGuest = new Guest(name, lastName, phoneNumber, email, login, password);
            String passwordConfirmation = Utils.encryptionMD5(wrapper.getRequestParameter("password_confirmation"));

            int validationResult = dataValidation(newGuest, passwordConfirmation);
            if (validationResult == 1) {
                wrapper.setRequestAttribute("error", "login exists");
                wrapper.setRequestAttribute("guest_data", newGuest);
            } else {
                if (validationResult == 2) {
                    wrapper.setRequestAttribute("error", "different password and confirmation");
                    wrapper.setRequestAttribute("guest_data", newGuest);
                } else {
                    DAOFactory.getDAOGuest().insertNewGuest(newGuest);
                    wrapper.setRequestAttribute("login", login);
                    wrapper.setRequestAttribute("password", Utils.decryptionMD5(password));
                    wrapper.setRequestAttribute("result", "guest inserted");
                }
            }
        }
        return Config.getInstance().getProperty(Config.REGISTRATION_PAGE);
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