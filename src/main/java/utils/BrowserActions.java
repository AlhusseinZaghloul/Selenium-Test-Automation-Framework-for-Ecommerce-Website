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
        LogsUtils.info("Navigated to URL: ", url);
    }
    /**
     * This method is used to get the current url of page.
     *
     * @param driver The WebDriver instance used to control the browser.
     */
    public static String getCurrentUrl(WebDriver driver) {
        LogsUtils.info("Current URL: ", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }
    /**
     * This method is used to get the current page title.
     *
     * @param driver The WebDriver instance used to control the browser.
     */
    public static String getPageTitle(WebDriver driver) {
        LogsUtils.info("Page title: ", driver.getTitle());
        return driver.getTitle();
    }
    /**
     * This method is used to refresh the current page.
     *
     * @param driver The WebDriver instance used to control the browser.
     */
    public static void refreshPage(WebDriver driver) {
        LogsUtils.info("Refreshing the page");
        driver.navigate().refresh();
    }
}
