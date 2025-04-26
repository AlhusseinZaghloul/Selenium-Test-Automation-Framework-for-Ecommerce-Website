package tests;

import drivers.GUIDriver;
import listeners.TestNGListeners;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.CheckoutComplete;
import pages.LoginPage;
import utils.CustomSoftAssertion;
import utils.JsonUtils;
import utils.Validations;
import static utils.PropertiesUtils.getPropertyValue;
import static utils.TImeStampUtils.getTimeStamp;

/**
 * This class contains end-to-end test scenarios for the application.
 * It includes an end-to-end scenario for adding products to the cart, checking out, and completing the purchase.
 */
@Listeners(TestNGListeners.class)
public class EndToEndScenariosTest {
    WebDriver driver;
    String browserName;
    JsonUtils testData;
    String username, password, homePageURL, expectedCompleteOrderMessage;
    // product names from JSON file
    String productName1, expectedPrice1;
    // shipping data from JSON file
    String firstName, lastName, postalCode;

    @BeforeClass
    public void init() {
        browserName = getPropertyValue("browserName");
        testData = new JsonUtils("testData");
        homePageURL = getPropertyValue("homePageURL");

        // Load login credentials from JSON
        username = testData.getJsonData("valid-login-credentials.username");
        password = testData.getJsonData("valid-login-credentials.password");

        // Load product name from JSON
        productName1 = testData.getJsonData("valid-products.product-1.name");

        // Load expected price from JSON
        expectedPrice1 = testData.getJsonData("valid-products.product-1.price");

        // Load shipping data from JSON
        firstName = testData.getJsonData("checkout-information.first-name") + getTimeStamp();
        lastName = testData.getJsonData("checkout-information.last-name") + getTimeStamp();
        postalCode = testData.getJsonData("checkout-information.postal-code");

        // Load expected complete order message from JSON
        expectedCompleteOrderMessage = testData.getJsonData("checkout-complete-message");

    }

    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver(browserName).get();
    }

    @Test(description = "End to End Scenario: Add products to cart, checkout and finish")
    public void fullWorkFlow() {
        new LoginPage(driver)
                .openLoginPage(getPropertyValue("loginPageURL"))
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton()
                .addProductToCart(productName1)
                .clickOnCartIcon()
                .clickOnCheckoutButton()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostalCode(postalCode)
                .clickOnContinueButton()
                .clickOnFinishButton();

        String actualText = new CheckoutComplete(driver).getCheckoutCompleteText();
        Validations.validateEquals(actualText, expectedCompleteOrderMessage, "Checkout complete message mismatch");

    }

    @AfterMethod
    public void tearDown() {
        GUIDriver.quitDriver();
        CustomSoftAssertion.customAssertAll();
    }

}
