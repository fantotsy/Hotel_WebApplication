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
        // Check whether user already logged in (as admin or as guest).
        String role = (String) wrapper.getSessionAttribute("role");
        if (role != null) {
            if (role.equals(ROLE_ADMIN)) {
                return "/admin";
            } else {
                return "/guest";
            }
        }
        // User is not logged in. Check input data.
        String enteredLogin = wrapper.getRequestParameter("login");
        String enteredPassword = Utils.encryptionMD5(wrapper.getRequestParameter("password"));
        String isAdmin = wrapper.getRequestParameter("isAdmin");

        // Check whether CheckBox 'Admin' was checked.
        if (isAdmin != null && isAdmin.equals("true")) {
            // Check login and password for Admin.
            if (enteredLogin.equals(ADMIN_LOGIN) && enteredPassword.equals(ADMIN_PASSWORD)) {
                wrapper.setSessionAttribute("role", ROLE_ADMIN);
                return "/admin";
            }
            return setErrorMessage(wrapper, "wrong entrance data");
        }

        boolean containsGuest = DAOFactory.getDAOGuest().containsCertainGuest(enteredLogin, enteredPassword);
        if (containsGuest) {
            Guest guest = DAOFactory.getDAOGuest().getCertainGuest(enteredLogin);
            wrapper.setSessionAttribute("guestInfo", guest);
            wrapper.setSessionAttribute("role", ROLE_GUEST);
            return "/guest";
        }
        return setErrorMessage(wrapper, "wrong entrance data");
    }

    private String setErrorMessage(ISessionRequestWrapper wrapper, String errorMessage) {
        wrapper.setRequestAttribute("error", errorMessage);
        return Config.getInstance().getProperty(Config.START_PAGE);
    }
}