package commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.fantotsy.commands.StartPageCommand;
import ua.fantotsy.controllers.SessionRequestWrapper;
import ua.fantotsy.utils.URNsGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(value = Parameterized.class)
public class StartPageCommandTest extends Assert {
    private final HashMap<String, String[]> requestParameters = new HashMap<>();
    private final HashMap<String, Object> sessionAttributes = new HashMap<>();

    private final String languageSession;
    private final String logout;
    private final String language;
    private final Boolean isSessionInvalidated;
    private final String viewPage;

    public StartPageCommandTest(String languageSession, String logout, String language, Boolean isSessionInvalidated, String viewPage) {
        this.languageSession = languageSession;
        this.logout = logout;
        this.language = language;
        this.isSessionInvalidated = isSessionInvalidated;
        this.viewPage = viewPage;
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Utils.setUpClass(StartPageCommandTest.class.getName());
    }

    @Before
    public void initializeHashMaps() {
        sessionAttributes.put("language", languageSession);

        requestParameters.put("logout", new String[]{logout});
        requestParameters.put("language", new String[]{language});
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {null, null, null, false, URNsGetter.getInstance().getURN(URNsGetter.START_PAGE)},
                {null, null, null, null, URNsGetter.getInstance().getURN(URNsGetter.START_PAGE)},
                {"ua", null, null, null, URNsGetter.getInstance().getURN(URNsGetter.START_PAGE)},
                {"en", null, null, null, URNsGetter.getInstance().getURN(URNsGetter.START_PAGE)},
                {null, null, "ua_UA", null, URNsGetter.getInstance().getURN(URNsGetter.START_PAGE)},
                {null, null, "en_US", false, URNsGetter.getInstance().getURN(URNsGetter.START_PAGE)},
                {null, null, null, true, URNsGetter.getInstance().getURN(URNsGetter.START_PAGE)},
                {null, "true", null, false, "session_invalidate"}

        });
    }

    @Test
    public void testExecuteSessionNotInvalidated() throws ServletException, IOException {
        SessionRequestWrapper wrapper = new SessionRequestWrapper(requestParameters, sessionAttributes);
        wrapper.setSessionInvalidated(isSessionInvalidated);
        StartPageCommand command = new StartPageCommand();
        String result = command.execute(wrapper);
        assertEquals(viewPage, result);
    }
}