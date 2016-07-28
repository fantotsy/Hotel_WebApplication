package commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.fantotsy.commands.CheckOrderDataCommand;
import ua.fantotsy.controllers.SessionRequestWrapper;
import ua.fantotsy.utils.ActionsGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(value = Parameterized.class)
public class CheckOrderDataCommandTest extends Assert {
    private final HashMap<String, String[]> requestParameters = new HashMap<>();
    private final HashMap<String, Object> sessionAttributes = new HashMap<>();

    private final String dateChosenSession;
    private final String dateChosenRequest;
    private final String checkInDate;
    private final String checkOutDate;
    private final String[] apartmentTypes;
    private final String[] apartmentCapacities;
    private final String viewPage;

    public CheckOrderDataCommandTest(String dateChosenSession, String dateChosenRequest, String checkInDate, String checkOutDate, String[] apartmentTypes, String[] apartmentCapacities, String viewPage) {
        this.dateChosenSession = dateChosenSession;
        this.dateChosenRequest = dateChosenRequest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.apartmentTypes = apartmentTypes;
        this.apartmentCapacities = apartmentCapacities;
        this.viewPage = viewPage;
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Utils.setUpClass(CheckOrderDataCommandTest.class.getName());
    }

    @Before
    public void initializeHashMaps() {
        sessionAttributes.put("date_chosen", dateChosenSession);

        requestParameters.put("date_chosen", new String[]{dateChosenRequest});
        requestParameters.put("check-in_date", new String[]{checkInDate});
        requestParameters.put("check-out_date", new String[]{checkOutDate});
        requestParameters.put("apartment_type[]", apartmentTypes);
        requestParameters.put("apartment_capacity[]", apartmentCapacities);
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"true", null, "2016-10-12", "2016-11-12", new String[]{"simple", "lux"}, new String[]{"1", "2"}, ActionsGetter.getInstance().getAction(ActionsGetter.ORDER_VALID)},
                {null, "true", "2016-10-12", "2016-11-12", new String[]{"simple", "lux"}, new String[]{"1", "2"}, ActionsGetter.getInstance().getAction(ActionsGetter.ORDER_VALID)},
                {null, "true", "2016-11-12", "2016-10-12", new String[]{"simple", "lux"}, new String[]{"1", "2"}, ActionsGetter.getInstance().getAction(ActionsGetter.GUEST)},
                {null, "true", "2016-11-12", "2016-10-12", null, new String[]{"1", "2"}, ActionsGetter.getInstance().getAction(ActionsGetter.GUEST)},
                {null, "true", "2016-11-12", "2016-10-12", new String[]{"simple", "lux"}, null, ActionsGetter.getInstance().getAction(ActionsGetter.GUEST)}
        });
    }

    @Test
    public void testExecute() throws ServletException, IOException {
        SessionRequestWrapper wrapper = new SessionRequestWrapper(requestParameters, sessionAttributes);
        CheckOrderDataCommand command = new CheckOrderDataCommand();
        String result = command.execute(wrapper);
        assertEquals(viewPage, result);
    }
}