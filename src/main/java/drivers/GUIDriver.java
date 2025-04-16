package drivers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utils.LogsUtils;

import static org.testng.AssertJUnit.fail;

/**
 * Utility class for managing Selenium WebDriver instances in a thread-safe manner.
 * Uses ThreadLocal to ensure each thread has its own WebDriver instance, making it suitable
 * for parallel test execution in automation frameworks.
 */
public class GUIDriver {
    //ThreadLocal storage for WebDriver instances, ensuring thread safety.
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Constructor for GUIDriver. Initializes a new WebDriver instance based on the provided browser name.
     *
     * @param browserName The name of the browser to initialize (e.g., "chrome", "firefox", "edge").
     */
    public GUIDriver(String browserName) {
        WebDriver driver=getDriver(browserName).startDriver();
        setDriver(driver);
    }
    /**
     * Retrieves the WebDriver instance associated with the current thread from the ThreadLocal.
     * If no driver has been initialized, logs an error and fails the test, indicating a setup issue.
     *
     * @return The WebDriver instance for the current thread.
     */

    @Step("Getting WebDriver instance")
    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            LogsUtils.error("Driver is null");
            fail("Driver is null");
        }
        return driverThreadLocal.get();
    }

    /**
     * Factory method to get the appropriate AbstractDriver implementation based on the browser name.
     *
     * @param browserName The name of the browser.
     * @return An instance of AbstractDriver for the specified browser.
     */
    @Step("Getting driver instance")
    private AbstractDriver getDriver(String browserName) {
        return switch (browserName.toLowerCase()) {
            case "chrome" -> new ChromeFactory();
            case "firefox" -> new FirefoxFactory();
            case "edge" -> new EdgeFactory();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        };
    }

    /**
     * Sets the provided WebDriver instance in the ThreadLocal for the current thread.
     *
     * @param driver The WebDriver instance to set.
     */
    @Step("Setting WebDriver instance")
    private void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    /**
     * Retrieves the WebDriver instance from the ThreadLocal. If no driver exists, it fails the test.
     * This method ensures the driver is available for use in the current thread.
     * @return The WebDriver instance for the current thread.
     */
    @Step("Getting WebDriver instance")
    public WebDriver get() {
        if (driverThreadLocal.get() == null) {
            LogsUtils.error("Driver is null");
            fail("Driver is null");
        }
        return driverThreadLocal.get();
    }

    /**
     * Quits the WebDriver instance associated with the current thread, if it exists, and
     * removes it from the ThreadLocal to clean up resources.
     */
    @Step("Quitting WebDriver")
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}