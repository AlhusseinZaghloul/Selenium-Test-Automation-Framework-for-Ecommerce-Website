package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Scrolling {
    private Scrolling() {
        // Prevent instantiation
    }

    public static void scrollToElement(WebDriver driver, By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",
                ElementsActions.findElement(driver, locator));
    }
}
