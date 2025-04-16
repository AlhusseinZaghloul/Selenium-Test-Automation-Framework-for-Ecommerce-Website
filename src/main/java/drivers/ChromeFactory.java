package drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.PropertiesUtils;

import java.util.Map;

/**
 * ChromeFactory class is responsible for creating and configuring a Chrome WebDriver instance.
 * It extends the AbstractDriver class and implements the WebDriverOptionsAbstract interface.
 * The getOptions method sets various options for the Chrome browser, including arguments for
 * maximizing the window, disabling extensions, and handling notifications.
 * The startDriver method initializes and returns a new instance of ChromeDriver with the specified options.
 */
public class ChromeFactory extends AbstractDriver implements WebDriverOptionsAbstract <ChromeOptions>{
    @Override
    public ChromeOptions getOptions() {
        // Configure Chrome-specific options for the WebDriver
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
        if(!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")){
            options.addArguments("--headless");
        }
        return options;
    }

    @Override
    public WebDriver startDriver() {
        return new ChromeDriver(getOptions());
    }
}
