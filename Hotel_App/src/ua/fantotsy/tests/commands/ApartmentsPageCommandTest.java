package commands;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.fantotsy.commands.ApartmentsPageCommand;
import ua.fantotsy.controllers.SessionRequestWrapper;
import ua.fantotsy.utils.UrnGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(value = Parameterized.class)
public class ApartmentsPageCommandTest extends Assert {
    private final HashMap<String, String[]> requestParameters = new HashMap<>();
    private final HashMap<String, Object> sessionAttributes = new HashMap<>();

    private final String categoryId;
    private final String apartmentId;
    private final String addApartment;
    private final String removeApartment;

    public ApartmentsPageCommandTest(final String categoryId, final String apartmentId, final String addApartment, final String removeApartment) {
        this.categoryId = categoryId;
        this.apartmentId = apartmentId;
        this.addApartment = addApartment;
        this.removeApartment = removeApartment;
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Utils.setUpClass(ApartmentsPageCommandTest.class.getName());
    }

    @Before
    public void initializeHashMaps() {
        requestParameters.put("category_id", new String[]{categoryId});
        requestParameters.put("apartment_number", new String[]{apartmentId});
        requestParameters.put("add_apartment", new String[]{addApartment});
        requestParameters.put("remove_apartment", new String[]{removeApartment});
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {null, null, null, null},
                {"1", "112", "true", null},
                {"1", "111", null, "true"}
        });
    }

    @Test
    public void testExecute() throws ServletException, IOException {
        SessionRequestWrapper wrapper = new SessionRequestWrapper(requestParameters, sessionAttributes);
        ApartmentsPageCommand command = new ApartmentsPageCommand();
        String result = command.execute(wrapper);
        assertEquals(UrnGetter.getInstance().getUrn(UrnGetter.MAIN_ADMIN_APARTMENTS_PAGE), result);
    }
}