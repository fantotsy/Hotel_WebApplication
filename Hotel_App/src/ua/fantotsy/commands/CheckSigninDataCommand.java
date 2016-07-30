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
 * Class {@code CheckSigninDataCommand} is a command, which implements
 * {@link ICommand} and redirects to another command or page.
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
     * Checks whether user is logged in (as admin or guest).
     * If not, the validation of login and password is made.
     * Then {@code execute} determines whether to let entrance or not.
     *
     * @param wrapper session and request wrapper.
     * @return string for redirection to another page.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
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

    private String setErrorMessage(ISessionRequestWrapper wrapper, String errorMessage) {
        wrapper.setRequestAttribute("error", errorMessage);
        return URNsGetter.getInstance().getURN(URNsGetter.START_PAGE);
    }

    private String setSessionData(ISessionRequestWrapper wrapper, String login) {
        Guest guest = DAOFactory.getDAOGuest().getCertainGuest(login);
        wrapper.setSessionAttribute("guestInfo", guest);
        wrapper.setSessionAttribute("role", ROLE_GUEST);
        return ActionsGetter.getInstance().getAction(ActionsGetter.GUEST);
    }
}