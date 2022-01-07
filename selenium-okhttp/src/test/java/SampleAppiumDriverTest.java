import io.appium.java_client.AppiumDriver;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class SampleAppiumDriverTest {

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

            MutableCapabilities caps = new MutableCapabilities();
            caps.setCapability("platformName", "iOS");
            caps.setCapability("browserName", "Safari");
            caps.setCapability("appium:deviceName", "iPhone 13 Simulator");
            caps.setCapability("appium:platformVersion", "15.0");
            MutableCapabilities sauceOptions = new MutableCapabilities();
            sauceOptions.setCapability("appiumVersion", "1.22.0");
            caps.setCapability("sauce:options", sauceOptions);

            AppiumDriver driver = new AppiumDriver(executor, caps);

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
