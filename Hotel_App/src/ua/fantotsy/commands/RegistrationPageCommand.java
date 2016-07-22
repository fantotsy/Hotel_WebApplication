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
        if (register != null && register.equals("pressed")) {
            String login = wrapper.getRequestParameter("login");
            String name = wrapper.getRequestParameter("name");
            String lastName = wrapper.getRequestParameter("surname");
            String password = Utils.encryptionMD5(wrapper.getRequestParameter("password"));
            String phoneNumber = wrapper.getRequestParameter("phone");
            String email = wrapper.getRequestParameter("email");
            Guest newGuest = new Guest(name, lastName, phoneNumber, email, login, password);
            String passwordConfirmation = Utils.encryptionMD5(wrapper.getRequestParameter("password_confirmation"));

            //Check whether 'login' exists and 'password' equals its 'confirmation'.
            boolean containsLogin = DAOFactory.getDAOGuest().containsCertainLogin(login);
            if (containsLogin) {
                setErrorMessage(wrapper, newGuest, "login exists");
            } else {
                if (!password.equals(passwordConfirmation)) {
                    setErrorMessage(wrapper, newGuest, "different password and confirmation");
                } else {
                    //All entered data is valid.
                    DAOFactory.getDAOGuest().insertNewGuest(newGuest);
                    wrapper.setRequestAttribute("login", login);
                    wrapper.setRequestAttribute("password", Utils.decryptionMD5(password));
                    wrapper.setRequestAttribute("result", "guest inserted");
                }
            }
        }
        return Config.getInstance().getProperty(Config.REGISTRATION_PAGE);
    }

    private void setErrorMessage(ISessionRequestWrapper wrapper, Guest newGuest, String errorMessage) {
        wrapper.setRequestAttribute("error", errorMessage);
        wrapper.setRequestAttribute("guest_data", newGuest);
    }
}