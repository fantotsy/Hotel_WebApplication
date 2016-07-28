package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.utils.ActionsGetter;
import ua.fantotsy.utils.URNsGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Command which is created by pressing 'Sign in' button, which is located
 * in 'web/WEB-INF/jsp/index.jsp'. This command is subscribed for action '/main'.
 *
 * @author fantotsy
 * @version 1.0
 */

public class CheckSigninDataCommand implements ICommand {
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = Utils.encryptionMD5("admin");
    private static final String ROLE_ADMIN = "admin";
    private static final String ROLE_GUEST = "guest";

    /**
     * This method checked whether user is logged in. If not, the validation of login and password is made.
     * Then method determines whether to let entrance or decline it.
     *
     * @param wrapper request wrapper.
     * @return string, which is used in {@link ua.fantotsy.controllers.ServletController} to
     * define where to redirect current request and response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        // Check whether user already logged in (as admin or as guest).
        String role = (String) wrapper.getSessionAttribute("role");
        if (role != null) {
            if (role.equals(ROLE_ADMIN)) {
                return ActionsGetter.getInstance().getAction(ActionsGetter.ADMIN);
            } else {
                return ActionsGetter.getInstance().getAction(ActionsGetter.GUEST);
            }
        }

        // Check whether guest is redirected from 'registration' page.
        String justRegistered = wrapper.getRequestParameter("just_registered");
        if (justRegistered != null && justRegistered.equals("true")) {
            String login = wrapper.getRequestParameter("login");
            return setSessionData(wrapper, login);
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
                return ActionsGetter.getInstance().getAction(ActionsGetter.ADMIN);
            }
            return setErrorMessage(wrapper, "wrong_entrance_data");
        }

        boolean containsGuest = DAOFactory.getDAOGuest().containsCertainGuest(enteredLogin, enteredPassword);
        if (containsGuest) {
            return setSessionData(wrapper, enteredLogin);
        }
        return setErrorMessage(wrapper, "wrong_entrance_data");
    }

    /**
     * This method inserts an error message into request using {@link ua.fantotsy.controllers.SessionRequestWrapper}.
     *
     * @param wrapper request wrapper.
     * @param errorMessage a type of message.
     * @return string, which is used in {@link ua.fantotsy.controllers.ServletController} to
     * define where to redirect current request and response.
     */
    private String setErrorMessage(ISessionRequestWrapper wrapper, String errorMessage) {
        wrapper.setRequestAttribute("error", errorMessage);
        return URNsGetter.getInstance().getURN(URNsGetter.START_PAGE);
    }

    /**
     * This method creates {@link ua.fantotsy.entities.Guest} with certain login and inserts it into session.
     * The data gets from data base with the help of {@link ua.fantotsy.DAOs.DAOGuest}.
     *
     * @param wrapper request wrapper.
     * @param login guest's login.
     * @return string, which is used in {@link ua.fantotsy.controllers.ServletController} to
     * define where to redirect current request and response.
     */
    private String setSessionData(ISessionRequestWrapper wrapper, String login) {
        Guest guest = DAOFactory.getDAOGuest().getCertainGuest(login);
        wrapper.setSessionAttribute("guestInfo", guest);
        wrapper.setSessionAttribute("role", ROLE_GUEST);
        return ActionsGetter.getInstance().getAction(ActionsGetter.GUEST);
    }
}