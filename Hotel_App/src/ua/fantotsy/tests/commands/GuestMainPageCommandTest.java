package commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.fantotsy.commands.GuestMainPageCommand;
import ua.fantotsy.controllers.SessionRequestWrapper;
import ua.fantotsy.entities.Guest;
import ua.fantotsy.utils.URNsGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(value = Parameterized.class)
public class GuestMainPageCommandTest extends Assert {
    private final HashMap<String, String[]> requestParameters = new HashMap<>();
    private final HashMap<String, Object> sessionAttributes = new HashMap<>();

    private final Guest guestInfoSession;
    private final String reservationId;

    public GuestMainPageCommandTest(Guest guestInfoSession, String reservationId) {
        this.guestInfoSession = guestInfoSession;
        this.reservationId = reservationId;
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Utils.setUpClass(GuestMainPageCommandTest.class.getName());
    }

    @Before
    public void initializeHashMaps() {
        sessionAttributes.put("guestInfo", guestInfoSession);

        requestParameters.put("reservation_id", new String[]{reservationId});
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        Guest guestInfo = new Guest(1, "one", "one", "+380123456789", "user1@mail.com", "user1");
        return Arrays.asList(new Object[][]{
                {guestInfo, null},
                {guestInfo, "1"},
        });
    }

    @Test
    public void testExecute() throws ServletException, IOException {
        SessionRequestWrapper wrapper = new SessionRequestWrapper(requestParameters, sessionAttributes);
        GuestMainPageCommand command = new GuestMainPageCommand();
        String result = command.execute(wrapper);
        assertEquals(URNsGetter.getInstance().getURN(URNsGetter.MAIN_GUEST_PAGE), result);
    }
}