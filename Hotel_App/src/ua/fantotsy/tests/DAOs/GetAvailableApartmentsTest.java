package DAOs;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.fantotsy.datasource.DaoFactory;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(value = Parameterized.class)
public class GetAvailableApartmentsTest extends Assert {
    private final String arrival;
    private final String departure;
    private final List<String> types;
    private final List<String> capacities;

    public GetAvailableApartmentsTest(String arrival, String departure, List<String> types, List<String> capacities) {
        this.arrival = arrival;
        this.departure = departure;
        this.types = types;
        this.capacities = capacities;
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Utils.setUpClass(GetAvailableApartmentsTest.class.getName());
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"2016-07-27", "2017-07-29", Arrays.asList("simple"), Arrays.asList("2")}
        });
    }

    @Test
    public void testExecute() throws ServletException, IOException {
        Map<Integer, Integer> result = DaoFactory.getDAOApartment().getAvailableApartments(arrival, departure, types, capacities);
        assertEquals(getExpectedMap(), result);
    }

    private Map<Integer, Integer> getExpectedMap() {
        return new HashMap<Integer, Integer>() {{
            put(201, 2);
            put(202, 2);
            put(203, 2);
            put(204, 2);
            put(205, 2);
            put(206, 2);
            put(207, 2);
            put(208, 2);
            put(209, 2);
        }};
    }
}