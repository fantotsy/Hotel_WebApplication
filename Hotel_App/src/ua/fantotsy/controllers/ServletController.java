package ua.fantotsy.controllers;

import ua.fantotsy.commands.ICommand;
import ua.fantotsy.utils.ActionsGetter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class {@code ServletController}, which extends {@link HttpServlet},
 * is a only one servlet, which reacts on every action from jsp pages.
 *
 * @author fantotsy
 * @version 1.0
 */
public class ServletController extends HttpServlet {

    /**
     * Gets command with the help of {@link CommandGetter} and creates
     * an instance of {@link SessionRequestWrapper}.
     * The {@link ICommand#execute(ISessionRequestWrapper)} method returns a string,
     * which is used to determine the redirection.
     *
     * @param request  instance of {@link HttpServletRequest}.
     * @param response instance of {@link HttpServletResponse}.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ICommand command = CommandGetter.getInstance().getCommand(request);
        ISessionRequestWrapper wrapper = new SessionRequestWrapper(request);
        String viewPage = command.execute(wrapper);
        if (viewPage.equals("session_invalidate")) {
            HttpSession session = request.getSession(true);
            session.invalidate();
            request.setAttribute("isSessionInvalidated", "true");
            viewPage = ActionsGetter.getInstance().getAction(ActionsGetter.INDEX);
        } else {
            wrapper.insertAttributes(request);
        }
        request.getRequestDispatcher(viewPage).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}