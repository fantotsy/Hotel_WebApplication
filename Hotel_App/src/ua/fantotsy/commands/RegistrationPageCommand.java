package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.utils.URNsGetter;
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
                wrapper.setRequestAttribute("error_login", "login_exists");
                wrapper.setRequestAttribute("guest_data", newGuest);
            } else {
                if (!password.equals(passwordConfirmation)) {
                    wrapper.setRequestAttribute("error_password", "different_password_and_confirmation");
                    wrapper.setRequestAttribute("guest_data", newGuest);
                } else {
                    //All entered data is valid.
                    DAOFactory.getDAOGuest().insertNewGuest(newGuest);
                    wrapper.setRequestAttribute("login", login);
                    wrapper.setRequestAttribute("result", "guest inserted");
                }
            }
        }
        return URNsGetter.getInstance().getURN(URNsGetter.REGISTRATION_PAGE);
    }
}