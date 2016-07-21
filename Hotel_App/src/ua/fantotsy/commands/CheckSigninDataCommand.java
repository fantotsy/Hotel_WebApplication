package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.properties.Config;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;

public class CheckSigninDataCommand implements ICommand {
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = Utils.encryptionMD5("admin");
    private static final String ROLE_ADMIN = "admin";
    private static final String ROLE_GUEST = "guest";

    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        String role = (String) wrapper.getSessionAttribute("role");
        if (role != null && role.equals(ROLE_ADMIN)) {
            return "/admin";
        } else if (role != null && role.equals(ROLE_GUEST)) {
            return "/guest";
        } else {
            String enteredLogin = wrapper.getRequestParameter("login");
            String enteredPassword = Utils.encryptionMD5(wrapper.getRequestParameter("password"));
            String isAdmin = wrapper.getRequestParameter("isAdmin");

            // Check whether CheckBox 'Admin' was checked.
            if (isAdmin != null && isAdmin.equals("true")) {
                // Check login and password for Admin.
                if (enteredLogin.equals(ADMIN_LOGIN) && enteredPassword.equals(ADMIN_PASSWORD)) {
                    wrapper.setSessionAttribute("login", ADMIN_LOGIN);
                    wrapper.setSessionAttribute("role", ROLE_ADMIN);
                    return "/admin";
                } else {
                    wrapper.setRequestAttribute("error", "wrong entrance data");
                    return Config.getInstance().getProperty(Config.START_PAGE);
                }
            } else {
                int validationCode = DAOFactory.getDAOGuest().containsCertainGuest(enteredLogin, enteredPassword);
                if (validationCode == 1) {
                    wrapper.setRequestAttribute("error", "wrong entrance data");
                    return Config.getInstance().getProperty(Config.START_PAGE);
                } else {
                    Guest guest = DAOFactory.getDAOGuest().getCertainGuest(enteredLogin);
                    System.out.println(guest);
                    wrapper.setSessionAttribute("guestInfo", guest);
                    wrapper.setSessionAttribute("login", enteredLogin);
                    wrapper.setSessionAttribute("role", ROLE_GUEST);
                    return "/guest";
                }
            }
        }
    }
}