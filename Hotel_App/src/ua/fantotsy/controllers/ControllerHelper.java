package ua.fantotsy.controllers;

import ua.fantotsy.commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ControllerHelper {

    private static final String START_PAGE = "/index";
    private static ControllerHelper instance = null;
    private static Map<String, ICommand> commands = new HashMap<>();

    static {
        commands.put("/index", new StartPageCommand());
        commands.put("/registration", new RegistrationPageCommand());
        commands.put("/main", new CheckSigninDataCommand());
        commands.put("/main_guest", new GuestMainPageCommand());
        commands.put("/main_admin", new AdminMainPageCommand());
        commands.put("/main_admin_apartments", new ApartmentsPageCommand());
        commands.put("/main_admin_reservations", new ReservationsPageCommand());
        commands.put("/main_admin_guests", new GuestsPageCommand());
        commands.put("/booking", new CheckOrderDataCommand());
        commands.put("/order_valid", new GuestBookingPageCommand());
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }

    public ICommand getCommand(HttpServletRequest request) {
        String urlPath = request.getServletPath();

        if (urlPath == null) {
            return commands.get(START_PAGE);
        }

        ICommand command = commands.get(urlPath);
        if (command == null) {
            System.out.println("This command does not exist!");
            return commands.get(START_PAGE);
        }

        return command;
    }
}