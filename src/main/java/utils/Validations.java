package utils;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * This class contains static methods for various validation checks.
 * It is designed to be used in test cases to assert conditions and validate expected outcomes.
 * The methods use TestNG's Assert class for assertions.
 */
public class Validations {

    private Validations() {
        //private constructor
    }

    public static void validateTrue(boolean condition, String message) {
        LogsUtils.info("Validating that the condition is true");
        Assert.assertTrue(condition, message);
    }

    public static void validateFalse(boolean condition, String message) {
        LogsUtils.info("Validating that the condition is false");
        Assert.assertFalse(condition, message);
    }

    public static void validateEquals(String actual, String expected, String message) {
        LogsUtils.info("Validating that actual value is equal to expected value");
        Assert.assertEquals(actual, expected, message);
    }

    public static void validateNotEquals(String actual, String expected, String message) {
        LogsUtils.info("Validating that actual value is not equal to expected value");
        Assert.assertNotEquals(actual, expected, message);
    }

    public static void validatePageUrl(WebDriver driver, String expected, String message) {
        LogsUtils.info("Validating that the current URL is equal to the expected URL");
        Assert.assertEquals(BrowserActions.getCurrentURL(driver), expected,message);
    }

    public static void validatePageTitle(WebDriver driver, String expected) {
        LogsUtils.info("Validating that the page title is equal to the expected title");
        Assert.assertEquals(BrowserActions.getPageTitle(driver), expected);
    }
}
