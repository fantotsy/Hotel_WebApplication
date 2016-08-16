package commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.fantotsy.commands.RegistrationPageCommand;
import ua.fantotsy.controllers.SessionRequestWrapper;
import ua.fantotsy.utils.UrlGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(value = Parameterized.class)
public class RegistrationPageCommandTest extends Assert {
    private final HashMap<String, String[]> requestParameters = new HashMap<>();
    private final HashMap<String, Object> sessionAttributes = new HashMap<>();

    private final String register;
    private final String login;
    private final String name;
    private final String surname;
    private final String password;
    private final String phone;
    private final String email;
    private final String passwordConfirmation;

    public RegistrationPageCommandTest(String register, String login, String name, String surname, String password, String phone, String email, String passwordConfirmation) {
        this.register = register;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.passwordConfirmation = passwordConfirmation;
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Utils.setUpClass(RegistrationPageCommandTest.class.getName());
    }

    @Before
    public void initializeHashMaps() {
        requestParameters.put("register", new String[]{register});
        requestParameters.put("login", new String[]{login});
        requestParameters.put("name", new String[]{name});
        requestParameters.put("surname", new String[]{surname});
        requestParameters.put("password", new String[]{password});
        requestParameters.put("phone", new String[]{phone});
        requestParameters.put("email", new String[]{email});
        requestParameters.put("password_confirmation", new String[]{passwordConfirmation});
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {null, "user1", "one", "one", "user1", "123456789", "user1@mail.com", "user1"},
                {"pressed", "user1", "one", "one", "user1", "123456789", "user1@mail.com", "user1"},
                {"pressed", "testUser", "test", "test", "testUser", "123456789", "test1@mail.com", "testUser"}
        });
    }

    @Test
    public void testExecute() throws ServletException, IOException {
        SessionRequestWrapper wrapper = new SessionRequestWrapper(requestParameters, sessionAttributes);
        RegistrationPageCommand command = new RegistrationPageCommand();
        String result = command.execute(wrapper);
        assertEquals(UrlGetter.getInstance().getUrl(UrlGetter.REGISTRATION_PAGE), result);
    }
}