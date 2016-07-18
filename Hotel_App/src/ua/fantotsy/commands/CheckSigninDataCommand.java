package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.datasource.DAOFactory;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.properties.Config;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckSigninDataCommand implements ICommand {
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = Utils.encryptionMD5("admin");
    private static final String ROLE_ADMIN = "admin";
    private static final String ROLE_GUEST = "guest";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enteredLogin = request.getParameter("login");
        String enteredPassword = Utils.encryptionMD5(request.getParameter("password"));
        String isAdmin = request.getParameter("isAdmin");
        HttpSession session = request.getSession(true);

        // Check whether CheckBox 'Admin' was checked.
        if (isAdmin != null && isAdmin.equals("true")) {
            // Check login and password for Admin.
            if (enteredLogin.equals(ADMIN_LOGIN) && enteredPassword.equals(ADMIN_PASSWORD)) {
                session.setAttribute("login", ADMIN_LOGIN);
                //request.setAttribute("password", ADMIN_PASSWORD);
                session.setAttribute("role", ROLE_ADMIN);
                request.getRequestDispatcher("/main_admin").forward(request, response);
            } else {
                wrongEntranceData(request, response);
            }
        } else {
            int validationCode = DAOFactory.getDAOGuest().containsCertainGuest(enteredLogin, enteredPassword);
            if (validationCode == 1) {
                wrongEntranceData(request, response);
            } else {
                Guest guest = DAOFactory.getDAOGuest().getCertainGuest(enteredLogin);
                System.out.println(guest);
                session.setAttribute("guestInfo", guest);
                session.setAttribute("login", enteredLogin);
                //request.setAttribute("password", enteredPassword);
                session.setAttribute("role", ROLE_GUEST);
                request.getRequestDispatcher("/main_guest").forward(request, response);
            }
        }
    }

    private void wrongEntranceData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", "wrong entrance data");
        request.getRequestDispatcher(Config.getInstance().getProperty(Config.START_PAGE)).forward(request, response);
    }
}