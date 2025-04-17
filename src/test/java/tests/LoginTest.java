package tests;

import drivers.GUIDriver;
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
    String expectedUrl;
    String expectedErrorMessageForInvalidCredentials;
    String expectedErrorMessageForLockedOutUser;


    @BeforeClass
    public void init() {
        // Initialize the test data
        testData = new JsonUtils("testData");
        expectedUrl = getPropertyValue("homePageURL");
        expectedErrorMessageForInvalidCredentials = testData.getJsonData("error-messages.invalid-data");
        expectedErrorMessageForLockedOutUser = testData.getJsonData("error-messages.locked_out_user");
    }

    @BeforeMethod
    public void setUp() {
        browserName = PropertiesUtils.getPropertyValue("browserName");
        driver = new GUIDriver(browserName).get();
        new LoginPage(driver).openLoginPage(getPropertyValue("loginPageURL"));
    }

    @Test
    public void loginTest() {
        new LoginPage(driver)
                .enterUsername(testData.getJsonData("valid-login-credentials.username"))
                .enterPassword(testData.getJsonData("valid-login-credentials.password"))
                .clickLoginButton();

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl, "URL does not match after login");
    }

    @Test
    public void loginWithInvalidCredentials() {
        new LoginPage(driver)
                .enterUsername(testData.getJsonData("invalid-login-credentials.username"))
                .enterPassword(testData.getJsonData("invalid-login-credentials.password"))
                .clickLoginButton();

        String actualErrorMessage = new LoginPage(driver).getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessageForInvalidCredentials,
                "Error message does not match for invalid credentials");
    }

    @Test
    public void loginWithLockedOutUser() {
        new LoginPage(driver)
                .enterUsername(testData.getJsonData("locked-out-user.username"))
                .enterPassword(testData.getJsonData("locked-out-user.password"))
                .clickLoginButton();

        String actualErrorMessage = new LoginPage(driver).getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessageForLockedOutUser,
                "Error message does not match for locked out user");
    }

    @AfterMethod
    public void tearDown() {
        GUIDriver.quitDriver();
    }

}