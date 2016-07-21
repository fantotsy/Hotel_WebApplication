package ua.fantotsy.controllers;

import javax.servlet.ServletException;
import java.io.IOException;

public interface ICommand {
    String execute(ISessionRequestWrapper wrapper) throws ServletException, IOException;
}