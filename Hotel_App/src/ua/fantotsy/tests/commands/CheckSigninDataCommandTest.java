package commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.fantotsy.commands.CheckSignInDataCommand;
import ua.fantotsy.controllers.SessionRequestWrapper;
import ua.fantotsy.utils.ActionsGetter;
import ua.fantotsy.utils.UrnGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(value = Parameterized.class)
public class CheckSigninDataCommandTest extends Assert {
    private final HashMap<String, String[]> requestParameters = new HashMap<>();
    private final HashMap<String, Object> sessionAttributes = new HashMap<>();

    private final String roleSession;
    private final String justRegistered;
    private final String login;
    private final String password;
    private final String isAdmin;
    private final String viewPage;

    public CheckSigninDataCommandTest(String roleSession, String justRegistered, String login, String password, String isAdmin, String viewPage) {
        this.roleSession = roleSession;
        this.justRegistered = justRegistered;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
        this.viewPage = viewPage;
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Utils.setUpClass(CheckSigninDataCommandTest.class.getName());
    }

    @Before
    public void initializeHashMaps() {
        sessionAttributes.put("role", roleSession);

        requestParameters.put("just_registered", new String[]{justRegistered});
        requestParameters.put("login", new String[]{login});
        requestParameters.put("password", new String[]{password});
        requestParameters.put("isAdmin", new String[]{isAdmin});
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"admin", null, null, null, null, ActionsGetter.getInstance().getAction(ActionsGetter.ADMIN)},
                {"guest", null, null, null, null, ActionsGetter.getInstance().getAction(ActionsGetter.GUEST)},
                {null, "true", "user1", null, null, ActionsGetter.getInstance().getAction(ActionsGetter.GUEST)},
                {null, null, "admin", "admin", "true", ActionsGetter.getInstance().getAction(ActionsGetter.ADMIN)},
                {null, null, "wrong_login", "wrong_password", "true", UrnGetter.getInstance().getUrn(UrnGetter.START_PAGE)},
                {null, null, "user1", "user1", null, ActionsGetter.getInstance().getAction(ActionsGetter.GUEST)},
                {null, null, "wrong_login", "wrong_password", null, UrnGetter.getInstance().getUrn(UrnGetter.START_PAGE)}
        });
    }

    @Test
    public void testExecute() throws ServletException, IOException {
        SessionRequestWrapper wrapper = new SessionRequestWrapper(requestParameters, sessionAttributes);
        CheckSignInDataCommand command = new CheckSignInDataCommand();
        String result = command.execute(wrapper);
        assertEquals(viewPage, result);
    }
}