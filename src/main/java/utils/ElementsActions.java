package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Utility class for performing common actions on web elements, such as sending keys, clicking,
 * retrieving text, and getting attributes. This class provides a layer of abstraction over
 * Selenium's WebDriver API, incorporating waits and logging for better test reliability and
 * debugging.
 */
public class ElementsActions {
    private ElementsActions() {
        // Prevent instantiation
    }

    /**
     * Sends keys to a specified web element after ensuring it's visible.
     *
     * @param driver  The WebDriver instance.
     * @param locator The By locator of the target element.
     * @param data    The string data to send to the element.
     */
    @Step("Sending data: '{data}' to element: {locator}")
    public static void sendData(WebDriver driver, By locator, String data) {
        Waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver, locator).sendKeys(data);
        LogsUtils.info("Data entered: ", data, " in element: ", locator.toString());
    }

    /**
     * Clicks on the web element identified by the given locator after ensuring it is clickable
     * and scrolled into view.
     *
     * @param driver  The WebDriver instance used to interact with the browser.
     * @param locator The By locator used to identify the target web element.
     */
    @Step("Clicking on element: {locator}")
    public static void clicking(WebDriver driver, By locator) {
        Waits.waitForElementClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver, locator).click();
        LogsUtils.info("Clicked on element: ", locator.toString());
    }

    /**
     * Retrieves the text content from the web element identified by the given locator after
     * ensuring it is visible and scrolled into view.
     *
     * @param driver  The WebDriver instance used to interact with the browser.
     * @param locator The By locator used to identify the target web element.
     * @return The text content of the specified element as a String.
     */
    @Step("Getting text from element: {locator}")
    public static String getText(WebDriver driver, By locator) {
        Waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        String text = findElement(driver, locator).getText();
        LogsUtils.info("Getting text: ", text, " from element: ", locator.toString());
        return text;
    }

    /**
     * Retrieves the value of the specified attribute from the web element identified by the given
     * locator after ensuring it is visible.
     *
     * @param driver    The WebDriver instance used to interact with the browser.
     * @param locator   The By locator used to identify the target web element.
     * @param attribute The name of the attribute to retrieve (e.g., "value", "href", "class").
     * @return The value of the specified attribute as a String.
     */
    @Step("Getting attribute '{attribute}' from element: {locator}")
    public static String getAttributeFromElement(WebDriver driver, By locator, String attribute) {
        Waits.waitForElementVisible(driver, locator);
        LogsUtils.info("Getting attribute: ", attribute, " from element: ", locator.toString());
        return findElement(driver, locator).getDomAttribute(attribute);
    }

    /**
     * Finds a web element using the specified locator.
     *
     * @param driver  The WebDriver instance.
     * @param locator The By locator of the target element.
     * @return The found WebElement.
     */
    @Step("Finding element: {locator}")
    public static WebElement findElement(WebDriver driver, By locator) {
        LogsUtils.info( "Finding element: ", locator.toString());
        return driver.findElement(locator);
    }


}