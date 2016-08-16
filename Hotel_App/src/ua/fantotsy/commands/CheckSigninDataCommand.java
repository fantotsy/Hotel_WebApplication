package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.datasource.DaoFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.utils.ActionsGetter;
import ua.fantotsy.utils.UrlGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Class {@code CheckSignInDataCommand} is a command, which implements
 * {@link ICommand} and redirects to another command or page.
 *
 * @author fantotsy
 * @version 1.0
 */
public class CheckSignInDataCommand implements ICommand {
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
        } else {
            if (isRedirectedFromRegistrationPage(wrapper)) {
                String login = wrapper.getRequestParameter("login");
                setSessionData(wrapper, login);
                return ActionsGetter.getInstance().getAction(ActionsGetter.GUEST);
            } else {
                String enteredLogin = wrapper.getRequestParameter("login");
                String enteredPassword = Utils.encryptionMD5(wrapper.getRequestParameter("password"));
                String isAdmin = wrapper.getRequestParameter("isAdmin");
                if (isAdminCheckboxChecked(isAdmin)) {
                    if (isAdminCredentialsValid(enteredLogin, enteredPassword)) {
                        wrapper.setSessionAttribute("role", ROLE_ADMIN);
                        setAntiCsrfToken(wrapper);
                        return ActionsGetter.getInstance().getAction(ActionsGetter.ADMIN);
                    } else {
                        return setErrorMessage(wrapper, "wrong_entrance_data");
                    }
                } else {
                    if (isGuestCredentialsValid(enteredLogin, enteredPassword)) {
                        setSessionData(wrapper, enteredLogin);
                        return ActionsGetter.getInstance().getAction(ActionsGetter.GUEST);
                    } else {
                        return setErrorMessage(wrapper, "wrong_entrance_data");
                    }
                }
            }
        }
    }

    private boolean isRedirectedFromRegistrationPage(ISessionRequestWrapper wrapper) {
        String justRegistered = wrapper.getRequestParameter("just_registered");
        return (justRegistered != null && justRegistered.equals("true"));
    }

    private boolean isAdminCheckboxChecked(String isAdmin) {
        return (isAdmin != null && isAdmin.equals("true"));
    }

    private boolean isAdminCredentialsValid(String login, String password) {
        return (login.equals(ADMIN_LOGIN) && password.equals(ADMIN_PASSWORD));
    }

    private String setErrorMessage(ISessionRequestWrapper wrapper, String errorMessage) {
        wrapper.setRequestAttribute("error", errorMessage);
        return UrlGetter.getInstance().getUrl(UrlGetter.START_PAGE);
    }

    private boolean isGuestCredentialsValid(String login, String password) {
        return DaoFactory.getDAOGuest().containsCertainGuest(login, password);
    }

    private void setSessionData(ISessionRequestWrapper wrapper, String login) {
        Guest guest = DaoFactory.getDAOGuest().getCertainGuest(login);
        wrapper.setSessionAttribute("guestInfo", guest);
        wrapper.setSessionAttribute("role", ROLE_GUEST);
        setAntiCsrfToken(wrapper);
    }

    private void setAntiCsrfToken(ISessionRequestWrapper wrapper){
        String sessionId = wrapper.getSessionId();
        String antiCsrfToken = Utils.encryptionMD5(sessionId);
        wrapper.setSessionAttribute("antiCsrfToken", antiCsrfToken);
    }
}