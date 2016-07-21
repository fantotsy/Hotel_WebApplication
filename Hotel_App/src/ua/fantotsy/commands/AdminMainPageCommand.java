package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;

import javax.servlet.ServletException;
import java.io.IOException;

public class AdminMainPageCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        return null;
        //request.getRequestDispatcher(Config.getInstance().getProperty(Config.MAIN_ADMIN_PAGE)).forward(request, response);
    }
}
