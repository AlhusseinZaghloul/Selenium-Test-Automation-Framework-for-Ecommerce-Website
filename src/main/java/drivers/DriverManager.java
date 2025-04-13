package drivers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.testng.AssertJUnit.fail;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
        super();
    }

    @Step("Setting up browser")
    public static WebDriver createInstance(String browserName) { // no usages
        WebDriver driver = BrowserFactory.getBrowser(browserName);
        setDriver(driver);
        return getDriver();
    }

    @Step("Getting WebDriver instance")
    public static WebDriver getDriver() { // 1 usage
        if (driverThreadLocal.get() == null) {
            fail("Driver is null");
        }
        return driverThreadLocal.get();
    }

    @Step("Setting WebDriver instance")
    public static void setDriver(WebDriver driver) { // 1 usage
        driverThreadLocal.set(driver);
    }



    @Step("Getting WebDriver instance")
    public static WebDriver getDriverThreadLocal() {
        if (driverThreadLocal.get() == null) {
            WebDriver driver = BrowserFactory.getBrowser("chrome"); // Default to Chrome
            setDriver(driver);
        }
        return driverThreadLocal.get();
    }

    @Step("Quitting WebDriver")
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}