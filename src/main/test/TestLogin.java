import net.sourceforge.jwebunit.junit.WebTester;
import org.eclipse.jetty.server.Server;
import org.junit.BeforeClass;

/**
 * Zhannur Diyas
 * 1/11/2017 | 2:58 PM
 */
public class TestLogin {
    private static WebTester tester;
    private static Server server;

    @BeforeClass
    public static void init() throws Exception {
        tester = new WebTester();
        server = new Server(0);

    }
}
