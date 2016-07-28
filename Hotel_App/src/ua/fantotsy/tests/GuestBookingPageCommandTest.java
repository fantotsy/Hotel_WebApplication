import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.fantotsy.commands.GuestBookingPageCommand;
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
public class GuestBookingPageCommandTest extends Assert {
    private final HashMap<String, String[]> requestParameters = new HashMap<>();
    private final HashMap<String, Object> sessionAttributes = new HashMap<>();

    private final String arrivalSession;
    private final String departureSession;
    private final List<String> types;
    private final List<String> capacities;
    private final Guest guestInfo;
    private final String categoryId;
    private final String[] bookedApartments;

    public GuestBookingPageCommandTest(String arrivalSession, String departureSession, List<String> types, List<String> capacities, Guest guestInfo, String categoryId, String[] bookedApartments) {
        this.arrivalSession = arrivalSession;
        this.departureSession = departureSession;
        this.types = types;
        this.capacities = capacities;
        this.guestInfo = guestInfo;
        this.categoryId = categoryId;
        this.bookedApartments = bookedApartments;
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Utils.setUpClass(GuestBookingPageCommandTest.class.getName());
    }

    @Before
    public void initializeHashMaps() {
        sessionAttributes.put("arrival", arrivalSession);
        sessionAttributes.put("departure", departureSession);
        sessionAttributes.put("types", types);
        sessionAttributes.put("capacities", capacities);
        sessionAttributes.put("guestInfo", guestInfo);

        requestParameters.put("category_id", new String[]{categoryId});
        requestParameters.put("booked_apartments[]", bookedApartments);
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        Guest guestInfo = new Guest(1, "one", "one", "+380123456789", "user1@mail.com", "user1");
        return Arrays.asList(new Object[][]{
                {"2016-10-12", "2016-11-12", Arrays.asList("simple", "lux"), Arrays.asList("1", "2"), guestInfo, "1", null},
                {"2016-10-12", "2016-11-12", Arrays.asList("simple", "lux"), Arrays.asList("1", "2"), guestInfo, "1", new String[]{"101", "102"}}
        });
    }

    @Test
    public void testExecute() throws ServletException, IOException {
        SessionRequestWrapper wrapper = new SessionRequestWrapper(requestParameters, sessionAttributes);
        GuestBookingPageCommand command = new GuestBookingPageCommand();
        String result = command.execute(wrapper);
        assertEquals(URNsGetter.getInstance().getURN(URNsGetter.MAIN_GUEST_BOOKING_PAGE), result);
    }
}