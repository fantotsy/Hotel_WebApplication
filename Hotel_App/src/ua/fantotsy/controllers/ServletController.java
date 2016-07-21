package ua.fantotsy.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ControllerHelper.getInstance().getCommand(request).execute(request, response);
        ICommand command = ControllerHelper.getInstance().getCommand(request);
        System.out.println(command);
        ISessionRequestWrapper wrapper = new SessionRequestWrapper(request);
        HttpSession session = request.getSession(true);
        String viewPage = command.execute(wrapper);
        wrapper.insertAttributes(request);
        request.getRequestDispatcher(viewPage).forward(request,response);
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