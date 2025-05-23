package tests;

import drivers.GUIDriver;
import listeners.TestNGListeners;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.JsonUtils;
import utils.PropertiesUtils;
import utils.Validations;

import static utils.PropertiesUtils.getPropertyValue;

@Listeners(TestNGListeners.class)
public class LoginTest {
    WebDriver driver;
    String browserName;
    JsonUtils testData;
    String expectedURL;
    String expectedErrorMessageForInvalidCredentials;
    String expectedErrorMessageForLockedOutUser;


    @BeforeClass
    public void init() {
        // Initialize the test data
        testData = new JsonUtils("testData");
        expectedURL = getPropertyValue("homePageURL");
        expectedErrorMessageForInvalidCredentials = testData.getJsonData("error-messages.invalid-data");
        expectedErrorMessageForLockedOutUser = testData.getJsonData("error-messages.locked_out_user");
    }

    @BeforeMethod
    public void setUp() {
        browserName = PropertiesUtils.getPropertyValue("browserName");
        driver = new GUIDriver(browserName).get();
        new LoginPage(driver).openLoginPage(getPropertyValue("loginPageURL"));
    }

    @Test(description = "Verify that the user can log in with valid credentials")
    public void loginTest() {
        new LoginPage(driver)
                .enterUsername(testData.getJsonData("valid-login-credentials.username"))
                .enterPassword(testData.getJsonData("valid-login-credentials.password"))
                .clickLoginButton();

        String actualURL = driver.getCurrentUrl();
        Validations.validatePageUrl(driver, actualURL,"URL does not match after login");
    }

    @Test(description = "Verify that the user cannot log in with invalid credentials")
    public void loginWithInvalidCredentials() {
        new LoginPage(driver)
                .enterUsername(testData.getJsonData("invalid-login-credentials.username"))
                .enterPassword(testData.getJsonData("invalid-login-credentials.password"))
                .clickLoginButton();

        String actualErrorMessage = new LoginPage(driver).getErrorMessage();
        Validations.validateEquals(actualErrorMessage, expectedErrorMessageForInvalidCredentials,
                "Error message does not match for invalid credentials");
    }

    @Test(description = "Verify that the user cannot log in with locked out user")
    public void loginWithLockedOutUser() {
        new LoginPage(driver)
                .enterUsername(testData.getJsonData("locked-out-user.username"))
                .enterPassword(testData.getJsonData("locked-out-user.password"))
                .clickLoginButton();

        String actualErrorMessage = new LoginPage(driver).getErrorMessage();
        Validations.validateEquals(actualErrorMessage, expectedErrorMessageForLockedOutUser,
                "Error message does not match for locked out user");
    }

    @AfterMethod
    public void tearDown() {
        GUIDriver.quitDriver();
    }

}