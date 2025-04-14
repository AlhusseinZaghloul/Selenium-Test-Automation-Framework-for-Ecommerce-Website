package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Scrolling {
    private Scrolling() {
        // Prevent instantiation
    }

    /**
     * Scrolls the page to the specified element.
     *
     * @param driver  The WebDriver instance.
     * @param locator The locator of the element to scroll to.
     */
    @Step("Scrolling to element: {locator}")
    public static void scrollToElement(WebDriver driver, By locator) {
        LogsUtils.info("Scrolling to element: ", locator.toString());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",
                ElementsActions.findElement(driver, locator));
    }
}
