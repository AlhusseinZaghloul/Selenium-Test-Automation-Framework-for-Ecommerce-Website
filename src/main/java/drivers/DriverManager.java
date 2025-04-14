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
public class DriverManager {
    //ThreadLocal storage for WebDriver instances, ensuring thread safety.
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // Private constructor to prevent instantiation of this utility class.
    private DriverManager() {
        super();
    }

    /**
     * Creates a new WebDriver instance for the specified browser, sets it in the ThreadLocal,
     * and returns it. This method allows explicit initialization of the WebDriver with a
     * specific browser type.
     *
     * @param browserName The name of the browser to create the driver for (e.g., "chrome", "firefox").
     * @return The created WebDriver instance.
     */
    @Step("Setting up browser")
    public static WebDriver createInstance(String browserName) { // no usages
        WebDriver driver = BrowserFactory.getBrowser(browserName);
        LogsUtils.info("Creating driver instance for browser: ", browserName);
        setDriver(driver);
        return getDriver();
    }

    /**
     * Retrieves the WebDriver instance associated with the current thread from the ThreadLocal.
     * If no driver has been initialized, logs an error and fails the test, indicating a setup issue.
     *
     * @return The WebDriver instance for the current thread.
     */
    @Step("Getting WebDriver instance")
    public static WebDriver getDriver() { // 1 usage
        if (driverThreadLocal.get() == null) {
            LogsUtils.error("Driver is null");
            fail("Driver is null");
        }
        return driverThreadLocal.get();
    }

    /**
     * Sets the provided WebDriver instance in the ThreadLocal for the current thread.
     *
     * @param driver The WebDriver instance to set.
     */
    @Step("Setting WebDriver instance")
    public static void setDriver(WebDriver driver) { // 1 usage
        driverThreadLocal.set(driver);
    }

    /**
     * Retrieves the WebDriver instance from the ThreadLocal. If no driver exists, lazily
     * initializes a new Chrome driver by default, sets it, and returns it. This method
     * provides a fallback mechanism for driver initialization.
     *
     * @return The WebDriver instance for the current thread.
     */
    @Step("Getting WebDriver instance")
    public static WebDriver getDriverThreadLocal() {
        if (driverThreadLocal.get() == null) {
            WebDriver driver = BrowserFactory.getBrowser("chrome"); // Default to Chrome
            setDriver(driver);
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