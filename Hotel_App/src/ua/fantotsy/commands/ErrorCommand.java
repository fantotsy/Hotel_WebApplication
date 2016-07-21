package ua.fantotsy.commands;

import ua.fantotsy.controllers.ICommand;
import ua.fantotsy.controllers.ISessionRequestWrapper;
import ua.fantotsy.properties.Config;

import javax.servlet.ServletException;
import java.io.IOException;

public class ErrorCommand implements ICommand {
    @Override
    public String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException {
        return Config.getInstance().getProperty(Config.ERROR_PAGE);
    }
}
