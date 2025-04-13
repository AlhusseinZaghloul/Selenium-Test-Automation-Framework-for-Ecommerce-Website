package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


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
        driver.findElement(locator).sendKeys(data);
        LogsUtils.info("Data entered: ", data, " in element: ", locator.toString());
    }

    /**
     * Clicks on a specified web element after ensuring it's clickable.
     *
     * @param driver  The WebDriver instance.
     * @param locator The By locator of the target element.
     */
    @Step("Clicking on element: {locator}")
    public static void clicking(WebDriver driver, By locator) {
        Waits.waitForElementClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        driver.findElement(locator).click();
        LogsUtils.info("Clicked on element: ", locator.toString());
    }
    /**
     * Retrieves the text from a specified web element after ensuring it's visible.
     *
     * @param driver  The WebDriver instance.
     * @param locator The By locator of the target element.
     * @return The text content of the specified element as a String.
     */
    public static String getText(WebDriver driver, By locator) {
        Waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        String text = driver.findElement(locator).getText();
        LogsUtils.info("Getting text: ", text, " from element: ", locator.toString());
        return text;
    }
    /**
     * Retrieves a specified attribute's value from a web element after ensuring it's visible.
     *
     * @param driver    The WebDriver instance.
     * @param locator   The By locator of the target element.
     * @param attribute The name of the attribute to retrieve (e.g., "value", "href", "class").
     * @return The value of the specified attribute as a String.
     */
    @Step("Getting attribute '{attribute}' from element: {locator}")
    public static String getAttributeFromElement(WebDriver driver, By locator, String attribute) {
        Waits.waitForElementVisible(driver, locator);
        LogsUtils.info("Getting attribute: ", attribute, " from element: ", locator.toString());
        return driver.findElement(locator).getDomAttribute(attribute);
    }


}