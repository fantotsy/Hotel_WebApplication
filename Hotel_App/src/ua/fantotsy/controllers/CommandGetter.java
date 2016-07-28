package ua.fantotsy.controllers;

import ua.fantotsy.commands.*;
import ua.fantotsy.utils.ActionsGetter;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to get commands by request.
 * Every command is subscribed for a certain action.
 *
 * @author fantotsy
 * @version 1.0
 */

public class CommandGetter {
    private static CommandGetter instance = null;
    private Map<String, ICommand> commands;

    private CommandGetter() {
        commands = new HashMap<String, ICommand>() {{
            put(ActionsGetter.getInstance().getAction(ActionsGetter.INDEX), new StartPageCommand());
            put(ActionsGetter.getInstance().getAction(ActionsGetter.REGISTRATION), new RegistrationPageCommand());
            put(ActionsGetter.getInstance().getAction(ActionsGetter.MAIN), new CheckSigninDataCommand());
            put(ActionsGetter.getInstance().getAction(ActionsGetter.GUEST), new GuestMainPageCommand());
            put(ActionsGetter.getInstance().getAction(ActionsGetter.ADMIN), new AdminMainPageCommand());
            put(ActionsGetter.getInstance().getAction(ActionsGetter.APARTMENTS), new ApartmentsPageCommand());
            put(ActionsGetter.getInstance().getAction(ActionsGetter.RESERVATIONS), new ReservationsPageCommand());
            put(ActionsGetter.getInstance().getAction(ActionsGetter.GUESTS), new GuestsPageCommand());
            put(ActionsGetter.getInstance().getAction(ActionsGetter.BOOKING), new CheckOrderDataCommand());
            put(ActionsGetter.getInstance().getAction(ActionsGetter.ORDER_VALID), new GuestBookingPageCommand());
            put(ActionsGetter.getInstance().getAction(ActionsGetter.ERROR), new ErrorCommand());
        }};
    }

    public static CommandGetter getInstance() {
        if (instance == null) {
            instance = new CommandGetter();
        }
        return instance;
    }

    /**
     * This method determines which command corresponds to a specific request and returns this command.
     *
     * @param request instance of {@link HttpServletRequest}.
     * @return command, which is instance of {@link ICommand}.
     */
    public ICommand getCommand(HttpServletRequest request) {
        final String START_PAGE = ActionsGetter.getInstance().getAction(ActionsGetter.INDEX);
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