package tests;

import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.createInstance("edge");
        new LoginPage(driver).openLoginPage();
    }

    @Test
    public void loginTest() {
        new LoginPage(driver)
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl, "URL does not match after login");
    }
    @Test
    public void loginWithInvalidCredentials() {
        new LoginPage(driver)
                .enterUsername("invalid_user")
                .enterPassword("invalid_password")
                .clickLoginButton();

        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        String actualErrorMessage = new LoginPage(driver).getErrorMessage();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message does not match");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

}