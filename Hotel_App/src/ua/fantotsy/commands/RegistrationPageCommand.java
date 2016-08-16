package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DaoFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.utils.UrlGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Class {@code RegistrationPageCommand} is a command, which implements
 * {@link ICommand} and redirects to registration page.
 *
 * @author fantotsy
 * @version 1.0
 */
public class RegistrationPageCommand implements ICommand {

    /**
     * Checks whether register button was pressed.
     * Moreover, {@code execute} checks whether
     * registration information is valid.
     *
     * @param wrapper session and request wrapper.
     * @return string for redirection to another page.
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        if (isRegisterPressed(wrapper)) {
            String login = wrapper.getRequestParameter("login");
            String name = wrapper.getRequestParameter("name");
            String lastName = wrapper.getRequestParameter("surname");
            String password = Utils.encryptionMD5(wrapper.getRequestParameter("password"));
            String phoneNumber = wrapper.getRequestParameter("phone");
            String email = wrapper.getRequestParameter("email");
            Guest newGuest = new Guest(name, lastName, phoneNumber, email, login);
            String passwordConfirmation = Utils.encryptionMD5(wrapper.getRequestParameter("password_confirmation"));
            if (isLoginAlreadyExists(login)) {
                wrapper.setRequestAttribute("error_login", "login_exists");
                wrapper.setRequestAttribute("guest_data", newGuest);
            } else {
                if (isPasswordEqualsConfirmation(password, passwordConfirmation)) {
                    newGuest.setPassword(password);
                    DaoFactory.getDAOGuest().insertNewGuest(newGuest);
                    wrapper.setRequestAttribute("login", login);
                    wrapper.setRequestAttribute("result", "guest inserted");
                } else {
                    wrapper.setRequestAttribute("error_password", "different_password_and_confirmation");
                    wrapper.setRequestAttribute("guest_data", newGuest);
                }
            }
        }
        return UrlGetter.getInstance().getUrl(UrlGetter.REGISTRATION_PAGE);
    }

    private boolean isRegisterPressed(ISessionRequestWrapper wrapper) {
        String register = wrapper.getRequestParameter("register");
        return (register != null && register.equals("pressed"));
    }

    private boolean isLoginAlreadyExists(String login) {
        return DaoFactory.getDAOGuest().containsCertainLogin(login);
    }

    private boolean isPasswordEqualsConfirmation(String password, String passwordConfirmation) {
        return (password != null && passwordConfirmation != null && password.equals(passwordConfirmation));
    }
}