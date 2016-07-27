package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.utils.URNsGetter;

import javax.servlet.ServletException;
import java.io.IOException;

public class AdminMainPageCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        return URNsGetter.getInstance().getURN(URNsGetter.MAIN_ADMIN_PAGE);
    }
}
