package ua.fantotsy.commands;

import ua.fantotsy.controllers.ISessionRequestWrapper;

import javax.servlet.ServletException;
import java.io.IOException;

public interface ICommand {
    String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException;
}