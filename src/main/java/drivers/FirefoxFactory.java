package drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.PropertiesUtils;

/**
 * FirefoxFactory class is responsible for creating and configuring a Firefox WebDriver instance.
 * It extends the AbstractDriver class and implements the WebDriverOptionsAbstract interface.
 * The getOptions method configures various options for the Firefox driver, such as starting maximized,
 * disabling extensions, and setting the page load strategy. The startDriver method initializes
 * and returns a new instance of FirefoxDriver with the specified options.
 */
public class FirefoxFactory extends AbstractDriver implements WebDriverOptionsAbstract <FirefoxOptions>{
    @Override
    public FirefoxOptions getOptions() {
        // Configure Firefox-specific options for the WebDriver
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.addArguments("--disable-infobars");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        //firefoxOptions.addArguments("--remote-allow-origins=*");
        firefoxOptions.setAcceptInsecureCerts(true);
        if(!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")){
            firefoxOptions.addArguments("--headless");
        }
        return firefoxOptions;
    }

    @Override
    public WebDriver startDriver() {
        return new FirefoxDriver(getOptions());
    }
}
