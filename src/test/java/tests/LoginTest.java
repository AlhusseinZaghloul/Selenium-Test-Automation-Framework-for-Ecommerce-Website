package tests;

import drivers.DriverManager;
import listeners.TestNGListeners;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.JsonUtils;
import utils.PropertiesUtils;

import static utils.PropertiesUtils.getPropertyValue;

@Listeners(TestNGListeners.class)
public class LoginTest {
    WebDriver driver;
    String browserName;
    JsonUtils testData;

    @BeforeClass
    public void init() {
        browserName = PropertiesUtils.getPropertyValue("browserType");
        testData = new JsonUtils("testData");
    }

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.createInstance(browserName);
        new LoginPage(driver).openLoginPage(getPropertyValue("loginPageURL"));
    }

    @Test
    public void loginTest() {
        new LoginPage(driver)
                .enterUsername(testData.getJsonData("valid-login-credentials.username"))
                .enterPassword(testData.getJsonData("valid-login-credentials.password"))
                .clickLoginButton();

        String expectedUrl = getPropertyValue("homePageURL");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl, "URL does not match after login");
    }

    @Test
    public void loginWithInvalidCredentials() {
        new LoginPage(driver)
                .enterUsername(testData.getJsonData("invalid-login-credentials.username"))
                .enterPassword(testData.getJsonData("invalid-login-credentials.password"))
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