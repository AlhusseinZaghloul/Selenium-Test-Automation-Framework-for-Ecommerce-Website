package drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;

public class BrowserFactory {
    public static WebDriver getBrowser(String browserName) { // no usages

        switch (browserName.toLowerCase()) {

            case "chrome":
                ChromeOptions options = getChromeOptions();
                return new ChromeDriver(options);

            case "firefox":
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                return new FirefoxDriver(firefoxOptions);

            default:
                EdgeOptions edgeOptions = getEdgeOptions();
                return new EdgeDriver(edgeOptions);
        }
    }
    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        Map<String, Object> prefs = Map.of(
                "profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        //options.addArguments("--headless");
        return options;
    }
    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.addArguments("--disable-infobars");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        //firefoxOptions.addArguments("--remote-allow-origins=*");
        firefoxOptions.setAcceptInsecureCerts(true);
        //firefoxOptions.addArguments("--headless");
        return firefoxOptions;
    }
    private static EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("--disable-extensions");
        edgeOptions.addArguments("--disable-infobars");
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--remote-allow-origins=*");
        Map<String, Object> edgePrefs = Map.of("profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false, "profile.password_manager_enabled", false,
                "autofill.profile_enabled", false);
        edgeOptions.setExperimentalOption("prefs", edgePrefs);
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        //edgeOptions.addArguments("--headless");
        return edgeOptions;
    }

}
