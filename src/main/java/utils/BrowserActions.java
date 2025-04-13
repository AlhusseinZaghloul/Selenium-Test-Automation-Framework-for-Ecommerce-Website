package utils;

import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private BrowserActions() {
        // Private constructor to prevent instantiation
    }
    /**
     * This method is used to navigate to a specified URL.
     *
     * @param driver The WebDriver instance used to control the browser.
     * @param url    The URL to navigate to.
     */
    public static void navigateToUrl(WebDriver driver, String url) {
        driver.get(url);
    }
}
