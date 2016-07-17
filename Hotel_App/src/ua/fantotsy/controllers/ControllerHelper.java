package ua.fantotsy.controllers;

import ua.fantotsy.commands.StartPageCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ControllerHelper {

    private static final String START_PAGE = "/index";
    private static ControllerHelper instance = null;
    private static Map<String, ICommand> commands = new HashMap<>();

    static {
        commands.put("/index", new StartPageCommand());
//        ua.fantotsy.commands.put("/main", new GetMainPageCommand());
//        ua.fantotsy.commands.put("/registration", new GetRegistrationFormCommand());
//        ua.fantotsy.commands.put("/main-admin", new AdminMainPageCommand());
//        ua.fantotsy.commands.put("/main-user", new UserMainPageCommand());
//        ua.fantotsy.commands.put("/date-chosen", new DateChosenCommand());
//        ua.fantotsy.commands.put("/main-user-booked", new BookApartmentsCommand());
//        ua.fantotsy.commands.put("/main-admin-action", new DeleteInsertCommand());
//        ua.fantotsy.commands.put("/deleteinsert", new GetDeleteInsertCommand());
//        ua.fantotsy.commands.put("/getallreservations", new GetAllReservationsCommand());
//        ua.fantotsy.commands.put("/getguests", new GetAllGuestsCommand());
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }

    public ICommand getCommand(HttpServletRequest request) {
        String urlPath = request.getServletPath();
        System.out.println(urlPath);
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