import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class SampleRemoteWebDriverTest {

    public static final String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
    public static final String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    public static final String SAUCE_URL = String.format("https://%s:%s@ondemand.us-west-1.saucelabs.com/wd/hub",SAUCE_USERNAME,SAUCE_ACCESS_KEY);



    @Test
    public void myTest() throws MalformedURLException {

//        Enable these to proxy all traffic
//        System.setProperty("http.proxyHost", "localhost");
//        System.setProperty("https.proxyHost", "localhost");
//        System.setProperty("http.proxyPort", "8889");
//        System.setProperty("https.proxyPort", "8889");

        try {


            HTTPFactory factory = new HTTPFactory();
            HttpCommandExecutor executor = new HttpCommandExecutor(Collections.emptyMap(), new URL(SAUCE_URL), factory);

            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setCapability("platformName", "Windows 10");
            browserOptions.setCapability("browserVersion", "latest");
            Map<String, Object> sauceOptions = new HashMap<>();
            browserOptions.setCapability("sauce:options", sauceOptions);

            RemoteWebDriver driver = new RemoteWebDriver(executor, browserOptions);

            driver.get("https://saucedemo.com");

            WebElement user = driver.findElementById("user-name");
            user.sendKeys("test-user");

            driver.getPageSource();

            driver.quit();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        }

    }


}
